package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.dg.b;
import java.io.IOException;
import java.net.URL;

public final class cn {
    final String a;
    final int b;
    final String c;
    final String d;

    public cn(String str, int i, String str2, String str3) {
        if (str == null) {
            throw new NullPointerException("host == null");
        } else if (str2 == null) {
            throw new NullPointerException("userAgent == null");
        } else {
            this.a = str;
            this.b = i;
            this.c = str2;
            this.d = str3;
        }
    }

    String a() {
        return "CONNECT " + this.a + ":" + this.b + " HTTP/1.1";
    }

    dg b() throws IOException {
        b a = new b().a(new URL("https", this.a, this.b, "/"));
        a.a("Host", this.b == cs.a("https") ? this.a : this.a + ":" + this.b);
        a.a("User-Agent", this.c);
        if (this.d != null) {
            a.a("Proxy-Authorization", this.d);
        }
        a.a("Proxy-Connection", "Keep-Alive");
        return a.a();
    }
}
