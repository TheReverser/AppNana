package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import com.chartboost.sdk.Chartboost.CBAgeGateConfirmation;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bn;
import com.facebook.BuildConfig;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public final class a {
    private static final String b = a.class.getSimpleName();
    private static a c;
    public com.chartboost.sdk.Model.a.a a = new com.chartboost.sdk.Model.a.a(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(com.chartboost.sdk.Model.a aVar) {
            synchronized (this.a) {
                boolean z = aVar.f;
            }
            if (aVar.c == b.LOADING) {
                aVar.c = b.LOADED;
                if (z) {
                    aVar.n().a(aVar);
                }
            }
            if (!z) {
                aVar.n().f(aVar);
            }
            aVar.n().m(aVar);
        }

        public void b(com.chartboost.sdk.Model.a aVar) {
            aVar.n().b().c(aVar);
            aVar.n().b().b(aVar);
            if (aVar.c == b.DISPLAYED) {
                c a = this.a.e.a();
                if (a != null) {
                    a.a(aVar, true);
                }
            }
            if (aVar.d == c.INTERSTITIAL) {
                com.chartboost.sdk.Tracking.a.c("interstitial", aVar.e, aVar.m());
            } else if (aVar.d == c.MORE_APPS) {
                com.chartboost.sdk.Tracking.a.c("more-apps", aVar.e, aVar.m());
            } else if (aVar.d == c.REWARDED_VIDEO) {
                com.chartboost.sdk.Tracking.a.c("rewarded-video", aVar.e, aVar.m());
            }
        }

        public void a(com.chartboost.sdk.Model.a aVar, String str, com.chartboost.sdk.Libraries.e.a aVar2) {
            boolean z = true;
            boolean z2 = (str == null || str.equals(BuildConfig.VERSION_NAME) || str.equals("null")) ? false : true;
            aVar.n().b().a(aVar);
            if (aVar.d != c.REWARDED_VIDEO) {
                aVar.n().b().c(aVar);
                if (aVar.c == b.DISPLAYED) {
                    c a = this.a.e.a();
                    if (a != null) {
                        if (z2) {
                            z = false;
                        }
                        a.a(aVar, z);
                    }
                }
            }
            if (z2) {
                av avVar = new av("api/click", null, "main");
                Context c = this.a.e.c();
                if (c == null) {
                    c = this.a.e.getValidContext();
                }
                avVar.b(c);
                avVar.a(aVar.a, ShareConstants.WEB_DIALOG_PARAM_TO);
                avVar.a(aVar.a, "cgn");
                avVar.a(aVar.a, "creative");
                avVar.a(aVar.a, "ad_id");
                avVar.a(aVar2, "cgn");
                avVar.a(aVar2, "creative");
                avVar.a(aVar2, "type");
                avVar.a(aVar2, "more_type");
                aVar.m = avVar;
                if (aVar.d != c.REWARDED_VIDEO) {
                    aVar.c = b.LOADING_URL;
                    this.a.e.a(aVar);
                }
                this.a.b(aVar, str);
            } else {
                this.a.g.a(aVar, false, str, CBClickError.URI_INVALID);
            }
            if (aVar.d == c.INTERSTITIAL) {
                com.chartboost.sdk.Tracking.a.b("interstitial", aVar.e, aVar.m());
            } else if (aVar.d == c.MORE_APPS) {
                com.chartboost.sdk.Tracking.a.b("more-apps", aVar.e, aVar.m());
            } else if (aVar.d == c.REWARDED_VIDEO) {
                com.chartboost.sdk.Tracking.a.b("rewarded-video", aVar.e, aVar.m());
            }
        }

        public void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError) {
            aVar.n().a(aVar, cBImpressionError);
        }

        public void c(com.chartboost.sdk.Model.a aVar) {
            aVar.n = true;
            av avVar;
            if (this.a.f.getDelegate() != null) {
                avVar = new av("/api/video-complete", null, "main");
                avVar.a("location", (Object) aVar.e);
                avVar.a("reward", (Object) aVar.a.e("reward"));
                avVar.a("currency-name", (Object) aVar.a.e("currency-name"));
                avVar.a("ad_id", (Object) aVar.m());
                avVar.a(true);
                avVar.j();
            } else {
                avVar = new av("/api/video-complete", null, "main");
                avVar.a("location", (Object) aVar.e);
                avVar.a("reward", (Object) aVar.a.e("reward"));
                avVar.a("currency-name", (Object) aVar.a.e("currency-name"));
                avVar.a("ad_id", (Object) aVar.m());
                avVar.a(true);
                avVar.j();
            }
        }

        public void d(com.chartboost.sdk.Model.a aVar) {
            aVar.o = true;
            av avVar = new av("/reward/show", null, "main");
            avVar.a("cached", (Object) Boolean.valueOf(true));
            avVar.a(true);
            avVar.a("ad_id", (Object) aVar.m());
            avVar.j();
            com.chartboost.sdk.Tracking.a.a("rewarded-video", aVar.e, aVar.m());
            aVar.n().e(aVar.e);
        }
    };
    private ba d = ba.a(this.g);
    private Chartboost e = Chartboost.sharedChartboost();
    private CBPreferences f = this.e.getPreferences();
    private com.chartboost.sdk.impl.ba.a g = new com.chartboost.sdk.impl.ba.a(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(com.chartboost.sdk.Model.a aVar, boolean z, String str, CBClickError cBClickError) {
            if (aVar.d != c.REWARDED_VIDEO) {
                aVar.c = b.DISMISSING;
            }
            c a = this.a.e.a();
            if (a != null && a.a()) {
                a.a(true);
            }
            if (z) {
                if (aVar.m != null) {
                    aVar.m.b(true);
                    aVar.m.a(true);
                    aVar.m.j();
                }
            } else if (this.a.f.getDelegate() != null) {
                this.a.f.getDelegate().didFailToRecordClick(str, cBClickError);
            }
        }
    };

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.LOADING_URL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.CACHED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[b.LOADED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[b.DISPLAYED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private a() {
    }

    public static a a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private final void a(final com.chartboost.sdk.Model.a aVar, final String str) {
        Runnable anonymousClass1 = new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                this.c.d.a(aVar, str, this.c.e.getHostActivity());
            }
        };
        c a = this.e.a();
        if (a == null || !a.a) {
            anonymousClass1.run();
        } else {
            aVar.a(anonymousClass1);
        }
    }

    private final void b(final com.chartboost.sdk.Model.a aVar, final String str) {
        CBAgeGateConfirmation anonymousClass2 = new CBAgeGateConfirmation(this) {
            final /* synthetic */ a c;

            public void execute(final boolean confirm) {
                this.c.e.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        if (confirm) {
                            this.b.c.a(aVar, str);
                        } else {
                            this.b.c.g.a(aVar, false, str, CBClickError.AGE_GATE_FAILURE);
                        }
                    }
                });
            }
        };
        if (this.f.getDelegate() == null || !this.f.getDelegate().shouldPauseClickForConfirmation(anonymousClass2)) {
            a(aVar, str);
            return;
        }
        c a = this.e.a();
        if (a != null && a.a()) {
            a.a(true);
        }
    }

    protected final boolean b() {
        if (c() == null) {
            return false;
        }
        this.a.b(c());
        return true;
    }

    protected final com.chartboost.sdk.Model.a c() {
        c a = this.e.a();
        bn c = a == null ? null : a.c();
        if (c == null) {
            return null;
        }
        return c.h();
    }

    public final void a(Activity activity, com.chartboost.sdk.Model.a aVar) {
        if (aVar != null) {
            switch (AnonymousClass5.a[aVar.c.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    if (aVar.j) {
                        this.e.a(aVar);
                        return;
                    }
                    return;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                case Logger.INFO_LOGGING_LEVEL /*4*/:
                    this.e.a(aVar);
                    return;
                case Logger.WARN_LOGGING_LEVEL /*5*/:
                    if (!aVar.e()) {
                        c a = this.e.a();
                        if (a != null) {
                            CBLogging.b(b, "Error onActivityStart " + aVar.c.name());
                            a.c(aVar);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
