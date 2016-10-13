package com.chartboost.sdk.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class z extends ByteArrayOutputStream {
    private final u a;

    public z(u uVar, int i) {
        this.a = uVar;
        this.buf = this.a.a(Math.max(i, 256));
    }

    public void close() throws IOException {
        this.a.a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.a.a(this.buf);
    }

    private void a(int i) {
        if (this.count + i > this.buf.length) {
            Object a = this.a.a((this.count + i) * 2);
            System.arraycopy(this.buf, 0, a, 0, this.count);
            this.a.a(this.buf);
            this.buf = a;
        }
    }

    public synchronized void write(byte[] buffer, int offset, int len) {
        a(len);
        super.write(buffer, offset, len);
    }

    public synchronized void write(int oneByte) {
        a(1);
        super.write(oneByte);
    }
}
