package com.vungle.publisher.protocol;

import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LocalAdReportEvent;
import com.vungle.publisher.db.model.LocalVideo;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.protocol.message.ReportLocalAd;
import com.vungle.publisher.protocol.message.RequestLocalAd;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ReportLocalAdHttpRequest extends ReportAdHttpRequest<RequestLocalAd, ReportLocalAd, LocalAdReport> {

    @Singleton
    /* compiled from: vungle */
    static class Factory extends com.vungle.publisher.protocol.ReportAdHttpRequest.Factory<RequestLocalAd, RequestLocalAdResponse, ReportLocalAd, ReportLocalAdHttpRequest, LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo> {
        @Inject
        com.vungle.publisher.protocol.message.ReportLocalAd.Factory g;

        protected final /* synthetic */ HttpRequest a() {
            return new ReportLocalAdHttpRequest();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.protocol.message.ReportAd.Factory d() {
            return this.g;
        }

        protected final /* synthetic */ ReportAdHttpRequest e() {
            return new ReportLocalAdHttpRequest();
        }

        Factory() {
        }
    }
}
