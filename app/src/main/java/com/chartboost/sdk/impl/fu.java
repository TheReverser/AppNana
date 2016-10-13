package com.chartboost.sdk.impl;

import java.io.Serializable;

public class fu implements Serializable {
    public boolean equals(Object o) {
        return o instanceof fu;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "MinKey";
    }
}
