package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.at;
import com.vungle.publisher.at.b;
import com.vungle.publisher.db.model.Ad.a;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import com.vungle.sdk.VunglePub.Gender;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class StreamingAd extends Ad<StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    Factory u;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.streamingVideo.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.db.model.Ad.Factory<StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        @Inject
        Provider<StreamingAd> d;
        @Inject
        com.vungle.publisher.db.model.StreamingVideo.Factory e;

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new String[i];
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Video.Factory b_() {
            return this.e;
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new StreamingAd[i];
        }

        protected final /* synthetic */ as c_() {
            return (StreamingAd) this.d.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        public final StreamingAd a(RequestStreamingAdResponse requestStreamingAdResponse) {
            StreamingAd streamingAd = (StreamingAd) super.a((RequestAdResponse) requestStreamingAdResponse);
            streamingAd.a(a.ready);
            return streamingAd;
        }

        protected final Ad.b b() {
            return Ad.b.streaming;
        }

        public final StreamingAd a(String str) {
            return (StreamingAd) super.a(Ad.b.streaming, str);
        }

        protected final String e_() {
            return "ad";
        }
    }

    public final /* synthetic */ at a(b bVar) {
        switch (AnonymousClass1.a[bVar.ordinal()]) {
            case Gender.FEMALE /*1*/:
                return (StreamingVideo) k();
            default:
                super.a(bVar);
                return null;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory a() {
        return this.u;
    }

    protected final /* bridge */ /* synthetic */ as.a a_() {
        return this.u;
    }

    protected StreamingAd() {
    }
}
