package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.CBOrientation.Difference;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.d;
import com.facebook.BuildConfig;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class at extends d {
    private List<com.chartboost.sdk.Libraries.e.a> j = new ArrayList();
    private i k = new i(this);
    private i l = new i(this);
    private i m = new i(this);
    private Map<String, i> n;

    public interface b {
        int a();

        void a(com.chartboost.sdk.Libraries.e.a aVar, int i);
    }

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Difference.values().length];

        static {
            try {
                a[Difference.ANGLE_90.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Difference.ANGLE_180.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Difference.ANGLE_270.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Difference.ANGLE_0.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public class a extends com.chartboost.sdk.d.a {
        final /* synthetic */ at c;
        private ImageView d;
        private ImageView e;
        private FrameLayout f;
        private bl g;
        private bp h;
        private bp i;
        private a j;

        public class a extends ArrayAdapter<com.chartboost.sdk.Libraries.e.a> {
            final /* synthetic */ a a;
            private Context b;

            public /* synthetic */ Object getItem(int x0) {
                return a(x0);
            }

            public a(a aVar, Context context) {
                this.a = aVar;
                super(context, 0, aVar.c.j);
                this.b = context;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                Difference forcedOrientationDifference = CBPreferences.getInstance().getForcedOrientationDifference();
                if (forcedOrientationDifference.isReverse()) {
                    position = (getCount() - 1) - position;
                }
                final com.chartboost.sdk.Libraries.e.a a = a(position);
                com.chartboost.sdk.Libraries.e.a a2 = a.a("type");
                b bVar = null;
                if (convertView == null) {
                    if (a2.equals("featured")) {
                        bVar = new ao(this.b);
                    } else if (a2.equals("regular")) {
                        bVar = new ap(this.b);
                    } else if (a2.equals("webview")) {
                        bVar = new aq(this.b);
                    }
                    convertView = new bp(this.b, (View) bVar);
                } else {
                    bp convertView2 = (bp) convertView;
                    bVar = (b) convertView2.a();
                }
                bVar.a(a, position);
                an anVar = (an) bVar;
                if (forcedOrientationDifference.isOdd()) {
                    convertView.setLayoutParams(new LayoutParams(bVar.a(), -1));
                } else {
                    convertView.setLayoutParams(new LayoutParams(-1, bVar.a()));
                }
                OnClickListener anonymousClass1 = new OnClickListener(this) {
                    final /* synthetic */ a c;

                    public void onClick(View v) {
                        String e = a.e("deep-link");
                        if (TextUtils.isEmpty(e)) {
                            e = a.e(ShareConstants.WEB_DIALOG_PARAM_LINK);
                        }
                        com.chartboost.sdk.Tracking.a.a("more-apps", a.e("location"), a.e("ad_id"), position);
                        this.c.a.c.a(e, a);
                    }
                };
                anVar.a = anonymousClass1;
                anVar.setOnClickListener(anonymousClass1);
                if (bVar instanceof ap) {
                    ((ap) bVar).b.setOnClickListener(anonymousClass1);
                }
                return convertView;
            }

            public int getCount() {
                return this.a.c.j.size();
            }

            public com.chartboost.sdk.Libraries.e.a a(int i) {
                return (com.chartboost.sdk.Libraries.e.a) this.a.c.j.get(i);
            }
        }

        private a(final at atVar, Context context) {
            this.c = atVar;
            super(atVar, context);
            setBackgroundColor(-1842205);
            this.f = new FrameLayout(context);
            this.e = new ImageView(context);
            this.d = new ImageView(context);
            this.g = new bl(context, CBPreferences.getInstance().getForcedOrientationDifference().isOdd() ? 0 : 1);
            this.g.b().setBackgroundColor(-1842205);
            this.f.setFocusable(false);
            this.e.setFocusable(false);
            this.d.setFocusable(false);
            this.d.setClickable(true);
            this.h = new bp(context, this.d);
            this.i = new bp(context, this.f);
            addView(this.i);
            this.f.addView(this.e);
            addView(this.h);
            a(this.e);
            a(this.f);
            a(this.d);
            a(this.i);
            a(this.h);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View v) {
                    this.b.c.h();
                }
            });
            this.j = new a(this, context);
        }

        private void a(View view) {
            int i = 200;
            if (200 == getId()) {
                i = 201;
            }
            int i2 = i;
            View findViewById = findViewById(i);
            while (findViewById != null) {
                i2++;
                findViewById = findViewById(i2);
            }
            view.setId(i2);
            view.setSaveEnabled(false);
        }

        protected void a(int i, int i2) {
            int i3 = 50;
            if (this.g.a() != null) {
                removeView(this.g.a());
            }
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
            ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            ViewGroup.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            ViewGroup.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            final Difference forcedOrientationDifference = CBPreferences.getInstance().getForcedOrientationDifference();
            layoutParams2.width = forcedOrientationDifference.isOdd() ? CBUtility.a(50, getContext()) : -1;
            layoutParams2.height = forcedOrientationDifference.isOdd() ? -1 : CBUtility.a(50, getContext());
            switch (AnonymousClass1.a[forcedOrientationDifference.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    layoutParams2.addRule(11);
                    break;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    layoutParams2.addRule(12);
                    break;
            }
            Drawable bitmapDrawable = new BitmapDrawable(this.c.m.f());
            bitmapDrawable.setTileModeX(TileMode.REPEAT);
            bitmapDrawable.setTileModeY(TileMode.CLAMP);
            this.f.setBackgroundDrawable(bitmapDrawable);
            if (this.c.l != null) {
                this.e.setImageBitmap(this.c.l.f());
                layoutParams.width = CBUtility.a(this.c.l.b(), getContext());
                layoutParams.height = CBUtility.a(Math.min(50, this.c.l.c()), getContext());
            }
            this.d.setImageBitmap(this.c.k.f());
            layoutParams3.width = CBUtility.a(forcedOrientationDifference.isOdd() ? 30 : 50, getContext());
            if (!forcedOrientationDifference.isOdd()) {
                i3 = 30;
            }
            layoutParams3.height = CBUtility.a(i3, getContext());
            switch (AnonymousClass1.a[forcedOrientationDifference.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    layoutParams3.bottomMargin = CBUtility.a(10, getContext());
                    layoutParams3.rightMargin = CBUtility.a(10, getContext());
                    layoutParams3.addRule(11);
                    layoutParams3.addRule(12);
                    break;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    layoutParams3.leftMargin = CBUtility.a(10, getContext());
                    layoutParams3.bottomMargin = CBUtility.a(10, getContext());
                    layoutParams3.addRule(12);
                    break;
                case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                    layoutParams3.topMargin = CBUtility.a(10, getContext());
                    layoutParams3.leftMargin = CBUtility.a(10, getContext());
                    break;
                default:
                    layoutParams3.rightMargin = CBUtility.a(10, getContext());
                    layoutParams3.topMargin = CBUtility.a(10, getContext());
                    layoutParams3.addRule(11);
                    break;
            }
            layoutParams4.width = -1;
            layoutParams4.height = -1;
            switch (AnonymousClass1.a[forcedOrientationDifference.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    layoutParams4.addRule(0, this.i.getId());
                    break;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    layoutParams4.addRule(2, this.i.getId());
                    break;
                case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                    layoutParams4.addRule(1, this.i.getId());
                    break;
                default:
                    layoutParams4.addRule(3, this.i.getId());
                    break;
            }
            this.g.a(forcedOrientationDifference.isOdd() ? 0 : 1);
            a(this.g.a());
            this.g.a(this.j);
            addView(this.g.a(), layoutParams4);
            if (forcedOrientationDifference == Difference.ANGLE_180) {
                this.g.b().setGravity(80);
            } else if (forcedOrientationDifference == Difference.ANGLE_90) {
                this.g.b().setGravity(5);
            } else {
                this.g.b().setGravity(0);
            }
            this.i.setLayoutParams(layoutParams2);
            this.e.setLayoutParams(layoutParams);
            this.e.setScaleType(ScaleType.FIT_CENTER);
            this.h.setLayoutParams(layoutParams3);
            this.d.setScaleType(ScaleType.FIT_CENTER);
            post(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (this.b.g != null) {
                        this.b.a = true;
                        this.b.requestLayout();
                        this.b.g.a().requestLayout();
                        this.b.g.b().requestLayout();
                        this.b.a = false;
                        if (forcedOrientationDifference == Difference.ANGLE_180 || forcedOrientationDifference == Difference.ANGLE_90) {
                            this.b.g.c();
                        }
                    }
                }
            });
        }

        public void c() {
            super.c();
            this.d = null;
            this.e = null;
            this.g = null;
        }
    }

    public at(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
    }

    protected com.chartboost.sdk.d.a a(Context context) {
        return new a(context);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        com.chartboost.sdk.Libraries.e.a a = aVar.a("cells");
        if (a.b()) {
            a(CBImpressionError.INTERNAL);
            return false;
        }
        this.n = new HashMap();
        for (int i = 0; i < a.n(); i++) {
            com.chartboost.sdk.Libraries.e.a c = a.c(i);
            this.j.add(c);
            com.chartboost.sdk.Libraries.e.a a2 = c.a("type");
            i iVar;
            if (a2.equals("regular")) {
                c = c.a("assets");
                if (c.c()) {
                    iVar = new i(this);
                    this.n.put(BuildConfig.VERSION_NAME + i, iVar);
                    iVar.a(c, "icon", new Bundle());
                }
            } else if (a2.equals("featured")) {
                c = c.a("assets");
                if (c.c()) {
                    iVar = new i(this);
                    this.n.put(String.format(Locale.US, "%d-%s", new Object[]{Integer.valueOf(i), "portrait"}), iVar);
                    iVar.a(c, "portrait", new Bundle());
                    iVar = new i(this);
                    this.n.put(String.format(Locale.US, "%d-%s", new Object[]{Integer.valueOf(i), "landscape"}), iVar);
                    iVar.a(c, "landscape", new Bundle());
                }
            } else if (a2.equals("webview")) {
            }
        }
        this.k.a("close");
        this.l.a("header-center");
        this.m.a("header-tile");
        return true;
    }

    public void d() {
        super.d();
        this.j = null;
        for (Entry value : this.n.entrySet()) {
            ((i) value.getValue()).d();
        }
        this.n.clear();
        this.k.d();
        this.l.d();
        this.m.d();
        this.k = null;
        this.m = null;
        this.l = null;
    }
}
