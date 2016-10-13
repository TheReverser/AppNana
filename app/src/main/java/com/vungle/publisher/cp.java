package com.vungle.publisher;

import java.lang.reflect.Array;

/* compiled from: vungle */
public final class cp<E> {
    private final Class<E> a;
    private final E[] b = a(10);
    private int c;
    private int d;

    public cp(Class<E> cls) {
        this.a = cls;
    }

    private E[] b() {
        Object a;
        synchronized (this.b) {
            int length = this.b.length;
            int c = c();
            a = a(c);
            if (this.c + c > length) {
                length -= this.c;
                System.arraycopy(this.b, this.c, a, 0, length);
                System.arraycopy(this.b, 0, a, length, c - length);
            } else {
                System.arraycopy(this.b, this.c, a, 0, c);
            }
        }
        return a;
    }

    public final E[] a() {
        E[] b;
        synchronized (this.b) {
            b = b();
            synchronized (this.b) {
                this.d = 0;
            }
        }
        return b;
    }

    private int c() {
        int i;
        synchronized (this.b) {
            i = this.d;
        }
        return i;
    }

    private E[] a(int i) {
        return (Object[]) Array.newInstance(this.a, i);
    }
}
