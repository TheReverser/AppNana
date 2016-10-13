package com.chartboost.sdk;

import com.chartboost.sdk.Chartboost.CBAPIResponseCallback;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.ay.c;
import com.chartboost.sdk.impl.i;
import com.facebook.share.internal.ShareConstants;

final class e {
    private static e a = null;

    private static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    private e() {
    }

    protected static void a(String str, CBAPIResponseCallback cBAPIResponseCallback) {
        av avVar = new av("api/get", "https://www.chartboost.com", null);
        avVar.a("raw", (Object) Integer.valueOf(1));
        avVar.a("cache", (Object) Integer.valueOf(1));
        if (str != null) {
            avVar.a("location", (Object) str);
        }
        a().a(avVar, cBAPIResponseCallback);
    }

    protected static void a(String str, int i, CBAPIResponseCallback cBAPIResponseCallback) {
        av avVar = new av("api/get_batch", "https://www.chartboost.com", null);
        avVar.a("raw", (Object) Integer.valueOf(1));
        avVar.a("cache", (Object) Integer.valueOf(1));
        if (str != null) {
            avVar.a("location", (Object) str);
        }
        if (i > 10) {
            i = 10;
        }
        avVar.a("amount", (Object) Integer.valueOf(i));
        a().a(avVar, cBAPIResponseCallback);
    }

    protected static void b(String str, CBAPIResponseCallback cBAPIResponseCallback) {
        av avVar = new av("api/show", "https://www.chartboost.com", null);
        avVar.a("ad_id", (Object) str);
        a().a(avVar, cBAPIResponseCallback);
    }

    protected static void a(CBAPIResponseCallback cBAPIResponseCallback) {
        av avVar = new av("api/more", "https://www.chartboost.com", null);
        avVar.a("format", (Object) ShareConstants.WEB_DIALOG_PARAM_DATA);
        a().a(avVar, cBAPIResponseCallback);
    }

    private void a(av avVar, final CBAPIResponseCallback cBAPIResponseCallback) {
        avVar.b(Chartboost.sharedChartboost().getValidContext());
        avVar.a(new c(this) {
            final /* synthetic */ e b;

            public void a(a aVar, ay ayVar, i iVar) {
                if (cBAPIResponseCallback != null) {
                    cBAPIResponseCallback.onSuccess(aVar.e());
                }
            }

            public void a(ay ayVar, CBError cBError, i iVar) {
                if (cBAPIResponseCallback != null) {
                    cBAPIResponseCallback.onFailure(cBError.c());
                }
            }
        });
    }
}
