package com.appnana.android.net;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class HttpClient {
    private static final String TAG = HttpClient.class.getSimpleName();
    private static Context mContext;
    private static HttpClient mInstance;
    private RequestQueue mRequestQueue;

    public static HttpClient newInstance(Context context) {
        mContext = context;
        mInstance = new HttpClient();
        return mInstance;
    }

    public static HttpClient getInstance() {
        if (mContext != null) {
            return mInstance;
        }
        throw new NullPointerException("Must call HttpClient.newInstance(ApplicationContext) in Application class first.");
    }

    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue(mContext, new OkHttpStack());
        }
        return this.mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.cancelAll(tag);
        }
    }
}
