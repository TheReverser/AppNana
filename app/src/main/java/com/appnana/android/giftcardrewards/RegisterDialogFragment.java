package com.appnana.android.giftcardrewards;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.exception.AccountException;
import com.appnana.android.giftcardrewards.exception.DuplicatedException;
import com.appnana.android.giftcardrewards.exception.EmptyPasswordException;
import com.appnana.android.giftcardrewards.exception.InvalidEmailException;
import com.appnana.android.giftcardrewards.exception.UnknownException;
import com.appnana.android.giftcardrewards.model.AppNanaPrefrences;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.utils.MapizLog;
import com.facebook.BuildConfig;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;

public class RegisterDialogFragment extends TrackedSherlockDialogFragment implements OnClickListener {
    public static String TAG = "RegisterDialogFragment";
    private static RegisterDialogFragment instance;
    Button mBtnClose;
    Button mBtnLogin;
    Button mBtnRegister;
    Activity mContext;
    AlertDialog mDialog;
    EditText mEditEmail;
    EditText mEditPassword;
    RelativeLayout mLayoutLoading;
    TextView mTxtTerms;
    OnClickListener onErrorButtonClickListener = new OnClickListener() {
        public void onClick(View view) {
            RegisterDialogFragment.this.show(RegisterDialogFragment.this.getMainActivity().getSupportFragmentManager(), RegisterDialogFragment.TAG);
            RegisterDialogFragment.this.mDialog.dismiss();
        }
    };

    private class RegisterTask extends AsyncTask<String, Void, Boolean> {
        String email;
        int errorRes;
        String password;

        private RegisterTask() {
        }

        protected Boolean doInBackground(String... params) {
            try {
                this.email = params[0];
                this.password = params[1];
                UserModel.createForLoginOrRegister(RegisterDialogFragment.this.mContext, this.email, this.password);
                UserCommand.create().register();
                return Boolean.valueOf(true);
            } catch (InvalidEmailException e) {
                this.errorRes = R.string.invalid_email_address;
                return Boolean.valueOf(false);
            } catch (EmptyPasswordException e2) {
                this.errorRes = R.string.must_input_password;
                return Boolean.valueOf(false);
            } catch (AccountException e3) {
                this.errorRes = R.string.error_information;
                return Boolean.valueOf(false);
            } catch (UnknownException e4) {
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            } catch (DuplicatedException e5) {
                this.errorRes = R.string.register_email_registered_body;
                return Boolean.valueOf(false);
            } catch (Exception e6) {
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e6.toString());
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            RegisterDialogFragment.this.getMainActivity().setLoadingLayoutVisibility(8);
            if (result.booleanValue()) {
                RegisterDialogFragment.this.mContext.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).edit().putString(AppNanaPrefrences.PREF_EMAIL, this.email).putString(AppNanaPrefrences.PREF_PASSWORD, RegisterDialogFragment.this.mEditPassword.getText().toString()).apply();
                RegisterDialogFragment.this.getMainActivity().onLoggedIn();
                return;
            }
            RegisterDialogFragment.this.mDialog = AlertDialog.alert(RegisterDialogFragment.this.mContext, R.string.tips_invalid, this.errorRes, RegisterDialogFragment.this.onErrorButtonClickListener);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            RegisterDialogFragment.this.getMainActivity().setLoadingLayoutVisibility(0);
        }
    }

    public static RegisterDialogFragment getInstance() {
        if (instance == null) {
            instance = new RegisterDialogFragment();
        }
        return instance;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.Theme_MatchWidth_Dialog_FullWidth);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_register, null);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        setListeners();
        initialize();
    }

    private void initialize() {
        this.mContext = getActivity();
        this.mBtnLogin.setText(Html.fromHtml("<u>" + this.mBtnLogin.getText() + "</u>"));
        this.mTxtTerms.setText(Html.fromHtml(getString(R.string.tips_terms_service, new Object[]{"<u>", "</u>"})));
    }

    private void findViews() {
        this.mEditEmail = (EditText) getView().findViewById(R.id.edit_email);
        this.mEditPassword = (EditText) getView().findViewById(R.id.edit_password);
        this.mBtnRegister = (Button) getView().findViewById(R.id.btn_register);
        this.mBtnLogin = (Button) getView().findViewById(R.id.btn_login);
        this.mBtnClose = (Button) getView().findViewById(16908327);
        this.mLayoutLoading = (RelativeLayout) getView().findViewById(R.id.layout_loading);
        this.mTxtTerms = (TextView) getView().findViewById(R.id.txt_terms);
    }

    private void setListeners() {
        this.mBtnRegister.setOnClickListener(this);
        this.mBtnLogin.setOnClickListener(this);
        this.mTxtTerms.setOnClickListener(this);
        this.mBtnClose.setOnClickListener(this);
    }

    private void register() {
        final String email = this.mEditEmail.getText().toString().trim();
        final String password = this.mEditPassword.getText().toString().trim();
        if (email.equals(BuildConfig.VERSION_NAME)) {
            this.mDialog = AlertDialog.alert(this.mContext, R.string.tips_invalid, R.string.invalid_email_address, this.onErrorButtonClickListener);
        } else if (password.equals(BuildConfig.VERSION_NAME)) {
            this.mDialog = AlertDialog.alert(this.mContext, R.string.tips_invalid, R.string.must_input_password, this.onErrorButtonClickListener);
        } else {
            hideKeyboard();
            this.mDialog = AlertDialog.confirm(this.mContext, R.string.tips_important, R.string.register_confirm_email_body, email, R.string.btn_yes, new OnClickListener() {
                public void onClick(View v) {
                    new RegisterTask().execute(new String[]{email, password});
                    RegisterDialogFragment.this.mDialog.dismiss();
                }
            });
        }
    }

    private void getTerms() {
        Intent intent = new Intent(this.mContext, BrowserActivity.class);
        intent.putExtra(BrowserActivity.EXTRA_URI, Settings.getInstance().getTermsURL());
        intent.putExtra(ShareConstants.TITLE, R.string.terms_service);
        startActivity(intent);
    }

    public MainActivity getMainActivity() {
        return (MainActivity) this.mContext;
    }

    private void hideKeyboard() {
        getMainActivity().hideKeyboard(new EditText[]{this.mEditEmail, this.mEditPassword});
    }

    public void dismiss() {
        hideKeyboard();
        super.dismiss();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case 16908327:
                dismiss();
                return;
            case R.id.btn_register /*2131558530*/:
                register();
                dismiss();
                return;
            case R.id.btn_login /*2131558531*/:
                LoginDialogFragment.getInstance().show(getFragmentManager(), LoginDialogFragment.TAG);
                dismiss();
                return;
            case R.id.txt_terms /*2131558538*/:
                getTerms();
                return;
            default:
                return;
        }
    }
}
