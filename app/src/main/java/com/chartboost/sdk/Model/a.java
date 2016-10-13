package com.chartboost.sdk.Model;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.d;
import com.chartboost.sdk.impl.ab;
import com.chartboost.sdk.impl.ac;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ae;
import com.chartboost.sdk.impl.as;
import com.chartboost.sdk.impl.at;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.bn;
import com.chartboost.sdk.impl.bq;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.util.Date;

public final class a {
    public com.chartboost.sdk.Libraries.e.a a;
    public Date b;
    public b c = b.LOADING;
    public c d;
    public String e;
    public boolean f;
    public boolean g;
    public boolean h;
    public bn i;
    public boolean j;
    public boolean k = false;
    public boolean l = false;
    public av m;
    public boolean n = false;
    public boolean o = false;
    private d p;
    private a q;
    private Runnable r;
    private Runnable s;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.INTERSTITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.REWARDED_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.MORE_APPS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public interface a {
        void a(a aVar);

        void a(a aVar, CBImpressionError cBImpressionError);

        void a(a aVar, String str, com.chartboost.sdk.Libraries.e.a aVar2);

        void b(a aVar);

        void c(a aVar);

        void d(a aVar);
    }

    public enum b {
        LOADING,
        LOADED,
        DISPLAYED,
        CACHED,
        DISMISSING,
        LOADING_URL,
        NONE
    }

    public enum c {
        INTERSTITIAL,
        MORE_APPS,
        REWARDED_VIDEO
    }

    public a(c cVar, boolean z, String str, boolean z2) {
        this.f = z;
        this.g = z;
        this.h = false;
        this.d = cVar;
        this.j = z2;
        this.a = com.chartboost.sdk.Libraries.e.a.a;
        this.e = str;
        if (this.e == null) {
            this.e = "Default";
        }
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar, a aVar2) {
        if (aVar == null) {
            aVar = com.chartboost.sdk.Libraries.e.a.a();
        }
        this.a = aVar;
        this.b = new Date();
        this.c = b.LOADING;
        this.q = aVar2;
        if (aVar.a("type").equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
            switch (AnonymousClass1.a[this.d.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    this.p = new ad(this);
                    break;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    this.p = new ae(this);
                    break;
                case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                    this.p = new at(this);
                    break;
            }
        }
        this.p = new bq(this);
        this.p.a(aVar);
    }

    public void a() {
        if (this.q != null) {
            this.q.b(this);
        }
    }

    public void b() {
        if (this.q != null) {
            this.q.a(this);
        }
    }

    public void a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        if (this.c == b.DISPLAYED && !this.l) {
            if (str == null) {
                str = this.a.e(ShareConstants.WEB_DIALOG_PARAM_LINK);
            }
            String e = this.a.e("deep-link");
            if (!TextUtils.isEmpty(e)) {
                try {
                    if (a(e)) {
                        str = e;
                    }
                } catch (Exception e2) {
                }
            }
            this.q.a(this, str, aVar);
        }
    }

    public void a(CBImpressionError cBImpressionError) {
        if (this.q != null) {
            this.q.a(this, cBImpressionError);
        }
    }

    public void c() {
        if (this.q != null) {
            this.q.c(this);
        }
    }

    public void d() {
        if (this.q != null) {
            this.q.d(this);
        }
    }

    public boolean e() {
        this.p.b();
        if (this.p.e() != null) {
            return true;
        }
        return false;
    }

    public void f() {
        g();
        if (!this.g || this.h) {
            if (this.p != null) {
                this.p.d();
            }
            this.p = null;
            return;
        }
        this.c = b.CACHED;
    }

    public void g() {
        if (this.i != null) {
            this.i.d();
            try {
                if (this.p.e().getParent() != null) {
                    this.i.removeView(this.p.e());
                }
            } catch (Throwable e) {
                CBLogging.b("CBImpression", "Exception raised while cleaning up views", e);
            }
            this.i = null;
        }
        if (this.p != null) {
            this.p.f();
        }
    }

    public CBImpressionError h() {
        return this.p.c();
    }

    public com.chartboost.sdk.impl.bn.a i() {
        return this.p.e();
    }

    public void j() {
        if (!(this.p == null || this.p.e() == null)) {
            this.p.e().setVisibility(8);
        }
        if (this.r != null) {
            this.r.run();
            this.r = null;
        }
    }

    public void a(Runnable runnable) {
        this.r = runnable;
    }

    public void b(Runnable runnable) {
        this.s = runnable;
    }

    public void k() {
        this.l = true;
    }

    public void l() {
        if (this.s != null) {
            this.s.run();
            this.s = null;
        }
        this.l = false;
        this.k = false;
    }

    public String m() {
        return this.a.e("ad_id");
    }

    public com.chartboost.sdk.b n() {
        switch (AnonymousClass1.a[this.d.ordinal()]) {
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                return ac.f();
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                return as.e();
            default:
                return ab.e();
        }
    }

    public void o() {
        n().i(this);
    }

    public boolean p() {
        if (this.p != null) {
            return this.p.j();
        }
        return false;
    }

    public void q() {
        if (this.p != null) {
            this.p.k();
        }
    }

    public void r() {
        if (this.p != null) {
            this.p.l();
        }
    }

    public boolean a(String str) {
        return Chartboost.sharedChartboost().getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST).size() > 0;
    }
}
