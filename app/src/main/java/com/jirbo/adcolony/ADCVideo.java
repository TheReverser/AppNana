package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import com.facebook.BuildConfig;
import com.immersion.hapticmediasdk.HapticContentSDK;
import com.immersion.hapticmediasdk.HapticContentSDKFactory;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public abstract class ADCVideo extends Activity {
    static int a;
    static int b;
    static int c;
    static boolean d;
    static boolean e;
    static boolean f;
    static boolean g;
    static Activity h;
    static boolean i;
    static boolean j;
    String A = BuildConfig.VERSION_NAME;
    String B = BuildConfig.VERSION_NAME;
    boolean C = true;
    boolean D = true;
    e E;
    ae F;
    AdColonyAd G;
    HapticContentSDK H;
    String I;
    boolean J;
    boolean K = true;
    String L = "Your purchase will begin shortly!";
    VideoView M;
    FrameLayout N;
    FrameLayout O;
    FrameLayout P;
    Rect Q = new Rect();
    ADCImage R;
    a S;
    FileInputStream T;
    boolean k;
    boolean l;
    boolean m;
    boolean n;
    boolean o;
    double p;
    double q;
    long r;
    long s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int y;
    int z;

    class a extends View {
        Rect a = new Rect();
        final /* synthetic */ ADCVideo b;

        public a(ADCVideo aDCVideo, Activity activity) {
            this.b = aDCVideo;
            super(activity);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawARGB(255, 0, 0, 0);
            getDrawingRect(this.a);
            this.b.R.a(canvas, (this.a.width() - this.b.R.f) / 2, (this.a.height() - this.b.R.g) / 2);
            invalidate();
        }
    }

    static void a() {
        a = 0;
        d = false;
        e = false;
        g = false;
    }

    public void onCreate(Bundle savedInstanceState) {
        boolean z;
        int i;
        int i2 = 1;
        a.aa = false;
        super.onCreate(savedInstanceState);
        this.J = a.i("haptics_enabled");
        this.I = a.j("haptics_filepath");
        this.L = a.j("in_progress");
        this.B = a.j("video_filepath");
        if (this.J) {
            try {
                this.H = HapticContentSDKFactory.GetNewSDKInstance(0, this);
                this.H.openHaptics(this.I);
            } catch (Exception e) {
                e.printStackTrace();
                this.J = false;
            }
            if (this.H == null) {
                this.J = false;
            }
        }
        h = this;
        if (a.i("video_enabled")) {
            z = false;
        } else {
            z = true;
        }
        a.Q = z;
        if (a.i("end_card_enabled")) {
            z = false;
        } else {
            z = true;
        }
        a.P = z;
        a.R = a.i("load_timeout_enabled");
        a.S = a.h("load_timeout");
        for (i = 0; i < a.ag.size(); i++) {
            if (a.ag.get(i) != null) {
                AdColonyNativeAdView adColonyNativeAdView = (AdColonyNativeAdView) a.ag.get(i);
                if (adColonyNativeAdView.ad != null) {
                    adColonyNativeAdView.S.setVisibility(4);
                }
                if (adColonyNativeAdView.Q != null) {
                    adColonyNativeAdView.Q.setVisibility(4);
                }
            }
        }
        this.G = a.J;
        if (this.G == null) {
            finish();
            return;
        }
        if (a.i("v4iap_enabled")) {
            this.G.u = AdColonyIAPEngagement.AUTOMATIC;
            this.G.t = true;
            this.G.m = a.j("product_id");
        }
        e = this.G.s;
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        if (a.m) {
            i = getResources().getConfiguration().orientation;
            int i3 = (i == 0 || i == 6 || i == 2) ? 6 : 7;
            a.w = i3;
            if (VERSION.SDK_INT < 10 || Build.MODEL.equals("Kindle Fire")) {
                if (Build.MODEL.equals("Kindle Fire")) {
                    getRequestedOrientation();
                    switch (((WindowManager) getSystemService("window")).getDefaultDisplay().getRotation()) {
                        case Gender.MALE /*0*/:
                            break;
                        case Gender.FEMALE /*1*/:
                            i2 = 0;
                            break;
                        case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                            i2 = 9;
                            break;
                        default:
                            i2 = 8;
                            break;
                    }
                }
                i2 = i;
                a.w = i2;
                setRequestedOrientation(i2);
            } else {
                setRequestedOrientation(a.w);
            }
        } else if (VERSION.SDK_INT >= 10) {
            setRequestedOrientation(6);
        } else {
            setRequestedOrientation(0);
        }
        setVolumeControlStream(3);
        this.E = new e(this);
        this.N = new FrameLayout(this);
        this.F = new ae(this);
        this.P = new FrameLayout(this);
        this.S = new a(this, this);
        this.R = new ADCImage(a.j("browser_icon"));
        AdColonyBrowser.A = false;
        a.K = this;
        a.L = this;
    }

    public void onResume() {
        i = true;
        super.onResume();
        if (a.a()) {
            finish();
        }
        b();
        if (this.C) {
            this.C = false;
            if (!d) {
                if (this.F.Q) {
                    this.O.addView(this.F.a);
                }
                if (this.F.Q) {
                    this.O.setVisibility(4);
                }
                if (Build.MODEL.equals("Kindle Fire")) {
                    this.F.m = 20;
                }
                if (Build.MODEL.equals("SCH-I800")) {
                    this.F.m = 25;
                }
                this.N.addView(this.E, new LayoutParams(this.x, this.y, 17));
                if (this.F.Q) {
                    this.N.addView(this.O, new LayoutParams(this.t, this.u - this.F.m, 17));
                }
                this.N.addView(this.F, new LayoutParams(this.t, this.u, 17));
            }
        }
        if (g) {
            this.P.removeView(this.S);
            this.P.addView(this.S);
            setContentView(this.P);
        } else {
            setContentView(this.N);
            if (d) {
                this.r = System.currentTimeMillis();
            }
        }
        this.E.a(this.F);
        this.E.a(this.F);
        try {
            this.T = new FileInputStream(this.B);
            this.E.a(this.T.getFD());
            if (!j) {
                onWindowFocusChanged(true);
            }
            if (a.Q) {
                this.F.a();
                this.F.c();
            }
        } catch (IOException e) {
            a.e("Unable to play video: " + a.j("video_filepath"));
            finish();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (a.aa) {
            a.v = true;
            a.K = null;
            a.aa = true;
        } else {
            a.v = true;
            a.K = null;
            a.aa = true;
        }
    }

    boolean b() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.t = displayMetrics.widthPixels;
        this.u = displayMetrics.heightPixels;
        this.t = displayMetrics.widthPixels;
        this.u = displayMetrics.heightPixels;
        this.z = -16777216;
        getWindow().setBackgroundDrawable(new ColorDrawable(this.z));
        int i = this.t;
        int i2 = this.u;
        this.x = i;
        this.y = i2;
        if (!a.m && this.x < this.y) {
            this.t = i2;
            this.u = i;
            this.x = i2;
            this.y = i;
        }
        if (!a.B) {
            return false;
        }
        a.B = false;
        return true;
    }

    public void onWindowFocusChanged(boolean has_focus) {
        if (has_focus) {
            j = false;
            if (d || !i) {
                if (g) {
                    if (this.M != null) {
                        this.M.seekTo(b);
                        this.M.start();
                        return;
                    }
                    if (this.P != null) {
                        this.P.removeAllViews();
                    }
                    setContentView(this.N);
                    return;
                } else if (d) {
                    this.F.invalidate();
                    return;
                } else {
                    return;
                }
            } else if (this.E != null) {
                if (c != 0) {
                    a = c;
                }
                c = 0;
                this.E.seekTo(a);
                if (a.m) {
                    Handler handler = new Handler();
                    Runnable anonymousClass1 = new Runnable(this) {
                        final /* synthetic */ ADCVideo a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.E.setBackgroundColor(0);
                        }
                    };
                    this.E.setBackgroundColor(-16777216);
                    handler.postDelayed(anonymousClass1, 900);
                } else {
                    this.E.setBackgroundColor(0);
                }
                if (!w.H) {
                    this.F.S = false;
                    this.E.start();
                    if (this.J) {
                        if (this.K) {
                            this.H.play();
                        } else {
                            this.H.resume();
                        }
                        this.K = false;
                    }
                }
                this.F.requestFocus();
                this.F.invalidate();
                return;
            } else {
                return;
            }
        }
        if (i) {
            if (this.J) {
                this.H.pause();
            }
            a = this.E.getCurrentPosition();
            this.E.pause();
        }
        j = true;
    }

    public void onPause() {
        i = false;
        if (!g) {
            b = 0;
        } else if (this.M != null) {
            b = this.M.getCurrentPosition();
            this.M.stopPlayback();
        }
        if (d) {
            View view = new View(this);
            view.setBackgroundColor(-16777216);
            setContentView(view);
            this.s = System.currentTimeMillis();
            if (!isFinishing()) {
                this.q += ((double) (this.s - this.r)) / 1000.0d;
            }
        }
        if (this.E != null) {
            if (this.E.getCurrentPosition() != 0) {
                a = this.E.getCurrentPosition();
            }
            this.E.a();
            this.E.setBackgroundColor(-16777216);
            if (this.J) {
                this.H.pause();
            }
        } else {
            a = 0;
        }
        this.F.A = true;
        this.F.I = false;
        this.F.H = false;
        this.F.J = false;
        this.F.u = 0;
        this.F.t = 0;
        this.F.invalidate();
        super.onPause();
    }

    public boolean onKeyUp(int keycode, KeyEvent event) {
        if (w.I != null && w.I.onKeyDown(keycode, event)) {
            return true;
        }
        if (keycode == 4) {
            if (d) {
                if (g) {
                    this.M.stopPlayback();
                    g = false;
                    this.P.removeAllViews();
                    setContentView(this.N);
                } else if (this.F != null && this.F.t == 0) {
                    a.aa = true;
                    this.F.f();
                }
            } else if (this.F != null && w.I != null) {
                Iterator it = w.I.o.iterator();
                while (it.hasNext()) {
                    ((ADCImage) it.next()).a();
                }
                w.I = null;
                w.H = false;
                this.E.start();
            } else if (this.F != null && this.F.M && this.F.O) {
                a.aa = true;
                this.F.g();
            }
            return true;
        } else if (keycode == 82) {
            return true;
        } else {
            return super.onKeyUp(keycode, event);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == 4) {
            return true;
        }
        return super.onKeyDown(keycode, event);
    }

    void a(String str) {
        this.A = str;
        g = true;
        this.M = new VideoView(this);
        this.M.setVideoURI(Uri.parse(str));
        new MediaController(this).setMediaPlayer(this.M);
        this.M.setLayoutParams(new LayoutParams(this.t, this.u, 17));
        this.P.addView(this.M);
        this.P.addView(this.S);
        setContentView(this.P);
        this.M.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ ADCVideo a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer media_player) {
                this.a.setContentView(this.a.N);
                this.a.P.removeAllViews();
                ADCVideo.g = false;
            }
        });
        this.M.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ ADCVideo a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer media_player) {
                this.a.P.removeViewAt(1);
            }
        });
        this.M.start();
    }
}
