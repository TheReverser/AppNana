package com.vungle.publisher;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* compiled from: vungle */
final class cy extends Handler {
    final db a = new db();
    boolean b;
    private final int c = 10;
    private final cw d;

    cy(cw cwVar, Looper looper) {
        super(looper);
        this.d = cwVar;
    }

    public final void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                da a = this.a.a();
                if (a == null) {
                    synchronized (this) {
                        a = this.a.a();
                        if (a == null) {
                            this.b = false;
                            this.b = false;
                            return;
                        }
                    }
                }
                this.d.a(a);
            } while (SystemClock.uptimeMillis() - uptimeMillis < ((long) this.c));
            if (sendMessage(obtainMessage())) {
                this.b = true;
                return;
            }
            throw new cx("Could not send handler message");
        } catch (Throwable th) {
            this.b = false;
        }
    }
}
