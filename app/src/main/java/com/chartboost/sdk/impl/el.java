package com.chartboost.sdk.impl;

import com.facebook.BuildConfig;
import com.facebook.share.internal.ShareConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class el {
    private static final ej[] a = new ej[]{new ej(ej.e, BuildConfig.VERSION_NAME), new ej(ej.b, "GET"), new ej(ej.b, "POST"), new ej(ej.c, "/"), new ej(ej.c, "/index.html"), new ej(ej.d, "http"), new ej(ej.d, "https"), new ej(ej.a, "200"), new ej(ej.a, "500"), new ej(ej.a, "404"), new ej(ej.a, "403"), new ej(ej.a, "400"), new ej(ej.a, "401"), new ej("accept-charset", BuildConfig.VERSION_NAME), new ej("accept-encoding", BuildConfig.VERSION_NAME), new ej("accept-language", BuildConfig.VERSION_NAME), new ej("accept-ranges", BuildConfig.VERSION_NAME), new ej("accept", BuildConfig.VERSION_NAME), new ej("access-control-allow-origin", BuildConfig.VERSION_NAME), new ej("age", BuildConfig.VERSION_NAME), new ej("allow", BuildConfig.VERSION_NAME), new ej("authorization", BuildConfig.VERSION_NAME), new ej("cache-control", BuildConfig.VERSION_NAME), new ej("content-disposition", BuildConfig.VERSION_NAME), new ej("content-encoding", BuildConfig.VERSION_NAME), new ej("content-language", BuildConfig.VERSION_NAME), new ej("content-length", BuildConfig.VERSION_NAME), new ej("content-location", BuildConfig.VERSION_NAME), new ej("content-range", BuildConfig.VERSION_NAME), new ej("content-type", BuildConfig.VERSION_NAME), new ej("cookie", BuildConfig.VERSION_NAME), new ej("date", BuildConfig.VERSION_NAME), new ej("etag", BuildConfig.VERSION_NAME), new ej("expect", BuildConfig.VERSION_NAME), new ej("expires", BuildConfig.VERSION_NAME), new ej("from", BuildConfig.VERSION_NAME), new ej("host", BuildConfig.VERSION_NAME), new ej("if-match", BuildConfig.VERSION_NAME), new ej("if-modified-since", BuildConfig.VERSION_NAME), new ej("if-none-match", BuildConfig.VERSION_NAME), new ej("if-range", BuildConfig.VERSION_NAME), new ej("if-unmodified-since", BuildConfig.VERSION_NAME), new ej("last-modified", BuildConfig.VERSION_NAME), new ej(ShareConstants.WEB_DIALOG_PARAM_LINK, BuildConfig.VERSION_NAME), new ej("location", BuildConfig.VERSION_NAME), new ej("max-forwards", BuildConfig.VERSION_NAME), new ej("proxy-authenticate", BuildConfig.VERSION_NAME), new ej("proxy-authorization", BuildConfig.VERSION_NAME), new ej("range", BuildConfig.VERSION_NAME), new ej("referer", BuildConfig.VERSION_NAME), new ej("refresh", BuildConfig.VERSION_NAME), new ej("retry-after", BuildConfig.VERSION_NAME), new ej("server", BuildConfig.VERSION_NAME), new ej("set-cookie", BuildConfig.VERSION_NAME), new ej("strict-transport-security", BuildConfig.VERSION_NAME), new ej("transfer-encoding", BuildConfig.VERSION_NAME), new ej("user-agent", BuildConfig.VERSION_NAME), new ej("vary", BuildConfig.VERSION_NAME), new ej("via", BuildConfig.VERSION_NAME), new ej("www-authenticate", BuildConfig.VERSION_NAME)};
    private static final Map<ds, Integer> b = c();

    static final class a {
        ej[] a = new ej[8];
        int b = (this.a.length - 1);
        int c = 0;
        co d = new com.chartboost.sdk.impl.co.a();
        co e = new com.chartboost.sdk.impl.co.a();
        int f = 0;
        private final a g;
        private final List<ej> h = new ArrayList();
        private final dr i;
        private int j;

        a(boolean z, int i, ee eeVar) {
            this.g = z ? a.RESPONSE : a.REQUEST;
            this.j = i;
            this.i = dy.a(eeVar);
        }

        void a(int i) {
            this.j = i;
            if (this.j >= this.f) {
                return;
            }
            if (this.j == 0) {
                d();
            } else {
                b(this.f - this.j);
            }
        }

        private void d() {
            e();
            Arrays.fill(this.a, null);
            this.b = this.a.length - 1;
            this.c = 0;
            this.f = 0;
        }

        private int b(int i) {
            int i2 = 0;
            if (i > 0) {
                for (int length = this.a.length - 1; length >= this.b && i > 0; length--) {
                    i -= this.a[length].j;
                    this.f -= this.a[length].j;
                    this.c--;
                    i2++;
                }
                this.d.d(i2);
                this.e.d(i2);
                System.arraycopy(this.a, this.b + 1, this.a, (this.b + 1) + i2, this.c);
                this.b += i2;
            }
            return i2;
        }

        void a() throws IOException {
            while (!this.i.e()) {
                int f = this.i.f() & 255;
                if (f == 128) {
                    e();
                } else if ((f & 128) == 128) {
                    c(a(f, 127) - 1);
                } else if (f == 64) {
                    f();
                } else if ((f & 64) == 64) {
                    e(a(f, 63) - 1);
                } else if (f == 0) {
                    g();
                } else if ((f & 192) == 0) {
                    f(a(f, 63) - 1);
                } else {
                    throw new AssertionError("unhandled byte: " + Integer.toBinaryString(f));
                }
            }
        }

        private void e() {
            this.d.a();
            this.e.a();
        }

        void b() {
            int length = this.a.length - 1;
            while (length != this.b) {
                if (this.d.c(length) && !this.e.c(length)) {
                    this.h.add(this.a[length]);
                }
                length--;
            }
        }

        List<ej> c() {
            List arrayList = new ArrayList(this.h);
            this.h.clear();
            this.e.a();
            return arrayList;
        }

        private void c(int i) {
            if (h(i)) {
                ej ejVar = el.a[i - this.c];
                if (this.j == 0) {
                    this.h.add(ejVar);
                    return;
                } else {
                    a(-1, ejVar);
                    return;
                }
            }
            int d = d(i);
            if (!this.d.c(d)) {
                this.h.add(this.a[d]);
                this.e.a(d);
            }
            this.d.b(d);
        }

        private int d(int i) {
            return (this.b + 1) + i;
        }

        private void e(int i) throws IOException {
            this.h.add(new ej(g(i), a(false)));
        }

        private void f() throws IOException {
            this.h.add(new ej(a(true), a(false)));
        }

        private void f(int i) throws IOException {
            a(-1, new ej(g(i), a(false)));
        }

        private void g() throws IOException {
            a(-1, new ej(a(true), a(false)));
        }

        private ds g(int i) {
            if (h(i)) {
                return el.a[i - this.c].h;
            }
            return this.a[d(i)].h;
        }

        private boolean h(int i) {
            return i >= this.c;
        }

        private void a(int i, ej ejVar) {
            int i2;
            int i3 = ejVar.j;
            if (i != -1) {
                i2 = i3 - this.a[d(i)].j;
            } else {
                i2 = i3;
            }
            if (i2 > this.j) {
                d();
                this.h.add(ejVar);
                return;
            }
            i3 = b((this.f + i2) - this.j);
            if (i == -1) {
                if (this.c + 1 > this.a.length) {
                    Object obj = new ej[(this.a.length * 2)];
                    System.arraycopy(this.a, 0, obj, this.a.length, this.a.length);
                    if (obj.length == 64) {
                        this.d = ((com.chartboost.sdk.impl.co.a) this.d).b();
                        this.e = ((com.chartboost.sdk.impl.co.a) this.e).b();
                    }
                    this.d.d(this.a.length);
                    this.e.d(this.a.length);
                    this.b = this.a.length - 1;
                    this.a = obj;
                }
                i3 = this.b;
                this.b = i3 - 1;
                this.d.a(i3);
                this.a[i3] = ejVar;
                this.c++;
            } else {
                i3 = (i3 + d(i)) + i;
                this.d.a(i3);
                this.a[i3] = ejVar;
            }
            this.f += i2;
        }

        private int h() throws IOException {
            return this.i.f() & 255;
        }

        int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            i3 = 0;
            while (true) {
                int h = h();
                if ((h & 128) == 0) {
                    return (h << i3) + i2;
                }
                i2 += (h & 127) << i3;
                i3 += 7;
            }
        }

        ds a(boolean z) throws IOException {
            ds a;
            int h = h();
            Object obj = (h & 128) == 128 ? 1 : null;
            ds c = this.i.c((long) a(h, 127));
            if (obj != null) {
                a = this.g.a(c);
            } else {
                a = c;
            }
            if (z) {
                return a.d();
            }
            return a;
        }
    }

    static final class b {
        private final dx a;

        b(dx dxVar) {
            this.a = dxVar;
        }

        void a(List<ej> list) throws IOException {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ds dsVar = ((ej) list.get(i)).h;
                Integer num = (Integer) el.b.get(dsVar);
                if (num != null) {
                    a(num.intValue() + 1, 63, 64);
                    a(((ej) list.get(i)).i);
                } else {
                    this.a.c(64);
                    a(dsVar);
                    a(((ej) list.get(i)).i);
                }
            }
        }

        void a(int i, int i2, int i3) throws IOException {
            if (i < i2) {
                this.a.c(i3 | i);
                return;
            }
            this.a.c(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.a.c((i4 & 127) | 128);
                i4 >>>= 7;
            }
            this.a.c(i4);
        }

        void a(ds dsVar) throws IOException {
            a(dsVar.e(), 127, 0);
            this.a.b(dsVar);
        }
    }

    private static Map<ds, Integer> c() {
        Map linkedHashMap = new LinkedHashMap(a.length);
        for (int i = 0; i < a.length; i++) {
            if (!linkedHashMap.containsKey(a[i].h)) {
                linkedHashMap.put(a[i].h, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
