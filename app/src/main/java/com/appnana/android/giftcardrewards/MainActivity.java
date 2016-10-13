package com.appnana.android.giftcardrewards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.exception.AccessForbiddenException;
import com.appnana.android.giftcardrewards.exception.SessionExpiredException;
import com.appnana.android.giftcardrewards.exception.UnknownException;
import com.appnana.android.giftcardrewards.model.AppNanaPrefrences;
import com.appnana.android.giftcardrewards.model.OfferModel;
import com.appnana.android.giftcardrewards.model.RewardModel;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.giftcardrewards.service.WebService;
import com.appnana.android.net.HttpClient;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import com.chartboost.sdk.Chartboost;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.immersion.hapticmediasdk.HapticContentSDK;
import com.viewpagerindicator.AppNanaTabPageIndicator;
import com.vungle.sdk.VunglePub.Gender;
import java.util.Date;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;

public class MainActivity extends SherlockFragmentActivity {
    public static final int PAGE_INDEX_INVITATION = 2;
    public static final int PAGE_INDEX_OFFER = 1;
    public static final int PAGE_INDEX_REWARD = 0;
    private final int[] TITLE = new int[]{R.string.nav_rewards, R.string.nav_get_nanas, R.string.nav_invite};
    private boolean autoRefreshNanaerInfo = true;
    private Chartboost chartboost;
    Button mBtnAuth;
    private AlertDialog mDialog;
    private SherlockFragment[] mFragments = new SherlockFragment[3];
    public AppNanaTabPageIndicator mIndicator;
    public int mLastPageIndex = 0;
    private int mLastPoints = -1;
    private LinearLayout mLayoutAuth;
    public RelativeLayout mLayoutLoading;
    private LinearLayout mLayoutPointsShown;
    ViewPager mPager;
    TextView mTxtPoints;
    private boolean needUpdateNanasUIManually;
    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        public void onPageSelected(int index) {
            switch (index) {
                case Gender.MALE /*0*/:
                    MainActivity.this.updateUserPoints();
                    MainActivity.this.initAuthLayout();
                    return;
                default:
                    return;
            }
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    };
    private OnClickListener startToAuth = new OnClickListener() {
        public void onClick(View v) {
            LoginDialogFragment.getInstance().show(MainActivity.this.getSupportFragmentManager(), LoginDialogFragment.TAG);
        }
    };

    class AppNanaRewardAdapter extends FragmentPagerAdapter {
        public AppNanaRewardAdapter(FragmentManager fm) {
            super(fm);
        }

        public SherlockFragment getItem(int position) {
            return MainActivity.this.mFragments[position];
        }

        public CharSequence getPageTitle(int position) {
            return MainActivity.this.getString(MainActivity.this.TITLE[position]);
        }

        public int getCount() {
            return MainActivity.this.TITLE.length;
        }
    }

    private class RefreshPointsTask extends AsyncTask<Void, Void, Boolean> {
        int errorRes;

        private RefreshPointsTask() {
        }

