package com.vungle.publisher;

/* compiled from: vungle */
final class cu implements Runnable {
    final db a = new db();
    private final cw b;

    cu(cw cwVar) {
        this.b = cwVar;
    }

    public final void run() {
        da a = this.a.a();
        if (a == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.b.a(a);
    }
}
