package com.appnana.android.giftcardrewards;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnana.android.giftcardrewards.adapter.HistoryAdapter;
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.model.RewardModel;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.giftcardrewards.service.WebService;
import com.appnana.android.net.HttpClient;
import com.appnana.android.offerwall.R;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.offerwall.model.AppNana;
import com.appnana.android.offerwall.model.AppNanaHistory.Offer;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;

public class HistoryActivity extends ListActivity implements OnClickListener {
    private OnClickListener acceptOfferClickListener = new OnClickListener() {
        public void onClick(View view) {
            HistoryActivity.this.openBrowserToDownloadOffer();
            HistoryActivity.this.mDialog.dismiss();
        }
    };
    private AbstractOffer clickedOffer;
    private HistoryAdapter mAdapter;
    private Button mBtnBack;
    private Button mBtnHelp;
    private Button mBtnLoadMore;
    private AlertDialog mDialog;
    private List<Offer> mHistory;
    private RelativeLayout mLayoutPopup;
    private TextView mTxtTips;
    private OnClickListener onOpenPlayStoreButtonClicked = new OnClickListener() {
        public void onClick(View v) {
            Offer historyOffer = (Offer) HistoryActivity.this.clickedOffer;
            if (historyOffer.needTracking()) {
                HistoryActivity.this.openBrowserToDownloadOffer();
            } else {
                BrowserActivity.openSearchResultInPlayStore(HistoryActivity.this, historyOffer.getKeyword());
            }
        }
    };

    private class GetOfferHistoryTask extends AsyncTask<String, Void, List<Offer>> {
        private GetOfferHistoryTask() {
        }

        protected List<Offer> doInBackground(String... params) {
            try {
                return UserCommand.create().getHistory(HistoryActivity.this.mHistory.size());
            } catch (IOException e) {
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e.toString());
                return new ArrayList();
            }
        }

        protected void onPostExecute(List<Offer> result) {
            super.onPostExecute(result);
            if (HistoryActivity.this.mHistory != null && HistoryActivity.this.mAdapter != null) {
                int offerCount = result.size();
                if (offerCount < 5) {
                    HistoryActivity.this.mBtnLoadMore.setVisibility(8);
                    if (HistoryActivity.this.mHistory.isEmpty()) {
                        ((TextView) HistoryActivity.this.getListView().getEmptyView()).setText(R.string.no_history);
                    }
                } else {
                    HistoryActivity.this.mBtnLoadMore.setText(R.string.tips_load_more_offers);
                    HistoryActivity.this.mBtnLoadMore.setOnClickListener(HistoryActivity.this);
                }
                if (offerCount != 0) {
                    HistoryActivity.this.mHistory.addAll(result);
                    HistoryActivity.this.mAdapter.notifyDataSetChanged();
                }
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            HistoryActivity.this.mBtnLoadMore.setText(R.string.loading);
            HistoryActivity.this.mBtnLoadMore.setOnClickListener(null);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
        findViews();
        setListeners();
        initData();
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

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(UserModel.EXTRA_NAME, UserModel.getInstance());
        outState.putParcelable(Settings.EXTRA_NAME, Settings.getInstance());
        if (WebService.localContext != null) {
            CookieStore cookieStore = new BasicCookieStore();
            List<Cookie> cookies = ((BasicCookieStore) WebService.localContext.getAttribute("http.cookie-store")).getCookies();
            Bundle bundle = new Bundle();
            int cookieSize = cookies.size();
            for (int i = 0; i < cookieSize; i++) {
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

    private void restoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(UserModel.EXTRA_NAME)) {
            UserModel.restoreInstance((UserModel) savedInstanceState.getParcelable(UserModel.EXTRA_NAME));
        }
        if (savedInstanceState.containsKey(Settings.EXTRA_NAME)) {
            Settings.restoreInstance((Settings) savedInstanceState.getParcelable(Settings.EXTRA_NAME));
        }
        if (savedInstanceState.containsKey("http.cookie-store")) {
            BasicCookieStore cookieStore = new BasicCookieStore();
            Bundle bundle = savedInstanceState.getBundle("http.cookie-store");
            int cookieSize = bundle.size();
            for (int i = 0; i < cookieSize; i++) {
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

    private void findViews() {
        this.mTxtTips = (TextView) findViewById(R.id.txt_tips);
        this.mBtnBack = (Button) findViewById(R.id.btn_back);
        this.mBtnHelp = (Button) findViewById(R.id.btn_help);
        this.mBtnLoadMore = (Button) findViewById(R.id.btn_load_more);
        this.mLayoutPopup = (RelativeLayout) findViewById(R.id.layout_popup);
    }

    private void setListeners() {
        this.mBtnBack.setOnClickListener(this);
        this.mBtnLoadMore.setOnClickListener(this);
        this.mBtnHelp.setOnClickListener(this);
        this.mTxtTips.setOnClickListener(this);
        this.mLayoutPopup.setOnClickListener(this);
    }

    private void initData() {
        AppNanaApp app = (AppNanaApp) getApplication();
        this.mHistory = new ArrayList();
        this.mAdapter = new HistoryAdapter(this, this.mHistory, app.getOfferMap());
        setListAdapter(this.mAdapter);
        loadMore();
    }

    private void initView() {
        getListView().addHeaderView(getLayoutInflater().inflate(R.layout.widget_history_header, getListView(), false));
        getListView().addFooterView(getLayoutInflater().inflate(R.layout.widget_history_footer, getListView(), false));
    }

    private void showTips() {
        this.mLayoutPopup.setVisibility(0);
    }

    private void hideTips() {
        this.mLayoutPopup.setVisibility(8);
    }

    private void loadMore() {
        new GetOfferHistoryTask().execute(new String[0]);
    }

    private void openBrowserToDownloadOffer() {
        BrowserActivity.openInSystemBrowser(this, this.clickedOffer.getActionUrl());
    }

    public void finish() {
        this.mHistory.clear();
        this.mHistory = null;
        this.mAdapter = null;
        super.finish();
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        this.clickedOffer = this.mAdapter.getItem(position - 1);
        if (this.clickedOffer != null) {
            Offer historyOffer = this.clickedOffer;
            if (historyOffer.isInHouseOffer() && historyOffer.needTracking()) {
                historyOffer.setActionUrl(AppNana.Offer.setTrackingParams(historyOffer.getActionUrl(), UserModel.getInstance().getCustomID()));
            }
            if (historyOffer.isSearchOffer()) {
                AlertDialog.alertSearchOffer(this, historyOffer.getKeyword(), historyOffer.getAppId(), this.onOpenPlayStoreButtonClicked);
            } else {
                this.mDialog = AlertDialog.confirm((Context) this, (int) R.string.tips_important, this.clickedOffer.getActionMessage(), (int) R.string.tips_go, this.acceptOfferClickListener);
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back /*2131558472*/:
                finish();
                return;
            case R.id.layout_popup /*2131558480*/:
                hideTips();
                return;
            case R.id.btn_help /*2131558513*/:
                BrowserActivity.getHelp(this);
                return;
            case R.id.btn_load_more /*2131558569*/:
                loadMore();
                return;
            case R.id.txt_tips /*2131558570*/:
                showTips();
                return;
            default:
                return;
        }
    }
}
