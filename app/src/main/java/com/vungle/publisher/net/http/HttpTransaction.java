package com.vungle.publisher.net.http;

import android.os.SystemClock;
import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.cg;
import com.vungle.publisher.ci;
import java.util.EnumMap;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class HttpTransaction {
    public HttpRequest a;
    public ci b;
    public b c;
    @Inject
    HttpTransport d;
    private cg e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        private static final EnumMap<HttpRequest.b, b> a;
        @Inject
        Provider<HttpTransaction> c;

        static {
            EnumMap enumMap = new EnumMap(HttpRequest.b.class);
            a = enumMap;
            enumMap.put(HttpRequest.b.download, b.downloadLocalAd);
            a.put(HttpRequest.b.reportAd, b.reportAd);
            a.put(HttpRequest.b.requestConfig, b.requestConfig);
            a.put(HttpRequest.b.requestLocalAd, b.requestLocalAd);
            a.put(HttpRequest.b.requestStreamingAd, b.requestStreamingAd);
            a.put(HttpRequest.b.sessionEnd, b.sessionEnd);
            a.put(HttpRequest.b.sessionStart, b.sessionStart);
            a.put(HttpRequest.b.trackEvent, b.externalNetworkRequest);
            a.put(HttpRequest.b.trackInstall, b.reportInstall);
            a.put(HttpRequest.b.unfilledAd, b.unfilledAd);
        }

        public final HttpTransaction a(HttpRequest httpRequest, cg cgVar) {
            return a(httpRequest, cgVar, new ci());
        }

        public final HttpTransaction a(HttpRequest httpRequest, cg cgVar, ci ciVar) {
            HttpTransaction httpTransaction = (HttpTransaction) this.c.get();
            httpTransaction.a = httpRequest;
            httpTransaction.e = cgVar;
            b bVar = (b) a.get(httpRequest.b());
            if (bVar == null) {
                Logger.w(Logger.NETWORK_TAG, "missing mapping for HttpTransaction requestType = " + httpRequest.b().toString());
                bVar = b.otherTask;
            }
            httpTransaction.c = bVar;
            httpTransaction.b = ciVar;
            return httpTransaction;
        }
    }

    HttpTransaction() {
    }

    public final void a() {
        ci ciVar = this.b;
        if (ciVar.a <= 0) {
            ciVar.a = SystemClock.elapsedRealtime();
        }
        ciVar.b++;
        ciVar.c++;
        this.e.c(this, HttpTransport.a(this.a));
    }

    public String toString() {
        return "{" + this.a + ", " + this.b + "}";
    }
}
