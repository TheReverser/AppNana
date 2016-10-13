package com.chartboost.sdk.impl;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class dv implements ee {
    private int a = 0;
    private final dr b;
    private final Inflater c = new Inflater(true);
    private final dw d;
    private final CRC32 e = new CRC32();

    public dv(ee eeVar) throws IOException {
        this.b = dy.a(eeVar);
        this.d = new dw(this.b, this.c);
    }

    public long b(dx dxVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j == 0) {
            return 0;
        } else {
            if (this.a == 0) {
                a();
                this.a = 1;
            }
            if (this.a == 1) {
                long j2 = dxVar.b;
                long b = this.d.b(dxVar, j);
                if (b != -1) {
                    a(dxVar, j2, b);
                    return b;
                }
                this.a = 2;
            }
            if (this.a == 2) {
                b();
                this.a = 3;
                if (!this.b.e()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private void a() throws IOException {
        this.b.a(10);
        byte d = this.b.b().d(3);
        byte b = ((d >> 1) & 1) == 1 ? (byte) 1 : (byte) 0;
        if (b != (byte) 0) {
            a(this.b.b(), 0, 10);
        }
        a("ID1ID2", 8075, this.b.g());
        this.b.b(8);
        if (((d >> 2) & 1) == 1) {
            this.b.a(2);
            if (b != (byte) 0) {
                a(this.b.b(), 0, 2);
            }
            int h = this.b.b().h() & 65535;
            this.b.a((long) h);
            if (b != (byte) 0) {
                a(this.b.b(), 0, (long) h);
            }
            this.b.b((long) h);
        }
        if (((d >> 3) & 1) == 1) {
            long a = this.b.a((byte) 0);
            if (b != (byte) 0) {
                a(this.b.b(), 0, a + 1);
            }
            this.b.b(a + 1);
        }
        if (((d >> 4) & 1) == 1) {
            long a2 = this.b.a((byte) 0);
            if (b != (byte) 0) {
                a(this.b.b(), 0, a2 + 1);
            }
            this.b.b(a2 + 1);
        }
        if (b != (byte) 0) {
            a("FHCRC", this.b.h(), (short) ((int) this.e.getValue()));
            this.e.reset();
        }
    }

    private void b() throws IOException {
        a("CRC", this.b.j(), (int) this.e.getValue());
        a("ISIZE", this.b.j(), this.c.getTotalOut());
    }

    public void close() throws IOException {
        this.d.close();
    }

    private void a(dx dxVar, long j, long j2) {
        eb ebVar = dxVar.a;
        long j3 = j2;
        while (j3 > 0) {
            int i = ebVar.c - ebVar.b;
            if (j < ((long) i)) {
                int min = (int) Math.min(j3, ((long) i) - j);
                this.e.update(ebVar.a, (int) (((long) ebVar.b) + j), min);
                j3 -= (long) min;
            }
            j -= (long) i;
            ebVar = ebVar.d;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
