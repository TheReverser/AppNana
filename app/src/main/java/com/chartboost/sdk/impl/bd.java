package com.chartboost.sdk.impl;

import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Libraries.f.k;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Model.CBError;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;

public class bd implements Observer {
    private static final String a = bd.class.getSimpleName();
    private static bd b;
    private Chartboost c = Chartboost.sharedChartboost();
    private g d = new g("CBVideoDirectory", true);
    private l e = az.a(this.c.getContext()).a();
    private ax f = ax.a();
    private ConcurrentHashMap<Integer, b> g = new ConcurrentHashMap();
    private a h = a.kCBIntial;
    private a i = a.kCBIntial;
    private com.chartboost.sdk.impl.ay.c j = new com.chartboost.sdk.impl.ay.c(this) {
        final /* synthetic */ bd a;

        {
            this.a = r1;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, i iVar) {
            this.a.h = a.kCBIntial;
            if (aVar != null) {
                CBLogging.a(bd.a, "Got Video list from server :)" + aVar);
                this.a.a(aVar.a("videos"));
            }
        }

        public void a(ay ayVar, CBError cBError, i iVar) {
        }
    };

    public enum a {
        kCBIntial,
        kCBInProgress
    }

    private class b<T> extends k<T> {
        final /* synthetic */ bd a;
        private String b;
        private long c = System.currentTimeMillis();
        private String d;

        public b(bd bdVar, int i, String str, c<?> cVar, String str2) {
            this.a = bdVar;
            super(i, str, cVar);
            this.b = str2;
            this.d = str;
        }

        protected void b(T t) {
            CBLogging.a(bd.a, "####### deliverResponse for Video Dwonload");
        }

        protected m<T> a(i iVar) {
            CBLogging.a(bd.a, "####### parseNetworkResponse for Video Download");
            if (iVar != null) {
                com.chartboost.sdk.Tracking.a.d(this.b, Long.valueOf((System.currentTimeMillis() - this.c) / 1000).toString(), this.d);
                CBLogging.a(bd.a, "parseNetworkResponse: Storing video in cache" + this.b);
                this.a.d.a(this.b, iVar.b);
                com.chartboost.sdk.Tracking.a.e("cache", "hit", this.b);
            }
            return m.a(null, null);
        }

        public com.chartboost.sdk.impl.k.a s() {
            return com.chartboost.sdk.impl.k.a.LOW;
        }
    }

    private class c<T> implements com.chartboost.sdk.impl.m.a {
        final /* synthetic */ bd a;
        private b<T> b;

        private c(bd bdVar) {
            this.a = bdVar;
        }

        public void a(r rVar) {
            if ((rVar instanceof q) || (rVar instanceof p) || (rVar instanceof h)) {
                this.a.i = a.kCBIntial;
                if (this.b != null) {
                    com.chartboost.sdk.Tracking.a.a(this.b.b, Long.valueOf((System.currentTimeMillis() - this.b.c) / 1000).toString(), this.b.d, rVar.getMessage());
                    com.chartboost.sdk.Tracking.a.e("cache", "miss", this.b.b);
                }
                this.a.g.put(Integer.valueOf(this.b.hashCode()), this.b);
                CBLogging.a(bd.a, "####### onErrorResponse Video Download" + rVar.getMessage() + this.b.b);
            }
        }
    }

    public static bd a() {
        if (b == null) {
            b = new bd();
        }
        return b;
    }

    private bd() {
        ax.a().addObserver(this);
        b();
    }

    public synchronized void b() {
        synchronized (this) {
            CBLogging.a(a, "Calling Prfetch Video");
            if (a.kCBInProgress != this.h) {
                if (!(this.g == null || this.g.isEmpty())) {
                    this.g.clear();
                    this.e.a(Integer.valueOf(hashCode()));
                    this.i = a.kCBIntial;
                    CBLogging.a(a, "prefetchVideo: Clearing all volley request for new start");
                }
                this.h = a.kCBInProgress;
                JSONArray jSONArray = new JSONArray();
                if (c() != null) {
                    for (Object put : c()) {
                        jSONArray.put(put);
                    }
                }
                com.chartboost.sdk.Tracking.a.d();
                av avVar = new av("/api/video-prefetch", "https://live.chartboost.com", AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
                avVar.a("local-videos", (Object) jSONArray);
                k[] kVarArr = new k[2];
                kVarArr[0] = f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, com.chartboost.sdk.Libraries.a.a);
                kVarArr[1] = f.a("videos", f.b(f.a(f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, f.a()), f.a(ShareConstants.WEB_DIALOG_PARAM_ID, f.a()))));
                avVar.a(f.a(kVarArr));
                avVar.a("/api/video-prefetch");
                avVar.b(true);
                avVar.a(this.j);
            }
        }
    }

    private void a(com.chartboost.sdk.Libraries.e.a aVar) {
        com.chartboost.sdk.Tracking.a.e();
        if (aVar != null) {
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            String[] c = c();
            for (int i = 0; i < aVar.n(); i++) {
                com.chartboost.sdk.Libraries.e.a c2 = aVar.c(i);
                if (!(c2.b(ShareConstants.WEB_DIALOG_PARAM_ID) || c2.b(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO))) {
                    String e = c2.e(ShareConstants.WEB_DIALOG_PARAM_ID);
                    String e2 = c2.e(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
                    if (!this.d.b(e)) {
                        hashMap2.put(e, e2);
                    }
                    hashMap.put(e, e2);
                }
            }
            CBLogging.a(a, "synchronizeVideos: Delete and Download new videos");
            a(hashMap, c);
            a(hashMap2);
        }
    }

    private void a(HashMap<String, String> hashMap, String[] strArr) {
        CBLogging.a(a, "deleteVideos: Deleteing videos in cache");
        if (hashMap != null && strArr != null) {
            for (String str : strArr) {
                if (!hashMap.containsKey(str)) {
                    File c = this.d.c(str);
                    if (c != null) {
                        this.d.c(c);
                    }
                }
            }
        }
    }

    private synchronized <T> void a(HashMap<String, String> hashMap) {
        CBLogging.a(a, "downloadVideos: Downloading videos from server");
        for (String str : hashMap.keySet()) {
            c cVar = new c();
            k bVar = new b(this, 0, (String) hashMap.get(str), cVar, str);
            bVar.a(new d(2500, 0, 1.0f));
            cVar.b = bVar;
            bVar.a((Object) Integer.valueOf(hashCode()));
            this.i = a.kCBInProgress;
            CBLogging.a(a, "######## Volley Request adding it to queue " + str);
            com.chartboost.sdk.Tracking.a.a((String) hashMap.get(str), str);
            this.e.a(bVar);
        }
        CBLogging.a(a, "######## Done");
    }

    public String[] c() {
        return this.d.a();
    }

    public String a(String str) {
        if (this.d.b(str)) {
            return this.d.c(str).getPath();
        }
        return null;
    }

    public void update(Observable observable, Object data) {
        e();
    }

    private synchronized void e() {
        CBLogging.a(a, "Process Request called");
        if (this.h == a.kCBInProgress) {
            this.e.a(Integer.valueOf(hashCode()));
            this.g.clear();
            this.i = a.kCBIntial;
        } else if ((this.i == a.kCBIntial && this.g != null) || !this.g.isEmpty()) {
            CBLogging.a(a, "Process Request called to get videos from server");
            this.i = a.kCBInProgress;
            for (Integer num : this.g.keySet()) {
                this.e.a((k) this.g.get(num));
                this.g.remove(num);
            }
        }
    }
}
