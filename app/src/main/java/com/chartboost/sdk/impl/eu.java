package com.chartboost.sdk.impl;

import com.facebook.internal.NativeProtocol;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class eu implements Closeable {
    static final /* synthetic */ boolean k;
    private static final ExecutorService l = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), cs.a("OkHttp SpdyConnection", true));
    final cj a;
    final boolean b;
    long c;
    long d;
    final es e;
    final es f;
    final eh g;
    final ei h;
    final long i;
    final b j;
    private final eo m;
    private final Map<Integer, ev> n;
    private final String o;
    private int p;
    private int q;
    private boolean r;
    private long s;
    private Map<Integer, eq> t;
    private final er u;
    private int v;
    private boolean w;
    private final Set<Integer> x;

    public static class a {
        private String a;
        private dr b;
        private dq c;
        private eo d = eo.a;
        private cj e = cj.SPDY_3;
        private er f = er.a;
        private boolean g;

        public a(String str, boolean z, dr drVar, dq dqVar) {
            this.a = str;
            this.g = z;
            this.b = drVar;
            this.c = dqVar;
        }

        public a a(cj cjVar) {
            this.e = cjVar;
            return this;
        }

        public eu a() {
            return new eu();
        }
    }

    class b extends cq implements com.chartboost.sdk.impl.eh.a {
        final /* synthetic */ eu a;

        private b(eu euVar) {
            this.a = euVar;
            super("OkHttp %s", euVar.o);
        }

        protected void a() {
            eg egVar;
            Throwable th;
            eg egVar2 = eg.INTERNAL_ERROR;
            eg egVar3 = eg.INTERNAL_ERROR;
            try {
                if (!this.a.b) {
                    this.a.g.a();
                }
                do {
                } while (this.a.g.a(this));
                try {
                    this.a.a(eg.NO_ERROR, eg.CANCEL);
                } catch (IOException e) {
                }
            } catch (IOException e2) {
                egVar = eg.PROTOCOL_ERROR;
                try {
                    this.a.a(egVar, eg.PROTOCOL_ERROR);
                } catch (IOException e3) {
                }
            } catch (Throwable th2) {
                th = th2;
                this.a.a(egVar, egVar3);
                throw th;
            }
        }

        public void a(boolean z, int i, dr drVar, int i2) throws IOException {
            if (this.a.d(i)) {
                this.a.a(i, drVar, i2, z);
                return;
            }
            ev a = this.a.a(i);
            if (a == null) {
                this.a.a(i, eg.INVALID_STREAM);
                drVar.b((long) i2);
                return;
            }
            a.a(drVar, i2);
            if (z) {
                a.g();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(boolean r9, boolean r10, int r11, int r12, int r13, java.util.List<com.chartboost.sdk.impl.ej> r14, com.chartboost.sdk.impl.ek r15) {
            /*
            r8 = this;
            r0 = r8.a;
            r0 = r0.d(r11);
            if (r0 == 0) goto L_0x000e;
        L_0x0008:
            r0 = r8.a;
            r0.a(r11, r14, r10);
        L_0x000d:
            return;
        L_0x000e:
            r7 = r8.a;
            monitor-enter(r7);
            r0 = r8.a;	 Catch:{ all -> 0x001b }
            r0 = r0.r;	 Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x001e;
        L_0x0019:
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x001b:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            throw r0;
        L_0x001e:
            r0 = r8.a;	 Catch:{ all -> 0x001b }
            r0 = r0.a(r11);	 Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x008e;
        L_0x0026:
            r0 = r15.a();	 Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0035;
        L_0x002c:
            r0 = r8.a;	 Catch:{ all -> 0x001b }
            r1 = com.chartboost.sdk.impl.eg.INVALID_STREAM;	 Catch:{ all -> 0x001b }
            r0.a(r11, r1);	 Catch:{ all -> 0x001b }
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x0035:
            r0 = r8.a;	 Catch:{ all -> 0x001b }
            r0 = r0.p;	 Catch:{ all -> 0x001b }
            if (r11 > r0) goto L_0x003f;
        L_0x003d:
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x003f:
            r0 = r11 % 2;
            r1 = r8.a;	 Catch:{ all -> 0x001b }
            r1 = r1.q;	 Catch:{ all -> 0x001b }
            r1 = r1 % 2;
            if (r0 != r1) goto L_0x004d;
        L_0x004b:
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x004d:
            r0 = new com.chartboost.sdk.impl.ev;	 Catch:{ all -> 0x001b }
            r2 = r8.a;	 Catch:{ all -> 0x001b }
            r1 = r11;
            r3 = r9;
            r4 = r10;
            r5 = r13;
            r6 = r14;
            r0.<init>(r1, r2, r3, r4, r5, r6);	 Catch:{ all -> 0x001b }
            r1 = r8.a;	 Catch:{ all -> 0x001b }
            r1.p = r11;	 Catch:{ all -> 0x001b }
            r1 = r8.a;	 Catch:{ all -> 0x001b }
            r1 = r1.n;	 Catch:{ all -> 0x001b }
            r2 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x001b }
            r1.put(r2, r0);	 Catch:{ all -> 0x001b }
            r1 = com.chartboost.sdk.impl.eu.l;	 Catch:{ all -> 0x001b }
            r2 = new com.chartboost.sdk.impl.eu$b$1;	 Catch:{ all -> 0x001b }
            r3 = "OkHttp %s stream %d";
            r4 = 2;
            r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x001b }
            r5 = 0;
            r6 = r8.a;	 Catch:{ all -> 0x001b }
            r6 = r6.o;	 Catch:{ all -> 0x001b }
            r4[r5] = r6;	 Catch:{ all -> 0x001b }
            r5 = 1;
            r6 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x001b }
            r4[r5] = r6;	 Catch:{ all -> 0x001b }
            r2.<init>(r8, r3, r4, r0);	 Catch:{ all -> 0x001b }
            r1.submit(r2);	 Catch:{ all -> 0x001b }
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x008e:
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            r1 = r15.b();
            if (r1 == 0) goto L_0x00a1;
        L_0x0095:
            r1 = com.chartboost.sdk.impl.eg.PROTOCOL_ERROR;
            r0.b(r1);
            r0 = r8.a;
            r0.b(r11);
            goto L_0x000d;
        L_0x00a1:
            r0.a(r14, r15);
            if (r10 == 0) goto L_0x000d;
        L_0x00a6:
            r0.g();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.eu.b.a(boolean, boolean, int, int, int, java.util.List, com.chartboost.sdk.impl.ek):void");
        }

        public void a(int i, eg egVar) {
            if (this.a.d(i)) {
                this.a.c(i, egVar);
                return;
            }
            ev b = this.a.b(i);
            if (b != null) {
                b.c(egVar);
            }
        }

        public void a(boolean z, es esVar) {
            ev[] evVarArr = null;
            synchronized (this.a) {
                long j;
                int d = this.a.f.d(NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST);
                if (z) {
                    this.a.f.a();
                }
                this.a.f.a(esVar);
                if (this.a.a() == cj.HTTP_2) {
                    c();
                }
                int d2 = this.a.f.d(NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST);
                if (d2 == -1 || d2 == d) {
                    j = 0;
                } else {
                    j = (long) (d2 - d);
                    if (!this.a.w) {
                        this.a.a(j);
                        this.a.w = true;
                    }
                    if (!this.a.n.isEmpty()) {
                        evVarArr = (ev[]) this.a.n.values().toArray(new ev[this.a.n.size()]);
                    }
                }
            }
            if (evVarArr != null && j != 0) {
                for (ev evVar : this.a.n.values()) {
                    synchronized (evVar) {
                        evVar.b(j);
                    }
                }
            }
        }

        private void c() {
            eu.l.submit(new cq(this, "OkHttp %s ACK Settings", this.a.o) {
                final /* synthetic */ b a;

                public void a() {
                    try {
                        this.a.a.h.b();
                    } catch (IOException e) {
                    }
                }
            });
        }

        public void b() {
        }

        public void a(boolean z, int i, int i2) {
            if (z) {
                eq c = this.a.c(i);
                if (c != null) {
                    c.b();
                    return;
                }
                return;
            }
            this.a.a(true, i, i2, null);
        }

        public void a(int i, eg egVar, ds dsVar) {
            if (dsVar.e() > 0) {
            }
            synchronized (this.a) {
                this.a.r = true;
                Iterator it = this.a.n.entrySet().iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    if (((Integer) entry.getKey()).intValue() > i && ((ev) entry.getValue()).b()) {
                        ((ev) entry.getValue()).c(eg.REFUSED_STREAM);
                        it.remove();
                    }
                }
            }
        }

        public void a(int i, long j) {
            if (i == 0) {
                synchronized (this.a) {
                    eu euVar = this.a;
                    euVar.d += j;
                    this.a.notifyAll();
                }
                return;
            }
            ev a = this.a.a(i);
            if (a != null) {
                synchronized (a) {
                    a.b(j);
                }
            }
        }

        public void a(int i, int i2) {
        }

        public void a(int i, int i2, List<ej> list) {
            this.a.a(i2, (List) list);
        }
    }

    static {
        boolean z;
        if (eu.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        k = z;
    }

    private eu(a aVar) {
        ew emVar;
        int i = 1;
        this.n = new HashMap();
        this.s = System.nanoTime();
        this.c = 0;
        this.e = new es();
        this.f = new es();
        this.w = false;
        this.x = new LinkedHashSet();
        this.a = aVar.e;
        this.u = aVar.f;
        this.b = aVar.g;
        this.m = aVar.d;
        this.q = aVar.g ? 1 : 2;
        if (!aVar.g) {
            i = 2;
        }
        this.v = i;
        if (aVar.g) {
            this.e.a(7, 0, 16777216);
        }
        this.o = aVar.a;
        if (this.a == cj.HTTP_2) {
            emVar = new em();
        } else if (this.a == cj.SPDY_3) {
            emVar = new et();
        } else {
            throw new AssertionError(this.a);
        }
        this.d = (long) this.f.d(NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST);
        this.g = emVar.a(aVar.b, this.b);
        this.h = emVar.a(aVar.c, this.b);
        this.i = (long) emVar.a();
        this.j = new b();
        new Thread(this.j).start();
    }

    public cj a() {
        return this.a;
    }

    synchronized ev a(int i) {
        return (ev) this.n.get(Integer.valueOf(i));
    }

    synchronized ev b(int i) {
        ev evVar;
        evVar = (ev) this.n.remove(Integer.valueOf(i));
        if (evVar != null && this.n.isEmpty()) {
            a(true);
        }
        return evVar;
    }

    private synchronized void a(boolean z) {
        this.s = z ? System.nanoTime() : Long.MAX_VALUE;
    }

    public synchronized boolean b() {
        return this.s != Long.MAX_VALUE;
    }

    public synchronized long c() {
        return this.s;
    }

    public ev a(List<ej> list, boolean z, boolean z2) throws IOException {
        return a(0, (List) list, z, z2);
    }

    private ev a(int i, List<ej> list, boolean z, boolean z2) throws IOException {
        ev evVar;
        boolean z3 = !z;
        boolean z4 = !z2;
        synchronized (this.h) {
            synchronized (this) {
                if (this.r) {
                    throw new IOException("shutdown");
                }
                int i2 = this.q;
                this.q += 2;
                evVar = new ev(i2, this, z3, z4, -1, list);
                if (evVar.a()) {
                    this.n.put(Integer.valueOf(i2), evVar);
                    a(false);
                }
            }
            if (i == 0) {
                this.h.a(z3, z4, i2, i, -1, 0, list);
            } else if (this.b) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            } else {
                this.h.a(i, i2, (List) list);
            }
        }
        if (!z) {
            this.h.c();
        }
        return evVar;
    }

    public void a(int i, boolean z, dx dxVar, long j) throws IOException {
        if (j == 0) {
            this.h.a(z, i, dxVar, 0);
            return;
        }
        while (j > 0) {
            int min;
            boolean z2;
            synchronized (this) {
                while (this.d <= 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                min = (int) Math.min(Math.min(j, this.d), this.i);
                this.d -= (long) min;
            }
            j -= (long) min;
            ei eiVar = this.h;
            if (z && j == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            eiVar.a(z2, i, dxVar, min);
        }
    }

    void a(long j) {
        this.d += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void a(int i, eg egVar) {
        final int i2 = i;
        final eg egVar2 = egVar;
        l.submit(new cq(this, "OkHttp %s stream %d", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ eu c;

            public void a() {
                try {
                    this.c.b(i2, egVar2);
                } catch (IOException e) {
                }
            }
        });
    }

    void b(int i, eg egVar) throws IOException {
        this.h.a(i, egVar);
    }

    void a(int i, long j) {
        final int i2 = i;
        final long j2 = j;
        l.submit(new cq(this, "OkHttp Window Update %s stream %d", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ eu c;

            public void a() {
                try {
                    this.c.h.a(i2, j2);
                } catch (IOException e) {
                }
            }
        });
    }

    private void a(boolean z, int i, int i2, eq eqVar) {
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final eq eqVar2 = eqVar;
        l.submit(new cq(this, "OkHttp %s ping %08x%08x", new Object[]{this.o, Integer.valueOf(i), Integer.valueOf(i2)}) {
            final /* synthetic */ eu e;

            public void a() {
                try {
                    this.e.b(z2, i3, i4, eqVar2);
                } catch (IOException e) {
                }
            }
        });
    }

    private void b(boolean z, int i, int i2, eq eqVar) throws IOException {
        synchronized (this.h) {
            if (eqVar != null) {
                eqVar.a();
            }
            this.h.a(z, i, i2);
        }
    }

    private synchronized eq c(int i) {
        return this.t != null ? (eq) this.t.remove(Integer.valueOf(i)) : null;
    }

    public void d() throws IOException {
        this.h.c();
    }

    public void a(eg egVar) throws IOException {
        synchronized (this.h) {
            synchronized (this) {
                if (this.r) {
                    return;
                }
                this.r = true;
                int i = this.p;
                this.h.a(i, egVar, cs.a);
            }
        }
    }

    public void close() throws IOException {
        a(eg.NO_ERROR, eg.CANCEL);
    }

    private void a(eg egVar, eg egVar2) throws IOException {
        IOException iOException;
        IOException e;
        if (k || !Thread.holdsLock(this)) {
            ev[] evVarArr;
            eq[] eqVarArr;
            try {
                a(egVar);
                iOException = null;
            } catch (IOException e2) {
                iOException = e2;
            }
            synchronized (this) {
                if (this.n.isEmpty()) {
                    evVarArr = null;
                } else {
                    ev[] evVarArr2 = (ev[]) this.n.values().toArray(new ev[this.n.size()]);
                    this.n.clear();
                    a(false);
                    evVarArr = evVarArr2;
                }
                if (this.t != null) {
                    eq[] eqVarArr2 = (eq[]) this.t.values().toArray(new eq[this.t.size()]);
                    this.t = null;
                    eqVarArr = eqVarArr2;
                } else {
                    eqVarArr = null;
                }
            }
            if (evVarArr != null) {
                e2 = iOException;
                for (ev a : evVarArr) {
                    try {
                        a.a(egVar2);
                    } catch (IOException iOException2) {
                        if (e2 != null) {
                            e2 = iOException2;
                        }
                    }
                }
                iOException2 = e2;
            }
            if (eqVarArr != null) {
                for (eq c : eqVarArr) {
                    c.c();
                }
            }
            try {
                this.g.close();
            } catch (IOException e22) {
                iOException2 = e22;
            }
            try {
                this.h.close();
                e22 = iOException2;
            } catch (IOException e3) {
                e22 = e3;
                if (iOException2 != null) {
                    e22 = iOException2;
                }
            }
            if (e22 != null) {
                throw e22;
            }
            return;
        }
        throw new AssertionError();
    }

    public void e() throws IOException {
        this.h.a();
        this.h.a(this.e);
    }

    private boolean d(int i) {
        return this.a == cj.HTTP_2 && i != 0 && (i & 1) == 0;
    }

    private void a(int i, List<ej> list) {
        synchronized (this) {
            if (this.x.contains(Integer.valueOf(i))) {
                a(i, eg.PROTOCOL_ERROR);
                return;
            }
            this.x.add(Integer.valueOf(i));
            final int i2 = i;
            final List<ej> list2 = list;
            l.submit(new cq(this, "OkHttp %s Push Request[%s]", new Object[]{this.o, Integer.valueOf(i)}) {
                final /* synthetic */ eu c;

                public void a() {
                    if (this.c.u.a(i2, list2)) {
                        try {
                            this.c.h.a(i2, eg.CANCEL);
                            synchronized (this.c) {
                                this.c.x.remove(Integer.valueOf(i2));
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            });
        }
    }

    private void a(int i, List<ej> list, boolean z) {
        final int i2 = i;
        final List<ej> list2 = list;
        final boolean z2 = z;
        l.submit(new cq(this, "OkHttp %s Push Headers[%s]", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ eu d;

            public void a() {
                boolean a = this.d.u.a(i2, list2, z2);
                if (a) {
                    try {
                        this.d.h.a(i2, eg.CANCEL);
                    } catch (IOException e) {
                        return;
                    }
                }
                if (a || z2) {
                    synchronized (this.d) {
                        this.d.x.remove(Integer.valueOf(i2));
                    }
                }
            }
        });
    }

    private void a(int i, dr drVar, int i2, boolean z) throws IOException {
        final dx dxVar = new dx();
        drVar.a((long) i2);
        drVar.b(dxVar, (long) i2);
        if (dxVar.l() != ((long) i2)) {
            throw new IOException(dxVar.l() + " != " + i2);
        }
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        l.submit(new cq(this, "OkHttp %s Push Data[%s]", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ eu e;

            public void a() {
                try {
                    boolean a = this.e.u.a(i3, dxVar, i4, z2);
                    if (a) {
                        this.e.h.a(i3, eg.CANCEL);
                    }
                    if (a || z2) {
                        synchronized (this.e) {
                            this.e.x.remove(Integer.valueOf(i3));
                        }
                    }
                } catch (IOException e) {
                }
            }
        });
    }

    private void c(int i, eg egVar) {
        final int i2 = i;
        final eg egVar2 = egVar;
        l.submit(new cq(this, "OkHttp %s Push Reset[%s]", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ eu c;

            public void a() {
                this.c.u.a(i2, egVar2);
                synchronized (this.c) {
                    this.c.x.remove(Integer.valueOf(i2));
                }
            }
        });
    }
}
