package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class LocalAdReportEvent extends AdReportEvent<LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo, RequestLocalAdResponse> {
    @Inject
    Factory e;
    @Inject
    Factory f;

    @Singleton
    /* compiled from: vungle */
    static class Factory extends com.vungle.publisher.db.model.AdReportEvent.Factory<LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo, RequestLocalAdResponse> {
        @Inject
        Provider<LocalAdReportEvent> a;

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new LocalAdReportEvent[i];
        }

        protected final /* synthetic */ as c_() {
            return (LocalAdReportEvent) this.a.get();
        }

        protected Factory() {
        }

        protected final String e_() {
            return "ad_report_event";
        }
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.e;
    }

    protected LocalAdReportEvent() {
    }
}
