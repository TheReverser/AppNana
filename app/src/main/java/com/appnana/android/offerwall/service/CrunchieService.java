package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.Crunchie;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class CrunchieService extends OfferService {
    public CrunchieService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "https://ads.crunchiemedia.com/";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("a", Crunchie.TOKEN));
        params.add(new BasicNameValuePair("p", "android"));
        params.add(new BasicNameValuePair("deviceid", this.device.getAdvertisingId()));
        params.add(new BasicNameValuePair("ndid", this.ndid));
        params.add(new BasicNameValuePair("platform", "android"));
        return params;
    }

    protected Class getClazz() {
        return Crunchie.class;
    }
}
