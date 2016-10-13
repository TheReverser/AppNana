package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.ct.a;
import com.chartboost.sdk.impl.dg.b;
import com.facebook.appevents.AppEventsConstants;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheRequest;
import java.net.CookieHandler;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocketFactory;

public class da {
    final ch a;
    long b = -1;
    public final boolean c;
    private cd d;
    private dl e;
    private cl f;
    private do g;
    private boolean h;
    private dg i;
    private dg j;
    private ed k;
    private dq l;
    private ck m;
    private di n;
    private ee o;
    private ee p;
    private InputStream q;
    private di r;
    private CacheRequest s;

    public da(ch chVar, dg dgVar, boolean z, cd cdVar, dl dlVar, dk dkVar) {
        this.a = chVar;
        this.i = dgVar;
        this.j = dgVar;
        this.c = z;
        this.d = cdVar;
        this.e = dlVar;
        this.f = cdVar != null ? cdVar.b() : null;
        this.k = dkVar;
    }

    public final void a() throws IOException {
        if (this.m == null) {
            if (this.g != null) {
                throw new IllegalStateException();
            }
            u();
            ci f = this.a.f();
            di a = f != null ? f.a(this.j) : null;
            ct a2 = new a(System.currentTimeMillis(), this.j, a).a();
            this.m = a2.c;
            this.j = a2.a;
            if (f != null) {
                f.a(this.m);
            }
            if (this.m != ck.NETWORK) {
                this.r = a2.b;
            }
            if (!(a == null || this.m.b())) {
                cs.a(a.h());
            }
            if (this.m.a()) {
                if (this.d == null) {
                    s();
                }
                this.g = (do) this.d.a(this);
                if (c() && this.k == null) {
                    this.k = this.g.a(this.j);
                    return;
                }
                return;
            }
            if (this.d != null) {
                this.a.j().a(this.d);
                this.d = null;
            }
            this.n = this.r;
            if (this.r.h() != null) {
                a(this.r.h().b());
            }
        }
    }

    private di r() {
        return this.n.i().a(null).a();
    }

    private void s() throws IOException {
        HostnameVerifier hostnameVerifier = null;
        if (this.d != null) {
            throw new IllegalStateException();
        }
        if (this.e == null) {
            String host = this.j.a().getHost();
            if (host == null || host.length() == 0) {
                throw new UnknownHostException(this.j.a().toString());
            }
            SSLSocketFactory g;
            if (this.j.k()) {
                g = this.a.g();
                hostnameVerifier = this.a.h();
            } else {
                g = null;
            }
            this.e = new dl(new cb(host, cs.a(this.j.a()), g, hostnameVerifier, this.a.i(), this.a.c(), this.a.m()), this.j.b(), this.a.d(), this.a.j(), cp.a, this.a.l());
        }
        this.d = this.e.a(this.j.c());
        if (!this.d.a()) {
            this.d.a(this.a.a(), this.a.b(), v());
            if (this.d.j()) {
                this.a.j().b(this.d);
            }
            this.a.l().b(this.d.b());
        } else if (!this.d.j()) {
            this.d.b(this.a.b());
        }
        this.f = this.d.b();
    }

    public void b() {
        if (this.b != -1) {
            throw new IllegalStateException();
        }
        this.b = System.currentTimeMillis();
    }

    boolean c() {
        return db.a(this.j.c());
    }

    public final ed d() {
        if (this.m != null) {
            return this.k;
        }
        throw new IllegalStateException();
    }

    public final dq e() {
        dq dqVar = this.l;
        if (dqVar != null) {
            return dqVar;
        }
        ed d = d();
        if (d == null) {
            return null;
        }
        dqVar = dy.a(d);
        this.l = dqVar;
        return dqVar;
    }

    public final boolean f() {
        return this.n != null;
    }

    public final dg g() {
        return this.j;
    }

    public final di h() {
        if (this.n != null) {
            return this.n;
        }
        throw new IllegalStateException();
    }

    public final ee i() {
        if (this.n != null) {
            return this.p;
        }
        throw new IllegalStateException();
    }

    public final InputStream j() {
        InputStream inputStream = this.q;
        if (inputStream != null) {
            return inputStream;
        }
        inputStream = dy.a(i()).k();
        this.q = inputStream;
        return inputStream;
    }

    public final cd k() {
        return this.d;
    }

    public da a(IOException iOException) {
        if (!(this.e == null || this.d == null)) {
            this.e.a(this.d, iOException);
        }
        Object obj = (this.k == null || (this.k instanceof dk)) ? 1 : null;
        if ((this.e == null && this.d == null) || ((this.e != null && !this.e.a()) || !b(iOException) || obj == null)) {
            return null;
        }
        return new da(this.a, this.i, this.c, n(), this.e, (dk) this.k);
    }

