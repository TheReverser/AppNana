package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.CBPreferences;

public class NaCl {
    private static boolean a = false;

    public static native byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    public static native byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    public static native j keypair();

    public static native byte[] randombytes(int i);

    static {
        System.loadLibrary("chartboost");
    }

    public static byte[] a() {
        String appPublicKey = CBPreferences.getInstance().getAppPublicKey();
        if (appPublicKey == null) {
            if (a) {
                return null;
            }
            CBLogging.a("The Chartboost public key is *not* set. The Store requires this to be set.");
            a = true;
            return null;
        } else if (appPublicKey.length() == 64) {
            return b.a(appPublicKey);
        } else {
            if (a) {
                return null;
            }
            CBLogging.a("The Chartboost public key is *not* valid. The Store requires this to be properly set.");
            a = true;
            return null;
        }
    }
}
