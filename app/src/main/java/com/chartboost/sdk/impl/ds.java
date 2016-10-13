package com.chartboost.sdk.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public final class ds {
    public static final ds a = a(new byte[0]);
    private static final char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    final byte[] b;
    private transient int d;
    private transient String e;

    ds(byte[] bArr) {
        this.b = bArr;
    }

    public static ds a(byte... bArr) {
        return new ds((byte[]) bArr.clone());
    }

    public static ds a(String str) {
        try {
            ds dsVar = new ds(str.getBytes("UTF-8"));
            dsVar.e = str;
            return dsVar;
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public String a() {
        String str = this.e;
        if (str == null) {
            try {
                str = new String(this.b, "UTF-8");
                this.e = str;
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return str;
    }

    public String b() {
        return dp.a(this.b);
    }

    public String c() {
        int i = 0;
        char[] cArr = new char[(this.b.length * 2)];
        byte[] bArr = this.b;
        int length = bArr.length;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr[i2] = c[(b >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = c[b & 15];
            i++;
        }
        return new String(cArr);
    }

    public boolean b(String str) {
        if (str == null || this.b.length != str.length()) {
            return false;
        }
        if (str == this.e) {
            return true;
        }
        for (int i = 0; i < this.b.length; i++) {
            if (this.b[i] != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public ds d() {
        int i = 0;
        while (i < this.b.length) {
            byte b = this.b[i];
            if (b < (byte) 65 || b > (byte) 90) {
                i++;
            } else {
                byte[] bArr = (byte[]) this.b.clone();
                int i2 = i + 1;
                bArr[i] = (byte) (b + 32);
                for (i = i2; i < bArr.length; i++) {
                    byte b2 = bArr[i];
                    if (b2 >= (byte) 65 && b2 <= (byte) 90) {
                        bArr[i] = (byte) (b2 + 32);
                    }
                }
                return new ds(bArr);
            }
        }
        return this;
    }

    public int e() {
        return this.b.length;
    }

    public byte[] f() {
        return (byte[]) this.b.clone();
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof ds) && Arrays.equals(((ds) o).b, this.b));
    }

    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        i = Arrays.hashCode(this.b);
        this.d = i;
        return i;
    }

    public String toString() {
        if (this.b.length == 0) {
            return "ByteString[size=0]";
        }
        if (this.b.length <= 16) {
            return String.format("ByteString[size=%s data=%s]", new Object[]{Integer.valueOf(this.b.length), c()});
        }
        try {
            return String.format("ByteString[size=%s md5=%s]", new Object[]{Integer.valueOf(this.b.length), a(MessageDigest.getInstance("MD5").digest(this.b)).c()});
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }
}
