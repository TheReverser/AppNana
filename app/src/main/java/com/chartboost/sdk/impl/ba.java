package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.Model.a.c;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class ba {
    private static ba c;
    private a a;
    private com.chartboost.sdk.Model.a b;

    public interface a {
        void a(com.chartboost.sdk.Model.a aVar, boolean z, String str, CBClickError cBClickError);
    }

    public static ba a(a aVar) {
        if (c == null) {
            c = new ba(aVar);
        }
        return c;
    }

    private ba(a aVar) {
        this.a = aVar;
    }

    public void a(com.chartboost.sdk.Model.a aVar, final String str, final Activity activity) {
        this.b = aVar;
        try {
            String scheme = new URI(str).getScheme();
            if (scheme == null) {
                if (this.a != null) {
                    this.a.a(aVar, false, str, CBClickError.URI_INVALID);
                }
            } else if (scheme.equals("http") || scheme.equals("https")) {
                aw.a().execute(new Runnable(this) {
                    final /* synthetic */ ba c;

                    public void run() {
                        String str;
                        Throwable th;
                        String str2 = str;
                        if (ax.a().c()) {
                            HttpURLConnection httpURLConnection = null;
                            try {
                                CBPreferences instance = CBPreferences.getInstance();
                                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                                try {
                                    httpURLConnection2.setInstanceFollowRedirects(false);
                                    httpURLConnection2.setConnectTimeout(instance.getTimeout());
                                    httpURLConnection2.setReadTimeout(instance.getTimeout());
                                    String headerField = httpURLConnection2.getHeaderField("Location");
                                    if (headerField != null) {
                                        str2 = headerField;
                                    }
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                        str = str2;
                                        a(str);
                                    }
                                } catch (Throwable e) {
                                    Throwable th2 = e;
                                    httpURLConnection = httpURLConnection2;
                                    th = th2;
                                    try {
                                        CBLogging.b("CBURLOpener", "Exception raised while opening a HTTP Conection", th);
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                            str = str2;
                                            a(str);
                                        }
                                        str = str2;
                                        a(str);
                                    } catch (Throwable th3) {
                                        th = th3;
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th4) {
                                    httpURLConnection = httpURLConnection2;
                                    th = th4;
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (Exception e2) {
                                th = e2;
                                CBLogging.b("CBURLOpener", "Exception raised while opening a HTTP Conection", th);
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                    str = str2;
                                    a(str);
                                }
                                str = str2;
                                a(str);
                            }
                        }
                        str = str2;
                        a(str);
                    }

                    public void a(final String str) {
                        Runnable anonymousClass1 = new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                this.b.c.a(str, activity);
                            }
                        };
                        if (activity != null) {
                            activity.runOnUiThread(anonymousClass1);
                        } else {
                            new Handler().post(anonymousClass1);
                        }
                    }
                });
            } else {
                a(str, activity);
            }
        } catch (URISyntaxException e) {
            if (this.a != null) {
                this.a.a(aVar, false, str, CBClickError.URI_INVALID);
            }
        }
    }

    private void a(String str, Context context) {
        Intent intent;
        if (this.b.d != c.REWARDED_VIDEO) {
            this.b.c = b.NONE;
        }
        if (context == null) {
            context = Chartboost.sharedChartboost().getContext();
        }
        if (context != null) {
            try {
                intent = new Intent("android.intent.action.VIEW");
                if (!(context instanceof Activity)) {
                    intent.addFlags(268435456);
                }
                intent.setData(Uri.parse(str));
                context.startActivity(intent);
            } catch (Exception e) {
                if (str.startsWith("market://")) {
                    try {
                        str = "http://market.android.com/" + str.substring(9);
                        intent = new Intent("android.intent.action.VIEW");
                        if (!(context instanceof Activity)) {
                            intent.addFlags(268435456);
                        }
                        intent.setData(Uri.parse(str));
                        context.startActivity(intent);
                    } catch (Throwable e2) {
                        CBLogging.b("CBURLOpener", "Exception raised openeing an inavld playstore URL", e2);
                        if (this.a != null) {
                            this.a.a(this.b, false, str, CBClickError.URI_UNRECOGNIZED);
                            return;
                        }
                        return;
                    }
                } else if (this.a != null) {
                    this.a.a(this.b, false, str, CBClickError.URI_UNRECOGNIZED);
                }
            }
            if (this.a != null) {
                this.a.a(this.b, true, str, null);
            }
        } else if (this.a != null) {
            this.a.a(this.b, false, str, CBClickError.NO_HOST_ACTIVITY);
        }
    }
}
