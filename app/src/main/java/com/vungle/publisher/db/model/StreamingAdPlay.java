package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class StreamingAdPlay extends AdPlay<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    Factory e;
    @Inject
    Factory f;

    @Singleton
    /* compiled from: vungle */
    static class Factory extends com.vungle.publisher.db.model.AdPlay.Factory<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        @Inject
        Provider<StreamingAdPlay> a;
        @Inject
        Factory b;

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new StreamingAdPlay[i];
        }

        protected final /* synthetic */ as c_() {
            return (StreamingAdPlay) this.a.get();
        }

        protected Factory() {
        }

        protected final String e_() {
            return "ad_play";
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdReportEvent.Factory a() {
        return this.f;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.e;
    }

    protected StreamingAdPlay() {
    }
}
