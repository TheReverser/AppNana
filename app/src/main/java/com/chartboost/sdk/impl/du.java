package com.chartboost.sdk.impl;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class du implements ed {
    private final dq a;
    private final Deflater b;
    private boolean c;

    public du(ed edVar, Deflater deflater) {
        this.a = dy.a(edVar);
        this.b = deflater;
    }

    public void a(dx dxVar, long j) throws IOException {
        ef.a(dxVar.b, 0, j);
        while (j > 0) {
            eb ebVar = dxVar.a;
            int min = (int) Math.min(j, (long) (ebVar.c - ebVar.b));
            this.b.setInput(ebVar.a, ebVar.b, min);
            a(false);
            dxVar.b -= (long) min;
            ebVar.b += min;
            if (ebVar.b == ebVar.c) {
                dxVar.a = ebVar.a();
                ec.a.a(ebVar);
            }
            j -= (long) min;
        }
    }

    @IgnoreJRERequirement
    private void a(boolean z) throws IOException {
        dx b = this.a.b();
        while (true) {
            eb f = b.f(1);
            int deflate = z ? this.b.deflate(f.a, f.c, 2048 - f.c, 2) : this.b.deflate(f.a, f.c, 2048 - f.c);
            if (deflate > 0) {
                f.c += deflate;
                b.b += (long) deflate;
                this.a.c();
            } else if (this.b.needsInput()) {
                return;
            }
        }
    }

    public void a() throws IOException {
        a(true);
        this.a.a();
    }

    public void close() throws IOException {
        if (!this.c) {
            Throwable th;
            Throwable th2 = null;
            try {
                this.b.finish();
                a(false);
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                this.b.end();
                th3 = th2;
            } catch (Throwable th4) {
                th3 = th4;
                if (th2 != null) {
                    th3 = th2;
                }
            }
            try {
                this.a.close();
            } catch (Throwable th22) {
                if (th3 == null) {
                    th3 = th22;
                }
            }
            this.c = true;
            if (th3 != null) {
                ef.a(th3);
            }
        }
    }

    public String toString() {
        return "DeflaterSink(" + this.a + ")";
    }
}
