package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.at.b;
import com.vungle.publisher.db.model.Viewable.BaseFactory;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestAdResponse.CallToActionOverlay;

/* compiled from: vungle */
public abstract class Video<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends Viewable<A, V, R> {
    public Float e;
    public Integer f;
    public Integer g;
    public Boolean h;
    public Boolean i;
    public Integer j;
    public Integer k;
    public Integer l;
    public Integer m;
    public Integer n;

    /* compiled from: vungle */
    public static abstract class Factory<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends BaseFactory<A, V, V, R> {
        protected abstract b b();

        protected /* synthetic */ Viewable b(Ad ad, RequestAdResponse requestAdResponse) {
            return a(ad, requestAdResponse);
        }

        protected Factory() {
        }

        protected V a(A a, R r) {
            Video video = (Video) super.b(a, r);
            if (video != null) {
                a(video, (RequestAdResponse) r);
            }
            return video;
        }

        static V a(V v, RequestAdResponse requestAdResponse) {
            v.g = requestAdResponse.l();
            v.k = requestAdResponse.h();
            v.l = requestAdResponse.i();
            v.m = requestAdResponse.j();
            v.n = requestAdResponse.n();
            CallToActionOverlay d = requestAdResponse.d();
            if (d != null) {
                v.e = d.c();
                v.f = d.g();
                v.h = d.d();
                v.i = d.e();
                v.j = d.f();
            }
            return v;
        }

        protected final V a(String str, boolean z) throws SQLException {
            return (Video) a(str, b(), z);
        }

        protected V a(V v, Cursor cursor, boolean z) {
            super.a((Viewable) v, cursor, z);
            v.e = aq.b(cursor, "cta_clickable_percent");
            v.f = aq.d(cursor, "enable_cta_delay_seconds");
            v.g = aq.d(cursor, "height");
            v.h = aq.a(cursor, "is_cta_enabled");
            v.i = aq.a(cursor, "is_cta_shown_on_touch");
            v.j = aq.d(cursor, "show_cta_delay_seconds");
            v.k = aq.d(cursor, "show_close_delay_incentivized_seconds");
            v.l = aq.d(cursor, "show_close_delay_interstitial_seconds");
            v.m = aq.d(cursor, "show_countdown_delay_seconds");
            v.n = aq.d(cursor, "width");
            return v;
        }
    }

    public abstract Uri i();

    protected Video() {
    }

    protected ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put("cta_clickable_percent", this.e);
        a.put("enable_cta_delay_seconds", this.f);
        a.put("height", this.g);
        a.put("is_cta_enabled", this.h);
        a.put("is_cta_shown_on_touch", this.i);
        a.put("show_cta_delay_seconds", this.j);
        a.put("show_close_delay_incentivized_seconds", this.k);
        a.put("show_close_delay_interstitial_seconds", this.l);
        a.put("show_countdown_delay_seconds", this.m);
        a.put("width", this.n);
        return a;
    }

    protected StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "cta_clickable_percent", this.e, false);
        as.a(p, "enable_cta_delay_seconds", this.f, false);
        as.a(p, "height", this.g, false);
        as.a(p, "is_cta_enabled", this.h, false);
        as.a(p, "is_cta_shown_on_touch", this.i, false);
        as.a(p, "show_cta_delay_seconds", this.j, false);
        as.a(p, "show_close_delay_incentivized_seconds", this.k, false);
        as.a(p, "show_close_delay_interstitial_seconds", this.l, false);
        as.a(p, "show_countdown_delay_seconds", this.m, false);
        as.a(p, "width", this.n, false);
        return p;
    }
}
