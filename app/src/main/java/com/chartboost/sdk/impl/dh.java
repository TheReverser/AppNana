package com.chartboost.sdk.impl;

import java.net.Proxy.Type;
import java.net.URL;

public final class dh {
    static String a(dg dgVar, Type type, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dgVar.c());
        stringBuilder.append(" ");
        if (a(dgVar, type)) {
            stringBuilder.append(dgVar.a());
        } else {
            stringBuilder.append(a(dgVar.a()));
        }
        stringBuilder.append(" ");
        stringBuilder.append(a(i));
        return stringBuilder.toString();
    }

    private static boolean a(dg dgVar, Type type) {
        return !dgVar.k() && type == Type.HTTP;
    }

    public static String a(URL url) {
        String file = url.getFile();
        if (file == null) {
            return "/";
        }
        if (file.startsWith("/")) {
            return file;
        }
        return "/" + file;
    }

    public static String a(int i) {
        return i == 1 ? "HTTP/1.1" : "HTTP/1.0";
    }
}
