package com.vungle.publisher.display.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vungle.log.Logger;
import com.vungle.publisher.ba;

/* compiled from: vungle */
public class PostRollFragment extends WebViewFragment {
    private a d;

    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.ba.a {
    }

    public final /* bridge */ /* synthetic */ void a(com.vungle.publisher.bb.a aVar) {
        super.a(aVar);
    }

    public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle x0) {
        super.onSaveInstanceState(x0);
    }

    public PostRollFragment(String url, a listener, com.vungle.publisher.bb.a webViewClientListener) {
        super(url);
        a(listener, webViewClientListener);
    }

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onCreate", e);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View onCreateView;
        Throwable e;
        try {
            onCreateView = super.onCreateView(inflater, container, savedInstanceState);
            try {
                this.b.setWebChromeClient(new ba(this.d));
            } catch (Exception e2) {
                e = e2;
                Logger.w(Logger.AD_TAG, "exception in onCreateView", e);
                return onCreateView;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            onCreateView = null;
            e = th;
            Logger.w(Logger.AD_TAG, "exception in onCreateView", e);
            return onCreateView;
        }
        return onCreateView;
    }

    public final void a() {
        try {
            this.d.a();
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onBackPressed", e);
        }
    }

    public final void a(a aVar, com.vungle.publisher.bb.a aVar2) {
        try {
            super.a(aVar2);
            this.d = aVar;
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onActivityCreated", e);
        }
    }

    public final String b() {
        return "postRollFragment";
    }
}
