package com.trialpay.android.base;

import android.app.Activity;
import android.content.Context;
import com.trialpay.android.BaseTrialpayManager;
import com.trialpay.android.BaseTrialpayManager.DisplayMode;
import com.trialpay.android.BaseTrialpayManager.Gender;
import com.trialpay.android.DealspotView;
import com.trialpay.android.Video;
import com.trialpay.android.Video.VideoCachingClient;
import java.util.ArrayList;
import java.util.List;

public class TrialpayManager extends BaseTrialpayManager {
    protected Activity currentActivity = null;
    private final List<EventListener> eventListeners = new ArrayList();

    public static abstract class EventListener {
        @Deprecated
        public void onEvent(String message) {
        }

        public void onOpen(String touchpointName) {
            onEvent(BaseTrialpayManager.OFFERWALL_OPEN);
        }

        public void onClose(String touchpointName) {
            onEvent(BaseTrialpayManager.OFFERWALL_CLOSE);
        }

        public void onBalanceUpdate(String touchpointName) {
            onEvent(BaseTrialpayManager.BALANCE_UPDATE);
        }

        public void onCloseAndBalanceUpdate(String touchpointName) {
            onBalanceUpdate(touchpointName);
            onClose(touchpointName);
        }
    }

    protected Activity getCurrentActivity() {
        return this.currentActivity;
    }

    public String getSdkVer() {
        return "sdk." + super.getSdkVer();
    }

    @Deprecated
    public void setOnEventListener(EventListener eventListener) {
        addEventListener(eventListener);
    }

    public void addEventListener(EventListener eventListener) {
        this.eventListeners.add(eventListener);
    }

    public void removeEventListener(EventListener eventListener) {
        this.eventListeners.remove(eventListener);
    }

    public void handleCloseOfferwallView(String touchpointName) {
        super.handleCloseOfferwallView(touchpointName);
        if (this.eventListeners != null) {
            boolean handleReward = getBalance(getVic(touchpointName)) > 0;
            for (EventListener eventListener : this.eventListeners) {
                if (handleReward) {
                    eventListener.onCloseAndBalanceUpdate(touchpointName);
                } else {
                    eventListener.onClose(touchpointName);
                }
            }
        }
    }

    public void handleOpenOfferwallView(String touchpointName) {
        super.handleOpenOfferwallView(touchpointName);
        if (this.eventListeners != null) {
            for (EventListener eventListener : this.eventListeners) {
                eventListener.onOpen(touchpointName);
            }
        }
    }

    protected void handleBalanceUpdated(String vic) {
        super.handleBalanceUpdated(vic);
        final String touchpointName = getTouchpoint(vic);
        if (this.eventListeners != null) {
            for (final EventListener eventListener : this.eventListeners) {
                getCurrentActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        eventListener.onBalanceUpdate(touchpointName);
                    }
                });
            }
        }
    }

    public static TrialpayManager getInstance(Activity activity) {
        TrialpayManager trialpayManager = (TrialpayManager) BaseTrialpayManager.getInstance(TrialpayManager.class);
        trialpayManager.currentActivity = activity;
        trialpayManager.appLoaded();
        return trialpayManager;
    }

    public void registerVic(String touchpointName, String vic) {
        super.registerVic(touchpointName, vic);
    }

    public void open(String touchpointName) {
        super.open(touchpointName);
    }

    public void openPopup(String touchpointName) {
        super.open(touchpointName, DisplayMode.TP_POPUP_MODE);
    }

    public void openOfferwall(String touchpointName) {
        super.open(touchpointName, DisplayMode.TP_FULLSCREEN_MODE);
    }

    public DealspotView createDealspotView(Context baseContext, String touchpointName) {
        return super.createDealspotView(baseContext, touchpointName);
    }

    public void setSid(String sid) {
        super.setSid(sid);
    }

    public String getSid() {
        return super.getSid();
    }

    public void initiateBalanceChecks() {
        super.initiateBalanceChecks();
    }

    public int withdrawBalance(String touchpointName) {
        return super.withdrawBalance(touchpointName);
    }

    public void startAvailabilityCheck(String touchpointName) {
        super.startAvailabilityCheck(touchpointName);
    }

    public boolean isAvailable(String touchpointName) {
        return super.isAvailable(touchpointName);
    }

    public void setAge(int age) {
        super.setAge(age);
    }

    public void setGender(Gender gender) {
        super.setGender(gender);
    }

    public void updateLevel(int level) {
        super.updateLevel(level);
    }

    public void setCustomParam(String paramName, String paramValue) {
        super.setCustomParam(paramName, paramValue);
    }

    public void clearCustomParam(String paramName) {
        super.clearCustomParam(paramName);
    }

    public void updateVcPurchaseInfo(String touchpointName, float dollarAmount, int vcAmount) {
        super.updateVcPurchaseInfo(touchpointName, dollarAmount, vcAmount);
    }

    public void updateVcBalance(String touchpointName, int vcAmount) {
        super.updateVcBalance(touchpointName, vcAmount);
    }

    public void setVideoCachingClient(VideoCachingClient newVideoCachingClient) {
        Video.setVideoCachingClient(newVideoCachingClient);
    }
}
