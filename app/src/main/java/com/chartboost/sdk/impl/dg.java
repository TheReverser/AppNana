package com.chartboost.sdk.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class dg {
    private final URL a;
    private final String b;
    private final cw c;
    private final a d;
    private final Object e;
    private volatile c f;
    private volatile URI g;
    private volatile cc h;

    public static abstract class a {
    }

    public static class b {
        private URL a;
        private String b;
        private com.chartboost.sdk.impl.cw.a c;
        private a d;
        private Object e;

        public b() {
            this.b = "GET";
            this.c = new com.chartboost.sdk.impl.cw.a();
        }

        private b(dg dgVar) {
            this.a = dgVar.a;
            this.b = dgVar.b;
            this.d = dgVar.d;
            this.e = dgVar.e;
            this.c = dgVar.c.b();
        }

        public b a(URL url) {
            if (url == null) {
                throw new IllegalArgumentException("url == null");
            }
            this.a = url;
            return this;
        }

        public b a(String str, String str2) {
            this.c.b(str, str2);
            return this;
        }

        public b b(String str, String str2) {
            this.c.a(str, str2);
            return this;
        }

        public b a(String str) {
            return a("User-Agent", str);
        }

        public b a(String str, a aVar) {
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("method == null || method.length() == 0");
            }
            this.b = str;
            this.d = aVar;
            return this;
        }

        public dg a() {
            if (this.a != null) {
                return new dg();
            }
            throw new IllegalStateException("url == null");
        }
    }

    private static class c {
        private String a;
        private String b;

        public c(cw cwVar) {
            for (int i = 0; i < cwVar.a(); i++) {
                String a = cwVar.a(i);
                String b = cwVar.b(i);
                if ("User-Agent".equalsIgnoreCase(a)) {
                    this.a = b;
                } else if ("Proxy-Authorization".equalsIgnoreCase(a)) {
                    this.b = b;
                }
            }
        }
    }

    private dg(b bVar) {
        Object e;
        this.a = bVar.a;
        this.b = bVar.b;
        this.c = bVar.c.a();
        this.d = bVar.d;
        if (bVar.e != null) {
            e = bVar.e;
        } else {
            dg dgVar = this;
        }
        this.e = e;
    }

    public URL a() {
        return this.a;
    }

    public URI b() throws IOException {
        try {
            URI uri = this.g;
            if (uri == null) {
                uri = cr.a().a(this.a);
                this.g = uri;
            }
            return uri;
        } catch (URISyntaxException e) {
            throw new IOException(e.getMessage());
        }
    }

    public String c() {
        return this.b;
    }

    public cw d() {
        return this.c;
    }

    public String a(String str) {
        return this.c.a(str);
    }

    public a e() {
        return this.d;
    }

    public b f() {
        return new b();
    }

    public cw g() {
        return this.c;
    }

    public String h() {
        return l().a;
    }

    public String i() {
        return l().b;
    }

    private c l() {
        c cVar = this.f;
        if (cVar != null) {
            return cVar;
        }
        cVar = new c(this.c);
        this.f = cVar;
        return cVar;
    }

    public cc j() {
        cc ccVar = this.h;
        if (ccVar != null) {
            return ccVar;
        }
        ccVar = cc.a(this.c);
        this.h = ccVar;
        return ccVar;
    }

    public boolean k() {
        return a().getProtocol().equals("https");
    }
}
