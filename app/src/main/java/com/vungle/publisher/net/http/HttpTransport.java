package com.vungle.publisher.net.http;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.immersion.hapticmediasdk.HapticContentSDK;
import com.vungle.log.Logger;
import com.vungle.publisher.ce;
import com.vungle.publisher.cf;
import com.vungle.publisher.cq;
import com.vungle.publisher.net.http.HttpRequest.a;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class HttpTransport {
    static {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    @Inject
    HttpTransport() {
    }

    public static cf a(HttpRequest httpRequest) {
        HttpURLConnection httpURLConnection = null;
        int i = -1;
        List arrayList = new ArrayList();
        try {
            a a = httpRequest.a();
            String str = httpRequest.b;
            int i2 = 0;
            while (i2 < 5) {
                Logger.d(Logger.NETWORK_TAG, a + " " + str);
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setConnectTimeout(HapticContentSDK.b044404440444ф04440444);
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setReadTimeout(HapticContentSDK.b044404440444ф04440444);
                httpURLConnection.setUseCaches(false);
                if (a != null) {
                    httpURLConnection.setRequestMethod(a.toString());
                }
                Bundle bundle = httpRequest.c;
                if (bundle != null) {
                    for (String str2 : bundle.keySet()) {
                        String string = bundle.getString(str2);
                        if (string == null) {
                            for (String str3 : bundle.getStringArray(str2)) {
                                Logger.v(Logger.NETWORK_TAG, "request header: " + str2 + ": " + str3);
                                httpURLConnection.addRequestProperty(str2, str3);
                            }
                        } else {
                            Logger.v(Logger.NETWORK_TAG, "request header: " + str2 + ": " + string);
                            httpURLConnection.addRequestProperty(str2, string);
                        }
                    }
                }
                String str22 = httpRequest.d;
                if (!TextUtils.isEmpty(str22)) {
                    Logger.d(Logger.NETWORK_TAG, "request body: " + str22);
                    byte[] bytes = str22.getBytes();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                    httpURLConnection.getOutputStream().write(bytes);
                }
                i = httpURLConnection.getResponseCode();
                if (a(i2, i)) {
                    String headerField = httpURLConnection.getHeaderField("Location");
                    Logger.i(Logger.NETWORK_TAG, a(a, i2, i, httpURLConnection.getContentType(), httpRequest.b, str, headerField));
                    arrayList.add(new ce(str, i, headerField, httpURLConnection.getHeaderFieldDate("Date", -1) == -1 ? null : Long.valueOf(httpURLConnection.getHeaderFieldDate("Date", -1))));
                    i2++;
                    str = headerField;
                } else {
                    if ((i == 200 ? 1 : null) != null) {
                        Logger.d(Logger.NETWORK_TAG, a(a, i2, i, httpURLConnection.getContentType(), httpRequest.b, str, null));
                    } else {
                        Logger.i(Logger.NETWORK_TAG, a(a, i2, i, httpURLConnection.getContentType(), httpRequest.b, str, null));
                    }
                    return new cf(httpURLConnection, i, arrayList);
                }
            }
        } catch (Throwable e) {
            Logger.w(Logger.NETWORK_TAG, cq.a(e));
            i = 601;
        } catch (Throwable e2) {
            Logger.d(Logger.NETWORK_TAG, cq.a(e2));
            i = 602;
        } catch (Throwable e22) {
            Logger.d(Logger.NETWORK_TAG, cq.a(e22));
            i = 603;
        } catch (Throwable e222) {
            Logger.w(Logger.NETWORK_TAG, cq.a(e222));
            i = 600;
        }
        return new cf(httpURLConnection, i, arrayList);
    }

    private static boolean a(int i, int i2) {
        if (i <= 0) {
            boolean z = i2 == 301 || i2 == 302;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private static String a(a aVar, int i, int i2, String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder("HTTP");
        boolean a = a(i, i2);
        if (a) {
            stringBuilder.append(" redirect count ").append(i).append(',');
        }
        stringBuilder.append(" response code ").append(i2).append(", content-type ").append(str).append(" for ").append(aVar).append(" to");
        if (i > 0) {
            stringBuilder.append(" original URL ").append(str2).append(',');
        }
        stringBuilder.append(" requested URL ").append(str3);
        if (a) {
            stringBuilder.append(", next URL ").append(str4);
        }
        return stringBuilder.toString();
    }
}
