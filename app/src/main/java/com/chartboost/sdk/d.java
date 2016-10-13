package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.be;
import java.util.ArrayList;
import java.util.List;

public abstract class d {
    public static Handler a = new Handler();
    protected List<b> b = new ArrayList();
    protected List<b> c = new ArrayList();
    protected com.chartboost.sdk.Libraries.e.a d;
    protected com.chartboost.sdk.Model.a e;
    protected CBOrientation f;
    public List<Runnable> g = new ArrayList();
    protected boolean h = true;
    protected boolean i = true;
    private boolean j;
    private a k;

    public interface b {
        boolean a();
    }

    public abstract class a extends RelativeLayout implements com.chartboost.sdk.impl.bn.a {
        protected boolean a = false;
        final /* synthetic */ d b;
        private boolean c = false;

        protected abstract void a(int i, int i2);

        public a(d dVar, Context context) {
            this.b = dVar;
            super(context);
            dVar.k = this;
            dVar.j = false;
            setFocusableInTouchMode(true);
            requestFocus();
        }

        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            if (!this.a) {
                if (CBPreferences.getInstance().getForcedOrientationDifference().isOdd()) {
                    b(h, w);
                } else {
                    b(w, h);
                }
            }
        }

        private boolean b(int i, int i2) {
            boolean z = true;
            if (this.c) {
                return false;
            }
            this.c = true;
            try {
                CBOrientation orientation = CBPreferences.getInstance().getOrientation();
                if (this.b.h && orientation.isPortrait()) {
                    this.b.f = orientation;
                } else if (this.b.i && orientation.isLandscape()) {
                    this.b.f = orientation;
                }
                a(i, i2);
            } catch (Throwable e) {
                CBLogging.b("CBViewProtocol", "Exception raised while layouting Subviews", e);
                z = false;
            }
            this.c = false;
            return z;
        }

        public void a() {
            a((Activity) getContext());
        }

        public View b() {
            return this;
        }

        public void c() {
        }

        public boolean a(Activity activity) {
            int width;
            int height;
            try {
                width = getWidth();
                height = getHeight();
                if (width == 0 || height == 0) {
                    View findViewById = activity.getWindow().findViewById(16908290);
                    if (findViewById == null) {
                        findViewById = activity.getWindow().getDecorView();
                    }
                    width = findViewById.getWidth();
                    height = findViewById.getHeight();
                }
            } catch (Exception e) {
                height = 0;
                width = 0;
            }
            if (width == 0 || r0 == 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
            }
            if (!CBPreferences.getInstance().getForcedOrientationDifference().isOdd()) {
                int i = height;
                height = width;
                width = i;
            }
            return b(height, width);
        }

        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            for (int i = 0; i < this.b.g.size(); i++) {
                d.a.removeCallbacks((Runnable) this.b.g.get(i));
            }
            this.b.g.clear();
        }
    }

    protected abstract a a(Context context);

    public d(com.chartboost.sdk.Model.a aVar) {
        this.e = aVar;
        this.k = null;
        this.f = CBPreferences.getInstance().getOrientation();
        this.j = false;
    }

    public CBOrientation a() {
        return this.f;
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        this.d = aVar.a("assets");
        if (!this.d.b()) {
            return true;
        }
        a(CBImpressionError.INTERNAL);
        return false;
    }

    public void a(b bVar) {
        if (bVar.a()) {
            this.c.remove(bVar);
        }
        this.b.remove(bVar);
        if (this.b.isEmpty() && !b()) {
            a(CBImpressionError.INTERNAL);
        }
    }

    public boolean b() {
        if (!this.c.isEmpty()) {
            return false;
        }
        i();
        return true;
    }

    public CBImpressionError c() {
        Context c = Chartboost.sharedChartboost().c();
        if (c == null) {
            this.k = null;
            return CBImpressionError.NO_HOST_ACTIVITY;
        } else if (!this.i && !this.h) {
            return CBImpressionError.WRONG_ORIENTATION;
        } else {
            this.f = CBPreferences.getInstance().getOrientation();
            if ((this.f.isLandscape() && !this.i) || (this.f.isPortrait() && !this.h)) {
                this.f = this.f.rotate90();
            }
            this.k = a(c);
            if (this.k.a(c)) {
                return null;
            }
            this.k = null;
            return CBImpressionError.INTERNAL;
        }
    }

    public void d() {
        f();
        for (int i = 0; i < this.g.size(); i++) {
            a.removeCallbacks((Runnable) this.g.get(i));
        }
        this.g.clear();
    }

    public a e() {
        return this.k;
    }

    public void f() {
        if (this.k != null) {
            this.k.c();
        }
        this.k = null;
    }

    public com.chartboost.sdk.Libraries.e.a g() {
        return this.d;
    }

    public void b(b bVar) {
        this.b.add(bVar);
        this.c.add(bVar);
    }

    protected void a(CBImpressionError cBImpressionError) {
        this.e.a(cBImpressionError);
    }

    protected void h() {
        if (!this.j) {
            this.j = true;
            this.e.a();
        }
    }

    protected void i() {
        this.e.b();
    }

    public void a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        this.e.a(str, aVar);
    }

    public void a(boolean z, View view) {
        a(z, view, true);
    }

    public void a(final boolean z, final View view, boolean z2) {
        int i = 8;
        if (!z || view.getVisibility() != 0) {
            if (!z && view.getVisibility() == 8) {
                return;
            }
            if (z2) {
                Runnable anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ d c;

                    public void run() {
                        if (!z) {
                            view.setVisibility(8);
                            view.setClickable(false);
                        }
                        this.c.g.remove(this);
                    }
                };
                be.a(z, view, 510);
                this.g.add(anonymousClass1);
                a.postDelayed(anonymousClass1, 510);
                return;
            }
            if (z) {
                i = 0;
            }
            view.setVisibility(i);
            view.setClickable(z);
        }
    }

    protected void a(Runnable runnable, long j) {
        this.g.add(runnable);
        a.postDelayed(runnable, j);
    }

    public static int a(String str) {
        int i = 0;
        if (str != null) {
            try {
                if (str.startsWith("#") && str.length() == 5) {
                    StringBuilder stringBuilder = new StringBuilder(9);
                    stringBuilder.append("#");
                    for (int i2 = 0; i2 < str.length() - 1; i2++) {
                        stringBuilder.append(str.charAt(i2 + 1));
                        stringBuilder.append(str.charAt(i2 + 1));
                    }
                    str = stringBuilder.toString();
                }
                i = Color.parseColor(str);
            } catch (Throwable e) {
                CBLogging.d("CBViewProtocol", "error parsing color", e);
            }
        }
        return i;
    }

    public boolean j() {
        return false;
    }

    public void k() {
    }

    public void l() {
    }
}
