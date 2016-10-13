package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.Supersonic;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class SupersonicService extends OfferService {
    public SupersonicService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "http://www.supersonicads.com/delivery/mobilePanel.php";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("applicationKey", Supersonic.APP_KEY));
        params.add(new BasicNameValuePair("applicationUserId", this.ndid));
        params.add(new BasicNameValuePair("deviceIdsAID", this.device.getAdvertisingId()));
        params.add(new BasicNameValuePair("deviceOs", "android"));
        params.add(new BasicNameValuePair("deviceModel", this.device.getModel()));
        params.add(new BasicNameValuePair("deviceOSVersion", this.device.getOSVersion()));
        params.add(new BasicNameValuePair("pageSize", String.valueOf(30)));
        params.add(new BasicNameValuePair("format", "json"));
        return params;
    }

    protected Class getClazz() {
        return Supersonic.class;
    }
}
