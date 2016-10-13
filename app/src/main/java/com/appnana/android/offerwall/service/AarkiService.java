package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.Aarki;
import com.facebook.AccessToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AarkiService extends OfferService {
    public AarkiService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "http://ar.aarki.net/offers";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("src", Aarki.PLACEMENT_ID));
        params.add(new BasicNameValuePair(AccessToken.USER_ID_KEY, this.ndid));
        params.add(new BasicNameValuePair("advertising_id", this.device.getAdvertisingId()));
        params.add(new BasicNameValuePair("tracking_limited", this.device.isLimitAdTrackingEnabled() ? "y" : "n"));
        params.add(new BasicNameValuePair("country", this.device.getCountry().toUpperCase(Locale.US)));
        return params;
    }

    protected Class getClazz() {
        return Aarki.class;
    }
}
