package com.appnana.android.giftcardrewards;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.giftcardrewards.service.WebService;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

public abstract class GameCenterBaseActivity extends Activity {
    public static final int LOGIN_RESULT_CODE = 1;
    public static final int REGISTER_RESULT_CODE = 2;
    public static final int REQUEST_CODE = 2;
    protected final String JAVASCRIPT_NAME = "android";
    protected boolean isFullscreenMode;
    protected OnClickListener loginButtonClickListener = new OnClickListener() {
        public void onClick(View v) {
            GameCenterBaseActivity.this.finish(GameCenterBaseActivity.LOGIN_RESULT_CODE);
        }
    };
    protected ProgressBar mProgressBar;
    protected OnClickListener registerButtonClickListener = new OnClickListener() {
        public void onClick(View v) {
            GameCenterBaseActivity.this.finish(GameCenterBaseActivity.REQUEST_CODE);
        }
    };

    public class GameCenterJavascriptInterface {
        Context mContext;

        public GameCenterJavascriptInterface(Context context) {
            this.mContext = context;
        }

        public String getGamingIntervals() {
            Object[] objArr = new Object[GameCenterBaseActivity.LOGIN_RESULT_CODE];
            objArr[0] = Settings.getInstance().getGamingIntervals();
            return String.format("{\"gaming_interval\":\"%s\"}", objArr);
        }

        public String getNanaerInfo() {
            Object[] objArr = new Object[GameCenterBaseActivity.REQUEST_CODE];
            objArr[0] = Integer.valueOf(UserModel.getInstance().getGamingTimes());
            objArr[GameCenterBaseActivity.LOGIN_RESULT_CODE] = UserModel.getInstance().getLastGamingTime();
            return String.format("{\"gaming_times\":%d,\"last_gaming_times\":\"%s\"}", objArr);
        }

        public String getNanaerSessionId() {
            if (WebService.localContext == null) {
                return null;
            }
            for (Cookie cookie : ((BasicCookieStore) WebService.localContext.getAttribute("http.cookie-store")).getCookies()) {
                if (cookie.getName().equals("sessionid")) {
                    return cookie.getValue();
                }
            }
            return null;
        }

        public void showAnonymousNanaerDialog(OnClickListener loginButtonClickListener, OnClickListener registerButtonClickListener) {
            AlertDialog.confirm(this.mContext, (int) R.string.tips_sorry, (int) R.string.tips_must_login_or_register, (int) R.string.tips_login, loginButtonClickListener, (int) R.string.tips_register, registerButtonClickListener);
        }
    }

    protected abstract void setup();

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        findViews();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
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

    public void finish(int resultCode) {
        setResult(resultCode);
        finish();
    }

    protected void findViews() {
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    protected void enterFullscreen() {
        if (!this.isFullscreenMode) {
            getWindow().addFlags(1024);
            this.isFullscreenMode = true;
        }
    }

    protected void exitFullscreen() {
        if (this.isFullscreenMode) {
            getWindow().clearFlags(1024);
            this.isFullscreenMode = false;
        }
    }

    protected void setFullscreenMode(boolean fullscreen) {
        if (fullscreen) {
            enterFullscreen();
        } else {
            exitFullscreen();
        }
    }

    protected boolean isInGamePage(String url) {
        return url.contains("?id=");
    }

    protected void changeProgress(int progressInPercent) {
        if (progressInPercent >= 100) {
            this.mProgressBar.setVisibility(8);
            return;
        }
        this.mProgressBar.setVisibility(0);
        this.mProgressBar.setProgress(progressInPercent);
    }
}
