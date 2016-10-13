package com.vungle.publisher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.display.view.AdFragment;
import com.vungle.publisher.display.view.PostRollFragment;
import com.vungle.publisher.display.view.VideoFragment;
import com.vungle.publisher.display.view.VideoFragment.Factory;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.reporting.AdReportEventListener;
import com.vungle.sdk.VunglePub.Gender;
import java.io.File;
import javax.inject.Inject;

/* compiled from: vungle */
public class FullScreenAdActivity extends FragmentActivity {
    public static final String AD_CONFIG_EXTRA_KEY = "adConfig";
    public static final String AD_ID_EXTRA_KEY = "adId";
    @Inject
    AdManager a;
    @Inject
    AdReportEventListener b;
    @Inject
    bf c;
    @Inject
    EventBus d;
    @Inject
    Factory e;
    @Inject
    SdkState f;
    private Ad g;
    private AdFragment h;
    private PostRollFragment i;
    private View j;
    private VideoFragment k;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[Orientation.values().length];

        static {
            try {
                a[Orientation.autoRotate.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Orientation.matchVideo.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* compiled from: vungle */
    class a extends com.vungle.publisher.display.view.PostRollFragment.a {
        final /* synthetic */ FullScreenAdActivity a;

        a(FullScreenAdActivity fullScreenAdActivity) {
            this.a = fullScreenAdActivity;
        }

        public final void a() {
            Logger.v(Logger.AD_TAG, "postRoll.onCancel()");
            this.a.a(true, false);
        }

        public final void b() {
            Logger.v(Logger.AD_TAG, "postRoll.onCta()");
            this.a.b.a(com.vungle.publisher.db.model.EventTracking.a.postroll_click);
            this.a.b.a(com.vungle.publisher.db.model.AdReportEvent.a.download, null);
            this.a.a(com.vungle.publisher.db.model.EventTracking.a.postroll_click);
        }

        public final void c() {
            Logger.v(Logger.AD_TAG, "postRoll.onRepeat()");
            this.a.b();
            AdReportEventListener adReportEventListener = this.a.b;
            try {
                adReportEventListener.a(com.vungle.publisher.db.model.AdReportEvent.a.replay, null);
                adReportEventListener.a = adReportEventListener.b.q();
            } catch (Throwable e) {
                Logger.w(Logger.REPORT_TAG, "error reporting replay", e);
            }
        }
    }

    /* compiled from: vungle */
    class b implements com.vungle.publisher.display.view.VideoFragment.a {
        final /* synthetic */ FullScreenAdActivity a;

        b(FullScreenAdActivity fullScreenAdActivity) {
            this.a = fullScreenAdActivity;
        }

        public final void a() {
            Logger.v(Logger.AD_TAG, "video.onCancel()");
            this.a.a();
        }

        public final void b() {
            Logger.v(Logger.AD_TAG, "video.onCta()");
            this.a.b.a(com.vungle.publisher.db.model.AdReportEvent.a.cta, null);
            this.a.a(com.vungle.publisher.db.model.EventTracking.a.video_click);
        }

        public final void c() {
            Logger.v(Logger.AD_TAG, "video.onNext()");
            this.a.a();
        }
    }

    /* compiled from: vungle */
    class c implements com.vungle.publisher.bb.a {
        final /* synthetic */ FullScreenAdActivity a;

        c(FullScreenAdActivity fullScreenAdActivity) {
            this.a = fullScreenAdActivity;
        }

        public final void a() {
            this.a.a(false, false);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        try {
            Logger.d(Logger.AD_TAG, "interstital ad");
            super.onCreate(savedInstanceState);
            Injector.getInstance().a.inject(this);
            Intent intent = getIntent();
            a aVar = (a) intent.getParcelableExtra(AD_CONFIG_EXTRA_KEY);
            String stringExtra = intent.getStringExtra(AD_ID_EXTRA_KEY);
            AdManager adManager = this.a;
            Ad a = adManager.g.a(stringExtra);
            if (a == null) {
                a = adManager.n.a(stringExtra);
            }
            this.g = a;
            if (a == null) {
                Logger.w(Logger.AD_TAG, "no ad in activity");
                this.d.a(new aj());
                finish();
                return;
            }
            int i;
            this.j = getWindow().getDecorView();
            Video k = a.k();
            this.b.b();
            boolean z = savedInstanceState != null;
            if (!z) {
                this.d.a(new x(a, aVar));
            }
            Factory factory = this.e;
            VideoFragment videoFragment = (VideoFragment) getSupportFragmentManager().findFragmentByTag("videoFragment");
            AdReportEventListener adReportEventListener = this.b;
            com.vungle.publisher.display.view.VideoFragment.a bVar = new b(this);
            this.k = videoFragment == null ? Factory.a((VideoFragment) factory.a.get(), a, aVar, adReportEventListener, bVar) : Factory.a(videoFragment, a, aVar, adReportEventListener, bVar);
            if ((a instanceof LocalAd) && ((LocalAd) a).u() != null) {
                this.i = (PostRollFragment) getSupportFragmentManager().findFragmentByTag("postRollFragment");
                if (this.i == null) {
                    stringExtra = new File(bo.a(r3.j(), "index.html")).toURI().toString();
                    Logger.d(Logger.AD_TAG, "post-roll URL: " + stringExtra);
                    this.i = new PostRollFragment(stringExtra, new a(this), new c(this));
                } else {
                    this.i.a(new a(this), new c(this));
                }
            }
            a(aVar);
            Orientation orientation = aVar.getOrientation();
            switch (AnonymousClass2.a[orientation.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation);
                    i = 10;
                    break;
                default:
                    boolean z2 = (k.g == null || k.n == null || k.n.intValue() <= k.g.intValue()) ? false : true;
                    if (!z2) {
                        z2 = (k.g == null || k.n == null || k.g.intValue() <= k.n.intValue()) ? false : true;
                        if (!z2) {
                            Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (unknown) --> auto-rotate");
                            i = 10;
                            break;
                        }
                        Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (portrait)");
                        i = 7;
                        break;
                    }
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (landscape)");
                    i = 6;
                    break;
            }
            setRequestedOrientation(i);
            if ("postRollFragment".equals(z ? savedInstanceState.getString("currentFragment") : null)) {
                a();
            } else {
                b();
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error playing ad", e);
            a(false, false);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
            outState.putString("currentFragment", this.h.b());
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "error in onSaveInstanceState", e);
        }
    }

    final void a(final a aVar) {
        if (this.c.a(bd.KITKAT) && aVar.isImmersiveMode()) {
            this.j.setSystemUiVisibility(5894);
            this.j.setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener(this) {
                final /* synthetic */ FullScreenAdActivity b;

                public final void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & 4) == 0) {
                        this.b.a(aVar);
                    }
                }
            });
        }
    }

