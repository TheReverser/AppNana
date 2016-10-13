package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.BuildConfig;
import com.facebook.internal.ServerProtocol;
import com.immersion.hapticmediasdk.HapticContentSDKFactory;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

class ae extends View implements OnCompletionListener, OnErrorListener {
    static float[] ay = new float[80];
    boolean A = true;
    boolean B = true;
    boolean C = true;
    boolean D = true;
    boolean E;
    boolean F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    boolean L;
    boolean M;
    boolean N;
    boolean O;
    boolean P;
    boolean Q;
    boolean R;
    boolean S;
    boolean T;
    boolean U;
    Canvas V;
    String W = a.l.a.b;
    String Z;
    WebView a;
    float aA;
    float aB;
    float aC;
    float aD;
    float aE;
    float aF;
    Paint aG = new Paint(1);
    RectF aH = new RectF();
    b aI = new b(this);
    Handler aJ = new Handler(this) {
        final /* synthetic */ ae a;

        {
            this.a = r1;
        }

        public void handleMessage(Message m) {
            if (!this.a.d.isFinishing() && this.a.d.E != null) {
                this.a.a(m.what);
            }
        }
    };
    String aa;
    String ab;
    String ac;
    b ad;
    Paint ae = new Paint();
    Paint af = new Paint(1);
    Paint ag = new Paint(1);
    Paint ah = new Paint(1);
    Rect ai = new Rect();
    ADCImage aj;
    ADCImage ak;
    ADCImage al;
    ADCImage am;
    ADCImage an;
    ADCImage ao;
    ADCImage ap;
    ADCImage aq;
    ADCImage ar;
    ADCImage as;
    ADCImage at;
    ADCImage[] au = new ADCImage[4];
    ADCImage[] av = new ADCImage[4];
    m aw;
    String[] ax = new String[4];
    float az;
    WebView b;
    View c;
    ADCVideo d;
    double e = 1.0d;
    double f = 1.0d;
    int g = 99;
    int h = 0;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int t;
    int u;
    long v;
    long w;
    float x;
    boolean y = true;
    boolean z = true;

    class a extends View {
        Rect a = new Rect();
        final /* synthetic */ ae b;

        public a(ae aeVar, Activity activity) {
            this.b = aeVar;
            super(activity);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawARGB(255, 0, 0, 0);
            getDrawingRect(this.a);
            this.b.as.a(canvas, (this.a.width() - this.b.as.f) / 2, (this.a.height() - this.b.as.g) / 2);
            invalidate();
        }
    }

    class b extends Handler {
        final /* synthetic */ ae a;

        b(ae aeVar) {
            this.a = aeVar;
            a();
        }

        void a() {
            sendMessageDelayed(obtainMessage(), 500);
        }

        public void handleMessage(Message m) {
            a();
            if (!this.a.d.isFinishing() && this.a.d.E != null) {
                synchronized (this) {
                    if (!(this.a.ad == null || !this.a.ad.a() || this.a.d.E.isPlaying())) {
                        this.a.ad = null;
                        this.a.t = 0;
                        if (this.a.d.E != null) {
                            this.a.d.E.a();
                        }
                        this.a.d.n = true;
                        this.a.f();
                    }
                }
            }
        }
    }

