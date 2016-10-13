package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.d;

public abstract class af extends d {
    private static int j = 21504;
    protected String C;
    protected com.chartboost.sdk.Libraries.e.a D;
    public float E = 1.0f;
    private i k = new i(this);
    private i l = new i(this);
    private i m = new i(this);
    private i n = new i(this);

    public abstract class a extends com.chartboost.sdk.d.a {
        private boolean c = false;
        protected bg f;
        protected bh g;
        protected bp h;
        final /* synthetic */ af i;

        protected a(af afVar, Context context) {
            this.i = afVar;
            super(afVar, context);
            setBackgroundColor(0);
            setLayoutParams(new LayoutParams(-1, -1));
            this.h = new bp(context);
            this.f = new bg(context);
            this.h.a(this.f);
            addView(this.h, new LayoutParams(-1, -1));
        }

        protected void d() {
            this.g = new bh(this, getContext()) {
                final /* synthetic */ a a;

                protected void a(MotionEvent motionEvent) {
                    this.a.g();
                }
            };
            this.h.a(this.g);
        }

        protected void a(int i, int i2) {
            i c;
            if (!this.c) {
                d();
                this.c = true;
            }
            boolean isPortrait = this.i.a().isPortrait();
            i a = isPortrait ? this.i.k : this.i.l;
            if (isPortrait) {
                c = this.i.m;
            } else {
                c = this.i.n;
            }
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            CBLogging.a("CBNativeInterstitialViewProtocol", "Laying out interstitial");
            this.i.a(layoutParams, a, 1.0f);
            this.i.E = Math.min(Math.min(((float) i) / ((float) layoutParams.width), ((float) i2) / ((float) layoutParams.height)), 1.0f);
            layoutParams.width = (int) (((float) layoutParams.width) * this.i.E);
            layoutParams.height = (int) (((float) layoutParams.height) * this.i.E);
            Point b = this.i.b(isPortrait ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round((((float) (i - layoutParams.width)) / 2.0f) + ((((float) b.x) / a.g()) * this.i.E));
            layoutParams.topMargin = Math.round(((((float) b.y) / a.g()) * this.i.E) + (((float) (i2 - layoutParams.height)) / 2.0f));
            this.i.a(layoutParams2, c, this.i.E);
            b = this.i.b(isPortrait ? "close-portrait" : "close-landscape");
            int round = Math.round((((((float) (-layoutParams2.width)) * 1.0f) * this.i.E) + ((float) b.x)) - (((float) layoutParams2.width) / 2.0f)) + (layoutParams.leftMargin + layoutParams.width);
            int round2 = Math.round((((float) b.y) + (((float) (layoutParams2.height * 0)) * this.i.E)) - (((float) layoutParams2.height) / 2.0f)) + layoutParams.topMargin;
            layoutParams2.leftMargin = Math.min(Math.max(0, round), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams2.height);
            layoutParams2.addRule(11, 0);
            this.f.setLayoutParams(layoutParams);
            this.g.setLayoutParams(layoutParams2);
            this.f.setScaleType(ScaleType.FIT_CENTER);
            this.f.a(a);
            this.g.a(c);
        }

        protected void g() {
            this.i.h();
        }

        public void c() {
            super.c();
            this.f = null;
            this.g = null;
        }

        protected void a(View view) {
            while (findViewById(af.j) != null) {
                af.r();
            }
            view.setId(af.j);
            af.r();
        }
    }

    static /* synthetic */ int r() {
        int i = j;
        j = i + 1;
        return i;
    }

    public af(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        this.C = aVar.e("ad_id");
        this.D = aVar.a("ux");
        if (this.d.b("frame-portrait") || this.d.b("close-portrait")) {
            this.h = false;
        }
        if (this.d.b("frame-landscape") || this.d.b("close-landscape")) {
            this.i = false;
        }
        this.l.a("frame-landscape");
        this.k.a("frame-portrait");
        this.n.a("close-landscape");
        this.m.a("close-portrait");
        return true;
    }

    protected Point b(String str) {
        com.chartboost.sdk.Libraries.e.a a = this.d.a(str).a("offset");
        if (a.c()) {
            return new Point(a.f("x"), a.f("y"));
        }
        return new Point(0, 0);
    }

    public void a(ViewGroup.LayoutParams layoutParams, i iVar, float f) {
        layoutParams.width = (int) ((((float) iVar.b()) / iVar.g()) * f);
        layoutParams.height = (int) ((((float) iVar.c()) / iVar.g()) * f);
    }

    public void d() {
        super.d();
        this.l.d();
        this.k.d();
        this.n.d();
        this.m.d();
        this.l = null;
        this.k = null;
        this.n = null;
        this.m = null;
    }
}
