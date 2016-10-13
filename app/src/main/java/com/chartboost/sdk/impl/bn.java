package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.Libraries.CBOrientation.Difference;

public final class bn extends RelativeLayout {
    private a a;
    private bf b;
    private bf c;
    private bm d;
    private OrientationEventListener e;
    private Difference f = null;
    private com.chartboost.sdk.Model.a g = null;

    public interface a {
        void a();

        View b();
    }

    public bn(Context context, com.chartboost.sdk.Model.a aVar) {
        super(context);
        this.g = aVar;
        this.b = new bf(context);
        addView(this.b, new LayoutParams(-1, -1));
        this.c = new bf(context);
        addView(this.c, new LayoutParams(-1, -1));
        this.c.setVisibility(8);
        final CBPreferences instance = CBPreferences.getInstance();
        if (instance.getOrientation() != null && instance.getOrientation() != CBOrientation.UNSPECIFIED) {
            this.f = instance.getForcedOrientationDifference();
            this.e = new OrientationEventListener(this, context, 1) {
                final /* synthetic */ bn b;

                public void onOrientationChanged(int orientation) {
                    Difference forcedOrientationDifference = instance.getForcedOrientationDifference();
                    if (this.b.f != forcedOrientationDifference) {
                        this.b.f = forcedOrientationDifference;
                        if (this.b.a != null) {
                            this.b.a.a();
                        }
                        if (this.b.d != null && this.b.d.getVisibility() == 0) {
                            this.b.d.a();
                        }
                        this.b.invalidate();
                    }
                }
            };
            this.e.enable();
        }
    }

    public void a() {
        if (this.a == null) {
            this.a = this.g.i();
            addView(this.a.b(), new LayoutParams(-1, -1));
            this.a.a();
        }
        c();
    }

    public void b() {
        boolean z = !this.g.k;
        this.g.k = true;
        if (this.d == null) {
            this.d = new bm(getContext());
            this.d.setVisibility(8);
            addView(this.d, new LayoutParams(-1, -1));
        } else {
            this.c.bringToFront();
            this.c.setVisibility(0);
            this.c.a();
            be.a(false, this.b);
            this.d.bringToFront();
            this.d.a();
        }
        if (!g()) {
            this.d.setVisibility(0);
            if (z) {
                e().a();
                be.a(true, this.d);
            }
        }
    }

    public void c() {
        if (this.d != null) {
            this.d.clearAnimation();
            this.d.setVisibility(8);
        }
    }

    public void d() {
        if (this.e != null) {
            this.e.disable();
            this.e = null;
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    public bf e() {
        return this.b;
    }

    public View f() {
        return this.a == null ? null : this.a.b();
    }

    public boolean g() {
        return this.d != null && this.d.getVisibility() == 0;
    }

    public com.chartboost.sdk.Model.a h() {
        return this.g;
    }
}