    ae(ADCVideo aDCVideo) {
        super(aDCVideo);
        this.d = aDCVideo;
        this.M = a.l.a.s;
        if (a.J != null) {
            this.M |= a.J.i.v.l.a;
            a.J.n = a.J.o;
        }
        this.x = aDCVideo.getResources().getDisplayMetrics().density;
        this.Q = a.O;
        if (a.e != null) {
            a.T = a.e;
        }
        if (a.J != null && a.J.i.u.d) {
            this.N = !this.Q;
        }
        if (this.N) {
            this.aj = new ADCImage(a.j("end_card_filepath"));
            this.n = this.aj.f;
            this.o = this.aj.g;
            if (this.n == 0) {
                this.n = 480;
            }
            if (this.o == 0) {
                this.o = 320;
            }
            this.au[0] = new ADCImage(a.j("info_image_normal"));
            this.au[1] = new ADCImage(a.j("download_image_normal"));
            this.au[2] = new ADCImage(a.j("replay_image_normal"));
            this.au[3] = new ADCImage(a.j("continue_image_normal"));
            this.av[0] = new ADCImage(a.j("info_image_down"), true);
            this.av[1] = new ADCImage(a.j("download_image_down"), true);
            this.av[2] = new ADCImage(a.j("replay_image_down"), true);
            this.av[3] = new ADCImage(a.j("continue_image_down"), true);
            this.ax[0] = "Info";
            this.ax[1] = "Download";
            this.ax[2] = "Replay";
            this.ax[3] = "Continue";
        } else if (this.Q) {
            this.ao = new ADCImage(a.j("reload_image_normal"));
            this.am = new ADCImage(a.j("close_image_normal"));
            this.an = new ADCImage(a.j("close_image_down"));
            this.ap = new ADCImage(a.j("reload_image_down"));
            this.as = new ADCImage(a.j("browser_icon"));
            this.c = new a(this, aDCVideo);
            b();
        }
        if (this.M) {
            this.ak = new ADCImage(a.j("skip_video_image_normal"));
            this.al = new ADCImage(a.j("skip_video_image_down"));
            this.p = a.h("skip_delay") * 1000;
        }
        this.aG.setStyle(Style.STROKE);
        float f = 2.0f * aDCVideo.getResources().getDisplayMetrics().density;
        if (f > 6.0f) {
            f = 6.0f;
        }
        if (f < 4.0f) {
            this.aG.setStrokeWidth(2.0f * aDCVideo.getResources().getDisplayMetrics().density);
            this.aG.setColor(-3355444);
            this.S = false;
            this.L = false;
            this.T = false;
        } else {
            this.aG.setStrokeWidth(2.0f * aDCVideo.getResources().getDisplayMetrics().density);
            this.aG.setColor(-3355444);
            this.S = false;
            this.L = false;
            this.T = false;
        }
        if (a.J != null) {
            this.L = a.J.i.v.m.a;
            this.T = a.i("image_overlay_enabled");
        }
        if (this.L) {
            this.aq = new ADCImage(a.j("engagement_image_normal"));
            this.ar = new ADCImage(a.j("engagement_image_down"));
            this.ab = a.J.i.v.m.j;
            this.Z = a.J.i.v.m.l;
            this.aa = a.J.i.v.m.o;
            this.r = a.J.i.v.m.c;
            this.q = a.h("engagement_delay") * 1000;
            if (this.Z.equals(BuildConfig.VERSION_NAME)) {
                this.Z = "Learn More";
            }
            if (!this.aa.equals(BuildConfig.VERSION_NAME)) {
                this.G = true;
            }
            if (this.G) {
                this.b = new WebView(aDCVideo);
                this.b.setBackgroundColor(0);
            }
            if (this.aq == null || this.ar == null) {
                this.L = false;
            }
        }
        if (this.T) {
            double d;
            this.at = new ADCImage(a.j("image_overlay_filepath"));
            if (AdColony.isTablet()) {
                d = (((double) this.r) * (((double) this.x) / 1.0d)) / ((double) this.at.g);
            } else {
                d = (((double) this.r) * (((double) this.x) / 0.75d)) / ((double) this.at.g);
            }
            this.at.a(d);
        }
        if (ADCVideo.d) {
            e();
        }
        this.ae.setColor(-1);
        this.ag.setTextSize(24.0f);
        this.ag.setColor(-16777216);
        this.af.setColor(-3355444);
        this.af.setTextSize(20.0f);
        this.af.setTextAlign(Align.CENTER);
        this.ah.setTextSize(20.0f);
        this.ah.setColor(-1);
        try {
            getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
        } catch (Exception e) {
        }
    }

