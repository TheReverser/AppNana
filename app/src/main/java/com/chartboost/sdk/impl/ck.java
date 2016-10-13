package com.chartboost.sdk.impl;

public enum ck {
    CACHE,
    CONDITIONAL_CACHE,
    NETWORK,
    NONE;

    public boolean a() {
        return this == CONDITIONAL_CACHE || this == NETWORK;
    }

    public boolean b() {
        return this == CACHE || this == CONDITIONAL_CACHE;
    }
}
