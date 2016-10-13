package com.chartboost.sdk.impl;

import android.text.TextUtils;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.b;

public class ab extends b {
    private static ab d;

    protected ab() {
        super(Chartboost.sharedChartboost());
    }

    public static ab e() {
        if (d == null) {
            d = new ab();
        }
        return d;
    }

    protected a a(String str, boolean z) {
        return new a(c.INTERSTITIAL, z, str, false);
    }

    protected av d(a aVar) {
        av avVar = new av("/interstitial/get", null, "main");
        avVar.a(k.a.HIGH);
        avVar.a(com.chartboost.sdk.Model.b.b);
        return avVar;
    }

    protected a c() {
        return new a(this) {
            final /* synthetic */ ab a;
            private CBPreferences b = CBPreferences.getInstance();

            {
                this.a = r2;
            }

            public void a(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didClickInterstitial(aVar.e);
                }
            }

            public void b(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didCloseInterstitial(aVar.e);
                }
            }

            public void c(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didDismissInterstitial(aVar.e);
                }
            }

            public void d(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didCacheInterstitial(aVar.e);
                }
            }

            public void a(a aVar, CBImpressionError cBImpressionError) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didFailToLoadInterstitial(aVar.e, cBImpressionError);
                }
            }

            public void e(a aVar) {
                if (this.b.getDelegate() != null) {
                    this.b.getDelegate().didShowInterstitial(aVar.e);
                }
            }

            public boolean f(a aVar) {
                if (this.b.getDelegate() != null) {
                    return this.b.getDelegate().shouldDisplayInterstitial(aVar.e);
                }
                return true;
            }

            public boolean h(a aVar) {
                if (this.b.getDelegate() != null) {
                    return this.b.getDelegate().shouldRequestInterstitial(aVar.e);
                }
                return true;
            }

            public boolean g(a aVar) {
                return false;
            }

            public boolean i(a aVar) {
                if (this.b.getDelegate() != null) {
                    return this.b.getDelegate().shouldRequestInterstitialsInFirstSession();
                }
                return true;
            }
        };
    }

    protected void j(a aVar) {
        boolean z = true;
        av avVar = new av("/interstitial/show", null, "main");
        avVar.a(true);
        avVar.b(d());
        if (!(aVar == null || aVar.a == null)) {
            String e = aVar.a.e("deep-link");
            if (!TextUtils.isEmpty(e)) {
                String str = "retarget_reinstall";
                if (aVar.a(e)) {
                    z = false;
                }
                avVar.a(str, (Object) Boolean.valueOf(z));
            }
        }
        String e2 = aVar.a.e("ad_id");
        if (e2 != null) {
            avVar.a("ad_id", (Object) e2);
        }
        avVar.j();
        com.chartboost.sdk.Tracking.a.a("interstitial", aVar.e, aVar.m());
    }
}
