package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flurry.sdk.fv;
import com.flurry.sdk.ib;
import com.flurry.sdk.jn;

public final class InstallReceiver extends BroadcastReceiver {
    static final String a = InstallReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        ib.a(4, a, "Received an Install nofication of " + intent.getAction());
        String string = intent.getExtras().getString("referrer");
        ib.a(4, a, "Received an Install referrer of " + string);
        if (string == null || !"com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            ib.a(5, a, "referrer is null");
            return;
        }
        if (!string.contains("=")) {
            ib.a(4, a, "referrer is before decoding: " + string);
            string = jn.d(string);
            ib.a(4, a, "referrer is: " + string);
        }
        new fv(context).a(string);
    }
}
