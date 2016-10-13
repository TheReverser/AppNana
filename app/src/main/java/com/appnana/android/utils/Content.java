package com.appnana.android.utils;

import android.content.ClipData;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.ClipboardManager;

public class Content {
    public static void copy(Context context, String text) {
        if (VERSION.SDK_INT < 11) {
            ((ClipboardManager) context.getSystemService("clipboard")).setText(text);
        } else {
            ((android.content.ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", text));
        }
    }
}