    private boolean b(IOException iOException) {
        boolean z;
        if ((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) {
            z = true;
        } else {
            z = false;
        }
        return (z || (iOException instanceof ProtocolException)) ? false : true;
    }

    public cl l() {
        return this.f;
    }

    private void t() throws IOException {
        ci f = this.a.f();
        if (f != null) {
            if (ct.a(this.n, this.j)) {
                this.s = f.a(r());
            } else {
                f.b(this.j);
            }
        }
    }

    public final void m() throws IOException {
        if (!(this.g == null || this.d == null)) {
            this.g.c();
        }
        this.d = null;
    }

    public final cd n() {
        if (this.l != null) {
            cs.a(this.l);
        } else if (this.k != null) {
            cs.a(this.k);
        }
        if (this.p == null) {
            cs.a(this.d);
            this.d = null;
            return null;
        }
        cs.a(this.p);
        cs.a(this.q);
        if (this.g == null || this.g.d()) {
            cd cdVar = this.d;
            this.d = null;
            return cdVar;
        }
        cs.a(this.d);
        this.d = null;
        return null;
    }

    private void a(ee eeVar) throws IOException {
        this.o = eeVar;
        if (this.h && "gzip".equalsIgnoreCase(this.n.a("Content-Encoding"))) {
            this.n = this.n.i().b("Content-Encoding").b("Content-Length").a();
            this.p = new dv(eeVar);
            return;
        }
        this.p = eeVar;
    }

    public final boolean o() {
        if (this.j.c().equals("HEAD")) {
            return false;
        }
        int c = this.n.c();
        if ((c < 100 || c >= 200) && c != 204 && c != 304) {
            return true;
        }
        if (df.a(this.n) != -1 || "chunked".equalsIgnoreCase(this.n.a("Transfer-Encoding"))) {
            return true;
        }
        return false;
    }

    private void u() throws IOException {
        b f = this.j.f();
        if (this.j.h() == null) {
            f.a(p());
        }
        if (this.j.a("Host") == null) {
            f.a("Host", a(this.j.a()));
        }
        if ((this.d == null || this.d.k() != 0) && this.j.a("Connection") == null) {
            f.a("Connection", "Keep-Alive");
        }
        if (this.j.a("Accept-Encoding") == null) {
            this.h = true;
            f.a("Accept-Encoding", "gzip");
        }
        if (c() && this.j.a("Content-Type") == null) {
            f.a("Content-Type", "application/x-www-form-urlencoded");
        }
        CookieHandler e = this.a.e();
        if (e != null) {
            df.a(f, e.get(this.j.b(), df.a(f.a().d(), null)));
        }
        this.j = f.a();
    }

    public static String p() {
        String property = System.getProperty("http.agent");
        return property != null ? property : "Java" + System.getProperty("java.version");
    }

    public static String a(URL url) {
        return cs.a(url) != cs.a(url.getProtocol()) ? url.getHost() + ":" + url.getPort() : url.getHost();
    }

    public final void q() throws IOException {
        if (this.n == null) {
            if (this.m == null) {
                throw new IllegalStateException("call sendRequest() first!");
            } else if (this.m.a()) {
                if (this.l != null && this.l.b().l() > 0) {
                    this.l.a();
                }
                if (this.b == -1) {
                    if (df.a(this.j) == -1 && (this.k instanceof dk)) {
                        this.j = this.j.f().a("Content-Length", Long.toString(((dk) this.k).b())).a();
                    }
                    this.g.b(this.j);
                }
                if (this.k != null) {
                    if (this.l != null) {
                        this.l.close();
                    } else {
                        this.k.close();
                    }
                    if (this.k instanceof dk) {
                        this.g.a((dk) this.k);
                    }
                }
                this.g.a();
                this.n = this.g.b().a(this.j).a(this.d.i()).a(df.b, Long.toString(this.b)).a(df.c, Long.toString(System.currentTimeMillis())).a(this.m).a();
                this.d.a(this.n.e());
                a(this.n.g());
                if (this.m == ck.CONDITIONAL_CACHE) {
                    if (this.r.a(this.n)) {
                        this.g.e();
                        m();
                        this.n = a(this.r, this.n);
                        ci f = this.a.f();
                        f.a();
                        f.a(this.r, r());
                        if (this.r.h() != null) {
                            a(this.r.h().b());
                            return;
                        }
                        return;
                    }
                    cs.a(this.r.h());
                }
                if (o()) {
                    t();
                    a(this.g.a(this.s));
                    return;
                }
                this.o = this.g.a(this.s);
                this.p = this.o;
            }
        }
    }

    private static di a(di diVar, di diVar2) throws IOException {
        int i = 0;
        cw.a aVar = new cw.a();
        cw g = diVar.g();
        for (int i2 = 0; i2 < g.a(); i2++) {
            String a = g.a(i2);
            String b = g.b(i2);
            if (!("Warning".equals(a) && b.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES)) && (!a(a) || diVar2.a(a) == null)) {
                aVar.a(a, b);
            }
        }
        cw g2 = diVar2.g();
        while (i < g2.a()) {
            String a2 = g2.a(i);
            if (a(a2)) {
                aVar.a(a2, g2.b(i));
            }
            i++;
        }
        return diVar.i().a(aVar.a()).a();
    }

    private static boolean a(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    private cn v() {
        if (!this.j.k()) {
            return null;
        }
        String h = this.j.h();
        if (h == null) {
            h = p();
        }
        URL a = this.j.a();
        return new cn(a.getHost(), cs.a(a), h, this.j.i());
    }

    public void a(cw cwVar) throws IOException {
        CookieHandler e = this.a.e();
        if (e != null) {
            e.put(this.j.b(), df.a(cwVar, null));
        }
    }
}
