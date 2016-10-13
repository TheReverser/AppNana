package com.vungle.publisher.display.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings.System;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.appnana.android.giftcardrewards.model.Settings;
import com.facebook.BuildConfig;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.view.ViewHelper;
import com.vungle.log.Logger;
import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.audio.VolumeChangeContentObserver;
import com.vungle.publisher.bc;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.h;
import com.vungle.publisher.image.BitmapFactory;
import com.vungle.publisher.k;
import com.vungle.publisher.l;
import com.vungle.publisher.reporting.AdReportEventListener;
import com.vungle.publisher.u;
import com.vungle.publisher.y;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class VideoFragment extends AdFragment implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private Bitmap A;
    private Bitmap B;
    private final TypeEvaluator<?> C = new ArgbEvaluator();
    private final Handler D = new Handler();
    private final Runnable E = new c(this);
    private VolumeChangeContentObserver F;
    private AlertDialog G;
    private String H;
    private int I;
    private boolean J;
    private boolean K;
    private int L;
    private boolean M;
    private AtomicBoolean N = new AtomicBoolean();
    private int O;
    com.vungle.publisher.a a;
    AdReportEventListener b;
    a c;
    Video<?, ?, ?> d;
    ImageView e;
    ObjectAnimator f;
    ObjectAnimator g;
    TouchDelegate h;
    final AtomicBoolean i = new AtomicBoolean();
    ObjectAnimator j;
    @Inject
    AlertDialogFactory k;
    @Inject
    AudioManager l;
    @Inject
    BitmapFactory m;
    @Inject
    Factory n;
    @Inject
    DisplayUtils o;
    @Inject
    EventBus p;
    @Inject
    com.vungle.publisher.audio.VolumeChangeContentObserver.Factory q;
    @Inject
    Factory r;
    private ImageView s;
    private CountdownProgressView t;
    private ImageView u;
    private ViewGroup v;
    private VideoView w;
    private ViewGroup x;
    private Bitmap y;
    private Bitmap z;

    /* compiled from: vungle */
    public interface a {
        void a();

        void b();

        void c();
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<VideoFragment> a;

        Factory() {
        }

        public static VideoFragment a(VideoFragment videoFragment, Ad<?, ?, ?> ad, com.vungle.publisher.a aVar, AdReportEventListener adReportEventListener, a aVar2) {
            String f = ad.f();
            Video k = ad.k();
            if (k == null) {
                return null;
            }
            videoFragment.a = aVar;
            videoFragment.d = k;
            videoFragment.H = f;
            videoFragment.c = aVar2;
            videoFragment.b = adReportEventListener;
            return videoFragment;
        }

        public static void a(VideoFragment videoFragment, Bundle bundle) {
            if (bundle != null) {
                videoFragment.M = bundle.getBoolean("adStarted");
            }
        }
    }

    /* compiled from: vungle */
    class b implements OnClickListener {
        final /* synthetic */ VideoFragment a;

        b(VideoFragment videoFragment) {
            this.a = videoFragment;
        }

        public final void onClick(View view) {
            Logger.v(Logger.AD_TAG, "close clicked");
            this.a.j(false);
        }
    }

    /* compiled from: vungle */
    class c implements Runnable {
        final /* synthetic */ VideoFragment a;

        c(VideoFragment videoFragment) {
            this.a = videoFragment;
        }

        public final void run() {
            try {
                int c = this.a.c(false);
                this.a.c(c);
                this.a.t.setProgress(c / 1000);
                this.a.p.a(new u(c));
            } catch (Throwable e) {
                Logger.w(Logger.AD_TAG, e);
            } finally {
                this.a.D.postDelayed(this, 50);
            }
        }
    }

    /* compiled from: vungle */
    class d extends Handler {
        final /* synthetic */ VideoFragment a;

        d(VideoFragment videoFragment) {
            this.a = videoFragment;
        }

        public final void handleMessage(Message message) {
            Object obj = 1;
            try {
                VideoFragment videoFragment = this.a;
                int i = message.arg1;
                int i2 = message.arg2;
                if (i2 != 0) {
                    obj = null;
                }
                boolean compareAndSet = videoFragment.i.compareAndSet(true, false);
                if (obj != null || compareAndSet) {
                    Logger.d(Logger.AD_TAG, "volume change " + (compareAndSet ? "un" : BuildConfig.VERSION_NAME) + "mute");
                    videoFragment.d();
                    videoFragment.e(compareAndSet);
                }
                float a = videoFragment.a((float) i2);
                if (i2 < i) {
                    videoFragment.b.a(com.vungle.publisher.db.model.AdReportEvent.a.volumedown, Float.valueOf(a));
                } else {
                    videoFragment.b.a(com.vungle.publisher.db.model.AdReportEvent.a.volumeup, Float.valueOf(a));
                }
            } catch (Throwable e) {
                Logger.w(Logger.AD_TAG, "error handling volume change: " + (message == null ? "null message" : message.arg1 + " --> " + message.arg2), e);
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            com.vungle.publisher.audio.VolumeChangeContentObserver.Factory factory = this.q;
            this.F = new VolumeChangeContentObserver(factory.a, new d(this));
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onCreate", e);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View relativeLayout = new RelativeLayout(getActivity());
        this.x = relativeLayout;
        return relativeLayout;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        try {
            super.onActivityCreated(savedInstanceState);
            Factory.a(this, savedInstanceState);
            if (this.M) {
                this.b.a(com.vungle.publisher.db.model.AdReportEvent.a.videoreset, null);
            }
            FragmentActivity activity = getActivity();
            View videoView = new VideoView(activity);
            this.w = videoView;
            View bcVar = new bc(activity);
            this.s = bcVar;
            View countdownProgressView = new CountdownProgressView(activity, this.n.a);
            this.t = countdownProgressView;
            View imageView = new ImageView(activity);
            this.u = imageView;
            this.x.addView(videoView);
            LayoutParams layoutParams = (LayoutParams) videoView.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = -1;
            layoutParams.addRule(13);
            View relativeLayout = new RelativeLayout(activity);
            this.v = relativeLayout;
            this.x.addView(relativeLayout);
            layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.addRule(10);
            relativeLayout.addView(bcVar);
            layoutParams = (LayoutParams) bcVar.getLayoutParams();
            layoutParams.addRule(9);
            layoutParams.addRule(15);
            ViewHelper.setAlpha(bcVar, 0.0f);
            View relativeLayout2 = new RelativeLayout(activity);
            this.x.addView(relativeLayout2);
            layoutParams = (LayoutParams) relativeLayout2.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.addRule(12);
            relativeLayout2.addView(countdownProgressView);
            layoutParams = (LayoutParams) countdownProgressView.getLayoutParams();
            layoutParams.addRule(9);
            layoutParams.addRule(15);
            ViewHelper.setAlpha(countdownProgressView, 0.0f);
            relativeLayout2.addView(imageView);
            layoutParams = (LayoutParams) imageView.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            int round = Math.round(this.o.a(2));
            int round2 = Math.round(this.o.a(1));
            relativeLayout.setPadding(round, round2, round, round2);
            relativeLayout2.setPadding(round, round2, round, round2);
            this.A = a("vg_mute_on.png");
            this.B = a("vg_mute_off.png");
            this.s.setScaleType(ScaleType.FIT_CENTER);
            this.u.setScaleType(ScaleType.FIT_CENTER);
            Bitmap a = a("vg_close.png");
            if (a != null) {
                bcVar.setImageBitmap(a);
            }
            Logger.i(Logger.AD_TAG, "video play URI = " + this.d.i());
            videoView.setVideoURI(this.d.i());
            this.f = a(relativeLayout2);
            this.j = a(relativeLayout);
            if (Boolean.TRUE.equals(this.d.h)) {
                this.y = a("vg_cta.png");
                this.z = a("vg_cta_disabled.png");
                Integer num = this.d.f;
                Integer num2 = this.d.j;
                Integer num3;
                if (num == null) {
                    if (num2 != null) {
                        Logger.v(Logger.AD_TAG, "overriding cta enabled from null to " + num2);
                        num = num2;
                    }
                    num3 = num2;
                    num2 = num;
                    num = num3;
                } else if (num2 == null) {
                    Logger.v(Logger.AD_TAG, "overriding cta shown from null to " + num);
                    num2 = num;
                } else {
                    if (num2.intValue() > num.intValue()) {
                        Logger.v(Logger.AD_TAG, "overriding cta shown from " + num2 + " to " + num);
                        num2 = num;
                    }
                    num3 = num2;
                    num2 = num;
                    num = num3;
                }
                Logger.d(Logger.AD_TAG, "cta shown at " + num + " seconds; enabled at " + num2 + " seconds");
                if (num2 == null) {
                    round = 0;
                } else {
                    round = num2.intValue();
                }
                this.I = round;
                if (num == null) {
                    round = 0;
                } else {
                    round = num.intValue();
                }
                this.L = round;
                final View bcVar2 = new bc(getActivity());
                this.e = bcVar2;
                this.v.addView(bcVar2);
                layoutParams = (LayoutParams) bcVar2.getLayoutParams();
                layoutParams.addRule(11);
                layoutParams.addRule(15);
                bcVar2.setScaleType(ScaleType.FIT_CENTER);
                final Float f = this.d.e;
                if (f == null || f.floatValue() <= 1.0f) {
                    Logger.v(Logger.AD_TAG, "cta clickable area not scaled");
                } else {
                    bcVar2.post(new Runnable(this) {
                        final /* synthetic */ VideoFragment c;

                        public final void run() {
                            float sqrt = (float) Math.sqrt((double) f.floatValue());
                            int height = bcVar2.getHeight();
                            int width = bcVar2.getWidth();
                            int round = Math.round(((float) height) * sqrt);
                            int round2 = Math.round(sqrt * ((float) width));
                            Logger.v(Logger.AD_TAG, "scaling cta clickable area " + f + "x - width: " + width + " --> " + round2 + ", height: " + height + " --> " + round);
                            Rect rect = new Rect();
                            bcVar2.getHitRect(rect);
                            rect.bottom = rect.top + round;
                            rect.left = rect.right - round2;
                            this.c.h = new TouchDelegate(rect, bcVar2);
                        }
                    });
                }
                if (Boolean.TRUE.equals(this.d.i)) {
                    ViewHelper.setAlpha(bcVar2, 0.0f);
                    bcVar2.setImageBitmap(this.y);
                    this.g = ObjectAnimator.ofFloat(bcVar2, "alpha", new float[]{0.0f, 1.0f});
                    this.g.setDuration(750);
                } else {
                    h(this.L >= this.I);
                }
                bcVar2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ VideoFragment b;

                    public final void onClick(View view) {
                        if (this.b.J) {
                            Logger.d(Logger.AD_TAG, "cta overlay onClick");
                            bcVar2.setOnClickListener(null);
                            this.b.b(false);
                            this.b.c.b();
                            return;
                        }
                        Logger.v(Logger.AD_TAG, "cta overlay onClick, but not enabled");
                    }
                });
            }
            bcVar.setOnClickListener(new b(this));
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VideoFragment a;

                {
                    this.a = r1;
                }

                public final void onClick(View view) {
                    boolean z;
                    Logger.d(Logger.AD_TAG, (this.a.c() ? BuildConfig.VERSION_NAME : "un") + "mute clicked");
                    VideoFragment videoFragment = this.a;
                    if (videoFragment.c()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    videoFragment.d(z);
                    if (z && videoFragment.f() == 0) {
                        videoFragment.l.setStreamVolume(3, (int) (0.4f * ((float) videoFragment.e())), 0);
                        videoFragment.d();
                    }
                    videoFragment.e(z);
                }
            });
            this.K = this.a.isSoundEnabled();
            videoView.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ VideoFragment a;

                {
                    this.a = r1;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean z = false;
                    Logger.v(Logger.AD_TAG, "video onTouch");
                    if (this.a.h != null) {
                        this.a.h.onTouchEvent(motionEvent);
                    }
                    VideoFragment videoFragment = this.a;
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    String str;
                    ObjectAnimator objectAnimator = videoFragment.j;
                    if (!(objectAnimator.getDuration() == 749)) {
                        z = true;
                    }
                    String str2 = Logger.AD_TAG;
                    StringBuilder stringBuilder = new StringBuilder("toggle bar animation");
                    if (objectAnimator.isRunning()) {
                        str = " - reversing " + (z ? "hide" : "show") + " animation";
                    } else {
                        str = BuildConfig.VERSION_NAME;
                    }
                    Logger.v(str2, stringBuilder.append(str).toString());
                    objectAnimator.cancel();
                    float animatedFraction = 1.0f - objectAnimator.getAnimatedFraction();
                    if (z) {
                        videoFragment.a(videoFragment.f, animatedFraction);
                        videoFragment.a(videoFragment.j, animatedFraction);
                    } else {
                        videoFragment.b(750);
                    }
                    if (videoFragment.e == null || ViewHelper.getAlpha(videoFragment.e) != 0.0f || videoFragment.g == null || videoFragment.g.isRunning()) {
                        return true;
                    }
                    videoFragment.g.start();
                    return true;
                }
            });
            videoView.setOnCompletionListener(this);
            videoView.setOnErrorListener(this);
            videoView.setOnPreparedListener(this);
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onActivityCreated", e);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
            outState.putString(FullScreenAdActivity.AD_ID_EXTRA_KEY, this.d.d());
            outState.putParcelable(FullScreenAdActivity.AD_CONFIG_EXTRA_KEY, (Parcelable) this.a);
            outState.putBoolean("adStarted", this.M);
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onSaveInstanceState", e);
        }
    }

    private Bitmap a(String str) {
        Bitmap bitmap = null;
        try {
            bitmap = this.m.getBitmap(str);
        } catch (IOException e) {
            Logger.w(Logger.AD_TAG, "error loading " + str);
        }
        return bitmap;
    }

    private static ObjectAnimator a(View view) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("backgroundColor");
        objectAnimator.setTarget(view);
        return objectAnimator;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        int duration = mediaPlayer.getDuration();
        Logger.d(Logger.AD_TAG, "video ready: duration " + duration + " ms");
        this.t.setMax(Math.round(((float) duration) / 1000.0f));
        this.p.a(new k(duration));
        h();
    }

    public void onResume() {
        try {
            super.onResume();
            Logger.d(Logger.AD_TAG, "video onResume");
            f(this.K);
            this.b.a(com.vungle.publisher.db.model.AdReportEvent.a.volume, Float.valueOf(a((float) f())));
            getActivity().getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.F);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, e);
            g();
        }
    }

    public void onPause() {
        Logger.d(Logger.AD_TAG, "video onPause");
        try {
            super.onPause();
            i();
            getActivity().getContentResolver().unregisterContentObserver(this.F);
            g(true);
            if (this.M) {
                this.p.a(new u(this.w.getCurrentPosition()));
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, e);
            g();
        }
    }

    public final void a(boolean z) {
        try {
            super.a(z);
            if (z) {
                h();
            } else {
                i();
            }
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onWindowFocusChanged", e);
        }
    }

    private void g() {
        this.D.removeCallbacks(this.E);
    }

    private void h() {
        boolean z = true;
        boolean z2 = !this.M;
        this.M = true;
        if (this.G == null || !this.G.isShowing()) {
            z = false;
        }
        if (!z) {
            c(this.w.getCurrentPosition());
            this.w.requestFocus();
            this.w.start();
            b((int) Settings.MIN_NANAS_TO_ALERT_RATING);
            this.D.post(this.E);
            if (z2) {
                this.p.a(new y());
            }
        }
    }

    private void i() {
        this.w.pause();
        g();
    }

    final void b(boolean z) {
        g();
        int c = c(z);
        this.p.a(z ? new h(c) : new l(c));
        this.M = false;
        this.O = 0;
        this.N.set(false);
    }

    final int c(boolean z) {
        int duration = z ? this.w.getDuration() : this.w.getCurrentPosition();
        int i = this.O;
        if (duration > i) {
            this.O = duration;
            return duration;
        }
        if (duration < i) {
            Logger.w(Logger.AD_TAG, "watched millis decreased from " + i + " --> " + duration);
        }
        return i;
    }

    final void b(int i) {
        a(this.f, i);
        a(this.j, i);
    }

    private void a(ObjectAnimator objectAnimator, int i) {
        a(objectAnimator, 1140850688, 0, i, 0.0f);
    }

    final void a(ObjectAnimator objectAnimator, float f) {
        a(objectAnimator, 0, 1140850688, 749, f);
    }

    private void a(ObjectAnimator objectAnimator, int i, int i2, int i3, float f) {
        Logger.v(Logger.AD_TAG, "animateBar startColor: " + i + ", endColor: " + i2 + ", durationMillis: " + i3 + ", startPercent: " + f);
        objectAnimator.setDuration((long) i3);
        objectAnimator.setIntValues(new int[]{i, i2});
        objectAnimator.setCurrentPlayTime((long) Math.round(((float) i3) * f));
        objectAnimator.setEvaluator(this.C);
        objectAnimator.start();
    }

    final boolean c() {
        return this.K && f() > 0;
    }

    final void d(boolean z) {
        this.K = z;
        f(z);
    }

    private void f(boolean z) {
        g(z);
        d();
    }

    private void g(boolean z) {
        this.l.setStreamMute(3, !z);
    }

    final void d() {
        this.u.setImageBitmap(c() ? this.B : this.A);
    }

    final int e() {
        return this.l.getStreamMaxVolume(3);
    }

    final int f() {
        return this.l.getStreamVolume(3);
    }

    final float a(float f) {
        int e = e();
        return e == 0 ? -1.0f : f / ((float) e);
    }

    final void e(boolean z) {
        if (z) {
            AdReportEventListener adReportEventListener = this.b;
            adReportEventListener.a(com.vungle.publisher.db.model.AdReportEvent.a.unmuted, null);
            adReportEventListener.a(com.vungle.publisher.db.model.EventTracking.a.unmute);
            return;
        }
        adReportEventListener = this.b;
        adReportEventListener.a(com.vungle.publisher.db.model.AdReportEvent.a.muted, null);
        adReportEventListener.a(com.vungle.publisher.db.model.EventTracking.a.mute);
    }

    public final void a() {
        Logger.v(Logger.AD_TAG, "back button pressed");
        j(true);
    }

    private boolean j() {
        return ViewHelper.getAlpha(this.s) == 1.0f;
    }

    public final boolean a(int i) {
        if (i == 24) {
            if (f() == 0) {
                Logger.d(Logger.AD_TAG, "volume up - pending unmute");
                d(true);
                this.i.set(true);
            } else {
                Logger.v(Logger.AD_TAG, "volume up");
            }
        }
        return false;
    }

    final void c(int i) {
        boolean z = true;
        if (Boolean.TRUE.equals(this.d.h)) {
            if (Boolean.TRUE.equals(this.d.i)) {
                if (ViewHelper.getAlpha(this.e) < 1.0f) {
                    z = false;
                }
                i(z);
            } else {
                a(this.e, this.L, i);
                if (i < this.I * 1000) {
                    z = false;
                }
                i(z);
            }
        }
        Integer num = this.a.isIncentivized() ? this.d.k : this.d.l;
        if (num != null) {
            a(this.s, num.intValue(), i);
        }
        num = this.d.m;
        if (num != null) {
            a(this.t, num.intValue(), i);
        }
    }

    private void h(boolean z) {
        boolean z2 = z && this.H != null;
        Logger.v(Logger.AD_TAG, "cta button " + (z2 ? "enabled" : "disabled"));
        this.J = z2;
        this.e.setImageBitmap(z2 ? this.y : this.z);
    }

    private void i(boolean z) {
        if (z != this.J) {
            h(z);
        }
    }

    private static void a(View view, int i, int i2) {
        float alpha = ViewHelper.getAlpha(view);
        int i3 = i * 1000;
        float f = 0.0f;
        int i4 = i3 - 750;
        if (i2 > i4) {
            f = i2 >= i3 ? 1.0f : ((float) (i2 - i4)) / ((float) (i3 - i4));
        }
        if (f != alpha) {
            ViewHelper.setAlpha(view, f);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Logger.d(Logger.AD_TAG, "video.onCompletion");
        b(true);
        this.c.c();
    }

    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        Logger.e(Logger.AD_TAG, "video.onError: " + what + ", " + extra);
        b(false);
        this.c.c();
        return true;
    }

    private void k() {
        b(false);
        this.w.stopPlayback();
        AdReportEventListener adReportEventListener = this.b;
        adReportEventListener.a(com.vungle.publisher.db.model.EventTracking.a.video_close);
        adReportEventListener.a(com.vungle.publisher.db.model.AdReportEvent.a.close, null);
        this.c.a();
    }

    private void j(boolean z) {
        if (z) {
            boolean z2 = j() || this.a.isBackButtonImmediatelyEnabled();
            if (!z2) {
                return;
            }
        } else if (!j()) {
            return;
        }
        if (this.N.compareAndSet(false, true)) {
            Logger.d(Logger.AD_TAG, "exiting video");
            if (this.a.isIncentivized()) {
                AlertDialog alertDialog;
                this.w.pause();
                if (this.G != null) {
                    alertDialog = this.G;
                } else {
                    AlertDialogFactory alertDialogFactory = this.k;
                    FragmentActivity activity = getActivity();
                    com.vungle.publisher.a aVar = this.a;
                    com.vungle.publisher.display.view.AlertDialogFactory.a anonymousClass5 = new com.vungle.publisher.display.view.AlertDialogFactory.a(this) {
                        final /* synthetic */ VideoFragment a;

                        {
                            this.a = r1;
                        }

                        public final void a() {
                            d();
                        }

                        public final void b() {
                            Logger.d(Logger.AD_TAG, "cancel video");
                            this.a.k();
                        }

                        public final void c() {
                            d();
                        }

                        private void d() {
                            this.a.w.start();
                            this.a.N.set(false);
                        }
                    };
                    Builder builder = new Builder(activity);
                    builder.setTitle(aVar.getIncentivizedCancelDialogTitle());
                    builder.setMessage(aVar.getIncentivizedCancelDialogBodyText());
                    builder.setPositiveButton(aVar.getIncentivizedCancelDialogKeepWatchingButtonText(), new com.vungle.publisher.display.view.AlertDialogFactory.AnonymousClass1(alertDialogFactory, anonymousClass5));
                    builder.setNegativeButton(aVar.getIncentivizedCancelDialogCloseButtonText(), new com.vungle.publisher.display.view.AlertDialogFactory.AnonymousClass2(alertDialogFactory, anonymousClass5));
                    builder.setOnCancelListener(new com.vungle.publisher.display.view.AlertDialogFactory.AnonymousClass3(alertDialogFactory, anonymousClass5));
                    alertDialog = builder.create();
                }
                this.G = alertDialog;
                alertDialog.show();
                return;
            }
            this.s.setOnClickListener(null);
            k();
        }
    }

    public final String b() {
        return "videoFragment";
    }
}
