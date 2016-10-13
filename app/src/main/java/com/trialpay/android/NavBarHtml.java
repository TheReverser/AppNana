package com.trialpay.android;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.trialpay.android.NavBar.EventContainerStatus;
import com.trialpay.android.NavBar.EventPageStatus;
import com.trialpay.android.NavBar.EventSource;
import com.trialpay.android.NavBar.SimpleCommandListener;
import com.trialpay.android.NavBar.UrlCommandListener;
import com.trialpay.android.UrlManager.Url;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class NavBarHtml extends NavBarAbstract {
    private static String TAG = "Trialpay.NavBarHtml";
    private Button closeBtn;
    private boolean isLoaded;
    private ArrayList<String> javascriptCommandsHistory = new ArrayList();
    private ViewGroup layout;
    private List<String> pendingJavascriptCommands = new LinkedList();
    private String vic;
    private WebView webView;

    public NavBarHtml(OfferwallView offerwallView) {
        this.webView = new WebView(offerwallView.getContext());
        this.webView.setLayoutParams(new LayoutParams(-1, 0));
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setPadding(0, 0, 0, 0);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new BaseWebViewClient(offerwallView) {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.startsWith("tp://")) {
                    return false;
                }
                NavBarHtml.this.handleTpUrls(url.trim());
                return true;
            }

            protected void onConnectionErrorAlertClose() {
                NavBarHtml.this.handleTpUrls("tp://close");
            }
        });
        this.webView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.e(NavBarHtml.TAG, String.format("%s @ %d: %s", new Object[]{cm.message(), Integer.valueOf(cm.lineNumber()), cm.sourceId()}));
                return true;
            }
        });
        this.closeBtn = new Button(offerwallView.getContext());
        this.closeBtn.setText("X");
        this.closeBtn.setPadding(3, 3, 3, 3);
        LayoutParams closeBtnLayoutParams = new LayoutParams((int) (getScaledDensity() * 44.0f), (int) (getScaledDensity() * 44.0f));
        closeBtnLayoutParams.addRule(11, -1);
        this.closeBtn.setLayoutParams(closeBtnLayoutParams);
        this.closeBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                NavBarHtml.this.fireOnCommand(NavBarHtml.this.closeCommandListener);
            }
        });
        this.layout = new RelativeLayout(offerwallView.getContext());
        this.layout.setLayoutParams(new LayoutParams(-1, -2));
        this.layout.setPadding(0, 0, 0, 0);
        this.layout.addView(this.webView);
        this.layout.addView(this.closeBtn);
    }

    private float getScaledDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) this.webView.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
        return metrics.scaledDensity;
    }

    private void setWebViewLayoutParams(int height) {
        this.webView.setLayoutParams(new LayoutParams(-1, (int) (((float) height) * getScaledDensity())));
        this.layout.invalidate();
        postDelayFullscreenSettings();
    }

    private void postDelayFullscreenSettings() {
        if (VERSION.SDK_INT < 11 && (this.webView.getContext() instanceof OfferwallActivity)) {
            ((OfferwallActivity) this.webView.getContext()).forceFullscreenSettings();
        }
    }

    public View getView() {
        return this.layout;
    }

    public void showSpinner() {
        executeJavascript("showSpinner()", new String[]{"hideSpinner", "showSpinner"});
    }

    public void hideSpinner() {
        executeJavascript("hideSpinner()", new String[]{"hideSpinner", "showSpinner"});
    }

    public void setTitle(String title) {
        executeJavascript("setTitle(" + quoteStringForJs(title) + ")", new String[]{"setTitle"});
    }

    public void setSubTitle(String subtitle) {
        executeJavascript("setSubTitle(" + quoteStringForJs(subtitle) + ")", new String[]{"setSubTitle"});
    }

    private String getWebViewUrl(String vic) {
        return UrlManager.getInstance().getNavbarUrl().addQueryParam("vic", vic).toString();
    }

    private String quoteStringForJs(String str) {
        return JSONObject.quote(str);
    }

    private void executePendingJavascript() {
        for (int i = 0; i < this.pendingJavascriptCommands.size(); i++) {
            this.webView.loadUrl("javascript:" + ((String) this.pendingJavascriptCommands.get(i)));
        }
    }

    private void handleTpUrls(String url) {
        Log.v(TAG, "handleTpUrls(" + url + ")");
        boolean handled = false;
        if (url.startsWith("tp://ready")) {
            if (!this.isLoaded) {
                this.layout.removeView(this.closeBtn);
                this.isLoaded = true;
                executePendingJavascript();
                this.pendingJavascriptCommands.clear();
                postDelayFullscreenSettings();
            }
            handled = true;
        } else if (url.startsWith("tp://close")) {
            handled = fireOnCommand(this.closeCommandListener);
        } else if (url.startsWith("tp://up")) {
            handled = fireOnCommand(this.upCommandListener);
        } else if (url.startsWith("tp://back")) {
            handled = fireOnCommand(this.backCommandListener);
        } else if (url.startsWith("tp://reload")) {
            handled = fireOnCommand(this.reloadCommandListener);
        } else if (url.startsWith("tp://refresh")) {
            handled = fireOnCommand(this.refreshCommandListener);
        } else if (url.startsWith("tp://offerwall/")) {
            handled = fireOnCommand(this.offerwallCommandListener, Url.getDecodedString(url.substring("tp://offerwall/".length())));
        } else if (url.startsWith("tp://offer/")) {
            handled = fireOnCommand(this.offerCommandListener, Url.getDecodedString(url.substring("tp://offer/".length())));
        } else if (url.startsWith("tp://changeNavBarHeight/")) {
            setWebViewLayoutParams(Integer.parseInt(url.substring("tp://changeNavBarHeight/".length())));
            handled = true;
        } else {
            Log.e(TAG, "Unknown command, ignore");
        }
        if (!handled) {
            Log.d(TAG, url + " command was not handled");
        }
    }

    private boolean fireOnCommand(SimpleCommandListener listener) {
        if (listener == null) {
            return false;
        }
        listener.onCommand();
        return true;
    }

    private boolean fireOnCommand(UrlCommandListener listener, String url) {
        if (listener == null) {
            return false;
        }
        listener.onCommand(url);
        return true;
    }

    private void executeJavascript(String command, String[] invalidateCommands) {
        if (this.isLoaded) {
            this.webView.loadUrl("javascript:" + command);
        } else {
            addJavascriptCommandToList(this.pendingJavascriptCommands, command, invalidateCommands);
        }
        addJavascriptCommandToList(this.javascriptCommandsHistory, command, invalidateCommands);
    }

    private static void addJavascriptCommandToList(List<String> target, String command, String[] invalidateCommands) {
        if (invalidateCommands != null) {
            for (int pendingIdx = target.size() - 1; pendingIdx >= 0; pendingIdx--) {
                String pendingCmd = (String) target.get(pendingIdx);
                for (String indexOf : invalidateCommands) {
                    if (pendingCmd.indexOf(indexOf) == 0) {
                        target.remove(pendingIdx);
                        break;
                    }
                }
            }
        }
        target.add(command);
    }

    public void open(String vic) {
        boolean z;
        if (vic != null) {
            z = true;
        } else {
            z = false;
        }
        Utils.assertTrue(z, "Vic is not provided", TAG);
        this.vic = vic;
        String url = getWebViewUrl(vic);
        Log.v(TAG, "Loading navigation bar: " + url);
        this.isLoaded = false;
        this.pendingJavascriptCommands.clear();
        this.javascriptCommandsHistory.clear();
        this.webView.loadUrl(url);
    }

    public void executeCommand(String jsCommand) {
        executeJavascript(jsCommand, null);
    }

    public void reload() {
        this.webView.clearView();
        this.webView.reload();
    }

    public void onPageStatusChanged(EventSource source, EventPageStatus newStatus, String url) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("type", "pageStatusChanged").put(ShareConstants.FEED_SOURCE_PARAM, source == EventSource.E_OFFER_CONTAINER ? "offerContainer" : "offerwallContainer").put("newStatus", newStatus == EventPageStatus.E_LOADING_FINISHED ? "loadingFinished" : "loadingStarted").put(NativeProtocol.WEB_DIALOG_URL, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        executeJavascript("onSDKEvent(" + obj.toString() + ")", null);
    }

    public void onContainerStatusChanged(EventSource source, EventContainerStatus newStatus) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("type", "containerStatusChanged").put(ShareConstants.FEED_SOURCE_PARAM, source == EventSource.E_OFFER_CONTAINER ? "offerContainer" : "offerwallContainer").put("newStatus", newStatus == EventContainerStatus.E_OPENED ? "opened" : "closed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        executeJavascript("onSDKEvent(" + obj.toString() + ")", null);
    }

    public void saveState(Bundle outState) {
        outState.putString(TAG + ":vic", this.vic);
        outState.putStringArrayList(TAG + ":javascriptCommandsHistory", (ArrayList) this.javascriptCommandsHistory.clone());
    }

    public void restoreState(Bundle inState) {
        String vic = inState.getString(TAG + ":vic");
        if (vic != null) {
            open(vic);
        }
        ArrayList<String> list = inState.getStringArrayList(TAG + ":javascriptCommandsHistory");
        if (list != null) {
            Iterator i$ = list.iterator();
            while (i$.hasNext()) {
                executeJavascript((String) i$.next(), null);
            }
        }
    }
}
