package com.vungle.publisher;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.facebook.internal.NativeProtocol;
import com.vungle.log.Logger;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ba extends WebChromeClient {
    private a a;

    /* compiled from: vungle */
    public static class a {
        public void a() {
        }

        public void b() {
        }

        public void c() {
        }
    }

    public ba(a aVar) {
        this.a = aVar;
    }

    public final boolean onJsPrompt(WebView webView, String str, String message, String str2, JsPromptResult result) {
        Logger.v(Logger.AD_TAG, "js prompt: " + message);
        boolean startsWith = message.startsWith("vungle:");
        if (startsWith) {
            String str3 = null;
            try {
                str3 = message.substring(7);
                JSONObject jSONObject = new JSONObject(str3);
                String string = jSONObject.getString("method");
                jSONObject.getString(NativeProtocol.WEB_DIALOG_PARAMS);
                if ("close".equalsIgnoreCase(string)) {
                    this.a.a();
                } else if ("download".equalsIgnoreCase(string)) {
                    this.a.b();
                } else if ("replay".equalsIgnoreCase(string)) {
                    this.a.c();
                } else {
                    Logger.w(Logger.AD_TAG, "unknown javascript method: " + string);
                }
            } catch (IndexOutOfBoundsException e) {
                Logger.w(Logger.AD_TAG, "no javascript method call");
            } catch (Throwable e2) {
                Logger.w(Logger.AD_TAG, "invalid json " + str3, e2);
            } catch (Throwable e22) {
                Logger.e(Logger.AD_TAG, e22);
            }
            result.cancel();
        }
        return startsWith;
    }
}
