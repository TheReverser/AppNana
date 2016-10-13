package com.appnana.android.giftcardrewards;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.exception.AccessForbiddenException;
import com.appnana.android.giftcardrewards.exception.AccountException;
import com.appnana.android.giftcardrewards.exception.EmptyPasswordException;
import com.appnana.android.giftcardrewards.exception.InvalidEmailException;
import com.appnana.android.giftcardrewards.exception.MultipleDevicesException;
import com.appnana.android.giftcardrewards.exception.UnknownException;
import com.appnana.android.giftcardrewards.model.AppNanaPrefrences;
import com.appnana.android.giftcardrewards.model.OfferModel;
import com.appnana.android.giftcardrewards.model.RewardModel;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.utils.MapizLog;
import com.chartboost.sdk.Chartboost;
import com.facebook.BuildConfig;
import com.facebook.internal.NativeProtocol;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class SplashActivity extends Activity {
    private final int GMS_REQUEST_CODE = 1;
    private final int SPLASH_DISPLAY_LENGHT = 1000;
    private Chartboost chartboost;
    private boolean hasFinisedLoginAction = false;
    private boolean hasFinishedCountdown = false;
    private boolean hasGotRewards = false;
    private boolean hasGotSettings = false;
    private boolean isGmsAvailable = false;
    private ProgressBar mProBarLoading;

    private class GetRewardsTask extends AsyncTask<Void, Void, Void> {
        private GetRewardsTask() {
        }

        protected Void doInBackground(Void... arg0) {
            RewardModel.getInstance(SplashActivity.this);
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            dispatchGotRewardsEvent();
        }

        private void dispatchGotRewardsEvent() {
            SplashActivity.this.hasGotRewards = true;
            SplashActivity.this.startMainActivity();
        }
    }

    private class GetSettingsTask extends AsyncTask<Void, Void, Boolean> {
        int errorRes;

        private GetSettingsTask() {
        }

        protected Boolean doInBackground(Void... arg0) {
            try {
                UserCommand.create().getSettings();
                return Boolean.valueOf(true);
            } catch (Exception e) {
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e.toString());
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            SplashActivity.this.hasGotSettings = true;
            if (result.booleanValue()) {
                dispatchGotSettingsEvent();
            } else {
                SplashActivity.this.dispatchRequestFailedEvent(this.errorRes);
            }
        }

        private void dispatchGotSettingsEvent() {
            if (SplashActivity.this.checkUpdate()) {
                OnClickListener startDownload = new OnClickListener() {
                    public void onClick(View v) {
                        SplashActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Settings.getInstance().getUpdateURL())));
                        SplashActivity.this.finish();
                    }
                };
                SplashActivity.this.mProBarLoading.setVisibility(8);
                AlertDialog.alert(SplashActivity.this, R.string.update_required, R.string.tips_install_new, R.string.btn_update, startDownload);
                return;
            }
            SplashActivity.this.login();
        }
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean> {
        int errorRes;

        private LoginTask() {
        }

        protected Boolean doInBackground(String... params) {
            try {
                UserModel.createForLoginOrRegister(SplashActivity.this, params[0], params[1]);
                UserCommand.create().login(false);
                return Boolean.valueOf(true);
            } catch (InvalidEmailException e) {
                this.errorRes = R.string.invalid_email_address;
                return Boolean.valueOf(false);
            } catch (EmptyPasswordException e2) {
                this.errorRes = R.string.must_input_password;
                return Boolean.valueOf(false);
            } catch (MultipleDevicesException e3) {
                this.errorRes = R.string.error_multiple_devices;
                return Boolean.valueOf(false);
            } catch (AccessForbiddenException e4) {
                this.errorRes = R.string.error_irregular_activity;
                return Boolean.valueOf(false);
            } catch (AccountException e5) {
                this.errorRes = R.string.error_information;
                return Boolean.valueOf(false);
            } catch (UnknownException e6) {
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            } catch (Exception e7) {
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e7.toString());
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            SplashActivity.this.hasFinisedLoginAction = true;
            if (result.booleanValue()) {
                dispatchLoggedInEvent();
            } else {
                dispatchLoginFailedEvent();
            }
        }

        private void dispatchLoggedInEvent() {
            SplashActivity.this.startMainActivity();
        }

        private void dispatchLoginFailedEvent() {
            AlertDialog.alert(SplashActivity.this, R.string.tips_sorry, this.errorRes, R.string.btn_ok, new OnClickListener() {
                public void onClick(View v) {
                    SplashActivity.this.startMainActivity();
                }
            });
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkGms();
        findViews();
        initialize();
        configureChartboost();
    }

    protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(this);
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
        preCacheChartboost();
    }

    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
        this.chartboost.onStop(this);
    }

    private void checkGms() {
        int gmsCheckResult = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (gmsCheckResult == 0) {
            this.isGmsAvailable = true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(gmsCheckResult, this, 1).show();
        }
        startMainActivity();
    }

    private void findViews() {
        this.mProBarLoading = (ProgressBar) findViewById(R.id.probar_loading);
    }

    private void initialize() {
        new GetSettingsTask().execute(new Void[0]);
        new GetRewardsTask().execute(new Void[0]);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashActivity.this.dispatchFinishedCountdownEvent();
            }
        }, 1000);
    }

    private void startMainActivity() {
        if (this.hasFinishedCountdown && this.hasGotSettings && this.hasGotRewards && this.hasFinisedLoginAction && this.isGmsAvailable) {
            startMainActivityDirectly();
        }
    }

    private void startMainActivityDirectly() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void dispatchFinishedCountdownEvent() {
        this.hasFinishedCountdown = true;
        startMainActivity();
    }

    private boolean checkUpdate() {
        return Settings.getInstance().getVersionCode() > BuildConfig.VERSION_CODE;
    }

    private void login() {
        SharedPreferences settings = getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0);
        String email = settings.getString(AppNanaPrefrences.PREF_EMAIL, BuildConfig.VERSION_NAME);
        String password = settings.getString(AppNanaPrefrences.PREF_PASSWORD, BuildConfig.VERSION_NAME);
        if (email.equals(BuildConfig.VERSION_NAME) || password.equals(BuildConfig.VERSION_NAME)) {
            this.hasFinisedLoginAction = true;
            startMainActivity();
            return;
        }
        new LoginTask().execute(new String[]{email, password});
    }

    private void dispatchRequestFailedEvent(int errorRes) {
        AlertDialog.alert(this, R.string.tips_sorry, errorRes, R.string.btn_ok, new OnClickListener() {
            public void onClick(View v) {
                SplashActivity.this.startMainActivityDirectly();
            }
        });
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    private void configureChartboost() {
        this.chartboost = Chartboost.sharedChartboost();
        this.chartboost.onCreate(this, OfferModel.CHARTBOOST_APP_ID, OfferModel.CHARTBOOST_APP_SIGNATURE, null);
    }

    private void preCacheChartboost() {
        this.chartboost.onStart(this);
        this.chartboost.startSession();
        this.chartboost.cacheInterstitial();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            checkGms();
        }
    }
}
