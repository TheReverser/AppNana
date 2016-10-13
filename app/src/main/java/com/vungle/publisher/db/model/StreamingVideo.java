package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.facebook.internal.NativeProtocol;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import com.vungle.publisher.at.b;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class StreamingVideo extends Video<StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    com.vungle.publisher.db.model.StreamingAd.Factory a;
    @Inject
    Factory b;
    public String c;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.db.model.Video.Factory<StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        private static final b b = b.streamingVideo;
        @Inject
        Provider<StreamingVideo> a;

        protected final /* synthetic */ Viewable b(Ad ad, RequestAdResponse requestAdResponse) {
            return a((StreamingAd) ad, (RequestStreamingAdResponse) requestAdResponse);
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new StreamingVideo[i];
        }

        protected final /* synthetic */ as c_() {
            return (StreamingVideo) this.a.get();
        }

        protected Factory() {
        }

        protected final b b() {
            return b;
        }

        private StreamingVideo a(StreamingAd streamingAd, RequestStreamingAdResponse requestStreamingAdResponse) {
            StreamingVideo streamingVideo = (StreamingVideo) super.a((Ad) streamingAd, (RequestAdResponse) requestStreamingAdResponse);
            if (streamingVideo != null) {
                streamingVideo.c = requestStreamingAdResponse.m();
                streamingVideo.q = b;
            }
            return streamingVideo;
        }

        private StreamingVideo a(StreamingVideo streamingVideo, Cursor cursor, boolean z) {
            super.a((Video) streamingVideo, cursor, z);
            streamingVideo.c = aq.f(cursor, NativeProtocol.WEB_DIALOG_URL);
            return streamingVideo;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory D() {
        return this.a;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.b;
    }

    protected StreamingVideo() {
    }

    public final Uri i() {
        return Uri.parse(this.c);
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put(NativeProtocol.WEB_DIALOG_URL, this.c);
        return a;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, NativeProtocol.WEB_DIALOG_URL, this.c, false);
        return p;
    }
}
