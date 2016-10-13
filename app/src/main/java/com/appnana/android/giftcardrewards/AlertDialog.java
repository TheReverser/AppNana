package com.appnana.android.giftcardrewards;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.net.AppIconRequest;
import com.appnana.android.net.HttpClient;
import com.appnana.android.utils.Device;

public class AlertDialog extends Activity {
    private static final int MODE_ALERT = 2130903075;
    private static final int MODE_CONFIRM = 2130903076;
    private static final int MODE_CONFIRM_STH = 2130903079;
    private static final int MODE_CONFIRM_WITH_THREE_BUTTONS = 2130903077;
    private static final int MODE_CONFIRM_WITH_THREE_BUTTONS_UP1_DOWN2 = 2130903078;
    private static final int MODE_INVITATION = 2130903080;
    private static final int MODE_NODAILY = 2130903082;
    private static final int MODE_RATING = 2130903083;
    private static final int MODE_SEARCH_OFFER = 2130903086;
    Button mBtnNegative;
    Button mBtnNeutral;
    Button mBtnPositive;
    Context mContext;
    android.app.AlertDialog mDialog;
    int mDialogMode;
    TextView mTxtMessage;
    TextView mTxtTitle;
    Window mWindow = this.mDialog.getWindow();

    public static AlertDialog alert(Context context, int titleRes, int messageRes) {
        return alert(context, context.getString(titleRes), context.getString(messageRes));
    }

    public static AlertDialog alert(Context context, int titleRes, int messageRes, OnClickListener onClickListener) {
        AlertDialog dialog = alert(context, titleRes, messageRes);
        dialog.setNegativeButton(R.string.btn_ok, onClickListener);
        return dialog;
    }

    public static AlertDialog alert(Context context, int titleRes, String message) {
        return alert(context, context.getString(titleRes), message);
    }

    public static AlertDialog alert(Context context, String title, String message) {
        AlertDialog dialog = new AlertDialog(context, MODE_ALERT);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setNegativeButton(R.string.btn_ok);
        return dialog;
    }

    public static AlertDialog alert(Context context, int titleRes, int messageRes, int buttonTextRes, OnClickListener buttonListener) {
        AlertDialog dialog = alert(context, titleRes, messageRes);
        dialog.setNegativeButton(buttonTextRes, buttonListener);
        return dialog;
    }

    public static AlertDialog alertNanas(Context context, int titleRes, String formattedNanas) {
        return alertNanas(context, context.getString(titleRes), formattedNanas);
    }

    public static AlertDialog alertNanas(Context context, String title, String formattedNanas) {
        return alert(context, title, context.getString(R.string.receive_nanas_body, new Object[]{"<font color=\"#00A14B\">", formattedNanas, "</font>"}));
    }

    public static AlertDialog confirm(Context context, int titleRes, String message, int txtPositiveRes, OnClickListener listenerPositive) {
        AlertDialog dialog = new AlertDialog(context, MODE_CONFIRM);
        dialog.setTitle(titleRes);
        dialog.setMessage(message);
        dialog.setPositiveButton(txtPositiveRes, listenerPositive);
        dialog.setNegativeButton();
        return dialog;
    }

    public static AlertDialog confirm(Context context, int titleRes, int messageRes, int txtPositiveRes, OnClickListener listenerPositive) {
        return confirm(context, titleRes, context.getString(messageRes), txtPositiveRes, listenerPositive);
    }

    public static AlertDialog confirm(Context context, int titleRes, int messageRes, int neutralTextRes, OnClickListener neutralButtonClickListener, int positiveTextRes, OnClickListener positiveButtonClickListener) {
        return confirm(context, titleRes, context.getString(messageRes), neutralTextRes, neutralButtonClickListener, positiveTextRes, positiveButtonClickListener);
    }

    public static AlertDialog confirm(Context context, int titleRes, String message, int neutralTextRes, OnClickListener neutralButtonClickListener, int positiveTextRes, OnClickListener positiveButtonClickListener) {
        AlertDialog dialog = new AlertDialog(context, MODE_CONFIRM_WITH_THREE_BUTTONS);
        dialog.setTitle(titleRes);
        dialog.setMessage(message);
        dialog.setNeutralButton(neutralTextRes, neutralButtonClickListener);
        dialog.setPositiveButton(positiveTextRes, positiveButtonClickListener);
        dialog.setNegativeButton();
        return dialog;
    }

