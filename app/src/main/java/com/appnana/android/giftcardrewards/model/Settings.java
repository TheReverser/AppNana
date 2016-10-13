package com.appnana.android.giftcardrewards.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.appnana.android.giftcardrewards.BuildConfig;
import com.facebook.appevents.AppEventsConstants;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class Settings implements Parcelable {
    public static final Creator<Settings> CREATOR = new Creator<Settings>() {
        public Settings createFromParcel(Parcel source) {
            Settings settings = Settings.getInstance();
            settings.dailyPoints = source.readInt();
            settings.invitationPoints = source.readInt();
            settings.flurryOfferAmount = source.readInt();
            settings.versionCode = source.readInt();
            settings.updateURL = source.readString();
            settings.contactURL = source.readString();
            settings.termsURL = source.readString();
            settings.emailInviteNanas = source.readInt();
            settings.facebookInviteNanas = source.readInt();
            settings.adShownFlag = source.readString();
            settings.facebookShareParams = source.readString();
            settings.igImageCount = source.readInt();
            settings.instagramInviteNanasString = source.readString();
            settings.shareLimit = source.readInt();
            settings.appIconUrlRegex = source.readString();
            settings.gamingIntervals = source.readString();
            settings.displayIOExchangeRate = source.readInt();
            settings.appThisExchangeRate = source.readInt();
            settings.hangMyAdsExchangeRate = source.readInt();
            return settings;
        }

        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };
    public static final String EXTRA_NAME = "AppNanaSettings";
    public static final String FACEBOOK_SHARE_URI = "https://www.facebook.com/dialog/feed?app_id=%s&name=%s&link=%s&redirect_uri=%s&picture=http%%3A%%2F%%2Fs.mapiz.com%%2Fappnana%%2Fimage%%2Ffacebook%%2Ffb_share.png%%3Fv%%3D%s";
    public static final String FB_APP_ID_KEY = "app_id";
    private static final String FB_DEFAULT_APP_ID = "1578495615747491";
    private static final String FB_DEFAULT_LINK = "getnana.com/sharer.php";
    private static final String FB_DEFAULT_PICTURE_VER = "150612";
    private static final String FB_DEFAULT_REDIRECT_URI = "getnana.com";
    public static final String FB_LINK_KEY = "link";
    public static final String FB_NAME_KEY = "name";
    public static final String FB_PICTURE_VER_KEY = "picture_ver";
    public static final String FB_REDIRECT_URI_KEY = "redirect_uri";
    public static final String GAME_CENTER_URL = "http://appnana.com/game-list.html";
    public static final int MAIN_AD_FLAG = Integer.parseInt(AppEventsConstants.EVENT_PARAM_VALUE_YES, 2);
    public static final int MIN_NANAS_TO_ALERT_RATING = 2000;
    public static final int NANAS_PER_VIDEO = 5;
    public static final int REGISTER_POINTS = 10000;
    private static Settings instance;
    private String adShownFlag;
    private String appIconUrlRegex;
    private int appThisExchangeRate;
    private String contactURL;
    private int dailyPoints;
    private int displayIOExchangeRate;
    private int emailInviteNanas;
    private int facebookInviteNanas;
    public HashMap<String, String> facebookParams;
    private String facebookShareParams;
    private int flurryOfferAmount;
    private String gamingIntervals;
    private int hangMyAdsExchangeRate;
    private int igImageCount;
    private String instagramInviteNanasString;
    private int invitationPoints;
    private int shareLimit;
    private String termsURL;
    private String updateURL;
    private int versionCode;

    private Settings() {
    }

    private Settings(JSONObject settings) throws JSONException {
        this.dailyPoints = settings.getInt("daily_nanas");
        this.invitationPoints = settings.getInt("invite_nanas");
        this.flurryOfferAmount = settings.getInt("android_flurry_app_nanas");
        this.versionCode = settings.getInt("android_update");
        this.updateURL = settings.getString("android_update_url");
        this.contactURL = settings.getString("android_contact_url");
        this.termsURL = settings.getString("terms_url");
        this.emailInviteNanas = settings.getInt("email_invite_nanas");
        this.facebookInviteNanas = settings.getInt("facebook_invite_nanas");
        this.adShownFlag = settings.getString("show_ads");
        this.facebookShareParams = settings.getString("fb_params");
        this.igImageCount = settings.getInt("ig_image_count");
        this.instagramInviteNanasString = settings.getString("instagram_invite_nanas");
        this.shareLimit = settings.getInt("share_limit");
        this.appIconUrlRegex = settings.getString("app_icon_url_regex");
        this.gamingIntervals = settings.getString("gaming_interval");
        this.displayIOExchangeRate = settings.getInt("displayio_exchange_rate");
        this.appThisExchangeRate = settings.getInt("appthis_exchange_rate");
        this.hangMyAdsExchangeRate = settings.getInt("hangmyads_exchange_rate");
    }

    public static Settings getInstance() {
        if (instance == null) {
            return new Settings();
        }
        return instance;
    }

    public static void newInstance(JSONObject settings) throws JSONException {
        instance = new Settings(settings);
    }

    public static void restoreInstance(Settings settings) {
        instance = settings;
    }

    public int getDailyPoints() {
        return this.dailyPoints;
    }

    public String getDailyPointsShow() {
        return new DecimalFormat(",###").format((long) getDailyPoints());
    }

    public int getInvitationPoints() {
        return this.invitationPoints;
    }

    public String getInvitationPointsShow() {
        return new DecimalFormat(",###").format((long) getInvitationPoints());
    }

    public int getFlurryOfferAmount() {
        return this.flurryOfferAmount;
    }

    public int getVersionCode() {
        return this.versionCode + BuildConfig.VERSION_OFFSET;
    }

    public String getUpdateURL() {
        return this.updateURL;
    }

    public String getContactURL() {
        return this.contactURL;
    }

    public String getTermsURL() {
        return this.termsURL;
    }

    public int getEmailInviteNanas() {
        return this.emailInviteNanas;
    }

    public int getFacebookInviteNanas() {
        return this.facebookInviteNanas;
    }

    public int getIgImageCount() {
        return this.igImageCount;
    }

    public String getInstagramInviteNanasString() {
        return this.instagramInviteNanasString;
    }

    public int getShareLimit() {
        return this.shareLimit;
    }

    public String getFacebookShareParams() {
        return this.facebookShareParams;
    }

    public String getGamingIntervals() {
        return this.gamingIntervals;
    }

    public String getFacebookShareUrl(String name, String utmMedia) {
        parseFacebookShareParams();
        this.facebookParams.put(FB_NAME_KEY, URLEncoder.encode(name));
        String referrer = "type=fb_share&utm_source=Facebook&utm_media=" + utmMedia;
        this.facebookParams.put(FB_LINK_KEY, URLEncoder.encode(((String) this.facebookParams.get(FB_LINK_KEY)) + "?" + referrer));
        this.facebookParams.put(FB_REDIRECT_URI_KEY, URLEncoder.encode("http://" + ((String) this.facebookParams.get(FB_REDIRECT_URI_KEY)) + "?" + referrer));
        String appId = (String) this.facebookParams.get(FB_APP_ID_KEY);
        String title = (String) this.facebookParams.get(FB_NAME_KEY);
        String link = (String) this.facebookParams.get(FB_LINK_KEY);
        String redirectUri = (String) this.facebookParams.get(FB_REDIRECT_URI_KEY);
        String pictureVer = (String) this.facebookParams.get(FB_PICTURE_VER_KEY);
        String str = FACEBOOK_SHARE_URI;
        Object[] objArr = new Object[NANAS_PER_VIDEO];
        objArr[MAIN_AD_FLAG] = appId;
        objArr[1] = title;
        objArr[2] = link;
        objArr[3] = redirectUri;
        objArr[4] = pictureVer;
        return String.format(str, objArr);
    }

    private void parseFacebookShareParams() {
        this.facebookParams = new HashMap();
        String fbParamString = getFacebookShareParams();
        if (fbParamString == null || fbParamString.isEmpty()) {
            this.facebookParams.put(FB_APP_ID_KEY, FB_DEFAULT_APP_ID);
            this.facebookParams.put(FB_LINK_KEY, FB_DEFAULT_LINK);
            this.facebookParams.put(FB_REDIRECT_URI_KEY, FB_DEFAULT_REDIRECT_URI);
            this.facebookParams.put(FB_PICTURE_VER_KEY, FB_DEFAULT_PICTURE_VER);
            return;
        }
        String[] arr$ = getFacebookShareParams().split("&");
        int len$ = arr$.length;
        for (int i$ = MAIN_AD_FLAG; i$ < len$; i$++) {
            String[] keyValue = arr$[i$].split("=");
            this.facebookParams.put(keyValue[MAIN_AD_FLAG], keyValue[1]);
        }
    }

    public boolean needShownAd(int adFlag) {
        boolean needShow = (getAdShownFlag() & adFlag) == adFlag;
        if (needShow) {
            setAdShownFlag(getAdShownFlag() & (adFlag ^ -1));
        }
        return needShow;
    }

    public String getAdShownFlagString() {
        return this.adShownFlag;
    }

    public int getAdShownFlag() {
        try {
            return Integer.parseInt(this.adShownFlag, 2);
        } catch (Exception e) {
            return MAIN_AD_FLAG;
        }
    }

    public void setAdShownFlag(int flag) {
        this.adShownFlag = Integer.toBinaryString(flag);
    }

    public static String getRegisterPointsShow() {
        return new DecimalFormat(",###").format(10000);
    }

    public String getAppIconUrlRegex() {
        return this.appIconUrlRegex;
    }

    public int getDisplayIOExchangeRate() {
        return this.displayIOExchangeRate;
    }

    public int getAppThisExchangeRate() {
        return this.appThisExchangeRate;
    }

    public int getHangMyAdsExchangeRate() {
        return this.hangMyAdsExchangeRate;
    }

    public int describeContents() {
        return MAIN_AD_FLAG;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dailyPoints);
        dest.writeInt(this.invitationPoints);
        dest.writeInt(this.flurryOfferAmount);
        dest.writeInt(this.versionCode);
        dest.writeString(this.updateURL);
        dest.writeString(this.contactURL);
        dest.writeString(this.termsURL);
        dest.writeInt(this.emailInviteNanas);
        dest.writeInt(this.facebookInviteNanas);
        dest.writeString(this.adShownFlag);
        dest.writeString(this.facebookShareParams);
        dest.writeInt(this.igImageCount);
        dest.writeString(this.instagramInviteNanasString);
        dest.writeInt(this.shareLimit);
        dest.writeString(this.appIconUrlRegex);
        dest.writeString(this.gamingIntervals);
        dest.writeInt(this.displayIOExchangeRate);
        dest.writeInt(this.appThisExchangeRate);
        dest.writeInt(this.hangMyAdsExchangeRate);
    }
}
