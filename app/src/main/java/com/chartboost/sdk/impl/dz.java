package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.OutputStream;

final class dz implements dq {
    public final dx a;
    public final ed b;
    private boolean c;

    public dz(ed edVar, dx dxVar) {
        if (edVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.a = dxVar;
        this.b = edVar;
    }

    public dz(ed edVar) {
        this(edVar, new dx());
    }

    public dx b() {
        return this.a;
    }

    public void a(dx dxVar, long j) throws IOException {
        e();
        this.a.a(dxVar, j);
        c();
    }

    public dq a(ds dsVar) throws IOException {
        e();
        this.a.b(dsVar);
        return c();
    }

    public dq a(String str) throws IOException {
        e();
        this.a.b(str);
        return c();
    }

    public dq a(byte[] bArr) throws IOException {
        e();
        this.a.b(bArr);
        return c();
    }

    public dq a(byte[] bArr, int i, int i2) throws IOException {
        e();
        this.a.c(bArr, i, i2);
        return c();
    }

    public dq a(int i) throws IOException {
        e();
        this.a.d(i);
        return c();
    }

    public dq b(int i) throws IOException {
        e();
        this.a.e(i);
        return c();
    }

    public dq c() throws IOException {
        e();
        long n = this.a.n();
        if (n > 0) {
            this.b.a(this.a, n);
        }
        return this;
    }

    public OutputStream d() {
        return new OutputStream(this) {
            final /* synthetic */ dz a;

            {
                this.a = r1;
            }

            public void write(int b) throws IOException {
                a();
                this.a.a.c((byte) b);
                this.a.c();
            }

            public void write(byte[] data, int offset, int byteCount) throws IOException {
                a();
                this.a.a.c(data, offset, byteCount);
                this.a.c();
            }

            public void flush() throws IOException {
                if (!this.a.c) {
                    this.a.a();
                }
            }

            public void close() throws IOException {
                this.a.close();
            }

            public String toString() {
                return this.a + ".outputStream()";
            }

            private void a() throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
            }
        };
    }

    public void a() throws IOException {
        e();
        if (this.a.b > 0) {
            this.b.a(this.a, this.a.b);
        }
        this.b.a();
    }

    public void close() throws IOException {
        if (!this.c) {
            Throwable th = null;
            try {
                if (this.a.b > 0) {
                    this.b.a(this.a, this.a.b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.c = true;
            if (th != null) {
                ef.a(th);
            }
        }
    }

    public String toString() {
        return "buffer(" + this.b + ")";
    }

    private void e() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
    }
}
