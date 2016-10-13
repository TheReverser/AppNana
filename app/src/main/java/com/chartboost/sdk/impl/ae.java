package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.d;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public class ae extends ad {
    protected String A;
    protected int B;
    private boolean F;
    private boolean G;
    private boolean H;
    protected b j;
    protected int k;
    protected boolean l;
    protected boolean m;
    protected boolean n;
    protected boolean o;
    protected i p;
    protected i q;
    protected i r;
    protected i s;
    protected i t;
    protected i u;
    protected i v;
    protected i w;
    protected int x;
    public boolean y;
    protected String z;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.REWARD_OFFER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.VIDEO_PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.POST_VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public class a extends com.chartboost.sdk.impl.ad.a {
        final /* synthetic */ ae e;
        private bh j;
        private am k;
        private ai l;
        private ag m;
        private aj n;

        private a(final ae aeVar, Context context) {
            com.chartboost.sdk.Libraries.e.a a;
            this.e = aeVar;
            super(aeVar, context);
            this.l = new ai(context, aeVar);
            this.l.setVisibility(8);
            this.h.a(this.l);
            this.k = new am(context, aeVar);
            this.k.setVisibility(8);
            this.h.a(this.k);
            this.m = new ag(context, aeVar);
            this.m.setVisibility(8);
            this.h.a(this.m);
            this.n = new aj(context, aeVar);
            this.n.setVisibility(8);
            this.h.a(this.n);
            this.j = new bh(this, getContext()) {
                final /* synthetic */ a b;

                protected void a(MotionEvent motionEvent) {
                    this.b.n.a(false);
                    if (this.b.e.j == b.VIDEO_PLAYING) {
                        this.b.b(false);
                    }
                    com.chartboost.sdk.Tracking.a.c(this.b.e.A, this.b.e.e.m());
                    this.b.a(true);
                }
            };
            this.j.setVisibility(8);
            this.h.a(this.j);
            if (aeVar.D.a("progress").c("background-color") && aeVar.D.a("progress").c("border-color") && aeVar.D.a("progress").c("progress-color") && aeVar.D.a("progress").c("radius")) {
                ah d = this.k.d();
                d.a(d.a(aeVar.D.a("progress").e("background-color")));
                d.b(d.a(aeVar.D.a("progress").e("border-color")));
                d.c(d.a(aeVar.D.a("progress").e("progress-color")));
                d.b(aeVar.D.a("progress").a("radius").j());
            }
            if (aeVar.D.a("video-controls-background").c("color")) {
                this.k.a(d.a(aeVar.D.a("video-controls-background").e("color")));
            }
            if (aeVar.n) {
                this.m.a(aeVar.D.a("post-video-toaster").e(ShareConstants.WEB_DIALOG_PARAM_TITLE), aeVar.D.a("post-video-toaster").e("tagline"));
            }
            if (aeVar.m) {
                this.l.a(aeVar.D.a("confirmation").e("text"), d.a(aeVar.D.a("confirmation").e("color")));
            }
            if (aeVar.o) {
                this.n.a(aeVar.D.a("post-video-reward-toaster").a("position").equals("inside-top") ? com.chartboost.sdk.impl.aj.a.TOP : com.chartboost.sdk.impl.aj.a.BOTTOM);
                this.n.a(aeVar.D.a("post-video-reward-toaster").e("text"));
                if (aeVar.u.e()) {
                    this.n.a(aeVar.w);
                }
            }
            if (aeVar.d.a("video-click-button").b()) {
                this.k.e();
            }
            this.k.c(aeVar.D.i("video-progress-timer-enabled"));
            if (aeVar.a().isPortrait()) {
                a = aeVar.d.a("video-landscape");
            } else {
                a = aeVar.d.a("video-portrait");
            }
            bd a2 = bd.a();
            aeVar.A = a.e(ShareConstants.WEB_DIALOG_PARAM_ID);
            if (TextUtils.isEmpty(aeVar.A)) {
                aeVar.a(CBImpressionError.INTERNAL);
                return;
            }
            if (aeVar.z == null) {
                aeVar.z = a2.a(aeVar.A);
            }
            if (aeVar.z == null) {
                aeVar.a(CBImpressionError.INTERNAL);
            } else {
                this.k.a(aeVar.z);
            }
        }

        protected void d() {
            super.d();
            if (this.e.j != b.REWARD_OFFER || this.e.m) {
                a(this.e.j, false);
            } else {
                a(false);
            }
        }

        public void e() {
            b(true);
            if (this.e.k <= 1) {
                this.e.e.c();
            }
        }

        protected void a(int i, int i2) {
            super.a(i, i2);
            boolean isPortrait = this.e.a().isPortrait();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
            this.e.a(layoutParams, isPortrait ? this.e.q : this.e.p, this.e.E);
            Point b = this.e.b(isPortrait ? "replay-portrait" : "replay-landscape");
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.g.getLayoutParams();
            int round = Math.round((((((float) layoutParams5.leftMargin) + (((float) layoutParams5.width) / 2.0f)) + ((float) b.x)) - (((float) layoutParams.width) / 2.0f)) - ((CBUtility.b(40, getContext()) + (((float) layoutParams5.width) / 2.0f)) + (((float) layoutParams.width) / 2.0f)));
            int round2 = Math.round((((float) (b.y + layoutParams5.topMargin)) + (((float) layoutParams5.height) / 2.0f)) - (((float) layoutParams.height) / 2.0f));
            layoutParams.leftMargin = Math.min(Math.max(0, round), i - layoutParams.width);
            layoutParams.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams.height);
            this.j.bringToFront();
            if (isPortrait) {
                this.j.a(this.e.q);
            } else {
                this.j.a(this.e.p);
            }
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
            if (this.e.p()) {
                layoutParams5.leftMargin = 0;
                layoutParams5.topMargin = 0;
                layoutParams5.addRule(11);
                this.g.setLayoutParams(layoutParams5);
                this.f.setVisibility(8);
            } else {
                layoutParams2.width = layoutParams6.width;
                layoutParams2.height = layoutParams6.height;
                layoutParams2.leftMargin = layoutParams6.leftMargin;
                layoutParams2.topMargin = layoutParams6.topMargin;
                layoutParams3.width = layoutParams6.width;
                layoutParams3.height = layoutParams6.height;
                layoutParams3.leftMargin = layoutParams6.leftMargin;
                layoutParams3.topMargin = layoutParams6.topMargin;
                this.f.setVisibility(0);
            }
            layoutParams4.width = layoutParams6.width;
            layoutParams4.height = layoutParams6.height;
            layoutParams4.leftMargin = layoutParams6.leftMargin;
            layoutParams4.topMargin = layoutParams6.topMargin;
            this.l.setLayoutParams(layoutParams2);
            this.k.setLayoutParams(layoutParams3);
            this.m.setLayoutParams(layoutParams4);
            this.j.setLayoutParams(layoutParams);
        }

        private void a(boolean z) {
            if (this.e.j != b.VIDEO_PLAYING) {
                this.e.e.o();
                if (this.e.m) {
                    com.chartboost.sdk.Tracking.a.b("integrated", this.e.C);
                    a(b.REWARD_OFFER, z);
                    return;
                }
                a(b.VIDEO_PLAYING, z);
                ae aeVar = this.e;
                aeVar.k++;
                if (this.e.k > 1 || !this.e.D.a("timer").c("delay")) {
                    this.k.a(!this.e.l);
                } else {
                    String str = "InterstitialVideoViewProtocol";
                    String str2 = "controls starting %s, setting timer";
                    Object[] objArr = new Object[1];
                    objArr[0] = this.e.l ? "visible" : "hidden";
                    CBLogging.c(str, String.format(str2, objArr));
                    this.k.a(this.e.l);
                    this.e.a(new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            boolean z;
                            String str = "InterstitialVideoViewProtocol";
                            String str2 = "controls %s automatically from timer";
                            Object[] objArr = new Object[1];
                            objArr[0] = this.a.e.l ? "hidden" : "shown";
                            CBLogging.c(str, String.format(str2, objArr));
                            am b = this.a.k;
                            if (this.a.e.l) {
                                z = false;
                            } else {
                                z = true;
                            }
                            b.a(z, true);
                        }
                    }, Math.round(1000.0d * this.e.D.a("timer").g("delay")));
                }
                com.chartboost.sdk.Tracking.a.a(this.e.A, this.e.C, this.e.k);
                this.k.f();
                if (this.e.k <= 1) {
                    this.e.e.d();
                }
            }
        }

        private void b(boolean z) {
            this.k.g();
            com.chartboost.sdk.Tracking.a.d(this.e.A, this.e.C);
            if (this.e.j == b.VIDEO_PLAYING && z) {
                if (this.e.D.c("post-video-reward-toaster") && this.e.k <= 1 && this.e.n && this.e.u.e() && this.e.v.e()) {
                    c(true);
                }
                a(b.POST_VIDEO, true);
            }
        }

        private void c(boolean z) {
            if (z) {
                this.n.a(true);
            } else {
                this.n.setVisibility(0);
            }
            d.a.postDelayed(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.n.a(false);
                }
            }, 2500);
        }

        private void a(b bVar, boolean z) {
            this.e.j = bVar;
            switch (AnonymousClass2.a[bVar.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    this.e.a(!this.e.p(), this.c, z);
                    this.e.a(true, this.l, z);
                    this.e.a(false, this.k, z);
                    this.e.a(false, this.j, z);
                    this.e.a(false, this.m, z);
                    this.c.setEnabled(false);
                    this.j.setEnabled(false);
                    this.k.setEnabled(false);
                    break;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    this.e.a(false, this.c, z);
                    this.e.a(false, this.l, z);
                    this.e.a(true, this.k, z);
                    this.e.a(false, this.j, z);
                    this.e.a(false, this.m, z);
                    this.c.setEnabled(true);
                    this.j.setEnabled(false);
                    this.k.setEnabled(true);
                    break;
                case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                    this.e.a(true, this.c, z);
                    this.e.a(false, this.l, z);
                    this.e.a(false, this.k, z);
                    this.e.a(true, this.j, z);
                    this.e.a(false, this.m, z);
                    this.j.setEnabled(true);
                    this.c.setEnabled(true);
                    this.k.setEnabled(false);
                    if (this.e.y) {
                        c(false);
                        break;
                    }
                    break;
            }
            boolean f = f();
            this.g.setEnabled(f);
            this.e.a(f, this.g, z);
            a();
        }

        protected boolean f() {
            if (this.e.j == b.VIDEO_PLAYING && this.e.k <= 1) {
                float a = this.e.d.a("close-" + (this.e.a().isPortrait() ? "portrait" : "landscape")).a("delay").a(-1.0f);
                int round = a >= 0.0f ? Math.round(a * 1000.0f) : -1;
                this.e.B = round;
                if (round < 0) {
                    return false;
                }
                if (round > this.k.c().getCurrentPosition()) {
                    return false;
                }
            }
            return true;
        }

        public void c() {
            this.e.l();
            super.c();
        }

        protected void g() {
            if (this.e.j == b.VIDEO_PLAYING && this.e.D.a("cancel-popup").c(ShareConstants.WEB_DIALOG_PARAM_TITLE) && this.e.D.a("cancel-popup").c("text") && this.e.D.a("cancel-popup").c("cancel") && this.e.D.a("cancel-popup").c("confirm")) {
                this.k.h();
                if (this.e.k <= 1) {
                    this.e.m();
                    return;
                }
            }
            if (this.e.j == b.VIDEO_PLAYING) {
                b(false);
                this.k.i();
            }
            d.a.post(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.e.h();
                }
            });
            com.chartboost.sdk.Tracking.a.b(this.e.A, this.e.C, this.e.B);
        }

        protected void a(float f, float f2) {
            if ((!this.e.l || this.e.j != b.VIDEO_PLAYING) && this.e.j != b.REWARD_OFFER) {
                h();
                com.chartboost.sdk.Tracking.a.a("insterstitial", this.e.A, this.e.e.m(), (int) f, (int) f2);
            }
        }

        protected void h() {
            if (this.e.j == b.VIDEO_PLAYING) {
                b(false);
            }
            this.e.a(null, null);
        }

        protected void i() {
            com.chartboost.sdk.Tracking.a.c("integrated", this.e.C, true);
            this.e.m = false;
            a(true);
        }

        public void a() {
            super.a();
            this.l.a();
            this.k.a();
            this.m.a();
        }

        public void j() {
            this.e.a(CBImpressionError.INTERNAL);
        }
    }

    protected enum b {
        REWARD_OFFER,
        VIDEO_PLAYING,
        POST_VIDEO
    }

    public /* synthetic */ com.chartboost.sdk.d.a e() {
        return o();
    }

    public ae(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.j = b.REWARD_OFFER;
        this.F = true;
        this.G = false;
        this.H = false;
        this.x = 0;
        this.y = false;
        this.B = 0;
        this.j = b.REWARD_OFFER;
        this.p = new i(this);
        this.q = new i(this);
        this.r = new i(this);
        this.s = new i(this);
        this.t = new i(this);
        this.u = new i(this);
        this.v = new i(this);
        this.w = new i(this);
        this.k = 0;
    }

    public void m() {
        com.chartboost.sdk.impl.bi.a aVar = new com.chartboost.sdk.impl.bi.a();
        aVar.a(this.D.a("cancel-popup").e(ShareConstants.WEB_DIALOG_PARAM_TITLE)).b(this.D.a("cancel-popup").e("text")).d(this.D.a("cancel-popup").e("confirm")).c(this.D.a("cancel-popup").e("cancel"));
        aVar.a(o().getContext(), new com.chartboost.sdk.impl.bi.b(this) {
            final /* synthetic */ ae a;

            {
                this.a = r1;
            }

            public void a(bi biVar) {
                a o = this.a.o();
                if (o != null) {
                    o.k.f();
                }
            }

            public void a(bi biVar, int i) {
                a o = this.a.o();
                if (i != 1) {
                    if (o != null) {
                        o.b(false);
                        o.k.i();
                    }
                    this.a.h();
                } else if (o != null) {
                    o.k.f();
                }
            }
        });
    }

    protected com.chartboost.sdk.d.a a(Context context) {
        return new a(context);
    }

    public boolean j() {
        o().g();
        return true;
    }

    public void k() {
        super.k();
        if (this.j == b.VIDEO_PLAYING && this.G) {
            CBLogging.e("InterstitialVideoViewProtocol", "Video onResume");
            o().k.c().seekTo(this.x);
            if (!this.H) {
                o().k.f();
            }
        }
        this.H = false;
        this.G = false;
    }

    public void l() {
        super.l();
        if (this.j == b.VIDEO_PLAYING && !this.G) {
            CBLogging.e("InterstitialVideoViewProtocol", "Video onPause");
            if (!o().k.j()) {
                this.H = true;
            }
            this.G = true;
            o().k.h();
        }
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        if (this.d.b("video-landscape") || this.d.b("replay-landscape")) {
            this.i = false;
        }
        this.p.a("replay-landscape");
        this.q.a("replay-portrait");
        this.t.a("video-click-button");
        this.u.a("post-video-reward-icon");
        this.v.a("post-video-button");
        this.r.a("video-confirmation-button");
        this.s.a("video-confirmation-icon");
        this.w.a("post-video-reward-icon");
        this.l = aVar.a("ux").i("video-controls-togglable");
        if (this.D.a("post-video-toaster").c(ShareConstants.WEB_DIALOG_PARAM_TITLE) && this.D.a("post-video-toaster").c("tagline")) {
            this.n = true;
        }
        if (this.D.a("confirmation").c("text") && this.D.a("confirmation").c("color")) {
            this.m = true;
        }
        if (this.D.c("post-video-reward-toaster")) {
            this.o = true;
        }
        return true;
    }

    protected void i() {
        if (this.m && !(this.r.e() && this.s.e())) {
            this.m = false;
        }
        if (this.F) {
            super.i();
        } else {
            a(CBImpressionError.INTERNAL);
        }
    }

    public void d() {
        super.d();
        this.p.d();
        this.q.d();
        this.t.d();
        this.u.d();
        this.v.d();
        this.r.d();
        this.s.d();
        this.w.d();
        this.p = null;
        this.q = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.r = null;
        this.s = null;
        this.w = null;
    }

    public boolean n() {
        return this.j == b.VIDEO_PLAYING;
    }

    public a o() {
        return (a) super.e();
    }

    protected boolean p() {
        boolean isPortrait = CBPreferences.getInstance().getOrientation().isPortrait();
        DisplayMetrics displayMetrics = Chartboost.sharedChartboost().getContext().getResources().getDisplayMetrics();
        boolean z;
        if (((float) displayMetrics.widthPixels) / displayMetrics.density < 610.0f) {
            z = true;
        } else {
            z = false;
        }
        if (!isPortrait || !r2 || this.h || this.j == b.POST_VIDEO) {
            return false;
        }
        return true;
    }
}
