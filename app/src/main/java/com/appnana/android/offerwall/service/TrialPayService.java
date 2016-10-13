package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.TrialPay;
import com.facebook.appevents.AppEventsConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class TrialPayService extends OfferService {
    public TrialPayService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "https://geo.tp-cdn.com/api/offer/v1/";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("vic", TrialPay.VIC));
        params.add(new BasicNameValuePair("sid", this.ndid));
        params.add(new BasicNameValuePair("gaid", this.device.getAdvertisingId()));
        params.add(new BasicNameValuePair("gaid_en", this.device.isLimitAdTrackingEnabled() ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES));
        params.add(new BasicNameValuePair("ua", this.device.getWebViewUserAgent()));
        params.add(new BasicNameValuePair("num_offers", String.valueOf(30)));
        return params;
    }

    protected Class getClazz() {
        return TrialPay.class;
    }
}
