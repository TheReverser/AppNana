package com.chartboost.sdk.impl;

final class eb {
    final byte[] a = new byte[2048];
    int b;
    int c;
    eb d;
    eb e;

    eb() {
    }

    public eb a() {
        eb ebVar = this.d != this ? this.d : null;
        this.e.d = this.d;
        this.d.e = this.e;
        this.d = null;
        this.e = null;
        return ebVar;
    }

    public eb a(eb ebVar) {
        ebVar.e = this;
        ebVar.d = this.d;
        this.d.e = ebVar;
        this.d = ebVar;
        return ebVar;
    }

    public eb a(int i) {
        int i2 = (this.c - this.b) - i;
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        } else if (i < i2) {
            eb a = ec.a.a();
            System.arraycopy(this.a, this.b, a.a, a.b, i);
            this.b += i;
            a.c += i;
            this.e.a(a);
            return a;
        } else {
            eb a2 = ec.a.a();
            System.arraycopy(this.a, this.b + i, a2.a, a2.b, i2);
            this.c -= i2;
            a2.c = i2 + a2.c;
            a(a2);
            return this;
        }
    }

    public void b() {
        if (this.e == this) {
            throw new IllegalStateException();
        } else if ((this.e.c - this.e.b) + (this.c - this.b) <= 2048) {
            a(this.e, this.c - this.b);
            a();
            ec.a.a(this);
        }
    }

    public void a(eb ebVar, int i) {
        if ((ebVar.c - ebVar.b) + i > 2048) {
            throw new IllegalArgumentException();
        }
        if (ebVar.c + i > 2048) {
            System.arraycopy(ebVar.a, ebVar.b, ebVar.a, 0, ebVar.c - ebVar.b);
            ebVar.c -= ebVar.b;
            ebVar.b = 0;
        }
        System.arraycopy(this.a, this.b, ebVar.a, ebVar.c, i);
        ebVar.c += i;
        this.b += i;
    }
}