    public static AlertDialog confirmWithUp1Down2(Context context, int titleRes, String message, int neutralTextRes, OnClickListener neutralButtonClickListener, int positiveTextRes, OnClickListener positiveButtonClickListener) {
        AlertDialog dialog = new AlertDialog(context, MODE_CONFIRM_WITH_THREE_BUTTONS_UP1_DOWN2);
        dialog.setTitle(titleRes);
        dialog.setMessage(message);
        dialog.setNeutralButton(neutralTextRes, neutralButtonClickListener);
        dialog.setPositiveButton(positiveTextRes, positiveButtonClickListener);
        dialog.setNegativeButton();
        return dialog;
    }

    public static AlertDialog confirm(Context context, int titleRes, int messageRes, String confirmString, int positiveTextRes, OnClickListener positiveButtonClickListener) {
        AlertDialog dialog = new AlertDialog(context, MODE_CONFIRM_STH);
        dialog.setTitle(titleRes);
        dialog.setMessage(messageRes);
        dialog.setPositiveButton(positiveTextRes, positiveButtonClickListener);
        dialog.setNegativeButton();
        ((TextView) dialog.getDialog().getWindow().findViewById(R.id.txt_comfirm)).setText(confirmString);
        return dialog;
    }

    public static AlertDialog alertNoDailyNanas(Context context, String nanasShow, int inviteTimes, OnClickListener onClickInviteListener, OnClickListener onClickGetPointsListener) {
        AlertDialog dialog = new AlertDialog(context, MODE_NODAILY);
        dialog.setTitle((int) R.string.no_daily_nanas_title);
        dialog.setMessage((int) R.string.no_daily_nanas_body_1);
        dialog.setNegativeButton(R.string.nav_get_nanas, onClickGetPointsListener);
        dialog.setPositiveButton(R.string.nav_invite, onClickInviteListener);
        Window window = dialog.getDialog().getWindow();
        TextView txtInviteTimes = (TextView) window.findViewById(R.id.txt_invite_at_least);
        ((TextView) window.findViewById(R.id.txt_earn_at_least)).setText(nanasShow);
        txtInviteTimes.setText(String.valueOf(inviteTimes));
        return dialog;
    }

    public static AlertDialog alertInvitaionSuccess(Context context, OnClickListener onInviteClickListener) {
        AlertDialog dialog = new AlertDialog(context, MODE_INVITATION);
        dialog.setNegativeButton(R.string.btn_ok);
        dialog.setPositiveButton(R.string.nav_invite, onInviteClickListener);
        ((TextView) dialog.getDialog().getWindow().findViewById(R.id.txt_received_nanas)).setText(Html.fromHtml(context.getString(R.string.receive_nanas_body, new Object[]{"<font color=\"#00A14B\">", Settings.getInstance().getInvitationPointsShow(), "</font>"})));
        return dialog;
    }

