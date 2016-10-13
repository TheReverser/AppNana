package com.chartboost.sdk.impl;

import com.facebook.internal.NativeProtocol;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public final class ev {
    static final /* synthetic */ boolean d = (!ev.class.desiredAssertionStatus());
    long a = 0;
    long b;
    final a c;
    private final int e;
    private final eu f;
    private final int g;
    private long h = 0;
    private final List<ej> i;
    private List<ej> j;
    private final b k;
    private eg l = null;

    final class a implements ed {
        static final /* synthetic */ boolean a = (!ev.class.desiredAssertionStatus());
        final /* synthetic */ ev b;
        private boolean c;
        private boolean d;

        a(ev evVar) {
            this.b = evVar;
        }

        public void a(dx dxVar, long j) throws IOException {
            if (a || !Thread.holdsLock(this.b)) {
                while (j > 0) {
                    long min;
                    synchronized (this.b) {
                        while (this.b.b <= 0) {
                            try {
                                this.b.wait();
                            } catch (InterruptedException e) {
                                throw new InterruptedIOException();
                            }
                        }
                        this.b.i();
                        min = Math.min(this.b.b, j);
                        ev evVar = this.b;
                        evVar.b -= min;
                    }
                    j -= min;
                    this.b.f.a(this.b.e, false, dxVar, min);
                }
                return;
            }
            throw new AssertionError();
        }

        public void a() throws IOException {
            if (a || !Thread.holdsLock(this.b)) {
                synchronized (this.b) {
                    this.b.i();
                }
                this.b.f.d();
                return;
            }
            throw new AssertionError();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
            r6 = this;
            r2 = 1;
            r0 = a;
            if (r0 != 0) goto L_0x0013;
        L_0x0005:
            r0 = r6.b;
            r0 = java.lang.Thread.holdsLock(r0);
            if (r0 == 0) goto L_0x0013;
        L_0x000d:
            r0 = new java.lang.AssertionError;
            r0.<init>();
            throw r0;
        L_0x0013:
            r1 = r6.b;
            monitor-enter(r1);
            r0 = r6.c;	 Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x001c;
        L_0x001a:
            monitor-exit(r1);	 Catch:{ all -> 0x004d }
        L_0x001b:
            return;
        L_0x001c:
            monitor-exit(r1);	 Catch:{ all -> 0x004d }
            r0 = r6.b;
            r0 = r0.c;
            r0 = r0.d;
            if (r0 != 0) goto L_0x0037;
        L_0x0025:
            r0 = r6.b;
            r0 = r0.f;
            r1 = r6.b;
            r1 = r1.e;
            r3 = 0;
            r4 = 0;
            r0.a(r1, r2, r3, r4);
        L_0x0037:
            r1 = r6.b;
            monitor-enter(r1);
            r0 = 1;
            r6.c = r0;	 Catch:{ all -> 0x0050 }
            monitor-exit(r1);	 Catch:{ all -> 0x0050 }
            r0 = r6.b;
            r0 = r0.f;
            r0.d();
            r0 = r6.b;
            r0.h();
            goto L_0x001b;
        L_0x004d:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x004d }
            throw r0;
        L_0x0050:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0050 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.ev.a.close():void");
        }
    }

    private final class b implements ee {
        static final /* synthetic */ boolean a = (!ev.class.desiredAssertionStatus());
        final /* synthetic */ ev b;
        private final dx c;
        private final dx d;
        private final long e;
        private boolean f;
        private boolean g;

        private b(ev evVar, long j) {
            this.b = evVar;
            this.c = new dx();
            this.d = new dx();
            this.e = j;
        }

        public long b(dx dxVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            long j2;
            synchronized (this.b) {
                a();
                b();
                if (this.d.l() == 0) {
                    j2 = -1;
                } else {
                    j2 = this.d.b(dxVar, Math.min(j, this.d.l()));
                    ev evVar = this.b;
                    evVar.a += j2;
                    if (this.b.a >= ((long) (this.b.f.f.d(NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST) / 2))) {
                        this.b.f.a(this.b.e, this.b.a);
                        this.b.a = 0;
                    }
                    synchronized (this.b.f) {
                        eu a = this.b.f;
                        a.c += j2;
                        if (this.b.f.c >= ((long) (this.b.f.f.d(NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST) / 2))) {
                            this.b.f.a(0, this.b.f.c);
                            this.b.f.c = 0;
                        }
                    }
                }
            }
            return j2;
        }

        private void a() throws IOException {
            long nanoTime;
            long c;
            if (this.b.h != 0) {
                nanoTime = System.nanoTime() / 1000000;
                c = this.b.h;
            } else {
                c = 0;
                nanoTime = 0;
            }
            while (this.d.l() == 0 && !this.g && !this.f && this.b.l == null) {
                try {
                    if (this.b.h == 0) {
                        this.b.wait();
                    } else if (c > 0) {
                        this.b.wait(c);
                        c = (this.b.h + nanoTime) - (System.nanoTime() / 1000000);
                    } else {
                        throw new SocketTimeoutException("Read timed out");
                    }
                } catch (InterruptedException e) {
                    throw new InterruptedIOException();
                }
            }
        }

        void a(dr drVar, long j) throws IOException {
            if (a || !Thread.holdsLock(this.b)) {
                while (j > 0) {
                    boolean z;
                    Object obj;
                    synchronized (this.b) {
                        z = this.g;
                        obj = this.d.l() + j > this.e ? 1 : null;
                    }
                    if (obj != null) {
                        drVar.b(j);
                        this.b.b(eg.FLOW_CONTROL_ERROR);
                        return;
                    } else if (z) {
                        drVar.b(j);
                        return;
                    } else {
                        long b = drVar.b(this.c, j);
                        if (b == -1) {
                            throw new EOFException();
                        }
                        j -= b;
                        synchronized (this.b) {
                            if (this.d.l() == 0) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            this.d.a(this.c, this.c.l());
                            if (obj != null) {
                                this.b.notifyAll();
                            }
                        }
                    }
                }
                return;
            }
            throw new AssertionError();
        }

        public void close() throws IOException {
            synchronized (this.b) {
                this.f = true;
                this.d.o();
                this.b.notifyAll();
            }
            this.b.h();
        }

        private void b() throws IOException {
            if (this.f) {
                throw new IOException("stream closed");
            } else if (this.b.l != null) {
                throw new IOException("stream was reset: " + this.b.l);
            }
        }
    }

    ev(int i, eu euVar, boolean z, boolean z2, int i2, List<ej> list) {
        if (euVar == null) {
            throw new NullPointerException("connection == null");
        } else if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        } else {
            this.e = i;
            this.f = euVar;
            this.b = (long) euVar.f.d(NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST);
            this.k = new b((long) euVar.e.d(NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST));
            this.c = new a(this);
            this.k.g = z2;
            this.c.d = z;
            this.g = i2;
            this.i = list;
        }
    }

    public synchronized boolean a() {
        boolean z = false;
        synchronized (this) {
            if (this.l == null) {
                if (!(this.k.g || this.k.f) || (!(this.c.d || this.c.c) || this.j == null)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean b() {
        boolean z;
        if ((this.e & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        return this.f.b == z;
    }

    public synchronized List<ej> c() throws IOException {
        long nanoTime;
        long j;
        if (this.h != 0) {
            nanoTime = System.nanoTime() / 1000000;
            j = this.h;
        } else {
            nanoTime = 0;
            j = 0;
        }
        while (this.j == null && this.l == null) {
            if (this.h == 0) {
                wait();
            } else if (j > 0) {
                try {
                    wait(j);
                    j = (this.h + nanoTime) - (System.nanoTime() / 1000000);
                } catch (Throwable e) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException();
                    interruptedIOException.initCause(e);
                    throw interruptedIOException;
                }
            } else {
                throw new SocketTimeoutException("Read response header timeout. readTimeoutMillis: " + this.h);
            }
        }
        if (this.j != null) {
        } else {
            throw new IOException("stream was reset: " + this.l);
        }
        return this.j;
    }

    public void a(long j) {
        this.h = j;
    }

    public long d() {
        return this.h;
    }

    public ee e() {
        return this.k;
    }

    public ed f() {
        synchronized (this) {
            if (this.j != null || b()) {
            } else {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.c;
    }

    public void a(eg egVar) throws IOException {
        if (d(egVar)) {
            this.f.b(this.e, egVar);
        }
    }

    public void b(eg egVar) {
        if (d(egVar)) {
            this.f.a(this.e, egVar);
        }
    }

    private boolean d(eg egVar) {
        if (d || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (this.l != null) {
                    return false;
                } else if (this.k.g && this.c.d) {
                    return false;
                } else {
                    this.l = egVar;
                    notifyAll();
                    this.f.b(this.e);
                    return true;
                }
            }
        }
        throw new AssertionError();
    }

    void a(List<ej> list, ek ekVar) {
        if (d || !Thread.holdsLock(this)) {
            eg egVar = null;
            boolean z = true;
            synchronized (this) {
                if (this.j == null) {
                    if (ekVar.c()) {
                        egVar = eg.PROTOCOL_ERROR;
                    } else {
                        this.j = list;
                        z = a();
                        notifyAll();
                    }
                } else if (ekVar.d()) {
                    egVar = eg.STREAM_IN_USE;
                } else {
                    List arrayList = new ArrayList();
                    arrayList.addAll(this.j);
                    arrayList.addAll(list);
                    this.j = arrayList;
                }
            }
            if (egVar != null) {
                b(egVar);
                return;
            } else if (!z) {
                this.f.b(this.e);
                return;
            } else {
                return;
            }
        }
        throw new AssertionError();
    }

    void a(dr drVar, int i) throws IOException {
        if (d || !Thread.holdsLock(this)) {
            this.k.a(drVar, (long) i);
            return;
        }
        throw new AssertionError();
    }

    void g() {
        if (d || !Thread.holdsLock(this)) {
            boolean a;
            synchronized (this) {
                this.k.g = true;
                a = a();
                notifyAll();
            }
            if (!a) {
                this.f.b(this.e);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    synchronized void c(eg egVar) {
        if (this.l == null) {
            this.l = egVar;
            notifyAll();
        }
    }

    private void h() throws IOException {
        if (d || !Thread.holdsLock(this)) {
            Object obj;
            boolean a;
            synchronized (this) {
                obj = (!this.k.g && this.k.f && (this.c.d || this.c.c)) ? 1 : null;
                a = a();
            }
            if (obj != null) {
                a(eg.CANCEL);
                return;
            } else if (!a) {
                this.f.b(this.e);
                return;
            } else {
                return;
            }
        }
        throw new AssertionError();
    }

    void b(long j) {
        this.b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    private void i() throws IOException {
        if (this.c.c) {
            throw new IOException("stream closed");
        } else if (this.c.d) {
            throw new IOException("stream finished");
        } else if (this.l != null) {
            throw new IOException("stream was reset: " + this.l);
        }
    }
}
