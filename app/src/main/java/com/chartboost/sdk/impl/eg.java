package com.chartboost.sdk.impl;

public enum eg {
    NO_ERROR(0, -1, 0),
    PROTOCOL_ERROR(1, 1, 1),
    INVALID_STREAM(1, 2, -1),
    UNSUPPORTED_VERSION(1, 4, -1),
    STREAM_IN_USE(1, 8, -1),
    STREAM_ALREADY_CLOSED(1, 9, -1),
    INTERNAL_ERROR(2, 6, 2),
    FLOW_CONTROL_ERROR(3, 7, -1),
    STREAM_CLOSED(5, -1, -1),
    FRAME_TOO_LARGE(6, 11, -1),
    REFUSED_STREAM(7, 3, -1),
    CANCEL(8, 5, -1),
    COMPRESSION_ERROR(9, -1, -1),
    INVALID_CREDENTIALS(-1, 10, -1);
    
    public final int o;
    public final int p;
    public final int q;

    private eg(int i, int i2, int i3) {
        this.o = i;
        this.p = i2;
        this.q = i3;
    }

    public static eg a(int i) {
        for (eg egVar : values()) {
            if (egVar.p == i) {
                return egVar;
            }
        }
        return null;
    }

    public static eg b(int i) {
        for (eg egVar : values()) {
            if (egVar.o == i) {
                return egVar;
            }
        }
        return null;
    }

    public static eg c(int i) {
        for (eg egVar : values()) {
            if (egVar.q == i) {
                return egVar;
            }
        }
        return null;
    }
}
