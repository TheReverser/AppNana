package com.vungle.publisher.protocol;

import android.os.SystemClock;
import com.appnana.android.giftcardrewards.model.Settings;
import com.vungle.log.Logger;
import com.vungle.publisher.SafeBundleAdConfigFactory;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.bk;
import com.vungle.publisher.c;
import com.vungle.publisher.cd;
import com.vungle.publisher.ci;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.inject.annotations.RequestConfigHttpTransaction;
import com.vungle.publisher.inject.annotations.TrackInstallHttpTransaction;
import com.vungle.publisher.n;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.p;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import com.vungle.publisher.v;
import com.vungle.publisher.z;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ProtocolHttpGateway extends cd {
    @Inject
    EventBus a;
    @Inject
    PrepareLocalAdEventListener b;
    @Inject
    ReportAdHttpTransactionFactory e;
    @RequestConfigHttpTransaction
    @Inject
    HttpTransaction f;
    @Inject
    RequestLocalAdHttpTransactionFactory g;
    @Inject
    RequestStreamingAdHttpTransactionFactory h;
    @Inject
    SafeBundleAdConfigFactory i;
    @Inject
    SessionEndHttpTransactionFactory j;
    @Inject
    SessionStartHttpTransactionFactory k;
    @Inject
    @TrackInstallHttpTransaction
    Provider<HttpTransaction> l;
    @Inject
    UnfilledAdHttpTransactionFactory m;
    @Inject
    AdServiceReportingHandler n;
    private final AtomicBoolean o = new AtomicBoolean();

    /* compiled from: vungle */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ AdReport a;
        final /* synthetic */ ProtocolHttpGateway b;

        public AnonymousClass1(ProtocolHttpGateway protocolHttpGateway, AdReport adReport) {
            this.b = protocolHttpGateway;
            this.a = adReport;
        }

        public final void run() {
            try {
                HttpTransaction a;
                ReportAdHttpTransactionFactory reportAdHttpTransactionFactory = this.b.e;
                AdReport adReport = this.a;
                if (adReport instanceof LocalAdReport) {
                    a = reportAdHttpTransactionFactory.a((LocalAdReport) adReport);
                } else if (adReport instanceof StreamingAdReport) {
                    a = reportAdHttpTransactionFactory.a((StreamingAdReport) adReport);
                } else {
                    throw new UnsupportedOperationException("unknown report type " + adReport);
                }
                a.a();
            } catch (Throwable e) {
                Logger.w(Logger.PROTOCOL_TAG, "error sending report ad", e);
            }
        }
    }

    /* compiled from: vungle */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ c b;
        final /* synthetic */ ProtocolHttpGateway c;

        public AnonymousClass4(ProtocolHttpGateway protocolHttpGateway, String str, c cVar) {
            this.c = protocolHttpGateway;
            this.a = str;
            this.b = cVar;
        }

        public final void run() {
            try {
                this.c.h.a(this.a, this.b).a();
            } catch (Throwable e) {
                Logger.w(Logger.PROTOCOL_TAG, "error creating request streaming ad message", e);
                this.c.b();
            } catch (Throwable e2) {
                Logger.w(Logger.PROTOCOL_TAG, "error requesting streaming ad", e2);
                this.c.b();
            }
        }
    }

    /* compiled from: vungle */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ long a;
        final /* synthetic */ long b;
        final /* synthetic */ ProtocolHttpGateway c;

        public AnonymousClass5(ProtocolHttpGateway protocolHttpGateway, long j, long j2) {
            this.c = protocolHttpGateway;
            this.a = j;
            this.b = j2;
        }

        public final void run() {
            try {
                this.c.j.a(this.a, this.b).a();
            } catch (Throwable e) {
                Logger.w(Logger.PROTOCOL_TAG, "error sending session end", e);
            }
        }
    }

    /* compiled from: vungle */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ long a;
        final /* synthetic */ ProtocolHttpGateway b;

        public AnonymousClass6(ProtocolHttpGateway protocolHttpGateway, long j) {
            this.b = protocolHttpGateway;
            this.a = j;
        }

        public final void run() {
            try {
                this.b.k.a(this.a).a();
            } catch (Throwable e) {
                Logger.w(Logger.PROTOCOL_TAG, "error sending session start", e);
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    static class PrepareLocalAdEventListener extends bk {
        @Inject
        Provider<ProtocolHttpGateway> a;

        PrepareLocalAdEventListener() {
        }

        public void onEvent(z zVar) {
            a(null);
        }

        public void onEvent(v prepareAdRecoverableFailureEvent) {
            a(prepareAdRecoverableFailureEvent.a);
        }

        public void onEvent(p pVar) {
            a(null);
        }

        private void a(ci ciVar) {
            ((ProtocolHttpGateway) this.a.get()).b(ciVar);
        }
    }

    ProtocolHttpGateway() {
    }

    public final void a() {
        this.d.a(new Runnable(this) {
            final /* synthetic */ ProtocolHttpGateway a;

            {
                this.a = r1;
            }

            public final void run() {
                try {
                    this.a.f.a();
                } catch (Throwable e) {
                    Logger.w(Logger.PROTOCOL_TAG, "error sending request config", e);
                }
            }
        }, b.requestConfig);
    }

    public final void a(final ci ciVar) {
        this.d.a(new Runnable(this) {
            final /* synthetic */ ProtocolHttpGateway b;

            public final void run() {
                try {
                    if (this.b.o.compareAndSet(false, true)) {
                        this.b.n.a = SystemClock.elapsedRealtime();
                        this.b.b.b();
                        this.b.g.a(ciVar).a();
                        return;
                    }
                    Logger.d(Logger.PROTOCOL_TAG, "request ad already in progress");
                } catch (Throwable e) {
                    Logger.w(Logger.PROTOCOL_TAG, "error requesting local ad", e);
                    this.b.b(ciVar);
                }
            }
        }, b.requestLocalAd, (long) ciVar.a(Settings.MIN_NANAS_TO_ALERT_RATING));
    }

    final void b(ci ciVar) {
        this.o.set(false);
        this.b.d();
        if (ciVar != null) {
            a(ciVar);
        }
    }

    final void b() {
        this.a.a(new n());
    }

    public final void c(final ci ciVar) {
        this.d.a(new Runnable(this) {
            final /* synthetic */ ProtocolHttpGateway b;

            public final void run() {
                try {
                    ((HttpTransaction) this.b.l.get()).a();
                } catch (Throwable e) {
                    Logger.w(Logger.PROTOCOL_TAG, "error sending track install", e);
                    this.b.c(ciVar);
                }
            }
        }, b.reportInstall, (long) ciVar.a(5000));
    }

    public final void c() {
        this.d.a(new Runnable(this) {
            final /* synthetic */ ProtocolHttpGateway a;

            {
                this.a = r1;
            }

            public final void run() {
                try {
                    this.a.m.a(System.currentTimeMillis() / 1000).a();
                } catch (Throwable e) {
                    Logger.w(Logger.PROTOCOL_TAG, "error sending unfilled ad", e);
                }
            }
        }, b.unfilledAd);
    }
}
