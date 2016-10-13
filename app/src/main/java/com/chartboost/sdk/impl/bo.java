package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Handler;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.CBOrientation.Difference;

public final class bo extends bk {
    private Handler a;
    private float b;
    private long c;
    private Paint d;
    private Paint e;
    private Path f;
    private Path g;
    private RectF h;
    private RectF i;
    private Runnable j = new Runnable(this) {
        final /* synthetic */ bo a;

        {
            this.a = r1;
        }

        public void run() {
            Difference forcedOrientationDifference = CBPreferences.getInstance().getForcedOrientationDifference();
            float f = this.a.getContext().getResources().getDisplayMetrics().density;
            bo.a(this.a, (60.0f * f) * 0.016666668f);
            float width = (forcedOrientationDifference.isOdd() ? (float) this.a.getWidth() : (float) this.a.getHeight()) - (f * 9.0f);
            if (this.a.b > width) {
                bo.b(this.a, width * 2.0f);
            }
            if (this.a.getWindowVisibility() == 0) {
                this.a.invalidate();
            }
        }
    };

    static /* synthetic */ float a(bo boVar, float f) {
        float f2 = boVar.b + f;
        boVar.b = f2;
        return f2;
    }

    static /* synthetic */ float b(bo boVar, float f) {
        float f2 = boVar.b - f;
        boVar.b = f2;
        return f2;
    }

    public bo(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        this.b = 0.0f;
        this.a = new Handler();
        this.c = (long) (((double) System.nanoTime()) / 1000000.0d);
        this.d = new Paint();
        this.d.setColor(-1);
        this.d.setStyle(Style.STROKE);
        this.d.setStrokeWidth(f * 3.0f);
        this.d.setAntiAlias(true);
        this.e = new Paint();
        this.e.setColor(-1);
        this.e.setStyle(Style.FILL);
        this.e.setAntiAlias(true);
        this.f = new Path();
        this.g = new Path();
        this.i = new RectF();
        this.h = new RectF();
    }

    protected void a(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        Difference forcedOrientationDifference = CBPreferences.getInstance().getForcedOrientationDifference();
        canvas.save();
        if (forcedOrientationDifference.isReverse()) {
            canvas.rotate(180.0f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
        }
        this.h.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.h.inset(1.5f * f, 1.5f * f);
        float width = ((float) (forcedOrientationDifference.isOdd() ? getWidth() : getHeight())) / 2.0f;
        canvas.drawRoundRect(this.h, width, width, this.d);
        this.i.set(this.h);
        this.i.inset(3.0f * f, f * 3.0f);
        width = (forcedOrientationDifference.isOdd() ? this.i.width() : this.i.height()) / 2.0f;
        this.f.reset();
        this.f.addRoundRect(this.i, width, width, Direction.CW);
        width = forcedOrientationDifference.isOdd() ? this.i.width() : this.i.height();
        this.g.reset();
        if (forcedOrientationDifference.isOdd()) {
            this.g.moveTo(width, 0.0f);
            this.g.lineTo(width, width);
            this.g.lineTo(0.0f, width * 2.0f);
            this.g.lineTo(0.0f, width);
        } else {
            this.g.moveTo(0.0f, width);
            this.g.lineTo(width, width);
            this.g.lineTo(width * 2.0f, 0.0f);
            this.g.lineTo(width, 0.0f);
        }
        this.g.close();
        canvas.save();
        Object obj = 1;
        try {
            canvas.clipPath(this.f);
        } catch (UnsupportedOperationException e) {
            obj = null;
        }
        if (obj != null) {
            f = (-width) + this.b;
            while (true) {
                if (f >= (forcedOrientationDifference.isOdd() ? this.i.height() : this.i.width()) + width) {
                    break;
                }
                float f2 = (forcedOrientationDifference.isOdd() ? this.i.top : this.i.left) + f;
                canvas.save();
                if (forcedOrientationDifference.isOdd()) {
                    canvas.translate(this.i.left, f2);
                } else {
                    canvas.translate(f2, this.i.top);
                }
                canvas.drawPath(this.g, this.e);
                canvas.restore();
                f += 2.0f * width;
            }
        }
        canvas.restore();
        canvas.restore();
        long max = Math.max(0, 16 - (((long) (((double) System.nanoTime()) / 1000000.0d)) - this.c));
        this.a.removeCallbacks(this.j);
        this.a.postDelayed(this.j, max);
    }

    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.a.removeCallbacks(this.j);
        if (visibility == 0) {
            this.a.post(this.j);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.removeCallbacks(this.j);
        this.a.post(this.j);
    }

    protected void onDetachedFromWindow() {
        this.a.removeCallbacks(this.j);
        super.onDetachedFromWindow();
    }
}
