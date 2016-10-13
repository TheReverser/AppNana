package com.vungle.publisher;

import java.net.HttpURLConnection;
import java.util.List;

/* compiled from: vungle */
public final class cf {
    public final HttpURLConnection a;
    public int b;
    public List<ce> c;
    private final String d;

    public cf(HttpURLConnection httpURLConnection, int i, List<ce> list) {
        this.a = httpURLConnection;
        this.c = list;
        this.b = i;
        this.d = httpURLConnection == null ? null : String.valueOf(httpURLConnection.getURL());
    }
}
