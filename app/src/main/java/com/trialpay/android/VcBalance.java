package com.trialpay.android;

import android.util.Log;
import com.facebook.BuildConfig;
import com.trialpay.android.VcBalanceHttpClient.DataChunk;

public class VcBalance {
    private static final String TAG = "Trialpay.VcBalance";
    private final int EARLY_PING_BUFFER_SECS = 5;
    private final int RECOVERABLE_ERROR_DELAY_SECS = 10;
    private DataChunk currentServerBalanceData;
    private long lastFatalErrorTime;
    private long lastQueryTime;
    private long lastRecoverableErrorTime;
    private final BaseTrialpayManager trialpayManager;
    private final String vic;

    public VcBalance(String vic, BaseTrialpayManager trialpayManager) {
        this.vic = vic;
        this.trialpayManager = trialpayManager;
    }

    public long calculateNextPingTimeSecs() {
        long currentTime = Utils.getUnixTimestamp();
        if (this.lastFatalErrorTime != 0) {
            return 0;
        }
        if (this.lastRecoverableErrorTime != 0) {
            return this.lastRecoverableErrorTime + 10;
        }
        if (this.lastQueryTime == 0 || this.currentServerBalanceData == null || this.currentServerBalanceData.secondsValid == null) {
            return currentTime;
        }
        return this.lastQueryTime + ((long) this.currentServerBalanceData.secondsValid.intValue());
    }

    public boolean scheduleQuery(VcBalanceHttpClient queryClient) {
        long currentTime = Utils.getUnixTimestamp();
        long nextPingTime = calculateNextPingTimeSecs();
        if (nextPingTime == 0) {
            return false;
        }
        if (nextPingTime - currentTime > 5) {
            Log.v(TAG, String.format("[%s] waiting %d sec(s)", new Object[]{this.vic, Long.valueOf(nextPingTime - currentTime)}));
            return false;
        } else if (queryClient.hasVicInRequest(this.vic)) {
            Log.w(TAG, String.format("[%s] already scheduled, skip", new Object[]{this.vic}));
            return false;
        } else {
            queryClient.addRequestParams(this.vic);
            return true;
        }
    }

    public boolean onQueryResponse(VcBalanceHttpClient queryClient, VcBalanceHttpClient ackClient) {
        this.lastQueryTime = Utils.getUnixTimestamp();
        DataChunk response = queryClient.getResponse(this.vic);
        if (response == null) {
            raiseErrorFlag(false, "not found in \"query\" response");
            return false;
        }
        boolean z = response.vic != null && response.vic.equals(this.vic);
        Utils.assertTrue(z, "vic must be same", TAG);
        if (response.error != null) {
            if (response.error.equals(Strings.BALANCE_API_INVALID_VIC)) {
                raiseErrorFlag(true, "The VIC that was set is either not defined on TrialPay's servers or is not set to work with the Balance API.");
                return false;
            }
            raiseErrorFlag(false, String.format("%s error in \"query\" response", new Object[]{response.error}));
            return false;
        } else if (response.secondsValid == null || response.secondsValid.intValue() < 0) {
            raiseErrorFlag(false, "invalid seconds_valid parameter in response");
            return false;
        } else if (response.diffBalance == null || response.diffBalance.intValue() < 0) {
            raiseErrorFlag(false, "invalid balance parameter in response");
            return false;
        } else if (response.lastTransactionId == null) {
            raiseErrorFlag(false, "invalid last_transaction_id parameter in response");
            return false;
        } else {
            this.currentServerBalanceData = response;
            dropErrorFlag(false);
            if (this.currentServerBalanceData.diffBalance.intValue() > 0) {
                DataChunk dc = new DataChunk(this.currentServerBalanceData);
                dc.secondsValid = null;
                ackClient.addRequestParams(dc);
            }
            return true;
        }
    }

    public boolean onAckResponse(VcBalanceHttpClient ackClient) {
        DataChunk response = ackClient.getResponse(this.vic);
        if (response == null) {
            raiseErrorFlag(false, "not found in \"query\" response");
            return false;
        } else if (response.error != null) {
            raiseErrorFlag(false, String.format("%s error in \"ack\" response", new Object[]{response.error}));
            return false;
        } else if (response.success == null) {
            raiseErrorFlag(false, "no success param");
            return false;
        } else if (response.success.booleanValue()) {
            this.trialpayManager.addToBalance(this.vic, this.currentServerBalanceData.diffBalance.intValue(), this.currentServerBalanceData.vics);
            if (this.trialpayManager.getBalance(this.vic) > 0) {
                this.trialpayManager.handleBalanceUpdated(this.vic);
            }
            this.currentServerBalanceData.diffBalance = Integer.valueOf(0);
            return true;
        } else {
            raiseErrorFlag(false, "invalid success param value");
            return false;
        }
    }

    public void reschedule() {
        this.currentServerBalanceData = null;
        this.lastQueryTime = 0;
        dropErrorFlag(false);
    }

    private void raiseErrorFlag(boolean isFatal, String logMsg) {
        String str = TAG;
        String str2 = "[%s] %s%s";
        Object[] objArr = new Object[3];
        objArr[0] = this.vic;
        objArr[1] = isFatal ? "[F] " : BuildConfig.VERSION_NAME;
        objArr[2] = logMsg;
        Log.e(str, String.format(str2, objArr));
        if (isFatal) {
            this.lastFatalErrorTime = Utils.getUnixTimestamp();
        } else {
            this.lastRecoverableErrorTime = Utils.getUnixTimestamp();
        }
    }

    private void dropErrorFlag(boolean isFatal) {
        if (isFatal) {
            this.lastFatalErrorTime = 0;
            Log.e(TAG, String.format("[%s] fatal flag is dropped", new Object[]{this.vic}));
            return;
        }
        this.lastRecoverableErrorTime = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append("vic=").append(this.vic).append("|");
        sb.append("lastQueryTime=").append(this.lastQueryTime).append("|");
        sb.append("lastRecoverableErrorTime=").append(this.lastRecoverableErrorTime).append("|");
        sb.append("lastFatalErrorTime=").append(this.lastFatalErrorTime).append("|");
        sb.append("currentServerBalanceData=").append(this.currentServerBalanceData == null ? "null" : this.currentServerBalanceData.toString()).append("]");
        return sb.toString();
    }
}
