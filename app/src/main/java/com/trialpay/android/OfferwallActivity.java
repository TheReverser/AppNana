package com.trialpay.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import com.trialpay.android.OfferwallView.EventListener;

public class OfferwallActivity extends Activity implements EventListener {
    private static final String TAG = "Trialpay.OfferwallActivity";
    private OfferwallView offerwallView;
    private String touchpointName;

    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        if (BaseTrialpayManager.getInstance() == null) {
            Log.e(TAG, "TrialpayManager is not initialized yet");
            finish();
            return;
        }
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
            this.offerwallView = new OfferwallView(this);
            this.offerwallView.setOnEventListener(this);
        }
        setContentView(this.offerwallView);
        if (savedInstanceState == null && !this.offerwallView.loadContent(this.touchpointName)) {
            Log.e(TAG, "Failed to open offerwall for touchpoint: " + this.touchpointName);
            finish();
        }
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
        BaseTrialpayManager.getInstance().handleOpenOfferwallView(this.touchpointName);
    }

    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        forceFullscreenSettings(500);
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.offerwallView.onKeyUp(keyCode, event);
    }

    public void handleCloseOfferwallView(String touchpointName) {
        Log.d(TAG, "closeOfferwallView");
        this.offerwallView.close();
        setResult(-1);
        finish();
    }

    public void handleOpenOfferwallView(String touchpointName) {
    }

    protected void forceFullscreenSettings() {
        forceFullscreenSettings(-1);
    }

    protected void forceFullscreenSettings(int delayMsecs) {
        Runnable r = new Runnable() {
            public void run() {
                if ((OfferwallActivity.this.getWindow().getAttributes().flags & 1024) != 0) {
                    OfferwallActivity.this.getWindow().setFlags(1024, 1024);
                }
            }
        };
        if (delayMsecs < 0) {
            r.run();
        } else {
            new Handler().postDelayed(r, (long) delayMsecs);
        }
    }
}
