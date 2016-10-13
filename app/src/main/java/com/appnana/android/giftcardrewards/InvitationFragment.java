package com.appnana.android.giftcardrewards;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.exception.SessionExpiredException;
import com.appnana.android.giftcardrewards.exception.ShareLimitReachedException;
import com.appnana.android.giftcardrewards.exception.UnknownException;
import com.appnana.android.giftcardrewards.listener.OnCloseListener;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.offerwall.model.AppNana.Offer;
import com.appnana.android.offerwall.views.OfferLayout;
import com.appnana.android.offerwall.views.OfferWallLayout;
import com.appnana.android.utils.Device;
import com.facebook.BuildConfig;
import com.hall.emojimap.EmojiMapUtil;
import java.util.ArrayList;
import java.util.List;

public class InvitationFragment extends TrackedSherlockFragment implements OnClickListener, OnCloseListener {
    private SherlockFragmentActivity mContext;
    private AlertDialog mDialog;
    private RelativeLayout mLayoutAccount;
    private OfferWallLayout mLayoutSharerList;
    private TextView mTxtAccount;
    private TextView mTxtInvitationCode;
    private TextView mTxtInviteNanas;
    private TextView mTxtInviteTips;
    private TextView mTxtVersion;
    private OnClickListener offerClickListener = new OnClickListener() {
        public void onClick(View view) {
            switch (((Offer) ((OfferLayout) view).getModel()).getIdAsInt()) {
                case R.string.facebook /*2131099992*/:
                    InvitationFragment.this.shareViaFacebook();
                    return;
                case R.string.google_plus /*2131099995*/:
                    InvitationFragment.this.shareViaGooglePlus();
                    return;
                case R.string.instagram /*2131100000*/:
                    if (InvitationFragment.this.isInstagramInstalled()) {
                        InvitationFragment.this.showImageChoice();
                        return;
                    } else {
                        AlertDialog.alert(InvitationFragment.this.mContext, (int) R.string.tips_sorry, (int) R.string.error_instagram_not_exists);
                        return;
                    }
                default:
                    InvitationFragment.this.shareViaAndroidIntent();
                    return;
            }
        }
    };

    private class ShareInstagramTask extends AsyncTask<Uri, Void, Boolean> {
        int errorRes;
        Uri imageUri;

        private ShareInstagramTask() {
        }

        protected Boolean doInBackground(Uri... params) {
            this.imageUri = params[0];
            try {
                UserCommand.create().sendMassInvite("instagram");
                return Boolean.valueOf(true);
            } catch (SessionExpiredException e) {
                e.printStackTrace();
                this.errorRes = R.string.error_session_expired;
                return Boolean.valueOf(false);
            } catch (UnknownException e2) {
                e2.printStackTrace();
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            } catch (ShareLimitReachedException e3) {
                e3.printStackTrace();
                this.errorRes = R.string.error_share_limit_reached;
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            UserModel.getInstance().setMassInviteDone(UserModel.INSTAGRAM_MASS_INVITE);
            if (result.booleanValue()) {
                InvitationFragment.this.shareViaInstagram(this.imageUri);
            } else {
                AlertDialog.alert(InvitationFragment.this.mContext, (int) R.string.tips_sorry, this.errorRes);
            }
            InvitationFragment.this.hideLoadingView();
        }

        protected void onPreExecute() {
            super.onPreExecute();
            InvitationFragment.this.showLoadingView();
        }
    }

    private class SignOutTask extends AsyncTask<Void, Void, Void> {
        private SignOutTask() {
        }

