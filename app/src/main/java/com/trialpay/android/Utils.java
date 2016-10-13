package com.trialpay.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Telephony.Sms;
import android.util.Log;
import com.facebook.BuildConfig;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class Utils {
    private static final String TAG = "Trialpay.Utils";
    private static GaidResolver gaidResolver;

    public static String byteArrayToHexString(byte[] b) {
        String result = BuildConfig.VERSION_NAME;
        for (byte b2 : b) {
            result = result + Integer.toString((b2 & 255) + 256, 16).substring(1);
        }
        return result;
    }

    public static String toSHA1(String convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteArrayToHexString(md.digest(convertme.getBytes()));
    }

    public static String getAppVer(Context ctx) {
        String appver = "NA";
        try {
            return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return appver;
        }
    }

    public static void assertTrue(boolean condition, String message, String tag) {
        if (tag == null) {
            tag = TAG;
        }
        if (!condition) {
            Log.d(tag, "ASSERT FAILED: " + message);
        }
    }

    public static long getUnixTimestamp() {
        return Calendar.getInstance().getTime().getTime() / 1000;
    }

    @SuppressLint({"NewApi"})
    public static void openSms(Context context, String smsText) {
        Intent sendIntent;
        if (VERSION.SDK_INT >= 19) {
            String defaultSmsPackageName = Sms.getDefaultSmsPackage(context);
            sendIntent = new Intent("android.intent.action.SEND");
            sendIntent.setType("text/plain");
            sendIntent.putExtra("android.intent.extra.TEXT", smsText);
            if (defaultSmsPackageName != null) {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            sendIntent.setFlags(268435456);
            context.startActivity(sendIntent);
            return;
        }
        sendIntent = new Intent("android.intent.action.VIEW");
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("sms_body", smsText);
        sendIntent.putExtra("exit_on_sent", true);
        context.startActivity(sendIntent);
    }

    public static void initGaid(Context ctx) {
        if (gaidResolver == null) {
            gaidResolver = new GaidResolver(ctx);
        }
    }

    public static String getGaid() {
        if (gaidResolver == null) {
            Log.e(TAG, "GAID resolver is not initialized yet, call TrialpayManager(.getInstance()).appLoaded() to initialize");
            return BuildConfig.VERSION_NAME;
        }
        String gaid = gaidResolver.getGaid();
        if (gaid == null) {
            return BuildConfig.VERSION_NAME;
        }
        return gaid;
    }

    public static boolean isGaidTrackingEnabled() {
        if (gaidResolver == null) {
            Log.e(TAG, "GAID resolver is not initialized yet, call TrialpayManager(.getInstance()).appLoaded() to initialize");
            return false;
        }
        Boolean res = gaidResolver.isTrackingEnabled();
        if (res != null) {
            return res.booleanValue();
        }
        return false;
    }
}
