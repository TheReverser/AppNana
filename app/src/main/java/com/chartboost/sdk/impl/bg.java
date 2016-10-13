package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import com.chartboost.sdk.Libraries.i;

public class bg extends ImageView {
    private i a = null;

    public bg(Context context) {
        super(context);
    }

    public void a(i iVar) {
        if (this.a != iVar) {
            this.a = iVar;
            setImageDrawable(new BitmapDrawable(iVar.f()));
        }
    }

    public void setImageBitmap(Bitmap adImage) {
        this.a = null;
        setImageDrawable(new BitmapDrawable(adImage));
    }
}
