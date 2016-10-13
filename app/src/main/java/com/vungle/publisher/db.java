package com.vungle.publisher;

/* compiled from: vungle */
final class db {
    private da a;
    private da b;

    db() {
    }

    final synchronized void a(da daVar) {
        if (daVar == null) {
            throw new NullPointerException("null cannot be enqueued");
        }
        if (this.b != null) {
            this.b.c = daVar;
            this.b = daVar;
        } else if (this.a == null) {
            this.b = daVar;
            this.a = daVar;
        } else {
            throw new IllegalStateException("Head present, but no tail");
        }
        notifyAll();
    }

    final synchronized da a() {
        da daVar;
        daVar = this.a;
        if (this.a != null) {
            this.a = this.a.c;
            if (this.a == null) {
                this.b = null;
            }
        }
        return daVar;
    }

    final synchronized da b() throws InterruptedException {
        if (this.a == null) {
            wait(1000);
        }
        return a();
    }
}