    public void onDraw(Canvas canvas) {
        if (!this.F) {
            a();
            this.V = canvas;
            if (!this.O && this.M) {
                this.O = this.d.E.getCurrentPosition() > this.p;
            }
            if (!this.P && this.L) {
                this.P = this.d.E.getCurrentPosition() > this.q;
            }
            ADCVideo aDCVideo = this.d;
            int c;
            int i;
            if (ADCVideo.d && this.N) {
                canvas.drawARGB((this.d.z >> 24) & 255, 0, 0, 0);
                this.aj.a(canvas, (this.d.t - this.aj.f) / 2, (this.d.u - this.aj.g) / 2);
                c = this.aj.c() + ((int) (186.0d * this.e));
                int d = this.aj.d() + ((int) (470.0d * this.e));
                i = 0;
                while (i < this.au.length) {
                    if (this.t == i + 1 || !(this.u != i + 1 || this.A || this.u == 0)) {
                        this.av[i].a(this.e);
                        this.av[i].a(canvas, c, d);
                        c = (int) (((double) c) + (((double) 1125974016) * this.e));
                    } else if (this.A || i + 1 != this.u) {
                        this.au[i].a(this.e);
                        this.au[i].a(canvas, c, d);
                        c = (int) (((double) c) + (((double) 1125974016) * this.e));
                    }
                    this.af.setColor(-1);
                    this.af.clearShadowLayer();
                    canvas.drawText(this.ax[i], ((float) this.au[i].c()) + ((float) (this.au[i].f / 2)), (float) (this.au[i].d() + this.au[i].g), this.af);
                    i++;
                }
                return;
            }
            aDCVideo = this.d;
            if (ADCVideo.d && this.Q) {
                this.am.a(this.f);
                this.an.a(this.f);
                this.ao.a(this.f);
                this.ap.a(this.f);
                i = (a.m || this.i == 0) ? this.d.t - this.am.f : this.i;
                this.i = i;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                if (this.H) {
                    this.an.a(canvas, this.i, this.j);
                } else {
                    this.am.a(canvas, this.i, this.j);
                }
                if (this.I) {
                    this.ap.a(canvas, this.k, this.l);
                } else {
                    this.ao.a(canvas, this.k, this.l);
                }
                i();
                return;
            }
            if (this.d.E != null) {
                int i2;
                a.l.a(((double) this.d.E.getCurrentPosition()) / ((double) this.d.E.getDuration()), this.d.G);
                if (this.d.J) {
                    this.d.H.update((long) this.d.E.getCurrentPosition());
                }
                c = this.d.E.getCurrentPosition();
                i = ((this.s - c) + 999) / 1000;
                if (this.S && i == 1) {
                    i2 = 0;
                } else {
                    i2 = i;
                }
                if (i2 == 0) {
                    this.S = true;
                }
                if (c >= 500) {
                    if (this.B) {
                        this.aA = (float) (360.0d / (((double) this.s) / 1000.0d));
                        this.B = false;
                        Rect rect = new Rect();
                        this.af.getTextBounds("0123456789", 0, 9, rect);
                        this.aD = (float) rect.height();
                    }
                    this.aB = (float) getWidth();
                    this.aC = (float) getHeight();
                    this.aE = this.aD;
                    this.aF = (((float) this.d.u) - this.aD) - ((float) this.m);
                    this.aH.set(this.aE - (this.aD / 2.0f), this.aF - (2.0f * this.aD), this.aE + (2.0f * this.aD), this.aF + (this.aD / 2.0f));
                    this.aG.setShadowLayer((float) ((int) (4.0d * this.e)), 0.0f, 0.0f, -16777216);
                    this.az = (float) (((((double) this.s) / 1000.0d) - (((double) c) / 1000.0d)) * ((double) this.aA));
                    canvas.drawArc(this.aH, 270.0f, this.az, false, this.aG);
                    aDCVideo = this.d;
                    if (!ADCVideo.d) {
                        this.af.setColor(-3355444);
                        this.af.setShadowLayer((float) ((int) (2.0d * this.e)), 0.0f, 0.0f, -16777216);
                        this.af.setTextAlign(Align.CENTER);
                        this.af.setLinearText(true);
                        canvas.drawText(BuildConfig.VERSION_NAME + i2, this.aH.centerX(), (float) (((double) this.aH.centerY()) + (((double) this.af.getFontMetrics().bottom) * 1.35d)), this.af);
                    }
                    if (this.M) {
                        aDCVideo = this.d;
                        if (!ADCVideo.d && this.O) {
                            if (this.t == 10) {
                                this.al.a(canvas, this.d.t - this.al.f, (int) (this.e * 4.0d));
                            } else {
                                this.ak.a(canvas, this.d.t - this.ak.f, (int) (this.e * 4.0d));
                            }
                        }
                    }
                    if (this.L && this.P) {
                        if (!this.G && !this.T) {
                            if (this.J) {
                                this.ar.c((int) (((float) (this.d.t - this.ar.f)) - (this.aD / 2.0f)), ((this.d.u - this.ar.g) - this.m) - ((int) (this.aD / 2.0f)));
                                this.ar.a(canvas);
                            } else {
                                this.aq.c((int) (((float) (this.d.t - this.aq.f)) - (this.aD / 2.0f)), ((this.d.u - this.aq.g) - this.m) - ((int) (this.aD / 2.0f)));
                                this.aq.a(canvas);
                            }
                            this.ag.setTextAlign(Align.CENTER);
                            canvas.drawText(this.Z, (float) this.aq.e.centerX(), (float) (((double) this.aq.e.centerY()) + (((double) this.ag.getFontMetrics().bottom) * 1.35d)), this.ag);
                        } else if (!this.G && this.T) {
                            this.at.c((int) (((float) (this.d.t - this.at.f)) - (this.aD / 2.0f)), ((this.d.u - this.at.g) - this.m) - ((int) (this.aD / 2.0f)));
                            this.at.a(canvas);
                        }
                    }
                }
                if (w.I != null) {
                    w.I.onDraw(canvas);
                }
            }
            aDCVideo = this.d;
            if (ADCVideo.i) {
                invalidate();
            }
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.m = this.d.u - h;
        if (Build.MODEL.equals("Kindle Fire")) {
            this.m = 20;
        }
        if (Build.MODEL.equals("SCH-I800")) {
            this.m = 25;
        }
        if (Build.MODEL.equals("SHW-M380K") || Build.MODEL.equals("SHW-M380S") || Build.MODEL.equals("SHW-M380W")) {
            this.m = 40;
        }
    }

    void a(int i) {
        try {
            if (this.C || i == 10) {
                this.C = false;
                Object j;
                switch (i) {
                    case Gender.FEMALE /*1*/:
                        this.t = 0;
                        a.a("info", "{\"ad_slot\":" + a.J.h.k.c + "}", this.d.G);
                        j = a.j("info_url");
                        l.b.a("INFO ").b(j);
                        if (!j.startsWith("market:") && !j.startsWith("amzn:")) {
                            AdColonyBrowser.url = j;
                            this.d.startActivity(new Intent(this.d, AdColonyBrowser.class));
                            break;
                        }
                        this.d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(j)));
                        break;
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        this.t = 0;
                        a.a("download", "{\"ad_slot\":" + a.J.h.k.c + "}", this.d.G);
                        j = a.j("download_url");
                        l.b.a("DOWNLOAD ").b(j);
                        if (!j.startsWith("market:") && !j.startsWith("amzn:")) {
                            AdColonyBrowser.url = j;
                            this.d.startActivity(new Intent(this.d, AdColonyBrowser.class));
                            break;
                        }
                        this.d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(j)));
                        break;
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        this.t = 0;
                        h();
                        invalidate();
                        break;
                    case Logger.INFO_LOGGING_LEVEL /*4*/:
                        this.t = 0;
                        this.d.E.a();
                        f();
                        break;
                    case p.b /*10*/:
                        this.t = 0;
                        g();
                        break;
                    default:
                        this.t = 0;
                        break;
                }
                new Handler().postDelayed(new Runnable(this) {
                    final /* synthetic */ ae a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.C = true;
                    }
                }, 1500);
            }
        } catch (RuntimeException e) {
            this.C = true;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (w.I != null) {
            w.I.onTouchEvent(event);
            return true;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        ADCVideo aDCVideo;
        if (action == 0) {
            aDCVideo = this.d;
            if (!ADCVideo.d || !this.Q) {
                aDCVideo = this.d;
                if (ADCVideo.d && this.N) {
                    x = (int) (((double) (event.getX() - ((float) this.aj.c()))) / (this.e * 2.0d));
                    y = (int) (((double) (event.getY() - ((float) this.aj.d()))) / (this.e * 2.0d));
                    if (this.t == 0 && y >= 235 && y < 305) {
                        action = a(x, y);
                        this.t = action;
                        this.u = action;
                        this.A = false;
                        invalidate();
                    }
                }
                if (this.M && this.O && this.d.E != null && a(this.ak, r1, r0)) {
                    this.t = 10;
                    this.u = this.t;
                    this.A = false;
                    invalidate();
                    return true;
                } else if (this.L && this.P && (a(this.aq, r1, r0) || a(this.at, r1, r0))) {
                    this.J = true;
                    invalidate();
                    return true;
                }
            } else if (a(this.am, x, y)) {
                this.H = true;
                invalidate();
                return true;
            } else if (!a(this.ao, x, y)) {
                return false;
            } else {
                this.I = true;
                invalidate();
                return true;
            }
        } else if (action == 1) {
            aDCVideo = this.d;
            if (ADCVideo.d && this.Q) {
                if (a(this.am, x, y) && this.H) {
                    this.t = 4;
                    if (this.a != null) {
                        this.a.clearCache(true);
                    }
                    this.aJ.sendMessageDelayed(this.aJ.obtainMessage(this.t), 250);
                    return true;
                } else if (a(this.ao, x, y) && this.I) {
                    this.t = 3;
                    if (this.a != null) {
                        this.a.clearCache(true);
                    }
                    this.aJ.sendMessageDelayed(this.aJ.obtainMessage(this.t), 250);
                    return true;
                }
            }
            aDCVideo = this.d;
            if (ADCVideo.d && this.N) {
                x = (int) (((double) (event.getX() - ((float) this.aj.c()))) / (this.e * 2.0d));
                y = (int) (((double) (event.getY() - ((float) this.aj.d()))) / (this.e * 2.0d));
                if (!this.A && y >= 235 && y < 305) {
                    action = a(x, y);
                    if (action > 0 && action == this.u) {
                        this.aJ.sendMessageDelayed(this.aJ.obtainMessage(action), 250);
                    }
                }
            }
            if (this.M && this.O && this.d.E != null && a(this.ak, r1, r0)) {
                this.t = 10;
                this.A = true;
                this.u = this.t;
                this.aJ.sendMessageDelayed(this.aJ.obtainMessage(this.t), 250);
                return true;
            } else if (this.L && this.P && (a(this.aq, r1, r0) || a(this.at, r1, r0))) {
                this.J = false;
                if (this.ab.startsWith("market:") || this.ab.startsWith("amzn:")) {
                    this.d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.ab)));
                } else if (this.ab.startsWith("v4iap:")) {
                    this.ac = this.d.G.m;
                    this.d.G.u = AdColonyIAPEngagement.OVERLAY;
                    this.L = false;
                    this.U = true;
                    this.T = false;
                    g();
                } else {
                    AdColonyBrowser.url = this.ab;
                    this.d.startActivity(new Intent(this.d, AdColonyBrowser.class));
                }
                a.a("in_video_engagement", "{\"ad_slot\":" + a.J.h.k.c + "}", this.d.G);
                return true;
            } else {
                this.H = false;
                this.I = false;
                this.J = false;
                this.A = true;
                this.t = 0;
                invalidate();
                return true;
            }
        } else if (action == 3) {
            this.H = false;
            this.I = false;
            this.J = false;
            this.A = true;
            this.t = 0;
            invalidate();
            return true;
        }
        return true;
    }

    int a(int i, int i2) {
        if (i >= this.g && i < this.g + 62) {
            return 1;
        }
        if (i >= this.g + 78 && i < (this.g + 78) + 62) {
            return 2;
        }
        if (i >= (this.g + 78) + 78 && i < ((this.g + 78) + 78) + 62) {
            return 3;
        }
        if (i >= ((this.g + 78) + 78) + 78 && i < (((this.g + 78) + 78) + 78) + 62) {
            return 4;
        }
        if (this.d.E == null || !this.M || i < this.d.E.getWidth() - this.ak.f || i2 > this.ak.g) {
            return 0;
        }
        return 10;
    }

    public boolean a(ADCImage aDCImage, int i, int i2) {
        if (aDCImage != null && i < (aDCImage.c() + aDCImage.f) + 8 && i > aDCImage.c() - 8 && i2 < (aDCImage.d() + aDCImage.g) + 8 && i2 > aDCImage.d() - 8) {
            return true;
        }
        return false;
    }

    public void a() {
        boolean b = this.d.b();
        this.y |= b;
        if (this.d.E != null) {
            if (this.s <= 0) {
                this.s = this.d.E.getDuration();
            }
            if (b) {
                setLayoutParams(new LayoutParams(this.d.t, this.d.u, 17));
                this.d.E.setLayoutParams(new LayoutParams(this.d.x, this.d.y, 17));
                this.y = true;
            }
        }
        if (this.y) {
            double sqrt;
            this.y = false;
            if (this.z) {
                DisplayMetrics displayMetrics = AdColony.activity().getResources().getDisplayMetrics();
                float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
                float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
                sqrt = Math.sqrt((double) ((displayMetrics.heightPixels * displayMetrics.heightPixels) + (displayMetrics.widthPixels * displayMetrics.widthPixels))) / Math.sqrt((double) ((f * f) + (f2 * f2)));
                this.f = sqrt / 280.0d < 0.7d ? 0.7d : sqrt / 280.0d;
                if (!AdColony.isTablet() && this.f == 0.7d) {
                    this.f = 1.0d;
                }
                float f3 = this.f * 20.0d < 18.0d ? 18.0f : (float) (this.f * 20.0d);
                if (this.f * 20.0d < 18.0d) {
                    f = 18.0f;
                } else {
                    f = (float) (this.f * 20.0d);
                }
                this.af.setTextSize(f3);
                this.ah.setTextSize(f3);
                this.ag.setTextSize(f);
                if (!(!this.L || this.aq == null || this.ar == null)) {
                    this.aq.a(b(this.Z + (this.aq.f * 2)), this.aq.g);
                    this.ar.a(b(this.Z + (this.ar.f * 2)), this.ar.g);
                }
                int i;
                if (this.d.t > this.d.u) {
                    i = this.d.u;
                } else {
                    i = this.d.t;
                }
                this.z = false;
            }
            if (this.Q) {
                if (b && this.a != null) {
                    this.a.setLayoutParams(new LayoutParams(this.d.t, this.d.u - this.m, 17));
                }
                this.e = ((double) this.d.y) / 640.0d < 0.9d ? 0.9d : ((double) this.d.y) / 640.0d;
                if (!AdColony.isTablet() && this.e == 0.9d) {
                    this.e = 1.2d;
                }
            }
            if (this.N) {
                double d = (double) (this.n / this.o);
                sqrt = ((double) this.d.t) / d > ((double) this.d.u) / 1.0d ? ((double) this.d.u) / 1.0d : ((double) this.d.t) / d;
                this.d.x = (int) (d * sqrt);
                this.d.y = (int) (sqrt * 1.0d);
                this.e = this.d.t > this.d.u ? ((double) this.d.y) / 640.0d : ((double) this.d.y) / 960.0d;
                if (((double) this.d.t) / ((double) this.n) > ((double) this.d.u) / ((double) this.o)) {
                    sqrt = ((double) this.d.u) / ((double) this.o);
                } else {
                    sqrt = ((double) this.d.t) / ((double) this.n);
                }
                this.aj.a(sqrt);
                this.aj.d(this.d.t, this.d.u);
            }
            if (!(!this.L || this.aq == null || this.ar == null)) {
                if (this.aq == null || this.ar == null || this.aq.b == null || this.ar.b == null) {
                    this.L = false;
                } else {
                    int height = (int) (((double) this.ar.b.getHeight()) * this.f);
                    this.aq.b(this.aq.f, (int) (((double) this.aq.b.getHeight()) * this.f));
                    this.ar.b(this.ar.f, height);
                }
            }
            if (this.M) {
                this.ak.a(this.f);
                this.al.a(this.f);
            }
        }
    }

    void b() {
        this.a = new WebView(this.d);
        this.a.setFocusable(true);
        this.a.setHorizontalScrollBarEnabled(false);
        this.a.setVerticalScrollBarEnabled(false);
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(PluginState.ON_DEMAND);
        settings.setBuiltInZoomControls(true);
        settings.setGeolocationEnabled(true);
        this.a.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ ae a;

            {
                this.a = r1;
            }

            public boolean onConsoleMessage(ConsoleMessage cm) {
                String sourceId = cm.sourceId();
                if (sourceId == null) {
                    sourceId = "Internal";
                } else {
                    int lastIndexOf = sourceId.lastIndexOf(47);
                    if (lastIndexOf != -1) {
                        sourceId = sourceId.substring(lastIndexOf + 1);
                    }
                }
                l.b.a(cm.message()).a(" [").a(sourceId).a(" line ").a(cm.lineNumber()).b((Object) "]");
                return true;
            }

            public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
                callback.invoke(origin, true, false);
            }
        });
        this.d.O = new FrameLayout(this.d);
        if (a.i("hardware_acceleration_disabled")) {
            try {
                this.d.O.getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this.a, new Object[]{Integer.valueOf(1), null});
            } catch (Exception e) {
            }
        }
        this.aw = new m(this.d, this.a, this.d);
        this.a.setWebViewClient(new WebViewClient(this) {
            String a = a.T;
            final /* synthetic */ ae b;

            {
                this.b = r2;
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                l.a.a("DEC request: ").b((Object) url);
                if (url.contains("mraid:")) {
                    this.b.aw.a(url);
                    return true;
                } else if (url.contains("youtube")) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + url));
                    intent.putExtra("VIDEO_ID", url);
                    this.b.d.startActivity(intent);
                    return true;
                } else if (url.contains("mraid.js")) {
                    return true;
                } else {
                    return false;
                }
            }

            public void onLoadResource(WebView view, String url) {
                l.a.a("DEC onLoad: ").b((Object) url);
                if (url.equals(this.a)) {
                    l.a.b((Object) "DEC disabling mouse events");
                    this.b.a("if (typeof(CN) != 'undefined' && CN.div) {\n  if (typeof(cn_dispatch_on_touch_begin) != 'undefined') CN.div.removeEventListener('mousedown',  cn_dispatch_on_touch_begin, true);\n  if (typeof(cn_dispatch_on_touch_end) != 'undefined')   CN.div.removeEventListener('mouseup',  cn_dispatch_on_touch_end, true);\n  if (typeof(cn_dispatch_on_touch_move) != 'undefined')  CN.div.removeEventListener('mousemove',  cn_dispatch_on_touch_move, true);\n}\n");
                }
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (url.equals(this.a)) {
                    this.b.d.k = true;
                    this.b.v = System.currentTimeMillis();
                }
            }

            public void onPageFinished(WebView view, String url) {
                if (url.equals(this.a) || a.T.startsWith("<")) {
                    this.b.D = false;
                    this.b.d.l = true;
                    this.b.w = System.currentTimeMillis();
                    this.b.d.p = ((double) (this.b.w - this.b.v)) / 1000.0d;
                }
                this.b.d.N.removeView(this.b.c);
            }
        });
        if (VERSION.SDK_INT >= 19) {
            if (a.T.startsWith("<")) {
                this.a.loadData(a.T, "text/html; charset=UTF-8", null);
            } else {
                this.a.loadUrl(a.T);
            }
        }
        String a = ab.a(a.U, BuildConfig.VERSION_NAME);
        l.a.b((Object) "Injecting mraid");
        a(a);
        a("var is_tablet=" + (a.m ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false") + ";");
        a = a.m ? "tablet" : "phone";
        a("adc_bridge.adc_version='" + a.W + "'");
        a("adc_bridge.os_version='" + a.V + "'");
        a("adc_bridge.os_name='android'");
        a("adc_bridge.device_type='" + a + "'");
        a("adc_bridge.fireChangeEvent({state:'default'});");
        a("adc_bridge.fireReadyEvent()");
        if (VERSION.SDK_INT >= 19) {
            return;
        }
        if (a.T.startsWith("<")) {
            this.a.loadData(a.T, "text/html; charset=UTF-8", null);
        } else {
            this.a.loadUrl(a.T);
        }
    }

    public void onCompletion(MediaPlayer player) {
        c();
    }

    public void c() {
        d dVar = a.l;
        ADCVideo aDCVideo = this.d;
        dVar.a(ADCVideo.e, this.d.G);
        if (this.Q && this.D && a.R) {
            this.d.N.addView(this.c);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.D && this.a.d != null && this.a.Q && this.a.a != null) {
                        this.a.d.m = true;
                        this.a.f();
                    }
                }
            }, (long) (a.S * 1000));
        }
        if (a.P) {
            f();
        }
        a.a("card_shown", this.d.G);
        synchronized (this.aI) {
            this.ad = null;
            if (a.J.i.u.e) {
                this.ad = new b(a.J.i.u.g);
            }
        }
        if (this.Q) {
            Handler handler = new Handler();
            final View view = new View(this.d);
            Runnable anonymousClass6 = new Runnable(this) {
                final /* synthetic */ ae b;

                public void run() {
                    this.b.d.N.removeView(view);
                    this.b.a(true);
                    this.b.d.r = System.currentTimeMillis();
                }
            };
            view.setBackgroundColor(-16777216);
            this.d.N.addView(view);
            handler.postDelayed(anonymousClass6, 500);
            this.d.O.setVisibility(0);
        }
        this.d.r = System.currentTimeMillis();
        e();
    }

    void d() {
        this.a.loadUrl(a.T);
        l.a.a("Loading - end card url = ").b(a.T);
    }

    void a(String str) {
        if (!this.N && this.a != null) {
            if (VERSION.SDK_INT >= 19) {
                this.a.evaluateJavascript(str, null);
            } else {
                this.a.loadUrl("javascript:" + str);
            }
        }
    }

    void a(boolean z) {
        if (!this.N) {
            if (z) {
                a("adc_bridge.fireChangeEvent({viewable:true});");
            } else {
                a("adc_bridge.fireChangeEvent({viewable:false});");
            }
        }
    }

    void b(boolean z) {
        if (!this.N) {
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        c(true);
        return true;
    }

    void e() {
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ ae a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d.E != null) {
                    this.a.d.E.setVisibility(8);
                }
            }
        }, 300);
        if (this.d.J) {
            this.d.H.stop();
        }
        ADCVideo aDCVideo = this.d;
        ADCVideo.d = true;
        if (this.d.E != null) {
            this.d.E.a();
        }
        w.I = null;
        invalidate();
        this.I = false;
        invalidate();
    }

    void f() {
        if (this.d == null) {
            return;
        }
        if (!this.Q || (this.a != null && this.d.O != null && this.d.N != null)) {
            a.D = true;
            this.d.s = System.currentTimeMillis();
            ADCVideo aDCVideo = this.d;
            aDCVideo.q += ((double) (this.d.s - this.d.r)) / 1000.0d;
            a.aa = true;
            for (int i = 0; i < a.ag.size(); i++) {
                if (a.ag.get(i) != null) {
                    ((AdColonyNativeAdView) a.ag.get(i)).a();
                }
            }
            try {
                this.d.T.close();
            } catch (Exception e) {
            }
            this.d.finish();
            this.ad = null;
            if (this.Q) {
                this.d.N.removeView(this.d.O);
                this.a.destroy();
                this.a = null;
            }
            a.M.a(this.d.G);
            AdColonyBrowser.A = true;
        }
    }

    void g() {
        c(false);
    }

    void c(boolean z) {
        a.D = true;
        if (!a.J.b() || z) {
            for (int i = 0; i < a.ag.size(); i++) {
                if (a.ag.get(i) != null) {
                    ((AdColonyNativeAdView) a.ag.get(i)).a();
                }
            }
            this.d.finish();
            a.M.b(this.d.G);
            a.aa = true;
            AdColonyBrowser.A = true;
            return;
        }
        ADCVideo aDCVideo = this.d;
        ADCVideo.a = this.d.E.getCurrentPosition();
        w.I = new w(this.d, (AdColonyV4VCAd) a.J);
    }

    void h() {
        a.a("replay", this.d.G);
        ADCVideo aDCVideo = this.d;
        ADCVideo.e = true;
        aDCVideo = this.d;
        ADCVideo.d = false;
        aDCVideo = this.d;
        ADCVideo.a = 0;
        this.S = false;
        final View view = new View(this.d);
        view.setBackgroundColor(-16777216);
        this.d.N.addView(view, new LayoutParams(this.d.t, this.d.u, 17));
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ ae b;

            public void run() {
                if (this.b.Q) {
                    this.b.d.O.setVisibility(4);
                }
                this.b.d.N.removeView(view);
            }
        }, 900);
        this.d.E.start();
        if (this.d.J) {
            try {
                this.d.H = HapticContentSDKFactory.GetNewSDKInstance(0, this.d);
                this.d.H.openHaptics(this.d.I);
            } catch (Exception e) {
                this.d.J = false;
            }
            if (this.d.H == null) {
                this.d.J = false;
            }
            if (this.d.J) {
                this.d.H.play();
            }
        }
        a.l.a(this.d.G);
        this.d.E.requestFocus();
        this.d.E.setBackgroundColor(0);
        this.d.E.setVisibility(0);
        a(false);
    }

    int b(String str) {
        this.ag.getTextWidths(str, ay);
        float f = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            f += ay[i];
        }
        return (int) f;
    }

    void i() {
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ ae b;

            public void onGlobalLayout() {
                Rect rect = new Rect();
                this.getWindowVisibleDisplayFrame(rect);
                if (this.b.a != null) {
                    this.b.b((this.getRootView().getHeight() - (rect.bottom - rect.top)) - ((this.b.d.u - this.b.a.getHeight()) / 2));
                }
                this.b.j();
            }
        });
    }

    void j() {
        if (this.h >= 70 && !this.E) {
            this.E = true;
            b(true);
        } else if (this.E && this.h == 0) {
            this.E = false;
            b(false);
        }
    }

    void b(int i) {
        this.h = i;
        if (i < 0) {
            this.h = 0;
        }
    }
}
