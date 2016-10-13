package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.Woobi;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class WoobiService extends OfferService {
    public WoobiService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "http://api.Woobi.com/TAS/v1/offers/mobile";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("aid", String.valueOf(Woobi.APPLICATION_ID)));
        params.add(new BasicNameValuePair("cid", this.ndid));
        params.add(new BasicNameValuePair("idfa", this.device.getAdvertisingId()));
        return params;
    }

    protected Class getClazz() {
        return Woobi.class;
    }
}
