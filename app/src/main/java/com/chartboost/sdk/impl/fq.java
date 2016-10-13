package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Arrays;

public class fq implements Serializable {
    final byte a;
    final byte[] b;

    public byte a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof fq)) {
            return false;
        }
        fq fqVar = (fq) o;
        if (this.a != fqVar.a) {
            return false;
        }
        if (Arrays.equals(this.b, fqVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.b != null ? Arrays.hashCode(this.b) : 0) + (this.a * 31);
    }
}
