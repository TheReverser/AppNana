package com.vungle.publisher;

import com.vungle.log.Logger;

/* compiled from: vungle */
final class cv implements Runnable {
    final db a = new db();
    volatile boolean b;
    private final cw c;

    cv(cw cwVar) {
        this.c = cwVar;
    }

    public final void run() {
        while (true) {
            try {
                da b = this.a.b();
                if (b == null) {
                    synchronized (this) {
                        b = this.a.a();
                        if (b == null) {
                            this.b = false;
                            this.b = false;
                            return;
                        }
                    }
                }
                this.c.a(b);
            } catch (Throwable e) {
                Logger.w("Event", Thread.currentThread().getName() + " was interruppted", e);
                this.b = false;
                return;
            } catch (Throwable th) {
                this.b = false;
            }
        }
    }
}
