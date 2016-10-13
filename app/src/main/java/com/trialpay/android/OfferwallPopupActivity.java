package com.trialpay.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.trialpay.android.OfferwallView.EventListener;

public class OfferwallPopupActivity extends Activity implements EventListener {
    private static final String TAG = "TrialPay.OfferwallPopupActivity";
    private OfferwallView offerwallView;
    private AlertDialog popup;
    private String touchpointName;

    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.isEmpty()) {
            Log.e(TAG, "No parameters were sent");
            finish();
            return;
        }
        this.touchpointName = extras.getString(Strings.EXTRA_KEY_TOUCHPOINT_NAME);
        if (this.touchpointName == null) {
            Log.e(TAG, "touchpointName is missing");
            finish();
            return;
        }
        if (this.offerwallView == null) {
            this.offerwallView = new OfferwallView(this, true);
            this.offerwallView.setOnEventListener(this);
        }
        setContentView(new LinearLayout(this));
        if (savedInstanceState == null && !this.offerwallView.loadContent(this.touchpointName)) {
            Log.e(TAG, "Failed to open offerwall for touchpoint: " + this.touchpointName);
            finish();
        }
        createPopup();
    }

    public void createPopup() {
        int resource;
        EditText keyboardHack = new EditText(this);
        keyboardHack.setVisibility(8);
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.addView(this.offerwallView);
        wrapper.addView(keyboardHack, -1, -2);
        if (VERSION.SDK_INT >= 14) {
            resource = 16973931;
        } else {
            resource = 16973829;
        }
        Builder builder = new Builder(new ContextThemeWrapper(this, resource));
        builder.setView(wrapper);
        this.popup = builder.create();
        this.popup.setCancelable(false);
        this.popup.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1) {
                    return OfferwallPopupActivity.this.offerwallView.onKeyUp(i, keyEvent);
                }
                return false;
            }
        });
        this.popup.getWindow().setSoftInputMode(16);
        this.popup.requestWindowFeature(1);
    }

    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState " + this.touchpointName);
        this.offerwallView.saveState(outState);
        outState.putString("touchpointName", this.touchpointName);
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        this.touchpointName = savedInstanceState.getString("touchpointName");
        Log.d(TAG, "onRestoreInstanceState " + this.touchpointName);
        this.offerwallView.restoreState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
        this.popup.show();
        BaseTrialpayManager.getInstance().handleOpenOfferwallView(this.touchpointName);
    }

    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        this.offerwallView.unloadOfferContainer();
        this.popup.dismiss();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.offerwallView.onKeyUp(keyCode, event);
    }

    public void handleCloseOfferwallView(String touchpointName) {
        Log.d(TAG, "closeOfferwallView");
        setResult(-1);
        finish();
    }

    public void handleOpenOfferwallView(String touchpointName) {
    }
}
