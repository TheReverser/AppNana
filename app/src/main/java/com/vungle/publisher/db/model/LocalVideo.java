package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import com.vungle.publisher.at.b;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class LocalVideo extends Video<LocalAd, LocalVideo, RequestLocalAdResponse> implements a {
    String a;
    public LocalViewableDelegate b;
    @Inject
    com.vungle.publisher.db.model.LocalAd.Factory c;
    @Inject
    Factory d;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.db.model.Video.Factory<LocalAd, LocalVideo, RequestLocalAdResponse> {
        private static final b d = b.localVideo;
        @Inject
        Provider<LocalVideo> a;
        @Inject
        Factory b;

        protected final /* synthetic */ Viewable b(Ad ad, RequestAdResponse requestAdResponse) {
            return a((LocalAd) ad, (RequestLocalAdResponse) requestAdResponse);
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new LocalVideo[i];
        }

        protected final /* synthetic */ as c_() {
            LocalVideo localVideo = (LocalVideo) this.a.get();
            localVideo.b = this.b.a(localVideo);
            return localVideo;
        }

        protected Factory() {
        }

        protected final b b() {
            return d;
        }

        private LocalVideo a(LocalAd localAd, RequestLocalAdResponse requestLocalAdResponse) {
            LocalVideo localVideo = (LocalVideo) super.a((Ad) localAd, (RequestAdResponse) requestLocalAdResponse);
            if (localVideo != null) {
                localVideo.a = requestLocalAdResponse.w;
                localVideo.a(requestLocalAdResponse.v);
                localVideo.b.c = requestLocalAdResponse.m();
                localVideo.q = d;
            }
            return localVideo;
        }

        private LocalVideo a(LocalVideo localVideo, Cursor cursor, boolean z) {
            super.a((Video) localVideo, cursor, z);
            localVideo.b.a(cursor);
            localVideo.a = aq.f(cursor, "checksum");
            return localVideo;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory D() {
        return this.c;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.d;
    }

    protected LocalVideo() {
    }

    public final String g() {
        return this.b.c;
    }

    public final void a(Integer num) {
        this.b.d = num;
    }

    public final String h() {
        return "mp4";
    }

    public final String k() {
        return this.b.c();
    }

    public final Uri i() {
        return Uri.parse(this.b.c());
    }

    public final void l() {
        this.b.e();
    }

    public final boolean q() {
        return this.b.f();
    }

    public final boolean t() {
        return this.b.g();
    }

    public final boolean u() {
        return this.b.g();
    }

    public final boolean A() {
        return this.b.h();
    }

    public final int n() {
        return this.b.d();
    }

    public final boolean B() {
        return this.b.i();
    }

    public final int C() {
        return super.n();
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.b.a(a);
        a.put("checksum", this.a);
        return a;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        this.b.a(p);
        as.a(p, "checksum", this.a, false);
        return p;
    }
}
