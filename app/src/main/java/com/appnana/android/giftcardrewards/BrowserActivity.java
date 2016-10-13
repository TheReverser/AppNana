package com.appnana.android.giftcardrewards;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.exception.MassInviteAlreadySent;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

public class BrowserActivity extends Activity implements OnClickListener {
    public static final String EXTRA_REQUEST = "REQUEST_CODE";
    public static final String EXTRA_TITLE = "TITLE";
    public static final String EXTRA_URI = "URI";
    private Button mBtnBack;
    private RelativeLayout mLayoutLoading;
    private TextView mTxtTitle;
    private WebView mWebView;
    private OnClickListener onErrorDialogButtonClickListener = new OnClickListener() {
        public void onClick(View v) {
            BrowserActivity.this.finishWithCanceledResult();
        }
    };
    private int requestCode;

    private class VerifyFacebookPost extends AsyncTask<String, Void, Boolean> {
        int errorRes;

        private VerifyFacebookPost() {
        }

        protected Boolean doInBackground(String... params) {
            try {
                return Boolean.valueOf(UserCommand.create().verifyFacebookPost(params[0]));
            } catch (IOException e) {
                this.errorRes = R.string.error_session_expired;
                return Boolean.valueOf(false);
            } catch (MassInviteAlreadySent e2) {
                this.errorRes = R.string.error_offer_already_finished;
                return Boolean.valueOf(false);
            } catch (Exception e3) {
                this.errorRes = R.string.error_under_maintenance;
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e3.toString());
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result.booleanValue()) {
                BrowserActivity.this.finishWithOkResult();
            } else {
                AlertDialog.alert(BrowserActivity.this, R.string.tips_sorry, this.errorRes, BrowserActivity.this.onErrorDialogButtonClickListener);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            BrowserActivity.this.mLayoutLoading.setVisibility(0);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        findViews();
        setListeners();
        initialize();
    }

    protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(this);
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    private void findViews() {
        this.mLayoutLoading = (RelativeLayout) findViewById(R.id.layout_loading);
        this.mBtnBack = (Button) findViewById(R.id.btn_back);
        this.mTxtTitle = (TextView) findViewById(R.id.txt_title);
    }

    private void setListeners() {
        this.mBtnBack.setOnClickListener(this);
    }

    private void initialize() {
        Intent i = getIntent();
        this.requestCode = i.getIntExtra(EXTRA_REQUEST, 0);
        String url = i.getStringExtra(EXTRA_URI);
        this.mTxtTitle.setText(i.getIntExtra(EXTRA_TITLE, R.string.tips_help));
        initBrowser(url);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initBrowser(String url) {
        this.mWebView = (WebView) findViewById(R.id.web_browser);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.setScrollBarStyle(0);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                BrowserActivity.this.mLayoutLoading.setVisibility(0);
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                BrowserActivity.this.mLayoutLoading.setVisibility(8);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (BrowserActivity.this.requestCode != 1) {
                    return super.shouldOverrideUrlLoading(view, url);
                }
                String fbRedirectUri = URLDecoder.decode((String) Settings.getInstance().facebookParams.get(ServerProtocol.DIALOG_PARAM_REDIRECT_URI));
                if (fbRedirectUri == null || !url.toLowerCase().startsWith(fbRedirectUri.toLowerCase())) {
                    return super.shouldOverrideUrlLoading(view, url);
                }
                BrowserActivity.this.handelFacebookResult(url);
                return true;
            }
        });
        this.mWebView.loadUrl(url);
    }

    private void handelFacebookResult(String url) {
        if (url.contains(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID)) {
            if (Uri.parse(url).getQueryParameter(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID) == null) {
                finishWithCanceledResult();
                return;
            }
            new VerifyFacebookPost().execute(new String[]{postId});
            return;
        }
        finishWithCanceledResult();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back /*2131558472*/:
                finish();
                return;
            default:
                return;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || this.mWebView.getVisibility() != 0) {
            return super.onKeyDown(keyCode, event);
        }
        goBack(keyCode, event);
        return true;
    }

    private void finishWithOkResult() {
        setResult(-1);
        finish();
    }

    private void finishWithCanceledResult() {
        setResult(0);
        finish();
    }

    private void goBack(int keyCode, KeyEvent event) {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onKeyDown(keyCode, event);
        }
    }

    public static void openInSystemBrowser(Context context, String url) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
    }

    public static void openInPlayStore(Context context, String appId, String medium) {
        String appDetails = "details?id=" + appId + "&referrer=" + ("utm_source%3D" + Device.getInstance().getClientPackage() + "%26utm_medium%3D" + medium);
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://" + appDetails)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/" + appDetails)));
        }
    }

    public static void openSearchResultInPlayStore(Context context, String keyword) {
        keyword = URLEncoder.encode(keyword);
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=" + keyword)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/search?q=" + keyword)));
        }
    }

    public static void getHelp(Context context) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(EXTRA_URI, getContactUrl());
        intent.putExtra(EXTRA_TITLE, R.string.tips_help);
        context.startActivity(intent);
    }

    private static String getContactUrl() {
        String contactUrl = Settings.getInstance().getContactURL();
        if (!UserModel.getInstance().isLogged()) {
            return contactUrl;
        }
        contactUrl = contactUrl + "def/Field4=%s&Field5=%s&Field2=%s";
        return String.format(Locale.US, contactUrl, new Object[]{user.getFirstName(), user.getLastName(), user.getEmail()});
    }
}
