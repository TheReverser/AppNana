package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.chartboost.sdk.impl.at.b;

public abstract class an extends LinearLayout implements b {
    protected OnClickListener a = null;
    private Rect b = new Rect();
    private Paint c = null;
    private Paint d = null;
    private Rect e = null;

    public an(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        this.b.set(0, 0, getWidth(), a());
        a(canvas, this.b);
        b(canvas, this.b);
    }

    private void a(Canvas canvas, Rect rect) {
        if (this.d == null) {
            this.d = new Paint();
            this.d.setStyle(Style.FILL);
            this.d.setAntiAlias(true);
        }
        if (!(this.e != null && rect.top == this.e.top && rect.bottom == this.e.bottom)) {
            if (this.e == null) {
                this.e = new Rect();
            }
            this.e.set(rect);
            this.d.setShader(new LinearGradient(0.0f, (float) rect.top, 0.0f, (float) rect.bottom, -1447447, -2302756, TileMode.CLAMP));
        }
        canvas.drawRect(rect, this.d);
    }

    private void b(Canvas canvas, Rect rect) {
        if (this.c == null) {
            this.c = new Paint();
            this.c.setStyle(Style.FILL);
            this.c.setAntiAlias(true);
        }
        this.c.setColor(-723724);
        canvas.drawRect((float) rect.left, (float) rect.top, (float) rect.right, ((float) rect.top) + 1.0f, this.c);
        this.c.setColor(-3355444);
        canvas.drawRect((float) rect.left, ((float) rect.bottom) - 1.0f, (float) rect.right, (float) rect.bottom, this.c);
    }
}
