package com.trialpay.android;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import com.facebook.BuildConfig;
import com.facebook.appevents.AppEventsConstants;
import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.util.LinkedList;
import java.util.List;

public class UrlManager {
    private static final String TAG = "Trialpay.UrlManager";
    private static UrlManager _instance;
    private static Context context;
    private UrlSettings balanceApiPrefixUrl = new UrlSettings(Strings.BASE_URL, Strings.BALANCE_API_URL_PATH);
    private UrlSettings dealspotGeoPrefixUrl = new UrlSettings(null, null);
    private UrlSettings dealspotPrefixUrl = new UrlSettings(Strings.BASE_GEO_URL, Strings.DEALSPOT_TOUCHPOINT_URL_PATH);
    private UrlSettings dispatchPrefixUrl = new UrlSettings(Strings.BASE_URL, Strings.DISPATCH_URL_PATH);
    private final List<Pair<String, String>> globalParams = new LinkedList();
    private UrlSettings interstitialPrefixUrl = new UrlSettings(Strings.BASE_GEO_URL, Strings.INTERSTITIAL_URL_PATH);
    private UrlSettings navbarPrefixUrl = new UrlSettings(Strings.BASE_URL, Strings.NAVBAR_URL_PATH);
    private UrlSettings srcPrefixUrl = new UrlSettings(null, null);
    private String userAgent;
    private UrlSettings userPrefixUrl = new UrlSettings(null, null);

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode = new int[PrefixUrlMode.values().length];

