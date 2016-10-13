package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class dy {
    public static dr a(ee eeVar) {
        return new ea(eeVar);
    }

    public static dq a(ed edVar) {
        return new dz(edVar);
    }

    public static void a(dx dxVar, long j, long j2, OutputStream outputStream) throws IOException {
        ef.a(dxVar.b, j, j2);
        eb ebVar = dxVar.a;
        while (j >= ((long) (ebVar.c - ebVar.b))) {
            j -= (long) (ebVar.c - ebVar.b);
            ebVar = ebVar.d;
        }
        while (j2 > 0) {
            int i = (int) (((long) ebVar.b) + j);
            int min = (int) Math.min((long) (ebVar.c - i), j2);
            outputStream.write(ebVar.a, i, min);
            j2 -= (long) min;
            j = 0;
        }
    }

    public static ed a(final OutputStream outputStream) {
        return new ed() {
            private dt b = dt.a;

            public void a(dx dxVar, long j) throws IOException {
                ef.a(dxVar.b, 0, j);
                while (j > 0) {
                    this.b.b();
                    eb ebVar = dxVar.a;
                    int min = (int) Math.min(j, (long) (ebVar.c - ebVar.b));
                    outputStream.write(ebVar.a, ebVar.b, min);
                    ebVar.b += min;
                    j -= (long) min;
                    dxVar.b -= (long) min;
                    if (ebVar.b == ebVar.c) {
                        dxVar.a = ebVar.a();
                        ec.a.a(ebVar);
                    }
                }
            }

            public void a() throws IOException {
                outputStream.flush();
            }

            public void close() throws IOException {
                outputStream.close();
            }

            public String toString() {
                return "sink(" + outputStream + ")";
            }
        };
    }

    public static ee a(final InputStream inputStream) {
        return new ee() {
            private dt b = dt.a;

            public long b(dx dxVar, long j) throws IOException {
                if (j < 0) {
                    throw new IllegalArgumentException("byteCount < 0: " + j);
                }
                this.b.b();
                eb f = dxVar.f(1);
                int read = inputStream.read(f.a, f.c, (int) Math.min(j, (long) (2048 - f.c)));
                if (read == -1) {
                    return -1;
                }
                f.c += read;
                dxVar.b += (long) read;
                return (long) read;
            }

            public void close() throws IOException {
                inputStream.close();
            }

            public String toString() {
                return "source(" + inputStream + ")";
            }
        };
    }
}
