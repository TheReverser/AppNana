package com.vungle.publisher.reporting;

import android.content.ContentValues;
import com.facebook.internal.AnalyticsEvents;
import com.vungle.log.Logger;
import com.vungle.publisher.al;
import com.vungle.publisher.am;
import com.vungle.publisher.bk;
import com.vungle.publisher.cc;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.AdPlay;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.AdReport.Factory;
import com.vungle.publisher.db.model.AdReportEvent;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.k;
import com.vungle.publisher.l;
import com.vungle.publisher.u;
import com.vungle.publisher.x;
import com.vungle.publisher.y;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdReportEventListener extends bk {
    private static final a[] h = new a[]{a.play_percentage_0, a.play_percentage_25, a.play_percentage_50, a.play_percentage_75, a.play_percentage_80, a.play_percentage_100};
    public AdPlay<?, ?, ?, ?, ?, ?> a;
    public AdReport<?, ?, ?, ?, ?, ?> b;
    @Inject
    AdServiceReportingHandler c;
    @Inject
    Factory d;
    @Inject
    AdReportManager e;
    @Inject
    public cc g;
    private Ad<?, ?, ?> i;
    private int j;
    private final HashSet<a> k = new HashSet();

    private void a(Ad<?, ?, ?> ad) {
        if (this.i == null || !this.i.a((Ad) ad)) {
            AdReport a;
            Logger.i(Logger.REPORT_TAG, "new ad " + ad.y());
            this.i = ad;
            AdReportManager adReportManager = this.e;
            if (ad instanceof LocalAd) {
                a = adReportManager.c.a((LocalAd) ad);
            } else if (ad instanceof StreamingAd) {
                a = adReportManager.f.a((StreamingAd) ad);
            } else {
                throw new IllegalArgumentException("unknown ad type " + ad);
            }
            this.b = a;
            this.a = this.b.q();
            a();
            return;
        }
        Logger.v(Logger.REPORT_TAG, "same ad " + ad.y());
    }

    private void a() {
        this.j = 0;
        this.k.clear();
    }

    public final void a(AdReportEvent.a aVar, Object obj) {
        try {
            this.a.a(aVar, obj);
        } catch (Throwable e) {
            Logger.w(Logger.REPORT_TAG, "error reporting event", e);
        }
    }

    public final void a(a aVar) {
        if (this.i == null) {
            Logger.w(Logger.REPORT_TAG, "null ad in AdReportingHandler - cannot track event " + aVar);
        } else if (!this.k.contains(aVar)) {
            Logger.v(Logger.REPORT_TAG, "tpat event " + aVar.name());
            cc ccVar = this.g;
            Ad ad = this.i;
            String[] a = this.i.a(aVar);
            Map hashMap = new HashMap();
            hashMap.put("%timestamp%", String.valueOf(System.currentTimeMillis()));
            ccVar.a(ad, aVar, hashMap, a);
            this.k.add(aVar);
        }
    }

    public void onEvent(x startPlayAdEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received play ad start");
            a(startPlayAdEvent.a());
            Factory factory = this.d;
            ContentValues contentValues = new ContentValues();
            contentValues.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, AdReport.a.reportable.toString());
            factory.a.getWritableDatabase().update("ad_report", contentValues, "status = ?", new String[]{AdReport.a.playing.toString()});
            com.vungle.publisher.a aVar = startPlayAdEvent.b;
            AdReport adReport = this.b;
            adReport.a(AdReport.a.playing);
            adReport.a(aVar.getExtras());
            boolean isIncentivized = aVar.isIncentivized();
            adReport.b(isIncentivized);
            if (isIncentivized) {
                adReport.b(aVar.getIncentivizedUserId());
            }
            adReport.c(aVar.getPlacement());
            adReport.c(Long.valueOf(startPlayAdEvent.c));
            adReport.x();
        } catch (Throwable e) {
            Logger.w(Logger.REPORT_TAG, "error processing ad start event", e);
        }
    }

    public void onEvent(am endPlayAdEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received play ad end");
            a(endPlayAdEvent.a());
            a(endPlayAdEvent.b());
        } catch (Throwable e) {
            Logger.w(Logger.REPORT_TAG, "error processing ad end", e);
        }
    }

    public void onEvent(al destroyedErrorEndPlayEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received destroyed ad end");
            a(destroyedErrorEndPlayEvent.c);
        } catch (Exception e) {
            Logger.w(Logger.REPORT_TAG, "error processing destroyed ad end");
        }
    }

    private void a(long j) {
        d();
        AdReport adReport = this.b;
        if (adReport == null) {
            Logger.d(Logger.REPORT_TAG, "no current ad report");
        } else {
            adReport.a(AdReport.a.reportable);
            adReport.a(Long.valueOf(j));
            adReport.x();
        }
        this.e.a();
        this.i = null;
        this.b = null;
        this.a = null;
        a();
    }

    public void onEvent(k playVideoDurationEvent) {
        try {
            this.b.a(Integer.valueOf(playVideoDurationEvent.a));
        } catch (Throwable e) {
            Logger.w(Logger.REPORT_TAG, "error updating video duration millis", e);
        }
    }

    public void onEvent(y playVideoStartEvent) {
        try {
            this.a.c = Long.valueOf(playVideoStartEvent.c);
            this.a.m();
        } catch (Throwable e) {
            Logger.w(Logger.REPORT_TAG, "error updating play start millis", e);
        }
    }

    public void onEvent(u playVideoProgressEvent) {
        Object obj = null;
        try {
            int i = playVideoProgressEvent.a;
            Object obj2 = this.j < h.length ? 1 : null;
            boolean z = playVideoProgressEvent instanceof l;
            if (obj2 != null || z) {
                Integer j = this.b.j();
                if (j == null) {
                    Logger.d(Logger.REPORT_TAG, "null video duration millis for " + this.b.y());
                } else if (j.intValue() == 0) {
                    Logger.w(Logger.REPORT_TAG, "video duration millis 0 for " + this.b.y());
                } else {
                    if (obj2 != null) {
                        float intValue = ((float) i) / ((float) j.intValue());
                        a aVar = h[this.j];
                        if (intValue >= aVar.p) {
                            obj = 1;
                        }
                        if (obj != null) {
                            this.j++;
                            a(aVar);
                        }
                    }
                    if (obj != null || z) {
                        try {
                            AdPlay adPlay = this.a;
                            Integer valueOf = Integer.valueOf(playVideoProgressEvent.a);
                            if (adPlay.b == null || (valueOf != null && valueOf.intValue() > adPlay.b.intValue())) {
                                Logger.d(Logger.AD_TAG, "setting watched millis " + valueOf);
                                adPlay.b = valueOf;
                            } else {
                                Logger.w(Logger.AD_TAG, "ignoring decreased watched millis " + valueOf);
                            }
                            this.a.m();
                            this.b.b(Long.valueOf(playVideoProgressEvent.c));
                        } catch (Throwable e) {
                            Logger.w(Logger.REPORT_TAG, "error updating video view progress", e);
                        }
                    }
                }
            }
        } catch (Throwable e2) {
            Logger.w(Logger.REPORT_TAG, "error handling video view progress", e2);
        }
    }
}
