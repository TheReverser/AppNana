package com.jirbo.adcolony;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;

public class AdColonyOverlay extends ADCVideo {
    Rect U = new Rect();
    int V = 0;

    public void onConfigurationChanged(Configuration new_config) {
        super.onConfigurationChanged(new_config);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        this.t = defaultDisplay.getWidth();
        this.u = defaultDisplay.getHeight();
        a.B = true;
        final View view = new View(this);
        view.setBackgroundColor(-16777216);
        if (d && this.F.Q) {
            this.O.setLayoutParams(new LayoutParams(this.t, this.u - this.F.m, 17));
            this.N.addView(view, new LayoutParams(this.t, this.u, 17));
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ AdColonyOverlay b;

                public void run() {
                    this.b.N.removeView(view);
                }
            }, 1500);
        }
        this.F.a();
    }
}
