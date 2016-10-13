package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.di.b;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class dm implements do {
    private final da a;
    private final eu b;
    private ev c;

    private static class a implements ee {
        private final ev a;
        private final ee b;
        private final CacheRequest c;
        private final OutputStream d;
        private boolean e;
        private boolean f;

        a(ev evVar, CacheRequest cacheRequest) throws IOException {
            this.a = evVar;
            this.b = evVar.e();
            OutputStream body = cacheRequest != null ? cacheRequest.getBody() : null;
            if (body == null) {
                cacheRequest = null;
            }
            this.d = body;
            this.c = cacheRequest;
        }

        public long b(dx dxVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.f) {
                throw new IllegalStateException("closed");
            } else if (this.e) {
                return -1;
            } else {
                long b = this.b.b(dxVar, j);
                if (b == -1) {
                    this.e = true;
                    if (this.c != null) {
                        this.d.close();
                    }
                    return -1;
                } else if (this.d == null) {
                    return b;
                } else {
                    dy.a(dxVar, dxVar.l() - b, b, this.d);
                    return b;
                }
            }
        }

        public void close() throws IOException {
            if (!this.f) {
                if (!(this.e || this.d == null)) {
                    a();
                }
                this.f = true;
                if (!this.e) {
                    this.a.b(eg.CANCEL);
                    if (this.c != null) {
                        this.c.abort();
                    }
                }
            }
        }

        private boolean a() {
            long d;
            try {
                d = this.a.d();
                this.a.a(d);
                this.a.a(100);
                cs.a((ee) this, 100);
                this.a.a(d);
                return true;
            } catch (IOException e) {
                return false;
            } catch (Throwable th) {
                this.a.a(d);
            }
        }
    }

    public dm(da daVar, eu euVar) {
        this.a = daVar;
        this.b = euVar;
    }

    public ed a(dg dgVar) throws IOException {
        b(dgVar);
        return this.c.f();
    }

    public void b(dg dgVar) throws IOException {
        if (this.c == null) {
            this.a.b();
            this.c = this.b.a(a(dgVar, this.b.a(), dh.a(this.a.k().k())), this.a.c(), true);
            this.c.a((long) this.a.a.b());
        }
    }

    public void a(dk dkVar) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void a() throws IOException {
        this.c.f().close();
    }

    public b b() throws IOException {
        return a(this.c.c(), this.b.a());
    }

    public static List<ej> a(dg dgVar, cj cjVar, String str) {
        cw d = dgVar.d();
        List<ej> arrayList = new ArrayList(d.a() + 10);
        arrayList.add(new ej(ej.b, dgVar.c()));
        arrayList.add(new ej(ej.c, dh.a(dgVar.a())));
        String a = da.a(dgVar.a());
        if (cj.SPDY_3 == cjVar) {
            arrayList.add(new ej(ej.g, str));
            arrayList.add(new ej(ej.f, a));
        } else if (cj.HTTP_2 == cjVar) {
            arrayList.add(new ej(ej.e, a));
        } else {
            throw new AssertionError();
        }
        arrayList.add(new ej(ej.d, dgVar.a().getProtocol()));
        Set linkedHashSet = new LinkedHashSet();
        for (int i = 0; i < d.a(); i++) {
            ds a2 = ds.a(d.a(i).toLowerCase(Locale.US));
            String b = d.b(i);
            if (!a(cjVar, a2) && !a2.equals(ej.b) && !a2.equals(ej.c) && !a2.equals(ej.d) && !a2.equals(ej.e) && !a2.equals(ej.f) && !a2.equals(ej.g)) {
                if (linkedHashSet.add(a2)) {
                    arrayList.add(new ej(a2, b));
                } else {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (((ej) arrayList.get(i2)).h.equals(a2)) {
                            arrayList.set(i2, new ej(a2, a(((ej) arrayList.get(i2)).i.a(), b)));
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static String a(String str, String str2) {
        return '\u0000' + str2;
    }

    public static b a(List<ej> list, cj cjVar) throws IOException {
        String str = null;
        String str2 = "HTTP/1.1";
        com.chartboost.sdk.impl.cw.a aVar = new com.chartboost.sdk.impl.cw.a();
        aVar.b(df.e, cjVar.d.a());
        int i = 0;
        while (i < list.size()) {
            ds dsVar = ((ej) list.get(i)).h;
            String a = ((ej) list.get(i)).i.a();
            String str3 = str2;
            int i2 = 0;
            while (i2 < a.length()) {
                int indexOf = a.indexOf(0, i2);
                if (indexOf == -1) {
                    indexOf = a.length();
                }
                str2 = a.substring(i2, indexOf);
                if (!dsVar.equals(ej.a)) {
                    if (dsVar.equals(ej.g)) {
                        str3 = str2;
                        str2 = str;
                    } else {
                        if (!a(cjVar, dsVar)) {
                            aVar.a(dsVar.a(), str2);
                        }
                        str2 = str;
                    }
                }
                str = str2;
                i2 = indexOf + 1;
            }
            i++;
            str2 = str3;
        }
        if (str == null) {
            throw new ProtocolException("Expected ':status' header not present");
        } else if (str2 != null) {
            return new b().a(new dn(str2 + " " + str)).a(aVar.a());
        } else {
            throw new ProtocolException("Expected ':version' header not present");
        }
    }

    public void e() {
    }

    public ee a(CacheRequest cacheRequest) throws IOException {
        return new a(this.c, cacheRequest);
    }

    public void c() {
    }

    public boolean d() {
        return true;
    }

    private static boolean a(cj cjVar, ds dsVar) {
        if (cjVar == cj.SPDY_3) {
            if (dsVar.b("connection") || dsVar.b("host") || dsVar.b("keep-alive") || dsVar.b("proxy-connection") || dsVar.b("transfer-encoding")) {
                return true;
            }
            return false;
        } else if (cjVar != cj.HTTP_2) {
            throw new AssertionError(cjVar);
        } else if (dsVar.b("connection") || dsVar.b("host") || dsVar.b("keep-alive") || dsVar.b("proxy-connection") || dsVar.b("te") || dsVar.b("transfer-encoding") || dsVar.b("encoding") || dsVar.b("upgrade")) {
            return true;
        } else {
            return false;
        }
    }
}
