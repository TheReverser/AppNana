package com.chartboost.sdk.impl;

import java.io.Serializable;

public class fr implements Serializable {
    final String a;

    public String a() {
        return this.a;
    }

    public boolean equals(Object o) {
        if (!(o instanceof fr)) {
            return false;
        }
        return this.a.equals(((fr) o).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return a();
    }
}
