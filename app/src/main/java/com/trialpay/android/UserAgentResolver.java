package com.trialpay.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;

public class UserAgentResolver {
    private Context ctx;
    private Runnable onResolved;
    private String ua;

    public UserAgentResolver(Context ctx) {
        this.ctx = ctx;
    }

    public void setOnResolvedCallback(Runnable r) {
        this.onResolved = r;
    }

    public void resolve() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            doResolve();
            this.onResolved.run();
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                UserAgentResolver.this.doResolve();
                UserAgentResolver.this.onResolved.run();
            }
        });
    }

    private void doResolve() {
        this.ua = new WebView(this.ctx).getSettings().getUserAgentString();
    }

    public String getUserAgent() {
        return this.ua;
    }
}
