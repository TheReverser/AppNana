package com.vungle.publisher.ad.prepare;

import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.au;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LocalArchive;
import com.vungle.publisher.db.model.LocalVideo;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.q;
import com.vungle.publisher.reporting.AdReportManager;
import com.vungle.publisher.s;
import com.vungle.publisher.z;
import com.vungle.sdk.VunglePub.Gender;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class PrepareAdRunnable implements Runnable {
    private static final Object d = new Object();
    @Inject
    EventBus a;
    @Inject
    AdManager b;
    @Inject
    AdReportManager c;
    private String e;
    private com.vungle.publisher.at.b f;
    private Integer g;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[com.vungle.publisher.db.model.Ad.a.values().length];
        static final /* synthetic */ int[] b = new int[com.vungle.publisher.at.b.values().length];
        static final /* synthetic */ int[] c = new int[com.vungle.publisher.at.a.values().length];

        static {
            try {
                c[com.vungle.publisher.at.a.aware.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[com.vungle.publisher.at.a.downloading.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                c[com.vungle.publisher.at.a.downloaded.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                c[com.vungle.publisher.at.a.ready.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[com.vungle.publisher.at.b.preRoll.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[com.vungle.publisher.at.b.postRoll.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.deleting.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.invalid.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.ready.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.failed.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.aware.ordinal()] = 5;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.preparing.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.viewed.ordinal()] = 7;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        Provider<PrepareAdRunnable> a;

        public final PrepareAdRunnable a(String str, com.vungle.publisher.at.b bVar, Integer num) {
            PrepareAdRunnable prepareAdRunnable = (PrepareAdRunnable) this.a.get();
            prepareAdRunnable.e = str;
            prepareAdRunnable.f = bVar;
            prepareAdRunnable.g = num;
            return prepareAdRunnable;
        }
    }

    /* compiled from: vungle */
    static class a extends RuntimeException {
        a(String str) {
            super(str);
        }

        a(String str, Throwable th) {
            super(str, th);
        }
    }

    /* compiled from: vungle */
    static class b extends RuntimeException {
        b(String str) {
            super(str);
        }
    }

    public void run() {
        try {
            synchronized (d) {
                if (a().i() == com.vungle.publisher.db.model.Ad.a.ready) {
                    this.a.a(new z());
                }
            }
        } catch (a e) {
            Logger.w(Logger.PREPARE_TAG, e.getMessage() + " for ad.id " + this.e);
            this.a.a(new s());
        } catch (Throwable e2) {
            Logger.e(Logger.PREPARE_TAG, "error processing ad.id: " + this.e, e2);
            this.a.a(new s());
        }
    }

    private LocalAd a() {
        String str = this.e;
        LocalAd a = this.b.g.a(str);
        if (a == null) {
            throw new IllegalArgumentException("no ad " + str);
        }
        com.vungle.publisher.db.model.Ad.a i = a.i();
        LocalAd localAd = null;
        switch (AnonymousClass1.a[i.ordinal()]) {
            case Gender.FEMALE /*1*/:
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                throw new a("ad status: " + i);
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                Logger.d(Logger.PREPARE_TAG, "ad already " + com.vungle.publisher.db.model.Ad.a.ready + ": " + str);
                return a;
            default:
                try {
                    localAd = a(a);
                    return localAd;
                } finally {
                    try {
                        a.m();
                        if (localAd != null) {
                            this.a.a(new q());
                        }
                    } catch (Exception e) {
                        Logger.e(Logger.PREPARE_TAG, "error saving ad " + a.d() + " to database");
                    }
                }
        }
    }

    private LocalAd a(LocalAd localAd) {
        com.vungle.publisher.at.b bVar = this.f;
        com.vungle.publisher.at.b bVar2 = this.f;
        Integer num = this.g;
        if (bVar2 != null) {
            au b = localAd.b(bVar2);
            switch (AnonymousClass1.b[bVar2.ordinal()]) {
                case Gender.FEMALE /*1*/:
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    b.a(num);
                    break;
            }
            Logger.i(Logger.PREPARE_TAG, bVar2 + " downloaded for ad " + this.e);
            b.a(com.vungle.publisher.at.a.downloaded);
        }
        String d = localAd.d();
        com.vungle.publisher.db.model.Ad.a i = localAd.i();
        if (i == com.vungle.publisher.db.model.Ad.a.failed) {
            com.vungle.publisher.db.model.Ad.a aVar = com.vungle.publisher.db.model.Ad.a.preparing;
            long currentTimeMillis = System.currentTimeMillis();
            long j = localAd.j();
            if (currentTimeMillis < j) {
                Logger.d(Logger.PREPARE_TAG, "clock change detected; updating ad.id " + d + " status from " + i + " to " + aVar);
                localAd.a(aVar);
            } else {
                currentTimeMillis = (currentTimeMillis - j) / 60000;
                if (currentTimeMillis >= 1440) {
                    Logger.d(Logger.PREPARE_TAG, "retrying " + com.vungle.publisher.db.model.Ad.a.failed + " ad.id " + d + " after " + currentTimeMillis + "/1440 minutes; updating status from " + i + " to " + aVar);
                    localAd.a(aVar);
                } else {
                    throw new a("ad marked failed " + currentTimeMillis + " minutes ago");
                }
            }
        }
        com.vungle.publisher.db.model.Ad.a aVar2 = com.vungle.publisher.db.model.Ad.a.failed;
        i = localAd.i();
        while (true) {
            int i2 = localAd.y;
            if (i2 < 3) {
                try {
                    switch (AnonymousClass1.a[i.ordinal()]) {
                        case Logger.WARN_LOGGING_LEVEL /*5*/:
                            this.c.a(localAd);
                            localAd.a(com.vungle.publisher.db.model.Ad.a.preparing);
                            break;
                        case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                            break;
                        case HapticPlaybackThread.PAUSE_AV_FOR_HAPTIC_BUFFERING /*7*/:
                            c(localAd);
                            break;
                        default:
                            throw new IllegalStateException("unexpected ad.status: " + i);
                    }
                    Logger.d(Logger.PREPARE_TAG, (bVar == null ? "download " : "prepare " + bVar + " ") + "prepare_retry_count " + i2 + " for ad " + this.e);
                    b(localAd);
                    return localAd;
                } catch (b e) {
                    Logger.w(Logger.PREPARE_TAG, e.getMessage() + " for ad.id: " + this.e);
                    localAd.y++;
                }
            } else {
                localAd.a(com.vungle.publisher.db.model.Ad.a.failed);
                throw new a("failed to prepare ad after " + i2 + " attempts");
            }
        }
    }

    private void b(LocalAd localAd) {
        Object obj = 1;
        d(localAd);
        for (au a : localAd.B()) {
            if (!a(a)) {
                obj = null;
            }
        }
        if (obj != null) {
            Logger.i(Logger.PREPARE_TAG, "ad ready " + localAd.d());
            localAd.a(com.vungle.publisher.db.model.Ad.a.ready);
            ((LocalAdReport) this.c.c.b(localAd)).d(Long.valueOf(System.currentTimeMillis()));
            return;
        }
        Logger.d(Logger.PREPARE_TAG, "ad not ready " + localAd.d());
    }

    private void c(LocalAd localAd) {
        String d = localAd.d();
        Logger.d(Logger.PREPARE_TAG, "re-verify prepare_retry_count " + localAd.y + " for ad " + d);
        d(localAd);
        au[] B = localAd.B();
        int length = B.length;
        int i = 0;
        while (i < length) {
            au auVar = B[i];
            if (auVar.u()) {
                i++;
            } else {
                throw new b(auVar.f() + " re-verification failed for ad_id " + auVar.d());
            }
        }
        com.vungle.publisher.db.model.Ad.a aVar = com.vungle.publisher.db.model.Ad.a.ready;
        Logger.i(Logger.PREPARE_TAG, "re-verified ad and set to " + aVar + ": " + d);
        this.c.a(localAd).d(Long.valueOf(-1));
        localAd.a(aVar);
    }

    private static void d(LocalAd localAd) throws a {
        Object obj;
        Object obj2 = null;
        LocalArchive A = localAd.A();
        LocalVideo localVideo = (LocalVideo) localAd.k();
        LocalArchive u = localAd.u();
        if (A != null) {
            obj = 1;
        } else {
            obj = null;
        }
        Object obj3 = localVideo != null ? 1 : null;
        Object obj4 = u != null ? 1 : null;
        if (!(obj == null && obj3 == null && obj4 == null)) {
            obj2 = 1;
        }
        String y = localAd.y();
        if (obj2 != null) {
            if (obj != null) {
                Logger.v(Logger.PREPARE_TAG, y + " has " + com.vungle.publisher.at.b.preRoll + ": " + A.h.c);
            }
            if (obj3 != null) {
                Logger.v(Logger.PREPARE_TAG, y + " has " + com.vungle.publisher.at.b.localVideo + ": " + localVideo.b.c);
            }
            if (obj4 != null) {
                Logger.v(Logger.PREPARE_TAG, y + " has " + com.vungle.publisher.at.b.postRoll + ": " + u.h.c);
            }
        } else {
            localAd.a(com.vungle.publisher.db.model.Ad.a.invalid);
        }
        if (obj2 == null) {
            throw new a("invalid ad - no viewables");
        }
    }

    private boolean a(au auVar) {
        com.vungle.publisher.at.b f = auVar.f();
        com.vungle.publisher.at.a e = auVar.e();
        switch (AnonymousClass1.c[e.ordinal()]) {
            case Gender.FEMALE /*1*/:
                try {
                    auVar.l();
                    return false;
                } catch (Throwable e2) {
                    throw new a("external storage not available, could not download ad", e2);
                }
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                Logger.d(Logger.PREPARE_TAG, f + " still downloading for ad_id " + this.e);
                return false;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                try {
                    if (auVar.q()) {
                        return true;
                    }
                    throw new b(auVar.f() + " post processing failed for ad_id " + auVar.d());
                } catch (Throwable e22) {
                    throw new a("external storage not available, could not post process ad", e22);
                }
            case Logger.INFO_LOGGING_LEVEL /*4*/:
                Logger.v(Logger.PREPARE_TAG, f + " already " + e + " for ad_id " + this.e);
                return true;
            default:
                throw new IllegalStateException("unexpected " + f + " status: " + e);
        }
    }
}
