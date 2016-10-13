package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.AppThis;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AppThisService extends OfferService {
    public AppThisService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "http://feed.appthis.com/real-time-feed/v1";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("api_key", AppThis.API_KEY));
        params.add(new BasicNameValuePair("androidid", this.device.getAdvertisingId()));
        params.add(new BasicNameValuePair("clickid", this.ndid));
        params.add(new BasicNameValuePair("user_agent", this.device.getWebViewUserAgent()));
        params.add(new BasicNameValuePair(ShareConstants.FEED_SOURCE_PARAM, "android"));
        params.add(new BasicNameValuePair("total_campaigns", String.valueOf(30)));
        return params;
    }

    protected Class getClazz() {
        return AppThis.class;
    }
}
