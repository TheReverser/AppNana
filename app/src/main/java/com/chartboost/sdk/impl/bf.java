package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;

public final class bf extends View {
    private boolean a = false;

    public bf(Context context) {
        super(context);
        setFocusable(false);
        setBackgroundColor(-1442840576);
    }

    public void a() {
        if (!this.a) {
            be.a(true, this);
            this.a = true;
        }
    }
}
