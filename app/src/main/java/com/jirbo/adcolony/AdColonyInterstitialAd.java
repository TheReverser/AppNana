package com.jirbo.adcolony;

import android.graphics.Bitmap;

public class AdColonyInterstitialAd extends AdColonyAd {
    AdColonyAdListener v;
    AdColonyNativeAdListener w;
    AdColonyNativeAdView x;
    boolean y;

    public AdColonyInterstitialAd() {
        a.u = false;
        a.e();
        this.j = "interstitial";
        this.k = "fullscreen";
        this.y = false;
        this.l = ab.b();
    }

    public AdColonyInterstitialAd(String zone_id) {
        this.j = "interstitial";
        this.k = "fullscreen";
        a.e();
        this.g = zone_id;
        this.y = false;
        this.l = ab.b();
    }

    boolean b() {
        return false;
    }

    public AdColonyInterstitialAd withListener(AdColonyAdListener listener) {
        this.v = listener;
        return this;
    }

    boolean a(boolean z) {
        if (this.g == null) {
            this.g = a.l.e();
            if (this.g == null) {
                return false;
            }
        }
        return a.l.f(this.g);
    }

    public boolean isReady() {
        if (this.g == null) {
            this.g = a.l.e();
            if (this.g == null) {
                return false;
            }
        }
        if (!AdColony.isZoneNative(this.g)) {
            return a.l.f(this.g);
        }
        a.ac = 12;
        return false;
    }

    public void show() {
        a.ac = 0;
        if (this.y) {
            l.d.b((Object) "Show attempt on out of date ad object. Please instantiate a new ad object for each ad attempt.");
            return;
        }
        this.y = true;
        this.j = "interstitial";
        this.k = "fullscreen";
        if (isReady()) {
            if (a.v) {
                AnonymousClass2 anonymousClass2 = new j(this, a.l) {
                    final /* synthetic */ AdColonyInterstitialAd a;

                    void a() {
                        this.o.d.a(this.a.g, this.a);
                    }
                };
                a.v = false;
                c();
                a.J = this;
                if (!a.l.b(this)) {
                    if (this.v != null) {
                        this.v.onAdColonyAdAttemptFinished(this);
                    }
                    a.v = true;
                    return;
                } else if (this.v != null) {
                    this.v.onAdColonyAdStarted(this);
                }
            }
            this.f = 4;
            return;
        }
        AnonymousClass1 anonymousClass1 = new j(this, a.l) {
            final /* synthetic */ AdColonyInterstitialAd a;

            void a() {
                if (this.a.g != null) {
                    this.o.d.a(this.a.g, this.a);
                }
            }
        };
        this.f = 2;
        if (this.v != null) {
            this.v.onAdColonyAdAttemptFinished(this);
        }
    }

    void a() {
        this.j = "interstitial";
        this.k = "fullscreen";
        if (this.v != null) {
            this.v.onAdColonyAdAttemptFinished(this);
        } else if (this.w != null) {
            if (canceled()) {
                this.x.I = true;
            } else {
                this.x.I = false;
            }
            this.w.onAdColonyNativeAdFinished(true, this.x);
        }
        a.h();
        System.gc();
        if (!(a.u || AdColonyBrowser.B)) {
            for (int i = 0; i < a.ad.size(); i++) {
                ((Bitmap) a.ad.get(i)).recycle();
            }
            a.ad.clear();
        }
        a.K = null;
        a.v = true;
    }
}
