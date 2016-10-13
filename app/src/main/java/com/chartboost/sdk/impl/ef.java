package com.chartboost.sdk.impl;

final class ef {
    public static void a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static int a(short s) {
        int i = 65535 & s;
        return ((i & 255) << 8) | ((65280 & i) >>> 8);
    }

    public static int a(int i) {
        return ((((-16777216 & i) >>> 24) | ((16711680 & i) >>> 8)) | ((65280 & i) << 8)) | ((i & 255) << 24);
    }

    public static void a(Throwable th) {
        b(th);
    }

    private static <T extends Throwable> void b(Throwable th) throws Throwable {
        throw th;
    }
}
