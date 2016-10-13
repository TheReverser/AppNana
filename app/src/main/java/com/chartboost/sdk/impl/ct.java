package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.di.b;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class ct {
    private static final com.chartboost.sdk.impl.di.a d = new com.chartboost.sdk.impl.di.a() {
        public InputStream a() {
            return cs.c;
        }
    };
    private static final dn e;
    public final dg a;
    public final di b;
    public final ck c;

    public static class a {
        final long a;
        final dg b;
        final di c;
        private Date d;
        private String e;
        private Date f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l = -1;

        public a(long j, dg dgVar, di diVar) {
            this.a = j;
            this.b = dgVar;
            this.c = diVar;
            if (diVar != null) {
                for (int i = 0; i < diVar.g().a(); i++) {
                    String a = diVar.g().a(i);
                    String b = diVar.g().b(i);
                    if ("Date".equalsIgnoreCase(a)) {
                        this.d = cz.a(b);
                        this.e = b;
                    } else if ("Expires".equalsIgnoreCase(a)) {
                        this.h = cz.a(b);
                    } else if ("Last-Modified".equalsIgnoreCase(a)) {
                        this.f = cz.a(b);
                        this.g = b;
                    } else if ("ETag".equalsIgnoreCase(a)) {
                        this.k = b;
                    } else if ("Age".equalsIgnoreCase(a)) {
                        this.l = cv.a(b);
                    } else if (df.b.equalsIgnoreCase(a)) {
                        this.i = Long.parseLong(b);
                    } else if (df.c.equalsIgnoreCase(a)) {
                        this.j = Long.parseLong(b);
                    }
                }
            }
        }

        public ct a() {
            ct b = b();
            if (b.c == ck.CACHE || !this.b.j().i()) {
                return b;
            }
            return new ct(b.a, new b().a(b.a).a(ct.e).a(ck.NONE).a(ct.d).a(), ck.NONE);
        }

        private ct b() {
            long j = 0;
            if (this.c == null) {
                return new ct(this.b, this.c, ck.NETWORK);
            }
            if (this.b.k() && this.c.f() == null) {
                return new ct(this.b, this.c, ck.NETWORK);
            }
            if (!ct.a(this.c, this.b)) {
                return new ct(this.b, this.c, ck.NETWORK);
            }
            cc j2 = this.b.j();
            if (j2.a() || a(this.b)) {
                return new ct(this.b, this.c, ck.NETWORK);
            }
            long toMillis;
            long d = d();
            long c = c();
            if (j2.c() != -1) {
                c = Math.min(c, TimeUnit.SECONDS.toMillis((long) j2.c()));
            }
            if (j2.h() != -1) {
                toMillis = TimeUnit.SECONDS.toMillis((long) j2.h());
            } else {
                toMillis = 0;
            }
            cc j3 = this.c.j();
            if (!(j3.f() || j2.g() == -1)) {
                j = TimeUnit.SECONDS.toMillis((long) j2.g());
            }
            if (j3.a() || d + toMillis >= r4 + c) {
                dg.b f = this.b.f();
                if (this.f != null) {
                    f.a("If-Modified-Since", this.g);
                } else if (this.d != null) {
                    f.a("If-Modified-Since", this.e);
                }
                if (this.k != null) {
                    f.a("If-None-Match", this.k);
                }
                dg a = f.a();
                return new ct(a, this.c, a(a) ? ck.CONDITIONAL_CACHE : ck.NETWORK);
            }
            b a2 = this.c.i().a(ck.CACHE);
            if (toMillis + d >= c) {
                a2.b("Warning", "110 HttpURLConnection \"Response is stale\"");
            }
            if (d > 86400000 && e()) {
                a2.b("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
            }
            return new ct(this.b, a2.a(), ck.CACHE);
        }

        private long c() {
            cc j = this.c.j();
            if (j.c() != -1) {
                return TimeUnit.SECONDS.toMillis((long) j.c());
            }
            long time;
            if (this.h != null) {
                time = this.h.getTime() - (this.d != null ? this.d.getTime() : this.j);
                if (time <= 0) {
                    time = 0;
                }
                return time;
            } else if (this.f == null || this.c.a().a().getQuery() != null) {
                return 0;
            } else {
                time = (this.d != null ? this.d.getTime() : this.i) - this.f.getTime();
                if (time > 0) {
                    return time / 10;
                }
                return 0;
            }
        }

        private long d() {
            long j = 0;
            if (this.d != null) {
                j = Math.max(0, this.j - this.d.getTime());
            }
            if (this.l != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.l));
            }
            return (j + (this.j - this.i)) + (this.a - this.j);
        }

        private boolean e() {
            return this.c.j().c() == -1 && this.h == null;
        }

        private static boolean a(dg dgVar) {
            return (dgVar.a("If-Modified-Since") == null && dgVar.a("If-None-Match") == null) ? false : true;
        }
    }

    static {
        try {
            e = new dn("HTTP/1.1 504 Gateway Timeout");
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

    private ct(dg dgVar, di diVar, ck ckVar) {
        this.a = dgVar;
        this.b = diVar;
        this.c = ckVar;
    }

    public static boolean a(di diVar, dg dgVar) {
        int c = diVar.c();
        if (c != 200 && c != 203 && c != 300 && c != 301 && c != 410) {
            return false;
        }
        cc j = diVar.j();
        if ((dgVar.a("Authorization") == null || j.e() || j.f() || j.d() != -1) && !j.b()) {
            return true;
        }
        return false;
    }
}
