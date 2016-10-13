package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.NaCl;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ay.c;
import com.facebook.BuildConfig;
import com.facebook.GraphResponse;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.json.JSONObject;
import org.json.JSONTokener;

public class az implements Observer {
    private static az c;
    private static List<Runnable> e = new ArrayList();
    private static long k = 240000;
    private ax a = null;
    private Handler b;
    private l d;
    private com.chartboost.sdk.Tracking.a f;
    private g g;
    private ConcurrentHashMap<ay, File> h;
    private ConcurrentHashMap<ay, File> i;
    private CountDownTimer j;
    private volatile boolean l = false;

    private class a extends y {
        final /* synthetic */ az a;
        private final ch b;

        public a(az azVar) {
            this(azVar, new ch());
        }

        public a(az azVar, ch chVar) {
            this.a = azVar;
            if (chVar == null) {
                throw new NullPointerException("Client must not be null.");
            }
            this.b = chVar;
        }

        protected HttpURLConnection a(URL url) throws IOException {
            return this.b.a(url);
        }
    }

    private class b<T> implements Runnable {
        final /* synthetic */ az a;
        private ay b;

        private class a<T> extends k<T> {
            final /* synthetic */ b a;
            private ay b;
            private HttpEntity c;
            private com.chartboost.sdk.impl.k.a d;

            public a(b bVar, int i, String str, com.chartboost.sdk.impl.m.a aVar, ay ayVar) {
                this.a = bVar;
                super(i, str, aVar);
                this.b = ayVar;
                try {
                    this.c = (HttpEntity) ayVar.f;
                } catch (Throwable e) {
                    CBLogging.b("CBRequestManager", "Entity not found", e);
                }
                this.d = ayVar.u;
            }

            public String p() {
                if (this.c == null || this.c.getContentType() == null) {
                    return "application/json; charset=utf-8";
                }
                return this.c.getContentType().getValue();
            }

            public byte[] q() {
                return (this.b.g() == null ? BuildConfig.VERSION_NAME : this.b.g().toString()).getBytes();
            }

            public com.chartboost.sdk.impl.k.a s() {
                return this.d;
            }

            public Map<String, String> i() throws a {
                Map<String, String> hashMap = new HashMap();
                for (Entry entry : this.b.h().entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
                }
                return hashMap;
            }

            protected m<T> a(i iVar) {
                JSONObject jSONObject;
                JSONObject jSONObject2;
                CBError cBError;
                Exception e;
                JSONObject jSONObject3;
                boolean z = false;
                int i = iVar.a;
                if (i <= 300 || i >= 200) {
                    try {
                        String str;
                        JSONObject jSONObject4;
                        byte[] bArr = iVar.b;
                        if ((this.b instanceof bc) && iVar.c != null && ((String) iVar.c.get("Content-Type")).equals("application/x-chartboost-encrypted")) {
                            bArr = NaCl.decrypt(bArr, ((bc) this.b).k(), bc.l(), ((bc) this.b).m().b);
                            str = bArr != null ? new String(bArr) : null;
                        } else {
                            str = new String(bArr);
                        }
                        if (str != null) {
                            jSONObject = new JSONObject(new JSONTokener(str));
                            try {
                                CBError cBError2;
                                com.chartboost.sdk.Libraries.f.a i2 = this.b.i();
                                StringBuilder stringBuilder = new StringBuilder();
                                if (i2 == null || i2.a(jSONObject, stringBuilder)) {
                                    cBError2 = null;
                                } else {
                                    cBError2 = new CBError(com.chartboost.sdk.Model.CBError.a.UNEXPECTED_RESPONSE, "Json response failed validation");
                                }
                                jSONObject2 = jSONObject;
                                cBError = cBError2;
                                jSONObject4 = jSONObject2;
                            } catch (Exception e2) {
                                e = e2;
                                jSONObject2 = jSONObject;
                                cBError = new CBError(com.chartboost.sdk.Model.CBError.a.MISCELLANEOUS, e.getLocalizedMessage());
                                jSONObject3 = jSONObject2;
                                if (jSONObject3 == null) {
                                }
                                this.a.a(false, cBError, jSONObject3, iVar, this.b);
                                return m.a(null);
                            }
                        }
                        cBError = new CBError(com.chartboost.sdk.Model.CBError.a.INVALID_RESPONSE, "Response is not a valid json object");
                        jSONObject4 = null;
                        jSONObject3 = jSONObject4;
                    } catch (Exception e3) {
                        e = e3;
                        jSONObject = null;
                        jSONObject2 = jSONObject;
                        cBError = new CBError(com.chartboost.sdk.Model.CBError.a.MISCELLANEOUS, e.getLocalizedMessage());
                        jSONObject3 = jSONObject2;
                        if (jSONObject3 == null) {
                        }
                        this.a.a(false, cBError, jSONObject3, iVar, this.b);
                        return m.a(null);
                    }
                }
                cBError = new CBError(com.chartboost.sdk.Model.CBError.a.NETWORK_FAILURE, "Request failed. Response code: " + i + " is not valid ");
                jSONObject3 = null;
                if (jSONObject3 == null && cBError == null) {
                    b bVar = this.a;
                    if (jSONObject3 != null) {
                        z = true;
                    }
                    bVar.a(z, cBError, jSONObject3, iVar, this.b);
                    return m.a(null, null);
                }
                this.a.a(false, cBError, jSONObject3, iVar, this.b);
                return m.a(null);
            }

