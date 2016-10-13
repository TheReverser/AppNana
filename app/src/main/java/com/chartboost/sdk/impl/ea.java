package com.chartboost.sdk.impl;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

final class ea implements dr {
    public final dx a;
    public final ee b;
    private boolean c;

    public ea(ee eeVar, dx dxVar) {
        if (eeVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.a = dxVar;
        this.b = eeVar;
    }

    public ea(ee eeVar) {
        this(eeVar, new dx());
    }

    public dx b() {
        return this.a;
    }

    public long b(dx dxVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        a();
        if (this.a.b == 0 && this.b.b(this.a, 2048) == -1) {
            return -1;
        }
        return this.a.b(dxVar, Math.min(j, this.a.b));
    }

    public boolean e() throws IOException {
        a();
        return this.a.e() && this.b.b(this.a, 2048) == -1;
    }

    public void a(long j) throws IOException {
        a();
        while (this.a.b < j) {
            if (this.b.b(this.a, 2048) == -1) {
                throw new EOFException();
            }
        }
    }

    public byte f() throws IOException {
        a(1);
        return this.a.f();
    }

    public ds c(long j) throws IOException {
        a(j);
        return this.a.c(j);
    }

    public String d(long j) throws IOException {
        a(j);
        return this.a.e(j);
    }

    public String a(boolean z) throws IOException {
        a();
        long j = 0;
        do {
            j = this.a.a((byte) 10, j);
            if (j == -1) {
                j = this.a.b;
            } else if (j <= 0 || this.a.d(j - 1) != (byte) 13) {
                r0 = d(j);
                b(1);
                return r0;
            } else {
                r0 = d(j - 1);
                b(2);
                return r0;
            }
        } while (this.b.b(this.a, 2048) != -1);
        if (z) {
            throw new EOFException();
        } else if (this.a.b != 0) {
            return d(this.a.b);
        } else {
            return null;
        }
    }

    public short g() throws IOException {
        a(2);
        return this.a.g();
    }

    public int h() throws IOException {
        a(2);
        return this.a.h();
    }

    public int i() throws IOException {
        a(4);
        return this.a.i();
    }

    public int j() throws IOException {
        a(4);
        return this.a.j();
    }

    public void b(long j) throws IOException {
        a();
        while (j > 0) {
            if (this.a.b == 0 && this.b.b(this.a, 2048) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.a.l());
            this.a.b(min);
            j -= min;
        }
    }

    public long a(byte b) throws IOException {
        a();
        long j = 0;
        do {
            j = this.a.a(b, j);
            if (j != -1) {
                return j;
            }
            j = this.a.b;
        } while (this.b.b(this.a, 2048) != -1);
        throw new EOFException();
    }

    public InputStream k() {
        return new InputStream(this) {
            final /* synthetic */ ea a;

            {
                this.a = r1;
            }

            public int read() throws IOException {
                a();
                if (this.a.a.b == 0 && this.a.b.b(this.a.a, 2048) == -1) {
                    return -1;
                }
                return this.a.a.f() & 255;
            }

            public int read(byte[] data, int offset, int byteCount) throws IOException {
                a();
                ef.a((long) data.length, (long) offset, (long) byteCount);
                if (this.a.a.b == 0 && this.a.b.b(this.a.a, 2048) == -1) {
                    return -1;
                }
                return this.a.a.b(data, offset, byteCount);
            }

            public int available() throws IOException {
                a();
                return (int) Math.min(this.a.a.b, 2147483647L);
            }

            public void close() throws IOException {
                this.a.close();
            }

            public String toString() {
                return this.a + ".inputStream()";
            }

            private void a() throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
            }
        };
    }

    public void close() throws IOException {
        if (!this.c) {
            this.c = true;
            this.b.close();
            this.a.o();
        }
    }

    public String toString() {
        return "buffer(" + this.b + ")";
    }

    private void a() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
    }
}
