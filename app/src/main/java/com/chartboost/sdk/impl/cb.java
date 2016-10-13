package com.chartboost.sdk.impl;

import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class cb {
    final Proxy a;
    final String b;
    final int c;
    final SSLSocketFactory d;
    final HostnameVerifier e;
    final cg f;
    final List<cj> g;

    public cb(String str, int i, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, cg cgVar, Proxy proxy, List<cj> list) throws UnknownHostException {
        if (str == null) {
            throw new NullPointerException("uriHost == null");
        } else if (i <= 0) {
            throw new IllegalArgumentException("uriPort <= 0: " + i);
        } else if (cgVar == null) {
            throw new IllegalArgumentException("authenticator == null");
        } else if (list == null) {
            throw new IllegalArgumentException("protocols == null");
        } else {
            this.a = proxy;
            this.b = str;
            this.c = i;
            this.d = sSLSocketFactory;
            this.e = hostnameVerifier;
            this.f = cgVar;
            this.g = cs.a((List) list);
        }
    }

    public String a() {
        return this.b;
    }

    public SSLSocketFactory b() {
        return this.d;
    }

    public Proxy c() {
        return this.a;
    }

    public boolean equals(Object other) {
        if (!(other instanceof cb)) {
            return false;
        }
        cb cbVar = (cb) other;
        if (cs.a(this.a, cbVar.a) && this.b.equals(cbVar.b) && this.c == cbVar.c && cs.a(this.d, cbVar.d) && cs.a(this.e, cbVar.e) && cs.a(this.f, cbVar.f) && cs.a(this.g, cbVar.g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((this.d != null ? this.d.hashCode() : 0) + ((((this.b.hashCode() + 527) * 31) + this.c) * 31)) * 31;
        if (this.e != null) {
            hashCode = this.e.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f != null) {
            hashCode = this.f.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.a != null) {
            i = this.a.hashCode();
        }
        return ((hashCode + i) * 31) + this.g.hashCode();
    }
}
