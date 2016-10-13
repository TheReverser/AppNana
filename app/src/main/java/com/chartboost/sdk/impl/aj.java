package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.d;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public final class aj extends RelativeLayout implements com.chartboost.sdk.impl.bn.a {
    private ak a;
    private LinearLayout b;
    private bg c;
    private TextView d;
    private ae e;
    private a f = a.BOTTOM;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum a {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public aj(Context context, ae aeVar) {
        super(context);
        this.e = aeVar;
        a(context);
    }

    public void a(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("the given side cannot be null");
        }
        this.f = aVar;
        LayoutParams layoutParams = null;
        setClickable(false);
        switch (AnonymousClass2.a[this.f.ordinal()]) {
            case Gender.FEMALE /*1*/:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.a(48, getContext()));
                layoutParams.addRule(10);
                this.a.b(1);
                break;
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.a(48, getContext()));
                layoutParams.addRule(12);
                this.a.b(4);
                break;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.a(48, getContext()), -1);
                layoutParams.addRule(9);
                this.a.b(8);
                break;
            case Logger.INFO_LOGGING_LEVEL /*4*/:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.a(48, getContext()), -1);
                layoutParams.addRule(11);
                this.a.b(2);
                break;
        }
        setLayoutParams(layoutParams);
    }

    private void a(Context context) {
        Context context2 = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 8.0f);
        setGravity(17);
        this.a = new ak(context2);
        this.a.a(-1);
        this.a.setBackgroundColor(-855638017);
        addView(this.a, new RelativeLayout.LayoutParams(-1, -1));
        this.b = new LinearLayout(context2);
        this.b.setOrientation(0);
        this.b.setGravity(17);
        int a = CBUtility.a(36, context2);
        this.c = new bg(context2);
        this.c.setPadding(round, round, round, round);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        this.c.setScaleType(ScaleType.FIT_CENTER);
        this.d = new TextView(getContext());
        this.d.setPadding(round / 2, round, round, round);
        this.d.setTextColor(-15264491);
        this.d.setTextSize(2, 13.0f);
        this.d.setTypeface(null, 1);
        this.d.setGravity(17);
        this.b.addView(this.c, layoutParams);
        this.b.addView(this.d, new LinearLayout.LayoutParams(-2, -1));
        addView(this.b, new RelativeLayout.LayoutParams(-1, -1));
        a();
    }

    public void a(i iVar) {
        this.c.a(iVar);
        this.c.setScaleType(ScaleType.CENTER_INSIDE);
    }

    public void a(String str) {
        this.d.setText(str);
    }

    public void a() {
    }

    public View b() {
        return this;
    }

    public void a(boolean z) {
        a(z, 510);
    }

    private void a(final boolean z, long j) {
        this.e.y = z;
        if (!z || getVisibility() != 0) {
            if (z || getVisibility() != 8) {
                Animation translateAnimation;
                Runnable anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ aj b;

                    public void run() {
                        if (!z) {
                            this.b.setVisibility(8);
                            this.b.clearAnimation();
                        }
                        this.b.e.g.remove(this);
                    }
                };
                if (z) {
                    setVisibility(0);
                }
                float b = CBUtility.b(48, getContext());
                float f;
                switch (AnonymousClass2.a[this.f.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                        if (z) {
                            f = -b;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, z ? 0.0f : -b);
                        break;
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        if (z) {
                            f = b;
                        } else {
                            f = 0.0f;
                        }
                        if (z) {
                            b = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, b);
                        break;
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        if (z) {
                            f = -b;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, z ? 0.0f : -b, 0.0f, 0.0f);
                        break;
                    case Logger.INFO_LOGGING_LEVEL /*4*/:
                        f = z ? b : 0.0f;
                        if (z) {
                            b = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, b, 0.0f, 0.0f);
                        break;
                    default:
                        translateAnimation = null;
                        break;
                }
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(!z);
                startAnimation(translateAnimation);
                this.e.g.add(anonymousClass1);
                d.a.postDelayed(anonymousClass1, j);
            }
        }
    }
}
