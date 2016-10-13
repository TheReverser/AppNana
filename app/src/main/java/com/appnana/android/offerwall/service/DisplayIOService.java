package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.DisplayIO;
import com.appnana.android.utils.Device;
import com.facebook.appevents.AppEventsConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class DisplayIOService extends OfferService {
    public DisplayIOService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "http://srv.display.io/asrv";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("placement", AppEventsConstants.EVENT_PARAM_VALUE_YES));
        List<NameValuePair> appendParams = new ArrayList();
        appendParams.add(new BasicNameValuePair("google_aid", Device.getInstance().getAdvertisingId()));
        appendParams.add(new BasicNameValuePair("aff_sub", this.ndid));
        appendParams.add(new BasicNameValuePair("aff_sub2", "android"));
        params.add(new BasicNameValuePair("cappend", URLEncodedUtils.format(appendParams, "UTF-8")));
        return params;
    }

    protected Class getClazz() {
        return DisplayIO.class;
    }
}
