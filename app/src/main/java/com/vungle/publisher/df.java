package com.vungle.publisher;

/* compiled from: vungle */
final class df {
    final Object a;
    final dd b;
    final int c = 0;
    volatile boolean d = true;

    df(Object obj, dd ddVar) {
        this.a = obj;
        this.b = ddVar;
    }

    public final boolean equals(Object other) {
        if (!(other instanceof df)) {
            return false;
        }
        df dfVar = (df) other;
        if (this.a == dfVar.a && this.b.equals(dfVar.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() + this.b.d.hashCode();
    }
}
