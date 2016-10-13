package com.chartboost.sdk.impl;

public enum cj {
    HTTP_2("HTTP-draft-09/2.0", true),
    SPDY_3("spdy/3.1", true),
    HTTP_11("http/1.1", false);
    
    public final ds d;
    public final boolean e;

    private cj(String str, boolean z) {
        this.d = ds.a(str);
        this.e = z;
    }
}
