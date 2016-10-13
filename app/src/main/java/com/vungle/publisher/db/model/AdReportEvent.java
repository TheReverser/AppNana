package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.share.internal.ShareConstants;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.cr;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import java.util.List;

/* compiled from: vungle */
public abstract class AdReportEvent<T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends as<Integer> {
    P a;
    public a b;
    public long c;
    public String d;
    private String e;

    /* compiled from: vungle */
    public static abstract class Factory<T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends com.vungle.publisher.as.a<E, Integer> {
        protected final /* synthetic */ as a(as asVar, Cursor cursor) {
            AdReportEvent adReportEvent = (AdReportEvent) asVar;
            adReportEvent.b = (a) aq.a(cursor, "event", a.class);
            adReportEvent.s = aq.d(cursor, ShareConstants.WEB_DIALOG_PARAM_ID);
            adReportEvent.c = aq.e(cursor, "insert_timestamp_millis").longValue();
            adReportEvent.d = aq.f(cursor, "value");
            return adReportEvent;
        }

        protected Factory() {
        }

        protected final E a(P p, a aVar, Object obj) {
            if (p == null) {
                throw new IllegalArgumentException("null ad_play");
            } else if (aVar == null) {
                throw new IllegalArgumentException("null event");
            } else {
                AdReportEvent adReportEvent = (AdReportEvent) c_();
                adReportEvent.a = p;
                adReportEvent.b = aVar;
                adReportEvent.d = obj == null ? null : obj.toString();
                return adReportEvent;
            }
        }

        protected final List<E> a(P p) {
            if (p == null) {
                throw new IllegalArgumentException("null ad_play");
            }
            if (((Integer) p.s()) == null) {
                throw new IllegalArgumentException("null play_id");
            }
            List<E> a = a("play_id = ?", new String[]{((Integer) p.s()).toString()}, "insert_timestamp_millis ASC");
            for (E e : a) {
                e.a = p;
            }
            return a;
        }
    }

    /* compiled from: vungle */
    public enum a {
        back,
        close,
        custom,
        download,
        cta,
        muted,
        preRollCta,
        preRollWatch("watch"),
        replay,
        unmuted("un-muted"),
        videoerror,
        videoreset,
        volume,
        volumedown,
        volumeup;
        
        private final String p;

        private a(String str) {
            this.p = str;
        }

        public final String toString() {
            return this.p == null ? name() : this.p;
        }
    }

    protected AdReportEvent() {
    }

    private Integer d() {
        return this.a == null ? null : (Integer) this.a.s();
    }

    protected final String b() {
        return "ad_report_event";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            this.c = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
            contentValues.put("play_id", d());
            contentValues.put("event", cr.a(this.b));
            contentValues.put("value", this.d);
        }
        return contentValues;
    }

    public String toString() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        str = super.toString();
        this.e = str;
        return str;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "play_id", d(), false);
        as.a(p, "event", this.b, false);
        as.a(p, "insert_timestamp_millis", Long.valueOf(this.c), false);
        as.a(p, "value", this.d, false);
        return p;
    }
}
