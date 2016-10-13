package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.at;
import com.vungle.publisher.cr;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.i;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.q;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class Ad<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends as<String> {
    protected static final String a = ("(SELECT DISTINCT ad_id FROM ad_report WHERE status IN ('" + com.vungle.publisher.db.model.AdReport.a.reportable + "', '" + com.vungle.publisher.db.model.AdReport.a.playing + "'))");
    protected static final String b = ("id NOT IN " + a);
    protected static final String c = ("id IN " + a);
    protected String d;
    protected String e;
    protected String f;
    protected String g;
    protected Map<com.vungle.publisher.db.model.EventTracking.a, List<EventTracking>> h;
    protected long i;
    protected a j;
    protected b k;
    protected long l;
    protected V m;
    String n;
    protected boolean o;
    protected boolean p;
    @Inject
    EventBus q;

    /* compiled from: vungle */
    public static abstract class Factory<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends com.vungle.publisher.as.a<A, String> {
        @Inject
        Factory a;
        @Inject
        EventBus b;

        protected abstract b b();

        protected abstract com.vungle.publisher.db.model.Video.Factory<A, V, R> b_();

        protected Factory() {
        }

        protected A a(R r) {
            Ad ad = (Ad) c_();
            String f = r.f();
            ad.s = f;
            ad.k = b();
            ad.h = this.a.a(f, r.k());
            ad.m = b_().a(ad, (RequestAdResponse) r);
            b(ad, r);
            return ad;
        }

        public int a(A a, R r) {
            b(a, r);
            com.vungle.publisher.db.model.Video.Factory.a(a.k(), (RequestAdResponse) r).m();
            Factory factory = this.a;
            String f = r.f();
            factory.a(f);
            Map a2 = factory.a(f, r.k());
            Factory.a(a2);
            a.h = a2;
            return a.m();
        }

        protected final int c() {
            Logger.d(Logger.DATABASE_TAG, "deleting " + b() + " records without pending reports in status " + a.deleting);
            return this.c.getWritableDatabase().delete("ad", Ad.b + " AND status = ?", new String[]{r0.toString()});
        }

        public boolean a(Ad<?, ?, ?> ad) {
            if (!a("id = ? AND " + Ad.b + " AND ((expiration_timestamp_seconds IS NULL OR expiration_timestamp_seconds <= ?) OR status != ?)", new String[]{ad.d(), Long.toString(System.currentTimeMillis() / 1000), a.ready.toString()})) {
                return false;
            }
            Logger.d(Logger.DATABASE_TAG, "deleting ad after successful report");
            if (ad.n() > 0) {
                return true;
            }
            return false;
        }

        protected final A a(b bVar, String str) {
            return (Ad) super.a((Object) str, "type = ?", new String[]{bVar.toString()});
        }

        protected final int a(List<? extends Ad<?, ?, ?>> list, a aVar) {
            int size = list.size();
            Object[] objArr = new String[size];
            int i = 0;
            for (Ad ad : list) {
                objArr[0] = ad.d();
                a i2 = ad.i();
                int i3 = (aVar == a.ready || i2 != a.ready) ? (aVar != a.ready || i2 == a.ready) ? 0 : 1 : -1;
                i3 += i;
                ad.a(aVar);
                i = i3;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, aVar.toString());
            String str = "id IN (" + aq.a(size) + ")";
            Logger.d(Logger.DATABASE_TAG, "updating status of ads " + cr.a(objArr) + " to " + aVar);
            int updateWithOnConflict = this.c.getWritableDatabase().updateWithOnConflict(e_(), contentValues, str, objArr, 3);
            if (updateWithOnConflict > 0) {
                if (i > 0) {
                    Logger.d(Logger.DATABASE_TAG, "ad availability increased by " + i);
                    this.b.a(new q());
                } else if (i < 0) {
                    Logger.d(Logger.DATABASE_TAG, "ad availability decreased by " + i);
                    this.b.a(new i());
                }
            }
            return updateWithOnConflict;
        }

        private static A b(A a, R r) {
            a.d = r.b();
            Object c = r.c();
            String e = r.e();
            if (TextUtils.isEmpty(c)) {
                a.e = e;
            } else {
                a.e = c;
                a.f = e;
            }
            a.g = r.g();
            return a;
        }

        protected A a(A a, Cursor cursor, boolean z) {
            a.d = aq.f(cursor, "advertising_app_vungle_id");
            a.e = aq.f(cursor, "call_to_action_final_url");
            a.f = aq.f(cursor, "call_to_action_url");
            a.g = aq.f(cursor, "delivery_id");
            a.s = aq.f(cursor, ShareConstants.WEB_DIALOG_PARAM_ID);
            a.i = aq.e(cursor, "insert_timestamp_millis").longValue();
            a.j = (a) aq.a(cursor, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, a.class);
            a.k = (b) aq.a(cursor, "type", b.class);
            a.l = aq.e(cursor, "update_timestamp_millis").longValue();
            if (z) {
                b(a);
                a((Ad) a, z);
            }
            return a;
        }

        protected final Map<com.vungle.publisher.db.model.EventTracking.a, List<EventTracking>> b(A a) {
            if (a.o) {
                return a.h;
            }
            Map<com.vungle.publisher.db.model.EventTracking.a, List<EventTracking>> b = this.a.b((String) a.s);
            a.h = b;
            a.o = true;
            return b;
        }

        protected final V a(A a, boolean z) {
            if (a.p) {
                return a.m;
            }
            V a2 = b_().a((String) a.s, z);
            a.m = a2;
            a.p = true;
            return a2;
        }
    }

    /* compiled from: vungle */
    public enum a {
        aware,
        failed,
        invalid,
        preparing,
        ready,
        viewed,
        deleting
    }

    /* compiled from: vungle */
    public enum b {
        local,
        streaming
    }

    protected abstract Factory<A, V, R> a();

    public /* synthetic */ Object r() {
        return l();
    }

    public final /* bridge */ /* synthetic */ Object s() {
        return (String) this.s;
    }

    protected Ad() {
    }

    protected final String b() {
        return "ad";
    }

    protected final boolean f_() {
        return false;
    }

    public final String d() {
        return (String) this.s;
    }

    public final String e() {
        return this.d;
    }

    public final String f() {
        return this.e;
    }

    public final String g() {
        return this.f;
    }

    public final String h() {
        return this.g;
    }

    public final String[] a(com.vungle.publisher.db.model.EventTracking.a aVar) {
        if (t() != null) {
            List<EventTracking> list = (List) t().get(aVar);
            if (list != null) {
                int size = list.size();
                if (size > 0) {
                    String[] strArr = new String[size];
                    int i = 0;
                    for (EventTracking eventTracking : list) {
                        size = i + 1;
                        strArr[i] = eventTracking.c;
                        i = size;
                    }
                    return strArr;
                }
            }
        }
        return null;
    }

    private Map<com.vungle.publisher.db.model.EventTracking.a, List<EventTracking>> t() {
        return a().b(this);
    }

    public final a i() {
        return this.j;
    }

    public void a(a aVar) {
        Logger.v(Logger.PREPARE_TAG, "setting status from " + this.j + " to " + aVar + " for " + y());
        this.j = aVar;
    }

    public final void b(a aVar) {
        a().a(Arrays.asList(new Ad[]{this}), aVar);
    }

    public final long j() {
        return this.l;
    }

    public final V k() {
        return a().a(this, false);
    }

    public <W extends at<A, V, R>> W a(com.vungle.publisher.at.b bVar) {
        throw new IllegalArgumentException("unknown viewable type: " + bVar);
    }

    public String l() throws SQLException {
        String str = (String) super.r();
        if (this.h != null) {
            for (List<EventTracking> it : this.h.values()) {
                for (EventTracking r : it) {
                    r.r();
                }
            }
        }
        if (this.m != null) {
            this.m.r();
        }
        return str;
    }

    public int m() {
        int m = super.m();
        if (m == 1 && this.m != null) {
            this.m.m();
        }
        return m;
    }

    public int n() {
        return super.n();
    }

    protected ContentValues a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        this.l = currentTimeMillis;
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(ShareConstants.WEB_DIALOG_PARAM_ID, (String) this.s);
            this.i = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
            contentValues.put("type", this.k.toString());
        }
        contentValues.put("advertising_app_vungle_id", this.d);
        contentValues.put("call_to_action_final_url", this.e);
        contentValues.put("call_to_action_url", this.f);
        contentValues.put("delivery_id", this.g);
        contentValues.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, this.j.toString());
        contentValues.put("update_timestamp_millis", Long.valueOf(currentTimeMillis));
        return contentValues;
    }

    protected final StringBuilder o() {
        StringBuilder o = super.o();
        as.a(o, "type", this.k, false);
        return o;
    }

    protected StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "advertising_app_vungle_id", this.d, false);
        as.a(p, "call_to_action_final_url", this.e, false);
        as.a(p, "call_to_action_url", this.f, false);
        as.a(p, "delivery_id", this.g, false);
        as.a(p, "insert_timestamp_millis", Long.valueOf(this.i), false);
        as.a(p, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, this.j, false);
        as.a(p, "update_timestamp_millis", Long.valueOf(this.l), false);
        as.a(p, "eventTrackings", this.h == null ? null : Integer.valueOf(this.h.size()), false);
        return p;
    }

    public boolean equals(Object ad) {
        return (ad instanceof Ad) && a((Ad) ad);
    }

    public final boolean a(Ad<?, ?, ?> ad) {
        return (ad == null || ad.s == null || !((String) ad.s).equals(this.s)) ? false : true;
    }

    public int hashCode() {
        return this.s == null ? super.hashCode() : ((String) this.s).hashCode();
    }

    public final boolean q() {
        return a().a(this);
    }
}
