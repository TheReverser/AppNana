package com.chartboost.sdk.impl;

import com.facebook.BuildConfig;
import java.io.IOException;
import java.net.ProtocolException;

public final class dn {
    private final String a;
    private final int b;
    private final int c;
    private final String d;

    public dn(String str) throws IOException {
        int charAt;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            charAt = str.charAt(7) - 48;
            if (charAt < 0 || charAt > 9) {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else if (str.startsWith("ICY ")) {
            charAt = 0;
            i = 4;
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        if (str.length() < i + 3) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            String str2;
            int parseInt = Integer.parseInt(str.substring(i, i + 3));
            String str3 = BuildConfig.VERSION_NAME;
            if (str.length() <= i + 3) {
                str2 = str3;
            } else if (str.charAt(i + 3) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            } else {
                str2 = str.substring(i + 4);
            }
            this.d = str2;
            this.c = parseInt;
            this.a = str;
            this.b = charAt;
        } catch (NumberFormatException e) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b != -1 ? this.b : 1;
    }

    public int c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }
}
