package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public final class bl {
    private ScrollView a;
    private HorizontalScrollView b;
    private ViewGroup c;
    private LinearLayout d;
    private BaseAdapter e;
    private List<List<View>> f;
    private List<List<View>> g;
    private List<Integer> h;
    private int i;
    private DataSetObserver j = new DataSetObserver(this) {
        final /* synthetic */ bl a;

        {
            this.a = r1;
        }

        public void onChanged() {
            int i;
            int childCount = this.a.d.getChildCount();
            for (i = 0; i < childCount; i++) {
                List list = (List) this.a.g.get(((Integer) this.a.h.get(i)).intValue());
                List list2 = (List) this.a.f.get(((Integer) this.a.h.get(i)).intValue());
                View childAt = this.a.d.getChildAt(i);
                list.remove(childAt);
                list2.add(childAt);
            }
            this.a.h.clear();
            this.a.d.removeAllViews();
            int count = this.a.e.getCount();
            for (childCount = 0; childCount < count; childCount++) {
                LayoutParams layoutParams;
                i = this.a.e.getItemViewType(childCount);
                list = (List) this.a.g.get(i);
                list2 = (List) this.a.f.get(i);
                this.a.h.add(Integer.valueOf(i));
                View view = null;
                if (!list2.isEmpty()) {
                    view = (View) list2.get(0);
                    list2.remove(0);
                }
                View view2 = this.a.e.getView(childCount, view, this.a.d);
                list.add(view2);
                if (this.a.i == 0) {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                } else {
                    layoutParams = new LinearLayout.LayoutParams(-1, -2);
                }
                if (childCount < count - 1) {
                    layoutParams.setMargins(0, 0, 0, 1);
                }
                this.a.d.addView(view2, layoutParams);
            }
            this.a.d.requestLayout();
        }
    };

    public bl(Context context, int i) {
        this.d = new LinearLayout(context);
        this.i = i;
        this.d.setOrientation(i);
        if (i == 0) {
            this.c = a(context);
        } else {
            this.c = b(context);
        }
        this.c.addView(this.d);
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.d.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ bl a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent ev) {
                try {
                    View currentFocus = ((Activity) this.a.d.getContext()).getCurrentFocus();
                    if (currentFocus != null) {
                        ((InputMethodManager) this.a.d.getContext().getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getApplicationWindowToken(), 0);
                    }
                } catch (Exception e) {
                }
                return false;
            }
        });
    }

    private HorizontalScrollView a(Context context) {
        if (this.b == null) {
            this.b = new HorizontalScrollView(context);
            this.b.setFillViewport(true);
        }
        return this.b;
    }

    private ScrollView b(Context context) {
        if (this.a == null) {
            this.a = new ScrollView(context);
            this.a.setFillViewport(true);
        }
        return this.a;
    }

    public ViewGroup a() {
        return this.c;
    }

    public void a(BaseAdapter baseAdapter) {
        if (this.e != null) {
            this.e.unregisterDataSetObserver(this.j);
        }
        this.e = baseAdapter;
        this.e.registerDataSetObserver(this.j);
        this.d.removeAllViews();
        this.f.clear();
        this.g.clear();
        for (int i = 0; i < this.e.getViewTypeCount(); i++) {
            this.f.add(new ArrayList());
            this.g.add(new ArrayList());
        }
        this.h.clear();
        this.e.notifyDataSetChanged();
    }

    public void a(int i) {
        if (i != this.i) {
            this.i = i;
            this.d.setOrientation(i);
            this.c.removeView(this.d);
            if (i == 0) {
                this.c = a(d());
            } else {
                this.c = b(d());
            }
            this.c.addView(this.d);
        }
    }

    private Context d() {
        return this.d.getContext();
    }

    public LinearLayout b() {
        return this.d;
    }

    public void c() {
        if (this.c == this.a && this.a != null) {
            this.a.fullScroll(130);
        } else if (this.c == this.b && this.b != null) {
            this.b.fullScroll(66);
        }
    }
}
