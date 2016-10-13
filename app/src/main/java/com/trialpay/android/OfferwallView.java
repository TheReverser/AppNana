package com.trialpay.android;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.BuildConfig;
import com.immersion.hapticmediasdk.HapticContentSDK;
import com.trialpay.android.NavBar.EventContainerStatus;
import com.trialpay.android.NavBar.EventPageStatus;
import com.trialpay.android.NavBar.EventSource;
import com.trialpay.android.NavBar.SimpleCommandListener;
import com.trialpay.android.NavBar.UrlCommandListener;
import com.trialpay.android.UrlManager.Url;
import com.vungle.sdk.VunglePub.Gender;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OfferwallView extends LinearLayout {
    public static boolean FORCE_REQUEST_FOCUS_ON_TOUCH_DOWN = false;
    private static final String TAG = "Trialpay.OfferwallView";
    private RelativeLayout containers;
    private final List<EventListener> eventListeners = new ArrayList();
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable issue18726DoRestoreOwcHeightRunnable = new Runnable() {
        public void run() {
            LayoutParams params = OfferwallView.this.offerwallContainer.getLayoutParams();
            params.height = OfferwallView.this.owcOldViewHeight.intValue();
            OfferwallView.this.offerwallContainer.setLayoutParams(params);
            OfferwallView.this.owcOldViewHeight = null;
        }
    };
    private Runnable issue18726doShrinkOwcRunnable = new Runnable() {
        public void run() {
            Log.d(OfferwallView.TAG, "issue18726doShrinkOwcRunnable " + OfferwallView.this.owcCorrectContentHeight + " " + OfferwallView.this.offerwallContainer.getContentHeight());
            OfferwallView.this.issue18726DoShrinkOwc((int) (OfferwallView.this.getContext().getResources().getDisplayMetrics().density * ((float) OfferwallView.this.owcCorrectContentHeight)), 1000);
        }
    };
    private NavBar navBar;
    private WebView offerContainer;
    private WebView offerwallContainer;
    private int owcCorrectContentHeight;
    private Integer owcOldViewHeight;
    private volatile boolean receivedError = false;
    private String touchpointName;
    private boolean wrapContent = false;

    public interface EventListener {
        void handleCloseOfferwallView(String str);

        void handleOpenOfferwallView(String str);
    }

    public void forceRequestFocusOnTouchDown(WebView webView) {
        webView.requestFocus(130);
        webView.setOnTouchListener(new OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case Gender.MALE /*0*/:
                    case Gender.FEMALE /*1*/:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
    }

    public OfferwallView(Context context) {
        super(context);
        Log.d(TAG, "create view");
        init(context);
    }

    public OfferwallView(Context context, boolean doWrapContent) {
        super(context);
        Log.d(TAG, "create view");
        this.wrapContent = doWrapContent;
        init(context);
    }

    public void init(Context context) {
        final Context finalContext = context;
        createLayout(context);
        this.offerwallContainer.setWebViewClient(new BaseWebViewClient(this) {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(OfferwallView.TAG, "shouldOverrideUrlLoading " + url);
                if (url.startsWith(Strings.COMMAND_PREFIX_OW_TO_NAVIGATION_BAR)) {
                    OfferwallView.this.navBar.executeCommand(Url.getDecodedString(url.substring(Strings.COMMAND_PREFIX_OW_TO_NAVIGATION_BAR.length())));
                    return true;
                } else if (url.startsWith(Strings.COMMAND_PREFIX_PRECREDIT)) {
                    BaseTrialpayManager trialpayManager = BaseTrialpayManager.getInstance();
                    String[] params = Url.getDecodedString(url.substring(Strings.COMMAND_PREFIX_PRECREDIT.length())).split("/");
                    if (Utils.toSHA1(params[0] + '/' + params[1] + '/' + trialpayManager.getSid()).equals(params[2])) {
                        Integer amount = Integer.valueOf(0);
                        try {
                            amount = Integer.valueOf(Integer.parseInt(params[0]));
                        } catch (NumberFormatException e) {
                        }
                        trialpayManager.addToPrecredit(trialpayManager.getVic(OfferwallView.this.touchpointName), amount.intValue());
                    }
                    return true;
                } else if (url.startsWith(Video.videoPrefix)) {
                    return Video.open(view.getContext(), url.replace(Video.videoPrefix, BuildConfig.VERSION_NAME), OfferwallView.this.touchpointName, false);
                } else if (url.startsWith("tp://")) {
                    return true;
                } else {
                    if (url.startsWith("tpbowhttp")) {
                        url = url.substring(5);
                    } else if (url.startsWith("http")) {
                        String host = Uri.parse(url).getHost().toLowerCase(Locale.US);
                        boolean isTrialpayHost = false;
                        for (String tpDomain : Strings.TP_DOMAINS) {
                            if (host.contains(tpDomain)) {
                                isTrialpayHost = true;
                                break;
                            }
                        }
                        if (!isTrialpayHost || url.contains("tp_base_page=1")) {
                            return false;
                        }
                        OfferwallView.this.loadOfferContainer(view.getContext(), url);
                        return true;
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                    intent.addFlags(268435456);
                    view.getContext().startActivity(intent);
                    return true;
                }
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                OfferwallView.this.navBar.showSpinner();
                OfferwallView.this.navBar.onPageStatusChanged(EventSource.E_OFFERWALL_CONTAINER, EventPageStatus.E_LOADING_STARTED, url);
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                OfferwallView.this.navBar.hideSpinner();
                OfferwallView.this.navBar.onPageStatusChanged(EventSource.E_OFFERWALL_CONTAINER, EventPageStatus.E_LOADING_FINISHED, url);
                super.onPageFinished(view, url);
                if (OfferwallView.this.wrapContent) {
                    OfferwallView.this.offerwallContainer.loadUrl("javascript:(function(){\tif (window.tpAndroid18726IntervalIdx) {\t\tclearInterval(window.tpAndroid18726IntervalIdx);\t}\twindow.tpAndroid18726IntervalIdx = setInterval(function(){               TpAndroid.issue18726WorkaroundCallback(document.documentElement.scrollHeight);    }, 300);})()");
                }
            }

            protected void onConnectionErrorAlertClose() {
                if (OfferwallView.this.offerContainer != null) {
                    OfferwallView.this.unloadOfferContainer();
                }
                OfferwallView.this.raiseCloseOfferwallEvent();
            }
        });
        this.navBar.setUpCommandListener(new SimpleCommandListener() {
            public void onCommand() {
                Log.v(OfferwallView.TAG, "Navigation Bar: UP");
                if (OfferwallView.this.offerContainer != null) {
                    OfferwallView.this.unloadOfferContainer();
                } else {
                    OfferwallView.this.raiseCloseOfferwallEvent();
                }
            }
        });
        this.navBar.setBackCommandListener(new SimpleCommandListener() {
            public void onCommand() {
                Log.v(OfferwallView.TAG, "Navigation Bar: BACK - touchpoint " + OfferwallView.this.touchpointName);
                OfferwallView.this.goBack();
            }
        });
        this.navBar.setCloseCommandListener(new SimpleCommandListener() {
            public void onCommand() {
                Log.v(OfferwallView.TAG, "Navigation Bar: CLOSE");
                OfferwallView.this.unloadOfferContainer();
                OfferwallView.this.raiseCloseOfferwallEvent();
            }
        });
        this.navBar.setReloadCommandListener(new SimpleCommandListener() {
            public void onCommand() {
                Log.v(OfferwallView.TAG, "Navigation Bar: RELOAD");
                OfferwallView.this.offerwallContainer.clearCache(true);
                OfferwallView.this.loadContent(OfferwallView.this.touchpointName);
            }
        });
        this.navBar.setRefreshCommandListener(new SimpleCommandListener() {
            public void onCommand() {
                Log.v(OfferwallView.TAG, "Navigation Bar: REFRESH");
                if (OfferwallView.this.offerContainer != null) {
                    OfferwallView.this.offerContainer.reload();
                } else {
                    OfferwallView.this.offerwallContainer.reload();
                }
            }
        });
        this.navBar.setOfferwallCommandListener(new UrlCommandListener() {
            public void onCommand(String url) {
                Log.v(OfferwallView.TAG, "Navigation Bar: OFFERWALL: " + url);
                OfferwallView.this.unloadOfferContainer();
                OfferwallView.this.offerwallContainer.loadUrl(url);
            }
        });
        this.navBar.setOfferCommandListener(new UrlCommandListener() {
            public void onCommand(String url) {
                Log.v(OfferwallView.TAG, "Navigation Bar: OFFER: " + url);
                if (OfferwallView.this.offerContainer != null) {
                    OfferwallView.this.offerContainer.loadUrl(url);
                } else {
                    OfferwallView.this.loadOfferContainer(finalContext, url);
                }
            }
        });
        BaseTrialpayManager trialpayManager = BaseTrialpayManager.getInstance();
        if (trialpayManager != null) {
            setOnEventListener(trialpayManager);
        }
    }

    @JavascriptInterface
    public void issue18726WorkaroundCallback(int contentHeight) {
        if (this.owcCorrectContentHeight > contentHeight) {
            this.handler.removeCallbacks(this.issue18726doShrinkOwcRunnable);
            this.handler.postDelayed(this.issue18726doShrinkOwcRunnable, 200);
        }
        this.owcCorrectContentHeight = contentHeight;
    }

    private void issue18726DoShrinkOwc(int intermediateSize, int restoreDelay) {
        Log.d(TAG, "issue18726DoShrinkOwc " + intermediateSize + " " + restoreDelay);
        LayoutParams params = this.offerwallContainer.getLayoutParams();
        if (this.owcOldViewHeight != null) {
            params.height = this.owcOldViewHeight.intValue();
            this.owcOldViewHeight = null;
            this.handler.removeCallbacks(this.issue18726DoRestoreOwcHeightRunnable);
        }
        this.owcOldViewHeight = Integer.valueOf(params.height);
        params.height = intermediateSize;
        this.offerwallContainer.setLayoutParams(params);
        this.handler.postDelayed(this.issue18726DoRestoreOwcHeightRunnable, (long) restoreDelay);
    }

    public void saveState(Bundle outState) {
        Bundle offerwallContainerBundle = new Bundle();
        this.offerwallContainer.saveState(offerwallContainerBundle);
        outState.putBundle("offerwallContainer", offerwallContainerBundle);
        if (this.offerContainer != null) {
            Bundle offerContainerBundle = new Bundle();
            this.offerContainer.saveState(offerContainerBundle);
            outState.putBundle("offerContainer", offerContainerBundle);
        }
        if (this.navBar != null) {
            Bundle navBarBundle = new Bundle();
            this.navBar.saveState(navBarBundle);
            outState.putBundle("navBar", navBarBundle);
        }
    }

    public void restoreState(Bundle inState) {
        this.touchpointName = inState.getString("touchpointName");
        this.offerwallContainer.restoreState(inState.getBundle("offerwallContainer"));
        Bundle offerContainerBundle = inState.getBundle("offerContainer");
        if (offerContainerBundle != null) {
            if (this.offerContainer == null) {
                loadOfferContainer(getContext(), null);
            }
            this.offerContainer.restoreState(offerContainerBundle);
        }
        Bundle navBarBundle = inState.getBundle("navBar");
        if (navBarBundle != null) {
            this.navBar.restoreState(navBarBundle);
        }
    }

    protected WebView createWebView(Context context) {
        WebView webView = new WebView(context);
        webView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        webView.addJavascriptInterface(this, "TpAndroid");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.setScrollBarStyle(0);
        if (VERSION.SDK_INT < 19) {
            webView.getSettings().setDatabasePath(context.getApplicationContext().getDir("database", 0).getPath());
        }
        webView.setWebChromeClient(createWebChromeClient());
        if (FORCE_REQUEST_FOCUS_ON_TOUCH_DOWN || VERSION.SDK_INT < 11) {
            forceRequestFocusOnTouchDown(webView);
        }
        return webView;
    }

    protected WebChromeClient createWebChromeClient() {
        return new WebChromeClient() {
            public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(2 * estimatedSize);
            }
        };
    }

    protected void createLayout(Context context) {
        Log.d(TAG, "createLayout");
        if (this.wrapContent) {
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        } else {
            setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        }
        setOrientation(1);
        this.navBar = createNavBar(context);
        addView(this.navBar.getView());
        this.containers = new RelativeLayout(context);
        if (this.wrapContent) {
            this.containers.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        } else {
            this.containers.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        }
        this.offerwallContainer = createWebView(context);
        if (this.wrapContent) {
            this.offerwallContainer.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        } else {
            this.offerwallContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        }
        this.containers.addView(this.offerwallContainer);
        this.navBar.onContainerStatusChanged(EventSource.E_OFFERWALL_CONTAINER, EventContainerStatus.E_OPENED);
        addView(this.containers);
    }

    protected NavBar createNavBar(Context context) {
        return new NavBarHtml(this);
    }

    protected void loadOfferContainer(final Context context, String url) {
        this.offerContainer = createWebView(context);
        this.offerContainer.getSettings().setBuiltInZoomControls(true);
        this.navBar.onContainerStatusChanged(EventSource.E_OFFER_CONTAINER, EventContainerStatus.E_OPENED);
        this.offerContainer.setWebViewClient(new BaseWebViewClient(this) {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(OfferwallView.TAG, "shouldOverrideUrlLoading " + url);
                if (url.startsWith(Strings.COMMAND_PREFIX_OW_TO_NAVIGATION_BAR)) {
                    OfferwallView.this.navBar.executeCommand(Url.getDecodedString(url.substring(Strings.COMMAND_PREFIX_OW_TO_NAVIGATION_BAR.length())));
                    return true;
                } else if (url.startsWith(Strings.COMMAND_PREFIX_PRECREDIT)) {
                    BaseTrialpayManager trialpayManager = BaseTrialpayManager.getInstance();
                    String[] params = Url.getDecodedString(url.substring(Strings.COMMAND_PREFIX_PRECREDIT.length())).split("/");
                    if (Utils.toSHA1(params[0] + '/' + params[1] + '/' + trialpayManager.getSid()).equals(params[2])) {
                        Integer amount = Integer.valueOf(0);
                        try {
                            amount = Integer.valueOf(Integer.parseInt(params[0]));
                        } catch (NumberFormatException e) {
                        }
                        trialpayManager.addToPrecredit(trialpayManager.getVic(OfferwallView.this.touchpointName), amount.intValue());
                    }
                    return true;
                } else if (url.startsWith(Video.videoPrefix)) {
                    return Video.open(context, url.replace(Video.videoPrefix, BuildConfig.VERSION_NAME), OfferwallView.this.touchpointName, false);
                } else if (url.startsWith("tpshr")) {
                    String sms_text = url.substring(8);
                    OfferwallView.this.unloadOfferContainer();
                    Utils.openSms(view.getContext(), sms_text);
                    return true;
                } else if (url.startsWith("http")) {
                    return false;
                } else {
                    if (url.startsWith("tpbow")) {
                        url = url.substring(5);
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                    intent.addFlags(268435456);
                    OfferwallView.this.getContext().startActivity(intent);
                    return true;
                }
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                OfferwallView.this.navBar.showSpinner();
                OfferwallView.this.navBar.onPageStatusChanged(EventSource.E_OFFER_CONTAINER, EventPageStatus.E_LOADING_STARTED, url);
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                OfferwallView.this.navBar.hideSpinner();
                OfferwallView.this.navBar.onPageStatusChanged(EventSource.E_OFFER_CONTAINER, EventPageStatus.E_LOADING_FINISHED, url);
                super.onPageFinished(view, url);
            }

            protected void onConnectionErrorAlertClose() {
                if (OfferwallView.this.offerContainer != null) {
                    OfferwallView.this.unloadOfferContainer();
                }
            }
        });
        this.containers.addView(this.offerContainer);
        if (url != null) {
            this.offerContainer.loadUrl(url);
        }
    }

    protected void unloadOfferwallContainer() {
        if (this.offerwallContainer != null) {
            this.offerwallContainer.loadUrl("javascript: window.tpDestroy && window.tpDestroy();");
            this.containers.removeView(this.offerwallContainer);
            this.offerwallContainer.stopLoading();
            this.offerwallContainer.removeAllViews();
            this.offerwallContainer.destroy();
            this.offerwallContainer = null;
            this.navBar.onContainerStatusChanged(EventSource.E_OFFER_CONTAINER, EventContainerStatus.E_CLOSED);
        }
    }

    protected void unloadOfferContainer() {
        if (this.offerContainer != null) {
            this.offerContainer.loadUrl("javascript: window.tpDestroy && window.tpDestroy();");
            this.containers.removeView(this.offerContainer);
            this.offerContainer.stopLoading();
            this.offerContainer.removeAllViews();
            this.offerContainer.destroy();
            this.offerContainer = null;
            this.navBar.onContainerStatusChanged(EventSource.E_OFFER_CONTAINER, EventContainerStatus.E_CLOSED);
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyUp(keyCode, event);
        }
        goBack();
        return true;
    }

    private void goBack() {
        Log.d(TAG, "goBack");
        if (this.offerContainer != null) {
            if (this.offerContainer.canGoBack()) {
                this.offerContainer.goBack();
            } else {
                unloadOfferContainer();
            }
        } else if (this.offerwallContainer == null || !this.offerwallContainer.canGoBack()) {
            raiseCloseOfferwallEvent();
        } else {
            this.offerwallContainer.goBack();
        }
    }

    public void setOnEventListener(EventListener eventListener) {
        this.eventListeners.add(eventListener);
    }

    private void raiseCloseOfferwallEvent() {
        Log.d(TAG, "raiseCloseOfferwallEvent");
        if (this.eventListeners != null) {
            for (EventListener eventListener : this.eventListeners) {
                eventListener.handleCloseOfferwallView(this.touchpointName);
            }
            this.navBar.onContainerStatusChanged(EventSource.E_OFFERWALL_CONTAINER, EventContainerStatus.E_CLOSED);
        }
    }

    public void close() {
        unloadOfferContainer();
        unloadOfferwallContainer();
    }

    public boolean loadContent(String touchpointName) {
        this.touchpointName = touchpointName;
        Log.d(TAG, "loadContent - touchpoint: " + touchpointName);
        String url = BaseTrialpayManager.getInstance().getTouchpointUrl(touchpointName);
        if (url == null || OfferAvailabilityCheckTask.NO_TOUCHPOINT.equals(url)) {
            url = UrlManager.getOfferwallUrl(touchpointName);
            if (url == null) {
                Log.e(TAG, "Unable to get offerwall URL for " + touchpointName);
                return false;
            }
        }
        Url dsUrl = new Url(url);
        dsUrl.addQueryParam("tp_base_page", 1);
        url = dsUrl.toString();
        unloadOfferContainer();
        this.offerwallContainer.loadUrl(url);
        this.navBar.open(BaseTrialpayManager.getInstance().getVic(touchpointName));
        return true;
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl, BaseWebViewClient webViewClient) {
        if (!this.receivedError) {
            this.receivedError = true;
            final BaseWebViewClient finalWebViewClient = webViewClient;
            OnClickListener dialogClickListener = new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case HapticContentSDK.INACCESSIBLE_URL /*-2*/:
                            finalWebViewClient.onConnectionErrorAlertClose();
                            break;
                        case Gender.UNKNOWN /*-1*/:
                            OfferwallView.this.navBar.reload();
                            if (OfferwallView.this.offerContainer == null) {
                                OfferwallView.this.offerwallContainer.loadUrl("about:blank");
                                OfferwallView.this.offerwallContainer.reload();
                                break;
                            }
                            OfferwallView.this.offerContainer.loadUrl("about:blank");
                            OfferwallView.this.offerContainer.reload();
                            break;
                    }
                    OfferwallView.this.receivedError = false;
                }
            };
            new Builder(getContext()).setMessage("There seems to be a problem with your internet connection.\nWould you like to try to reload the page?").setPositiveButton("Reload", dialogClickListener).setNegativeButton("Close", dialogClickListener).setCancelable(false).show();
        }
    }
}
