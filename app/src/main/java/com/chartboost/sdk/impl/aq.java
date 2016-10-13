package com.chartboost.sdk.impl;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.impl.at.b;

public class aq extends an implements b {
    private WebView b;

    public aq(Context context) {
        super(context);
        this.b = new WebView(context);
        addView(this.b, new LayoutParams(-1, -1));
        this.b.setBackgroundColor(0);
        this.b.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) {
                    return false;
                }
                if (url.contains("chartboost") && url.contains("click") && this.a.a != null) {
                    this.a.a.onClick(this.a);
                }
                return true;
            }
        });
    }

    public void a(a aVar, int i) {
        String e = aVar.e("html");
        if (e != null) {
            try {
                this.b.loadDataWithBaseURL("file:///android_res/", e, "text/html", "UTF-8", null);
            } catch (Throwable e2) {
                CBLogging.b("CBNativeMoreAppsWebViewCell", "Exception raised loading data into webview", e2);
            }
        }
    }

    public int a() {
        return CBUtility.a(100, getContext());
    }
}
