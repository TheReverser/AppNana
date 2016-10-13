package com.chartboost.sdk.impl;

import java.util.List;
import java.util.Map;

public class ga<T> {
    private final Map<Class<?>, T> a = gc.c();
    private final Map<Class<?>, T> b = gb.a(new a());

    private final class a implements gd<Class<?>, T> {
        final /* synthetic */ ga a;

        private a(ga gaVar) {
            this.a = gaVar;
        }

        public T a(Class<?> cls) {
            for (Class cls2 : ga.a((Class) cls)) {
                T t = this.a.a.get(cls2);
                if (t != null) {
                    return t;
                }
            }
            return null;
        }
    }

    public static <T> List<Class<?>> a(Class<T> cls) {
        return fz.a(cls);
    }

    public T a(Object obj) {
        return this.b.get(obj);
    }

    public T a(Class<?> cls, T t) {
        try {
            T put = this.a.put(cls, t);
            return put;
        } finally {
            this.b.clear();
        }
    }

    public int a() {
        return this.a.size();
    }
}
