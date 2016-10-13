package com.chartboost.sdk.impl;

import android.os.Handler;
import java.util.concurrent.Executor;

public class e implements n {
    private final Executor a;

    private class a implements Runnable {
        final /* synthetic */ e a;
        private final k b;
        private final m c;
        private final Runnable d;

        public a(e eVar, k kVar, m mVar, Runnable runnable) {
            this.a = eVar;
            this.b = kVar;
            this.c = mVar;
            this.d = runnable;
        }

        public void run() {
            if (this.b.h()) {
                this.b.b("canceled-at-delivery");
                return;
            }
            if (this.c.a()) {
                this.b.b(this.c.a);
            } else {
                this.b.b(this.c.c);
            }
            if (this.c.d) {
                this.b.a("intermediate-response");
            } else {
                this.b.b("done");
            }
            if (this.d != null) {
                this.d.run();
            }
        }
    }

    public e(final Handler handler) {
        this.a = new Executor(this) {
            final /* synthetic */ e a;

            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    public void a(k<?> kVar, m<?> mVar) {
        a(kVar, mVar, null);
    }

    public void a(k<?> kVar, m<?> mVar, Runnable runnable) {
        kVar.v();
        kVar.a("post-response");
        this.a.execute(new a(this, kVar, mVar, runnable));
    }

    public void a(k<?> kVar, r rVar) {
        kVar.a("post-error");
        this.a.execute(new a(this, kVar, m.a(rVar), null));
    }
}
