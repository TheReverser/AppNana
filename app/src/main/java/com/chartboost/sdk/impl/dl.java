package com.chartboost.sdk.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLProtocolException;

public final class dl {
    private final cb a;
    private final URI b;
    private final ProxySelector c;
    private final ce d;
    private final cp e;
    private final cm f;
    private Proxy g;
    private InetSocketAddress h;
    private boolean i;
    private Proxy j;
    private Iterator<Proxy> k;
    private InetAddress[] l;
    private int m;
    private int n;
    private int o = -1;
    private final List<cl> p;

    public dl(cb cbVar, URI uri, ProxySelector proxySelector, ce ceVar, cp cpVar, cm cmVar) {
        this.a = cbVar;
        this.b = uri;
        this.c = proxySelector;
        this.d = ceVar;
        this.e = cpVar;
        this.f = cmVar;
        this.p = new LinkedList();
        a(uri, cbVar.c());
    }

    public boolean a() {
        return g() || d() || b() || i();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.chartboost.sdk.impl.cd a(java.lang.String r6) throws java.io.IOException {
        /*
        r5 = this;
        r0 = 1;
    L_0x0001:
        r1 = r5.d;
        r2 = r5.a;
        r1 = r1.a(r2);
        if (r1 == 0) goto L_0x001f;
    L_0x000b:
        r2 = "GET";
        r2 = r6.equals(r2);
        if (r2 != 0) goto L_0x0019;
    L_0x0013:
        r2 = r1.e();
        if (r2 == 0) goto L_0x001b;
    L_0x0019:
        r0 = r1;
    L_0x001a:
        return r0;
    L_0x001b:
        r1.close();
        goto L_0x0001;
    L_0x001f:
        r1 = r5.g();
        if (r1 != 0) goto L_0x005d;
    L_0x0025:
        r1 = r5.d();
        if (r1 != 0) goto L_0x0054;
    L_0x002b:
        r1 = r5.b();
        if (r1 != 0) goto L_0x0049;
    L_0x0031:
        r0 = r5.i();
        if (r0 != 0) goto L_0x003d;
    L_0x0037:
        r0 = new java.util.NoSuchElementException;
        r0.<init>();
        throw r0;
    L_0x003d:
        r0 = new com.chartboost.sdk.impl.cd;
        r1 = r5.d;
        r2 = r5.j();
        r0.<init>(r1, r2);
        goto L_0x001a;
    L_0x0049:
        r1 = r5.c();
        r5.g = r1;
        r1 = r5.g;
        r5.a(r1);
    L_0x0054:
        r1 = r5.e();
        r5.h = r1;
        r5.f();
    L_0x005d:
        r1 = r5.h();
        if (r1 != r0) goto L_0x0080;
    L_0x0063:
        r1 = new com.chartboost.sdk.impl.cl;
        r2 = r5.a;
        r3 = r5.g;
        r4 = r5.h;
        r1.<init>(r2, r3, r4, r0);
        r0 = r5.f;
        r0 = r0.c(r1);
        if (r0 == 0) goto L_0x0082;
    L_0x0076:
        r0 = r5.p;
        r0.add(r1);
        r0 = r5.a(r6);
        goto L_0x001a;
    L_0x0080:
        r0 = 0;
        goto L_0x0063;
    L_0x0082:
        r0 = new com.chartboost.sdk.impl.cd;
        r2 = r5.d;
        r0.<init>(r2, r1);
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.dl.a(java.lang.String):com.chartboost.sdk.impl.cd");
    }

    public void a(cd cdVar, IOException iOException) {
        boolean z = true;
        if (cdVar.n() <= 0) {
            cl b = cdVar.b();
            if (!(b.b().type() == Type.DIRECT || this.c == null)) {
                this.c.connectFailed(this.b, b.b().address(), iOException);
            }
            this.f.a(b);
            if (g() && !(iOException instanceof SSLHandshakeException) && !(iOException instanceof SSLProtocolException)) {
                if (h() != 1) {
                    z = false;
                }
                this.f.a(new cl(this.a, this.g, this.h, z));
            }
        }
    }

    private void a(URI uri, Proxy proxy) {
        this.i = true;
        if (proxy != null) {
            this.j = proxy;
            return;
        }
        List select = this.c.select(uri);
        if (select != null) {
            this.k = select.iterator();
        }
    }

    private boolean b() {
        return this.i;
    }

    private Proxy c() {
        if (this.j != null) {
            this.i = false;
            return this.j;
        }
        if (this.k != null) {
            while (this.k.hasNext()) {
                Proxy proxy = (Proxy) this.k.next();
                if (proxy.type() != Type.DIRECT) {
                    return proxy;
                }
            }
        }
        this.i = false;
        return Proxy.NO_PROXY;
    }

    private void a(Proxy proxy) throws UnknownHostException {
        String host;
        this.l = null;
        if (proxy.type() == Type.DIRECT) {
            host = this.b.getHost();
            this.n = cs.a(this.b);
        } else {
            SocketAddress address = proxy.address();
            if (address instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                String hostName = inetSocketAddress.getHostName();
                this.n = inetSocketAddress.getPort();
                host = hostName;
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address.getClass());
            }
        }
        this.l = this.e.a(host);
        this.m = 0;
    }

    private boolean d() {
        return this.l != null;
    }

    private InetSocketAddress e() throws UnknownHostException {
        InetAddress[] inetAddressArr = this.l;
        int i = this.m;
        this.m = i + 1;
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddressArr[i], this.n);
        if (this.m == this.l.length) {
            this.l = null;
            this.m = 0;
        }
        return inetSocketAddress;
    }

    private void f() {
        this.o = this.a.b() != null ? 1 : 0;
    }

    private boolean g() {
        return this.o != -1;
    }

    private int h() {
        if (this.o == 1) {
            this.o = 0;
            return 1;
        } else if (this.o == 0) {
            this.o = -1;
            return 0;
        } else {
            throw new AssertionError();
        }
    }

    private boolean i() {
        return !this.p.isEmpty();
    }

    private cl j() {
        return (cl) this.p.remove(0);
    }
}
