package com.vungle.publisher.display.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.facebook.internal.NativeProtocol;
import com.vungle.publisher.bb;
import com.vungle.publisher.bb.a;
import com.vungle.publisher.bf;
import javax.inject.Inject;

/* compiled from: vungle */
abstract class WebViewFragment extends AdFragment {
    protected a a;
    protected WebView b;
    @Inject
    bf c;
    private String d;

    protected WebViewFragment(String url) {
        this.d = url;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.d = savedInstanceState.getString(NativeProtocol.WEB_DIALOG_URL);
        }
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentActivity activity = getActivity();
        WebView webView = new WebView(activity);
        this.b = webView;
        this.c.a(webView);
        webView.setWebViewClient(new bb(this.a));
        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        settings.setUseWideViewPort(false);
        webView.setBackgroundColor(0);
        webView.setBackgroundResource(0);
        webView.loadUrl(this.d);
        View frameLayout = new FrameLayout(activity);
        frameLayout.addView(webView);
        LayoutParams layoutParams = webView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        return frameLayout;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NativeProtocol.WEB_DIALOG_URL, this.d);
    }
}
