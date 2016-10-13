package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout.LayoutParams;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.impl.at.b;
import com.facebook.internal.NativeProtocol;

public class ao extends an implements b {
    private ar b;

    public ao(Context context) {
        super(context);
        this.b = new ar(context);
        addView(this.b, new LayoutParams(-1, -1));
    }

    public void a(a aVar, int i) {
        a a = aVar.a("assets").a(CBPreferences.getInstance().getOrientation().isPortrait() ? "portrait" : "landscape");
        if (a.c()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            bb.a().a(a.e(NativeProtocol.WEB_DIALOG_URL), a.e("checksum"), null, this.b, bundle);
        }
    }

    public int a() {
        return CBUtility.a(110, getContext());
    }
}
