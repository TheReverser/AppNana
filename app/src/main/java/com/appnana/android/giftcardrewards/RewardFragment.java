package com.appnana.android.giftcardrewards;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockDialogFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.exception.AccessForbiddenException;
import com.appnana.android.giftcardrewards.exception.NotEnoughPointsException;
import com.appnana.android.giftcardrewards.exception.SessionExpiredException;
import com.appnana.android.giftcardrewards.model.AppNanaPrefrences;
import com.appnana.android.giftcardrewards.model.CountryInfo;
import com.appnana.android.giftcardrewards.model.CountryInfoModel;
import com.appnana.android.giftcardrewards.model.Reward;
import com.appnana.android.giftcardrewards.model.RewardModel;
import com.appnana.android.giftcardrewards.model.RewardType;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.offerwall.R;
import com.appnana.android.utils.MapizLog;
import com.facebook.BuildConfig;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RewardFragment extends TrackedSherlockFragment implements OnClickListener {
    public static final int REQUEST_SHARE = 1;
    private OnClickListener clickReward = new OnClickListener() {
        public void onClick(View v) {
            if (RewardFragment.this.mRewardDialog == null) {
                RewardFragment.this.mRewardDialog = new RewardDialogFragment();
            }
            RewardFragment.this.selectedRewardLayout = (RelativeLayout) v;
            RewardFragment.this.mRewardDialog.show(RewardFragment.this.mContext.getSupportFragmentManager(), ((Reward) RewardFragment.this.selectedRewardLayout.getTag()).getKey());
        }
    };
    private Button mBtnHelp;
    private SherlockFragmentActivity mContext;
    private CountrySpinner mCountrySpinner;
    private AlertDialog mDialog;
    private LayoutInflater mInflater;
    private RelativeLayout mLayoutHowTo;
    private LinearLayout mLayoutRewards;
    private RewardDialogFragment mRewardDialog;
    private View mView;
    private RelativeLayout selectedRewardLayout;

    public class CountryAdapter extends ArrayAdapter<CountryInfo> {
        Context context = null;
        List<CountryInfo> countryData = null;

        public CountryAdapter(Activity context, int resource, List<CountryInfo> data) {
            super(context, resource, data);
            this.context = context;
            this.countryData = data;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            CountryInfo countryInfo = (CountryInfo) this.countryData.get(position);
            LinearLayout layout = (LinearLayout) RewardFragment.this.mInflater.inflate(R.layout.widget_country_item, null);
            TextView textView = (TextView) layout.findViewById(R.id.txt_name);
            textView.setText(countryInfo.getNameRes());
            textView.setTextColor(-1);
            textView.setPadding(0, 0, 0, 0);
            textView.setCompoundDrawablesWithIntrinsicBounds(countryInfo.getIconRes(), 0, 0, 0);
            layout.setTag(countryInfo);
            layout.setDuplicateParentStateEnabled(true);
            return layout;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            CountryInfo countryInfo = (CountryInfo) this.countryData.get(position);
            LinearLayout layout = (LinearLayout) RewardFragment.this.mInflater.inflate(R.layout.widget_country_item, null);
            TextView textView = (TextView) layout.findViewById(R.id.txt_name);
            textView.setText(countryInfo.getNameRes());
            textView.setCompoundDrawablesWithIntrinsicBounds(countryInfo.getIconRes(), 0, 0, 0);
            layout.setTag(countryInfo);
            return layout;
        }
    }

    private class GetRewardsTask extends AsyncTask<String, Void, Boolean> {
        private GetRewardsTask() {
        }

        protected Boolean doInBackground(String... Locale) {
            try {
                RewardModel.getInstance(RewardFragment.this.mContext).updateRewards(Locale[0]);
                return Boolean.valueOf(true);
            } catch (Exception e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            ((MainActivity) RewardFragment.this.mContext).mLayoutLoading.setVisibility(8);
            if (result.booleanValue()) {
                dispatchGotRewardsEvent();
            } else {
                dispatchGetRewardsFailed();
            }
        }

        private void dispatchGotRewardsEvent() {
            RewardFragment.this.updateRewardLayout();
        }

        private void dispatchGetRewardsFailed() {
            AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_sorry, (int) R.string.error_under_maintenance);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            ((MainActivity) RewardFragment.this.mContext).mLayoutLoading.setVisibility(0);
        }
    }

    @SuppressLint({"ValidFragment"})
    private class RewardDialogFragment extends SherlockDialogFragment implements OnClickListener {
        OnClickListener gotoGetNanasPage;
        LinearLayout layoutGuest;
        LinearLayout layoutLogged;
        Button mBtnClose;
        Button mBtnLogin;
        Button mBtnRedeem;
        Button mBtnRegister;
        EditText mEditBitcoinAddress;
        EditText mEditPayPalAccount;
        EditText mEditUserName;
        ImageView mImgIcon;
        LinearLayout mLayoutPayPalInfo;
        LinearLayout mLayoutRefundTips;
        LinearLayout mLayoutUserEmail;
        TextView mTxtPayPalAccount;
        TextView mTxtRefundBody;
        TextView mTxtRefundTitle;
        TextView mTxtRewardScope;
        TextView mTxtTips;
        TextView mTxtTitle;
        TextView mTxtUserEmail;
        View mView;
        OnClickListener onConfirmationChangeCountryButtOnClickListener;
        OnClickListener onConfirmationYesButtOnClickListener;
        private OnClickListener onShareButtonClickListener;
        Reward reward;

        private class RedeemTask extends AsyncTask<Void, Void, Boolean> {
            int errorRes;

            private RedeemTask() {
            }

            protected Boolean doInBackground(Void... params) {
                try {
                    return Boolean.valueOf(UserCommand.create().isRedeemSuccess(RewardDialogFragment.this.reward.getID()));
                } catch (NotEnoughPointsException e) {
                    this.errorRes = R.string.no_enough_nanas;
                    return Boolean.valueOf(false);
                } catch (AccessForbiddenException e2) {
                    this.errorRes = R.string.error_irregular_activity;
                    return Boolean.valueOf(false);
                } catch (SessionExpiredException e3) {
                    this.errorRes = R.string.error_session_expired;
                    return Boolean.valueOf(false);
                } catch (Exception e4) {
                    MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e4.toString());
                    this.errorRes = R.string.error_under_maintenance;
                    return Boolean.valueOf(false);
                }
            }

            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                ((MainActivity) RewardFragment.this.mContext).mLayoutLoading.setVisibility(8);
                if (result.booleanValue()) {
                    UserModel.getInstance().costPoints(RewardDialogFragment.this.reward.getRealCost());
                    ((MainActivity) RewardFragment.this.mContext).updateUserPoints();
                    RewardFragment.this.mDialog = AlertDialog.confirm(RewardFragment.this.mContext, (int) R.string.dialog_share_title, RewardFragment.this.mContext.getString(R.string.dialog_share_body, new Object[]{"<font color=\"#00A14B\">", Integer.valueOf(Settings.getInstance().getFacebookInviteNanas()), "</font>"}), (int) R.string.tips_share, RewardDialogFragment.this.onShareButtonClickListener);
                    if (RewardDialogFragment.this.reward.hasRefundNanas()) {
                        Context access$300 = RewardFragment.this.mContext;
                        SherlockFragmentActivity access$3002 = RewardFragment.this.mContext;
                        Object[] objArr = new Object[RewardFragment.REQUEST_SHARE];
                        objArr[0] = RewardDialogFragment.this.reward.getDisplayRefundPercent();
                        AlertDialog.alertNanas(access$300, access$3002.getString(R.string.nanas_back_body, objArr), RewardDialogFragment.this.reward.getDisplayRefundNanas());
                    }
                    if (RewardDialogFragment.this.reward.isPayPal()) {
                        AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_thanks, (int) R.string.redeem_paypal_success_message);
                        return;
                    } else if (RewardDialogFragment.this.reward.isBitcoin()) {
                        AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_thanks, (int) R.string.redeem_bitcoin_success_message);
                        return;
                    } else {
                        AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_thanks, (int) R.string.redeem_reward_success_body);
                        return;
                    }
                }
                AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_sorry, this.errorRes);
                if (this.errorRes == R.string.error_irregular_activity || this.errorRes == R.string.error_session_expired) {
                    RewardFragment.this.getSherlockActivity().onLoggedOut();
                }
            }

            protected void onPreExecute() {
                super.onPreExecute();
                ((MainActivity) RewardFragment.this.mContext).mLayoutLoading.setVisibility(0);
            }
        }

        private RewardDialogFragment() {
            this.gotoGetNanasPage = new OnClickListener() {
                public void onClick(View v) {
                    RewardDialogFragment.this.dismiss();
                    RewardFragment.this.mDialog.dismiss();
                    ((MainActivity) RewardFragment.this.mContext).mIndicator.setCurrentItem(RewardFragment.REQUEST_SHARE);
                }
            };
            this.onConfirmationYesButtOnClickListener = new OnClickListener() {
                public void onClick(View v) {
                    RewardFragment.this.mDialog.dismiss();
                    RewardDialogFragment.this.redeem();
                }
            };
            this.onConfirmationChangeCountryButtOnClickListener = new OnClickListener() {
                public void onClick(View v) {
                    RewardFragment.this.mDialog.dismiss();
                    RewardDialogFragment.this.dismiss();
                    RewardFragment.this.mCountrySpinner.performClick();
                }
            };
            this.onShareButtonClickListener = new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(RewardFragment.this.mContext, BrowserActivity.class);
                    intent.putExtra(ShareConstants.TITLE, R.string.tips_facebook);
                    intent.putExtra(BrowserActivity.EXTRA_URI, Settings.getInstance().getFacebookShareUrl(RewardFragment.this.mContext.getString(R.string.share_name), "reward"));
                    intent.putExtra(BrowserActivity.EXTRA_REQUEST, RewardFragment.REQUEST_SHARE);
                    RewardFragment.this.mContext.startActivityForResult(intent, RewardFragment.REQUEST_SHARE);
                    RewardFragment.this.mDialog.dismiss();
                }
            };
        }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setStyle(RewardFragment.REQUEST_SHARE, R.style.Theme_MatchWidth_RewardDialog);
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            this.mView = inflater.inflate(R.layout.dialog_reward, container, false);
            return this.mView;
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            findViews();
            setListeners();
        }

        public void onResume() {
            super.onResume();
            initialize();
        }

        public void show(FragmentManager manager, String tag) {
            super.show(manager, tag);
        }

        private void initialize() {
            this.reward = (Reward) RewardFragment.this.selectedRewardLayout.getTag();
            CountryInfo locale = this.reward.getLocale();
            this.mImgIcon.setImageDrawable(((ImageView) RewardFragment.this.selectedRewardLayout.findViewById(R.id.img_icon)).getDrawable());
            this.mTxtTitle.setText(this.reward.getName());
            this.mTxtRewardScope.setCompoundDrawablesWithIntrinsicBounds(locale.getIconRes(), 0, 0, 0);
            Button button = this.mBtnRegister;
            Object[] objArr = new Object[RewardFragment.REQUEST_SHARE];
            objArr[0] = Settings.getRegisterPointsShow();
            button.setText(getString(R.string.tips_register_get_nanas, objArr));
            if (this.reward.isAvailableWorldwide()) {
                this.mTxtRewardScope.setText(R.string.tips_valid_worldwide);
            } else {
                TextView textView = this.mTxtRewardScope;
                objArr = new Object[RewardFragment.REQUEST_SHARE];
                objArr[0] = getString(locale.getShortNameRes());
                textView.setText(getString(R.string.tips_valid_country, objArr));
            }
            if (UserModel.getInstance().isLogged()) {
                if (this.reward.hasRefundNanas()) {
                    this.mLayoutRefundTips.setVisibility(0);
                    textView = this.mTxtRefundTitle;
                    objArr = new Object[RewardFragment.REQUEST_SHARE];
                    objArr[0] = this.reward.getDisplayRefundPercent();
                    textView.setText(getString(R.string.nanas_back_body, objArr));
                    textView = this.mTxtRefundBody;
                    objArr = new Object[RewardFragment.REQUEST_SHARE];
                    objArr[0] = this.reward.getDisplayRefundNanas();
                    textView.setText(getString(R.string.nanas_back_body_2, objArr));
                } else {
                    this.mLayoutRefundTips.setVisibility(8);
                }
                this.layoutGuest.setVisibility(8);
                this.layoutLogged.setVisibility(0);
                this.mTxtUserEmail.setText(UserModel.getInstance().getEmail());
                this.mEditUserName.setText(UserModel.getInstance().getName());
                this.mEditBitcoinAddress.setText(UserModel.getInstance().getBitcoinAddress());
                this.mTxtTips.setText(R.string.redeem_reward_card);
                this.mLayoutUserEmail.setVisibility(0);
                this.mLayoutPayPalInfo.setVisibility(8);
                this.mEditBitcoinAddress.setVisibility(8);
                if (this.reward.isPayPal()) {
                    this.mLayoutUserEmail.setVisibility(8);
                    this.mLayoutPayPalInfo.setVisibility(0);
                    if (UserModel.getInstance().getPayPalAccount().equals(BuildConfig.VERSION_NAME)) {
                        this.mEditPayPalAccount.setVisibility(0);
                        this.mTxtTips.setText(R.string.redeem_paypal_1);
                        return;
                    }
                    this.mTxtTips.setText(R.string.redeem_paypal);
                    this.mTxtPayPalAccount.setText(UserModel.getInstance().getPayPalAccount());
                    this.mEditPayPalAccount.setVisibility(8);
                    this.mEditPayPalAccount.setText(UserModel.getInstance().getPayPalAccount());
                    return;
                } else if (this.reward.isBitcoin()) {
                    this.mEditBitcoinAddress.setVisibility(0);
                    this.mTxtTips.setText(R.string.redeem_bitcoin);
                    return;
                } else {
                    return;
                }
            }
            this.layoutGuest.setVisibility(0);
            this.layoutLogged.setVisibility(8);
        }

        private void findViews() {
            this.mBtnRegister = (Button) this.mView.findViewById(R.id.btn_register);
            this.mBtnLogin = (Button) this.mView.findViewById(R.id.btn_login);
            this.mBtnClose = (Button) this.mView.findViewById(R.id.btn_close_symbol);
            this.mBtnRedeem = (Button) this.mView.findViewById(R.id.btn_redeem);
            this.mTxtUserEmail = (TextView) this.mView.findViewById(R.id.txt_user_email);
            this.mEditUserName = (EditText) this.mView.findViewById(R.id.edit_user_name);
            this.mImgIcon = (ImageView) this.mView.findViewById(R.id.img_icon);
            this.mTxtTitle = (TextView) this.mView.findViewById(R.id.txt_title);
            this.layoutGuest = (LinearLayout) this.mView.findViewById(R.id.layout_guest);
            this.layoutLogged = (LinearLayout) this.mView.findViewById(R.id.layout_logged);
            this.mTxtTips = (TextView) this.mView.findViewById(R.id.txt_tips_redeem);
            this.mTxtPayPalAccount = (TextView) this.mView.findViewById(R.id.txt_paypal_account);
            this.mEditPayPalAccount = (EditText) this.mView.findViewById(R.id.edit_paypal_account);
            this.mEditBitcoinAddress = (EditText) this.mView.findViewById(R.id.edit_bitcoin_address);
            this.mLayoutUserEmail = (LinearLayout) this.mView.findViewById(R.id.layout_user_email);
            this.mLayoutPayPalInfo = (LinearLayout) this.mView.findViewById(R.id.layout_paypal_info);
            this.mTxtRewardScope = (TextView) this.mView.findViewById(R.id.txt_reward_scope);
            this.mLayoutRefundTips = (LinearLayout) this.mView.findViewById(R.id.layout_refund_tips);
            this.mTxtRefundTitle = (TextView) this.mView.findViewById(R.id.txt_refund_title);
            this.mTxtRefundBody = (TextView) this.mView.findViewById(R.id.txt_refund_body);
        }

        private void setListeners() {
            this.mBtnRegister.setOnClickListener(this);
            this.mBtnLogin.setOnClickListener(this);
            this.mBtnClose.setOnClickListener(this);
            this.mBtnRedeem.setOnClickListener(this);
        }

        private void redeem() {
            new RedeemTask().execute(new Void[0]);
            dismiss();
        }

        private void checkForm() {
            String userEnteredString;
            if (this.reward.isPayPal()) {
                userEnteredString = this.mEditPayPalAccount.getText().toString();
                if (userEnteredString.equals(BuildConfig.VERSION_NAME)) {
                    AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_invalid, (int) R.string.must_input_paypal_account);
                    return;
                } else if (!UserModel.getInstance().setPayPalAccount(userEnteredString)) {
                    AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_invalid, (int) R.string.invalid_paypal_account);
                    return;
                }
            }
            if (this.reward.isBitcoin()) {
                userEnteredString = this.mEditBitcoinAddress.getText().toString();
                if (userEnteredString.equals(BuildConfig.VERSION_NAME)) {
                    AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_invalid, (int) R.string.must_input_bitcoin_address);
                    return;
                } else if (!UserModel.getInstance().setBitcoinAddress(userEnteredString).booleanValue()) {
                    AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_invalid, (int) R.string.invalid_bitcoin_address);
                    return;
                }
            }
            if (!UserModel.getInstance().setName(this.mEditUserName.getText().toString())) {
                AlertDialog.alert(RewardFragment.this.mContext, (int) R.string.tips_invalid, (int) R.string.invalid_input_name);
            } else if (UserModel.getInstance().getPoints() < this.reward.getCost()) {
                RewardFragment.this.mDialog = AlertDialog.confirm(RewardFragment.this.mContext, (int) R.string.no_enough_nanas, BuildConfig.VERSION_NAME, (int) R.string.nav_get_nanas, this.gotoGetNanasPage);
            } else if (this.reward.isPayPal() && this.mTxtPayPalAccount.getText().toString().equals(BuildConfig.VERSION_NAME)) {
                RewardFragment.this.mDialog = AlertDialog.confirm(RewardFragment.this.mContext, R.string.tips_important, R.string.confirm_paypal_account, "PayPal: " + UserModel.getInstance().getPayPalAccount(), R.string.btn_ok, new OnClickListener() {
                    public void onClick(View v) {
                        RewardFragment.this.mDialog.dismiss();
                        RewardDialogFragment.this.confirmRedeemAction();
                    }
                });
            } else {
                confirmRedeemAction();
            }
        }

        private void hideKeyboard() {
            try {
                InputMethodManager imm = (InputMethodManager) RewardFragment.this.mContext.getSystemService("input_method");
                IBinder iBinder = null;
                if (this.mEditUserName.isFocused()) {
                    iBinder = this.mEditUserName.getWindowToken();
                } else if (this.mEditPayPalAccount.isFocused()) {
                    iBinder = this.mEditPayPalAccount.getWindowToken();
                } else if (this.mEditBitcoinAddress.isFocused()) {
                    iBinder = this.mEditBitcoinAddress.getWindowToken();
                }
                imm.hideSoftInputFromWindow(iBinder, 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void confirmRedeemAction() {
            hideKeyboard();
            if (this.reward.isAvailableWorldwide()) {
                RewardFragment.this.mDialog = getRedeemConfrimationDialog();
            } else {
                RewardFragment.this.mDialog = getRedeemConfrimationDialogWithChangeCountryButton();
            }
        }

        private AlertDialog getRedeemConfrimationDialog() {
            Context access$300 = RewardFragment.this.mContext;
            Object[] objArr = new Object[RewardFragment.REQUEST_SHARE];
            objArr[0] = "<br>";
            return AlertDialog.confirm(access$300, (int) R.string.redeem_confirm_title, getString(R.string.tips_available_worldwide, objArr), (int) R.string.btn_yes, this.onConfirmationYesButtOnClickListener);
        }

        private AlertDialog getRedeemConfrimationDialogWithChangeCountryButton() {
            return AlertDialog.confirmWithUp1Down2(RewardFragment.this.mContext, R.string.redeem_confirm_title, getString(R.string.redeem_confirm_body, new Object[]{getString(this.reward.getLocale().getShortNameRes()), "<br>"}), R.string.btn_change_country, this.onConfirmationChangeCountryButtOnClickListener, R.string.btn_yes, this.onConfirmationYesButtOnClickListener);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_close_symbol /*2131558482*/:
                    dismiss();
                    return;
                case R.id.btn_register /*2131558530*/:
                    RegisterDialogFragment.getInstance().show(getFragmentManager(), RegisterDialogFragment.TAG);
                    dismiss();
                    return;
                case R.id.btn_login /*2131558531*/:
                    LoginDialogFragment.getInstance().show(getFragmentManager(), LoginDialogFragment.TAG);
                    dismiss();
                    return;
                case R.id.btn_redeem /*2131558553*/:
                    checkForm();
                    return;
                default:
                    return;
            }
        }
    }

    class SpinnerCountrySelectedListener implements OnItemSelectedListener {
        SpinnerCountrySelectedListener() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            if (RewardFragment.this.mCountrySpinner.isUsersAction().booleanValue()) {
                CountryInfo countryInfo = (CountryInfo) adapterView.getSelectedItem();
                GetRewardsTask getRewardsTask = new GetRewardsTask();
                String[] strArr = new String[RewardFragment.REQUEST_SHARE];
                strArr[0] = countryInfo.getCode();
                getRewardsTask.execute(strArr);
                RewardFragment.this.mCountrySpinner.finishClick();
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            RewardFragment.this.mCountrySpinner.finishClick();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        this.mView = inflater.inflate(R.layout.activity_reward, container, false);
        return this.mView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        setListeners();
        initialize();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void findViews() {
        this.mLayoutHowTo = (RelativeLayout) this.mView.findViewById(R.id.layout_howto);
        this.mLayoutRewards = (LinearLayout) this.mView.findViewById(R.id.layout_rewards);
        this.mBtnHelp = (Button) this.mView.findViewById(R.id.btn_help);
        this.mCountrySpinner = (CountrySpinner) this.mView.findViewById(R.id.spinner_country);
    }

    private void setListeners() {
        this.mLayoutHowTo.setOnClickListener(this);
        this.mBtnHelp.setOnClickListener(this);
    }

    private void initialize() {
        this.mContext = getSherlockActivity();
        initHowToLayout();
        initCountrySpinner();
        updateRewardLayout();
    }

    private void initCountrySpinner() {
        this.mCountrySpinner.setAdapter(new CountryAdapter(this.mContext, 17367048, CountryInfoModel.getInstance().getCountryInfoList()));
        this.mCountrySpinner.setOnItemSelectedListener(new SpinnerCountrySelectedListener());
        this.mCountrySpinner.setSelection(CountryInfoModel.getInstance().getPosition(this.mContext.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).getString(AppNanaPrefrences.PREF_LOCALE, CountryInfoModel.getInstance().getDefaultLocale())));
    }

    private void updateRewardLayout() {
        this.mLayoutRewards.removeAllViews();
        List<RewardType> rewardTypeList = RewardModel.getInstance(this.mContext).getRewards();
        int rewardTypeCount = rewardTypeList.size();
        for (int i = 0; i < rewardTypeCount; i += REQUEST_SHARE) {
            RewardType rewardType = (RewardType) rewardTypeList.get(i);
            View rewardFrameView = this.mInflater.inflate(R.layout.widget_reward_frame, null);
            LinearLayout rewardFrameLayout = (LinearLayout) rewardFrameView.findViewById(R.id.layout_frame);
            rewardFrameLayout.setTag(rewardType);
            ((TextView) rewardFrameView.findViewById(R.id.txt_type_name)).setText(rewardType.getFullName());
            List<Reward> rewardList = rewardType.getRewards();
            int rewardCount = rewardList.size();
            for (int j = 0; j < rewardCount; j += REQUEST_SHARE) {
                RelativeLayout rewardIconLayout;
                Reward reward = (Reward) rewardList.get(j);
                if (j == rewardCount - 1) {
                    rewardIconLayout = (RelativeLayout) this.mInflater.inflate(R.layout.widget_last_reward, null);
                } else {
                    rewardIconLayout = (RelativeLayout) this.mInflater.inflate(R.layout.widget_reward, null);
                }
                TextView refundPercentTextView = (TextView) rewardIconLayout.findViewById(R.id.txt_refund_percent);
                if (reward.hasRefundNanas()) {
                    refundPercentTextView.setVisibility(0);
                    String[] strArr = new Object[REQUEST_SHARE];
                    strArr[0] = reward.getDisplayRefundPercent();
                    refundPercentTextView.setText(getString(R.string.tips_refund_percent, strArr));
                } else {
                    refundPercentTextView.setVisibility(8);
                }
                Picasso.with(this.mContext).load(reward.getIcon()).placeholder((int) R.drawable.img_reward_loading).into((ImageView) rewardIconLayout.findViewById(R.id.img_icon));
                rewardIconLayout.setTag(reward);
                rewardIconLayout.setOnClickListener(this.clickReward);
                rewardFrameLayout.addView(rewardIconLayout);
            }
            this.mLayoutRewards.addView(rewardFrameView);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_howto /*2131558510*/:
                startHowToActivity();
                return;
            case R.id.btn_help /*2131558513*/:
                BrowserActivity.getHelp(this.mContext);
                return;
            case R.id.btn_login /*2131558531*/:
                startLoginActivity();
                return;
            default:
                return;
        }
    }

    private void startHowToActivity() {
        startActivity(new Intent(this.mContext, HowToActivity.class));
    }

    private void startLoginActivity() {
        LoginDialogFragment.getInstance().show(getFragmentManager(), LoginDialogFragment.TAG);
    }

    public void initHowToLayout() {
        if (UserModel.getInstance().isLogged()) {
            this.mLayoutHowTo.setVisibility(8);
        } else {
            this.mLayoutHowTo.setVisibility(0);
        }
    }
}
