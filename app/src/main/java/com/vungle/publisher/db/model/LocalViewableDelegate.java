package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.internal.NativeProtocol;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.at.b;
import com.vungle.publisher.au;
import com.vungle.publisher.bf;
import com.vungle.publisher.bn;
import com.vungle.publisher.bo;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.net.http.DownloadHttpGateway;
import com.vungle.publisher.net.http.DownloadHttpGateway.AnonymousClass1;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class LocalViewableDelegate {
    a a;
    b b;
    public String c;
    Integer d;
    @Inject
    DownloadHttpGateway e;
    @Inject
    bf f;
    @Inject
    SdkState g;

    /* compiled from: vungle */
    interface a extends au {
        boolean A();

        boolean t();
    }

    @Singleton
    /* compiled from: vungle */
    static class Factory {
        @Inject
        Provider<LocalViewableDelegate> a;

        Factory() {
        }

        final LocalViewableDelegate a(a aVar) {
            LocalViewableDelegate localViewableDelegate = (LocalViewableDelegate) this.a.get();
            localViewableDelegate.a = aVar;
            return localViewableDelegate;
        }
    }

    LocalViewableDelegate() {
    }

    private String j() {
        return this.a.d();
    }

    final String a() {
        return ((LocalAd) this.a.c()).t();
    }

    final File b() {
        String c = c();
        return c == null ? null : new File(c);
    }

    final String c() {
        return bo.a(a(), k() + "." + this.a.h());
    }

    private b k() {
        return this.a.f();
    }

    final int d() {
        this.a.B();
        return this.a.C();
    }

    final void e() {
        Logger.d(Logger.PREPARE_TAG, "downloading " + this.b + " for ad_id " + j());
        this.a.a(com.vungle.publisher.at.a.downloading);
        DownloadHttpGateway downloadHttpGateway = this.e;
        downloadHttpGateway.d.a(new AnonymousClass1(downloadHttpGateway, this.a), ScheduledPriorityExecutor.b.downloadLocalAd);
    }

    final boolean f() {
        boolean t = this.a.t();
        if (t) {
            com.vungle.publisher.at.a aVar = com.vungle.publisher.at.a.ready;
            Logger.i(Logger.PREPARE_TAG, k() + " " + aVar + " for ad_id " + j());
            this.a.a(aVar);
        } else {
            if (SdkState.a()) {
                Logger.i(Logger.PREPARE_TAG, "debug mode: post-processing failed for " + this.a.y() + " - not deleting " + c());
            } else {
                Logger.d(Logger.PREPARE_TAG, "post-processing failed for " + this.a.y() + " - deleting " + c());
                this.a.B();
            }
            this.a.a(com.vungle.publisher.at.a.aware);
        }
        return t;
    }

    final boolean g() throws bn {
        com.vungle.publisher.at.a aVar;
        boolean A = this.a.A();
        String j = j();
        b k = k();
        if (A) {
            Logger.i(Logger.PREPARE_TAG, k + " verified for ad_id " + j);
            aVar = com.vungle.publisher.at.a.ready;
        } else {
            Logger.w(Logger.PREPARE_TAG, k + " failed verification; reprocessing ad_id " + j);
            aVar = com.vungle.publisher.at.a.aware;
        }
        this.a.a(aVar);
        return A;
    }

    final boolean h() throws bn {
        if (this.f.o()) {
            String j = j();
            b k = k();
            if (this.d == null) {
                Logger.d(Logger.PREPARE_TAG, k + " size " + this.d + " for ad_id: " + j);
                return true;
            }
            File b = b();
            int length = b == null ? 0 : (int) b.length();
            if (length == this.d.intValue()) {
                Logger.d(Logger.PREPARE_TAG, k + " disk size matched size " + this.d + " for ad_id: " + j);
                return true;
            }
            boolean z;
            Logger.d(Logger.PREPARE_TAG, k + " disk size " + length + " failed to match size " + this.d + " for ad_id: " + j);
            b = b();
            if (b == null) {
                Logger.w(Logger.PREPARE_TAG, "null " + this.b + " file for ad " + j());
                z = false;
            } else if (b.exists()) {
                Logger.v(Logger.PREPARE_TAG, b.getAbsolutePath() + " exists, " + b.length() + " bytes");
                z = true;
            } else {
                Logger.w(Logger.PREPARE_TAG, b.getAbsolutePath() + " missing ");
                z = false;
            }
            if (!z) {
                return false;
            }
            Logger.d(Logger.PREPARE_TAG, "ignoring " + k + " size mismatch - file exists");
            return true;
        }
        throw new bn();
    }

    final boolean i() {
        File b = b();
        Logger.d(Logger.PREPARE_TAG, "deleting " + b);
        return b != null && b.delete();
    }

    final void a(ContentValues contentValues) {
        contentValues.put(NativeProtocol.WEB_DIALOG_URL, this.c);
        contentValues.put("size", this.d);
    }

    final void a(Cursor cursor) {
        this.c = aq.f(cursor, NativeProtocol.WEB_DIALOG_URL);
        this.d = aq.d(cursor, "size");
    }

    final void a(StringBuilder stringBuilder) {
        as.a(stringBuilder, NativeProtocol.WEB_DIALOG_URL, this.c);
        as.a(stringBuilder, "size", this.d);
    }
}
