package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class StreamingAdReportEvent extends AdReportEvent<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    Factory e;

    @Singleton
    /* compiled from: vungle */
    static class Factory extends com.vungle.publisher.db.model.AdReportEvent.Factory<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        @Inject
        Provider<StreamingAdReportEvent> a;

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new StreamingAdReportEvent[i];
        }

        protected final /* synthetic */ as c_() {
            return (StreamingAdReportEvent) this.a.get();
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

    protected StreamingAdReportEvent() {
    }
}
