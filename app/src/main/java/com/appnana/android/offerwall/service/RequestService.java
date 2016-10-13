package com.appnana.android.offerwall.service;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.net.GsonRequest;
import com.appnana.android.net.HttpClient;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import com.facebook.BuildConfig;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public abstract class RequestService {
    private static final String TAG = OfferService.class.getSimpleName();
    protected Device device = Device.getInstance();

    protected abstract Class getClazz();

    protected abstract List<NameValuePair> getParams();

    protected abstract int getRequestMethod();

    protected abstract String getUrl();

    protected RequestService() {
    }

    public void fetchData(String tag, Listener listener, ErrorListener errorListener) {
        GsonRequest request;
        if (getRequestMethod() == 0) {
            request = new GsonRequest(getFullUrl(), getClazz(), listener, errorListener);
            MapizLog.d(tag, getFullUrl());
        } else {
            MapizLog.d(tag, getUrl());
            String requestBody = getRequestBody();
            if (requestBody == null) {
                request = new GsonRequest(getUrl(), getParams(), getClazz(), listener, errorListener);
                MapizLog.d(tag, URLEncodedUtils.format(getParams(), "UTF-8"));
            } else {
                request = new GsonRequest(getUrl(), requestBody, getClazz(), listener, errorListener);
                MapizLog.d(tag, requestBody);
            }
        }
        HttpClient instance = HttpClient.getInstance();
        if (tag == null) {
            tag = TAG;
        }
        instance.addToRequestQueue(request, tag);
    }

    public String getFullUrl() {
        if (getParams() == null) {
            return getUrl();
        }
        return getUrl() + "?" + URLEncodedUtils.format(getParams(), "UTF-8");
    }

    protected String getRequestBody() {
        return null;
    }

    protected String convertToQueryString(List<NameValuePair> params) {
        String data = BuildConfig.VERSION_NAME;
        for (NameValuePair param : params) {
            data = data + "&" + param.getName() + "=" + param.getValue();
        }
        if (data.startsWith("&")) {
            return data.substring(1);
        }
        return data;
    }
}
