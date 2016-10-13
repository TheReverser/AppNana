package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.impl.at.b;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;

public class ap extends an implements b {
    public au b;
    private ar c;
    private TextView d;

    public ap(Context context) {
        super(context);
        this.c = new ar(context);
        this.b = new au(context);
        this.d = new TextView(context);
        this.d.setTypeface(null, 1);
        this.d.setTextSize(2, 16.0f);
        this.d.setShadowLayer(1.0f, 1.0f, 1.0f, -1);
        this.d.setBackgroundColor(0);
        this.d.setTextColor(-16777216);
        this.d.setEllipsize(TruncateAt.END);
        setBackgroundColor(-3355444);
        setFocusable(false);
        addView(this.c);
        addView(this.d);
        addView(this.b);
    }

    public void a(a aVar, int i) {
        this.d.setText(aVar.a(ShareConstants.WEB_DIALOG_PARAM_NAME).d("Unknown App"));
        CharSequence e = aVar.e("deep-text");
        if (TextUtils.isEmpty(e)) {
            this.b.setText(aVar.a("text").d("VIEW"));
        } else {
            this.b.setText(e);
        }
        a a = aVar.a("assets").a("icon");
        if (a.c()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            bb.a().a(a.e(NativeProtocol.WEB_DIALOG_URL), a.e("checksum"), null, this.c, bundle);
        }
        b();
    }

    protected void b() {
        int a = CBUtility.a(50, getContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        int a2 = CBUtility.a(10, getContext());
        layoutParams.setMargins(a2, a2, a2, a2);
        layoutParams2.setMargins(a2, a2, a2, a2);
        layoutParams3.setMargins(a2, a2, a2, a2);
        layoutParams2.weight = 1.0f;
        this.d.setGravity(16);
        layoutParams3.gravity = 16;
        this.c.setLayoutParams(layoutParams);
        this.c.setScaleType(ScaleType.FIT_CENTER);
        this.d.setLayoutParams(layoutParams2);
        this.b.setLayoutParams(layoutParams3);
    }

    public int a() {
        return CBUtility.a(70, getContext());
    }
}
