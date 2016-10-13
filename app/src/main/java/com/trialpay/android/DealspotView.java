package com.trialpay.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.trialpay.android.BaseTrialpayManager.DisplayMode;
import com.trialpay.android.UrlManager.Url;

public class DealspotView extends WebView {
    private static final String TAG = "DealspotView";
    private final String touchpointName;

    public DealspotView(Context baseContext, String touchpointName) {
        super(baseContext);
        this.touchpointName = touchpointName;
        setBackgroundColor(0);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setSupportZoom(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setInitialScale(100);
        getSettings().setDomStorageEnabled(true);
        hideDealspotIcon();
        setupListeners();
    }

    public void hideDealspotIcon() {
        setVisibility(4);
    }

    public void showDealspotIcon() {
        setVisibility(0);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        refresh();
    }

    public void refresh() {
        Url url = UrlManager.getInstance().getDealspotTouchpointUrl(getContext(), this.touchpointName, getHeight(), getWidth());
        Log.d(TAG, "Refresh DealSpot " + url);
        if (url != null) {
            loadUrl(url.toString());
        }
    }

    public void setupListeners() {
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == 2;
            }
        });
        setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(DealspotView.TAG, "DS URL " + url);
                if (url.startsWith("trialpay://dsresizefull:")) {
                    DealspotView.this.hideDealspotIcon();
                    String landingPageUrl = url.substring(24);
                    if (landingPageUrl.contains("dispatch") && !landingPageUrl.contains("sdkver")) {
                        landingPageUrl = (landingPageUrl + (landingPageUrl.contains("?") ? "&" : "?")) + "sdkver=" + BaseTrialpayManager.getInstance().getSdkVer();
                    }
                    try {
                        BaseTrialpayManager.getInstance().registerTouchpointUrl(landingPageUrl, DealspotView.this.touchpointName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BaseTrialpayManager.getInstance().open(DealspotView.this.touchpointName, DisplayMode.TP_FULLSCREEN_MODE);
                } else if (url.startsWith("trialpay://dsresizetouchpoint")) {
                    DealspotView.this.showDealspotIcon();
                } else if (url.startsWith("http")) {
                    return false;
                } else {
                    if (url.startsWith("tpbowhttp")) {
                        url = url.substring(5);
                    }
                    DealspotView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url.substring(5))));
                }
                return true;
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Log.d(DealspotView.TAG, "ERROR onReceivedSslError: " + error);
                DealspotView.this.hideDealspotIcon();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.d(DealspotView.TAG, "ERROR onReceivedError: " + description);
                DealspotView.this.hideDealspotIcon();
            }
        });
    }
}
