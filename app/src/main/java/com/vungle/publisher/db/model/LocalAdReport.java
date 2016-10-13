package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import com.vungle.publisher.db.model.Ad.b;
import com.vungle.publisher.db.model.AdReport.BaseFactory;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class LocalAdReport extends AdReport<LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo, RequestLocalAdResponse> {
    Long p;
    @Inject
    Factory q;
    @Inject
    Factory u;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends BaseFactory<LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo, RequestLocalAdResponse> {
        @Inject
        com.vungle.publisher.db.model.LocalAd.Factory b;
        @Inject
        Factory d;
        @Inject
        Provider<LocalAdReport> e;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* bridge */ /* synthetic */ as a(as asVar, Cursor cursor) {
            return a((LocalAdReport) asVar, cursor, false);
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory a() {
            return this.b;
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdPlay.Factory b() {
            return this.d;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new LocalAdReport[i];
        }

        protected final /* synthetic */ as c_() {
            return (LocalAdReport) this.e.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        protected Factory() {
        }

        protected final b c() {
            return b.local;
        }

        private LocalAdReport a(LocalAdReport localAdReport, Cursor cursor, boolean z) {
            super.a((AdReport) localAdReport, cursor, z);
            localAdReport.p = aq.e(cursor, "download_end_millis");
            return localAdReport;
        }

        protected final String e_() {
            return "ad_report";
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdPlay.Factory a() {
        return this.u;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.q;
    }

    protected LocalAdReport() {
    }

    public final int A() {
        if (this.p == null) {
            Logger.w(Logger.REPORT_TAG, "download end millis null for " + y());
            return -1;
        } else if (this.p.longValue() < 0) {
            return 0;
        } else {
            if (this.d != null) {
                return (int) (this.p.longValue() - this.d.longValue());
            }
            Logger.w(Logger.REPORT_TAG, "insert timestamp millis null for " + y());
            return -1;
        }
    }

    public final void d(Long l) {
        this.p = l;
        Logger.d(Logger.REPORT_TAG, "setting ad download end millis " + l + " (duration " + A() + " ms) for " + y());
        x();
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put("download_end_millis", this.p);
        return a;
    }

    public final StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "download_end_millis", this.p, false);
        return p;
    }
}
