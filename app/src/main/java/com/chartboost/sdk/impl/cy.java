package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.ProtocolException;
import java.net.Socket;

public final class cy {
    private static final byte[] g = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    private static final byte[] h = new byte[]{(byte) 48, (byte) 13, (byte) 10, (byte) 13, (byte) 10};
    private final ce a;
    private final cd b;
    private final dr c;
    private final dq d;
    private int e = 0;
    private int f = 0;

    private class a {
        protected final OutputStream a;
        protected boolean b;
        final /* synthetic */ cy c;
        private final CacheRequest d;

        a(cy cyVar, CacheRequest cacheRequest) throws IOException {
            this.c = cyVar;
            OutputStream body = cacheRequest != null ? cacheRequest.getBody() : null;
            if (body == null) {
                cacheRequest = null;
            }
            this.a = body;
            this.d = cacheRequest;
        }

        protected final void a(dx dxVar, long j) throws IOException {
            if (this.a != null) {
                dy.a(dxVar, dxVar.l() - j, j, this.a);
            }
        }

        protected final void a(boolean z) throws IOException {
            if (this.c.e != 5) {
                throw new IllegalStateException("state: " + this.c.e);
            }
            if (this.d != null) {
                this.a.close();
            }
            this.c.e = 0;
            if (z && this.c.f == 1) {
                this.c.f = 0;
                this.c.a.a(this.c.b);
            } else if (this.c.f == 2) {
                this.c.e = 6;
                this.c.b.close();
            }
        }

        protected final void a() {
            if (this.d != null) {
                this.d.abort();
            }
            cs.a(this.c.b);
            this.c.e = 6;
        }
    }

    private final class b implements ed {
        final /* synthetic */ cy a;
        private final byte[] b;
        private boolean c;

        private b(cy cyVar) {
            this.a = cyVar;
            this.b = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 13, (byte) 10};
        }

