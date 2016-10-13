package com.chartboost.sdk.impl;

public class fs extends fr {
    final fg b;

    public fg b() {
        return this.b;
    }

    public boolean equals(Object o) {
        if (!(o instanceof fs)) {
            return false;
        }
        fs fsVar = (fs) o;
        if (this.a.equals(fsVar.a) && this.b.equals(fsVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }
}
