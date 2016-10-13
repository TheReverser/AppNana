package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.HangMyAds;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HangMyAdsService extends OfferService {
    public HangMyAdsService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "http://offerwall.hangmyads.com/offerwall.php";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("pubid", HangMyAds.PUB_ID));
        params.add(new BasicNameValuePair("subid", this.ndid));
        params.add(new BasicNameValuePair("subid4", "android"));
        params.add(new BasicNameValuePair("google_aid", this.device.getAdvertisingId()));
        params.add(new BasicNameValuePair("type", "incent"));
        params.add(new BasicNameValuePair("model", "android"));
        params.add(new BasicNameValuePair("curr", "Nanas"));
        params.add(new BasicNameValuePair("format", "json"));
        return params;
    }

    protected Class getClazz() {
        return HangMyAds.class;
    }
}
