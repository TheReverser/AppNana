package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.eu.a;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketTimeoutException;
import javax.net.ssl.SSLSocket;

public final class cd implements Closeable {
    private final ce a;
    private final cl b;
    private Socket c;
    private InputStream d;
    private OutputStream e;
    private dr f;
    private dq g;
    private boolean h = false;
    private cy i;
    private eu j;
    private int k = 1;
    private long l;
    private cf m;
    private int n;

    public cd(ce ceVar, cl clVar) {
        this.a = ceVar;
        this.b = clVar;
    }

    public void a(int i, int i2, cn cnVar) throws IOException {
        if (this.h) {
            throw new IllegalStateException("already connected");
        }
        this.c = this.b.b.type() != Type.HTTP ? new Socket(this.b.b) : new Socket();
        cr.a().a(this.c, this.b.c, i);
        this.c.setSoTimeout(i2);
        this.d = this.c.getInputStream();
        this.e = this.c.getOutputStream();
        if (this.b.a.d != null) {
            a(cnVar);
        } else {
            o();
            this.i = new cy(this.a, this, this.f, this.g);
        }
        this.h = true;
    }

    private void a(cn cnVar) throws IOException {
        boolean z;
        cr a = cr.a();
        if (l()) {
            b(cnVar);
        }
        this.c = this.b.a.d.createSocket(this.c, this.b.a.b, this.b.a.c, true);
        SSLSocket sSLSocket = (SSLSocket) this.c;
        if (this.b.d) {
            a.a(sSLSocket, this.b.a.b);
        } else {
            a.a(sSLSocket);
        }
        if (this.b.d && (this.b.a.g.contains(cj.HTTP_2) || this.b.a.g.contains(cj.SPDY_3))) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.b.a.g.contains(cj.HTTP_2) && this.b.a.g.contains(cj.SPDY_3)) {
                a.a(sSLSocket, cs.f);
            } else if (this.b.a.g.contains(cj.HTTP_2)) {
                a.a(sSLSocket, cs.h);
            } else {
                a.a(sSLSocket, cs.g);
            }
        }
        sSLSocket.startHandshake();
        if (this.b.a.e.verify(this.b.a.b, sSLSocket.getSession())) {
            this.e = sSLSocket.getOutputStream();
            this.d = sSLSocket.getInputStream();
            this.m = cf.a(sSLSocket.getSession());
            o();
            cj cjVar = cj.HTTP_11;
            if (z) {
                ds b = a.b(sSLSocket);
                if (b != null) {
                    cjVar = cs.a(b);
                }
            }
            if (cjVar.e) {
                sSLSocket.setSoTimeout(0);
                this.j = new a(this.b.a.a(), true, this.f, this.g).a(cjVar).a();
                this.j.e();
                return;
            }
            this.i = new cy(this.a, this, this.f, this.g);
            return;
        }
        throw new IOException("Hostname '" + this.b.a.b + "' was not verified");
    }

    public boolean a() {
        return this.h;
    }

    public void close() throws IOException {
        this.c.close();
    }

    public cl b() {
        return this.b;
    }

    public Socket c() {
        return this.c;
    }

    public boolean d() {
        return (this.c.isClosed() || this.c.isInputShutdown() || this.c.isOutputShutdown()) ? false : true;
    }

    public boolean e() {
        int soTimeout;
        if (this.f == null || j()) {
            return true;
        }
        try {
            soTimeout = this.c.getSoTimeout();
            this.c.setSoTimeout(1);
            if (this.f.e()) {
                this.c.setSoTimeout(soTimeout);
                return false;
            }
            this.c.setSoTimeout(soTimeout);
            return true;
        } catch (SocketTimeoutException e) {
            return true;
        } catch (IOException e2) {
            return false;
        } catch (Throwable th) {
            this.c.setSoTimeout(soTimeout);
        }
    }

    public void f() {
        if (this.j != null) {
            throw new IllegalStateException("spdyConnection != null");
        }
        this.l = System.nanoTime();
    }

    public boolean g() {
        return this.j == null || this.j.b();
    }

    public boolean a(long j) {
        return h() < System.nanoTime() - j;
    }

    public long h() {
        return this.j == null ? this.l : this.j.c();
    }

    public cf i() {
        return this.m;
    }

    public Object a(da daVar) throws IOException {
        return this.j != null ? new dm(daVar, this.j) : new dc(daVar, this.i);
    }

    public boolean j() {
        return this.j != null;
    }

    public int k() {
        return this.k;
    }

    public void a(int i) {
        this.k = i;
    }

    public boolean l() {
        return this.b.a.d != null && this.b.b.type() == Type.HTTP;
    }

    public void b(int i) throws IOException {
        if (this.h) {
            this.c.setSoTimeout(i);
            return;
        }
        throw new IllegalStateException("updateReadTimeout - not connected");
    }

    public void m() {
        this.n++;
    }

    public int n() {
        return this.n;
    }

    private void b(cn cnVar) throws IOException {
        dr a = dy.a(dy.a(this.d));
        cy cyVar = new cy(this.a, this, a, dy.a(dy.a(this.e)));
        dg b = cnVar.b();
        String a2 = cnVar.a();
        do {
            cyVar.a(b.d(), a2);
            cyVar.d();
            di a3 = cyVar.e().a(b).a();
            cyVar.g();
            switch (a3.c()) {
                case 200:
                    if (a.b().l() > 0) {
                        throw new IOException("TLS tunnel buffered too many bytes!");
                    }
                    return;
                case 407:
                    b = cx.a(this.b.a.f, a3, this.b.b);
                    break;
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + a3.c());
            }
        } while (b != null);
        throw new IOException("Failed to authenticate with proxy");
    }

    private void o() throws IOException {
        this.f = dy.a(dy.a(this.d));
        this.g = dy.a(dy.a(this.e));
    }
}
