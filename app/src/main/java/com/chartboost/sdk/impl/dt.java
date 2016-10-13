package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.InterruptedIOException;

public class dt {
    public static final dt a = new dt() {
        public boolean a() {
            return false;
        }
    };
    private long b;

    public boolean a() {
        return System.nanoTime() - this.b >= 0;
    }

    public final void b() throws IOException {
        if (a()) {
            throw new IOException("Deadline reached");
        } else if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
    }
}
