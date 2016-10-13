package com.vungle.publisher.net.http;

import android.os.SystemClock;
import com.appnana.android.giftcardrewards.model.Settings;
import com.vungle.log.Logger;
import com.vungle.publisher.cf;
import com.vungle.publisher.cg;
import com.vungle.publisher.ch;
import com.vungle.publisher.ci;
import com.vungle.publisher.cs;

/* compiled from: vungle */
public abstract class MaxRetryAgeHttpResponseHandler extends cg {
    private int a = Settings.MIN_NANAS_TO_ALERT_RATING;
    private int b = 300000;
    public int g = 100;
    public int h = 0;
    int i = 0;

    protected final void d(HttpTransaction httpTransaction, cf cfVar) {
        ci ciVar = httpTransaction.b;
        int i = ciVar.b;
        int i2 = (this.g <= 0 || i < this.g) ? 0 : 1;
        if (i2 == 0) {
            i2 = (this.i <= 0 || SystemClock.elapsedRealtime() - ciVar.a < ((long) this.i)) ? 0 : 1;
            if (i2 == 0) {
                int i3 = cfVar.b;
                if (cg.a(i3) || i3 == 601) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                if (i2 != 0) {
                    i2 = ciVar.c;
                    if (i3 == 408 || i3 == 603) {
                        i3 = 0;
                    } else {
                        i3 = 1;
                    }
                    if (i3 == 0) {
                        i2 = ciVar.c - 1;
                        ciVar.c = i2;
                        if (i2 < 0) {
                            Logger.d(Logger.NETWORK_TAG, "Attempted to decrement softRetryCount < 0");
                            ciVar.c = 0;
                        }
                        i2 = ciVar.c;
                    }
                    if (this.h <= 0 || r0 < this.h) {
                        i2 = 0;
                    } else {
                        i2 = 1;
                    }
                    if (i2 == 0) {
                        i2 = cs.a(i, this.a, this.b);
                        Logger.d(Logger.NETWORK_TAG, "Retrying " + httpTransaction + " in " + (i2 / 1000) + " seconds");
                        this.f.a(new ch(httpTransaction), httpTransaction.c, (long) i2);
                        return;
                    }
                }
            }
        }
        super.d(httpTransaction, cfVar);
    }
}
