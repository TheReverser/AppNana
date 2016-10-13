package com.chartboost.sdk.impl;

enum ek {
    SPDY_SYN_STREAM,
    SPDY_REPLY,
    SPDY_HEADERS,
    HTTP_20_HEADERS;

    public boolean a() {
        return this == SPDY_REPLY || this == SPDY_HEADERS;
    }

    public boolean b() {
        return this == SPDY_SYN_STREAM;
    }

    public boolean c() {
        return this == SPDY_HEADERS;
    }

    public boolean d() {
        return this == SPDY_REPLY;
    }
}
