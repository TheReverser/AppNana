package com.chartboost.sdk.impl;

import com.facebook.BuildConfig;
import java.util.regex.Pattern;

public class bz {
    private static Pattern a = Pattern.compile("\\s+", 40);

    public static class a extends RuntimeException {
        final String a;

        a(String str) {
            super(str);
            this.a = str;
        }

        public String toString() {
            return this.a;
        }
    }

    public static void a(int i, int i2) {
        if (i != i2) {
            throw new a(BuildConfig.VERSION_NAME + i + " != " + i2);
        }
    }
}
