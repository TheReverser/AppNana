package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.offerwall.model.AppNana.Offer;
import com.facebook.BuildConfig;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import java.io.FileInputStream;

public class AdColonyNativeAdView extends FrameLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    boolean A;
    boolean B = true;
    boolean C;
    boolean D = true;
    boolean E;
    boolean F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    AdColonyInterstitialAd K;
    AdColonyNativeAdListener L;
    AdColonyNativeAdMutedListener M;
    ADCImage N;
    ADCImage O;
    ADCImage P;
    ImageView Q;
    b R;
    View S;
    Bitmap T;
    ADCImage U;
    ImageView V;
    boolean W = false;
    Button Z;
    TextView a;
    float aA;
    FileInputStream aB;
    String aa = BuildConfig.VERSION_NAME;
    String ab = BuildConfig.VERSION_NAME;
    String ac = BuildConfig.VERSION_NAME;
    MediaPlayer ad;
    Surface ae;
    String af;
    String ag;
    String ah;
    String ai;
    String aj;
    String ak;
    String al;
    String am = BuildConfig.VERSION_NAME;
    AdColonyIAPEngagement an = AdColonyIAPEngagement.NONE;
    int ao;
    int ap;
    int aq;
    int ar = -1;
    int as;
    int at = -3355444;
    int au = -16777216;
    int av;
    ab aw;
    a ax;
    float ay = 0.25f;
    float az = 0.25f;
    TextView b;
    TextView c;
    Activity d;
    String e;
    String f;
    ViewGroup g;
    SurfaceTexture h;
    int i;
    int j;
    int k;
    int l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    boolean x;
    boolean y;
    boolean z;

    class a extends TextureView implements SurfaceTextureListener {
        boolean a;
        boolean b;
        final /* synthetic */ AdColonyNativeAdView c;

        a(AdColonyNativeAdView adColonyNativeAdView, Context context) {
            this(adColonyNativeAdView, context, false);
        }

        a(AdColonyNativeAdView adColonyNativeAdView, Context context, boolean z) {
            this.c = adColonyNativeAdView;
            super(context);
            this.a = false;
            this.b = false;
            setSurfaceTextureListener(this);
            setWillNotDraw(false);
            this.a = z;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture texture, int w, int h) {
            if (texture == null) {
                this.c.u = true;
                this.c.Q.setVisibility(8);
                return;
            }
            this.c.R.setVisibility(0);
            this.c.h = texture;
            if (!this.c.u && !this.a) {
                this.c.ae = new Surface(texture);
                if (this.c.ad != null) {
                    this.c.ad.release();
                }
                this.c.i = w;
                this.c.j = h;
                this.c.ad = new MediaPlayer();
                try {
                    this.c.aB = new FileInputStream(this.c.f);
                    this.c.ad.setDataSource(this.c.aB.getFD());
                    this.c.ad.setSurface(this.c.ae);
                    this.c.ad.setOnCompletionListener(this.c);
                    this.c.ad.setOnPreparedListener(this.c);
                    this.c.ad.setOnErrorListener(this.c);
                    this.c.ad.prepareAsync();
                    l.c.b((Object) "[ADC] Native Ad Prepare called.");
                    this.b = true;
                    Handler handler = new Handler();
                    Runnable anonymousClass1 = new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (!this.a.c.z && !this.a.c.A) {
                                this.a.b = false;
                                this.a.c.u = true;
                                this.a.c.Q.setVisibility(8);
                            }
                        }
                    };
                    if (!this.b) {
                        handler.postDelayed(anonymousClass1, 1800);
                    }
                } catch (Exception e) {
                    this.c.u = true;
                    this.c.Q.setVisibility(8);
                }
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture texture, int w, int h) {
            l.c.b((Object) "[ADC] onSurfaceTextureSizeChanged");
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture texture) {
            l.c.b((Object) "[ADC] Native surface destroyed");
            this.c.z = false;
            this.c.Q.setVisibility(4);
            this.c.R.setVisibility(0);
            return true;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture texture) {
        }

        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            float x = event.getX();
            float y = event.getY();
            if (action == 1 && a.v && q.c() && (x <= ((float) ((this.c.aq - this.c.O.f) + 8)) || y >= ((float) (this.c.O.g + 8)) || this.c.u || this.c.ad == null || !this.c.ad.isPlaying())) {
                a.J = this.c.K;
                a.l.a.a(this.c.e, this.c.K.i);
                ADCVideo.a();
                this.c.K.j = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                this.c.K.k = "fullscreen";
                this.c.K.r = true;
                this.c.K.s = this.c.C;
                if ((this.c.z || this.c.u) && q.c()) {
                    if (this.c.L != null) {
                        this.c.L.onAdColonyNativeAdStarted(true, this.c);
                    }
                    if (this.c.ad == null || !this.c.ad.isPlaying()) {
                        this.c.K.o = 0.0d;
                        ADCVideo.c = 0;
                    } else {
                        ADCVideo.c = this.c.ad.getCurrentPosition();
                        this.c.K.o = this.c.K.n;
                        this.c.ad.pause();
                        this.c.u = true;
                    }
                    a.v = false;
                    a.l.d.b("video_expanded", this.c.K);
                    if (a.m) {
                        l.a.b((Object) "Launching AdColonyOverlay");
                        a.b().startActivity(new Intent(a.b(), AdColonyOverlay.class));
                    } else {
                        l.a.b((Object) "Launching AdColonyFullscreen");
                        a.b().startActivity(new Intent(a.b(), AdColonyFullscreen.class));
                    }
                    if (this.c.u) {
                        ag agVar = this.c.K.h.k;
                        agVar.d++;
                        a.l.a("start", "{\"ad_slot\":" + this.c.K.h.k.d + ", \"replay\":" + this.c.K.s + "}", this.c.K);
                        a.l.h.a(this.c.K.g, this.c.K.i.d);
                    }
                    this.c.C = true;
                }
            }
            return true;
        }
    }

    class b extends View {
        boolean a;
        final /* synthetic */ AdColonyNativeAdView b;

        public b(AdColonyNativeAdView adColonyNativeAdView, Context context) {
            this.b = adColonyNativeAdView;
            super(context);
        }

        public void onDraw(Canvas canvas) {
            this.b.g = (ViewGroup) getParent().getParent();
            Rect rect = new Rect();
            if (!(this.b.ad == null || this.b.ad.isPlaying() || !this.b.n)) {
                this.a = false;
            }
            if (getLocalVisibleRect(rect) && VERSION.SDK_INT >= 14 && this.b.z) {
                if ((!this.b.n || (this.b.n && (rect.top == 0 || rect.bottom - rect.top > this.b.getNativeAdHeight()))) && rect.bottom - rect.top > this.b.getNativeAdHeight() / 2) {
                    if (this.a || this.b.u || this.b.ad == null || this.b.ad.isPlaying() || this.b.A || this.b.K.a(true) || !this.b.t) {
                    }
                    if (!this.b.t) {
                        l.c.b((Object) "[ADC] Native Ad Starting");
                        this.b.b();
                        this.b.t = true;
                        this.b.K.j = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                        this.b.K.k = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                    } else if (!this.b.v && this.b.ad != null && q.c() && !this.b.ad.isPlaying() && !a.t) {
                        l.c.b((Object) "[ADC] Native Ad Resuming");
                        a.l.d.b("video_resumed", this.b.K);
                        if (!this.b.r) {
                            this.b.c(true);
                        }
                        this.b.setVolume(this.b.az);
                        this.b.ad.seekTo(this.b.K.p);
                        this.b.ad.start();
                    } else if (!(this.b.u || this.b.t || a.l.a(this.b.K.g, true, false))) {
                        this.b.u = true;
                        setVisibility(0);
                        this.b.Q.setVisibility(8);
                    }
                }
                this.a = true;
            } else {
                this.a = false;
            }
            if (!(this.b.u || q.c() || this.b.ad == null || this.b.ad.isPlaying())) {
                setVisibility(0);
                this.b.Q.setVisibility(8);
                this.b.u = true;
            }
            if (!this.b.u && this.b.ad != null && this.b.ad.isPlaying()) {
                setVisibility(8);
                this.b.Q.setVisibility(0);
            } else if (this.b.u || this.b.v) {
                canvas.drawARGB(255, 0, 0, 0);
                this.b.Q.setVisibility(8);
                this.b.N.a(canvas, (this.b.aq - this.b.N.f) / 2, (this.b.ar - this.b.N.g) / 2);
            }
            if (!this.b.A && !this.b.u) {
                invalidate();
            }
        }
    }

    public AdColonyNativeAdView(Activity context, String zone_id, int width) {
        super(context);
        a(context, zone_id, width);
        a();
    }

    public AdColonyNativeAdView(Activity context, String zone_id, int width, int height) {
        super(context);
        a(context, zone_id, width, height);
        a(false);
    }

    AdColonyNativeAdView(Activity context, String zone_id, int width, boolean is_private) {
        super(context);
        this.G = is_private;
        a(context, zone_id, width);
        a();
    }

    void a(Activity activity, String str, int i) {
        a(activity, str, i, 0);
    }

    void a(Activity activity, String str, int i, int i2) {
        int i3;
        int i4;
        a.e();
        if (!this.G) {
            a.ag.add(this);
        }
        a.ac = 0;
        this.d = activity;
        this.e = str;
        this.aq = i;
        this.k = i;
        if (i2 != 0) {
            this.l = i2;
            this.ar = i2;
            this.o = true;
        }
        this.r = true;
        this.aA = a.b().getResources().getDisplayMetrics().density;
        Display defaultDisplay = a.b().getWindowManager().getDefaultDisplay();
        if (VERSION.SDK_INT >= 14) {
            Point point = new Point();
            defaultDisplay.getSize(point);
            i3 = point.x;
            i4 = point.y;
        } else {
            i3 = defaultDisplay.getWidth();
            i4 = defaultDisplay.getHeight();
        }
        if (i3 >= i4) {
            i3 = i4;
        }
        this.av = i3;
        this.K = new AdColonyInterstitialAd(str);
        this.K.j = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
        this.K.k = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
        a.l.d.a(str, this.K);
        setBackgroundColor(-16777216);
    }

    void a() {
        a(true);
    }

    void a(boolean z) {
        float f;
        this.z = false;
        this.q = false;
        setWillNotDraw(false);
        this.K.x = this;
        if (this.B) {
            if (a.l == null || a.l.a == null || this.K == null || this.K.g == null || !a.l.a(this.K.g, true, false)) {
                this.u = true;
            } else {
                a.l.a.b(this.e);
            }
            this.K.b(true);
            this.aw = this.K.h;
            this.f = a.j("video_filepath");
            this.af = a.j("advertiser_name");
            this.ag = a.j(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION);
            this.ah = a.j(ShareConstants.WEB_DIALOG_PARAM_TITLE);
            this.ai = a.j("poster_image");
            this.aj = a.j("unmute");
            this.ak = a.j("mute");
            this.al = a.j("thumb_image");
            this.W = a.i("native_engagement_enabled");
            this.aa = a.j("native_engagement_label");
            this.ab = a.j("native_engagement_command");
            this.ac = a.j("native_engagement_type");
            this.J = a.i("v4iap_enabled");
            if (this.J) {
                this.an = AdColonyIAPEngagement.AUTOMATIC;
            }
            this.am = a.j("product_id");
            if (this.K.i == null || this.K.i.w == null) {
                this.y = true;
            } else {
                this.y = this.K.i.w.b;
            }
            if (this.aw != null) {
                this.aw.k();
            }
            if (this.K.i == null || this.K.i.w == null || !this.K.i.w.a || this.K.h == null) {
                a.ac = 13;
                return;
            }
            this.s = true;
            if (!this.G) {
                this.B = false;
            } else {
                return;
            }
        } else if (VERSION.SDK_INT < 14) {
            return;
        }
        this.ao = this.K.i.v.b;
        this.ap = this.K.i.v.c;
        if (this.ar == -1) {
            this.ar = (int) (((double) this.ap) * (((double) this.aq) / ((double) this.ao)));
            this.l = this.ar;
        }
        float f2 = ((float) this.ao) / ((float) this.ap);
        if (((float) this.aq) / ((float) this.ao) > ((float) this.ar) / ((float) this.ap)) {
            this.aq = (int) (((float) this.ar) * f2);
        } else {
            this.ar = (int) (((float) this.aq) / f2);
        }
        if (this.W) {
            this.Z = new Button(a.b());
            this.Z.setText(this.aa);
            this.Z.setGravity(17);
            this.Z.setTextSize((float) ((int) (18.0d * (((double) this.aq) / ((double) this.av)))));
            this.Z.setPadding(0, 0, 0, 0);
            this.Z.setBackgroundColor(this.at);
            this.Z.setTextColor(this.au);
            this.Z.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ AdColonyNativeAdView a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    if (action == 0) {
                        float[] fArr = new float[3];
                        Color.colorToHSV(this.a.at, fArr);
                        fArr[2] = fArr[2] * 0.8f;
                        this.a.Z.setBackgroundColor(Color.HSVToColor(fArr));
                    } else if (action == 3) {
                        this.a.Z.setBackgroundColor(this.a.at);
                    } else if (action == 1) {
                        if (this.a.J) {
                            this.a.an = AdColonyIAPEngagement.OVERLAY;
                            this.a.u = true;
                        } else {
                            if (this.a.ac.equals(Offer.TYPE_INSTALL) || this.a.ac.equals(NativeProtocol.WEB_DIALOG_URL)) {
                                a.l.d.b("native_overlay_click", this.a.K);
                                try {
                                    a.b().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.a.ab)));
                                } catch (Exception e) {
                                    Toast.makeText(a.b(), "Unable to open store.", 0).show();
                                }
                            }
                            this.a.Z.setBackgroundColor(this.a.at);
                        }
                    }
                    return true;
                }
            });
        }
        this.N = new ADCImage(this.ai, true, false);
        if (1.0f / (((float) this.N.f) / ((float) this.k)) > 1.0f / (((float) this.N.g) / ((float) this.l))) {
            f = 1.0f / (((float) this.N.g) / ((float) this.l));
        } else {
            f = 1.0f / (((float) this.N.f) / ((float) this.k));
        }
        this.N.a((double) f, true);
        this.P = new ADCImage(this.aj, true, false);
        this.O = new ADCImage(this.ak, true, false);
        this.U = new ADCImage(this.al, true, false);
        this.U.a((double) (1.0f / ((float) (((double) (((float) this.U.f) / ((float) this.aq))) / ((((double) this.aq) / 5.5d) / ((double) ((float) this.aq)))))), true);
        this.O.a((double) (this.aA / 2.0f), true);
        this.P.a((double) (this.aA / 2.0f), true);
        this.R = new b(this, a.b());
        this.V = new ImageView(a.b());
        this.Q = new ImageView(a.b());
        this.V.setImageBitmap(this.U.a);
        if (this.r) {
            this.Q.setImageBitmap(this.O.a);
        } else {
            this.Q.setImageBitmap(this.P.a);
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.O.f, this.O.g, 48);
        layoutParams.setMargins(this.k - this.O.f, 0, 0, 0);
        this.Q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdColonyNativeAdView a;

            {
                this.a = r1;
            }

            public void onClick(View v) {
                if (this.a.r) {
                    if (this.a.M != null) {
                        this.a.M.onAdColonyNativeAdMuted(this.a, true);
                    }
                    this.a.a(true, true);
                    this.a.x = true;
                } else if (this.a.T == this.a.P.a) {
                    if (this.a.M != null) {
                        this.a.M.onAdColonyNativeAdMuted(this.a, false);
                    }
                    this.a.x = false;
                    this.a.a(false, true);
                }
            }
        });
        this.T = this.O.a;
        if (this.u) {
            this.Q.setVisibility(8);
        }
        if (this.v) {
            this.Q.setVisibility(4);
        }
        if (VERSION.SDK_INT >= 14) {
            this.S = new a(this, a.b(), this.u);
        }
        int i = this.W ? 48 : 17;
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(this.aq, this.ar, i);
        if (i == 48) {
            layoutParams2.setMargins((this.k - this.aq) / 2, (this.l - this.ar) / 2, 0, 0);
        }
        if (VERSION.SDK_INT >= 14) {
            addView(this.S, layoutParams2);
        }
        if (VERSION.SDK_INT < 14) {
            this.u = true;
        }
        LayoutParams layoutParams3 = new FrameLayout.LayoutParams(this.k, this.l, i);
        layoutParams3.setMargins((this.k - this.aq) / 2, (this.l - this.ar) / 2, 0, 0);
        addView(this.R, layoutParams3);
        if (this.y && VERSION.SDK_INT >= 14 && this.D) {
            addView(this.Q, layoutParams);
        }
        if (this.W) {
            LayoutParams layoutParams4;
            if (z) {
                layoutParams4 = new FrameLayout.LayoutParams(this.k, this.l / 5, 80);
            } else {
                layoutParams4 = new FrameLayout.LayoutParams(this.k, this.ar / 5, 80);
            }
            addView(this.Z, layoutParams4);
        }
    }

    public boolean isReady() {
        if (this.K.a(true) && this.s && !this.F) {
            return true;
        }
        return false;
    }

    boolean b(boolean z) {
        if (this.K.a(true) && AdColony.isZoneNative(this.e)) {
            return true;
        }
        return false;
    }

    public int getNativeAdWidth() {
        return this.k;
    }

    public int getNativeAdHeight() {
        return this.W ? this.l + (this.l / 5) : this.l;
    }

    public void setOverlayButtonColor(int color) {
        if (this.W) {
            this.Z.setBackgroundColor(color);
        }
        this.at = color;
    }

    public void setOverlayButtonTextColor(int color) {
        if (this.W) {
            this.Z.setTextColor(color);
        }
        this.au = color;
    }

    public void setOverlayButtonTypeface(Typeface tf, int style) {
        if (this.W) {
            this.Z.setTypeface(tf, style);
        }
    }

    void a(boolean z, boolean z2) {
        if (z) {
            this.Q.setImageBitmap(this.P.a);
            this.r = false;
            a(0.0f, z2);
            this.T = this.P.a;
        } else if (!this.x && this.T == this.P.a) {
            this.Q.setImageBitmap(this.O.a);
            this.r = true;
            if (this.ad != null) {
                if (((double) this.az) != 0.0d) {
                    a(this.az, z2);
                } else {
                    a(0.25f, z2);
                }
            }
            this.T = this.O.a;
        }
    }

    public void setMuted(boolean mute) {
        a(mute, false);
    }

    public void destroy() {
        l.c.b((Object) "[ADC] Native Ad Destroy called.");
        if (this.ae != null) {
            this.ae.release();
        }
        if (this.ad != null) {
            this.ad.release();
        }
        this.ad = null;
        a.ag.remove(this);
    }

    public ImageView getAdvertiserImage() {
        if (this.U == null) {
            this.U = new ADCImage(this.al, true, false);
            this.U.a((double) (this.aA / 2.0f), true);
        }
        if (this.V == null) {
            this.V = new ImageView(a.b());
            this.V.setImageBitmap(this.U.a);
        }
        return this.V;
    }

    public String getTitle() {
        return this.ah;
    }

    public String getAdvertiserName() {
        return this.af;
    }

    public String getDescription() {
        return this.ag;
    }

    public boolean canceled() {
        return this.I;
    }

    public boolean iapEnabled() {
        return this.J;
    }

    public String iapProductID() {
        return this.am;
    }

    public AdColonyIAPEngagement iapEngagementType() {
        if (this.K == null || this.K.u != AdColonyIAPEngagement.END_CARD) {
            return this.an;
        }
        return AdColonyIAPEngagement.END_CARD;
    }

    public AdColonyNativeAdView withListener(AdColonyNativeAdListener listener) {
        this.L = listener;
        this.K.w = listener;
        return this;
    }

    public AdColonyNativeAdView withMutedListener(AdColonyNativeAdMutedListener mute_listener) {
        this.M = mute_listener;
        return this;
    }

    public void pause() {
        l.c.b((Object) "[ADC] Native Ad Pause called.");
        if (this.ad != null && !this.u && this.ad.isPlaying() && VERSION.SDK_INT >= 14) {
            a.l.d.b("video_paused", this.K);
            this.v = true;
            this.ad.pause();
            this.R.setVisibility(0);
            this.Q.setVisibility(4);
        }
    }

    public void resume() {
        l.c.b((Object) "[ADC] Native Ad Resume called.");
        if (this.ad != null && this.v && !this.u && VERSION.SDK_INT >= 14) {
            a.l.d.b("video_resumed", this.K);
            this.v = false;
            this.ad.seekTo(this.K.p);
            this.ad.start();
            this.R.setVisibility(4);
            this.Q.setVisibility(0);
        }
    }

    void c(boolean z) {
        if (this.ad != null && this.Q != null) {
            if (z) {
                this.ad.setVolume(0.0f, 0.0f);
                this.Q.setImageBitmap(this.P.a);
                this.T = this.P.a;
                return;
            }
            this.ad.setVolume(this.az, this.az);
            this.Q.setImageBitmap(this.O.a);
            this.T = this.O.a;
        }
    }

    void a(float f, boolean z) {
        if (VERSION.SDK_INT >= 14) {
            this.az = f;
            if (this.ad != null && ((double) f) >= 0.0d && ((double) f) <= 1.0d) {
                if (!this.x) {
                    this.ad.setVolume(f, f);
                }
                if (!this.z) {
                    return;
                }
                g gVar;
                if (this.T == this.P.a && ((double) f) > 0.0d && !this.x) {
                    gVar = new g();
                    gVar.b("user_action", z);
                    this.Q.setImageBitmap(this.O.a);
                    this.T = this.O.a;
                    a.l.d.a("sound_unmute", gVar, this.K);
                    this.r = true;
                } else if (this.T == this.O.a && ((double) f) == 0.0d) {
                    gVar = new g();
                    gVar.b("user_action", z);
                    this.Q.setImageBitmap(this.P.a);
                    this.T = this.P.a;
                    a.l.d.a("sound_mute", gVar, this.K);
                    this.r = false;
                }
            } else if (((double) f) >= 0.0d && ((double) f) <= 1.0d) {
                this.ay = f;
            }
        }
    }

    public void setVolume(float v) {
        a(v, false);
    }

    synchronized void b() {
        if ((this.u || this.ad == null || !this.ad.isPlaying()) && this.ad != null) {
            setVolume(this.az);
            this.ad.start();
            a.l.a(this.K);
            this.K.q = true;
            if (this.L != null) {
                this.L.onAdColonyNativeAdStarted(false, this);
            }
        }
    }

    void c() {
        if (!this.u && this.ad != null && this.ad.isPlaying() && !this.v) {
            a.l.d.b("video_paused", this.K);
            this.ad.pause();
        }
    }

    public void onPrepared(MediaPlayer player) {
        l.c.b((Object) "[ADC] Native Ad onPrepared called.");
        this.z = true;
        if (this.T == null || this.O.a == null) {
            this.R.setVisibility(0);
            this.Q.setVisibility(8);
            this.u = true;
            this.ad = null;
            this.K.p = 0;
        } else if (this.r || !this.T.equals(this.O.a)) {
            setVolume(this.az);
        } else {
            c(true);
        }
    }

    public void onCompletion(MediaPlayer player) {
        try {
            this.aB.close();
        } catch (Exception e) {
        }
        this.R.setVisibility(0);
        this.Q.setVisibility(8);
        this.K.j = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
        this.K.k = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
        this.K.q = true;
        this.u = true;
        if (this.ad != null) {
            this.ad.release();
        }
        this.ad = null;
        this.K.p = 0;
        g gVar = new g();
        gVar.b("ad_slot", this.K.h.k.d);
        gVar.b("replay", false);
        a.l.d.a("native_complete", gVar, this.K);
        if (this.L != null) {
            this.L.onAdColonyNativeAdFinished(false, this);
        }
        this.C = true;
    }

    public boolean onError(MediaPlayer player, int what, int extra) {
        this.R.setVisibility(0);
        this.Q.setVisibility(8);
        this.u = true;
        this.z = true;
        this.ad = null;
        this.K.p = 0;
        return true;
    }

    public void onDraw(Canvas canvas) {
        if (this.g != null) {
            Rect rect = new Rect();
            if (!this.g.hasFocus()) {
                this.g.requestFocus();
            }
            if (!(this.u || this.ad == null)) {
                this.as = this.ad.getCurrentPosition();
            }
            if (this.as != 0) {
                this.K.p = this.as;
            }
            getLocalVisibleRect(rect);
            boolean z = rect.bottom - rect.top > getNativeAdHeight() / 2;
            if ((z || this.n) && (!this.n || (z && (rect.bottom - rect.top >= getNativeAdHeight() || rect.top == 0)))) {
                if (this.u || this.ad == null || !this.ad.isPlaying()) {
                    if (!this.R.a) {
                        canvas.drawARGB(255, 0, 0, 0);
                    }
                } else if (this.z) {
                    this.K.j = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                    this.K.k = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                    a.l.a(((double) this.ad.getCurrentPosition()) / ((double) this.ad.getDuration()), this.K);
                    if (!this.H && this.ad.getCurrentPosition() > Settings.MIN_NANAS_TO_ALERT_RATING) {
                        this.H = true;
                        a.l.a("native_start", "{\"ad_slot\":" + this.K.h.k.d + ", \"replay\":false}", this.K);
                    }
                } else {
                    canvas.drawARGB(255, 0, 0, 0);
                }
            } else if (!(this.u || this.ad == null || !this.ad.isPlaying() || this.v)) {
                l.c.b((Object) "[ADC] Scroll Pause");
                a.l.d.b("video_paused", this.K);
                this.ad.pause();
                this.R.setVisibility(0);
            }
            if (!this.A && !this.u) {
                invalidate();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (VERSION.SDK_INT >= 14) {
            return false;
        }
        if (event.getAction() == 1 && a.v && q.c()) {
            a.J = this.K;
            a.l.a.a(this.e, this.K.i);
            ADCVideo.a();
            this.K.s = this.C;
            this.K.r = true;
            this.K.j = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
            this.K.k = "fullscreen";
            a.v = false;
            a.l.d.b("video_expanded", this.K);
            if (this.L != null) {
                this.L.onAdColonyNativeAdStarted(true, this);
            }
            if (a.m) {
                l.a.b((Object) "Launching AdColonyOverlay");
                a.b().startActivity(new Intent(a.b(), AdColonyOverlay.class));
            } else {
                l.a.b((Object) "Launching AdColonyFullscreen");
                a.b().startActivity(new Intent(a.b(), AdColonyFullscreen.class));
            }
            if (this.u) {
                this.K.f = -1;
                ag agVar = this.K.h.k;
                agVar.d++;
                a.l.a("start", "{\"ad_slot\":" + this.K.h.k.d + ", \"replay\":" + this.K.s + "}", this.K);
                a.l.h.a(this.K.g, this.K.i.d);
            }
            this.u = true;
            this.C = true;
        }
        return true;
    }

    public void notifyAddedToListView() {
        if (this.m) {
            ((a) this.S).onSurfaceTextureAvailable(this.h, this.i, this.j);
        } else {
            this.m = true;
        }
    }

    public void prepareForListView() {
        this.n = true;
    }
}
