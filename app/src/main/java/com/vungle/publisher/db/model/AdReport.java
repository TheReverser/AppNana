package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.cr;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.Ad.b;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.sdk.VunglePub.Gender;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public abstract class AdReport<T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends as<Integer> {
    protected A a;
    protected String b;
    protected String c;
    protected Long d;
    protected boolean e;
    protected String f;
    protected a g;
    protected Long h;
    protected Integer i;
    protected Long j;
    protected Long k;
    protected Map<String, AdReportExtra> l;
    protected List<P> m;
    protected boolean n;
    @Inject
    com.vungle.publisher.db.model.AdReportExtra.Factory o;

    /* compiled from: vungle */
    public static abstract class BaseFactory<T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends com.vungle.publisher.as.a<T, Integer> {
        @Inject
        com.vungle.publisher.db.model.AdReportExtra.Factory a;

        protected abstract com.vungle.publisher.db.model.Ad.Factory<A, V, R> a();

        protected abstract com.vungle.publisher.db.model.AdPlay.Factory<T, P, E, A, V, R> b();

        protected abstract b c();

        protected /* bridge */ /* synthetic */ as a(as asVar, Cursor cursor) {
            return a((AdReport) asVar, cursor, false);
        }

        protected BaseFactory() {
        }

        protected final List<T> d_() {
            List<T> a = a("status = ? AND ad_id IN (SELECT id FROM ad WHERE type = ?)", new String[]{a.reportable.toString(), c().toString()}, "insert_timestamp_millis ASC");
            for (T a2 : a) {
                a((AdReport) a2, null, true);
            }
            return a;
        }

        public final T a(A a) {
            T b = b(a);
            if (b != null) {
                return b;
            }
            AdReport adReport = (AdReport) c_();
            adReport.a = a;
            adReport.g = a.open;
            Logger.d(Logger.DATABASE_TAG, "creating new " + adReport.y());
            adReport.u();
            return adReport;
        }

        public final T b(A a) {
            String[] strArr = new String[]{a.open.toString()};
            String str = "status = ?";
            if (a == null) {
                throw new IllegalArgumentException("null ad");
            }
            String d = a.d();
            if (d == null) {
                throw new IllegalArgumentException("null ad_id");
            }
            Object[] objArr = new String[2];
            objArr[0] = d;
            for (int i = 0; i <= 0; i++) {
                objArr[1] = strArr[0];
            }
            List a2 = a("ad_id = ? AND " + str, (String[]) objArr, "insert_timestamp_millis DESC");
            str = "ad_id = ? AND " + str + ", with params: " + cr.a(objArr);
            int size = a2.size();
            switch (size) {
                case Gender.MALE /*0*/:
                    return null;
                case Gender.FEMALE /*1*/:
                    T a3 = a((AdReport) a2.get(0), (Ad) a, false);
                    Logger.d(Logger.DATABASE_TAG, "fetched " + a3.y());
                    return a3;
                default:
                    Logger.w(Logger.DATABASE_TAG, size + " ad_report records for " + str);
                    return null;
            }
        }

        protected T a(T t, Cursor cursor, boolean z) {
            t.s = aq.d(cursor, ShareConstants.WEB_DIALOG_PARAM_ID);
            t.a(aq.f(cursor, "ad_id"));
            t.c = aq.f(cursor, "incentivized_publisher_app_user_id");
            t.e = aq.a(cursor, "is_incentivized").booleanValue();
            t.d = aq.e(cursor, "insert_timestamp_millis");
            t.f = aq.f(cursor, "placement");
            t.g = (a) aq.a(cursor, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, a.class);
            t.h = aq.e(cursor, "update_timestamp_millis");
            t.i = aq.d(cursor, "video_duration_millis");
            t.j = aq.e(cursor, "view_end_millis");
            t.k = aq.e(cursor, "view_start_millis");
            return t;
        }

        private T a(T t, A a, boolean z) {
            if (a == null) {
                t.a = (Ad) a().a((Object) t.d());
            } else {
                t.a = a;
            }
            if (z) {
                t.m = b().b(t);
                t.l = this.a.b((Integer) t.s);
            }
            return t;
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public DatabaseHelper a;
        @Inject
        com.vungle.publisher.db.model.LocalAdReport.Factory b;
        @Inject
        com.vungle.publisher.db.model.StreamingAdReport.Factory c;

        Factory() {
        }

        public final List<AdReport<?, ?, ?, ?, ?, ?>> a() {
            List<AdReport<?, ?, ?, ?, ?, ?>> arrayList = new ArrayList();
            for (LocalAdReport add : this.b.d_()) {
                arrayList.add(add);
            }
            for (StreamingAdReport add2 : this.c.d_()) {
                arrayList.add(add2);
            }
            return arrayList;
        }
    }

    /* compiled from: vungle */
    public enum a {
        open,
        failed,
        playing,
        reportable
    }

    protected abstract com.vungle.publisher.db.model.AdPlay.Factory<T, P, E, A, V, R> a();

    public final /* synthetic */ Object r() {
        return u();
    }

    protected AdReport() {
    }

    protected final String b() {
        return "ad_report";
    }

    protected final String d() {
        return this.a == null ? this.b : this.a.d();
    }

    protected final void a(String str) {
        this.b = str;
    }

    public final A e() {
        return this.a;
    }

    public final void a(Map<String, String> map) {
        Map map2;
        com.vungle.publisher.db.model.AdReportExtra.Factory factory = this.o;
        Integer num = (Integer) this.s;
        if (map != null) {
            Map hashMap = new HashMap();
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                AdReportExtra b = factory.b();
                b.a = num;
                b.b = str;
                b.c = str2;
                hashMap.put(str, b);
            }
            map2 = hashMap;
        } else {
            map2 = null;
        }
        this.l = map2;
        int size = map2 == null ? 0 : map2.size();
        if (size <= 0) {
            Logger.d(Logger.DATABASE_TAG, "no new extras for " + y());
            return;
        }
        Logger.d(Logger.DATABASE_TAG, size + " new extras for " + y());
        this.n = true;
        B();
    }

    public final Map<String, AdReportExtra> f() {
        Map<String, AdReportExtra> map = this.l;
        if (map != null) {
            return map;
        }
        map = this.o.b((Integer) this.s);
        this.l = map;
        return map;
    }

    public final boolean g() {
        return this.e;
    }

    public final void b(boolean z) {
        this.e = z;
    }

    public final String h() {
        return this.c;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final String i() {
        return this.f;
    }

    public final void a(a aVar) {
        Logger.d(Logger.REPORT_TAG, "setting ad_report status " + aVar + " for " + y());
        this.g = aVar;
    }

    public final Integer j() {
        return this.i;
    }

    public final void a(Integer num) {
        Logger.d(Logger.REPORT_TAG, "setting video duration millis " + num + " for " + y());
        this.i = num;
        m();
    }

    public final int k() {
        if (this.k == null) {
            Logger.w(Logger.DATABASE_TAG, "unable to calculate ad duration because view start millis was null");
            return -1;
        } else if (this.j != null) {
            return (int) (this.j.longValue() - this.k.longValue());
        } else {
            Logger.w(Logger.DATABASE_TAG, "unable to calculate ad duration because view end millis was null");
            return -1;
        }
    }

    public final void a(Long l) {
        Logger.d(Logger.REPORT_TAG, "setting ad end millis " + l + " for " + y());
        this.j = l;
    }

    public final void b(Long l) {
        a(l);
        m();
    }

    public final Long l() {
        return this.k;
    }

    public final void c(Long l) {
        Logger.d(Logger.REPORT_TAG, "setting ad start millis " + l + " for " + y());
        this.k = l;
    }

    public final P q() {
        List A = A();
        P a = a().a(this);
        a.r();
        A.add(a);
        return a;
    }

    public final P[] t() {
        List A = A();
        return (AdPlay[]) A.toArray(a().c(A.size()));
    }

    private List<P> A() {
        List<P> list = this.m;
        if (list != null) {
            return list;
        }
        list = a().b(this);
        this.m = list;
        return list;
    }

    public final Integer u() throws SQLException {
        Integer num = (Integer) super.r();
        B();
        return num;
    }

    private void B() {
        if (this.n) {
            Map map = this.l;
            if (this.s == null) {
                Logger.d(Logger.DATABASE_TAG, "delaying inserting extras for uninserted " + y());
                return;
            }
            Logger.d(Logger.DATABASE_TAG, "replacing extras for " + y());
            this.o.a((Integer) this.s);
            if (!(map == null || map.isEmpty())) {
                com.vungle.publisher.as.a.a((as[]) map.values().toArray(com.vungle.publisher.db.model.AdReportExtra.Factory.a(map.size())));
            }
            this.n = false;
            return;
        }
        Logger.v(Logger.DATABASE_TAG, "no new extras to insert for " + y());
    }

    protected ContentValues a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("ad_id", d());
            Long valueOf = Long.valueOf(currentTimeMillis);
            this.d = valueOf;
            contentValues.put("insert_timestamp_millis", valueOf);
        }
        contentValues.put("incentivized_publisher_app_user_id", this.c);
        contentValues.put("is_incentivized", Boolean.valueOf(this.e));
        contentValues.put("placement", this.f);
        contentValues.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, cr.a(this.g));
        Long valueOf2 = Long.valueOf(currentTimeMillis);
        this.h = valueOf2;
        contentValues.put("update_timestamp_millis", valueOf2);
        contentValues.put("video_duration_millis", this.i);
        contentValues.put("view_end_millis", this.j);
        contentValues.put("view_start_millis", this.k);
        return contentValues;
    }

    public StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "ad_id", d(), false);
        as.a(p, "insert_timestamp_millis", this.d, false);
        as.a(p, "incentivized_publisher_app_user_id", this.c, false);
        as.a(p, "is_incentivized", Boolean.valueOf(this.e), false);
        as.a(p, "placement", this.f, false);
        as.a(p, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, this.g, false);
        as.a(p, "update_timestamp_millis", this.h, false);
        as.a(p, "video_duration_millis", this.i, false);
        as.a(p, "view_end_millis", this.j, false);
        as.a(p, "view_start_millis", this.k, false);
        as.a(p, "plays", this.m == null ? "not fetched" : Integer.valueOf(this.m.size()), false);
        return p;
    }
}
