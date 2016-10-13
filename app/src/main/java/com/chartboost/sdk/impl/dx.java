package com.chartboost.sdk.impl;

import com.facebook.BuildConfig;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class dx implements dq, dr, Cloneable {
    eb a;
    long b;

    public /* synthetic */ dq a(int i) throws IOException {
        return d(i);
    }

    public /* synthetic */ dq a(ds dsVar) throws IOException {
        return b(dsVar);
    }

    public /* synthetic */ dq a(String str) throws IOException {
        return b(str);
    }

    public /* synthetic */ dq a(byte[] bArr) throws IOException {
        return b(bArr);
    }

    public /* synthetic */ dq a(byte[] bArr, int i, int i2) throws IOException {
        return c(bArr, i, i2);
    }

    public /* synthetic */ dq b(int i) throws IOException {
        return e(i);
    }

    public /* synthetic */ dq c() throws IOException {
        return m();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return p();
    }

    public long l() {
        return this.b;
    }

    public dx b() {
        return this;
    }

    public OutputStream d() {
        return new OutputStream(this) {
            final /* synthetic */ dx a;

            {
                this.a = r1;
            }

            public void write(int b) {
                this.a.c((byte) b);
            }

            public void write(byte[] data, int offset, int byteCount) {
                this.a.c(data, offset, byteCount);
            }

            public void flush() {
            }

            public void close() {
            }

            public String toString() {
                return this + ".outputStream()";
            }
        };
    }

    public dx m() {
        return this;
    }

    public boolean e() {
        return this.b == 0;
    }

    public void a(long j) throws EOFException {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    public long a(byte b) throws EOFException {
        long b2 = b(b);
        if (b2 != -1) {
            return b2;
        }
        throw new EOFException();
    }

    public InputStream k() {
        return new InputStream(this) {
            final /* synthetic */ dx a;

            {
                this.a = r1;
            }

            public int read() {
                return this.a.f() & 255;
            }

            public int read(byte[] sink, int offset, int byteCount) {
                return this.a.b(sink, offset, byteCount);
            }

            public int available() {
                return (int) Math.min(this.a.b, 2147483647L);
            }

            public void close() {
            }

            public String toString() {
                return this.a + ".inputStream()";
            }
        };
    }

    public long n() {
        long j = this.b;
        if (j == 0) {
            return 0;
        }
        eb ebVar = this.a.e;
        if (ebVar.c < 2048) {
            return j - ((long) (ebVar.c - ebVar.b));
        }
        return j;
    }

    public byte f() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        eb ebVar = this.a;
        int i = ebVar.b;
        int i2 = ebVar.c;
        int i3 = i + 1;
        byte b = ebVar.a[i];
        this.b--;
        if (i3 == i2) {
            this.a = ebVar.a();
            ec.a.a(ebVar);
        } else {
            ebVar.b = i3;
        }
        return b;
    }

    public byte d(long j) {
        ef.a(this.b, j, 1);
        eb ebVar = this.a;
        while (true) {
            int i = ebVar.c - ebVar.b;
            if (j < ((long) i)) {
                return ebVar.a[ebVar.b + ((int) j)];
            }
            j -= (long) i;
            ebVar = ebVar.d;
        }
    }

    public short g() {
        if (this.b < 2) {
            throw new IllegalArgumentException("size < 2: " + this.b);
        }
        eb ebVar = this.a;
        int i = ebVar.b;
        int i2 = ebVar.c;
        if (i2 - i < 2) {
            return (short) (((f() & 255) << 8) | (f() & 255));
        }
        byte[] bArr = ebVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.b -= 2;
        if (i4 == i2) {
            this.a = ebVar.a();
            ec.a.a(ebVar);
        } else {
            ebVar.b = i4;
        }
        return (short) i;
    }

    public int i() {
        if (this.b < 4) {
            throw new IllegalArgumentException("size < 4: " + this.b);
        }
        eb ebVar = this.a;
        int i = ebVar.b;
        int i2 = ebVar.c;
        if (i2 - i < 4) {
            return ((((f() & 255) << 24) | ((f() & 255) << 16)) | ((f() & 255) << 8)) | (f() & 255);
        }
        byte[] bArr = ebVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        i3 = i4 + 1;
        i |= (bArr[i4] & 255) << 8;
        i4 = i3 + 1;
        i |= bArr[i3] & 255;
        this.b -= 4;
        if (i4 == i2) {
            this.a = ebVar.a();
            ec.a.a(ebVar);
            return i;
        }
        ebVar.b = i4;
        return i;
    }

    public int h() {
        return ef.a(g());
    }

    public int j() {
        return ef.a(i());
    }

    public ds c(long j) {
        return new ds(f(j));
    }

    public String e(long j) {
        ef.a(this.b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return BuildConfig.VERSION_NAME;
        } else {
            eb ebVar = this.a;
            if (((long) ebVar.b) + j > ((long) ebVar.c)) {
                try {
                    return new String(f(j), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new AssertionError(e);
                }
            }
            try {
                String str = new String(ebVar.a, ebVar.b, (int) j, "UTF-8");
                ebVar.b = (int) (((long) ebVar.b) + j);
                this.b -= j;
                if (ebVar.b != ebVar.c) {
                    return str;
                }
                this.a = ebVar.a();
                ec.a.a(ebVar);
                return str;
            } catch (UnsupportedEncodingException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    public String a(boolean z) throws EOFException {
        long b = b((byte) 10);
        if (b == -1) {
            if (z) {
                throw new EOFException();
            } else if (this.b != 0) {
                return e(this.b);
            } else {
                return null;
            }
        } else if (b <= 0 || d(b - 1) != (byte) 13) {
            r0 = e(b);
            b(1);
            return r0;
        } else {
            r0 = e(b - 1);
            b(2);
            return r0;
        }
    }

    private byte[] f(long j) {
        ef.a(this.b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        int i = 0;
        Object obj = new byte[((int) j)];
        while (((long) i) < j) {
            int min = (int) Math.min(j - ((long) i), (long) (this.a.c - this.a.b));
            System.arraycopy(this.a.a, this.a.b, obj, i, min);
            i += min;
            eb ebVar = this.a;
            ebVar.b = min + ebVar.b;
            if (this.a.b == this.a.c) {
                eb ebVar2 = this.a;
                this.a = ebVar2.a();
                ec.a.a(ebVar2);
            }
        }
        this.b -= j;
        return obj;
    }

    int b(byte[] bArr, int i, int i2) {
        eb ebVar = this.a;
        if (ebVar == null) {
            return -1;
        }
        int min = Math.min(i2, ebVar.c - ebVar.b);
        System.arraycopy(ebVar.a, ebVar.b, bArr, i, min);
        ebVar.b += min;
        this.b -= (long) min;
        if (ebVar.b != ebVar.c) {
            return min;
        }
        this.a = ebVar.a();
        ec.a.a(ebVar);
        return min;
    }

    public void o() {
        b(this.b);
    }

    public void b(long j) {
        ef.a(this.b, 0, j);
        this.b -= j;
        while (j > 0) {
            int min = (int) Math.min(j, (long) (this.a.c - this.a.b));
            j -= (long) min;
            eb ebVar = this.a;
            ebVar.b = min + ebVar.b;
            if (this.a.b == this.a.c) {
                eb ebVar2 = this.a;
                this.a = ebVar2.a();
                ec.a.a(ebVar2);
            }
        }
    }

    public dx b(ds dsVar) {
        return c(dsVar.b, 0, dsVar.b.length);
    }

    public dx b(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return c(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public dx b(byte[] bArr) {
        return c(bArr, 0, bArr.length);
    }

    public dx c(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        while (i < i3) {
            eb f = f(1);
            int min = Math.min(i3 - i, 2048 - f.c);
            System.arraycopy(bArr, i, f.a, f.c, min);
            i += min;
            f.c = min + f.c;
        }
        this.b += (long) i2;
        return this;
    }

    public dx c(int i) {
        eb f = f(1);
        byte[] bArr = f.a;
        int i2 = f.c;
        f.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    public dx d(int i) {
        eb f = f(2);
        byte[] bArr = f.a;
        int i2 = f.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        f.c = i2;
        this.b += 2;
        return this;
    }

    public dx e(int i) {
        eb f = f(4);
        byte[] bArr = f.a;
        int i2 = f.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >> 24) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) ((i >> 16) & 255);
        i3 = i2 + 1;
        bArr[i2] = (byte) ((i >> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        f.c = i2;
        this.b += 4;
        return this;
    }

    eb f(int i) {
        if (i < 1 || i > 2048) {
            throw new IllegalArgumentException();
        } else if (this.a == null) {
            this.a = ec.a.a();
            eb ebVar = this.a;
            eb ebVar2 = this.a;
            r0 = this.a;
            ebVar2.e = r0;
            ebVar.d = r0;
            return r0;
        } else {
            r0 = this.a.e;
            if (r0.c + i > 2048) {
                return r0.a(ec.a.a());
            }
            return r0;
        }
    }

    public void a(dx dxVar, long j) {
        if (dxVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        ef.a(dxVar.b, 0, j);
        while (j > 0) {
            eb ebVar;
            if (j < ((long) (dxVar.a.c - dxVar.a.b))) {
                ebVar = this.a != null ? this.a.e : null;
                if (ebVar == null || ((long) (ebVar.c - ebVar.b)) + j > 2048) {
                    dxVar.a = dxVar.a.a((int) j);
                } else {
                    dxVar.a.a(ebVar, (int) j);
                    dxVar.b -= j;
                    this.b += j;
                    return;
                }
            }
            ebVar = dxVar.a;
            long j2 = (long) (ebVar.c - ebVar.b);
            dxVar.a = ebVar.a();
            if (this.a == null) {
                this.a = ebVar;
                ebVar = this.a;
                eb ebVar2 = this.a;
                eb ebVar3 = this.a;
                ebVar2.e = ebVar3;
                ebVar.d = ebVar3;
            } else {
                this.a.e.a(ebVar).b();
            }
            dxVar.b -= j2;
            this.b += j2;
            j -= j2;
        }
    }

    public long b(dx dxVar, long j) {
        if (this.b == 0) {
            return -1;
        }
        if (j > this.b) {
            j = this.b;
        }
        dxVar.a(this, j);
        return j;
    }

    public long b(byte b) {
        return a(b, 0);
    }

    public long a(byte b, long j) {
        eb ebVar = this.a;
        if (ebVar == null) {
            return -1;
        }
        long j2 = 0;
        do {
            int i = ebVar.c - ebVar.b;
            if (j > ((long) i)) {
                j -= (long) i;
            } else {
                byte[] bArr = ebVar.a;
                long j3 = (long) ebVar.c;
                for (long j4 = ((long) ebVar.b) + j; j4 < j3; j4++) {
                    if (bArr[(int) j4] == b) {
                        return (j2 + j4) - ((long) ebVar.b);
                    }
                }
                j = 0;
            }
            j2 += (long) i;
            ebVar = ebVar.d;
        } while (ebVar != this.a);
        return -1;
    }

    public void a() {
    }

    public void close() {
    }

    public boolean equals(Object o) {
        long j = 0;
        if (!(o instanceof dx)) {
            return false;
        }
        dx dxVar = (dx) o;
        if (this.b != dxVar.b) {
            return false;
        }
        if (this.b == 0) {
            return true;
        }
        eb ebVar = this.a;
        eb ebVar2 = dxVar.a;
        int i = ebVar.b;
        int i2 = ebVar2.b;
        while (j < this.b) {
            long min = (long) Math.min(ebVar.c - i, ebVar2.c - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                byte b = ebVar.a[i];
                i = i2 + 1;
                if (b != ebVar2.a[i2]) {
                    return false;
                }
                i3++;
                i2 = i;
                i = i4;
            }
            if (i == ebVar.c) {
                ebVar = ebVar.d;
                i = ebVar.b;
            }
            if (i2 == ebVar2.c) {
                ebVar2 = ebVar2.d;
                i2 = ebVar2.b;
            }
            j += min;
        }
        return true;
    }

    public int hashCode() {
        eb ebVar = this.a;
        if (ebVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = ebVar.b;
            while (i2 < ebVar.c) {
                int i3 = ebVar.a[i2] + (i * 31);
                i2++;
                i = i3;
            }
            ebVar = ebVar.d;
        } while (ebVar != this.a);
        return i;
    }

    public String toString() {
        if (this.b == 0) {
            return "OkBuffer[size=0]";
        }
        if (this.b <= 16) {
            ds c = p().c(this.b);
            return String.format("OkBuffer[size=%s data=%s]", new Object[]{Long.valueOf(this.b), c.c()});
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(this.a.a, this.a.b, this.a.c - this.a.b);
            for (eb ebVar = this.a.d; ebVar != this.a; ebVar = ebVar.d) {
                instance.update(ebVar.a, ebVar.b, ebVar.c - ebVar.b);
            }
            return String.format("OkBuffer[size=%s md5=%s]", new Object[]{Long.valueOf(this.b), ds.a(instance.digest()).c()});
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public dx p() {
        dx dxVar = new dx();
        if (l() == 0) {
            return dxVar;
        }
        dxVar.c(this.a.a, this.a.b, this.a.c - this.a.b);
        for (eb ebVar = this.a.d; ebVar != this.a; ebVar = ebVar.d) {
            dxVar.c(ebVar.a, ebVar.b, ebVar.c - ebVar.b);
        }
        return dxVar;
    }
}
