package com.trialpay.android;

import android.util.Log;
import com.trialpay.android.VcBalanceHttpClient.InvalidResponseException;
import com.trialpay.android.VcBalanceHttpClient.ServerErrorException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

public class BalanceQueryAndWithdrawTask implements Runnable {
    private static final long ERROR_WAIT_DEFAULT_VALUE = 10;
    private static final long ERROR_WAIT_MAX = 604800;
    private static final double ERROR_WAIT_MULTIPLIER = 2.0d;
    private static final long PING_FREQ_LOW_LIMIT_SECS = 5;
    private static final String TAG = "Trialpay.BalanceQueryAndWithdrawTask";
    private long errorWait = ERROR_WAIT_DEFAULT_VALUE;
    private boolean pauseFlag = false;
    private int pauseTimeout = 0;
    private RescheduleFlag rescheduleFlag = RescheduleFlag.NONE;
    private boolean shutdownFlag;
    private final Map<String, VcBalance> trialpayBalances = new HashMap();
    private final BaseTrialpayManager trialpayManager;

    private enum RescheduleFlag {
        NONE,
        SOFT,
        HARD
    }

    public BalanceQueryAndWithdrawTask(BaseTrialpayManager trialpayManager) {
        this.trialpayManager = trialpayManager;
        this.errorWait = ERROR_WAIT_DEFAULT_VALUE;
    }

    private Long checkBalance() {
        Log.v(TAG, "checkBalance()");
        if (this.trialpayManager.getSid() == null) {
            Log.e(TAG, "SID is unavailable");
            return null;
        }
        VcBalanceHttpClient queryClient = createVcBalanceHttpClient(this.trialpayManager.getSid());
        for (String vic : this.trialpayManager.getVics()) {
            if (!this.trialpayBalances.containsKey(vic)) {
                this.trialpayBalances.put(vic, new VcBalance(vic, this.trialpayManager));
            }
            ((VcBalance) this.trialpayBalances.get(vic)).scheduleQuery(queryClient);
        }
        if (queryClient.getRequestVics().length == 0) {
            return Long.valueOf(PING_FREQ_LOW_LIMIT_SECS);
        }
        try {
            queryClient.execQueryRequest();
            VcBalanceHttpClient ackClient = createVcBalanceHttpClient(this.trialpayManager.getSid());
            for (String vic2 : queryClient.getRequestVics()) {
                ((VcBalance) this.trialpayBalances.get(vic2)).onQueryResponse(queryClient, ackClient);
            }
            if (ackClient.getRequestVics().length > 0) {
                try {
                    ackClient.execAckRequest();
                } catch (IOException e) {
                    Log.e(TAG, "Ack request: IO error, no further processing", e);
                    return null;
                } catch (JSONException e2) {
                    Log.e(TAG, "Ack request: JSON error, no further processing", e2);
                    return null;
                } catch (ServerErrorException e3) {
                    Log.e(TAG, "Ack request: Server returned " + e3.getMessage() + " error, no further processing");
                    return null;
                } catch (InvalidResponseException e4) {
                    Log.e(TAG, "Query request: Invalid Response [" + e4.getMessage() + "], no further processing");
                    return null;
                }
            }
            for (String vic22 : ackClient.getRequestVics()) {
                ((VcBalance) this.trialpayBalances.get(vic22)).onAckResponse(ackClient);
            }
            long minWait = ERROR_WAIT_MAX;
            long now = Utils.getUnixTimestamp();
            for (VcBalance balance : this.trialpayBalances.values()) {
                long nextPingTimeSecs = balance.calculateNextPingTimeSecs();
                if (nextPingTimeSecs != 0) {
                    long wait = Math.max(nextPingTimeSecs - now, 0);
                    if (wait < minWait) {
                        minWait = wait;
                    }
                }
            }
            return Long.valueOf(minWait);
        } catch (IOException e5) {
            Log.w(TAG, "Query request: IO error [" + e5.getMessage() + "], no further processing", e5);
            return null;
        } catch (JSONException e22) {
            Log.e(TAG, "Query request: JSON error [" + e22.getMessage() + "], no further processing", e22);
            return null;
        } catch (ServerErrorException e32) {
            Log.w(TAG, "Query request: Server returned [" + e32.getMessage() + "] error, no further processing");
            return null;
        } catch (InvalidResponseException e42) {
            Log.e(TAG, "Query request: Invalid Response [" + e42.getMessage() + "], no further processing");
            return null;
        }
    }

    private void resetBalanceSchedule(RescheduleFlag mode) {
        if (mode == RescheduleFlag.SOFT) {
            for (VcBalance balance : this.trialpayBalances.values()) {
                balance.reschedule();
            }
        } else {
            Utils.assertTrue(mode != RescheduleFlag.NONE, "Should have never happened", TAG);
            this.trialpayBalances.clear();
        }
        this.errorWait = ERROR_WAIT_DEFAULT_VALUE;
    }

    public void run() {
        while (this.trialpayManager != null) {
            if (this.rescheduleFlag != RescheduleFlag.NONE) {
                resetBalanceSchedule(this.rescheduleFlag);
                this.rescheduleFlag = RescheduleFlag.NONE;
            }
            if (this.shutdownFlag) {
                this.shutdownFlag = false;
                return;
            }
            checkPause();
            Long secondsValid = checkBalance();
            if (secondsValid == null) {
                secondsValid = Long.valueOf(this.errorWait);
                this.errorWait = (long) (((double) this.errorWait) * ERROR_WAIT_MULTIPLIER);
                if (this.errorWait > ERROR_WAIT_MAX) {
                    this.errorWait = ERROR_WAIT_MAX;
                }
            } else {
                this.errorWait = ERROR_WAIT_DEFAULT_VALUE;
            }
            if (secondsValid.longValue() < PING_FREQ_LOW_LIMIT_SECS) {
                secondsValid = Long.valueOf(PING_FREQ_LOW_LIMIT_SECS);
            }
            synchronized (this) {
                try {
                    Log.v(TAG, String.format("%d second(s) until next ping", new Object[]{secondsValid}));
                    wait(secondsValid.longValue() * 1000);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void resume() {
        Log.v(TAG, "resume balance checks");
        this.pauseTimeout = 0;
        this.pauseFlag = false;
    }

    public void pause(int pauseTimeout) {
        Log.v(TAG, "pause balance checks");
        this.pauseTimeout = pauseTimeout;
        this.pauseFlag = true;
    }

    private synchronized void checkPause() {
        int spentTime = 0;
        while (this.pauseFlag && spentTime < this.pauseTimeout) {
            try {
                wait(1000);
                spentTime++;
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        resume();
    }

    public synchronized void reschedule(boolean isHard) {
        resume();
        this.rescheduleFlag = isHard ? RescheduleFlag.HARD : RescheduleFlag.SOFT;
        notify();
    }

    public synchronized void shutdown() {
        resume();
        this.shutdownFlag = true;
        notify();
    }

    protected VcBalanceHttpClient createVcBalanceHttpClient(String sid) {
        return new VcBalanceHttpClient(sid);
    }
}
