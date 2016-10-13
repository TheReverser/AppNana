package com.chartboost.sdk.impl;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.Inflater;

public final class dw implements ee {
    private final dr a;
    private final Inflater b;
    private int c;
    private boolean d;

    public dw(ee eeVar, Inflater inflater) {
        this(dy.a(eeVar), inflater);
    }

    dw(dr drVar, Inflater inflater) {
        if (drVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = drVar;
            this.b = inflater;
        }
    }

    public long b(dx dxVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.d) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            boolean a;
            do {
                a = a();
                try {
                    eb f = dxVar.f(1);
                    int inflate = this.b.inflate(f.a, f.c, 2048 - f.c);
                    if (inflate > 0) {
                        f.c += inflate;
                        dxVar.b += (long) inflate;
                        return (long) inflate;
                    } else if (this.b.finished() || this.b.needsDictionary()) {
                        b();
                        return -1;
                    }
                } catch (Throwable e) {
                    IOException iOException = new IOException();
                    iOException.initCause(e);
                    throw iOException;
                }
            } while (!a);
            throw new EOFException("source exhausted prematurely");
        }
    }

    public boolean a() throws IOException {
        if (!this.b.needsInput()) {
            return false;
        }
        b();
        if (this.b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.a.e()) {
            return true;
        } else {
            eb ebVar = this.a.b().a;
            this.c = ebVar.c - ebVar.b;
            this.b.setInput(ebVar.a, ebVar.b, this.c);
            return false;
        }
    }

    private void b() throws IOException {
        if (this.c != 0) {
            int remaining = this.c - this.b.getRemaining();
            this.c -= remaining;
            this.a.b((long) remaining);
        }
    }

    public void close() throws IOException {
        if (!this.d) {
            this.b.end();
            this.d = true;
            this.a.close();
        }
    }
}
