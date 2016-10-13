package com.appnana.android.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.appnana.android.utils.Device;
import com.facebook.BuildConfig;
import java.util.HashMap;
import java.util.Map;

public class PingRequest<T> extends Request<String> {
    public PingRequest(String url) {
        super(0, url, null);
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> mHeaders = new HashMap();
        mHeaders.put("User-Agent", Device.getInstance().getUserAgent());
        return mHeaders;
    }

    protected void deliverResponse(String response) {
    }

    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return Response.success(BuildConfig.VERSION_NAME, HttpHeaderParser.parseCacheHeaders(response));
    }
}
