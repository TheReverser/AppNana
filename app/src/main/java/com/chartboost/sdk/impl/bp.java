package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Region.Op;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.CBOrientation.Difference;

public final class bp extends FrameLayout {
    private Matrix a;
    private Matrix b;
    private float[] c;
    private View d;
    private RelativeLayout e;

    public bp(Context context) {
        super(context);
        this.a = new Matrix();
        this.b = new Matrix();
        this.c = new float[2];
        this.e = new RelativeLayout(context);
        addView(this.e, new LayoutParams(-1, -1));
        this.d = this.e;
    }

    public void a(View view) {
        a(view, new RelativeLayout.LayoutParams(-2, -2));
    }

    public void a(View view, RelativeLayout.LayoutParams layoutParams) {
        if (this.e != null) {
            this.e.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("cannot call addViewToContainer() on CBRotatableContainer that was set up with a default view");
    }

    public bp(Context context, View view) {
        super(context);
        this.a = new Matrix();
        this.b = new Matrix();
        this.c = new float[2];
        addView(view, new LayoutParams(-1, -1));
        this.d = view;
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        int i;
        super.onSizeChanged(w, h, oldw, oldh);
        Difference forcedOrientationDifference = CBPreferences.getInstance().getForcedOrientationDifference();
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        if (forcedOrientationDifference.isOdd()) {
            i = h;
        } else {
            i = w;
        }
        layoutParams.width = i;
        if (!forcedOrientationDifference.isOdd()) {
            w = h;
        }
        layoutParams.height = w;
        this.d.setLayoutParams(layoutParams);
        this.d.measure(MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824), MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
        this.d.layout(0, 0, layoutParams.width, layoutParams.height);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (CBPreferences.getInstance().getForcedOrientationDifference().isOdd()) {
            super.onMeasure(heightMeasureSpec, widthMeasureSpec);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void dispatchDraw(Canvas canvas) {
        Difference forcedOrientationDifference = CBPreferences.getInstance().getForcedOrientationDifference();
        int asInt = forcedOrientationDifference.getAsInt();
        if (forcedOrientationDifference == Difference.ANGLE_0) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), Op.REPLACE);
        try {
            View view = (ViewGroup) getParent();
            try {
                View view2 = (ViewGroup) view.getParent();
                if ((view2 instanceof ScrollView) || (view2 instanceof HorizontalScrollView)) {
                    view = view2;
                }
            } catch (Exception e) {
            }
            int left = getLeft() - view.getScrollX();
            int top = getTop() - view.getScrollY();
            canvas.clipRect((float) (0 - left), (float) (0 - top), (float) (view.getWidth() - left), (float) (view.getHeight() - top), Op.INTERSECT);
        } catch (Exception e2) {
        }
        canvas.translate(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
        canvas.rotate((float) asInt);
        if (forcedOrientationDifference.isOdd()) {
            canvas.translate(((float) (-getHeight())) / 2.0f, ((float) (-getWidth())) / 2.0f);
        } else {
            canvas.translate(((float) (-getWidth())) / 2.0f, ((float) (-getHeight())) / 2.0f);
        }
        this.a = canvas.getMatrix();
        this.a.invert(this.b);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (CBPreferences.getInstance().getForcedOrientationDifference() == Difference.ANGLE_0) {
            return super.dispatchTouchEvent(event);
        }
        float[] fArr = this.c;
        fArr[0] = event.getRawX();
        fArr[1] = event.getRawY();
        this.b.mapPoints(fArr);
        event.setLocation(fArr[0], fArr[1]);
        return super.dispatchTouchEvent(event);
    }

    public View a() {
        return this.d;
    }
}
