package com.chartboost.sdk.impl;

import android.content.Context;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.impl.bn.a;

public final class ag extends LinearLayout implements a {
    private RelativeLayout a;
    private bg b;
    private bh c;
    private TextView d;
    private TextView e;
    private ae f;

    public ag(Context context, ae aeVar) {
        super(context);
        this.f = aeVar;
        a(context);
    }

    private void a(Context context) {
        Context context2 = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 8.0f);
        setGravity(1);
        setOrientation(1);
        this.a = new RelativeLayout(context2);
        this.b = new bg(context2);
        this.b.setPadding(round, round, round, round);
        if (this.f.u.e()) {
            this.b.a(this.f.u);
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        this.c = new bh(this, context2) {
            final /* synthetic */ ag a;

            protected void a(MotionEvent motionEvent) {
                this.a.f.o().h();
            }
        };
        this.c.setPadding(round, round, round, round);
        if (this.f.v.e()) {
            this.c.a(this.f.v);
        }
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(11);
        this.a.addView(this.b, layoutParams);
        this.a.addView(this.c, layoutParams2);
        this.d = new TextView(getContext());
        this.d.setTextColor(-15264491);
        this.d.setTypeface(null, 1);
        this.d.setGravity(17);
        this.d.setPadding(round, 0, round, round);
        this.e = new TextView(getContext());
        this.e.setTextColor(-15264491);
        this.e.setTypeface(null, 1);
        this.e.setGravity(17);
        this.e.setPadding(round, 0, round, round);
        addView(this.a);
        addView(this.d);
        addView(this.e);
        a();
    }

    public void a() {
        this.d.setTextSize(2, 18.0f * FloatMath.sqrt(this.f.E));
        this.e.setTextSize(2, 16.0f * FloatMath.sqrt(this.f.E));
    }

    public View b() {
        return this;
    }

    public void a(String str, String str2) {
        this.d.setText(str);
        this.e.setText(str2);
    }
}
