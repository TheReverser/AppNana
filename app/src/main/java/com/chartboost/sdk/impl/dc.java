package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.di.b;
import java.io.IOException;
import java.net.CacheRequest;

public final class dc implements do {
    private final da a;
    private final cy b;

    public dc(da daVar, cy cyVar) {
        this.a = daVar;
        this.b = cyVar;
    }

    public ed a(dg dgVar) throws IOException {
        long a = df.a(dgVar);
        if (this.a.c) {
            if (a > 2147483647L) {
                throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
            } else if (a == -1) {
                return new dk();
            } else {
                b(dgVar);
                return new dk((int) a);
            }
        } else if ("chunked".equalsIgnoreCase(dgVar.a("Transfer-Encoding"))) {
            b(dgVar);
            return this.b.f();
        } else if (a != -1) {
            b(dgVar);
            return this.b.a(a);
        } else {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
    }

    public void a() throws IOException {
        this.b.d();
    }

    public void a(dk dkVar) throws IOException {
        this.b.a(dkVar);
    }

    public void b(dg dgVar) throws IOException {
        this.a.b();
        this.b.a(dgVar.g(), dh.a(dgVar, this.a.k().b().b().type(), this.a.k().k()));
    }

    public b b() throws IOException {
        return this.b.e();
    }

    public void c() throws IOException {
        if (d()) {
            this.b.a();
        } else {
            this.b.b();
        }
    }

    public boolean d() {
        if ("close".equalsIgnoreCase(this.a.g().a("Connection")) || "close".equalsIgnoreCase(this.a.h().a("Connection")) || this.b.c()) {
            return false;
        }
        return true;
    }

    public void e() throws IOException {
        this.b.g();
    }

    public ee a(CacheRequest cacheRequest) throws IOException {
        if (!this.a.o()) {
            return this.b.a(cacheRequest, 0);
        }
        if ("chunked".equalsIgnoreCase(this.a.h().a("Transfer-Encoding"))) {
            return this.b.a(cacheRequest, this.a);
        }
        long a = df.a(this.a.h());
        if (a != -1) {
            return this.b.a(cacheRequest, a);
        }
        return this.b.a(cacheRequest);
    }
}
