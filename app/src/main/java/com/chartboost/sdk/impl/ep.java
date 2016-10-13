package com.chartboost.sdk.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

class ep {
    private final dw a;
    private int b;
    private final dr c = dy.a(this.a);

    static /* synthetic */ int a(ep epVar, long j) {
        int i = (int) (((long) epVar.b) - j);
        epVar.b = i;
        return i;
    }

    public ep(final dr drVar) {
        this.a = new dw(new ee(this) {
            final /* synthetic */ ep b;

            public long b(dx dxVar, long j) throws IOException {
                if (this.b.b == 0) {
                    return -1;
                }
                long b = drVar.b(dxVar, Math.min(j, (long) this.b.b));
                if (b == -1) {
                    return -1;
                }
                ep.a(this.b, b);
                return b;
            }

            public void close() throws IOException {
                drVar.close();
            }
        }, new Inflater(this) {
            final /* synthetic */ ep a;

            {
                this.a = r1;
            }

            public int inflate(byte[] buffer, int offset, int count) throws DataFormatException {
                int inflate = super.inflate(buffer, offset, count);
                if (inflate != 0 || !needsDictionary()) {
                    return inflate;
                }
                setDictionary(et.a);
                return super.inflate(buffer, offset, count);
            }
        });
    }

    public List<ej> a(int i) throws IOException {
        this.b += i;
        int i2 = this.c.i();
        if (i2 < 0) {
            throw new IOException("numberOfPairs < 0: " + i2);
        } else if (i2 > 1024) {
            throw new IOException("numberOfPairs > 1024: " + i2);
        } else {
            List<ej> arrayList = new ArrayList(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                ds d = b().d();
                ds b = b();
                if (d.e() == 0) {
                    throw new IOException("name.size == 0");
                }
                arrayList.add(new ej(d, b));
            }
            c();
            return arrayList;
        }
    }

    private ds b() throws IOException {
        return this.c.c((long) this.c.i());
    }

    private void c() throws IOException {
        if (this.b > 0) {
            this.a.a();
            if (this.b != 0) {
                throw new IOException("compressedLimit > 0: " + this.b);
            }
        }
    }

    public void a() throws IOException {
        this.c.close();
    }
}