        static {
            try {
                $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[PrefixUrlMode.TP_OFFERWALL_PREFIX_URL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[PrefixUrlMode.TP_BALANCE_PREFIX_URL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[PrefixUrlMode.TP_DEALSPOT_TOUCHPOINT_PREFIX_URL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[PrefixUrlMode.TP_DEALSPOT_GEO_PREFIX_URL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[PrefixUrlMode.TP_USER_PREFIX_URL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[PrefixUrlMode.TP_SRC_PREFIX_URL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[PrefixUrlMode.TP_NAVBAR_PREFIX_URL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[PrefixUrlMode.TP_INTERSTITIAL_PREFIX_URL.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    private static class Pair<T, K> {
        private T key;
        private K value;

        public Pair(T key, K value) {
            setKey(key);
            setValue(value);
        }

        public T getKey() {
            return this.key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public K getValue() {
            return this.value;
        }

        public void setValue(K value) {
            this.value = value;
        }
    }

    public enum PrefixUrlMode {
        TP_UNDEFINED(0),
        TP_OFFERWALL_PREFIX_URL(1),
        TP_BALANCE_PREFIX_URL(2),
        TP_DEALSPOT_TOUCHPOINT_PREFIX_URL(3),
        TP_DEALSPOT_GEO_PREFIX_URL(4),
        TP_USER_PREFIX_URL(5),
        TP_SRC_PREFIX_URL(6),
        TP_INTERSTITIAL_PREFIX_URL(7),
        TP_NAVBAR_PREFIX_URL(8);
        
        protected int mode;

        private PrefixUrlMode(int mode) {
            this.mode = mode;
        }

        public static PrefixUrlMode getMode(int prefixUrlMode) {
            for (PrefixUrlMode mode : values()) {
                if (mode.mode == prefixUrlMode) {
                    return mode;
                }
            }
            Log.w(UrlManager.TAG, "Undefined URL mode");
            return TP_UNDEFINED;
        }
    }

    public static class Url {
        private final StringBuilder path = new StringBuilder();
        private final StringBuilder queryString = new StringBuilder();

        public Url(String urlString) {
            if (urlString == null) {
                return;
            }
            if (urlString.contains("?")) {
                this.path.append(urlString.substring(0, urlString.indexOf(63)));
                this.queryString.append(urlString.substring(urlString.indexOf(63) + 1));
                return;
            }
            this.path.append(urlString);
        }

        public Url appendPath(String prefix, boolean doEncodePrefix) {
            StringBuilder stringBuilder = this.path;
            if (doEncodePrefix) {
                prefix = getEncodedString(prefix);
            }
            stringBuilder.append(prefix);
            return this;
        }

        public Url addQueryParam(String paramName, String paramValue) {
            if (paramValue != null) {
                String encodedParamName = getEncodedString(paramName);
                String encodedParamValue = getEncodedString(paramValue);
                if (this.queryString.length() > 0) {
                    this.queryString.append("&");
                }
                this.queryString.append(encodedParamName).append('=').append(encodedParamValue);
            }
            return this;
        }

        public Url addQueryParam(String paramName, long paramValue) {
            return addQueryParam(paramName, String.valueOf(paramValue));
        }

        public Url addQueryParam(String paramName, char paramValue) {
            return addQueryParam(paramName, String.valueOf(paramValue));
        }

        public String toString() {
            if (this.queryString.length() > 0) {
                return this.path.toString() + "?" + this.queryString.toString();
            }
            return this.path.toString();
        }

        public static String getEncodedString(String str) {
            if (str == null || str.equals(BuildConfig.VERSION_NAME)) {
                return BuildConfig.VERSION_NAME;
            }
            return Uri.encode(str).replace("(", "%28").replace(")", "%29").replace("!", "%21").replace("'", "%27").replace("*", "%2A");
        }

        public static String getDecodedString(String str) {
            if (str == null || str.equals(BuildConfig.VERSION_NAME)) {
                return BuildConfig.VERSION_NAME;
            }
            return Uri.decode(str);
        }
    }

    public static class UrlSettings {
        String customBaseUrl;
        String customPath;
        final String defaultBaseUrl;
        final String defaultPath;

        public UrlSettings(String defaultBaseUrl, String defaultPath) {
            this.defaultBaseUrl = defaultBaseUrl;
            this.defaultPath = defaultPath;
        }

        public Url toUrl() {
            StringBuilder stringBuilder = new StringBuilder();
            String str = this.customBaseUrl != null ? this.customBaseUrl : this.defaultBaseUrl != null ? this.defaultBaseUrl : BuildConfig.VERSION_NAME;
            stringBuilder = stringBuilder.append(str);
            str = this.customPath != null ? this.customPath : this.defaultPath != null ? this.defaultPath : BuildConfig.VERSION_NAME;
            return new Url(stringBuilder.append(str).toString());
        }

        public boolean isEmpty() {
            return this.defaultBaseUrl == null && this.defaultPath == null && this.customBaseUrl == null && this.customPath == null;
        }
    }

    public static UrlManager getInstance() {
        if (_instance == null) {
            setInstance(new UrlManager());
        }
        return _instance;
    }

    protected static void setInstance(UrlManager instance) {
        _instance = instance;
    }

    protected UrlManager() {
    }

    public void addGlobalQueryParameter(String key, String value) {
        this.globalParams.add(new Pair(key, value));
    }

    public int findGlobalQueryParameter(String key) {
        return findGlobalQueryParameter(key, 0);
    }

    public int findGlobalQueryParameter(String key, int startFrom) {
        for (int idx = startFrom; idx < this.globalParams.size(); idx++) {
            if (((String) ((Pair) this.globalParams.get(idx)).getKey()).equals(key)) {
                return idx;
            }
        }
        return -1;
    }

    public void removeGlobalQueryParameter(int idx) {
        this.globalParams.remove(idx);
    }

    public void removeGlobalQueryParameter(String key) {
        int idx = findGlobalQueryParameter(key);
        while (idx != -1) {
            removeGlobalQueryParameter(idx);
            idx = findGlobalQueryParameter(key, idx);
        }
    }

    public void removeAllGlobalQueryParameters() {
        this.globalParams.clear();
    }

    public Url getDealspotTouchpointUrl(Context context, String touchpointName, int height, int width) {
        String vic = BaseTrialpayManager.getInstance().getVic(touchpointName);
        if (vic == null) {
            Log.e(TAG, "Unknown touchpoint " + touchpointName);
            return null;
        }
        UrlSettings uhUrl = getPrefixUrl(PrefixUrlMode.TP_USER_PREFIX_URL);
        UrlSettings shUrl = getPrefixUrl(PrefixUrlMode.TP_SRC_PREFIX_URL);
        UrlSettings ghUrl = getPrefixUrl(PrefixUrlMode.TP_DEALSPOT_GEO_PREFIX_URL);
        Url url = getPrefixUrl(PrefixUrlMode.TP_DEALSPOT_TOUCHPOINT_PREFIX_URL).toUrl();
        url.addQueryParam("vic", vic);
        url.addQueryParam("height", height + "px");
        url.addQueryParam("width", width + "px");
        url.addQueryParam("ua", new WebView(context).getSettings().getUserAgentString());
        addCommonQueryParams(touchpointName, url);
        if (!uhUrl.isEmpty()) {
            url.addQueryParam("__uh", uhUrl.toUrl().toString());
        }
        if (!ghUrl.isEmpty()) {
            url.addQueryParam("__gh", ghUrl.toUrl().toString());
        }
        if (shUrl.isEmpty()) {
            return url;
        }
        url.addQueryParam("__sh", shUrl.toUrl().toString());
        return url;
    }

    public String getInterstitialApiUrl(Context context, String touchpointName) {
        Url api_url = getPrefixUrl(PrefixUrlMode.TP_INTERSTITIAL_PREFIX_URL).toUrl();
        api_url.addQueryParam("vic", BaseTrialpayManager.getInstance().getVic(touchpointName));
        if (this.userAgent != null) {
            api_url.addQueryParam("ua", this.userAgent);
        } else {
            Log.d(TAG, "User agent was not resolved. Have you called initApp()?");
        }
        api_url.addQueryParam("orientation_support", 2);
        addCommonQueryParams(touchpointName, api_url);
        return api_url.toString();
    }

    public static void setContext(Context context) {
        context = context;
    }

    public static Context getContext() {
        if (context == null) {
            context = BaseTrialpayManager.getInstance().getContext();
        }
        return context;
    }

    public static String getOfferwallUrl(String touchpointName) {
        BaseTrialpayManager trialpayManager = BaseTrialpayManager.getInstance();
        if (trialpayManager == null) {
            Log.e(TAG, "TrialpayManager Instance is not accessible. Cannot build the offerwall URL");
            return null;
        }
        String vic = trialpayManager.getVic(touchpointName);
        if (vic == null) {
            Log.e(TAG, "Unknown touchpoint " + touchpointName);
            return null;
        }
        Url url = getInstance().getDispatchPrefixUrl().appendPath(vic, true);
        addCommonQueryParams(touchpointName, url);
        url.addQueryParam("tp_base_page", 1);
        List<String> loadedVideoOffersList = Video.getLoadedVideoOfferIdsList();
        String offerIds = BuildConfig.VERSION_NAME;
        boolean isFirst = true;
        for (String offerId : loadedVideoOffersList) {
            if (!isFirst) {
                offerIds = offerIds + "-";
            }
            isFirst = false;
            offerIds = offerIds + offerId;
        }
        if (!offerIds.isEmpty()) {
            url.addQueryParam("loaded_vts", offerIds);
        }
        Log.v(TAG, "trialPay url: " + url);
        return url.toString();
    }

    private static void addCommonQueryParams(String touchpointName, Url url) {
        BaseTrialpayManager trialpayManager = BaseTrialpayManager.getInstance();
        url.addQueryParam("sid", trialpayManager.getSid()).addQueryParam("appver", Utils.getAppVer(getContext())).addQueryParam("sdkver", trialpayManager.getSdkVer());
        String gaid = Utils.getGaid();
        if (!gaid.equals(BuildConfig.VERSION_NAME)) {
            url.addQueryParam("gaid", gaid).addQueryParam("gaid_en", Utils.isGaidTrackingEnabled() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        trialpayManager.addCustomParamsToUrl(url, true);
        trialpayManager.addExtendedParamsToUrl(url, touchpointName);
    }

    public UrlSettings getPrefixUrl(PrefixUrlMode prefixUrlMode) {
        switch (AnonymousClass2.$SwitchMap$com$trialpay$android$UrlManager$PrefixUrlMode[prefixUrlMode.ordinal()]) {
            case Gender.FEMALE /*1*/:
                return this.dispatchPrefixUrl;
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                return this.balanceApiPrefixUrl;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                return this.dealspotPrefixUrl;
            case Logger.INFO_LOGGING_LEVEL /*4*/:
                return this.dealspotGeoPrefixUrl;
            case Logger.WARN_LOGGING_LEVEL /*5*/:
                return this.userPrefixUrl;
            case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                return this.srcPrefixUrl;
            case HapticPlaybackThread.PAUSE_AV_FOR_HAPTIC_BUFFERING /*7*/:
                return this.navbarPrefixUrl;
            case HapticPlaybackThread.HAPTIC_DOWNLOAD_ERROR /*8*/:
                return this.interstitialPrefixUrl;
            default:
                Utils.assertTrue(false, "Invalid prefixUrlMode", TAG);
                return null;
        }
    }

    public void setCustomPrefixUrl(PrefixUrlMode prefixUrlMode, String baseUrl, String basePath) {
        UrlSettings url = getPrefixUrl(prefixUrlMode);
        if (url != null) {
            url.customBaseUrl = baseUrl;
            url.customPath = basePath;
        }
    }

    public boolean hasCustomPrefixUrl(PrefixUrlMode prefixUrlMode) {
        UrlSettings url = getPrefixUrl(prefixUrlMode);
        if (url == null) {
            return false;
        }
        if (url.customBaseUrl == null && url.customPath == null) {
            return false;
        }
        return true;
    }

    public Url getDispatchPrefixUrl() {
        Url ret = this.dispatchPrefixUrl.toUrl();
        applyAllGlobalParams(ret);
        return ret;
    }

    public Url getVcBalanceUrl() {
        Url ret = this.balanceApiPrefixUrl.toUrl();
        applyAllGlobalParams(ret);
        return ret;
    }

    public Url getNavbarUrl() {
        Url ret = this.navbarPrefixUrl.toUrl();
        applyAllGlobalParams(ret);
        return ret;
    }

    private void applyAllGlobalParams(Url url) {
        for (int idx = 0; idx < this.globalParams.size(); idx++) {
            Pair<String, String> entity = (Pair) this.globalParams.get(idx);
            url.addQueryParam((String) entity.getKey(), (String) entity.getValue());
        }
    }

    public void resolveUa(Context context) {
        final UserAgentResolver resolver = new UserAgentResolver(context);
        resolver.setOnResolvedCallback(new Runnable() {
            public void run() {
                UrlManager.this.userAgent = resolver.getUserAgent();
                Log.v(UrlManager.TAG, "ua=" + UrlManager.this.userAgent);
            }
        });
        resolver.resolve();
    }
}