            protected void b(T t) {
            }
        }

        private class b implements com.chartboost.sdk.impl.m.a {
            final /* synthetic */ b a;
            private ay b;

            private b(b bVar) {
                this.a = bVar;
            }

            public void a(r rVar) {
                CBError cBError = new CBError(com.chartboost.sdk.Model.CBError.a.MISCELLANEOUS, rVar.getMessage());
                if (rVar != null && rVar.a != null && rVar.a.a == 200) {
                    this.a.a(true, null, null, rVar.a, this.b);
                } else if (rVar == null) {
                } else {
                    if ((rVar instanceof h) || (rVar instanceof q) || (rVar instanceof a) || !(rVar.a == null || rVar.a.b == null)) {
                        this.a.a(false, cBError, null, rVar.a, this.b);
                    }
                }
            }
        }

        public b(az azVar, ay ayVar) {
            this.a = azVar;
            this.b = ayVar;
        }

        public void run() {
            this.b.a(Chartboost.sharedChartboost().getContext());
            this.b.c();
            String str = (this.b.o == null ? "https://live.chartboost.com" : this.b.o) + this.b.e();
            Object bVar = new b();
            this.b.d();
            k aVar = new a(this, 1, str, bVar, this.b);
            bVar.b = this.b;
            this.a.d.a(aVar);
        }

        private void a(boolean z, CBError cBError, JSONObject jSONObject, i iVar, ay ayVar) {
            final boolean z2 = z;
            final JSONObject jSONObject2 = jSONObject;
            final i iVar2 = iVar;
            final CBError cBError2 = cBError;
            this.a.b.post(new Runnable(this) {
                final /* synthetic */ b e;

                public void run() {
                    ay a = this.e.b;
                    if (a.a.equals("/api/track")) {
                        if (this.e.a.f == null) {
                            this.e.a.f = com.chartboost.sdk.Tracking.a.a();
                        }
                        if (z2) {
                            this.e.a.f.m().c((File) this.e.a.i.get(a));
                            CBLogging.a("CBRequestManager", "### Removing track events sent to server...");
                        }
                        this.e.a.i.remove(a);
                        if (this.e.a.i.isEmpty()) {
                            this.e.a.l = false;
                            return;
                        }
                        return;
                    }
                    if (z2) {
                        if (!(a.i == null || jSONObject2 == null)) {
                            a.i.a(com.chartboost.sdk.Libraries.e.a.a(jSONObject2), a, iVar2);
                        }
                        this.e.a.g.c((File) this.e.a.h.get(a));
                        this.e.a.h.remove(a);
                    } else {
                        a.k = false;
                        if (a.i != null) {
                            a.i.a(a, cBError2, iVar2);
                        }
                    }
                    this.e.a.a(a, iVar2, cBError2, z2);
                }
            });
        }
    }

    public static az a(Context context) {
        if (c == null) {
            synchronized (az.class) {
                if (c == null) {
                    c = new az(context);
                }
            }
        }
        return c;
    }

    private az(Context context) {
        this.d = aa.a(context.getApplicationContext(), new a(this));
        this.b = new Handler();
        this.a = ax.a();
        this.g = new g("CBRequestManager", false);
        this.h = new ConcurrentHashMap();
        this.i = new ConcurrentHashMap();
        this.a.addObserver(this);
    }

    public l a() {
        return this.d;
    }

    private void a(ay ayVar, i iVar, CBError cBError, boolean z) {
        if (ayVar != null) {
            String str;
            if (this.f == null) {
                this.f = com.chartboost.sdk.Tracking.a.a();
            }
            com.chartboost.sdk.Libraries.e.b[] bVarArr = new com.chartboost.sdk.Libraries.e.b[5];
            bVarArr[0] = e.a("endpoint", ayVar.a);
            bVarArr[1] = e.a("statuscode", iVar == null ? "None" : Integer.valueOf(iVar.a));
            bVarArr[2] = e.a(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, cBError == null ? "None" : cBError.a());
            bVarArr[3] = e.a("errorDescription", cBError == null ? "None" : cBError.b());
            bVarArr[4] = e.a("retryCount", Integer.valueOf(ayVar.r));
            com.chartboost.sdk.Libraries.e.a a = e.a(bVarArr);
            String str2 = "request_manager";
            String str3 = ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID;
            if (z) {
                str = GraphResponse.SUCCESS_KEY;
            } else {
                str = "failure";
            }
            com.chartboost.sdk.Tracking.a.a(str2, str3, str, null, null, null, a.e());
        }
    }

