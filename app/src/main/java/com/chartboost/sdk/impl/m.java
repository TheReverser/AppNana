package com.chartboost.sdk.impl;

public class m<T> {
    public final T a;
    public final com.chartboost.sdk.impl.b.a b;
    public final r c;
    public boolean d;

    public interface a {
        void a(r rVar);
    }

    public static <T> m<T> a(T t, com.chartboost.sdk.impl.b.a aVar) {
        return new m(t, aVar);
    }

    public static <T> m<T> a(r rVar) {
        return new m(rVar);
    }

    public boolean a() {
        return this.c == null;
    }

    private m(T t, com.chartboost.sdk.impl.b.a aVar) {
        this.d = false;
        this.a = t;
        this.b = aVar;
        this.c = null;
    }

    private m(r rVar) {
        this.d = false;
        this.a = null;
        this.b = null;
        this.c = rVar;
    }
}
