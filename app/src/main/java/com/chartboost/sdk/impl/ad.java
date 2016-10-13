package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.i;

public class ad extends af {
    private i j = new i(this);
    private i k = new i(this);

    public class a extends com.chartboost.sdk.impl.af.a {
        protected bh c;
        final /* synthetic */ ad d;

        protected a(final ad adVar, Context context) {
            this.d = adVar;
            super(adVar, context);
            this.c = new bh(this, context) {
                final /* synthetic */ a b;

                protected void a(MotionEvent motionEvent) {
                    this.b.a(motionEvent.getX(), motionEvent.getY());
                }
            };
            a(this.c);
            this.h.a(this.c);
        }

        protected void a(float f, float f2) {
            this.d.a(null, null);
        }

        protected void a(int i, int i2) {
            super.a(i, i2);
            boolean isPortrait = this.d.a().isPortrait();
            i a = isPortrait ? this.d.j : this.d.k;
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            this.d.a(layoutParams, a, this.d.E);
            Point b = this.d.b(isPortrait ? "ad-portrait" : "ad-landscape");
            layoutParams.leftMargin = Math.round((((float) (i - layoutParams.width)) / 2.0f) + ((((float) b.x) / a.g()) * this.d.E));
            layoutParams.topMargin = Math.round(((((float) b.y) / a.g()) * this.d.E) + (((float) (i2 - layoutParams.height)) / 2.0f));
            this.c.setLayoutParams(layoutParams);
            this.c.setScaleType(ScaleType.FIT_CENTER);
            this.c.a(a);
        }

        public void c() {
            super.c();
            this.c = null;
        }
    }

    public ad(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
    }

    protected com.chartboost.sdk.d.a a(Context context) {
        return new a(this, context);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        if (this.d.b("ad-portrait")) {
            this.h = false;
        }
        if (this.d.b("ad-landscape")) {
            this.i = false;
        }
        this.k.a("ad-landscape");
        this.j.a("ad-portrait");
        return true;
    }

    public void d() {
        super.d();
        this.k.d();
        this.j.d();
        this.k = null;
        this.j = null;
    }
}
