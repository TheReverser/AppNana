package com.appnana.android.utils;

import android.util.Log;

public class MapizLog {
    private static boolean showLog = false;

    public static void enableLogging(boolean enable) {
        Log.i("MapizLog", "enableLogging: " + enable);
        showLog = enable;
    }

    public static void i(String tag, String msg) {
        if (showLog) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (showLog) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (showLog) {
            Log.w(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (showLog) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (showLog) {
            Log.v(tag, msg);
        }
    }
}
