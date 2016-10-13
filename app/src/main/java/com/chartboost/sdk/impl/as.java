package com.chartboost.sdk.impl;

import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.b;

public class as extends b {
    private static as d;
    private a e = null;
    private a f = null;

    private as() {
        super(Chartboost.sharedChartboost());
    }

    public static as e() {
        if (d == null) {
            synchronized (as.class) {
                if (d == null) {
                    d = new as();
                }
            }
        }
        return d;
    }

    public void f() {
        a(null);
    }

    public void g() {
        b(null);
    }

    public boolean h() {
        return c(null);
    }

    protected a a(String str, boolean z) {
        return new a(c.MORE_APPS, z, null, false);
    }

    protected av d(a aVar) {
        av avVar = new av("/more/get", null, "main");
        avVar.a(k.a.HIGH);
        avVar.a(com.chartboost.sdk.Model.b.c);
        return avVar;
    }

    protected void j(a aVar) {
        av avVar = new av("/more/show", null, "main");
        avVar.a(true);
        avVar.b(d());
        String e = aVar.a.e("ad_id");
        if (e != null) {
            avVar.a("ad_id", (Object) e);
        }
        avVar.j();
        com.chartboost.sdk.Tracking.a.a("more-apps", aVar.e, aVar.m());
    }

    public synchronized a l(a aVar) {
        return this.e;
    }

    public synchronized void m(a aVar) {
        this.e = null;
    }

    public synchronized void n(a aVar) {
        this.e = aVar;
    }

    public void a() {
        this.f = null;
    }

    public a d(String str) {
        return this.f;
    }

    public void e(String str) {
        this.f = null;
    }

    public void o(a aVar) {
        this.f = aVar;
    }

    public a c() {
        return new a(this) {
            final /* synthetic */ as a;
            private CBPreferences b = CBPreferences.getInstance();

            {
                this.a = r2;
            }

            public void a(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didClickMoreApps();
                }
            }

            public void b(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didCloseMoreApps();
                }
            }

            public void c(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didDismissMoreApps();
                }
            }

            public void d(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didCacheMoreApps();
                }
            }

            public void a(a aVar, CBImpressionError cBImpressionError) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didFailToLoadMoreApps(cBImpressionError);
                }
            }

            public void e(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didShowMoreApps();
                }
            }

            public boolean f(a aVar) {
                if (this.b.getDelegate() != null) {
                    return this.b.getDelegate().shouldDisplayMoreApps();
                }
                return true;
            }

            public boolean h(a aVar) {
                if (this.b.getDelegate() != null) {
                    return this.b.getDelegate().shouldRequestMoreApps();
                }
                return true;
            }

            public boolean g(a aVar) {
                if (this.b.getDelegate() != null) {
                    return this.b.getDelegate().shouldDisplayLoadingViewForMoreApps();
                }
                return true;
            }

            public boolean i(a aVar) {
                return true;
            }
        };
    }
}
