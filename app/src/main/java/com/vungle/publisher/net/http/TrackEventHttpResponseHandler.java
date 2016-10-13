package com.vungle.publisher.net.http;

import com.vungle.log.Logger;
import com.vungle.publisher.ce;
import com.vungle.publisher.cf;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public class TrackEventHttpResponseHandler extends MaxRetryAgeHttpResponseHandler {
    boolean a;
    @Inject
    Factory b;

    TrackEventHttpResponseHandler() {
    }

    protected final void c(HttpTransaction httpTransaction, cf cfVar) {
        try {
            super.c(httpTransaction, cfVar);
        } finally {
            if (this.a) {
                e(httpTransaction, cfVar);
            }
        }
    }

    private void e(HttpTransaction httpTransaction, cf cfVar) {
        try {
            TrackEventHttpRequest trackEventHttpRequest = (TrackEventHttpRequest) httpTransaction.a;
            Logger.d(Logger.NETWORK_TAG, "logging reqeust chain for " + trackEventHttpRequest);
            Ad ad = trackEventHttpRequest.e;
            List<ce> list = cfVar.c;
            if (list == null) {
                Logger.d(Logger.NETWORK_TAG, "null request chain for " + trackEventHttpRequest);
                return;
            }
            for (ce ceVar : list) {
                Factory factory = this.b;
                a aVar = trackEventHttpRequest.f;
                EventTrackingHttpLogEntry b = factory.b();
                b.a = ad.d();
                b.b = ad.h();
                b.c = aVar;
                b.e = Integer.valueOf(ceVar.b);
                b.f = Long.valueOf(ceVar.c.longValue());
                b.g = ceVar.a;
                b.r();
            }
        } catch (Throwable e) {
            Logger.w(Logger.NETWORK_TAG, "error logging call-to-action response", e);
        }
    }
}
