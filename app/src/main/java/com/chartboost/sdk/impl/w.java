package com.chartboost.sdk.impl;

import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class w implements x {
    protected final HttpClient a;

    public static final class a extends HttpEntityEnclosingRequestBase {
        public a(String str) {
            setURI(URI.create(str));
        }

        public String getMethod() {
            return "PATCH";
        }
    }

    public w(HttpClient httpClient) {
        this.a = httpClient;
    }

    private static void a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    public HttpResponse a(k<?> kVar, Map<String, String> map) throws IOException, a {
        HttpUriRequest b = b(kVar, map);
        a(b, (Map) map);
        a(b, kVar.i());
        a(b);
        HttpParams params = b.getParams();
        int t = kVar.t();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, t);
        return this.a.execute(b);
    }

    static HttpUriRequest b(k<?> kVar, Map<String, String> map) throws a {
        HttpEntityEnclosingRequestBase httpPost;
        switch (kVar.a()) {
            case Gender.UNKNOWN /*-1*/:
                byte[] m = kVar.m();
                if (m == null) {
                    return new HttpGet(kVar.d());
                }
                HttpUriRequest httpPost2 = new HttpPost(kVar.d());
                httpPost2.addHeader("Content-Type", kVar.l());
                httpPost2.setEntity(new ByteArrayEntity(m));
                return httpPost2;
            case Gender.MALE /*0*/:
                return new HttpGet(kVar.d());
            case Gender.FEMALE /*1*/:
                httpPost = new HttpPost(kVar.d());
                httpPost.addHeader("Content-Type", kVar.p());
                a(httpPost, (k) kVar);
                return httpPost;
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                httpPost = new HttpPut(kVar.d());
                httpPost.addHeader("Content-Type", kVar.p());
                a(httpPost, (k) kVar);
                return httpPost;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                return new HttpDelete(kVar.d());
            case Logger.INFO_LOGGING_LEVEL /*4*/:
                return new HttpHead(kVar.d());
            case Logger.WARN_LOGGING_LEVEL /*5*/:
                return new HttpOptions(kVar.d());
            case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                return new HttpTrace(kVar.d());
            case HapticPlaybackThread.PAUSE_AV_FOR_HAPTIC_BUFFERING /*7*/:
                httpPost = new a(kVar.d());
                httpPost.addHeader("Content-Type", kVar.p());
                a(httpPost, (k) kVar);
                return httpPost;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    private static void a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, k<?> kVar) throws a {
        byte[] q = kVar.q();
        if (q != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(q));
        }
    }

    protected void a(HttpUriRequest httpUriRequest) throws IOException {
    }
}
