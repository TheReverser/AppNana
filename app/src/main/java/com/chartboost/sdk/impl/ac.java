package com.chartboost.sdk.impl;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.Model.b;
import com.facebook.share.internal.ShareConstants;
import org.json.JSONArray;

public class ac extends ab {
    private static ac d;

    private ac() {
    }

    public static ac f() {
        if (d == null) {
            d = new ac();
        }
        return d;
    }

    protected a a(String str, boolean z) {
        return new a(c.REWARDED_VIDEO, z, str, false);
    }

    protected av d(a aVar) {
        av avVar = new av("/reward/get", null, "main");
        avVar.a(k.a.HIGH);
        avVar.a(b.b);
        bd a = bd.a();
        JSONArray jSONArray = new JSONArray();
        String[] c = a.c();
        if (c != null) {
            for (Object put : c) {
                jSONArray.put(put);
            }
        }
        return avVar;
    }

    protected void h(a aVar) {
    }

    public void f(a aVar) {
        if (b(aVar.a, aVar)) {
            super.f(aVar);
        } else {
            a(aVar, CBImpressionError.INTERNAL);
        }
    }

    protected void g(final a aVar) {
        e.a a = aVar.a.a("ux").a("pre-popup");
        if (a.c() && a.a(ShareConstants.WEB_DIALOG_PARAM_TITLE).d() && a.a("text").d() && a.a("confirm").d() && a.a("cancel").d()) {
            Builder builder = new Builder(this.a.getContext());
            builder.setTitle(a.e(ShareConstants.WEB_DIALOG_PARAM_TITLE));
            builder.setMessage(a.e("text")).setCancelable(false).setPositiveButton(a.e("confirm"), new OnClickListener(this) {
                final /* synthetic */ ac b;

                public void onClick(DialogInterface dialog, int id) {
                    super.g(aVar);
                }
            }).setNegativeButton(a.e("cancel"), new OnClickListener(this) {
                final /* synthetic */ ac b;

                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    this.b.a(aVar, CBImpressionError.USER_CANCELLATION);
                }
            });
            builder.create().show();
        }
        super.g(aVar);
    }

    protected a c() {
        return new a(this) {
            final /* synthetic */ ac a;
            private CBPreferences b = CBPreferences.getInstance();

            {
                this.a = r2;
            }

            public void a(a aVar) {
            }

            public void b(a aVar) {
            }

            public void c(a aVar) {
            }

            public void d(a aVar) {
            }

            public void a(a aVar, CBImpressionError cBImpressionError) {
            }

            public void e(a aVar) {
            }

            public boolean f(a aVar) {
                return true;
            }

            public boolean h(a aVar) {
                return true;
            }

            public boolean g(a aVar) {
                return false;
            }

            public boolean i(a aVar) {
                if (this.b.getDelegate() != null) {
                    return this.b.getDelegate().shouldRequestInterstitialsInFirstSession();
                }
                return true;
            }
        };
    }
}
