package com.appnana.android.giftcardrewards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.exception.AccessForbiddenException;
import com.appnana.android.giftcardrewards.exception.AlreadyInputException;
import com.appnana.android.giftcardrewards.exception.InvalidCodeException;
import com.appnana.android.giftcardrewards.exception.NotEnoughPointsException;
import com.appnana.android.giftcardrewards.exception.OfferAlreadyFinishedException;
import com.appnana.android.giftcardrewards.exception.OfferExpiredException;
import com.appnana.android.giftcardrewards.exception.OfferNotFoundException;
import com.appnana.android.giftcardrewards.exception.RfnConflictException;
import com.appnana.android.giftcardrewards.exception.RfnForbiddenException;
import com.appnana.android.giftcardrewards.exception.SelfCodeException;
import com.appnana.android.giftcardrewards.exception.SessionExpiredException;
import com.appnana.android.giftcardrewards.model.AppNanaPrefrences;
import com.appnana.android.giftcardrewards.model.OfferModel;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.offerwall.R;
import com.appnana.android.offerwall.controller.OfferController;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.offerwall.model.AppNana.Offer;
import com.appnana.android.offerwall.model.AppThis;
import com.appnana.android.offerwall.model.DisplayIO;
import com.appnana.android.offerwall.model.HangMyAds;
import com.appnana.android.offerwall.model.OfferListener;
import com.appnana.android.offerwall.views.OfferLayout;
import com.appnana.android.offerwall.views.OfferWallLayout;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import com.facebook.BuildConfig;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyV4VCAd;
import com.jirbo.adcolony.AdColonyV4VCListener;
import com.jirbo.adcolony.AdColonyV4VCReward;
import com.trialpay.android.base.TrialpayManager;
import com.vungle.log.Logger;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;
import com.vungle.sdk.VunglePub.Gender;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferListFragment extends TrackedSherlockFragment implements OnClickListener {
    private static final int OFFER_REQUEST_COUNT = 2;
    private OnClickListener acceptOfferClickListener = new OnClickListener() {
        public void onClick(View view) {
            OfferListFragment.this.mDialog.dismiss();
            AbstractOffer offer = OfferListFragment.this.mClickedOfferView.getModel();
            if (offer instanceof Offer) {
                OfferListFragment.this.clickAppNanaOffer((Offer) offer);
                return;
            }
            new AcceptOfferTask().execute(new AbstractOffer[]{offer});
        }
    };
    AdColonyAdListener adColonyAdListener = new AdColonyAdListener() {
        public void onAdColonyAdAttemptFinished(AdColonyAd adColonyAd) {
            if (adColonyAd.notShown() || adColonyAd.noFill()) {
                OfferListFragment.this.openVungleOfferWall();
            } else {
                OfferListFragment.this.onVideoEnd();
            }
        }

        public void onAdColonyAdStarted(AdColonyAd adColonyAd) {
            OfferListFragment.this.onVideoStart();
        }
    };
    AdColonyV4VCListener adColonyV4VCListener = new AdColonyV4VCListener() {
        public void onAdColonyV4VCReward(AdColonyV4VCReward reward) {
            if (reward.success()) {
                OfferListFragment.this.user.addPoints(reward.amount());
                OfferListFragment.this.getSherlockActivity().dispatchGotPoints();
            }
        }
    };
    private OnClickListener loginButtonClickListener = new OnClickListener() {
        public void onClick(View v) {
            LoginDialogFragment.getInstance().show(OfferListFragment.this.getFragmentManager(), LoginDialogFragment.TAG);
            OfferListFragment.this.mDialog.dismiss();
        }
    };
    AdColonyV4VCAd mAdColonyV4VCAd;
    private List<String> mAppNanaHistoryName;
    private List<Offer> mAppNanaOffers;
    private Button mBtnEnter;
    private Button mBtnHelpHistory;
    private OfferLayout mClickedOfferView;
    private SherlockFragmentActivity mContext;
    private AlertDialog mDialog;
    private EditText mEditInvitation;
    private ImageView mImgGameCenter;
    InterstitialAd mInterstitialAd;
    private LinearLayout mLayoutInvitation;
    private RelativeLayout mLayoutInvite;
    private LinearLayout mLayoutInviteInfo;
    private RelativeLayout mLayoutRegister;
    private OfferController mOfferController;
    private OfferWallLayout mOfferWallLayout;
    TrialpayManager mTrialpayManager;
    private TextView mTxtDailyRewardTips;
    private TextView mTxtInvitationNanas;
    private TextView mTxtInvitationTips;
    private TextView mTxtInviteFriends;
    private TextView mTxtInviteInfo;
    private TextView mTxtInviteInfoMessage;
    private TextView mTxtInviteNanas;
    private TextView mTxtRegisterNanas;
    private View mView;
    private OnClickListener offerClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (OfferListFragment.this.user.isLogged()) {
                OfferListFragment.this.mClickedOfferView = (OfferLayout) view;
                AbstractOffer offer = OfferListFragment.this.mClickedOfferView.getModel();
                if ((offer instanceof Offer) && ((Offer) offer).isFixed() && ((Offer) offer).getIdAsInt() != 1) {
                    OfferListFragment.this.clickAppNanaOffer((Offer) offer);
                    return;
                } else if ((offer instanceof Offer) && ((Offer) offer).isSearchOffer()) {
                    Offer appnanaOffer = (Offer) offer;
                    AlertDialog.alertSearchOffer(OfferListFragment.this.mContext, appnanaOffer.getKeyword(), appnanaOffer.getAppId(), OfferListFragment.this.onOpenPlayStoreButtonClicked);
                    return;
                } else {
                    OfferListFragment.this.mDialog = AlertDialog.confirm(OfferListFragment.this.mContext, (int) R.string.tips_important, offer.getDesc(), (int) R.string.tips_go, OfferListFragment.this.acceptOfferClickListener);
                    return;
                }
            }
            OfferListFragment.this.alertRegisterDialog();
        }
    };
    private OfferListener offerListener = new OfferListener() {
        public void onRequest() {
            MapizLog.d("start", "start request");
            OfferListFragment.this.mBtnHelpHistory.setVisibility(8);
        }

        public void onResponse(List<AbstractOffer> list) {
            ((AppNanaApp) OfferListFragment.this.mContext.getApplication()).setOfferMap(OfferListFragment.this.mOfferController.getOfferMap());
            OfferListFragment.this.mBtnHelpHistory.setVisibility(0);
            OfferListFragment.this.onOffersResponse();
        }
    };
    private int offerResponseCount;
    private OnClickListener onOpenPlayStoreButtonClicked = new OnClickListener() {
        public void onClick(View v) {
            OfferListFragment.this.clickAppNanaOffer((Offer) OfferListFragment.this.mClickedOfferView.getModel());
        }
    };
    private OnClickListener registerButtonClickListener = new OnClickListener() {
        public void onClick(View v) {
            RegisterDialogFragment.getInstance().show(OfferListFragment.this.getFragmentManager(), RegisterDialogFragment.TAG);
            OfferListFragment.this.mDialog.dismiss();
        }
    };
    private UserModel user;
    private final AdConfig vungleConfig = new AdConfig();
    EventListener vungleEventListener = new EventListener() {
        public void onVideoView(boolean isCompletedView, int watchedMillis, int videoDurationMillis) {
            if (isCompletedView) {
                OfferListFragment.this.mContext.runOnUiThread(new Runnable() {
                    public void run() {
                        OfferListFragment.this.user.addPoints(5);
                        OfferListFragment.this.getSherlockActivity().dispatchGotPoints();
                    }
                });
            }
        }

        public void onAdStart() {
            OfferListFragment.this.mContext.runOnUiThread(new Runnable() {
                public void run() {
                    OfferListFragment.this.onVideoStart();
                }
            });
        }

        public void onAdEnd(boolean wasCallToActionClicked) {
            OfferListFragment.this.mContext.runOnUiThread(new Runnable() {
                public void run() {
                    OfferListFragment.this.onVideoEnd();
                }
            });
        }

        public void onAdUnavailable(String reason) {
            OfferListFragment.this.mContext.runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.alert(OfferListFragment.this.mContext, (int) R.string.tips_sorry, (int) R.string.tips_no_offers);
                }
            });
        }

        public void onAdPlayableChanged(boolean isAdPlayable) {
        }
    };
    private final VunglePub vunglePub = VunglePub.getInstance();

    private class AcceptAppNanaOfferTask extends AsyncTask<Offer, Void, Boolean> {
        int errorRes;
        Offer offer;

        private AcceptAppNanaOfferTask() {
        }

        protected Boolean doInBackground(Offer... params) {
            UserCommand userCmd = UserCommand.create();
            try {
                this.offer = params[0];
                return Boolean.valueOf(userCmd.acceptOffer(this.offer.getId()));
            } catch (SessionExpiredException e) {
                this.errorRes = R.string.error_session_expired;
                return Boolean.valueOf(false);
            } catch (OfferNotFoundException e2) {
                this.errorRes = R.string.error_offer_not_found;
                return Boolean.valueOf(false);
            } catch (OfferExpiredException e3) {
                this.errorRes = R.string.error_offer_expired;
                return Boolean.valueOf(false);
            } catch (OfferAlreadyFinishedException e4) {
                this.errorRes = R.string.error_offer_already_finished;
                return Boolean.valueOf(false);
            } catch (AccessForbiddenException e5) {
                this.errorRes = R.string.error_irregular_activity;
                return Boolean.valueOf(false);
            } catch (Exception e6) {
                this.errorRes = R.string.error_under_maintenance;
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e6.toString());
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            ((MainActivity) OfferListFragment.this.mContext).mLayoutLoading.setVisibility(8);
            if (!result.booleanValue()) {
                AlertDialog.alert(OfferListFragment.this.mContext, (int) R.string.tips_sorry, this.errorRes);
                if (this.errorRes == R.string.error_irregular_activity || this.errorRes == R.string.error_session_expired) {
                    OfferListFragment.this.getSherlockActivity().onLoggedOut();
                }
            } else if (!this.offer.isSearchOffer() || this.offer.needTracking()) {
                this.offer.setActionUrl(Offer.setTrackingParams(this.offer.getActionUrl(), OfferListFragment.this.user.getCustomID()));
                OfferListFragment.this.openBrowserToDownloadOffer(this.offer);
            } else {
                OfferListFragment.this.showPlayStoreSearchResult(this.offer);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            OfferListFragment.this.getMainActivity().setLoadingLayoutVisibility(0);
        }
    }

    private class AcceptOfferTask extends AsyncTask<AbstractOffer, Void, Boolean> {
        int errorRes;
        AbstractOffer offer;

        private AcceptOfferTask() {
        }

        protected Boolean doInBackground(AbstractOffer... params) {
            UserCommand userCmd = UserCommand.create();
            try {
                this.offer = params[0];
                return Boolean.valueOf(userCmd.postHistory(this.offer));
            } catch (SessionExpiredException e) {
                this.errorRes = R.string.error_session_expired;
                return Boolean.valueOf(false);
            } catch (OfferNotFoundException e2) {
                this.errorRes = R.string.error_offer_not_found;
                return Boolean.valueOf(false);
            } catch (OfferExpiredException e3) {
                this.errorRes = R.string.error_offer_expired;
                return Boolean.valueOf(false);
            } catch (OfferAlreadyFinishedException e4) {
                this.errorRes = R.string.error_offer_already_finished;
                return Boolean.valueOf(false);
            } catch (AccessForbiddenException e5) {
                this.errorRes = R.string.error_irregular_activity;
                return Boolean.valueOf(false);
            } catch (Exception e6) {
                this.errorRes = R.string.error_under_maintenance;
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e6.toString());
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            ((MainActivity) OfferListFragment.this.mContext).mLayoutLoading.setVisibility(8);
            if (result.booleanValue()) {
                OfferListFragment.this.openBrowserToDownloadOffer(this.offer);
                return;
            }
            AlertDialog.alert(OfferListFragment.this.mContext, (int) R.string.tips_sorry, this.errorRes);
            if (this.errorRes == R.string.error_irregular_activity || this.errorRes == R.string.error_session_expired) {
                OfferListFragment.this.getSherlockActivity().onLoggedOut();
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            OfferListFragment.this.getMainActivity().setLoadingLayoutVisibility(0);
        }
    }

    private class GetAppNanaOffersTask extends AsyncTask<String, Void, List<Offer>> {
        private GetAppNanaOffersTask() {
        }

        protected List<Offer> doInBackground(String... params) {
            try {
                return UserCommand.create().getOffers();
            } catch (IOException e) {
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e.toString());
                return new ArrayList();
            }
        }

        protected void onPostExecute(List<Offer> result) {
            super.onPostExecute(result);
            OfferListFragment.this.mAppNanaOffers = result;
            OfferListFragment.this.onOffersResponse();
        }
    }

    private class GetOfferHistoryNameTask extends AsyncTask<String, Void, List<String>> {
        private GetOfferHistoryNameTask() {
        }

        protected List<String> doInBackground(String... params) {
            try {
                return UserCommand.create().getHistoryName();
            } catch (IOException e) {
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e.toString());
                return new ArrayList();
            }
        }

        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);
            OfferListFragment.this.mAppNanaHistoryName = result;
            OfferListFragment.this.onGotHistory();
        }
    }

    private class GetRfnTask extends AsyncTask<String, Void, Boolean> {
        int errorRes;

        private GetRfnTask() {
        }

        protected Boolean doInBackground(String... params) {
            try {
                return Boolean.valueOf(UserCommand.create().finishOfferRfn());
            } catch (AccessForbiddenException e) {
                this.errorRes = R.string.error_irregular_activity;
                e.printStackTrace();
                return Boolean.valueOf(false);
            } catch (RfnConflictException e2) {
                this.errorRes = R.string.error_rfn_conflict;
                e2.printStackTrace();
                return Boolean.valueOf(false);
            } catch (RfnForbiddenException e3) {
                this.errorRes = R.string.error_rfn_forbidden;
                e3.printStackTrace();
                return Boolean.valueOf(false);
            } catch (SessionExpiredException e4) {
                this.errorRes = R.string.error_session_expired;
                e4.printStackTrace();
                return Boolean.valueOf(false);
            } catch (Exception e5) {
                this.errorRes = R.string.error_under_maintenance;
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e5.toString());
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            OfferListFragment.this.mOfferWallLayout.removeAndShowNextOfferView(OfferListFragment.this.mClickedOfferView);
            MainActivity mainActivity = (MainActivity) OfferListFragment.this.mContext;
            mainActivity.mLayoutLoading.setVisibility(8);
            if (result.booleanValue()) {
                OfferListFragment.this.user.addPoints(OfferListFragment.this.user.getRfn());
                AlertDialog.alertNanas(OfferListFragment.this.mContext, (int) R.string.tips_success, OfferListFragment.this.user.getFormattedRfn());
                mainActivity.updateUserPoints();
            } else {
                AlertDialog.alert(OfferListFragment.this.mContext, (int) R.string.tips_sorry, this.errorRes);
                if (this.errorRes == R.string.error_irregular_activity || this.errorRes == R.string.error_session_expired) {
                    OfferListFragment.this.getSherlockActivity().onLoggedOut();
                }
            }
            OfferListFragment.this.user.setRfn(0);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            OfferListFragment.this.getMainActivity().setLoadingLayoutVisibility(0);
        }
    }

    private class VerifyInvitationCodeTask extends AsyncTask<String, Void, Boolean> {
        int errorRes;

        private VerifyInvitationCodeTask() {
        }

        protected Boolean doInBackground(String... params) {
            try {
                return Boolean.valueOf(UserCommand.create().isInvitationCodeVerified(params[0], OfferListFragment.this.user.getInvitationCode()));
            } catch (AlreadyInputException e) {
                this.errorRes = R.string.input_again_fail_body_2;
                return Boolean.valueOf(false);
            } catch (InvalidCodeException e2) {
                this.errorRes = R.string.invalid_invitation_code;
                return Boolean.valueOf(false);
            } catch (SelfCodeException e3) {
                this.errorRes = R.string.input_again_fail_body_1;
                return Boolean.valueOf(false);
            } catch (AccessForbiddenException e4) {
                this.errorRes = R.string.error_irregular_activity;
                return Boolean.valueOf(false);
            } catch (SessionExpiredException e5) {
                this.errorRes = R.string.error_session_expired;
                return Boolean.valueOf(false);
            } catch (NotEnoughPointsException e6) {
                this.errorRes = R.string.input_again_fail_body;
                return Boolean.valueOf(false);
            } catch (Exception e7) {
                this.errorRes = R.string.error_under_maintenance;
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e7.toString());
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            final MainActivity mainActivity = (MainActivity) OfferListFragment.this.mContext;
            mainActivity.mLayoutLoading.setVisibility(8);
            if (result.booleanValue()) {
                OfferListFragment.this.user.addPoints(Settings.getInstance().getInvitationPoints());
                OfferListFragment.this.user.addInputCodeCount();
                OfferListFragment.this.user.resetSentInvitaionCount();
                mainActivity.updateUserPoints();
                OfferListFragment.this.initJoinOffer();
                OfferListFragment.this.mDialog = AlertDialog.alertInvitaionSuccess(OfferListFragment.this.mContext, new OnClickListener() {
                    public void onClick(View v) {
                        mainActivity.mIndicator.setCurrentItem(OfferListFragment.OFFER_REQUEST_COUNT);
                        OfferListFragment.this.mDialog.dismiss();
                    }
                });
                OfferListFragment.this.mTxtInviteInfo.performClick();
                return;
            }
            AlertDialog.alert(OfferListFragment.this.mContext, this.errorRes == R.string.invalid_invitation_code ? R.string.tips_invalid : R.string.tips_sorry, this.errorRes);
            if (this.errorRes == R.string.error_irregular_activity || this.errorRes == R.string.error_session_expired) {
                OfferListFragment.this.getSherlockActivity().onLoggedOut();
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            OfferListFragment.this.getMainActivity().setLoadingLayoutVisibility(0);
        }
    }

    private void showPlayStoreSearchResult(Offer offer) {
        BrowserActivity.openSearchResultInPlayStore(this.mContext, offer.getKeyword());
        this.mOfferWallLayout.removeAndShowNextOfferView(this.mClickedOfferView);
    }

    private void openBrowserToDownloadOffer(AbstractOffer offer) {
        if (!((offer instanceof Offer) && ((Offer) offer).isSearchOffer())) {
            this.mAppNanaHistoryName.add(offer.getName());
        }
        BrowserActivity.openInSystemBrowser(this.mContext, offer.getActionUrl());
        this.mOfferWallLayout.removeAndShowNextOfferView(this.mClickedOfferView);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getSherlockActivity();
        this.user = UserModel.getInstance();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.activity_offerlist, container, false);
        return this.mView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        setListeners();
        initialize();
    }

    public void onResume() {
        super.onResume();
        if (this.mEditInvitation.hasFocus()) {
            this.mEditInvitation.clearFocus();
        }
        resumeVideoOfferwall();
    }

    public void onPause() {
        super.onPause();
        pauseVideoOfferwall();
        saveHistory();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onLoggedIn() {
        this.user = UserModel.getInstance();
        initJoinOffer();
        initAdcolonyOfferwall();
        initTrialpayOfferwall();
        resetOfferWall();
        requestOfferWall();
        setActionButtonText();
    }

    private void findViews() {
        this.mOfferWallLayout = (OfferWallLayout) this.mView.findViewById(R.id.layout_offerwall);
        this.mLayoutRegister = (RelativeLayout) this.mView.findViewById(R.id.layout_register);
        this.mLayoutInvitation = (LinearLayout) this.mView.findViewById(R.id.layout_invitation);
        this.mLayoutInviteInfo = (LinearLayout) this.mView.findViewById(R.id.layout_invite_info);
        this.mLayoutInvite = (RelativeLayout) this.mView.findViewById(R.id.layout_invite);
        this.mTxtDailyRewardTips = (TextView) this.mView.findViewById(R.id.txt_daily_reward);
        this.mEditInvitation = (EditText) this.mView.findViewById(R.id.edit_invitation);
        this.mBtnEnter = (Button) this.mView.findViewById(R.id.btn_enter);
        this.mTxtInvitationTips = (TextView) this.mView.findViewById(R.id.txt_tips_invitation);
        this.mTxtInviteInfo = (TextView) this.mView.findViewById(R.id.txt_invite_info);
        this.mTxtInviteInfoMessage = (TextView) this.mView.findViewById(R.id.txt_invite_info_message);
        this.mTxtInviteFriends = (TextView) this.mView.findViewById(R.id.txt_invite_friends);
        this.mBtnHelpHistory = (Button) this.mView.findViewById(R.id.btn_help_history);
        this.mImgGameCenter = (ImageView) this.mView.findViewById(R.id.img_gamecenter);
        this.mTxtRegisterNanas = (TextView) this.mLayoutRegister.findViewById(R.id.txt_nanas);
        this.mTxtInvitationNanas = (TextView) this.mLayoutInvitation.findViewById(R.id.txt_nanas);
        this.mTxtInviteNanas = (TextView) this.mLayoutInvite.findViewById(R.id.txt_nanas);
    }

    private void setListeners() {
        this.mLayoutInvite.setOnClickListener(this);
        this.mLayoutRegister.setOnClickListener(this);
        this.mBtnEnter.setOnClickListener(this);
        this.mTxtInviteInfo.setOnClickListener(this);
        this.mLayoutInviteInfo.setOnClickListener(this);
        this.mBtnHelpHistory.setOnClickListener(this);
        this.mImgGameCenter.setOnClickListener(this);
    }

    private void initialize() {
        this.mContext = getSherlockActivity();
        this.mTxtDailyRewardTips.setText(getString(R.string.tips_get_daily_nanas, new Object[]{Integer.valueOf(Settings.getInstance().getDailyPoints())}));
        this.mTxtInviteInfoMessage.setText(Html.fromHtml(getString(R.string.input_again_body, new Object[]{"<font color=\"#FFDD15\">", "5", "</font>"})));
        this.mTxtInviteFriends.setText(Html.fromHtml(getString(R.string.tips_invite_friends, new Object[]{"<br>", "<font color=\"#F26522\">", "</font>"})));
        this.mTxtInvitationTips.setText(Html.fromHtml(getString(R.string.tips_invitation, new Object[]{"<font color=\"#00A14B\">", Settings.getInstance().getInvitationPointsShow(), "</font>"})));
        this.mTxtRegisterNanas.setText(getString(R.string.tips_nanas, new Object[]{Settings.getRegisterPointsShow()}));
        this.mTxtInvitationNanas.setText(getString(R.string.tips_nanas, new Object[]{Settings.getInstance().getInvitationPointsShow()}));
        this.mTxtInviteNanas.setText(getString(R.string.tips_nanas, new Object[]{Settings.getInstance().getInvitationPointsShow()}));
        initJoinOffer();
        initVideoOfferwall();
        if (this.user.isLogged()) {
            initTrialpayOfferwall();
        }
        requestOfferWall();
        initInterstitialAd();
        setActionButtonText();
    }

    public void initJoinOffer() {
        if (this.user.isLogged()) {
            this.mLayoutRegister.setVisibility(8);
            if (this.user.canInputInvitationCode()) {
                SharedPreferences settings = this.mContext.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0);
                if (this.user.getInputCodeCount() > settings.getInt(AppNanaPrefrences.PREF_INPUT_TIMES, 0)) {
                    AlertDialog.alert(this.mContext, (int) R.string.input_again_success_title, (int) R.string.input_again_success_body);
                    settings.edit().putInt(AppNanaPrefrences.PREF_INPUT_TIMES, this.user.getInputCodeCount()).apply();
                }
                this.mLayoutInvitation.setVisibility(0);
                this.mLayoutInvite.setVisibility(8);
                this.mTxtInviteInfo.setVisibility(4);
                return;
            }
            this.mLayoutInvitation.setVisibility(8);
            this.mLayoutInvite.setVisibility(0);
            this.mTxtInviteInfo.setVisibility(0);
            return;
        }
        this.mLayoutRegister.setVisibility(0);
        this.mTxtInviteInfo.setVisibility(8);
        this.mLayoutInvitation.setVisibility(8);
        this.mLayoutInvite.setVisibility(8);
    }

    private void resetOfferWall() {
        this.offerResponseCount = 0;
        this.mOfferController.reset();
        this.mOfferWallLayout.reset();
    }

    private void requestOfferWall() {
        requestAppNanaOffers();
        Map<Class, Integer> mapExchangeRate = new HashMap();
        mapExchangeRate.put(DisplayIO.Offer.class, Integer.valueOf(Settings.getInstance().getDisplayIOExchangeRate()));
        mapExchangeRate.put(AppThis.Offer.class, Integer.valueOf(Settings.getInstance().getAppThisExchangeRate()));
        mapExchangeRate.put(HangMyAds.Offer.class, Integer.valueOf(Settings.getInstance().getHangMyAdsExchangeRate()));
        this.mOfferController = new OfferController(this.user.getCustomID(), this.offerListener, mapExchangeRate);
        this.mOfferController.addFixedOffer(createTrialPayOffer());
        this.mOfferController.addFixedOffer(createVideoOffer());
        if (this.user.getRfn() > 0) {
            this.mOfferController.addFixedOffer(createRfn(this.user.getRfn()));
        }
        requestOfferHistory();
        this.mOfferController.requestOffers();
    }

    private Offer createRfn(int nanas) {
        return new Offer(1, getString(R.string.random_nanas), nanas, R.drawable.ic_list_rfn, BuildConfig.VERSION_NAME, getString(R.string.get_random_nanas_offer, new Object[]{"<font color=\"#00A14B\">", this.user.getFormattedRfn(), "</font>"}), true);
    }

    private Offer createTrialPayOffer() {
        return new Offer(3, getString(R.string.nana_offers), 0, R.drawable.ic_list_offer, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, true);
    }

    private Offer createVideoOffer() {
        return new Offer(OFFER_REQUEST_COUNT, getString(R.string.nana_offers_videos), 0, R.drawable.ic_list_offer, getString(R.string.offer_video_desc), BuildConfig.VERSION_NAME, true);
    }

    private void requestAppNanaOffers() {
        if (this.user.isLogged()) {
            new GetAppNanaOffersTask().execute(new String[0]);
        } else {
            onOffersResponse();
        }
    }

    private void requestOfferHistory() {
        if (this.user.isLogged()) {
            restoreHistory();
        } else {
            onGotHistory();
        }
    }

    private void clickAppNanaOffer(Offer offer) {
        switch (offer.getIdAsInt()) {
            case Gender.FEMALE /*1*/:
                new GetRfnTask().execute(new String[0]);
                return;
            case OFFER_REQUEST_COUNT /*2*/:
                openAdColonyOfferWall();
                return;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                openTrialPayOfferWall();
                return;
            default:
                new AcceptAppNanaOfferTask().execute(new Offer[]{offer});
                return;
        }
    }

    private void alertRegisterDialog() {
        this.mDialog = AlertDialog.confirm(this.mContext, (int) R.string.tips_sorry, (int) R.string.tips_must_login_or_register, (int) R.string.tips_login, this.loginButtonClickListener, (int) R.string.tips_register, this.registerButtonClickListener);
    }

    private MainActivity getMainActivity() {
        return (MainActivity) this.mContext;
    }

    private void onGotHistory() {
        this.mOfferController.setAppNanaHistory(this.mAppNanaHistoryName);
    }

    private void onOffersResponse() {
        this.offerResponseCount++;
        if (this.offerResponseCount == OFFER_REQUEST_COUNT) {
            if (this.mAppNanaOffers != null) {
                this.mOfferController.addOffersToTop(this.mAppNanaOffers);
            }
            this.mOfferWallLayout.setData(this.mOfferController.getOffers(), this.offerClickListener);
        }
    }

    private void setActionButtonText() {
        if (this.user.isLogged()) {
            this.mBtnHelpHistory.setText(R.string.tips_history);
        } else {
            this.mBtnHelpHistory.setText(R.string.tips_help);
        }
    }

    private void checkToOpenHelpOrHistoryPage() {
        if (this.user.isLogged()) {
            openHistory();
        } else {
            openHelp();
        }
    }

    private void openHelp() {
        BrowserActivity.getHelp(this.mContext);
    }

    private void openHistory() {
        startActivity(new Intent(this.mContext, HistoryActivity.class));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_invite_info /*2131558501*/:
                this.mLayoutInviteInfo.setVisibility(0);
                return;
            case R.id.btn_help_history /*2131558503*/:
                checkToOpenHelpOrHistoryPage();
                return;
            case R.id.layout_invite_info /*2131558504*/:
                this.mLayoutInviteInfo.setVisibility(8);
                return;
            case R.id.img_gamecenter /*2131558507*/:
                openGameCenter();
                return;
            case R.id.btn_enter /*2131558578*/:
                getSherlockActivity().hideKeyboard(new EditText[]{this.mEditInvitation});
                new VerifyInvitationCodeTask().execute(new String[]{this.mEditInvitation.getText().toString()});
                return;
            case R.id.layout_invite /*2131558579*/:
                ((MainActivity) this.mContext).mIndicator.setCurrentItem(OFFER_REQUEST_COUNT);
                return;
            case R.id.layout_register /*2131558584*/:
                RegisterDialogFragment.getInstance().show(getFragmentManager(), RegisterDialogFragment.TAG);
                return;
            default:
                return;
        }
    }

    private void openGameCenter() {
        Class clazz;
        if (VERSION.SDK_INT >= 21) {
            clazz = GameCenterWebViewActivity.class;
        } else {
            clazz = GameCenterXWalkActivity.class;
        }
        startActivityForResult(new Intent(this.mContext, clazz), OFFER_REQUEST_COUNT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case OFFER_REQUEST_COUNT /*2*/:
                if (resultCode == 1) {
                    LoginDialogFragment.getInstance().show(this.mContext.getSupportFragmentManager(), LoginDialogFragment.TAG);
                    return;
                } else if (resultCode == OFFER_REQUEST_COUNT) {
                    RegisterDialogFragment.getInstance().show(this.mContext.getSupportFragmentManager(), RegisterDialogFragment.TAG);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void initInterstitialAd() {
        this.mInterstitialAd = new InterstitialAd(this.mContext);
        this.mInterstitialAd.setAdUnitId(getString(R.string.ad_interstitial_on_offerwall));
    }

    private void preLoadInterstitial() {
        if (needShowInterstitial()) {
            requestNewInterstitial();
        }
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new Builder().build());
    }

    private void showInterstitial() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
        }
    }

    private boolean needShowInterstitial() {
        boolean result = false;
        SharedPreferences preferences = this.mContext.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0);
        Editor editor = preferences.edit();
        long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
        long lastViewTimeInMillis = preferences.getLong(AppNanaPrefrences.PREF_VIEW_TIME_OF_LAST_VIDEO, currentTimeInMillis);
        int videoCountIn12H = preferences.getInt(AppNanaPrefrences.PREF_VIDEO_COUNT_IN_12H, 0);
        if (currentTimeInMillis == lastViewTimeInMillis) {
            editor.putLong(AppNanaPrefrences.PREF_VIEW_TIME_OF_LAST_VIDEO, lastViewTimeInMillis);
        }
        videoCountIn12H++;
        editor.putInt(AppNanaPrefrences.PREF_VIDEO_COUNT_IN_12H, videoCountIn12H);
        if (isOver12Hours(lastViewTimeInMillis)) {
            editor.putLong(AppNanaPrefrences.PREF_VIEW_TIME_OF_LAST_VIDEO, currentTimeInMillis);
            editor.putInt(AppNanaPrefrences.PREF_VIDEO_COUNT_IN_12H, 1);
        } else if (videoCountIn12H >= 3) {
            result = true;
        }
        editor.apply();
        return result;
    }

    private boolean isOver12Hours(long lastTimeInMillis) {
        return Calendar.getInstance().getTimeInMillis() - lastTimeInMillis >= 43200000;
    }

    private void saveHistory() {
        if (this.mAppNanaHistoryName != null && !this.mAppNanaHistoryName.isEmpty()) {
            this.mContext.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).edit().putString(AppNanaPrefrences.PREF_HISTORY, new Gson().toJson(this.mAppNanaHistoryName, new TypeToken<List<String>>() {
            }.getType())).apply();
        }
    }

    private void restoreHistory() {
        String historyString = this.mContext.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).getString(AppNanaPrefrences.PREF_HISTORY, null);
        if (historyString != null) {
            try {
                this.mAppNanaHistoryName = (List) new Gson().fromJson(historyString, new TypeToken<List<String>>() {
                }.getType());
                onGotHistory();
            } catch (Exception e) {
                MapizLog.e("getHistory", e.toString());
            }
        }
        if (this.mAppNanaHistoryName == null || this.mAppNanaHistoryName.isEmpty()) {
            new GetOfferHistoryNameTask().execute(new String[0]);
        }
    }

    private void initTrialpayOfferwall() {
        this.mTrialpayManager = TrialpayManager.getInstance(this.mContext);
        this.mTrialpayManager.setSid(UserModel.getInstance().getCustomID());
        this.mTrialpayManager.registerVic(OfferModel.TRIAL_PAY_TOUCH_POINT_NAME, OfferModel.TRIAL_PAY_CODE);
    }

    private void openTrialPayOfferWall() {
        if (this.mTrialpayManager == null) {
            initTrialpayOfferwall();
        }
        this.mTrialpayManager.open(OfferModel.TRIAL_PAY_TOUCH_POINT_NAME);
    }

    private void initVideoOfferwall() {
        if (this.user.isLogged()) {
            initAdcolonyOfferwall();
        }
        initVungleOfferwall();
    }

    private void onVideoStart() {
        preLoadInterstitial();
    }

    private void onVideoEnd() {
        showInterstitial();
    }

    private void pauseVideoOfferwall() {
        AdColony.pause();
        this.vunglePub.onPause();
    }

    private void resumeVideoOfferwall() {
        AdColony.resume(this.mContext);
        this.vunglePub.onResume();
    }

    private void initAdcolonyOfferwall() {
        String clientOptions = String.format("version:%s,store:google", new Object[]{Device.getInstance().getAppVersionName()});
        AdColony.setCustomID(this.user.getCustomID());
        AdColony.configure(this.mContext, clientOptions, OfferModel.ADCOLONY_APP_ID, OfferModel.ADCOLONY_ZONE_ID);
        AdColony.addV4VCListener(this.adColonyV4VCListener);
    }

    private void openAdColonyOfferWall() {
        this.mAdColonyV4VCAd = new AdColonyV4VCAd(OfferModel.ADCOLONY_ZONE_ID);
        this.mAdColonyV4VCAd.withListener(this.adColonyAdListener);
        this.mAdColonyV4VCAd.show();
    }

    private void initVungleOfferwall() {
        this.vunglePub.init(this.mContext, Device.getInstance().getClientPackage());
        this.vunglePub.setEventListeners(this.vungleEventListener);
    }

    private void openVungleOfferWall() {
        this.vungleConfig.setIncentivized(true);
        this.vungleConfig.setIncentivizedUserId(this.user.getCustomID());
        this.vunglePub.playAd(this.vungleConfig);
    }
}
