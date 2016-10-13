package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Date;

public class fp implements Serializable, Comparable<fp> {
    static final boolean a = Boolean.getBoolean("DEBUG.DBTIMESTAMP");
    final int b = 0;
    final Date c = null;

    public /* synthetic */ int compareTo(Object x0) {
        return a((fp) x0);
    }

    public int a() {
        if (this.c == null) {
            return 0;
        }
        return (int) (this.c.getTime() / 1000);
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return "TS time:" + this.c + " inc:" + this.b;
    }

    public int a(fp fpVar) {
        if (a() != fpVar.a()) {
            return a() - fpVar.a();
        }
        return b() - fpVar.b();
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + a();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fp)) {
            return false;
        }
        fp fpVar = (fp) obj;
        if (a() == fpVar.a() && b() == fpVar.b()) {
            return true;
        }
        return false;
    }
}