    public static AlertDialog alertRating(final Context context) {
        final AlertDialog dialog = new AlertDialog(context, MODE_RATING);
        dialog.setNegativeButton();
        dialog.setPositiveButton(R.string.btn_submit);
        final Window window = dialog.getDialog().getWindow();
        ((RatingBar) window.findViewById(R.id.rating_bar)).setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 3.0f) {
                    EditText editFeedback = (EditText) window.findViewById(R.id.edit_feedback);
                    LinearLayout layoutButtons = (LinearLayout) window.findViewById(R.id.layout_buttons);
                    window.clearFlags(131080);
                    editFeedback.setVisibility(0);
                    layoutButtons.setVisibility(0);
                    return;
                }
                BrowserActivity.openInPlayStore(context, Device.getInstance().getClientPackage(), "rating");
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public static AlertDialog alertSearchOffer(Context context, String keyword, String appId, OnClickListener onPositiveButtonClickListener) {
        final AlertDialog dialog = new AlertDialog(context, MODE_SEARCH_OFFER);
        dialog.setNegativeButton(R.string.btn_close_string);
        Window window = dialog.getDialog().getWindow();
        final Button btnCloseSymbol = (Button) window.findViewById(R.id.btn_close_symbol);
        ImageView imgAppIcon = (ImageView) window.findViewById(R.id.img_app_icon);
        ((TextView) window.findViewById(R.id.txt_keyword)).setText(keyword);
        final AppIconRequest request = new AppIconRequest(context, appId, Settings.getInstance().getAppIconUrlRegex(), imgAppIcon);
        HttpClient.getInstance().addToRequestQueue(request, "GetAppIcon: " + appId);
        btnCloseSymbol.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final Context context2 = context;
        final OnClickListener onClickListener = onPositiveButtonClickListener;
        dialog.setPositiveButton(R.string.btn_open_play_store, new OnClickListener() {
            public void onClick(View v) {
                if (!Device.getInstance().isPackageExisted("com.android.vending")) {
                    AlertDialog.alert(context2, (int) R.string.tips_sorry, (int) R.string.error_playstore_not_exists);
                } else if (request.isComplete()) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v);
                    }
                    dialog.mBtnNegative.setVisibility(0);
                    dialog.mBtnPositive.setVisibility(8);
                    btnCloseSymbol.setVisibility(8);
                } else {
                    AlertDialog.alert(context2, (int) R.string.tips_sorry, (int) R.string.error_loading);
                }
            }
        });
        return dialog;
    }

    public AlertDialog(Context context, int mode) {
        this.mContext = context;
        this.mDialogMode = mode;
        this.mDialog = new Builder(context).create();
        this.mDialog.show();
        this.mWindow.setContentView(mode);
        LayoutParams params = this.mWindow.getAttributes();
        params.width = -1;
        this.mWindow.setAttributes(params);
        findViews();
    }

    private void findViews() {
        this.mTxtTitle = (TextView) this.mWindow.findViewById(R.id.txt_title);
        this.mTxtMessage = (TextView) this.mWindow.findViewById(R.id.txt_message);
        switch (this.mDialogMode) {
            case MODE_ALERT /*2130903075*/:
                break;
            case MODE_CONFIRM /*2130903076*/:
            case MODE_CONFIRM_STH /*2130903079*/:
            case MODE_INVITATION /*2130903080*/:
            case MODE_NODAILY /*2130903082*/:
            case MODE_RATING /*2130903083*/:
            case MODE_SEARCH_OFFER /*2130903086*/:
                break;
            case MODE_CONFIRM_WITH_THREE_BUTTONS /*2130903077*/:
            case MODE_CONFIRM_WITH_THREE_BUTTONS_UP1_DOWN2 /*2130903078*/:
                this.mBtnNeutral = (Button) this.mWindow.findViewById(R.id.btn_neutral);
                break;
            default:
                return;
        }
        this.mBtnPositive = (Button) this.mWindow.findViewById(R.id.btn_positive);
        this.mBtnNegative = (Button) this.mWindow.findViewById(R.id.btn_negative);
    }

    public void setTitle(int titleRes) {
        this.mTxtTitle.setText(titleRes);
    }

    public void setTitle(String title) {
        this.mTxtTitle.setText(title);
    }

    public void setMessage(int messageRes) {
        this.mTxtMessage.setText(messageRes);
    }

    public void setMessage(String message) {
        this.mTxtMessage.setText(Html.fromHtml(message));
    }

    public void setNeutralButton(int textRes, OnClickListener listener) {
        this.mBtnNeutral.setText(textRes);
        this.mBtnNeutral.setOnClickListener(listener);
    }

    public void setPositiveButton(int textRes) {
        setPositiveButton(textRes, null);
    }

    public void setPositiveButton(int textRes, OnClickListener listener) {
        this.mBtnPositive.setText(textRes);
        if (listener == null) {
            this.mBtnPositive.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.this.dismiss();
                }
            });
        } else {
            this.mBtnPositive.setOnClickListener(listener);
        }
    }

    public void setNegativeButton() {
        setNegativeButton(R.string.tips_cancel);
    }

    public void setNegativeButton(int textRes) {
        setNegativeButton(textRes, null);
    }

    public void setNegativeButton(int textRes, OnClickListener listener) {
        this.mBtnNegative.setText(textRes);
        if (listener == null) {
            this.mBtnNegative.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.this.dismiss();
                }
            });
        } else {
            this.mBtnNegative.setOnClickListener(listener);
        }
    }

    public android.app.AlertDialog getDialog() {
        return this.mDialog;
    }

    public void dismiss() {
        this.mDialog.dismiss();
    }
}
