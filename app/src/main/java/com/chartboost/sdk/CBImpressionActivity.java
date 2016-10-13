package com.chartboost.sdk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.l;

public final class CBImpressionActivity extends Activity {
    protected Chartboost a;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        if (getIntent().getBooleanExtra("paramFullscreen", false)) {
            getWindow().addFlags(1024);
        }
        getWindow().setWindowAnimations(0);
        setContentView(new RelativeLayout(this));
        this.a = Chartboost.sharedChartboost();
        this.a.a(this);
    }

    protected void onStart() {
        super.onStart();
        this.a.a((Activity) this);
    }

    protected void onResume() {
        super.onResume();
        this.a.b(l.a((Activity) this));
    }

    protected void onPause() {
        super.onPause();
        this.a.c(l.a((Activity) this));
    }

    protected void onStop() {
        super.onStop();
        this.a.d(l.a((Activity) this));
    }

    protected void onDestroy() {
        super.onDestroy();
        this.a.b((Activity) this);
    }

    public void onBackPressed() {
        if (!this.a.b()) {
            super.onBackPressed();
        }
    }
}
