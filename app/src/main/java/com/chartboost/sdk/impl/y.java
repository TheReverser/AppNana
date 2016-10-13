package com.chartboost.sdk.impl;

import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class y implements x {
    private final a a;
    private final SSLSocketFactory b;

    public interface a {
        String a(String str);
    }

    public y() {
        this(null);
    }

    public y(a aVar) {
        this(aVar, null);
    }

    public y(a aVar, SSLSocketFactory sSLSocketFactory) {
        this.a = aVar;
        this.b = sSLSocketFactory;
    }

    public HttpResponse a(k<?> kVar, Map<String, String> map) throws IOException, a {
        String a;
        String d = kVar.d();
        HashMap hashMap = new HashMap();
        hashMap.putAll(kVar.i());
        hashMap.putAll(map);
        if (this.a != null) {
            a = this.a.a(d);
            if (a == null) {
                throw new IOException("URL blocked by rewriter: " + d);
            }
        }
        a = d;
        HttpURLConnection a2 = a(new URL(a), (k) kVar);
        for (String a3 : hashMap.keySet()) {
            a2.addRequestProperty(a3, (String) hashMap.get(a3));
        }
        a(a2, (k) kVar);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (a2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, a2.getResponseCode(), a2.getResponseMessage()));
        basicHttpResponse.setEntity(a(a2));
        for (Entry entry : a2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    protected HttpURLConnection a(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private HttpURLConnection a(URL url, k<?> kVar) throws IOException {
        HttpURLConnection a = a(url);
        int t = kVar.t();
        a.setConnectTimeout(t);
        a.setReadTimeout(t);
        a.setUseCaches(false);
        a.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.b != null) {
            ((HttpsURLConnection) a).setSSLSocketFactory(this.b);
        }
        return a;
    }

    static void a(HttpURLConnection httpURLConnection, k<?> kVar) throws IOException, a {
        switch (kVar.a()) {
            case Gender.UNKNOWN /*-1*/:
                byte[] m = kVar.m();
                if (m != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", kVar.l());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(m);
                    dataOutputStream.close();
                    return;
                }
                return;
            case Gender.MALE /*0*/:
                httpURLConnection.setRequestMethod("GET");
                return;
            case Gender.FEMALE /*1*/:
                httpURLConnection.setRequestMethod("POST");
                b(httpURLConnection, kVar);
                return;
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                httpURLConnection.setRequestMethod("PUT");
                b(httpURLConnection, kVar);
                return;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case Logger.INFO_LOGGING_LEVEL /*4*/:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case Logger.WARN_LOGGING_LEVEL /*5*/:
                httpURLConnection.setRequestMethod("OPTIONS");
                return;
            case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                httpURLConnection.setRequestMethod("TRACE");
                return;
            case HapticPlaybackThread.PAUSE_AV_FOR_HAPTIC_BUFFERING /*7*/:
                b(httpURLConnection, kVar);
                httpURLConnection.setRequestMethod("PATCH");
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void b(HttpURLConnection httpURLConnection, k<?> kVar) throws IOException, a {
        byte[] q = kVar.q();
        if (q != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", kVar.p());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(q);
            dataOutputStream.close();
        }
    }
}
