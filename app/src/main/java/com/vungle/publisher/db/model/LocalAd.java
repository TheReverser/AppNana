package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.facebook.appevents.AppEventsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.at;
import com.vungle.publisher.at.b;
import com.vungle.publisher.au;
import com.vungle.publisher.bn;
import com.vungle.publisher.bo;
import com.vungle.publisher.cn;
import com.vungle.publisher.cr;
import com.vungle.publisher.db.model.Ad.a;
import com.vungle.publisher.inject.annotations.AdTempDirectory;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import com.vungle.sdk.VunglePub.Gender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class LocalAd extends Ad<LocalAd, LocalVideo, RequestLocalAdResponse> {
    long A;
    boolean B;
    boolean C;
    @Inject
    Factory D;
    int u;
    Long v;
    String w;
    LocalArchive x;
    public int y;
    LocalArchive z;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.preRoll.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.localVideo.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.postRoll.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.db.model.Ad.Factory<LocalAd, LocalVideo, RequestLocalAdResponse> {
        @Inject
        @AdTempDirectory
        Provider<String> d;
        @Inject
        Factory e;
        @Inject
        com.vungle.publisher.db.model.Viewable.Factory f;
        @Inject
        Provider<LocalAd> g;
        @Inject
        com.vungle.publisher.db.model.LocalVideo.Factory h;
        @Inject
        ScheduledPriorityExecutor i;

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Video.Factory b_() {
            return this.h;
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new LocalAd[i];
        }

        protected final /* synthetic */ as c_() {
            return i();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        Factory() {
        }

        public final LocalAd a(RequestLocalAdResponse requestLocalAdResponse) throws bn {
            LocalAd localAd = (LocalAd) super.a((RequestAdResponse) requestLocalAdResponse);
            localAd.v = requestLocalAdResponse.s;
            localAd.a((String) this.d.get());
            localAd.z = this.e.a(localAd, requestLocalAdResponse, b.preRoll);
            localAd.x = this.e.a(localAd, requestLocalAdResponse, b.postRoll);
            localAd.a(a.aware);
            return localAd;
        }

        public final int a(LocalAd localAd, RequestLocalAdResponse requestLocalAdResponse) {
            localAd.v = requestLocalAdResponse.s;
            return super.a((Ad) localAd, (RequestAdResponse) requestLocalAdResponse);
        }

        protected final Ad.b b() {
            return Ad.b.local;
        }

        public final LocalAd a(String str) {
            return (LocalAd) super.a(Ad.b.local, str);
        }

        public final LocalAd e() {
            return a(a.ready);
        }

        public final LocalAd a(a... aVarArr) {
            int i;
            Throwable th;
            LocalAd localAd = null;
            String[] strArr = new String[aVarArr.length];
            for (i = 0; i < aVarArr.length; i++) {
                strArr[i] = String.valueOf(aVarArr[i]);
            }
            Cursor query;
            try {
                String str = "status IN (" + aq.a(strArr.length) + ") AND type = ? AND expiration_timestamp_seconds > ? ORDER BY received_timestamp_millis ASC LIMIT ?";
                String[] strArr2 = new String[]{Ad.b.local.toString(), Long.toString(System.currentTimeMillis() / 1000), AppEventsConstants.EVENT_PARAM_VALUE_YES};
                Object[] objArr = (String[]) cn.a(strArr, strArr2);
                String str2 = Ad.b.local.toString() + " ad records by query: " + str + "; parameters: " + cr.a(objArr);
                Logger.d(Logger.DATABASE_TAG, "fetching " + str2);
                query = this.c.getReadableDatabase().query("ad", null, str, objArr, null, null, null, null);
                try {
                    i = query.getCount();
                    switch (i) {
                        case Gender.MALE /*0*/:
                            Logger.d(Logger.DATABASE_TAG, "no " + str2);
                            break;
                        case Gender.FEMALE /*1*/:
                            if (query.moveToFirst()) {
                                LocalAd a = a(i(), query, true);
                                if (a.C()) {
                                    localAd = a;
                                    break;
                                }
                            }
                            break;
                        default:
                            throw new SQLException("fetched " + i + str2);
                    }
                    if (query != null) {
                        query.close();
                    }
                    return localAd;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        public final int a(List<LocalAd> list) {
            if (list == null || list.size() <= 0) {
                return 0;
            }
            if (Logger.isLoggable(3)) {
                StringBuilder stringBuilder = new StringBuilder("deleting ");
                int i = 1;
                for (LocalAd localAd : list) {
                    if (i != 0) {
                        i = 0;
                    } else {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(localAd.y());
                }
                Logger.d(Logger.DATABASE_TAG, stringBuilder.toString());
            }
            a(b((List) list), a.deleting);
            return c();
        }

        private static List<LocalAd> b(List<LocalAd> list) {
            List<LocalAd> arrayList = new ArrayList();
            for (LocalAd localAd : list) {
                int i = localAd.u;
                localAd.u = i + 1;
                if (localAd.D() || i >= 3) {
                    arrayList.add(localAd);
                } else {
                    Logger.w(Logger.DATABASE_TAG, "unable to delete files for " + localAd.y() + " attempt " + i);
                    localAd.m();
                }
            }
            return arrayList;
        }

        public final Long f() {
            Throwable th;
            Long l = null;
            Object asList = Arrays.asList(new String[]{a.viewed.toString(), a.deleting.toString()});
            List arrayList = new ArrayList();
            arrayList.add(Ad.b.local.toString());
            arrayList.addAll(asList);
            arrayList.add(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            Cursor query;
            try {
                query = this.c.getReadableDatabase().query("ad", new String[]{"expiration_timestamp_seconds"}, "type = ? AND status NOT IN (" + aq.a(asList.size()) + ") ORDER BY expiration_timestamp_seconds ASC LIMIT ?", (String[]) arrayList.toArray(new String[arrayList.size()]), null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        Long e = aq.e(query, "expiration_timestamp_seconds");
                        if (e == null) {
                            Logger.w(Logger.DATABASE_TAG, "next ad expiration time seconds is null");
                        } else {
                            l = Long.valueOf(e.longValue() * 1000);
                            Logger.d(Logger.DATABASE_TAG, "next ad expiration time millis " + l);
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                    return l;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        public final int g() {
            Logger.d(Logger.DATABASE_TAG, "deleting expired " + Ad.b.local + " ad records without pending reports");
            return a(a("type = ? AND expiration_timestamp_seconds <= ?", new String[]{Ad.b.local.toString(), String.valueOf(System.currentTimeMillis() / 1000)}, null));
        }

        public final int h() {
            List a = a("type = ? AND " + Ad.b + " ORDER BY insert_timestamp_millis DESC LIMIT ? OFFSET ?", new String[]{Ad.b.local.toString(), Integer.toString(Integer.MAX_VALUE), Integer.toString(4)}, null);
            Logger.d(Logger.DATABASE_TAG, "deleting " + a.size() + " extra " + Ad.b.local + " ad records to reach target size 4");
            return a(a);
        }

        private LocalAd a(LocalAd localAd, Cursor cursor, boolean z) {
            super.a(localAd, cursor, z);
            localAd.u = aq.c(cursor, "delete_local_content_attempts");
            localAd.v = aq.e(cursor, "expiration_timestamp_seconds");
            localAd.a(aq.f(cursor, "parent_path"));
            localAd.y = aq.c(cursor, "prepare_retry_count");
            localAd.A = System.currentTimeMillis();
            if (z) {
                a(localAd, z);
                b(localAd, z);
            }
            return localAd;
        }

        final LocalArchive a(LocalAd localAd, boolean z) {
            if (localAd.B) {
                return localAd.x;
            }
            LocalArchive localArchive = (LocalArchive) this.e.a((String) localAd.s, b.postRoll, z);
            localAd.x = localArchive;
            localAd.B = true;
            return localArchive;
        }

        final LocalArchive b(LocalAd localAd, boolean z) {
            if (localAd.C) {
                return localAd.z;
            }
            LocalArchive localArchive = (LocalArchive) this.e.a((String) localAd.s, b.preRoll, z);
            localAd.z = localArchive;
            localAd.C = true;
            return localArchive;
        }

        protected final String e_() {
            return "ad";
        }

        private LocalAd i() {
            return (LocalAd) this.g.get();
        }
    }

    public final /* synthetic */ at a(b bVar) {
        return b(bVar);
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory a() {
        return this.D;
    }

    protected final /* bridge */ /* synthetic */ as.a a_() {
        return this.D;
    }

    public final /* synthetic */ Object r() {
        return l();
    }

    protected LocalAd() {
    }

    public final void a(String str) {
        this.w = str;
        this.n = null;
    }

    public final String t() {
        if (this.n == null) {
            this.n = bo.a(this.w, bo.c((String) this.s));
        }
        return this.n;
    }

    public final LocalArchive u() {
        return this.D.a(this, false);
    }

    public final LocalArchive A() {
        return this.D.b(this, false);
    }

    public final void a(a aVar) {
        a aVar2 = this.j;
        super.a(aVar);
        if (aVar != aVar2 && aVar != a.failed) {
            Logger.v(Logger.PREPARE_TAG, "resetting prepare_retry_count from " + this.y + " to 0 for " + y());
            this.y = 0;
        }
    }

    public final au[] B() {
        List arrayList = new ArrayList();
        LocalArchive A = A();
        if (A != null) {
            arrayList.add(A);
        }
        LocalVideo localVideo = (LocalVideo) k();
        if (localVideo != null) {
            arrayList.add(localVideo);
        }
        A = u();
        if (A != null) {
            arrayList.add(A);
        }
        return (au[]) arrayList.toArray(new au[arrayList.size()]);
    }

    public final au b(b bVar) {
        switch (AnonymousClass1.a[bVar.ordinal()]) {
            case Gender.FEMALE /*1*/:
                return A();
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                return (au) k();
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                return u();
            default:
                return (au) super.a(bVar);
        }
    }

    final boolean C() {
        int i = 0;
        boolean z = true;
        for (au u : B()) {
            if (!u.u()) {
                z = false;
                break;
            }
        }
        if (!z) {
            a(a.aware);
            au[] B = B();
            int length = B.length;
            while (i < length) {
                B[i].a(at.a.aware);
                i++;
            }
            m();
        }
        return z;
    }

    public final boolean D() {
        return bo.a(t());
    }

    public final String l() throws SQLException {
        String l = super.l();
        if (this.x != null) {
            this.x.r();
        }
        if (this.z != null) {
            this.z.r();
        }
        return l;
    }

    public final int m() {
        int m = super.m();
        if (m == 1) {
            if (this.z != null) {
                this.z.m();
            }
            if (this.x != null) {
                this.x.m();
            }
        }
        return m;
    }

    public final int n() {
        int i = this.u;
        this.u = i + 1;
        if (bo.a(t())) {
            return super.n();
        }
        Logger.w(Logger.DATABASE_TAG, "unable to delete files for " + y() + " attempt " + i);
        m();
        return 0;
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put("delete_local_content_attempts", Integer.valueOf(this.u));
        a.put("expiration_timestamp_seconds", this.v);
        a.put("parent_path", this.w);
        a.put("prepare_retry_count", Integer.valueOf(this.y));
        a.put("received_timestamp_millis", Long.valueOf(this.A));
        return a;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "delete_local_content_attempts", Integer.valueOf(this.u), false);
        as.a(p, "expiration_timestamp_seconds", this.v, false);
        as.a(p, "parent_path", this.w, false);
        as.a(p, "prepare_retry_count", Integer.valueOf(this.y), false);
        as.a(p, "received_timestamp_millis", Long.valueOf(this.A), false);
        return p;
    }
}
