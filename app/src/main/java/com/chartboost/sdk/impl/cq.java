package com.chartboost.sdk.impl;

public abstract class cq implements Runnable {
    private final String a;

    protected abstract void a();

    public cq(String str, Object... objArr) {
        this.a = String.format(str, objArr);
    }

    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.a);
        try {
            a();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