        protected Boolean doInBackground(Void... params) {
            try {
                UserCommand.create().getUserInfo(false, false, UserModel.getInstance().needMoreNanas, UserModel.getInstance().needMoreInvites);
                return Boolean.valueOf(true);
            } catch (AccessForbiddenException e) {
                this.errorRes = R.string.error_irregular_activity;
                return Boolean.valueOf(false);
            } catch (SessionExpiredException e2) {
                this.errorRes = R.string.error_session_expired;
                return Boolean.valueOf(false);
            } catch (UnknownException e3) {
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            } catch (Exception e4) {
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e4.toString());
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result.booleanValue()) {
                MainActivity.this.dispatchGotPoints();
                return;
            }
            AlertDialog.alert(MainActivity.this, (int) R.string.tips_sorry, this.errorRes);
            if (this.errorRes == R.string.error_irregular_activity || this.errorRes == R.string.error_session_expired) {
                MainActivity.this.onLoggedOut();
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity.this.mLastPoints = UserModel.getInstance().getPoints();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock_Light_NoActionBar);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(UserModel.EXTRA_NAME)) {
                UserModel.restoreInstance((UserModel) savedInstanceState.getParcelable(UserModel.EXTRA_NAME));
            }
            if (savedInstanceState.containsKey(UserModel.EXTRA_NAME)) {
                Settings.restoreInstance((Settings) savedInstanceState.getParcelable(Settings.EXTRA_NAME));
            }
            if (savedInstanceState.containsKey("http.cookie-store")) {
                BasicCookieStore cookieStore = new BasicCookieStore();
                Bundle bundle = savedInstanceState.getBundle("http.cookie-store");
                int cookieSize = bundle.size();
                for (int i = 0; i < cookieSize; i += PAGE_INDEX_OFFER) {
                    Bundle cookieBundle = bundle.getBundle("cookie" + i);
                    String name = cookieBundle.getString(ShareConstants.WEB_DIALOG_PARAM_NAME);
                    String value = cookieBundle.getString("value");
                    String domain = cookieBundle.getString("domain");
                    String path = cookieBundle.getString("path");
                    Long expiry = null;
                    if (cookieBundle.containsKey("expiry")) {
                        expiry = Long.valueOf(cookieBundle.getLong("expiry"));
                    }
                    int version = cookieBundle.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
                    BasicClientCookie cookie = new BasicClientCookie(name, value);
                    cookie.setDomain(domain);
                    cookie.setPath(path);
                    if (expiry != null) {
                        cookie.setExpiryDate(new Date(expiry.longValue()));
                    }
                    cookie.setVersion(version);
                    cookieStore.addCookie(cookie);
                }
                WebService.localContext = new BasicHttpContext();
                WebService.localContext.setAttribute("http.cookie-store", cookieStore);
            }
            RewardModel.restoreInstance(this);
            if (Device.getInstance() == null) {
                Device.newInstance(getApplicationContext());
            }
            if (HttpClient.getInstance() == null) {
                HttpClient.newInstance(getApplicationContext());
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
        initialize();
    }

    private void findViews() {
        this.mBtnAuth = (Button) findViewById(R.id.btn_auth);
        this.mTxtPoints = (TextView) findViewById(R.id.txt_poitns);
        this.mPager = (ViewPager) findViewById(R.id.pager);
        this.mIndicator = (AppNanaTabPageIndicator) findViewById(R.id.indicator);
        this.mLayoutLoading = (RelativeLayout) findViewById(R.id.layout_loading);
        this.mLayoutPointsShown = (LinearLayout) findViewById(R.id.layout_points_shown);
        this.mLayoutAuth = (LinearLayout) findViewById(R.id.layout_auth);
    }

    private void setListeners() {
        this.mBtnAuth.setOnClickListener(this.startToAuth);
        this.mIndicator.setOnPageChangeListener(this.onPageChangeListener);
    }

    private void initialize() {
        initTabBar();
        getLastPointsFromPref();
        dispatchGotPoints();
        configureChartboost();
    }

    private void configureChartboost() {
        this.chartboost = Chartboost.sharedChartboost();
        this.chartboost.onCreate(this, OfferModel.CHARTBOOST_APP_ID, OfferModel.CHARTBOOST_APP_SIGNATURE, null);
    }

    public void updateUserPoints() {
        this.mTxtPoints.setText(UserModel.getInstance().getPointsShow());
    }

    public void initAuthLayout() {
        if (UserModel.getInstance().isLogged()) {
            this.mLayoutAuth.setVisibility(8);
            this.mLayoutPointsShown.setVisibility(0);
            return;
        }
        this.mLayoutAuth.setVisibility(0);
        this.mLayoutPointsShown.setVisibility(8);
    }

