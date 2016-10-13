package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.widget.Button;

public final class au extends Button {
    private Path a;
    private Path b;
    private Path c;
    private RectF d;
    private Paint e;
    private Paint f;
    private Shader g;
    private Shader h;
    private int i;

    public au(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        setTextSize(2, 13.0f);
        setShadowLayer(1.0f * f, 1.0f * f, 1.0f * f, -16757901);
        setTypeface(null, 1);
        setTextColor(-1);
        setClickable(true);
        setGravity(17);
        setPadding(Math.round(12.0f * f), Math.round(5.0f * f), Math.round(12.0f * f), Math.round(f * 5.0f));
        this.a = new Path();
        this.b = new Path();
        this.c = new Path();
        this.d = new RectF();
        this.e = new Paint();
        this.e.setStyle(Style.STROKE);
        this.e.setColor(-4496384);
        this.e.setAntiAlias(true);
        this.i = -1;
        this.f = new Paint();
        this.f.setStyle(Style.FILL);
        this.f.setAntiAlias(true);
        setBackgroundDrawable(new Drawable(this) {
            boolean a = false;
            final /* synthetic */ au b;

            {
                this.b = r2;
            }

            public void draw(Canvas canvas) {
                int i = 0;
                float f = this.b.getContext().getResources().getDisplayMetrics().density;
                int[] state = getState();
                int i2 = 0;
                while (i < state.length) {
                    if (state[i] == 16842919) {
                        i2 = 1;
                    }
                    i++;
                }
                float f2 = 6.0f * f;
                this.b.a.reset();
                this.b.d.set(getBounds());
                this.b.a.addRoundRect(this.b.d, f2, f2, Direction.CW);
                this.b.a();
                this.b.f.setShader(i2 != 0 ? this.b.h : this.b.g);
                canvas.drawPath(this.b.a, this.b.f);
                float f3 = 1.0f * f;
                this.b.b.reset();
                this.b.d.inset(f3 / 2.0f, f3 / 2.0f);
                this.b.b.addRoundRect(this.b.d, f2, f2, Direction.CW);
                this.b.c.reset();
                this.b.d.offset(0.0f, f3 / 2.0f);
                this.b.c.addRoundRect(this.b.d, f2, f2, Direction.CW);
                if (i2 == 0) {
                    this.b.e.setColor(-1);
                    canvas.drawPath(this.b.c, this.b.e);
                }
                this.b.e.setColor(-4496384);
                canvas.drawPath(this.b.b, this.b.e);
            }

            public void setAlpha(int alpha) {
                this.b.e.setAlpha(alpha);
                this.b.f.setAlpha(alpha);
            }

            public void setColorFilter(ColorFilter cf) {
                this.b.e.setColorFilter(cf);
                this.b.f.setColorFilter(cf);
            }

            public int getOpacity() {
                return 1;
            }

            protected boolean onStateChange(int[] states) {
                boolean z = false;
                for (int i : states) {
                    if (i == 16842919) {
                        z = true;
                    }
                }
                if (this.a == z) {
                    return false;
                }
                this.a = z;
                invalidateSelf();
                return true;
            }

            public boolean isStateful() {
                return true;
            }
        });
    }

    private void a() {
        int height = getHeight();
        if (height != this.i) {
            this.i = height;
            float f = 0.0f;
            float f2 = 0.0f;
            this.g = new LinearGradient(0.0f, f, f2, (float) getHeight(), new int[]{-81366, -89600, -97280}, null, TileMode.CLAMP);
            f = 0.0f;
            f2 = 0.0f;
            this.h = new LinearGradient(0.0f, f, f2, (float) getHeight(), new int[]{-97280, -89600, -81366}, null, TileMode.CLAMP);
        }
    }
}
