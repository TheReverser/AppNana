package com.appnana.android.offerwall.service;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.net.GsonRequest;
import com.appnana.android.net.HttpClient;
import com.appnana.android.offerwall.model.AppEvery;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AppEveryService extends OfferService {
    private static final String TAG = AppEveryService.class.getSimpleName();

    private class AppEveryGsonRequest extends GsonRequest {
        AppEveryGsonRequest(String url, Class clazz, Listener listener, ErrorListener errorListener) {
            super(url, clazz, listener, errorListener);
        }

        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> mHeaders = new HashMap();
            mHeaders.put("Accept", "application/json; version=0.1");
            mHeaders.put("User-Agent", Device.getInstance().getWebViewUserAgent());
            return mHeaders;
        }
    }

    public AppEveryService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "http://offerwall.appevery.com/api/offers/";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("pid", AppEvery.PUBLISHER_ID));
        params.add(new BasicNameValuePair("uid", this.ndid));
        params.add(new BasicNameValuePair("gaid", this.device.getAdvertisingId()));
        return params;
    }

    protected Class getClazz() {
        return AppEvery.class;
    }

    public void fetchData(String tag, Listener listener, ErrorListener errorListener) {
        AppEveryGsonRequest request = new AppEveryGsonRequest(getFullUrl(), getClazz(), listener, errorListener);
        MapizLog.d(tag, getFullUrl());
        HttpClient instance = HttpClient.getInstance();
        if (tag == null) {
            tag = TAG;
        }
        instance.addToRequestQueue(request, tag);
    }
}
