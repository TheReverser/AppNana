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
import com.appnana.android.giftcardrewards.controller.UserCommand;
import com.appnana.android.giftcardrewards.exception.AccessForbiddenException;
import com.appnana.android.giftcardrewards.exception.AccountException;
import com.appnana.android.giftcardrewards.exception.EmptyPasswordException;
import com.appnana.android.giftcardrewards.exception.InvalidEmailException;
import com.appnana.android.giftcardrewards.exception.MultipleDevicesException;
import com.appnana.android.giftcardrewards.exception.SessionExpiredException;
import com.appnana.android.giftcardrewards.exception.UnknownException;
import com.appnana.android.giftcardrewards.model.AppNanaPrefrences;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.utils.MapizLog;
import com.facebook.internal.NativeProtocol;

public class LoginDialogFragment extends TrackedSherlockDialogFragment implements OnClickListener {
    public static String TAG = "LoginDialogFragment";
    private static LoginDialogFragment instance;
    Button mBtnClose;
    Button mBtnForgot;
    Button mBtnLogin;
    Button mBtnRegister;
    Activity mContext;
    EditText mEditEmail;
    EditText mEditPassword;
    RelativeLayout mLayoutLoading;

    private class LoginTask extends AsyncTask<String, Void, Boolean> {
        AlertDialog dialog;
        String email;
        int errorRes;
        OnClickListener onButtonClickListener;
        String password;

        private LoginTask() {
            this.onButtonClickListener = new OnClickListener() {
                public void onClick(View view) {
                    LoginDialogFragment.this.show(LoginDialogFragment.this.getMainActivity().getSupportFragmentManager(), LoginDialogFragment.TAG);
                    LoginTask.this.dialog.dismiss();
                }
            };
        }

        protected Boolean doInBackground(String... params) {
            try {
                this.email = params[0];
                this.password = params[1];
                UserModel.createForLoginOrRegister(LoginDialogFragment.this.mContext, this.email, this.password);
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
            } catch (AccountException e4) {
                this.errorRes = R.string.error_information;
                return Boolean.valueOf(false);
            } catch (AccessForbiddenException e5) {
                this.errorRes = R.string.error_irregular_activity;
                return Boolean.valueOf(false);
            } catch (SessionExpiredException e6) {
                this.errorRes = R.string.error_session_expired;
                return Boolean.valueOf(false);
            } catch (UnknownException e7) {
                this.errorRes = R.string.error_under_maintenance;
                return Boolean.valueOf(false);
            } catch (Exception e8) {
                this.errorRes = R.string.error_under_maintenance;
                MapizLog.e(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e8.toString());
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            LoginDialogFragment.this.getMainActivity().setLoadingLayoutVisibility(8);
            if (result.booleanValue()) {
                LoginDialogFragment.this.mContext.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).edit().putString(AppNanaPrefrences.PREF_EMAIL, this.email).putString(AppNanaPrefrences.PREF_PASSWORD, this.password).commit();
                LoginDialogFragment.this.getMainActivity().onLoggedIn();
                return;
            }
            this.dialog = AlertDialog.alert(LoginDialogFragment.this.mContext, R.string.tips_invalid, this.errorRes, this.onButtonClickListener);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            LoginDialogFragment.this.getMainActivity().setLoadingLayoutVisibility(0);
        }
    }

    public static LoginDialogFragment getInstance() {
        if (instance == null) {
            instance = new LoginDialogFragment();
        }
        return instance;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.Theme_MatchWidth_Dialog_FullWidth);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_login, null);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        setListeners();
        init();
    }

    private void init() {
        this.mContext = getActivity();
        this.mBtnForgot.setText(Html.fromHtml("<u>" + this.mBtnForgot.getText() + "</u>"));
    }

    private void findViews() {
        this.mEditEmail = (EditText) getView().findViewById(R.id.edit_email);
        this.mEditPassword = (EditText) getView().findViewById(R.id.edit_password);
        this.mBtnLogin = (Button) getView().findViewById(R.id.btn_login);
        this.mBtnForgot = (Button) getView().findViewById(R.id.btn_forgot);
        this.mBtnRegister = (Button) getView().findViewById(R.id.btn_register);
        this.mBtnClose = (Button) getView().findViewById(16908327);
        this.mLayoutLoading = (RelativeLayout) getView().findViewById(R.id.layout_loading);
    }

    private void setListeners() {
        this.mBtnLogin.setOnClickListener(this);
        this.mBtnForgot.setOnClickListener(this);
        this.mBtnClose.setOnClickListener(this);
        this.mBtnRegister.setOnClickListener(this);
    }

    private void login() {
        String email = this.mEditEmail.getText().toString();
        String password = this.mEditPassword.getText().toString();
        hideKeyboard();
        new LoginTask().execute(new String[]{email, password});
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
                RegisterDialogFragment.getInstance().show(getFragmentManager(), RegisterDialogFragment.TAG);
                dismiss();
                return;
            case R.id.btn_login /*2131558531*/:
                login();
                dismiss();
                return;
            case R.id.btn_forgot /*2131558532*/:
                forgotPassword();
                return;
            default:
                return;
        }
    }

    private void forgotPassword() {
        Intent intent = new Intent(this.mContext, BrowserActivity.class);
        intent.putExtra(BrowserActivity.EXTRA_URI, UserModel.RESET_PASS_URL);
        startActivity(intent);
    }
}
