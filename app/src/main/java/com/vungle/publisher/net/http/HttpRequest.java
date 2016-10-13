package com.vungle.publisher.net.http;

import android.os.Bundle;
import android.text.TextUtils;
import com.vungle.publisher.bf;
import java.util.regex.Pattern;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class HttpRequest {
    public static final Pattern a = Pattern.compile("^https://");
    public String b;
    public Bundle c;
    public String d;

    /* compiled from: vungle */
    public static abstract class Factory<T extends HttpRequest> {
        @Inject
        bf a;

        public abstract T a();

        public T b() {
            T a = a();
            Bundle bundle = new Bundle();
            Object q = this.a.q();
            if (!TextUtils.isEmpty(q)) {
                bundle.putString("User-Agent", q);
            }
            a.c = bundle;
            return a;
        }
    }

    /* compiled from: vungle */
    public enum a {
        GET,
        HEAD,
        POST
    }

    /* compiled from: vungle */
    public enum b {
        download,
        reportAd,
        requestConfig,
        requestLocalAd,
        requestStreamingAd,
        sessionEnd,
        sessionStart,
        trackEvent,
        trackInstall,
        unfilledAd
    }

    public abstract a a();

    public abstract b b();

    public String toString() {
        return "{" + b() + "}";
    }
}
