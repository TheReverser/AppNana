package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import java.io.File;

public class aa {
    public static l a(Context context, x xVar) {
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = new StringBuilder(String.valueOf(packageName)).append("/").append(context.getPackageManager().getPackageInfo(packageName, 0).versionCode).toString();
        } catch (NameNotFoundException e) {
        }
        if (xVar == null) {
            if (VERSION.SDK_INT >= 9) {
                xVar = new y();
            } else {
                xVar = new w(AndroidHttpClient.newInstance(str));
            }
        }
        l lVar = new l(new v(file), new t(xVar));
        lVar.a();
        return lVar;
    }
}
