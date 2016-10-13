package com.chartboost.sdk.Libraries;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public final class b {
    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(bArr);
                bArr2 = instance.digest();
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return bArr2;
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        return String.format(Locale.US, "%0" + (bArr.length << 1) + "x", new Object[]{bigInteger});
    }

    public static byte[] a(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            byte b = bytes[i];
            byte b2 = bytes[i + 1];
            if ((byte) 48 <= b && b <= (byte) 57) {
                b = (byte) (((b - 48) * 16) + 0);
            } else if ((byte) 97 <= b && b <= (byte) 102) {
                b = (byte) ((((b - 97) + 10) * 16) + 0);
            } else if ((byte) 65 > b || b > (byte) 70) {
                b = (byte) 0;
            } else {
                b = (byte) ((((b - 65) + 10) * 16) + 0);
            }
            if ((byte) 48 <= b2 && b2 <= (byte) 57) {
                b = (byte) (b + (b2 - 48));
            } else if ((byte) 97 <= b2 && b2 <= (byte) 102) {
                b = (byte) (b + ((b2 - 97) + 10));
            } else if ((byte) 65 <= b2 && b2 <= (byte) 70) {
                b = (byte) (b + ((b2 - 65) + 10));
            }
            bArr[i / 2] = b;
        }
        return bArr;
    }
}
