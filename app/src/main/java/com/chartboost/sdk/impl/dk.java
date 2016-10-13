package com.chartboost.sdk.impl;

import java.io.IOException;
import java.net.ProtocolException;

final class dk implements ed {
    private boolean a;
    private final int b;
    private final dx c;

    public dk(int i) {
        this.c = new dx();
        this.b = i;
    }

    public dk() {
        this(-1);
    }

    public void close() throws IOException {
        if (!this.a) {
            this.a = true;
            if (this.c.l() < ((long) this.b)) {
                throw new ProtocolException("content-length promised " + this.b + " bytes, but received " + this.c.l());
            }
        }
    }

    public void a(dx dxVar, long j) throws IOException {
        if (this.a) {
            throw new IllegalStateException("closed");
        }
        cs.a(dxVar.l(), 0, j);
        if (this.b == -1 || this.c.l() <= ((long) this.b) - j) {
            this.c.a(dxVar, j);
            return;
        }
        throw new ProtocolException("exceeded content-length limit of " + this.b + " bytes");
    }

    public void a() throws IOException {
    }

    public long b() throws IOException {
        return this.c.l();
    }

    public void a(dq dqVar) throws IOException {
        dqVar.a(this.c.p(), this.c.l());
    }
}
