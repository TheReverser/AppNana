package com.chartboost.sdk.impl;

import com.facebook.BuildConfig;

public class fh implements fe {
    static final String[] a = new String[128];
    private byte[] b = new byte[1024];
    private byte[] c = new byte[1024];
    private fn d = new fn();

    static {
        a((byte) 48, (byte) 57);
        a((byte) 97, (byte) 122);
        a((byte) 65, (byte) 90);
    }

    static void a(byte b, byte b2) {
        while (b < b2) {
            a[b] = BuildConfig.VERSION_NAME + ((char) b);
            b = (byte) (b + 1);
        }
    }
}
