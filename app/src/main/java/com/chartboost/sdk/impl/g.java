package com.chartboost.sdk.impl;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class g extends Thread {
    private final BlockingQueue<k<?>> a;
    private final f b;
    private final b c;
    private final n d;
    private volatile boolean e = false;

    public g(BlockingQueue<k<?>> blockingQueue, f fVar, b bVar, n nVar) {
        this.a = blockingQueue;
        this.b = fVar;
        this.c = bVar;
        this.d = nVar;
    }

    public void a() {
        this.e = true;
        interrupt();
    }

    @TargetApi(14)
    private void a(k<?> kVar) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(kVar.c());
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                k kVar = (k) this.a.take();
                try {
                    kVar.a("network-queue-take");
                    if (kVar.h()) {
                        kVar.b("network-discard-cancelled");
                    } else {
                        a(kVar);
                        i a = this.b.a(kVar);
                        kVar.a("network-http-complete");
                        if (a.d && kVar.w()) {
                            kVar.b("not-modified");
                        } else {
                            m a2 = kVar.a(a);
                            kVar.a("network-parse-complete");
                            if (kVar.r() && a2.b != null) {
                                this.c.a(kVar.e(), a2.b);
                                kVar.a("network-cache-written");
                            }
                            kVar.v();
                            this.d.a(kVar, a2);
                        }
                    }
                } catch (r e) {
                    a(kVar, e);
                } catch (Throwable e2) {
                    s.a(e2, "Unhandled exception %s", e2.toString());
                    this.d.a(kVar, new r(e2));
                }
            } catch (InterruptedException e3) {
                if (this.e) {
                    return;
                }
            }
        }
    }

    private void a(k<?> kVar, r rVar) {
        this.d.a((k) kVar, kVar.a(rVar));
    }
}
