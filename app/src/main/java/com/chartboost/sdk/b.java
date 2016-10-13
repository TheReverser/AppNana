package com.chartboost.sdk;

import android.content.Context;
import android.os.Handler;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.bd;
import com.chartboost.sdk.impl.i;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class b {
    protected static Handler c = new Handler();
    public Chartboost a;
    public CBPreferences b;
    private ArrayList<com.chartboost.sdk.Model.a> d;
    private Map<String, com.chartboost.sdk.Model.a> e;
    private a f = null;

    protected interface a {
        void a(com.chartboost.sdk.Model.a aVar);

        void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError);

        void b(com.chartboost.sdk.Model.a aVar);

        void c(com.chartboost.sdk.Model.a aVar);

        void d(com.chartboost.sdk.Model.a aVar);

        void e(com.chartboost.sdk.Model.a aVar);

        boolean f(com.chartboost.sdk.Model.a aVar);

        boolean g(com.chartboost.sdk.Model.a aVar);

        boolean h(com.chartboost.sdk.Model.a aVar);

        boolean i(com.chartboost.sdk.Model.a aVar);
    }

    protected abstract com.chartboost.sdk.Model.a a(String str, boolean z);

    protected abstract a c();

    protected abstract av d(com.chartboost.sdk.Model.a aVar);

    protected abstract void j(com.chartboost.sdk.Model.a aVar);

    public b(Chartboost chartboost) {
        this.a = chartboost;
        this.b = CBPreferences.getInstance();
        this.e = new HashMap();
        this.d = new ArrayList();
    }

    public void a(final String str) {
        final com.chartboost.sdk.Model.a a = a(str, false);
        if (!b(a)) {
            c.post(new Runnable(this) {
                final /* synthetic */ b c;

                public void run() {
                    if (this.c.c(str)) {
                        this.c.f(this.c.d(str));
                    } else {
                        this.c.c(a);
                    }
                }
            });
        }
    }

    public void b(String str) {
        com.chartboost.sdk.Model.a a = a(str, true);
        if (!b(a)) {
            c(a);
        }
    }

    protected void a(com.chartboost.sdk.Model.a aVar) {
        o(aVar);
        b().d(aVar);
        aVar.c = com.chartboost.sdk.Model.a.b.CACHED;
    }

    protected final boolean b(com.chartboost.sdk.Model.a aVar) {
        if (b().i(aVar) || CBUtility.a().getInt("cbPrefSessionCount", 0) != 1) {
            return false;
        }
        a(aVar, CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED);
        return true;
    }

    protected void c(com.chartboost.sdk.Model.a aVar) {
        if (e(aVar) && b().h(aVar) && !p(aVar)) {
            if (!aVar.f && b().g(aVar)) {
                aVar.j = true;
                this.a.a(aVar);
            }
            a(d(aVar), aVar);
            if (aVar.d == c.INTERSTITIAL) {
                com.chartboost.sdk.Tracking.a.a("interstitial", aVar.e, aVar.f);
            } else if (aVar.d == c.MORE_APPS) {
                com.chartboost.sdk.Tracking.a.a("more-apps", aVar.e, aVar.f);
            } else if (aVar.d == c.REWARDED_VIDEO) {
                com.chartboost.sdk.Tracking.a.a("rewarded-video", aVar.e, aVar.f);
            }
        }
    }

    private final synchronized boolean p(com.chartboost.sdk.Model.a aVar) {
        boolean z = true;
        synchronized (this) {
            com.chartboost.sdk.Model.a l = l(aVar);
            if (l == null) {
                n(aVar);
                z = false;
            } else if (aVar.f || !l.f) {
                a(aVar, CBImpressionError.TOO_MANY_CONNECTIONS);
            } else {
                l.f = false;
            }
        }
        return z;
    }

    protected final void a(com.chartboost.sdk.Model.a aVar, CBError cBError) {
        a(aVar, cBError.c());
    }

    protected void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError) {
        m(aVar);
        c a = this.a.a();
        if (a != null && a.a()) {
            a.a(true);
        } else if (a != null && a.b()) {
            a.a(aVar, true);
        }
        if (aVar.d == c.INTERSTITIAL) {
            com.chartboost.sdk.Tracking.a.a("interstitial", aVar.e, cBImpressionError);
        } else if (aVar.d == c.MORE_APPS) {
            com.chartboost.sdk.Tracking.a.a("more-apps", aVar.e, cBImpressionError);
        } else if (aVar.d == c.REWARDED_VIDEO) {
            com.chartboost.sdk.Tracking.a.a("rewarded-video", aVar.e, cBImpressionError);
        }
        b().a(aVar, cBImpressionError);
    }

    protected final boolean e(com.chartboost.sdk.Model.a aVar) {
        if (Chartboost.isSessionStarted()) {
            c a = this.a.a();
            if (!aVar.f && a != null && a.b()) {
                a(aVar, CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
                return false;
            } else if (ax.a().c()) {
                return true;
            } else {
                a(aVar, CBImpressionError.INTERNET_UNAVAILABLE);
                return false;
            }
        }
        a(aVar, CBImpressionError.SESSION_NOT_STARTED);
        return false;
    }

    protected void f(com.chartboost.sdk.Model.a aVar) {
        m(aVar);
        if (aVar.c == com.chartboost.sdk.Model.a.b.DISPLAYED || b().f(aVar)) {
            boolean z = aVar.c == com.chartboost.sdk.Model.a.b.CACHED;
            h(aVar);
            c a = this.a.a();
            if (a != null) {
                if (a.a()) {
                    a.a(false);
                } else if (!(!aVar.j || z || aVar.c == com.chartboost.sdk.Model.a.b.DISPLAYED)) {
                    return;
                }
            }
            g(aVar);
        }
    }

    protected void g(com.chartboost.sdk.Model.a aVar) {
        this.a.a(aVar);
    }

    protected void h(com.chartboost.sdk.Model.a aVar) {
        i(aVar);
    }

    public final void i(com.chartboost.sdk.Model.a aVar) {
        boolean z = true;
        if (!aVar.h) {
            aVar.h = true;
            if (aVar.c != com.chartboost.sdk.Model.a.b.CACHED) {
                z = false;
            }
            if (d(aVar.e) == aVar) {
                if (z || aVar.g) {
                    j(aVar);
                }
                e(aVar.e);
                aVar.f = false;
            }
        }
    }

    protected final boolean k(com.chartboost.sdk.Model.a aVar) {
        return TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - aVar.b.getTime()) >= 86400;
    }

    protected final void a(com.chartboost.sdk.Libraries.e.a aVar, com.chartboost.sdk.Model.a aVar2) {
        if (aVar.f(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS) == 404) {
            CBLogging.b(aVar2.d, "Inavliad status code" + aVar.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS));
            a(aVar2, CBImpressionError.NO_AD_FOUND);
        } else if (aVar.f(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS) != 200) {
            CBLogging.b(aVar2.d, "Inavliad status code" + aVar.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS));
            a(aVar2, CBImpressionError.INTERNAL);
        } else {
            if (aVar2.d == c.INTERSTITIAL) {
                com.chartboost.sdk.Tracking.a.b("interstitial", aVar2.m(), aVar2.f);
            } else if (aVar2.d == c.MORE_APPS) {
                com.chartboost.sdk.Tracking.a.b("more-apps", aVar2.m(), aVar2.f);
            } else if (aVar2.d == c.REWARDED_VIDEO) {
                com.chartboost.sdk.Tracking.a.b("rewarded-video", aVar2.m(), aVar2.f);
            }
            aVar2.a(aVar, a.a().a);
        }
    }

    protected final void a(av avVar, final com.chartboost.sdk.Model.a aVar) {
        avVar.a("location", (Object) aVar.e);
        if (aVar.f) {
            avVar.a("cache", (Object) AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        avVar.b(this.a.getValidContext());
        avVar.a(new ay.c(this) {
            final /* synthetic */ b b;

            public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, i iVar) {
                CBLogging.a(aVar.d, "###Sucessful response received");
                if (aVar.d != c.REWARDED_VIDEO || this.b.b(aVar, aVar)) {
                    this.b.a(aVar, aVar);
                    return;
                }
                CBLogging.b(aVar.d, "###Video not available in the cache, so returning empty");
                this.b.a(aVar, CBImpressionError.INTERNAL);
            }

            public void a(ay ayVar, CBError cBError, i iVar) {
                this.b.a(aVar, cBError);
            }
        });
    }

    protected boolean b(com.chartboost.sdk.Libraries.e.a aVar, com.chartboost.sdk.Model.a aVar2) {
        if (aVar == null || aVar2 == null) {
            a(aVar2, CBImpressionError.INTERNAL);
            return false;
        }
        com.chartboost.sdk.Libraries.e.a a = aVar.a("assets");
        if (a == null) {
            a(aVar2, CBImpressionError.INTERNAL);
            return false;
        }
        if (CBPreferences.getInstance().getOrientation().isPortrait()) {
            a = a.a("video-landscape");
        } else {
            a = a.a("video-portrait");
        }
        if (bd.a().a(a.e(ShareConstants.WEB_DIALOG_PARAM_ID)) != null) {
            return true;
        }
        a(aVar2, CBImpressionError.INTERNAL);
        return false;
    }

    public synchronized com.chartboost.sdk.Model.a l(com.chartboost.sdk.Model.a aVar) {
        com.chartboost.sdk.Model.a aVar2;
        if (aVar != null) {
            String str = aVar.e == null ? "Default" : aVar.e;
            for (int i = 0; i < this.d.size(); i++) {
                if (str.equals(((com.chartboost.sdk.Model.a) this.d.get(i)).e == null ? "Default" : ((com.chartboost.sdk.Model.a) this.d.get(i)).e)) {
                    aVar2 = (com.chartboost.sdk.Model.a) this.d.get(i);
                    break;
                }
            }
        }
        aVar2 = null;
        return aVar2;
    }

    public synchronized void m(com.chartboost.sdk.Model.a aVar) {
        com.chartboost.sdk.Model.a l = l(aVar);
        if (l != null) {
            this.d.remove(l);
        }
    }

    public synchronized void n(com.chartboost.sdk.Model.a aVar) {
        this.d.add(aVar);
    }

    public boolean c(String str) {
        return d(str) != null;
    }

    public com.chartboost.sdk.Model.a d(String str) {
        com.chartboost.sdk.Model.a aVar = (com.chartboost.sdk.Model.a) this.e.get(str);
        return (aVar == null || k(aVar)) ? null : aVar;
    }

    public void e(String str) {
        this.e.remove(str);
    }

    public void a() {
        this.e.clear();
    }

    public void o(com.chartboost.sdk.Model.a aVar) {
        this.e.put(aVar.e, aVar);
    }

    public final a b() {
        if (this.f == null) {
            this.f = c();
        }
        return this.f;
    }

    protected Context d() {
        try {
            Method declaredMethod = Chartboost.class.getDeclaredMethod("getValidContext", new Class[0]);
            declaredMethod.setAccessible(true);
            return (Context) declaredMethod.invoke(this.a, new Object[0]);
        } catch (Throwable e) {
            CBLogging.b(this, "Error encountered getting valid context", e);
            CBUtility.throwProguardError(e);
            return Chartboost.sharedChartboost().getContext();
        }
    }
}
