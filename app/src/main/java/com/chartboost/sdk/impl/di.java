package com.chartboost.sdk.impl;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public final class di {
    private final dg a;
    private final dn b;
    private final cf c;
    private final cw d;
    private final a e;
    private final di f;
    private volatile c g;
    private volatile cc h;

    public static abstract class a implements Closeable {
        private ee a;

        public abstract InputStream a();

        public ee b() {
            ee eeVar = this.a;
            if (eeVar != null) {
                return eeVar;
            }
            eeVar = dy.a(a());
            this.a = eeVar;
            return eeVar;
        }

        public void close() throws IOException {
            a().close();
        }
    }

    public static class b {
        private dg a;
        private dn b;
        private cf c;
        private com.chartboost.sdk.impl.cw.a d;
        private a e;
        private di f;

        public b() {
            this.d = new com.chartboost.sdk.impl.cw.a();
        }

        private b(di diVar) {
            this.a = diVar.a;
            this.b = diVar.b;
            this.c = diVar.c;
            this.d = diVar.d.b();
            this.e = diVar.e;
            this.f = diVar.f;
        }

        public b a(dg dgVar) {
            this.a = dgVar;
            return this;
        }

        public b a(dn dnVar) {
            if (dnVar == null) {
                throw new IllegalArgumentException("statusLine == null");
            }
            this.b = dnVar;
            return this;
        }

        public b a(String str) {
            try {
                return a(new dn(str));
            } catch (Throwable e) {
                throw new IllegalArgumentException(e);
            }
        }

        public b a(cf cfVar) {
            this.c = cfVar;
            return this;
        }

        public b a(String str, String str2) {
            this.d.b(str, str2);
            return this;
        }

        public b b(String str, String str2) {
            this.d.a(str, str2);
            return this;
        }

        public b b(String str) {
            this.d.b(str);
            return this;
        }

        public b a(cw cwVar) {
            this.d = cwVar.b();
            return this;
        }

        public b a(a aVar) {
            this.e = aVar;
            return this;
        }

        public b a(ck ckVar) {
            return a(df.d, ckVar + " " + this.b.c());
        }

        public di a() {
            if (this.a == null) {
                throw new IllegalStateException("request == null");
            } else if (this.b != null) {
                return new di();
            } else {
                throw new IllegalStateException("statusLine == null");
            }
        }
    }

    private static class c {
        Date a;
        private Set<String> b;

        private c(cw cwVar) {
            this.b = Collections.emptySet();
            for (int i = 0; i < cwVar.a(); i++) {
                String a = cwVar.a(i);
                String b = cwVar.b(i);
                if ("Last-Modified".equalsIgnoreCase(a)) {
                    this.a = cz.a(b);
                } else if ("Vary".equalsIgnoreCase(a)) {
                    if (this.b.isEmpty()) {
                        this.b = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                    }
                    for (String trim : b.split(",")) {
                        this.b.add(trim.trim());
                    }
                }
            }
        }
    }

    private di(b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d.a();
        this.e = bVar.e;
        this.f = bVar.f;
    }

    public dg a() {
        return this.a;
    }

    public String b() {
        return this.b.a();
    }

    public int c() {
        return this.b.c();
    }

    public String d() {
        return this.b.d();
    }

    public int e() {
        return this.b.b();
    }

    public cf f() {
        return this.c;
    }

    public String a(String str) {
        return a(str, null);
    }

    public String a(String str, String str2) {
        String a = this.d.a(str);
        return a != null ? a : str2;
    }

    public cw g() {
        return this.d;
    }

    public a h() {
        return this.e;
    }

    public b i() {
        return new b();
    }

    public boolean a(di diVar) {
        if (diVar.c() == 304) {
            return true;
        }
        c k = diVar.k();
        if (k().a == null || k.a == null || k.a.getTime() >= k().a.getTime()) {
            return false;
        }
        return true;
    }

    private c k() {
        c cVar = this.g;
        if (cVar != null) {
            return cVar;
        }
        cVar = new c(this.d);
        this.g = cVar;
        return cVar;
    }

    public cc j() {
        cc ccVar = this.h;
        if (ccVar != null) {
            return ccVar;
        }
        ccVar = cc.a(this.d);
        this.h = ccVar;
        return ccVar;
    }
}
