package com.appnana.android.giftcardrewards.service;

import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class WebService {
    public static HttpContext localContext;
    private final String BASE_REQUEST_URL = "https://appnana2.mapiz.com/api/";
    private final String FINISH_OFFER_RFN_URL = "https://appnana2.mapiz.com/api/finish_offer_rfn/";
    private final String GET_REWARDS_REQUEST_URL = "https://appnana2.mapiz.com/api/rewards/";
    private final String GET_SETTINGS_REQUEST_URL = "https://appnana2.mapiz.com/api/settings/";
    private final String GET_USER_INFO_REQUEST_URL = "https://appnana2.mapiz.com/api/get_nanaer_info/";
    private final String HISTORY_URL = "https://appnana2.mapiz.com/api/history/";
    private final String INVITE_VERIFY_REQUEST_URL = "https://appnana2.mapiz.com/api/invite_verify/";
    private final String LOGIN_REQUEST_URL = "https://appnana2.mapiz.com/api/nanaer_login/";
    private final String LOGOUT_REQUEST_URL = "https://appnana2.mapiz.com/api/nanaer_logout/";
    private final String MASS_INVITE_URL = "https://appnana2.mapiz.com/api/mass_invite/";
    private final String OFFERS_URL = "https://appnana2.mapiz.com/api/offers/";
    private final String REDEEM_REQUEST_URL = "https://appnana2.mapiz.com/api/redeem/";
    private final String REGISTER_REQUEST_URL = "https://appnana2.mapiz.com/api/nanaer_register/";
    private final String VERIFY_FB_POST_URL = "https://appnana2.mapiz.com/api/facebook_share/";
    private UserModel user = UserModel.getInstance();

    private WebService() {
        if (localContext == null) {
            CookieStore cookieStore = new BasicCookieStore();
            localContext = new BasicHttpContext();
            localContext.setAttribute("http.cookie-store", cookieStore);
        }
    }

    public static WebService init() {
        return new WebService();
    }

    public HttpResponse getSettings() throws IOException {
        return get("https://appnana2.mapiz.com/api/settings/", new ArrayList());
    }

    public HttpResponse register() throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("email", this.user.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("password", this.user.getPassword()));
        nameValuePairs.add(new BasicNameValuePair(ShareConstants.FEED_SOURCE_PARAM, this.user.getSource()));
        nameValuePairs.add(new BasicNameValuePair("signupkey", this.user.getSignUpKey()));
        nameValuePairs.add(new BasicNameValuePair("android_id", Device.getInstance().getAndroidID()));
        nameValuePairs.add(new BasicNameValuePair("system_version", Device.getInstance().getOSVersion()));
        nameValuePairs.add(new BasicNameValuePair("system_name", Device.getInstance().getType()));
        nameValuePairs.add(new BasicNameValuePair("device_type", Device.getInstance().getModel()));
        nameValuePairs.add(new BasicNameValuePair("android_imei", Device.getInstance().getDeviceID()));
        nameValuePairs.add(new BasicNameValuePair("gaid", Device.getInstance().getAdvertisingId()));
        nameValuePairs.add(new BasicNameValuePair("gaid_enabled", String.valueOf(!Device.getInstance().isLimitAdTrackingEnabled())));
        return post("https://appnana2.mapiz.com/api/nanaer_register/", nameValuePairs);
    }

    public HttpResponse getRewards(String locale) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("platform", "android"));
        nameValuePairs.add(new BasicNameValuePair("locale", locale));
        return get("https://appnana2.mapiz.com/api/rewards/", nameValuePairs);
    }

    public HttpResponse login() throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("email", this.user.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("password", this.user.getPassword()));
        nameValuePairs.add(new BasicNameValuePair(ShareConstants.FEED_SOURCE_PARAM, this.user.getSource()));
        nameValuePairs.add(new BasicNameValuePair("signkey", this.user.getSignInKey()));
        nameValuePairs.add(new BasicNameValuePair("android_id", Device.getInstance().getAndroidID()));
        nameValuePairs.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, Device.getInstance().getAppVersionName()));
        nameValuePairs.add(new BasicNameValuePair("gaid", Device.getInstance().getAdvertisingId()));
        nameValuePairs.add(new BasicNameValuePair("gaid_enabled", String.valueOf(!Device.getInstance().isLimitAdTrackingEnabled())));
        return post("https://appnana2.mapiz.com/api/nanaer_login/", nameValuePairs);
    }

    public HttpResponse logout() throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("email", this.user.getEmail()));
        return get("https://appnana2.mapiz.com/api/nanaer_logout/", nameValuePairs);
    }

    public HttpResponse getUserInfo() throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("email", this.user.getEmail()));
        return get("https://appnana2.mapiz.com/api/get_nanaer_info/", nameValuePairs);
    }

    public HttpResponse redeem(int rewardID) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("email", this.user.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("paypal_email", this.user.getPayPalAccount()));
        nameValuePairs.add(new BasicNameValuePair("bitcoin_address", this.user.getBitcoinAddress()));
        nameValuePairs.add(new BasicNameValuePair(ShareConstants.WEB_DIALOG_PARAM_NAME, this.user.getName()));
        nameValuePairs.add(new BasicNameValuePair("reward_id", String.valueOf(rewardID)));
        nameValuePairs.add(new BasicNameValuePair("platform", "android"));
        return post("https://appnana2.mapiz.com/api/redeem/", nameValuePairs);
    }

    public HttpResponse inviteVerify(String invitationCode) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("email", this.user.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("invitation", invitationCode));
        return post("https://appnana2.mapiz.com/api/invite_verify/", nameValuePairs);
    }

    public HttpResponse finishOfferRfn() throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("device", String.valueOf(this.user.getDeviceID())));
        return post("https://appnana2.mapiz.com/api/finish_offer_rfn/", nameValuePairs);
    }

    public HttpResponse getOffers() throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("device_id", this.user.getEncryptedDeviceId()));
        nameValuePairs.add(new BasicNameValuePair("platform", "android"));
        nameValuePairs.add(new BasicNameValuePair("locale", Device.getInstance().getCountry().toLowerCase()));
        nameValuePairs.add(new BasicNameValuePair("client_key", Device.getInstance().getClientPackage()));
        return get("https://appnana2.mapiz.com/api/offers/", nameValuePairs);
    }

    public HttpResponse acceptOffer(String offerId) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("device_id", this.user.getEncryptedDeviceId()));
        nameValuePairs.add(new BasicNameValuePair("offer_id", offerId));
        nameValuePairs.add(new BasicNameValuePair("platform", "android"));
        return post("https://appnana2.mapiz.com/api/offers/", nameValuePairs);
    }

    public HttpResponse getHistory(int offset) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("device_id", this.user.getEncryptedDeviceId()));
        nameValuePairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        nameValuePairs.add(new BasicNameValuePair("limit", String.valueOf(5)));
        return get("https://appnana2.mapiz.com/api/history/", nameValuePairs);
    }

    public HttpResponse getHistoryName() throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("device_id", this.user.getEncryptedDeviceId()));
        nameValuePairs.add(new BasicNameValuePair("simple", AppEventsConstants.EVENT_PARAM_VALUE_YES));
        return get("https://appnana2.mapiz.com/api/history/", nameValuePairs);
    }

    public HttpResponse postHistory(AbstractOffer offer) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("device", this.user.getEncryptedDeviceId()));
        nameValuePairs.add(new BasicNameValuePair("offer_name", offer.getName()));
        nameValuePairs.add(new BasicNameValuePair("offer_nanas", String.valueOf(offer.getNanas())));
        nameValuePairs.add(new BasicNameValuePair("offer_link", offer.getActionUrl()));
        nameValuePairs.add(new BasicNameValuePair("offer_icon", offer.getIconUrl()));
        nameValuePairs.add(new BasicNameValuePair("service", offer.getNetwork().toLowerCase()));
        nameValuePairs.add(new BasicNameValuePair("service_offer_id", offer.getId()));
        nameValuePairs.add(new BasicNameValuePair("offer_desc", offer.getActionMessage()));
        return post("https://appnana2.mapiz.com/api/history/", nameValuePairs);
    }

    public HttpResponse verifyFacebookPost(String postId) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID, postId));
        return post("https://appnana2.mapiz.com/api/facebook_share/", nameValuePairs);
    }

    public HttpResponse sendMassInvite(String sharer) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("email_service", sharer));
        return post("https://appnana2.mapiz.com/api/mass_invite/", nameValuePairs);
    }

    private HttpResponse post(String url, List<NameValuePair> params) throws IOException {
        MapizLog.d(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, url);
        for (NameValuePair pair : params) {
            String key = pair.getName();
            MapizLog.d("param", key + "=" + pair.getValue());
        }
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept", "application/json; version=1.2");
        post.setHeader("User-Agent", Device.getInstance().getUserAgent());
        post.setHeader("Accept-Language", Device.getInstance().getLocale());
        post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpResponse response = httpClient.execute(post, localContext);
        MapizLog.d(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, "done");
        return response;
    }

    private HttpResponse get(String url, List<NameValuePair> params) throws IOException {
        url = url + getFieldString(params);
        MapizLog.d(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader("Accept", "application/json; version=1.2");
        get.setHeader("User-Agent", Device.getInstance().getUserAgent());
        get.setHeader("Accept-Language", Device.getInstance().getLocale());
        HttpResponse response = httpClient.execute(get, localContext);
        MapizLog.d(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, "done");
        return response;
    }

    private String getFieldString(List<NameValuePair> params) {
        String param = "?";
        for (NameValuePair pair : params) {
            param = param + pair.getName() + "=" + URLEncoder.encode(pair.getValue()) + "&";
        }
        return param.substring(0, param.length() - 1);
    }
}
