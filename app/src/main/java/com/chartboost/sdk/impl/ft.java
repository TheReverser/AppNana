package com.chartboost.sdk.impl;

import java.io.Serializable;

public class ft implements Serializable {
    public boolean equals(Object o) {
        return o instanceof ft;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "MaxKey";
    }
}
