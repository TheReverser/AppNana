package com.vungle.publisher.protocol;

import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.db.model.StreamingAdPlay;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.db.model.StreamingAdReportEvent;
import com.vungle.publisher.db.model.StreamingVideo;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.protocol.message.ReportStreamingAd;
import com.vungle.publisher.protocol.message.RequestStreamingAd;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ReportStreamingAdHttpRequest extends ReportAdHttpRequest<RequestStreamingAd, ReportStreamingAd, StreamingAdReport> {

    @Singleton
    /* compiled from: vungle */
    static class Factory extends com.vungle.publisher.protocol.ReportAdHttpRequest.Factory<RequestStreamingAd, RequestStreamingAdResponse, ReportStreamingAd, ReportStreamingAdHttpRequest, StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo> {
        @Inject
        com.vungle.publisher.protocol.message.ReportStreamingAd.Factory g;

        protected final /* synthetic */ HttpRequest a() {
            return new ReportStreamingAdHttpRequest();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.protocol.message.ReportAd.Factory d() {
            return this.g;
        }

        protected final /* synthetic */ ReportAdHttpRequest e() {
            return new ReportStreamingAdHttpRequest();
        }

        Factory() {
        }
    }
}
