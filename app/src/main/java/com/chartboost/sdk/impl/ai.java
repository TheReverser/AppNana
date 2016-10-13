package com.chartboost.sdk.impl;

import android.content.Context;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.impl.bn.a;

public final class ai extends LinearLayout implements a {
    private LinearLayout a;
    private bg b;
    private TextView c;
    private bh d;
    private ae e;
    private int f = Integer.MIN_VALUE;

    public ai(Context context, ae aeVar) {
        super(context);
        this.e = aeVar;
        a(context);
    }

    private void a(Context context) {
        Context context2 = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 8.0f);
        setOrientation(1);
        setGravity(17);
        this.a = new LinearLayout(context2);
        this.a.setGravity(17);
        this.a.setOrientation(0);
        this.a.setPadding(round, round, round, round);
        this.b = new bg(context2);
        this.b.setScaleType(ScaleType.CENTER_INSIDE);
        this.b.setPadding(0, 0, round, 0);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.e.a(layoutParams, this.e.s, this.e.E);
        this.c = new TextView(getContext());
        this.c.setTextColor(-1);
        this.c.setTypeface(null, 1);
        this.c.setGravity(17);
        this.a.addView(this.b, layoutParams);
        this.a.addView(this.c, new LinearLayout.LayoutParams(-2, -2));
        this.d = new bh(this, getContext()) {
            final /* synthetic */ ai a;

            protected void a(MotionEvent motionEvent) {
                this.a.d.setEnabled(false);
                this.a.e.o().i();
            }
        };
        this.d.setPadding(0, 0, 0, round);
        this.d.setScaleType(ScaleType.CENTER_INSIDE);
        this.d.setPadding(round, round, round, round);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.e.a(layoutParams, this.e.r, this.e.E);
        if (this.e.s.e()) {
            this.b.a(this.e.s);
        }
        if (this.e.r.e()) {
            this.d.a(this.e.r);
        }
        addView(this.a, new LinearLayout.LayoutParams(-2, -2));
        addView(this.d, layoutParams);
        a();
    }

    public void a(boolean z) {
        setBackgroundColor(z ? -16777216 : this.f);
    }

    public void a(String str, int i) {
        this.c.setText(str);
        this.f = i;
        a(this.e.p());
    }

    public void a() {
        a(this.e.p());
        this.c.setTextSize(2, 16.0f * FloatMath.sqrt(this.e.E));
    }

    public View b() {
        return this;
    }
}
