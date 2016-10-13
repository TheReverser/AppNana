package com.trialpay.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.util.Log;
import com.appnana.android.giftcardrewards.R;
import com.facebook.BuildConfig;
import com.facebook.GraphRequest;
import com.trialpay.android.OfferwallView.EventListener;
import com.trialpay.android.UrlManager.Url;
import com.vungle.log.Logger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class BaseTrialpayManager implements EventListener {
    public static final String BALANCE_UPDATE = "balance_update";
    public static final String OFFERWALL_CLOSE = "offerwall_close";
    public static final String OFFERWALL_OPEN = "offerwall_open";
    private static final String PREF_DB_NAME = "TrialpayDb";
    private static final String TAG = "Trialpay.BaseTrialpayManager";
    private static BaseTrialpayManager instance;
    private static TrialpayPreferences preferences;
    private BalanceQueryAndWithdrawTask balanceQueryAndWithdrawTask = null;
    private HashMap<String, String> customParamMap = null;
    private boolean enabledBalanceChecks = false;
    private boolean isAppLoadedCalled;
    private Map<String, OfferAvailabilityCheckTask> offer_tasks = null;
    StateListener stateListener = null;
    public Map<String, String> touchpointsIntegrationTypes = new HashMap();

    public interface StateListener {
        void onBecameBackground();

        void onBecameForeground();
    }

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$trialpay$android$BaseTrialpayManager$DisplayMode = new int[DisplayMode.values().length];

        static {
            try {
                $SwitchMap$com$trialpay$android$BaseTrialpayManager$DisplayMode[DisplayMode.TP_FULLSCREEN_MODE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$trialpay$android$BaseTrialpayManager$DisplayMode[DisplayMode.TP_POPUP_MODE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum DisplayMode {
        TP_UNKNOWN_MODE,
        TP_FULLSCREEN_MODE,
        TP_POPUP_MODE;

        public static int toInt(DisplayMode mode) {
            switch (AnonymousClass2.$SwitchMap$com$trialpay$android$BaseTrialpayManager$DisplayMode[mode.ordinal()]) {
                case com.vungle.sdk.VunglePub.Gender.FEMALE /*1*/:
                    return 1;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    return 2;
                default:
                    return 0;
            }
        }

        public static DisplayMode toDisplayMode(int mode) {
            switch (mode) {
                case com.vungle.sdk.VunglePub.Gender.FEMALE /*1*/:
                    return TP_FULLSCREEN_MODE;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    return TP_POPUP_MODE;
                default:
                    return TP_UNKNOWN_MODE;
            }
        }
    }

    public enum Gender {
        MALE('2'),
        FEMALE('1'),
        UNKNOWN('0');
        
        private final char c;

        private Gender(char c) {
            this.c = c;
        }

        public char toChar() {
            return this.c;
        }

        public static Gender charToEnum(char c) {
            switch (c) {
                case R.styleable.SherlockTheme_windowMinWidthMajor /*48*/:
                    return UNKNOWN;
                case R.styleable.SherlockTheme_windowMinWidthMinor /*49*/:
                case 'F':
                    return FEMALE;
                case GraphRequest.MAXIMUM_BATCH_SIZE /*50*/:
                case 'M':
                    return MALE;
                default:
                    Log.e(BaseTrialpayManager.TAG, String.format("Can't resolve '%c' to gender", new Object[]{Character.valueOf(c)}));
                    return UNKNOWN;
            }
        }
    }

    protected abstract Activity getCurrentActivity();

    protected BaseTrialpayManager() {
    }

    protected static BaseTrialpayManager getInstance(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (instance == null) {
            try {
                instance = (BaseTrialpayManager) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e) {
                Log.e("TrialpayManager", "Failed to create an instance for" + cls.getName() + ". Error: " + e.getMessage());
                return null;
            }
        } else if (!cls.isInstance(instance)) {
            Log.e("TrialpayManager", "Trying to get an instance for " + cls.getName() + " while holding " + instance.getClass().getName());
            return null;
        }
        return instance;
    }

    public void appLoaded() {
        if (!this.isAppLoadedCalled) {
            TrialpayPreferences pref = getPreferences();
            long now = Utils.getUnixTimestamp();
            if (!pref.hasCreationTime()) {
                pref.setCreationTime(now);
            }
            pref.logVisitTime(now);
            Utils.initGaid(getContext());
            UrlManager.getInstance().resolveUa(getContext());
            this.isAppLoadedCalled = true;
        }
    }

    public static BaseTrialpayManager getInstance() {
        return instance;
    }

    protected Context getContext() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            return activity.getApplicationContext();
        }
        return null;
    }

    private TrialpayPreferences getPreferences() {
        if (preferences == null) {
            Context ctx = getContext();
            if (ctx != null) {
                preferences = new TrialpayPreferences(ctx.getSharedPreferences(PREF_DB_NAME, 0));
            }
        }
        return preferences;
    }

    public void reset() {
        getPreferences().clearAll();
        resetBalanceActivities(true);
    }

    protected void setSid(String sid) {
        Log.v(TAG, "setSid(" + sid + ")");
        getPreferences().setSid(sid);
    }

    @SuppressLint({"SimpleDateFormat"})
    protected String getSid() {
        String sid = getPreferences().getSid(null);
        if (sid != null) {
            return sid;
        }
        sid = Utils.toSHA1(Integer.toString(new Random().nextInt(100000)) + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        getPreferences().setSid(sid);
        Log.v(TAG, "Generated sid=" + sid);
        return sid;
    }

    protected void registerVic(String touchpointName, String vic) {
        Log.v(TAG, "registerVic(" + touchpointName + "," + vic + ")");
        if (vic == null || vic.length() == 0) {
            throw new NullPointerException("Provide a valid (non-null) vic for TrialpayManager::registerVic(touchpointName,vic)");
        } else if (touchpointName == null || touchpointName.length() == 0) {
            throw new NullPointerException("Provide a valid (non-null) touchpointName for TrialpayManager::registerVic(touchpointName,vic)");
        } else {
            String oldVic = getVic(touchpointName);
            if (oldVic == null || !oldVic.equals(vic)) {
                if (oldVic != null) {
                    Log.w(TAG, "Reassigning touchpoint to vic '" + vic + "' (previously '" + oldVic + "')");
                }
                TrialpayPreferences pref = getPreferences();
                pref.startTransaction();
                String oldTouchpoint = getTouchpoint(vic);
                if (oldTouchpoint != null) {
                    Log.w(TAG, "Reassigning vic to touchpoint '" + touchpointName + "' (previously '" + oldTouchpoint + "')");
                    pref.removeTouchpoint(oldTouchpoint);
                }
                pref.addVicResolver(touchpointName, vic);
                pref.commit();
                startAvailabilityCheck(touchpointName);
                return;
            }
            startAvailabilityCheck(touchpointName);
        }
    }

    public List<String> getVics() {
        return getPreferences().getVics();
    }

    protected String getVic(String touchpointName) {
        return getPreferences().resolveVic(touchpointName, null);
    }

    protected String getTouchpoint(String vic) {
        TrialpayPreferences prefs = getPreferences();
        if (prefs != null) {
            return prefs.getTouchpoint(vic);
        }
        return null;
    }

    protected void registerTouchpointUrl(String landingPageUrl, String touchpointName) {
        TrialpayPreferences pref = getPreferences();
        pref.startTouchpointAccess(touchpointName);
        pref.setTouchpointUrl(landingPageUrl);
        pref.closeCurrentTouchpoint();
    }

    protected String getTouchpointUrl(String touchpointName) {
        TrialpayPreferences pref = getPreferences();
        pref.startTouchpointAccess(touchpointName);
        String url = pref.getTouchpointUrl();
        pref.closeCurrentTouchpoint();
        return url;
    }

    protected DealspotView createDealspotView(Context baseContext, String touchpointName) {
        return new DealspotView(baseContext, touchpointName);
    }

    protected void updateVcPurchaseInfo(String touchpointName, float dollarAmount, int vcAmount) {
        TrialpayPreferences pref = getPreferences();
        pref.startTouchpointAccess(touchpointName);
        pref.startTransaction();
        pref.setTotalDollarsSpent(pref.getTotalDollarsSpent(0.0f) + dollarAmount);
        pref.setPurchasedVcAmount(pref.getPurchasedVcAmount(0) + vcAmount);
        pref.commit();
        pref.closeCurrentTouchpoint();
    }

    protected void updateVcBalance(String touchpointName, int vcAmount) {
        TrialpayPreferences pref = getPreferences();
        pref.startTouchpointAccess(touchpointName);
        pref.setVcBalance(vcAmount);
        pref.closeCurrentTouchpoint();
    }

    protected void updateLevel(int newLevel) {
        getPreferences().setLevel(newLevel);
    }

    protected void setGender(Gender gender) {
        getPreferences().setGender(gender);
    }

    protected void setAge(int age) {
        getPreferences().setAge(age);
    }

    protected void addExtendedParamsToUrl(Url url, String touchpointName) {
        TrialpayPreferences pref = getPreferences();
        pref.startTouchpointAccess(touchpointName);
        if (pref.hasTotalDollarsSpent()) {
            url.addQueryParam("total_dollars_spent", new DecimalFormat("#.##").format((double) pref.getTotalDollarsSpent(0.0f)));
        }
        if (pref.hasPurchasedVcAmount()) {
            url.addQueryParam("total_vc_earned", (long) pref.getPurchasedVcAmount(0));
        }
        if (pref.hasGender()) {
            url.addQueryParam("tp_gender", pref.getGender(Gender.UNKNOWN).toChar());
        }
        if (pref.hasAge()) {
            url.addQueryParam("tp_age", (long) pref.getAge(0));
        }
        if (pref.hasVcBalance()) {
            url.addQueryParam("vc_balance", (long) pref.getVcBalance(0));
        }
        if (pref.hasLevel()) {
            url.addQueryParam("current_level", (long) pref.getLevel(0));
        }
        if (pref.hasCreationTime()) {
            url.addQueryParam("user_creation_timestamp", pref.getCreationTime());
        }
        if (pref.hasVisitTimes()) {
            url.addQueryParam("visit_timestamps", pref.getVisitTimes());
        }
        pref.closeCurrentTouchpoint();
    }

    protected void open(String touchpointName) {
        open(touchpointName, DisplayMode.TP_FULLSCREEN_MODE);
    }

    protected void open(String touchpointName, DisplayMode mode) {
        if (!isAvailable(touchpointName)) {
            Log.e(TAG, "No offers available for " + touchpointName);
        } else if (getVic(touchpointName) == null) {
            Log.e(TAG, "VIC is unavailable for " + touchpointName);
        } else {
            String url = getTouchpointUrl(touchpointName);
            if (url == null || !url.startsWith(Video.videoPrefix)) {
                Intent intent;
                switch (AnonymousClass2.$SwitchMap$com$trialpay$android$BaseTrialpayManager$DisplayMode[mode.ordinal()]) {
                    case com.vungle.sdk.VunglePub.Gender.FEMALE /*1*/:
                        intent = new Intent(getContext(), OfferwallActivity.class);
                        break;
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        intent = new Intent(getContext(), OfferwallPopupActivity.class);
                        break;
                    default:
                        Log.e(TAG, "Unknown mode: " + mode);
                        return;
                }
                intent.putExtra(Strings.EXTRA_KEY_TOUCHPOINT_NAME, touchpointName);
                getCurrentActivity().startActivity(intent);
                return;
            }
            Video.open(getContext(), url.replace(Video.videoPrefix, BuildConfig.VERSION_NAME), touchpointName, true);
        }
    }

    protected OfferwallView getOfferwallView(Context context) {
        return new OfferwallView(context);
    }

    public void handleCloseOfferwallView(String touchpointName) {
        resetBalanceActivities(false);
        if (this.balanceQueryAndWithdrawTask != null) {
            this.balanceQueryAndWithdrawTask.resume();
        }
    }

    public void handleOpenOfferwallView(String touchpointName) {
        if (this.balanceQueryAndWithdrawTask != null) {
            this.balanceQueryAndWithdrawTask.pause(300);
        }
    }

    protected void handleBalanceUpdated(String vic) {
    }

    protected String getSdkVer() {
        return "android.2.2014463";
    }

    protected void initiateBalanceChecks() {
        if (this.balanceQueryAndWithdrawTask == null) {
            this.balanceQueryAndWithdrawTask = new BalanceQueryAndWithdrawTask(this);
            new Thread(this.balanceQueryAndWithdrawTask).start();
        }
        this.enabledBalanceChecks = true;
    }

    protected int withdrawBalance(String touchpointName) {
        String vic = getVic(touchpointName);
        if (vic != null) {
            return getAndResetBalance(vic);
        }
        Log.e(TAG, "VIC is unavailable for " + touchpointName);
        return 0;
    }

    protected void resetBalanceActivities(boolean isHard) {
        if (this.enabledBalanceChecks) {
            this.balanceQueryAndWithdrawTask.reschedule(isHard);
        }
    }

    protected int addToBalance(String vic, int amount, List<String> precredited_vics) {
        if (precredited_vics != null) {
            for (String precredited_vic : precredited_vics) {
                int previousPrecredit = getPreferences().getPrecreditBalance(precredited_vic, 0);
                if (previousPrecredit > 0) {
                    amount -= previousPrecredit;
                    if (amount < 0) {
                        getPreferences().setPrecreditBalance(precredited_vic, -amount);
                        return 0;
                    }
                    getPreferences().setPrecreditBalance(precredited_vic, 0);
                }
            }
        }
        return addToBalanceNoPrecredit(vic, amount);
    }

    protected int getBalance(String vic) {
        return getPreferences().getBalance(vic, 0);
    }

    protected int getAndResetBalance(String vic) {
        int balance = getPreferences().getBalance(vic, 0);
        getPreferences().setBalance(vic, 0);
        return balance;
    }

    protected void addToPrecredit(String vic, int amount) {
        getPreferences().setPrecreditBalance(vic, amount + getPreferences().getPrecreditBalance(vic, 0));
        addToBalanceNoPrecredit(vic, amount);
    }

    private int addToBalanceNoPrecredit(String vic, int amount) {
        amount += getPreferences().getBalance(vic, 0);
        getPreferences().setBalance(vic, amount);
        return amount;
    }

    protected void setCustomParam(String paramName, String paramValue) {
        if (paramName != null && !BuildConfig.VERSION_NAME.equals(paramName)) {
            if (this.customParamMap == null) {
                this.customParamMap = new HashMap();
            }
            if (paramValue == null) {
                paramValue = BuildConfig.VERSION_NAME;
            }
            this.customParamMap.put(paramName, paramValue);
        }
    }

    protected void clearCustomParam(String paramName) {
        if (paramName != null) {
            this.customParamMap.remove(paramName);
        }
    }

    protected void addCustomParamsToUrl(Url url, boolean clearParams) {
        if (this.customParamMap != null && !this.customParamMap.isEmpty()) {
            for (String paramName : this.customParamMap.keySet()) {
                url.addQueryParam(paramName, (String) this.customParamMap.get(paramName));
            }
            if (clearParams) {
                this.customParamMap.clear();
            }
        }
    }

    public void setIntegrationTypeForTouchpoint(String touchpointName, String integrationType) {
        this.touchpointsIntegrationTypes.put(touchpointName, integrationType);
    }

    public String getIntegrationTypeForTouchpoint(String touchpointName) {
        return (String) this.touchpointsIntegrationTypes.get(touchpointName);
    }

    protected boolean isAvailable(String touchpointName) {
        String touchpoint_url = getTouchpointUrl(touchpointName);
        String integrationType = getIntegrationTypeForTouchpoint(touchpointName);
        boolean isOfferwallIntegration = integrationType == null ? touchpoint_url == null : integrationType.equals("offerwall");
        if (isOfferwallIntegration) {
            return true;
        }
        if (touchpoint_url == null) {
            Log.w(TAG, "Touchpoint " + touchpointName + " is not available!");
            return false;
        } else if (touchpoint_url.startsWith(Video.videoPrefix)) {
            return Video.isVideoReady(touchpoint_url.replace(Video.videoPrefix, BuildConfig.VERSION_NAME));
        } else {
            if (touchpoint_url.equals(OfferAvailabilityCheckTask.NO_TOUCHPOINT)) {
                return false;
            }
            return true;
        }
    }

    protected void startAvailabilityCheck(String touchpointName) {
        if (this.offer_tasks == null) {
            this.offer_tasks = new HashMap();
        }
        if (this.offer_tasks.get(touchpointName) == null) {
            OfferAvailabilityCheckTask offer_task = new OfferAvailabilityCheckTask(getInstance(), touchpointName);
            new Thread(offer_task).start();
            this.offer_tasks.put(touchpointName, offer_task);
        }
        if (VERSION.SDK_INT >= 14) {
            synchronized (this) {
                if (this.stateListener == null) {
                    this.stateListener = new StateListener() {
                        public void onBecameForeground() {
                            BaseTrialpayManager.instance.startAvailibilityChecks();
                        }

                        public void onBecameBackground() {
                            BaseTrialpayManager.instance.stopAvailibilityChecks();
                        }
                    };
                    try {
                        Class.forName("com.trialpay.android.Foreground").getMethod("addListener", new Class[]{Context.class, StateListener.class}).invoke(null, new Object[]{getContext(), this.stateListener});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected void stopAvailabilityCheck(String touchpointName) {
        if (this.offer_tasks.get(touchpointName) != null) {
            ((OfferAvailabilityCheckTask) this.offer_tasks.get(touchpointName)).terminate();
        }
    }

    protected void startAvailibilityChecks() {
        for (OfferAvailabilityCheckTask offer_task : this.offer_tasks.values()) {
            offer_task.reenable();
            new Thread(offer_task).start();
        }
    }

    protected void stopAvailibilityChecks() {
        for (OfferAvailabilityCheckTask offer_task : this.offer_tasks.values()) {
            offer_task.terminate();
        }
    }
}
