package com.vungle.publisher.protocol;

import com.vungle.publisher.cf;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class TrackInstallHttpResponseHandler extends FireAndForgetHttpResponseHandler {
    @Inject
    SdkState a;

    protected final void a(HttpTransaction httpTransaction, cf cfVar) throws IOException, JSONException {
        super.a(httpTransaction, cfVar);
        this.a.o.edit().putBoolean("IsVgAppInstalled", true).apply();
    }
}
