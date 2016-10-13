package com.vungle.publisher.display.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.widget.TextView;
import com.vungle.log.Logger;
import com.vungle.publisher.image.BitmapFactory;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class CountdownProgressView extends TextView {
    private int a;
    private int b;

    @Singleton
    /* compiled from: vungle */
    static class Factory {
        @Inject
        BitmapFactory a;

        Factory() {
        }
    }

    CountdownProgressView(Context context, BitmapFactory bitmapFactory) {
        super(context);
        setTextColor(-1);
        setTextSize(16.0f);
        setGravity(17);
        try {
            setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapFactory.getBitmap("vg_timer.png")));
        } catch (Throwable e) {
            Logger.d(Logger.AD_TAG, "error loading countdown progress bar background", e);
        }
    }

    final void setMax(int max) {
        if (max != this.a) {
            this.a = max;
            a();
        }
    }

    final void setProgress(int progress) {
        if (progress != this.b) {
            this.b = progress;
            a();
        }
    }

    private void a() {
        setText(Integer.toString(this.a - this.b));
        invalidate();
    }
}
