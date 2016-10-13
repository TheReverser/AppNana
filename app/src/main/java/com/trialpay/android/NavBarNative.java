package com.trialpay.android;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.trialpay.android.NavBar.EventContainerStatus;
import com.trialpay.android.NavBar.EventPageStatus;
import com.trialpay.android.NavBar.EventSource;

public class NavBarNative extends NavBarAbstract {
    private static String TAG = "NavBarNative";
    private Button doneButton;
    private RelativeLayout layout;
    private ProgressBar progressBar;

    public NavBarNative(Context context) {
        this.layout = new RelativeLayout(context);
        this.layout.setLayoutParams(new LayoutParams(-1, -2));
        this.layout.setPadding(3, 3, 3, 3);
        this.layout.setGravity(1);
        RelativeLayout layout2 = new RelativeLayout(context);
        this.layout.setLayoutParams(new LayoutParams(-1, -2));
        this.layout.setPadding(3, 3, 3, 3);
        this.layout.setGravity(3);
        this.doneButton = new Button(context);
        this.doneButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.doneButton.setText("Done");
        this.doneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                NavBarNative.this.closeCommandListener.onCommand();
            }
        });
        layout2.addView(this.doneButton);
        this.layout.addView(layout2);
        this.progressBar = new ProgressBar(context);
        this.progressBar.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    }

    public View getView() {
        return this.layout;
    }

    public void showSpinner() {
        if (this.progressBar.getParent() != null) {
            ((ViewGroup) this.progressBar.getParent()).removeView(this.progressBar);
        }
        this.layout.addView(this.progressBar);
    }

    public void hideSpinner() {
        this.layout.removeView(this.progressBar);
    }

    public void open(String vic) {
    }

    public void setTitle(String title) {
    }

    public void setSubTitle(String subtitle) {
    }

    public void executeCommand(String jsCommand) {
        Utils.assertTrue(true, "Not implemented", TAG);
    }

    public void reload() {
    }

    public void onPageStatusChanged(EventSource source, EventPageStatus newStatus, String url) {
        Utils.assertTrue(true, "Not implemented", TAG);
    }

    public void saveState(Bundle outState) {
        Utils.assertTrue(true, "Not implemented", TAG);
    }

    public void onContainerStatusChanged(EventSource source, EventContainerStatus newStatus) {
        Utils.assertTrue(true, "Not implemented", TAG);
    }

    public void restoreState(Bundle inState) {
        Utils.assertTrue(true, "Not implemented", TAG);
    }
}