        public void a(dx dxVar, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                a(j);
                this.a.d.a(dxVar, j);
                this.a.d.a("\r\n");
            }
        }

        public synchronized void a() throws IOException {
            if (!this.c) {
                this.a.d.a();
            }
        }

        public synchronized void close() throws IOException {
            if (!this.c) {
                this.c = true;
                this.a.d.a(cy.h);
                this.a.e = 3;
            }
        }

        private void a(long j) throws IOException {
            int i = 16;
            do {
                i--;
                this.b[i] = cy.g[(int) (15 & j)];
                j >>>= 4;
            } while (j != 0);
            this.a.d.a(this.b, i, this.b.length - i);
        }
    }

    private class c extends a implements ee {
        final /* synthetic */ cy d;
        private int e = -1;
        private boolean f = true;
        private final da g;

        c(cy cyVar, CacheRequest cacheRequest, da daVar) throws IOException {
            this.d = cyVar;
            super(cyVar, cacheRequest);
            this.g = daVar;
        }

        public long b(dx dxVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (!this.f) {
                return -1;
            } else {
                if (this.e == 0 || this.e == -1) {
                    b();
                    if (!this.f) {
                        return -1;
                    }
                }
                long b = this.d.c.b(dxVar, Math.min(j, (long) this.e));
                if (b == -1) {
                    a();
                    throw new IOException("unexpected end of stream");
                }
                this.e = (int) (((long) this.e) - b);
                a(dxVar, b);
                return b;
            }
        }

        private void b() throws IOException {
            if (this.e != -1) {
                this.d.c.a(true);
            }
            String a = this.d.c.a(true);
            int indexOf = a.indexOf(";");
            if (indexOf != -1) {
                a = a.substring(0, indexOf);
            }
            try {
                this.e = Integer.parseInt(a.trim(), 16);
                if (this.e == 0) {
                    this.f = false;
                    com.chartboost.sdk.impl.cw.a aVar = new com.chartboost.sdk.impl.cw.a();
                    this.d.a(aVar);
                    this.g.a(aVar.a());
                    a(true);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException("Expected a hex chunk size but was " + a);
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (this.f && !this.d.a((ee) this, 100)) {
                    a();
                }
                this.b = true;
            }
        }
    }

    private final class d implements ed {
        final /* synthetic */ cy a;
        private boolean b;
        private long c;

        private d(cy cyVar, long j) {
            this.a = cyVar;
            this.c = j;
        }

        public void a(dx dxVar, long j) throws IOException {
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            cs.a(dxVar.l(), 0, j);
            if (j > this.c) {
                throw new ProtocolException("expected " + this.c + " bytes but received " + j);
            }
            this.a.d.a(dxVar, j);
            this.c -= j;
        }

        public void a() throws IOException {
            if (!this.b) {
                this.a.d.a();
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                this.b = true;
                if (this.c > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                this.a.e = 3;
            }
        }
    }

    private class e extends a implements ee {
        final /* synthetic */ cy d;
        private long e;

        public e(cy cyVar, CacheRequest cacheRequest, long j) throws IOException {
            this.d = cyVar;
            super(cyVar, cacheRequest);
            this.e = j;
            if (this.e == 0) {
                a(true);
            }
        }

        public long b(dx dxVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.e == 0) {
                return -1;
            } else {
                long b = this.d.c.b(dxVar, Math.min(this.e, j));
                if (b == -1) {
                    a();
                    throw new ProtocolException("unexpected end of stream");
                }
                this.e -= b;
                a(dxVar, b);
                if (this.e == 0) {
                    a(true);
                }
                return b;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!(this.e == 0 || this.d.a((ee) this, 100))) {
                    a();
                }
                this.b = true;
            }
        }
    }

    class f extends a implements ee {
        final /* synthetic */ cy d;
        private boolean e;

        f(cy cyVar, CacheRequest cacheRequest) throws IOException {
            this.d = cyVar;
            super(cyVar, cacheRequest);
        }

        public long b(dx dxVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.e) {
                return -1;
            } else {
                long b = this.d.c.b(dxVar, j);
                if (b == -1) {
                    this.e = true;
                    a(false);
                    return -1;
                }
                a(dxVar, b);
                return b;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!this.e) {
                    a();
                }
                this.b = true;
            }
        }
    }

    public cy(ce ceVar, cd cdVar, dr drVar, dq dqVar) {
        this.a = ceVar;
        this.b = cdVar;
        this.c = drVar;
        this.d = dqVar;
    }

    public void a() {
        this.f = 1;
        if (this.e == 0) {
            this.f = 0;
            this.a.a(this.b);
        }
    }

    public void b() throws IOException {
        this.f = 2;
        if (this.e == 0) {
            this.e = 6;
            this.b.close();
        }
    }

    public boolean c() {
        return this.e == 6;
    }

    public void d() throws IOException {
        this.d.a();
    }

    public void a(cw cwVar, String str) throws IOException {
        if (this.e != 0) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.d.a(str).a("\r\n");
        for (int i = 0; i < cwVar.a(); i++) {
            this.d.a(cwVar.a(i)).a(": ").a(cwVar.b(i)).a("\r\n");
        }
        this.d.a("\r\n");
        this.e = 1;
    }

    public com.chartboost.sdk.impl.di.b e() throws IOException {
        if (this.e == 1 || this.e == 3) {
            com.chartboost.sdk.impl.di.b a;
            dn dnVar;
            do {
                dnVar = new dn(this.c.a(true));
                a = new com.chartboost.sdk.impl.di.b().a(dnVar).a(df.e, cj.HTTP_11.d.a());
                com.chartboost.sdk.impl.cw.a aVar = new com.chartboost.sdk.impl.cw.a();
                a(aVar);
                a.a(aVar.a());
            } while (dnVar.c() == 100);
            this.e = 4;
            return a;
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public void a(com.chartboost.sdk.impl.cw.a aVar) throws IOException {
        while (true) {
            String a = this.c.a(true);
            if (a.length() != 0) {
                aVar.a(a);
            } else {
                return;
            }
        }
    }

    public boolean a(ee eeVar, int i) {
        int soTimeout;
        Socket c = this.b.c();
        try {
            soTimeout = c.getSoTimeout();
            c.setSoTimeout(i);
            boolean a = cs.a(eeVar, i);
            c.setSoTimeout(soTimeout);
            return a;
        } catch (IOException e) {
            return false;
        } catch (Throwable th) {
            c.setSoTimeout(soTimeout);
        }
    }

    public ed f() {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 2;
        return new b();
    }

    public ed a(long j) {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 2;
        return new d(j);
    }

    public void a(dk dkVar) throws IOException {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 3;
        dkVar.a(this.d);
    }

    public ee a(CacheRequest cacheRequest, long j) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new e(this, cacheRequest, j);
    }

    public void g() throws IOException {
        a(null, 0);
    }

    public ee a(CacheRequest cacheRequest, da daVar) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new c(this, cacheRequest, daVar);
    }

    public ee a(CacheRequest cacheRequest) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new f(this, cacheRequest);
    }
}
