package com.vungle.publisher.protocol;

import android.os.SystemClock;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.AdManager.AnonymousClass3;
import com.vungle.publisher.ad.AdPreparer;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.cf;
import com.vungle.publisher.cg;
import com.vungle.publisher.ch;
import com.vungle.publisher.db.model.Ad.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler;
import com.vungle.publisher.o;
import com.vungle.publisher.p;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse.Factory;
import com.vungle.publisher.r;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import com.vungle.sdk.VunglePub.Gender;
import dagger.Lazy;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class RequestLocalAdHttpResponseHandler extends InfiniteRetryHttpResponseHandler {
    @Inject
    AdPreparer a;
    @Inject
    AdServiceReportingHandler b;
    @Inject
    EventBus c;
    @Inject
    EventTrackingHttpLogEntryDeleteDelegate d;
    @Inject
    Lazy<AdManager> e;
    @Inject
    Lazy<SdkState> j;
    @Inject
    Factory k;
    @Inject
    ScheduledPriorityExecutor l;
    @Inject
    ProtocolHttpGateway m;

    protected final void a(HttpTransaction httpTransaction, cf cfVar) throws IOException, JSONException {
        RequestLocalAdResponse requestLocalAdResponse = (RequestLocalAdResponse) this.k.a(cg.a(cfVar.a));
        Integer valueOf = requestLocalAdResponse.r == null ? null : Integer.valueOf(requestLocalAdResponse.r.intValue() * 1000);
        AdServiceReportingHandler adServiceReportingHandler = this.b;
        adServiceReportingHandler.b = SystemClock.elapsedRealtime() - adServiceReportingHandler.a;
        this.d.a(((RequestLocalAdHttpRequest) httpTransaction.a).e.g);
        if (valueOf == null) {
            if ((requestLocalAdResponse.s.longValue() * 1000 < System.currentTimeMillis() ? 1 : null) != null) {
                Logger.i(Logger.NETWORK_TAG, "received expired ad from server, tossing it and getting a new one");
                this.c.a(new r(httpTransaction.b));
                return;
            }
            AdManager adManager = (AdManager) this.e.get();
            String f = requestLocalAdResponse.f();
            LocalAd localAd = (LocalAd) adManager.g.a((Object) f);
            if (localAd != null) {
                try {
                    adManager.g.a(localAd, requestLocalAdResponse);
                } catch (Throwable e) {
                    Logger.w(Logger.PREPARE_TAG, "error updating ad " + f, e);
                }
                a i = localAd.i();
                switch (AnonymousClass3.a[i.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                    case Logger.INFO_LOGGING_LEVEL /*4*/:
                        Logger.i(Logger.PREPARE_TAG, "received " + localAd.y() + " in status " + i);
                        adManager.a.a(f);
                        break;
                    default:
                        Logger.w(Logger.PREPARE_TAG, "received " + localAd.y() + " in status " + i + " - ignoring");
                        break;
                }
            }
            try {
                adManager.g.h();
                localAd = adManager.g.a(requestLocalAdResponse);
                Logger.i(Logger.PREPARE_TAG, "received new " + localAd.y());
                localAd.l();
                adManager.a.a(f);
            } catch (Throwable e2) {
                Logger.w(Logger.PREPARE_TAG, "error preparing ad " + f, e2);
                adManager.d.a(new p());
            }
            SdkState sdkState = (SdkState) this.j.get();
            Integer a = requestLocalAdResponse.a();
            if (a == null) {
                Logger.v(Logger.AD_TAG, "ignoring set null min ad delay seconds");
                return;
            }
            int intValue = a.intValue();
            Logger.d(Logger.AD_TAG, "setting min ad delay seconds: " + intValue);
            sdkState.o.edit().putInt("VgAdDelayDuration", intValue).apply();
            return;
        }
        this.l.a(new ch(httpTransaction), httpTransaction.c, (long) valueOf.intValue());
    }

    protected final void a(HttpTransaction httpTransaction, cf cfVar, Exception exception) {
        this.c.a(new o());
    }
}
