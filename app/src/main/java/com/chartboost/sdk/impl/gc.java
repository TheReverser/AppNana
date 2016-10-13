package com.chartboost.sdk.impl;

import java.util.HashMap;
import java.util.Map;

abstract class gc<K, V> extends fx<K, V, Map<K, V>> {

    public static class a<K, V> {
        private com.chartboost.sdk.impl.fx.h.a a = com.chartboost.sdk.impl.fx.h.a.STABLE;
        private final Map<K, V> b = new HashMap();

        a() {
        }

        public gc<K, V> a() {
            return new b(this.b, this.a);
        }
    }

    static class b<K, V> extends gc<K, V> {
        b(Map<? extends K, ? extends V> map, com.chartboost.sdk.impl.fx.h.a aVar) {
            super(map, aVar);
        }

        public <N extends Map<? extends K, ? extends V>> Map<K, V> a(N n) {
            return new HashMap(n);
        }
    }

    public static <K, V> a<K, V> b() {
        return new a();
    }

    public static <K, V> gc<K, V> c() {
        return b().a();
    }

    protected gc(Map<? extends K, ? extends V> map, com.chartboost.sdk.impl.fx.h.a aVar) {
        super(map, aVar);
    }
}
