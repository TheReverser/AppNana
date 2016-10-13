package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.CBOrientation.Difference;
import com.chartboost.sdk.impl.bn.a;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public final class bm extends LinearLayout implements a {
    private TextView a;
    private bp b;
    private bo c;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Difference.values().length];

        static {
            try {
                a[Difference.ANGLE_90.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Difference.ANGLE_180.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Difference.ANGLE_270.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Difference.ANGLE_0.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public bm(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        setGravity(17);
        this.a = new TextView(getContext());
        this.a.setTextColor(-1);
        this.a.setTextSize(2, 16.0f);
        this.a.setTypeface(null, 1);
        this.a.setText("Loading...");
        this.a.setGravity(17);
        this.b = new bp(context, this.a);
        this.c = new bo(getContext());
        addView(this.b);
        addView(this.c);
        a();
    }

    public void a() {
        removeView(this.b);
        removeView(this.c);
        float f = getContext().getResources().getDisplayMetrics().density;
        int round = Math.round(20.0f * f);
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        switch (AnonymousClass1.a[CBPreferences.getInstance().getForcedOrientationDifference().ordinal()]) {
            case Gender.FEMALE /*1*/:
                setOrientation(0);
                layoutParams = new LinearLayout.LayoutParams(Math.round(f * 32.0f), -1);
                layoutParams.setMargins(round, round, 0, round);
                addView(this.c, layoutParams);
                layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
                layoutParams2.setMargins(round, round, round, round);
                addView(this.b, layoutParams2);
                return;
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                setOrientation(1);
                layoutParams = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0f));
                layoutParams.setMargins(round, round, round, 0);
                addView(this.c, layoutParams);
                layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.setMargins(round, round, round, round);
                addView(this.b, layoutParams2);
                return;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                setOrientation(0);
                layoutParams = new LinearLayout.LayoutParams(-2, -1);
                layoutParams.setMargins(round, round, 0, round);
                addView(this.b, layoutParams);
                layoutParams = new LinearLayout.LayoutParams(Math.round(f * 32.0f), -1);
                layoutParams.setMargins(round, round, round, round);
                addView(this.c, layoutParams);
                return;
            default:
                setOrientation(1);
                layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(round, round, round, 0);
                addView(this.b, layoutParams);
                layoutParams = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0f));
                layoutParams.setMargins(round, round, round, round);
                addView(this.c, layoutParams);
                return;
        }
    }

    public View b() {
        return this;
    }
}
