package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.AdscendMedia;
import com.facebook.appevents.AppEventsConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AdscendMediaService extends OfferService {
    public AdscendMediaService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return String.format("http://adscendmedia.com/adwall/api/publisher/%d/profile/%d/offers.json", new Object[]{Integer.valueOf(AdscendMedia.PUBLISHER_ID), Integer.valueOf(AdscendMedia.AD_WALL_ID)});
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("subid1", this.ndid));
        params.add(new BasicNameValuePair("limit", String.valueOf(30)));
        params.add(new BasicNameValuePair("offset", AppEventsConstants.EVENT_PARAM_VALUE_NO));
        params.add(new BasicNameValuePair("category_id", String.format("%d,%d", new Object[]{Integer.valueOf(18), Integer.valueOf(19)})));
        params.add(new BasicNameValuePair("device", this.device.getAdvertisingId()));
        params.add(new BasicNameValuePair("user_agent", this.device.getWebViewUserAgent()));
        params.add(new BasicNameValuePair("subid2", "android"));
        return params;
    }

    protected Class getClazz() {
        return AdscendMedia.class;
    }
}
