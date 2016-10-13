package com.trialpay.android;

import android.webkit.WebView;
import android.webkit.WebViewClient;

abstract class BaseWebViewClient extends WebViewClient {
    private OfferwallView offerwallView;

    protected abstract void onConnectionErrorAlertClose();

    public BaseWebViewClient(OfferwallView offerwallView) {
        this.offerwallView = offerwallView;
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        this.offerwallView.onReceivedError(view, errorCode, description, failingUrl, this);
    }
}