        protected Void doInBackground(Void... params) {
            UserCommand.create().logout();
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            InvitationFragment.this.getMainActivity().onLoggedOut();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_invitation, container, false);
        findViews(view);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
        initialize();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void initInvitationCode() {
        try {
            this.mTxtInvitationCode.setText(UserModel.getInstance().getInvitationCode());
            this.mTxtAccount.setText(UserModel.getInstance().getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findViews(View view) {
        this.mTxtInvitationCode = (TextView) view.findViewById(R.id.txt_invitationcode);
        this.mTxtInviteNanas = (TextView) view.findViewById(R.id.txt_invite_nanas);
        this.mTxtInviteTips = (TextView) view.findViewById(R.id.txt_invite_tips);
        this.mTxtAccount = (TextView) view.findViewById(R.id.txt_account);
        this.mTxtVersion = (TextView) view.findViewById(R.id.txt_version);
        this.mLayoutAccount = (RelativeLayout) view.findViewById(R.id.layout_account);
        this.mLayoutSharerList = (OfferWallLayout) view.findViewById(R.id.layout_share_list);
    }

    private void setListeners() {
        this.mLayoutAccount.setOnClickListener(this);
    }

    private void initialize() {
        this.mContext = getSherlockActivity();
        this.mTxtInvitationCode.setText(UserModel.getInstance().getInvitationCode());
        this.mTxtAccount.setText(UserModel.getInstance().getEmail());
        this.mTxtVersion.setText(getString(R.string.tips_version) + " " + Device.getInstance().getAppVersionName());
        this.mTxtInviteTips.setText(Html.fromHtml(getString(R.string.tips_share_invitation_code, new Object[]{"<font color=\"#00A14B\">", Settings.getInstance().getInvitationPointsShow(), "</font>"})));
        this.mTxtInviteNanas.setText(getString(R.string.tips_nanas, new Object[]{Settings.getInstance().getInvitationPointsShow()}));
        initShareList();
    }

    private void initShareList() {
        int[][] snss = new int[][]{new int[]{R.string.facebook, R.drawable.ic_list_facebook}, new int[]{R.string.instagram, R.drawable.ic_list_instagram}, new int[]{R.string.google_plus, R.drawable.ic_list_gplus}, new int[]{R.string.others, R.drawable.ic_list_others}};
        List<AbstractOffer> sharerList = new ArrayList();
        for (int[] sns : snss) {
            int nanas = 0;
            try {
                nanas = sns[0] == R.string.instagram ? UserModel.getInstance().getInstagramInviteNanas() : 0;
            } catch (Exception e) {
            }
            sharerList.add(new Offer(sns[0], getString(sns[0]), nanas, sns[1], BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, false));
        }
        this.mLayoutSharerList.setData(sharerList, this.offerClickListener);
    }

    private boolean isInstagramInstalled() {
        return Device.getInstance().isPackageExisted("com.instagram.android");
    }

    private void shareViaFacebook() {
        BrowserActivity.openInSystemBrowser(this.mContext, Settings.getInstance().getFacebookShareUrl(getString(R.string.share_name), "invite"));
    }

    private void shareViaGooglePlus() {
        BrowserActivity.openInSystemBrowser(this.mContext, getString(R.string.gp_share_link));
    }

    private void showImageChoice() {
        ImageOfChoiceFragment imageOfChoiceFragment = new ImageOfChoiceFragment();
        imageOfChoiceFragment.setOnCloseListener(this);
        imageOfChoiceFragment.show(getFragmentManager(), "image choice");
    }

    private void hideOfferNanas(int position) {
        try {
            ((OfferLayout) this.mLayoutSharerList.getChildAt(position)).setNanasViewVisibility(8);
        } catch (Exception e) {
        }
    }

    private void shareViaInstagram(Uri uri) {
        shareViaAndroidIntentWithImage(uri, EmojiMapUtil.replaceCheatSheetEmojis(getString(R.string.ig_share_caption)));
    }

    private void shareViaAndroidIntent() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.tips_help_get_more_points));
        intent.putExtra("android.intent.extra.TEXT", String.format(getString(R.string.tips_help_get_more_points_body_android), new Object[]{UserModel.getInstance().getInvitationCode()}));
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getText(R.string.share_with)));
    }

    private void shareViaAndroidIntentWithImage(Uri uri, String caption) {
        Intent share = new Intent("android.intent.action.SEND");
        share.setType("image/*");
        share.putExtra("android.intent.extra.STREAM", uri);
        share.putExtra("android.intent.extra.TEXT", caption);
        startActivity(Intent.createChooser(share, getString(R.string.share_with)));
    }

    private void showLoadingView() {
        getMainActivity().setLoadingLayoutVisibility(0);
    }

    private void hideLoadingView() {
        getMainActivity().setLoadingLayoutVisibility(8);
    }

    private MainActivity getMainActivity() {
        return (MainActivity) this.mContext;
    }

    private void confirmToLogout() {
        this.mDialog = AlertDialog.confirm(this.mContext, (int) R.string.btn_log_out, BuildConfig.VERSION_NAME, (int) R.string.btn_yes, new OnClickListener() {
            public void onClick(View view) {
                new SignOutTask().execute(new Void[0]);
                InvitationFragment.this.mDialog.dismiss();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_account /*2131558491*/:
                confirmToLogout();
                return;
            default:
                return;
        }
    }

    public void onResume() {
        super.onResume();
        UserModel userModel = UserModel.getInstance();
        if (userModel.isLogged() && userModel.hasFinishedInstagramMassInvite()) {
            hideOfferNanas(1);
        }
    }

    public void onClose(Bundle bundle) {
        Uri uri = (Uri) bundle.getParcelable(ImageOfChoiceFragment.KEY_DATA);
        if (uri != null) {
            if (UserModel.getInstance().hasFinishedInstagramMassInvite()) {
                shareViaInstagram(uri);
                return;
            }
            new ShareInstagramTask().execute(new Uri[]{uri});
        }
    }
}
