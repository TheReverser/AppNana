package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.Fyber;
import com.appnana.android.utils.Hash;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class FyberService extends OfferService {
    public FyberService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "https://api.fyber.com/feed/v1/offers.json";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("appid", Fyber.APP_ID));
        params.add(new BasicNameValuePair("device", this.device.getType()));
        params.add(new BasicNameValuePair("google_ad_id", this.device.getAdvertisingId()));
        params.add(new BasicNameValuePair("google_ad_id_limited_tracking_enabled", String.valueOf(this.device.isLimitAdTrackingEnabled())));
        params.add(new BasicNameValuePair("locale", this.device.getLanguage()));
        params.add(new BasicNameValuePair("offer_types", getOfferTypeIds()));
        params.add(new BasicNameValuePair("os_version", this.device.getOSVersion()));
        params.add(new BasicNameValuePair("pub0", "android"));
        params.add(new BasicNameValuePair("timestamp", String.valueOf(System.currentTimeMillis() / 1000)));
        params.add(new BasicNameValuePair("uid", this.ndid));
        params.add(new BasicNameValuePair("hashkey", getHashKey(params)));
        return params;
    }

    protected Class getClazz() {
        return Fyber.class;
    }

    private String getOfferTypeIds() {
        return "101,113";
    }

    private String getHashKey(List<NameValuePair> params) {
        return Hash.sha1(convertToQueryString(params) + "&" + Fyber.API_KEY);
    }
}
