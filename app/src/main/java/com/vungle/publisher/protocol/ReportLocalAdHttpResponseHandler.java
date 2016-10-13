package com.vungle.publisher.protocol;

import com.vungle.publisher.cf;
import com.vungle.publisher.cj;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.message.ReportLocalAd;
import com.vungle.publisher.protocol.message.RequestLocalAd;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
class ReportLocalAdHttpResponseHandler extends cj {
    @Inject
    EventTrackingHttpLogEntryDeleteDelegate b;

    ReportLocalAdHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, cf cfVar) throws IOException, JSONException {
        super.a(httpTransaction, cfVar);
        this.b.a(((RequestLocalAd) ((ReportLocalAd) ((ReportLocalAdHttpRequest) httpTransaction.a).g).c()).g);
    }
}
