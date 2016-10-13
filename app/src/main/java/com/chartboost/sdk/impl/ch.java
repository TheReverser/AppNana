package com.chartboost.sdk.impl;

import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.ResponseCache;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public final class ch implements Cloneable, URLStreamHandlerFactory {
    private final cm a = new cm();
    private Proxy b;
    private List<cj> c;
    private ProxySelector d;
    private CookieHandler e;
    private ci f;
    private SSLSocketFactory g;
    private HostnameVerifier h;
    private cg i;
    private ce j;
    private boolean k = true;
    private int l;
    private int m;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return o();
    }

    public void a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        } else if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            long toMillis = timeUnit.toMillis(j);
            if (toMillis > 2147483647L) {
                throw new IllegalArgumentException("Timeout too large.");
            }
            this.l = (int) toMillis;
        }
    }

    public int a() {
        return this.l;
    }

    public void b(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        } else if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            long toMillis = timeUnit.toMillis(j);
            if (toMillis > 2147483647L) {
                throw new IllegalArgumentException("Timeout too large.");
            }
            this.m = (int) toMillis;
        }
    }

    public int b() {
        return this.m;
    }

    public Proxy c() {
        return this.b;
    }

    public ProxySelector d() {
        return this.d;
    }

    public CookieHandler e() {
        return this.e;
    }

    public ch a(ci ciVar) {
        this.f = ciVar;
        return this;
    }

    public ci f() {
        return this.f;
    }

    public ch a(SSLSocketFactory sSLSocketFactory) {
        this.g = sSLSocketFactory;
        return this;
    }

    public SSLSocketFactory g() {
        return this.g;
    }

    public ch a(HostnameVerifier hostnameVerifier) {
        this.h = hostnameVerifier;
        return this;
    }

    public HostnameVerifier h() {
        return this.h;
    }

    public cg i() {
        return this.i;
    }

    public ce j() {
        return this.j;
    }

    public boolean k() {
        return this.k;
    }

    public cm l() {
        return this.a;
    }

    public ch a(List<cj> list) {
        List a = cs.a((List) list);
        if (!a.contains(cj.HTTP_11)) {
            throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + a);
        } else if (a.contains(null)) {
            throw new IllegalArgumentException("protocols must not contain null");
        } else {
            this.c = cs.a(a);
            return this;
        }
    }

    public List<cj> m() {
        return this.c;
    }

    public HttpURLConnection a(URL url) {
        return a(url, this.b);
    }

    HttpURLConnection a(URL url, Proxy proxy) {
        String protocol = url.getProtocol();
        ch n = n();
        n.b = proxy;
        if (protocol.equals("http")) {
            return new dd(url, n);
        }
        if (protocol.equals("https")) {
            return new de(url, n);
        }
        throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }

    ch n() {
        ch o = o();
        if (o.d == null) {
            o.d = ProxySelector.getDefault();
        }
        if (o.e == null) {
            o.e = CookieHandler.getDefault();
        }
        if (o.f == null) {
            o.f = a(ResponseCache.getDefault());
        }
        if (o.g == null) {
            o.g = p();
        }
        if (o.h == null) {
            o.h = ey.a;
        }
        if (o.i == null) {
            o.i = cx.a;
        }
        if (o.j == null) {
            o.j = ce.a();
        }
        if (o.c == null) {
            o.c = cs.f;
        }
        return o;
    }

    private synchronized SSLSocketFactory p() {
        if (this.g == null) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.g = instance.getSocketFactory();
            } catch (GeneralSecurityException e) {
                throw new AssertionError();
            }
        }
        return this.g;
    }

    public ch o() {
        try {
            return (ch) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private ci a(ResponseCache responseCache) {
        return (responseCache == null || (responseCache instanceof ci)) ? (ci) responseCache : new dj(responseCache);
    }

    public URLStreamHandler createURLStreamHandler(final String protocol) {
        if (protocol.equals("http") || protocol.equals("https")) {
            return new URLStreamHandler(this) {
                final /* synthetic */ ch b;

                protected URLConnection openConnection(URL url) {
                    return this.b.a(url);
                }

                protected URLConnection openConnection(URL url, Proxy proxy) {
                    return this.b.a(url, proxy);
                }

                protected int getDefaultPort() {
                    if (protocol.equals("http")) {
                        return 80;
                    }
                    if (protocol.equals("https")) {
                        return 443;
                    }
                    throw new AssertionError();
                }
            };
        }
        return null;
    }
}