    protected void onResume() {
        try {
            super.onResume();
            SdkState sdkState = this.f;
            Logger.d(Logger.AD_TAG, "onAdActivityResume()");
            sdkState.a(false);
            sdkState.n = 0;
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "error in onResume()", e);
        }
    }

    protected void onPause() {
        try {
            super.onPause();
            SdkState sdkState = this.f;
            Logger.d(Logger.AD_TAG, "onAdActivityPause()");
            sdkState.n = sdkState.f();
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "error in onPause()", e);
        }
    }

    public void onBackPressed() {
        try {
            Logger.v(Logger.AD_TAG, "back button pressed");
            this.b.a(com.vungle.publisher.db.model.AdReportEvent.a.back, null);
            this.h.a();
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "error in onBackPressed", e);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.h.a(keyCode);
        return super.onKeyDown(keyCode, event);
    }

    final void a() {
        if (this.i == null) {
            a(true, false);
            return;
        }
        this.b.a(com.vungle.publisher.db.model.EventTracking.a.postroll_view);
        a(this.i);
    }

    final void b() {
        if (this.k == null) {
            a();
        } else {
            a(this.k);
        }
    }

    final void a(com.vungle.publisher.db.model.EventTracking.a aVar) {
        boolean z = false;
        try {
            String f = this.g.f();
            Logger.v(Logger.AD_TAG, "call to action destination " + f);
            if (f != null) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(f));
                intent.addFlags(268435456);
                startActivity(intent);
                AdReportEventListener adReportEventListener = this.b;
                Ad ad = this.g;
                adReportEventListener.g.a(ad, aVar, null, ad.g());
            }
            z = true;
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "error loading call-to-action URL " + null, e);
        }
        a(z, true);
    }

    final void a(boolean z, boolean z2) {
        try {
            this.d.a(z ? new ao(this.g, z2) : new an(this.g, z2));
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "error exiting ad", e);
        } finally {
            finish();
        }
    }

    private void a(AdFragment adFragment) {
        if (adFragment != this.h) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.setCustomAnimations(17432576, 17432577);
            this.h = adFragment;
            beginTransaction.replace(16908290, adFragment, adFragment.b());
            beginTransaction.commit();
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        try {
            super.onWindowFocusChanged(hasFocus);
            this.h.a(hasFocus);
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "error in onWindowFocusChanged", e);
        }
    }
}
