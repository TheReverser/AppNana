package com.appnana.android.utils;

import com.facebook.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 15;
            int two_halfs = 0;
            while (true) {
                if (halfbyte < 0 || halfbyte > 9) {
                    buf.append((char) ((halfbyte - 10) + 97));
                } else {
                    buf.append((char) (halfbyte + 48));
                }
                halfbyte = data[i] & 15;
                int two_halfs2 = two_halfs + 1;
                if (two_halfs >= 1) {
                    break;
                }
                two_halfs = two_halfs2;
            }
        }
        return buf.toString();
    }

    public static String sha1(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[256];
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            return convertToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return BuildConfig.VERSION_NAME;
        } catch (UnsupportedEncodingException e2) {
            return BuildConfig.VERSION_NAME;
        }
    }
}
