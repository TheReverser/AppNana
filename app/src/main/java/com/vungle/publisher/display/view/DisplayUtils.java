package com.vungle.publisher.display.view;

import android.content.Context;
import android.util.TypedValue;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class DisplayUtils {
    @Inject
    Context a;

    public final float a(int i) {
        return TypedValue.applyDimension(1, (float) i, this.a.getResources().getDisplayMetrics());
    }

    DisplayUtils() {
    }
}
