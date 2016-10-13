package com.chartboost.sdk.impl;

import android.os.Process;
import com.chartboost.sdk.impl.b.a;
import java.util.concurrent.BlockingQueue;

public class c extends Thread {
    private static final boolean a = s.b;
    private final BlockingQueue<k<?>> b;
    private final BlockingQueue<k<?>> c;
    private final b d;
    private final n e;
    private volatile boolean f = false;

    public c(BlockingQueue<k<?>> blockingQueue, BlockingQueue<k<?>> blockingQueue2, b bVar, n nVar) {
        this.b = blockingQueue;
        this.c = blockingQueue2;
        this.d = bVar;
        this.e = nVar;
    }

    public void a() {
        this.f = true;
        interrupt();
    }

    public void run() {
        if (a) {
            s.a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.d.a();
        while (true) {
            try {
                final k kVar = (k) this.b.take();
                kVar.a("cache-queue-take");
                if (kVar.h()) {
                    kVar.b("cache-discard-canceled");
                } else {
                    a a = this.d.a(kVar.e());
                    if (a == null) {
                        kVar.a("cache-miss");
                        this.c.put(kVar);
                    } else if (a.a()) {
                        kVar.a("cache-hit-expired");
                        kVar.a(a);
                        this.c.put(kVar);
                    } else {
                        kVar.a("cache-hit");
                        m a2 = kVar.a(new i(a.a, a.f));
                        kVar.a("cache-hit-parsed");
                        if (a.b()) {
                            kVar.a("cache-hit-refresh-needed");
                            kVar.a(a);
                            a2.d = true;
                            this.e.a(kVar, a2, new Runnable(this) {
                                final /* synthetic */ c a;

                                public void run() {
                                    try {
                                        this.a.c.put(kVar);
                                    } catch (InterruptedException e) {
                                    }
                                }
                            });
                        } else {
                            this.e.a(kVar, a2);
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.f) {
                    return;
                }
            }
        }
    }
}
