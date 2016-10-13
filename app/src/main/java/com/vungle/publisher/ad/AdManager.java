package com.vungle.publisher.ad;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import com.vungle.log.Logger;
import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.af;
import com.vungle.publisher.aj;
import com.vungle.publisher.an;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.bf;
import com.vungle.publisher.bk;
import com.vungle.publisher.c;
import com.vungle.publisher.ca;
import com.vungle.publisher.cb;
import com.vungle.publisher.ci;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.Ad.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAd.Factory;
import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.db.model.Viewable;
import com.vungle.publisher.e;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.inject.annotations.FullScreenAdActivityClass;
import com.vungle.publisher.m;
import com.vungle.publisher.n;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.ProtocolHttpGateway.AnonymousClass4;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import com.vungle.publisher.t;
import com.vungle.publisher.x;
import com.vungle.sdk.VunglePub.Gender;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdManager {
    @Inject
    public AdPreparer a;
    @Inject
    Context b;
    @Inject
    bf c;
    @Inject
    public EventBus d;
    @Inject
    @FullScreenAdActivityClass
    Class e;
    @Inject
    public ScheduledPriorityExecutor f;
    @Inject
    public Factory g;
    @Inject
    cb h;
    @Inject
    Lazy<PlayAdEventListener> i;
    @Inject
    public Lazy<AdAvailabilityEventListener> j;
    @Inject
    Provider<PrepareStreamingAdEventListener> k;
    @Inject
    ProtocolHttpGateway l;
    @Inject
    SdkConfig m;
    @Inject
    public StreamingAd.Factory n;
    @Inject
    public Viewable.Factory o;
    @Inject
    Lazy<SdkState> p;

    /* compiled from: vungle */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ c a;
        final /* synthetic */ AdManager b;

        public AnonymousClass1(AdManager adManager, c cVar) {
            this.b = adManager;
            this.a = cVar;
        }

        public final void run() {
            Throwable e;
            Ad ad = null;
            Object obj = null;
            Logger.d(Logger.AD_TAG, "AdManager.playAd()");
            try {
                AdManager adManager = this.b;
                c cVar = this.a;
                Ad a = adManager.a(false);
                Ad a2 = adManager.a(a == null ? null : a.d(), cVar);
                if (a2 != null) {
                    a = a2;
                }
                Logger.i(Logger.AD_TAG, "next ad " + (a == null ? null : a.y()));
                if (a == null) {
                    try {
                        Logger.d(Logger.AD_TAG, "no ad to play");
                        this.b.d.a(new aj());
                    } catch (Exception e2) {
                        e = e2;
                        ad = a;
                        try {
                            Logger.e(Logger.AD_TAG, "error launching ad", e);
                            this.b.d.a(new an(ad, false));
                        } finally {
                            this.b.l.c();
                        }
                    }
                } else {
                    ((PlayAdEventListener) this.b.i.get()).b();
                    Intent intent = new Intent(this.b.b, this.b.e);
                    intent.addFlags(805306368);
                    intent.putExtra(FullScreenAdActivity.AD_CONFIG_EXTRA_KEY, this.a);
                    intent.putExtra(FullScreenAdActivity.AD_ID_EXTRA_KEY, a.d());
                    this.b.b.startActivity(intent);
                    obj = 1;
                }
                if (obj == null) {
                    this.b.l.c();
                }
            } catch (Exception e3) {
                e = e3;
                Logger.e(Logger.AD_TAG, "error launching ad", e);
                this.b.d.a(new an(ad, false));
            }
        }
    }

    /* compiled from: vungle */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.aware.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.failed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.preparing.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.viewed.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[a.ready.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class AdAvailabilityEventListener extends bk {
        @Inject
        AdManager a;

        AdAvailabilityEventListener() {
        }

        public void onEvent(e eVar) {
            this.a.b(false);
        }
    }

    @Singleton
    /* compiled from: vungle */
    static class PlayAdEventListener extends bk {
        final String a = Logger.PREPARE_TAG;
        @Inject
        AdManager b;

        PlayAdEventListener() {
        }

        public void onEvent(x startPlayAdEvent) {
            try {
                startPlayAdEvent.a().b(a.viewed);
            } catch (Exception e) {
                Logger.e(Logger.PREPARE_TAG, "error processing start play ad event");
            }
        }

        public void onEvent(m mVar) {
            Logger.d(Logger.PREPARE_TAG, "sent ad report - unregistering play ad listener");
            this.b.a(false);
            d();
        }

        public void onEvent(af afVar) {
            Logger.d(Logger.PREPARE_TAG, "play ad failure - unregistering play ad listener");
            d();
        }
    }

    /* compiled from: vungle */
    static class PrepareStreamingAdEventListener extends bk {
        final String a = Logger.PREPARE_TAG;
        volatile boolean b;
        volatile StreamingAd c;
        final long d = System.currentTimeMillis();
        @Inject
        StreamingAd.Factory e;

        PrepareStreamingAdEventListener() {
        }

        final void a() {
            this.b = true;
            synchronized (this) {
                notifyAll();
            }
        }

        public void onEvent(n prepareStreamingAdFailureEvent) {
            d();
            Logger.d(Logger.PREPARE_TAG, "request streaming ad failure after " + (prepareStreamingAdFailureEvent.c - this.d) + " ms");
            a();
        }

        public void onEvent(t prepareStreamingAdSuccessEvent) {
            d();
            long j = prepareStreamingAdSuccessEvent.c - this.d;
            RequestStreamingAdResponse requestStreamingAdResponse = prepareStreamingAdSuccessEvent.a;
            if (Boolean.TRUE.equals(requestStreamingAdResponse.r)) {
                Logger.d(Logger.PREPARE_TAG, "received streaming ad " + requestStreamingAdResponse.f() + " after " + j + " ms");
                String f = requestStreamingAdResponse.f();
                StreamingAd streamingAd = (StreamingAd) this.e.a((Object) f);
                if (streamingAd == null) {
                    streamingAd = this.e.a(requestStreamingAdResponse);
                    this.c = streamingAd;
                    Logger.d(Logger.PREPARE_TAG, "inserting new " + streamingAd.y());
                    try {
                        streamingAd.l();
                    } catch (SQLException e) {
                        Logger.d(Logger.PREPARE_TAG, "did not insert streaming ad - possible duplicate");
                    }
                } else {
                    try {
                        this.e.a((Ad) streamingAd, (RequestAdResponse) requestStreamingAdResponse);
                    } catch (Throwable e2) {
                        Logger.w(Logger.PREPARE_TAG, "error updating ad " + f, e2);
                    }
                    a i = streamingAd.i();
                    switch (AnonymousClass3.a[i.ordinal()]) {
                        case Gender.FEMALE /*1*/:
                            Logger.w(Logger.PREPARE_TAG, "unexpected ad status " + i + " for " + streamingAd.y());
                            break;
                        case Logger.INFO_LOGGING_LEVEL /*4*/:
                        case Logger.WARN_LOGGING_LEVEL /*5*/:
                            break;
                        default:
                            Logger.w(Logger.PREPARE_TAG, "existing " + streamingAd.y() + " with status " + i + " - ignoring");
                            break;
                    }
                    Logger.d(Logger.PREPARE_TAG, "existing " + streamingAd.y() + " with status " + i);
                    if (i != a.ready) {
                        streamingAd.b(a.ready);
                    }
                    this.c = streamingAd;
                }
            } else {
                Logger.d(Logger.PREPARE_TAG, "no streaming ad to play after " + j + " ms");
            }
            a();
        }
    }

    public final boolean a() {
        SdkState sdkState = (SdkState) this.p.get();
        if (!sdkState.m.get() && sdkState.b()) {
            if ((this.g.e() != null ? 1 : null) != null) {
                return true;
            }
        }
        return false;
    }

    final LocalAd a(boolean z) {
        if (this.c.o()) {
            LocalAd a;
            if (z) {
                a = this.g.a(a.ready, a.preparing);
            } else {
                a = this.g.e();
            }
            if (a == null) {
                Logger.d(Logger.PREPARE_TAG, "no local ad available");
                this.l.a(new ci());
                return null;
            }
            a i = a.i();
            if (i != a.preparing) {
                if (i == a.ready) {
                    Logger.v(Logger.PREPARE_TAG, "local ad already available for " + a.d());
                }
                return a;
            } else if (z) {
                Logger.d(Logger.PREPARE_TAG, "local ad partially prepared, restarting preparation for " + a.d());
                this.a.a(a.d());
                return null;
            } else {
                Logger.i(Logger.PREPARE_TAG, "local ad partially prepared, but not restarting preparation for " + a.d());
                return null;
            }
        }
        Logger.w(Logger.PREPARE_TAG, "unable to fetch local ad -  no external storage available");
        return null;
    }

    final StreamingAd a(String str, c cVar) {
        Throwable th;
        StreamingAd streamingAd;
        Throwable th2;
        StreamingAd streamingAd2;
        StreamingAd streamingAd3 = null;
        boolean z = false;
        try {
            if (this.m.b) {
                ca a = this.h.a();
                z = this.m.c.contains(a);
                Logger.d(Logger.PREPARE_TAG, "ad streaming " + (z ? "enabled" : "disabled") + " for " + a + " connectivity");
            } else {
                Logger.d(Logger.PREPARE_TAG, "ad streaming disabled");
            }
            if (!z) {
                return null;
            }
            Logger.d(Logger.PREPARE_TAG, "requesting streaming ad");
            PrepareStreamingAdEventListener prepareStreamingAdEventListener = (PrepareStreamingAdEventListener) this.k.get();
            prepareStreamingAdEventListener.b();
            ProtocolHttpGateway protocolHttpGateway = this.l;
            protocolHttpGateway.d.a(new AnonymousClass4(protocolHttpGateway, str, cVar), b.requestStreamingAd);
            long j = prepareStreamingAdEventListener.d;
            int i = this.m.d;
            Logger.d(Logger.CONFIG_TAG, "streaming response timeout config " + i + " ms");
            long j2 = ((long) i) + j;
            synchronized (prepareStreamingAdEventListener) {
                while (!prepareStreamingAdEventListener.b) {
                    try {
                        long currentTimeMillis = j2 - System.currentTimeMillis();
                        if (currentTimeMillis > 0) {
                            try {
                                prepareStreamingAdEventListener.wait(currentTimeMillis);
                            } catch (InterruptedException e) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        streamingAd = null;
                        th2 = th;
                    }
                }
                j2 = System.currentTimeMillis() - j;
                if (prepareStreamingAdEventListener.b) {
                    streamingAd = prepareStreamingAdEventListener.c;
                    if (streamingAd != null) {
                        try {
                            Logger.d(Logger.PREPARE_TAG, "request streaming ad success after " + j2 + " ms " + streamingAd.y());
                            streamingAd3 = streamingAd;
                        } catch (Throwable th4) {
                            th2 = th4;
                            try {
                                throw th2;
                            } catch (Throwable e2) {
                                th2 = e2;
                                streamingAd2 = streamingAd;
                            }
                        }
                    } else {
                        streamingAd3 = streamingAd;
                    }
                } else {
                    Logger.d(Logger.PREPARE_TAG, "request streaming ad timeout after " + j2 + " ms");
                    prepareStreamingAdEventListener.a();
                }
                try {
                    return streamingAd3;
                } catch (Throwable th32) {
                    th = th32;
                    streamingAd = streamingAd3;
                    th2 = th;
                    throw th2;
                }
            }
        } catch (Throwable e22) {
            th = e22;
            streamingAd2 = null;
            th2 = th;
            Logger.w(Logger.PREPARE_TAG, "error getting streaming ad", th2);
            return streamingAd2;
        }
    }

    public final void b(boolean z) {
        a(z);
        this.f.a(b.deleteExpiredAds);
        Long f = this.g.f();
        if (f != null) {
            this.f.a(new Runnable(this) {
                final /* synthetic */ AdManager a;

                {
                    this.a = r1;
                }

                public final void run() {
                    this.a.g.g();
                }
            }, b.deleteExpiredAds, f.longValue() - System.currentTimeMillis());
        }
    }
}