    private void initTabBar() {
        RewardFragment rewardFragment = null;
        OfferListFragment offerListFragment = null;
        InvitationFragment invitationFragment = null;
        if (getSupportFragmentManager().getFragments() != null) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof RewardFragment) {
                    rewardFragment = (RewardFragment) fragment;
                } else if (fragment instanceof OfferListFragment) {
                    offerListFragment = (OfferListFragment) fragment;
                } else if (fragment instanceof InvitationFragment) {
                    invitationFragment = (InvitationFragment) fragment;
                }
            }
        }
        SherlockFragment[] sherlockFragmentArr = this.mFragments;
        if (rewardFragment == null) {
            rewardFragment = new RewardFragment();
        }
        sherlockFragmentArr[0] = rewardFragment;
        sherlockFragmentArr = this.mFragments;
        if (offerListFragment == null) {
            offerListFragment = new OfferListFragment();
        }
        sherlockFragmentArr[PAGE_INDEX_OFFER] = offerListFragment;
        sherlockFragmentArr = this.mFragments;
        if (invitationFragment == null) {
            invitationFragment = new InvitationFragment();
        }
        sherlockFragmentArr[PAGE_INDEX_INVITATION] = invitationFragment;
        this.mPager.setAdapter(new AppNanaRewardAdapter(getSupportFragmentManager()));
        this.mIndicator.setViewPager(this.mPager);
    }

    public void setAutoRefreshNanaerInfo(boolean autoRefreshNanaerInfo) {
        this.autoRefreshNanaerInfo = autoRefreshNanaerInfo;
    }

    public void setNeedUpdateNanasUIManually(boolean needUpdateNanasUIManually) {
        this.needUpdateNanasUIManually = needUpdateNanasUIManually;
    }

    private void refreshPoints() {
        if (UserModel.getInstance().isLogged()) {
            new RefreshPointsTask().execute(new Void[0]);
        }
    }

    public void dispatchGotPoints() {
        int myPoints = UserModel.getInstance().getPoints();
        if (this.mLastPoints != -1 && this.mLastPoints < myPoints) {
            AlertDialog.alertNanas((Context) this, (int) R.string.tips_success, UserModel.getInstance().getPointsShow(myPoints - this.mLastPoints));
        }
        updateUserPoints();
    }

    private void checkNewUserAndDailyLogin() {
        MapizLog.d("isNewUser", String.valueOf(UserModel.getInstance().isNew));
        MapizLog.d("isDailyLogin", String.valueOf(UserModel.getInstance().isDailyLogin));
        MapizLog.d("needMoreNanas", String.valueOf(UserModel.getInstance().needMoreNanas));
        MapizLog.d("needMoreInvites", String.valueOf(UserModel.getInstance().needMoreInvites));
        if (UserModel.getInstance().isNew) {
            AlertDialog.alertNanas((Context) this, (int) R.string.tips_success, Settings.getRegisterPointsShow());
            UserModel.getInstance().isNew = false;
        }
        if (UserModel.getInstance().isDailyLogin) {
            AlertDialog.alertNanas((Context) this, (int) R.string.tips_daily_nanas, Settings.getInstance().getDailyPointsShow());
            UserModel.getInstance().isDailyLogin = false;
        }
        if (UserModel.getInstance().needMoreNanas > 0 || UserModel.getInstance().needMoreInvites > 0) {
            OnClickListener gotoGetNanasPage = new OnClickListener() {
                public void onClick(View v) {
                    MainActivity.this.mDialog.dismiss();
                    MainActivity.this.mIndicator.setCurrentItem(MainActivity.PAGE_INDEX_OFFER);
                }
            };
            OnClickListener gotoInvitePage = new OnClickListener() {
                public void onClick(View v) {
                    MainActivity.this.mDialog.dismiss();
                    MainActivity.this.mIndicator.setCurrentItem(MainActivity.PAGE_INDEX_INVITATION);
                }
            };
            int needMoreNanas = UserModel.getInstance().needMoreNanas;
            this.mDialog = AlertDialog.alertNoDailyNanas(this, UserModel.getInstance().getPointsShow(needMoreNanas), UserModel.getInstance().needMoreInvites, gotoInvitePage, gotoGetNanasPage);
            UserModel.getInstance().needMoreNanas = 0;
            UserModel.getInstance().needMoreInvites = 0;
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (this.autoRefreshNanaerInfo) {
            refreshPoints();
        }
        if (this.needUpdateNanasUIManually) {
            this.mLastPoints = UserModel.getInstance().getPoints();
            UserModel.getInstance().addPoints(5);
            dispatchGotPoints();
        }
        setAutoRefreshNanaerInfo(true);
        setNeedUpdateNanasUIManually(false);
    }

    protected void onStart() {
        super.onStart();
        initAuthLayout();
        updateUserPoints();
        checkNewUserAndDailyLogin();
        this.chartboost.onStart(this);
        this.chartboost.startSession();
        if (Settings.getInstance().needShownAd(Settings.MAIN_AD_FLAG)) {
            this.chartboost.showInterstitial();
        }
        FlurryAgent.onStartSession(this);
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    protected void onStop() {
        super.onStop();
        this.chartboost.onStop(this);
        FlurryAgent.onEndSession(this);
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.chartboost.onDestroy(this);
    }

    public void onBackPressed() {
        if (!this.chartboost.onBackPressed()) {
            super.onBackPressed();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PAGE_INDEX_OFFER /*1*/:
                if (resultCode == -1) {
                    UserModel.getInstance().addPoints(Settings.getInstance().getFacebookInviteNanas());
                    dispatchGotPoints();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void initFragmentsUI() {
        ((RewardFragment) this.mFragments[0]).initHowToLayout();
        ((OfferListFragment) this.mFragments[PAGE_INDEX_OFFER]).onLoggedIn();
        ((InvitationFragment) this.mFragments[PAGE_INDEX_INVITATION]).initInvitationCode();
    }

    public void onLoggedIn() {
        initAuthLayout();
        updateUserPoints();
        checkNewUserAndDailyLogin();
        initFragmentsUI();
    }

    public void onLoggedOut() {
        getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).edit().remove(AppNanaPrefrences.PREF_EMAIL).remove(AppNanaPrefrences.PREF_PASSWORD).apply();
        UserModel.getInstance().logout();
        this.mIndicator.setCurrentItem(0);
        initFragmentsUI();
        WebService.localContext = null;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(UserModel.EXTRA_NAME, UserModel.getInstance());
        outState.putParcelable(Settings.EXTRA_NAME, Settings.getInstance());
        if (WebService.localContext != null) {
            CookieStore cookieStore = new BasicCookieStore();
            List<Cookie> cookies = ((BasicCookieStore) WebService.localContext.getAttribute("http.cookie-store")).getCookies();
            Bundle bundle = new Bundle();
            int cookieSize = cookies.size();
            for (int i = 0; i < cookieSize; i += PAGE_INDEX_OFFER) {
                Cookie cookie = (Cookie) cookies.get(i);
                Bundle cookieBundle = new Bundle();
                cookieBundle.putString(ShareConstants.WEB_DIALOG_PARAM_NAME, cookie.getName());
                cookieBundle.putString("value", cookie.getValue());
                cookieBundle.putString("domain", cookie.getDomain());
                cookieBundle.putString("path", cookie.getPath());
                if (cookie.getExpiryDate() != null) {
                    cookieBundle.putLong("expiry", cookie.getExpiryDate().getTime());
                }
                cookieBundle.putInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, cookie.getVersion());
                bundle.putBundle("cookie" + i, cookieBundle);
            }
            outState.putBundle("http.cookie-store", bundle);
        }
    }

    public void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
        setLastPoints();
    }

    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
        checkToAlertRatingDialog();
    }

    private void checkToAlertRatingDialog() {
        if (UserModel.getInstance().isLogged() && UserModel.getInstance().getEarnedPoints() >= Settings.MIN_NANAS_TO_ALERT_RATING) {
            SharedPreferences settings = getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0);
            if (!settings.getBoolean(AppNanaPrefrences.PREF_RATING, false)) {
                AlertDialog.alertRating(this);
                settings.edit().putBoolean(AppNanaPrefrences.PREF_RATING, true).apply();
            }
        }
    }

    private void getLastPointsFromPref() {
        this.mLastPoints = getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).getInt(AppNanaPrefrences.PREF_LAST_POINTS, 0);
        if (UserModel.getInstance().isDailyLogin) {
            this.mLastPoints += Settings.getInstance().getDailyPoints();
        }
        if (UserModel.getInstance().isNew) {
            this.mLastPoints += HapticContentSDK.b044404440444ф04440444;
        }
    }

    public void setLastPoints() {
        this.mLastPoints = UserModel.getInstance().getPoints();
        getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).edit().putInt(AppNanaPrefrences.PREF_LAST_POINTS, this.mLastPoints).apply();
    }

    public void hideKeyboard(EditText[] editTexts) {
        EditText[] arr$ = editTexts;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$ += PAGE_INDEX_OFFER) {
            EditText editText = arr$[i$];
            if (editText.isFocused()) {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), PAGE_INDEX_INVITATION);
                return;
            }
        }
    }

    public void setLoadingLayoutVisibility(int visibility) {
        this.mLayoutLoading.setVisibility(visibility);
    }
}
