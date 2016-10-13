package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import com.vungle.publisher.db.model.Ad.b;
import com.vungle.publisher.db.model.AdReport.BaseFactory;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class StreamingAdReport extends AdReport<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    Factory p;
    @Inject
    Factory q;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends BaseFactory<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        @Inject
        Factory b;
        @Inject
        com.vungle.publisher.db.model.StreamingAd.Factory d;
        @Inject
        Provider<StreamingAdReport> e;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory a() {
            return this.d;
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdPlay.Factory b() {
            return this.b;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new StreamingAdReport[i];
        }

        protected final /* synthetic */ as c_() {
            return (StreamingAdReport) this.e.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        protected Factory() {
        }

        protected final b c() {
            return b.streaming;
        }

        protected final String e_() {
            return "ad_report";
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdPlay.Factory a() {
        return this.q;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.p;
    }

    protected StreamingAdReport() {
    }
}
