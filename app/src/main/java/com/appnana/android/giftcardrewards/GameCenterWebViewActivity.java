package com.appnana.android.giftcardrewards;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnana.android.giftcardrewards.GameCenterBaseActivity.GameCenterJavascriptInterface;
import com.appnana.android.giftcardrewards.model.Settings;

public class GameCenterWebViewActivity extends GameCenterBaseActivity {
    private WebView mWebView;

    private class GameCenterJavascriptInterfaceForWebView extends GameCenterJavascriptInterface {
        public GameCenterJavascriptInterfaceForWebView(Context context) {
            super(context);
        }

        @JavascriptInterface
        public String getGamingIntervals() {
            return super.getGamingIntervals();
        }

        @JavascriptInterface
        public String getNanaerInfo() {
            return super.getNanaerInfo();
        }

        @JavascriptInterface
        public String getNanaerSessionId() {
            return super.getNanaerSessionId();
        }

        @JavascriptInterface
        public void showAnonymousNanaerDialog() {
            super.showAnonymousNanaerDialog(GameCenterWebViewActivity.this.loginButtonClickListener, GameCenterWebViewActivity.this.registerButtonClickListener);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        private MyWebChromeClient() {
        }

        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            GameCenterWebViewActivity.this.changeProgress(newProgress);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        private MyWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            GameCenterWebViewActivity.this.setFullscreenMode(GameCenterWebViewActivity.this.isInGamePage(url));
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    protected void setup() {
        setContentView(R.layout.activity_gamecenter_webview);
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(false);
        }
        this.mWebView = (WebView) findViewById(R.id.web_view);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setWebViewClient(new MyWebViewClient());
        this.mWebView.setWebChromeClient(new MyWebChromeClient());
        this.mWebView.addJavascriptInterface(new GameCenterJavascriptInterfaceForWebView(this), "android");
        this.mWebView.loadUrl(Settings.GAME_CENTER_URL);
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
