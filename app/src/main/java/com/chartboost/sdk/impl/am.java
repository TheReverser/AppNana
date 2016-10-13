package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.impl.bn.a;

public final class am extends RelativeLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener, a {
    private static Handler k = new Handler();
    private RelativeLayout a;
    private ak b;
    private ak c;
    private bh d;
    private TextView e;
    private ah f;
    private al g;
    private ae h;
    private boolean i = false;
    private boolean j = false;
    private Runnable l = new Runnable(this) {
        final /* synthetic */ am a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d(false);
        }
    };
    private Runnable m = new Runnable(this) {
        final /* synthetic */ am a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b.setVisibility(8);
            this.a.f.setVisibility(8);
            this.a.c.setVisibility(8);
            this.a.d.setEnabled(false);
        }
    };
    private Runnable n = new Runnable(this) {
        final /* synthetic */ am a;
        private int b = 0;

        {
            this.a = r2;
        }

        public void run() {
            if (this.a.g.isPlaying()) {
                int currentPosition = this.a.g.getCurrentPosition();
                if (currentPosition > 0) {
                    this.a.h.x = currentPosition;
                }
                this.a.f.a(((float) currentPosition) / ((float) this.a.g.getDuration()));
                currentPosition /= 1000;
                if (this.b != currentPosition) {
                    this.b = currentPosition;
                    int i = currentPosition / 60;
                    currentPosition %= 60;
                    this.a.e.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(currentPosition)}));
                }
            }
            if (this.a.h.o().f() && this.a.h.o().g.getVisibility() == 8) {
                this.a.h.a(true, this.a.h.o().g);
            }
            am.k.removeCallbacks(this.a.n);
            am.k.postDelayed(this.a.n, 16);
        }
    };

    public am(Context context, ae aeVar) {
        super(context);
        this.h = aeVar;
        a(context);
    }

    private void a(Context context) {
        Context context2 = getContext();
        float f = getContext().getResources().getDisplayMetrics().density;
        int round = Math.round(f * 10.0f);
        this.g = new al(context2);
        this.h.o().a(this.g);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        addView(this.g, layoutParams);
        this.a = new RelativeLayout(context2);
        this.b = new ak(context2);
        this.b.setVisibility(8);
        this.b.setGravity(21);
        i iVar = this.h.t;
        Point b = this.h.b("video-click-button");
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, Math.round((((float) iVar.c()) / iVar.g()) + (6.0f * f)));
        layoutParams2.addRule(10);
        this.a.addView(this.b, layoutParams2);
        this.d = new bh(this, context2) {
            final /* synthetic */ am a;

            protected void a(MotionEvent motionEvent) {
                this.a.h.a(null, e.a(e.a("paused", Integer.valueOf(1))));
                com.chartboost.sdk.Tracking.a.a("install-button", this.a.h.A, this.a.h.C, Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
            }
        };
        layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = Math.round(((float) b.x) / iVar.g());
        layoutParams2.topMargin = Math.round(((float) b.y) / iVar.g());
        this.h.a(layoutParams2, iVar, 1.0f);
        this.b.addView(this.d, layoutParams2);
        this.d.a(iVar);
        this.c = new ak(context2);
        this.c.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(32.5f * f));
        layoutParams.addRule(12);
        this.a.addView(this.c, layoutParams);
        this.c.setGravity(16);
        this.c.setPadding(round, round, round, round);
        this.e = new TextView(context2);
        this.e.setTextColor(-1);
        this.e.setTextSize(2, 11.0f);
        this.e.setText("00:00");
        this.e.setGravity(17);
        this.e.setPadding(0, 0, 16, 0);
        this.e.setSingleLine();
        this.c.addView(this.e, new LinearLayout.LayoutParams(Math.round(48.0f * f), -1));
        this.f = new ah(context2);
        this.f.setVisibility(8);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, Math.round(f * 10.0f));
        layoutParams3.setMargins(round, round, round, round);
        this.c.addView(this.f, layoutParams3);
        layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.addRule(6, this.g.getId());
        layoutParams3.addRule(8, this.g.getId());
        layoutParams3.addRule(5, this.g.getId());
        layoutParams3.addRule(7, this.g.getId());
        addView(this.a, layoutParams3);
        a();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.d.setEnabled(enabled);
        if (enabled) {
            a(false);
        }
    }

    public void a(MediaPlayer mediaPlayer) {
        this.g.a(mediaPlayer);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k.removeCallbacks(this.n);
    }

    public boolean onTouchEvent(MotionEvent e) {
        if (!this.g.isPlaying() || e.getActionMasked() != 0) {
            return false;
        }
        if (this.h != null) {
            com.chartboost.sdk.Tracking.a.d(this.h.A, this.h.C, this.i);
        }
        d(true);
        return true;
    }

    public void onCompletion(MediaPlayer arg0) {
        this.h.x = this.g.getDuration();
        if (this.h.o() != null) {
            this.h.o().e();
        }
    }

    public void onPrepared(MediaPlayer mp) {
        a(mp);
        this.h.o().a();
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        CBLogging.b("VideoError", String.format("Video onError what extra: %d %d", new Object[]{Integer.valueOf(what), Integer.valueOf(extra)}));
        this.h.o().j();
        return false;
    }

    private void d(boolean z) {
        a(!this.i, z);
    }

    protected void a(boolean z, boolean z2) {
        k.removeCallbacks(this.l);
        k.removeCallbacks(this.m);
        if (this.h.l && this.h.n() && z != this.i) {
            this.i = z;
            Animation alphaAnimation = this.i ? new AlphaAnimation(0.0f, 1.0f) : new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(100 * ((long) (z2 ? 1 : 2)));
            alphaAnimation.setFillAfter(true);
            if (!this.j) {
                this.b.setVisibility(0);
                this.b.startAnimation(alphaAnimation);
                this.d.setEnabled(true);
            }
            this.f.setVisibility(0);
            this.c.setVisibility(0);
            this.c.startAnimation(alphaAnimation);
            if (this.i) {
                k.postDelayed(this.l, 3000);
            } else {
                k.postDelayed(this.m, alphaAnimation.getDuration());
            }
        }
    }

    public void a(boolean z) {
        k.removeCallbacks(this.l);
        k.removeCallbacks(this.m);
        if (z) {
            if (!this.j) {
                this.b.setVisibility(0);
            }
            this.f.setVisibility(0);
            this.c.setVisibility(0);
            this.d.setEnabled(true);
        } else {
            this.b.clearAnimation();
            this.c.clearAnimation();
            this.b.setVisibility(8);
            this.f.setVisibility(8);
            this.c.setVisibility(8);
            this.d.setEnabled(false);
        }
        this.i = z;
    }

    public void b(boolean z) {
        setBackgroundColor(z ? -16777216 : 0);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (!z) {
            layoutParams.addRule(6, this.g.getId());
            layoutParams.addRule(8, this.g.getId());
            layoutParams.addRule(5, this.g.getId());
            layoutParams.addRule(7, this.g.getId());
        }
        this.a.setLayoutParams(layoutParams);
    }

    public void a() {
        b(CBPreferences.getInstance().getOrientation().isPortrait());
        requestLayout();
    }

    public View b() {
        return this;
    }

    public al c() {
        return this.g;
    }

    public ah d() {
        return this.f;
    }

    public void a(int i) {
        this.b.setBackgroundColor(i);
        this.c.setBackgroundColor(i);
    }

    public void e() {
        this.b.setVisibility(8);
        this.j = true;
        this.d.setEnabled(false);
    }

    public void c(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void a(String str) {
        this.g.setOnCompletionListener(this);
        this.g.setOnErrorListener(this);
        this.g.setOnPreparedListener(this);
        this.g.setVideoURI(Uri.parse(str));
    }

    public void f() {
        CBLogging.e(this, String.format("starting video ad playback, 0s / %d", new Object[]{Integer.valueOf(this.g.getDuration())}));
        this.g.start();
        k.removeCallbacks(this.n);
        k.postDelayed(this.n, 16);
    }

    public void g() {
        CBLogging.e(this, String.format("stopping video ad playback, %d / %d", new Object[]{Integer.valueOf(this.g.getCurrentPosition()), Integer.valueOf(this.g.getDuration())}));
        if (this.g.isPlaying()) {
            this.h.x = this.g.getCurrentPosition();
            this.g.pause();
        }
        this.h.o().c.postInvalidate();
        k.removeCallbacks(this.n);
    }

    public void h() {
        if (this.g.isPlaying()) {
            this.h.x = this.g.getCurrentPosition();
        }
        this.g.pause();
        k.removeCallbacks(this.n);
    }

    public void i() {
        this.g.setVisibility(8);
        invalidate();
    }

    public boolean j() {
        return this.g.isPlaying();
    }
}
