package com.chartboost.sdk.impl;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class cl {
    final cb a;
    final Proxy b;
    final InetSocketAddress c;
    final boolean d;

    public cl(cb cbVar, Proxy proxy, InetSocketAddress inetSocketAddress, boolean z) {
        if (cbVar == null) {
            throw new NullPointerException("address == null");
        } else if (proxy == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        } else {
            this.a = cbVar;
            this.b = proxy;
            this.c = inetSocketAddress;
            this.d = z;
        }
    }

    public cb a() {
        return this.a;
    }

    public Proxy b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof cl)) {
            return false;
        }
        cl clVar = (cl) obj;
        if (this.a.equals(clVar.a) && this.b.equals(clVar.b) && this.c.equals(clVar.c) && this.d == clVar.d) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.c.hashCode() + ((((this.a.hashCode() + 527) * 31) + this.b.hashCode()) * 31);
        return (this.d ? hashCode * 31 : 0) + hashCode;
    }
}
