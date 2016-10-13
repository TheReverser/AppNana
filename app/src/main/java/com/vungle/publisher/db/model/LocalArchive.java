package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import com.vungle.publisher.at.b;
import com.vungle.publisher.bo;
import com.vungle.publisher.br;
import com.vungle.publisher.db.model.Viewable.BaseFactory;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import com.vungle.sdk.VunglePub.Gender;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class LocalArchive extends Viewable<LocalAd, LocalVideo, RequestLocalAdResponse> implements a {
    ArchiveEntry[] a;
    boolean b;
    boolean c;
    boolean d;
    @Inject
    Factory e;
    @Inject
    com.vungle.publisher.db.model.ArchiveEntry.Factory f;
    @Inject
    com.vungle.publisher.db.model.LocalAd.Factory g;
    @Inject
    public LocalViewableDelegate h;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.postRoll.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.preRoll.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    static class Factory extends BaseFactory<LocalAd, LocalArchive, LocalVideo, RequestLocalAdResponse> {
        @Inject
        Provider<LocalArchive> a;
        @Inject
        Factory b;

        protected final /* bridge */ /* synthetic */ as a(as asVar, Cursor cursor) {
            return a((LocalArchive) asVar, cursor, false);
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new LocalArchive[i];
        }

        protected final /* synthetic */ as c_() {
            LocalArchive localArchive = (LocalArchive) this.a.get();
            localArchive.h = this.b.a(localArchive);
            return localArchive;
        }

        protected Factory() {
        }

        final LocalArchive a(LocalAd localAd, RequestLocalAdResponse requestLocalAdResponse, b bVar) {
            if (requestLocalAdResponse == null) {
                return null;
            }
            String str;
            LocalArchive localArchive;
            switch (AnonymousClass2.a[bVar.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    str = requestLocalAdResponse.t;
                    if (str == null) {
                        return null;
                    }
                    localArchive = (LocalArchive) super.b(localAd, requestLocalAdResponse);
                    localArchive.q = bVar;
                    localArchive.a(str);
                    return localArchive;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    str = requestLocalAdResponse.u;
                    if (str == null) {
                        return null;
                    }
                    localArchive = (LocalArchive) super.b(localAd, requestLocalAdResponse);
                    localArchive.q = bVar;
                    localArchive.a(str);
                    return localArchive;
                default:
                    throw new IllegalArgumentException("cannot create archive of type: " + bVar);
            }
        }

        private LocalArchive a(LocalArchive localArchive, Cursor cursor, boolean z) {
            super.a((Viewable) localArchive, cursor, z);
            localArchive.h.a(cursor);
            if (z) {
                localArchive.i();
            }
            return localArchive;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory D() {
        return this.g;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.e;
    }

    protected LocalArchive() {
    }

    public final String g() {
        return this.h.c;
    }

    public final void a(String str) {
        this.h.c = str;
    }

    public final void a(Integer num) {
        this.h.d = num;
    }

    public final String h() {
        return "zip";
    }

    public final ArchiveEntry[] i() {
        if (!this.b) {
            a(this.f.a(this), false);
        }
        return this.a;
    }

    private void a(ArchiveEntry[] archiveEntryArr, boolean z) {
        this.a = archiveEntryArr;
        this.c = z;
        this.b = true;
    }

    public final String j() {
        return bo.a(this.h.a(), this.q);
    }

    public final String k() {
        return this.h.c();
    }

    public final void l() {
        this.h.e();
    }

    public final boolean q() {
        return this.h.f();
    }

    public final boolean t() {
        if (this.h.h() && F()) {
            return A();
        }
        return false;
    }

    private boolean F() {
        File b = this.h.b();
        try {
            final List arrayList = new ArrayList();
            br.a(b, new File(j()), new br.a(this) {
                final /* synthetic */ LocalArchive b;

                public final void a(File file, long j) {
                    Logger.v(Logger.PREPARE_TAG, "extracted " + file + ": " + j + " bytes");
                    List list = arrayList;
                    com.vungle.publisher.db.model.ArchiveEntry.Factory factory = this.b.f;
                    LocalArchive localArchive = this.b;
                    int i = (int) j;
                    ArchiveEntry b = factory.b();
                    b.a = localArchive;
                    b.b = file.getName();
                    b.c = Integer.valueOf(i);
                    list.add(b);
                }
            });
            a((ArchiveEntry[]) arrayList.toArray(new ArchiveEntry[arrayList.size()]), true);
            return true;
        } catch (Throwable e) {
            Logger.w(Logger.PREPARE_TAG, "error extracting " + b, e);
            return false;
        }
    }

    public final boolean u() {
        return this.h.g();
    }

    public final boolean A() {
        for (ArchiveEntry archiveEntry : i()) {
            boolean z;
            String a = bo.a(archiveEntry.a.j(), archiveEntry.b);
            File file = a == null ? null : new File(a);
            if (archiveEntry.c == null) {
                Logger.w(Logger.PREPARE_TAG, file + " size is null");
                z = false;
            } else {
                int length = (int) file.length();
                int intValue = archiveEntry.c.intValue();
                boolean z2 = length == intValue;
                if (z2) {
                    Logger.v(Logger.PREPARE_TAG, file + " size verified " + length);
                    z = z2;
                } else {
                    Logger.d(Logger.PREPARE_TAG, file + " size " + length + " doesn't match expected " + intValue);
                    z = file.exists();
                }
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final int n() {
        G();
        return this.h.d();
    }

    public final boolean B() {
        return this.h.i() & G();
    }

    private boolean G() {
        String j = j();
        Logger.d(Logger.DATABASE_TAG, "deleting " + this.q + " directory " + j);
        boolean a = bo.a(j());
        if (a) {
            Logger.v(Logger.DATABASE_TAG, "deleting " + this.q + " directory " + j);
            this.a = null;
            this.d = true;
        } else {
            Logger.w(Logger.DATABASE_TAG, "failed to delete " + this.q + " directory " + j);
        }
        return a;
    }

    public final int C() {
        return super.n();
    }

    public final int m() {
        int m = super.m();
        if (m == 1) {
            if (this.d) {
                this.f.a((Integer) this.s);
                G();
                Logger.v(Logger.DATABASE_TAG, "resetting areArchiveEntriesDeleted = false");
                this.d = false;
            } else if (this.c) {
                a.a(this.a);
                Logger.v(Logger.DATABASE_TAG, "resetting areArchiveEntriesNew = false");
                this.c = false;
            }
        }
        return m;
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.h.a(a);
        return a;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        this.h.a(p);
        return p;
    }
}
