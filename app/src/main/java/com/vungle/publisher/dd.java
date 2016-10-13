package com.vungle.publisher;

import java.lang.reflect.Method;

/* compiled from: vungle */
final class dd {
    final Method a;
    final dg b;
    final Class<?> c;
    String d;

    dd(Method method, dg dgVar, Class<?> cls) {
        this.a = method;
        this.b = dgVar;
        this.c = cls;
    }

    public final boolean equals(Object other) {
        if (!(other instanceof dd)) {
            return false;
        }
        a();
        dd ddVar = (dd) other;
        ddVar.a();
        return this.d.equals(ddVar.d);
    }

    private synchronized void a() {
        if (this.d == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(this.a.getDeclaringClass().getName());
            stringBuilder.append('#').append(this.a.getName());
            stringBuilder.append('(').append(this.c.getName());
            this.d = stringBuilder.toString();
        }
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
