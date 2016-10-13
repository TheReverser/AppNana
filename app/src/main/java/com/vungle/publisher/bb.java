package com.vungle.publisher;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.vungle.log.Logger;

/* compiled from: vungle */
public final class bb extends WebViewClient {
    a a;

    /* compiled from: vungle */
    public interface a {
        void a();
    }

    public bb(a aVar) {
        this.a = aVar;
    }

    public final void onReceivedError(WebView webView, int code, String desc, String str) {
        Logger.e(Logger.AD_TAG, "failed to load web view: code " + code + ", " + desc);
        this.a.a();
    }

    public final void onPageFinished(WebView webView, String url) {
        if (!url.toLowerCase().startsWith("javascript:")) {
            webView.loadUrl(new StringBuilder("javascript:function actionClicked(m,p){ var q = prompt('vungle:'+JSON.stringify({method:m,params:(p?p:null)}));if(q&&typeof q === 'string'){return JSON.parse(q).result;}};function noTapHighlight(){var l=document.getElementsByTagName('*');for(var i=0; i<l.length; i++){l[i].style.webkitTapHighlightColor='rgba(0,0,0,0)';}};noTapHighlight();").toString());
        }
    }
}
