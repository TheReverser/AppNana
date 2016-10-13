package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.impl.be;
import com.chartboost.sdk.impl.bn;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public final class c {
    private static c e;
    protected boolean a = false;
    private Chartboost b;
    private CBPreferences c;
    private bn d = null;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.LOADING_URL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private c(Chartboost chartboost) {
        this.b = chartboost;
        this.c = CBPreferences.getInstance();
    }

    protected static c a(Chartboost chartboost) {
        if (e == null) {
            e = new c(chartboost);
        }
        return e;
    }

    protected void a(a aVar) {
        switch (AnonymousClass3.a[aVar.c.ordinal()]) {
            case Gender.FEMALE /*1*/:
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                if (!aVar.j || aVar.k) {
                    d(aVar);
                    return;
                } else {
                    e(aVar);
                    return;
                }
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                e(aVar);
                return;
            default:
                d(aVar);
                return;
        }
    }

    private void d(a aVar) {
        if (this.d == null || this.d.h() == aVar) {
            Object obj = aVar.c != b.DISPLAYED ? 1 : null;
            aVar.c = b.DISPLAYED;
            Context c = this.b.c();
            CBImpressionError cBImpressionError = c == null ? CBImpressionError.NO_HOST_ACTIVITY : null;
            if (cBImpressionError == null) {
                cBImpressionError = aVar.h();
            }
            if (cBImpressionError != null) {
                aVar.a(cBImpressionError);
                return;
            }
            if (this.d == null) {
                this.d = new bn(c, aVar);
                c.addContentView(this.d, new LayoutParams(-1, -1));
            }
            this.d.a();
            aVar.i = this.d;
            if (obj != null) {
                this.d.e().a();
                be.b bVar = be.b.PERSPECTIVE_ROTATE;
                if (aVar.d == com.chartboost.sdk.Model.a.c.MORE_APPS) {
                    bVar = be.b.PERSPECTIVE_ZOOM;
                }
                be.b a = be.b.a(aVar.a.f("animation"));
                if (a != null) {
                    bVar = a;
                }
                if (this.c.getAnimationsOff()) {
                    bVar = be.b.NONE;
                }
                aVar.k();
                be.a(bVar, aVar, new be.a(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void a(a aVar) {
                        aVar.l();
                    }
                });
                aVar.n().b().e(aVar);
                return;
            }
            return;
        }
        aVar.a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
    }

    public void a(final a aVar, final boolean z) {
        CBLogging.b("CBViewController", "###### dismiss impressiony");
        Runnable anonymousClass2 = new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                if (!z) {
                    this.c.a = true;
                }
                aVar.c = b.DISMISSING;
                be.b bVar = be.b.PERSPECTIVE_ROTATE;
                if (aVar.d == com.chartboost.sdk.Model.a.c.MORE_APPS) {
                    bVar = be.b.PERSPECTIVE_ZOOM;
                }
                be.b a = be.b.a(aVar.a.f("animation"));
                if (a != null) {
                    bVar = a;
                }
                if (this.c.c.getAnimationsOff()) {
                    bVar = be.b.NONE;
                }
                be.b(bVar, aVar, new be.a(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void a(final a aVar) {
                        this.a.c.a = false;
                        if (aVar.c != b.LOADING_URL) {
                            this.a.c.b.b.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 b;

                                public void run() {
                                    this.b.a.c.c(aVar);
                                }
                            });
                        }
                        aVar.j();
                    }
                });
            }
        };
        if (aVar.l) {
            aVar.b(anonymousClass2);
        } else {
            anonymousClass2.run();
        }
    }

    private void e(a aVar) {
        Context c = this.b.c();
        if (c == null) {
            CBLogging.d(this, "No host activity to display loading view");
            return;
        }
        if (this.d == null) {
            this.d = new bn(c, aVar);
            c.addContentView(this.d, new LayoutParams(-1, -1));
        }
        this.d.b();
    }

    public void a(boolean z) {
        CBLogging.b("CBViewController", "###### dismiss loading view");
        if (a()) {
            this.d.c();
            if (z && this.d != null && this.d.h() != null) {
                c(this.d.h());
            }
        }
    }

    public void b(a aVar) {
        CBLogging.b("CBViewController", "###### removing impression silently");
        if (a()) {
            a(false);
        }
        aVar.g();
        try {
            ((ViewGroup) this.d.getParent()).removeView(this.d);
        } catch (Throwable e) {
            CBLogging.b("CBViewController", "Exception removing impression silently", e);
        }
        this.d = null;
    }

    public void c(a aVar) {
        CBLogging.b("CBViewController", "###### removing impression ");
        aVar.c = b.NONE;
        if (this.d != null) {
            try {
                ((ViewGroup) this.d.getParent()).removeView(this.d);
            } catch (Throwable e) {
                CBLogging.b("CBViewController", "Exception removing impression ", e);
            }
            aVar.f();
            this.d = null;
            this.a = false;
            if (this.c.getImpressionsUseActivities()) {
                d();
            }
        } else if (this.c.getImpressionsUseActivities()) {
            d();
        }
    }

    private void d() {
        CBLogging.b("CBViewController", "###### Closeingimpression ");
        Activity c = this.b.c();
        if (c != null && (c instanceof CBImpressionActivity)) {
            this.b.d();
            c.finish();
        }
    }

    public boolean a() {
        return this.d != null && this.d.g();
    }

    public boolean b() {
        return this.d != null;
    }

    public bn c() {
        return this.d;
    }
}
