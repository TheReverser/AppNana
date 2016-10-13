package com.vungle.publisher.reporting;

import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.AdReport.Factory;
import com.vungle.publisher.db.model.AdReport.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.m;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.ProtocolHttpGateway.AnonymousClass1;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdReportManager {
    @Inject
    EventBus a;
    @Inject
    Factory b;
    @Inject
    public LocalAdReport.Factory c;
    @Inject
    public ProtocolHttpGateway d;
    @Inject
    public SdkState e;
    @Inject
    StreamingAdReport.Factory f;

    public final LocalAdReport a(LocalAd localAd) {
        return (LocalAdReport) this.c.a(localAd);
    }

    public final void a() {
        String y;
        try {
            List<AdReport> a = this.b.a();
            Logger.i(Logger.REPORT_TAG, "sending " + a.size() + " ad reports");
            for (AdReport adReport : a) {
                y = adReport.y();
                Logger.d(Logger.REPORT_TAG, "sending " + y);
                ProtocolHttpGateway protocolHttpGateway = this.d;
                protocolHttpGateway.d.a(new AnonymousClass1(protocolHttpGateway, adReport), b.reportAd);
            }
            this.a.a(new m());
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error sending " + y, e);
            adReport.a(a.failed);
            adReport.m();
        } catch (Throwable th) {
            this.a.a(new m());
        }
    }
}
