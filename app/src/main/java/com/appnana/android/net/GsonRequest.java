package com.appnana.android.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;

public class GsonRequest<T> extends Request<T> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[]{PROTOCOL_CHARSET});
    private final Class<T> mClazz;
    private final Gson mGson;
    private final Listener<T> mListener;
    private Map<String, String> mParams;
    private String mRequestBody;

    private GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mGson = new Gson();
        this.mClazz = clazz;
        this.mListener = listener;
    }

    public GsonRequest(String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        this(0, url, (Class) clazz, (Listener) listener, errorListener);
    }

    public GsonRequest(String url, List<NameValuePair> params, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        this(1, url, (Class) clazz, (Listener) listener, errorListener);
        this.mParams = new HashMap();
        for (NameValuePair param : params) {
            this.mParams.put(param.getName(), param.getValue());
        }
    }

    public GsonRequest(String url, String requestBody, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        this(1, url, (Class) clazz, (Listener) listener, errorListener);
        this.mRequestBody = requestBody;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> mHeaders = new HashMap();
        mHeaders.put("Accept", "application/json");
        mHeaders.put("User-Agent", Device.getInstance().getUserAgent());
        return mHeaders;
    }

    protected void deliverResponse(T response) {
        this.mListener.onResponse(response);
    }

    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data);
            MapizLog.d(this.mClazz.getSimpleName(), json);
            return Response.success(this.mGson.fromJson(json, this.mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return this.mParams;
    }

    public String getBodyContentType() {
        if (this.mRequestBody == null) {
            return super.getBodyContentType();
        }
        return PROTOCOL_CONTENT_TYPE;
    }

    public byte[] getBody() throws AuthFailureError {
        if (this.mRequestBody == null) {
            return super.getBody();
        }
        try {
            return this.mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", new Object[]{this.mRequestBody, PROTOCOL_CHARSET});
            return null;
        }
    }
}