    protected <T> void a(ay ayVar, c cVar) {
        CBError cBError;
        if ((ayVar instanceof bc) && bc.l() == null) {
            if (cVar != null) {
                cBError = new CBError(com.chartboost.sdk.Model.CBError.a.PUBLIC_KEY_MISSING, "app public key is not set");
                a(ayVar, null, cBError, false);
                cVar.a(ayVar, cBError, null);
            }
        } else if (this.a.c()) {
            if (ayVar.t) {
                ayVar.t = false;
                a(ayVar);
            }
            a(new b(this, ayVar));
        } else {
            cBError = new CBError(com.chartboost.sdk.Model.CBError.a.INTERNET_UNAVAILABLE, null);
            ayVar.k = false;
            if (ayVar.t) {
                ayVar.t = false;
                a(ayVar);
            }
            a(ayVar, null, cBError, false);
            if (cVar != null) {
                cVar.a(ayVar, cBError, null);
            }
        }
    }

    public void a(Runnable runnable) {
        Object obj = null;
        synchronized (com.chartboost.sdk.Libraries.c.class) {
            com.chartboost.sdk.Libraries.c.a c = com.chartboost.sdk.Libraries.c.c();
            if (c == com.chartboost.sdk.Libraries.c.a.PRELOAD || c == com.chartboost.sdk.Libraries.c.a.LOADING) {
                e.add(runnable);
            } else {
                obj = 1;
            }
        }
        if (obj != null) {
            aw.a().execute(runnable);
        }
    }

    public static void b() {
        List<Runnable> arrayList = new ArrayList();
        synchronized (com.chartboost.sdk.Libraries.c.class) {
            arrayList.addAll(e);
            e.clear();
        }
        for (Runnable execute : arrayList) {
            aw.a().execute(execute);
        }
    }

    public synchronized void c() {
        synchronized (this) {
            if (this.h == null || this.h.isEmpty()) {
                String[] a = this.g.a();
                if (a != null) {
                    for (String str : a) {
                        ay a2 = a(str);
                        if (a2 != null) {
                            this.h.put(a2, this.g.c(str));
                            a2.t = false;
                            a2.r++;
                            a2.a(a2.i);
                        }
                    }
                }
            } else {
                for (ay ayVar : this.h.keySet()) {
                    if (!(ayVar == null || ayVar.k)) {
                        ayVar.r++;
                        ayVar.a(ayVar.i);
                    }
                }
            }
            d();
            e();
        }
    }

    public void d() {
        if (this.f == null) {
            this.f = com.chartboost.sdk.Tracking.a.a();
        }
        synchronized (this) {
            if (this.i.isEmpty() && !this.l) {
                try {
                    g m = this.f.m();
                    String[] a = m.a();
                    if (a != null) {
                        for (String str : a) {
                            if (!this.f.b(str)) {
                                this.l = true;
                                com.chartboost.sdk.Libraries.e.a a2 = m.a(str);
                                if (a2.c()) {
                                    CBLogging.a("CBRequestManager", "### Flushing out " + str + "track events from cache to server...");
                                    ay a3 = this.f.a(a2);
                                    this.i.put(a3, m.c(str));
                                    a3.j();
                                }
                            }
                        }
                    }
                } catch (Throwable e) {
                    CBLogging.b("CBRequestManager", "Error executing saved requests", e);
                }
            }
        }
    }

    private void a(ay ayVar) {
        if (ayVar != null) {
            Object a;
            if (ayVar.l) {
                if (ayVar instanceof av) {
                    a = this.g.a(null, ((av) ayVar).a());
                } else if (ayVar instanceof bc) {
                    a = this.g.a(null, ((bc) ayVar).a());
                }
                if ((!ayVar.l || ayVar.s) && a != null) {
                    this.h.put(ayVar, a);
                }
                return;
            }
            a = null;
            if (ayVar.l) {
            }
            this.h.put(ayVar, a);
        }
    }

    private ay a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a a = this.g.a(str);
        if (a == null || a.a("isEncryptedCBRequest") == null) {
            return null;
        }
        if (a.i("isEncryptedCBRequest")) {
            return bc.b(a);
        }
        return av.a(a);
    }

    public void e() {
        if (this.j == null) {
            this.j = new CountDownTimer(this, k, 1000) {
                final /* synthetic */ az a;

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    this.a.c();
                }
            }.start();
        }
    }

    public void f() {
        CBLogging.a("CBRequestManager", "Timer stopped:");
        if (this.j != null) {
            this.j.cancel();
            this.j = null;
        }
    }

    public void update(Observable observable, Object data) {
        if (this.j != null) {
            f();
        }
        c();
    }
}
