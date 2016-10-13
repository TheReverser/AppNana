package com.chartboost.sdk.impl;

public final class ej {
    public static final ds a = ds.a(":status");
    public static final ds b = ds.a(":method");
    public static final ds c = ds.a(":path");
    public static final ds d = ds.a(":scheme");
    public static final ds e = ds.a(":authority");
    public static final ds f = ds.a(":host");
    public static final ds g = ds.a(":version");
    public final ds h;
    public final ds i;
    final int j;

    public ej(String str, String str2) {
        this(ds.a(str), ds.a(str2));
    }

    public ej(ds dsVar, String str) {
        this(dsVar, ds.a(str));
    }

    public ej(ds dsVar, ds dsVar2) {
        this.h = dsVar;
        this.i = dsVar2;
        this.j = (dsVar.e() + 32) + dsVar2.e();
    }

    public boolean equals(Object other) {
        if (!(other instanceof ej)) {
            return false;
        }
        ej ejVar = (ej) other;
        if (this.h.equals(ejVar.h) && this.i.equals(ejVar.i)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.h.hashCode() + 527) * 31) + this.i.hashCode();
    }

    public String toString() {
        return String.format("%s: %s", new Object[]{this.h.a(), this.i.a()});
    }
}
