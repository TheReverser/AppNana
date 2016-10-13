package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler;
import com.vungle.publisher.protocol.RequestConfigAsync;
import com.vungle.publisher.protocol.message.RequestConfigResponse;
import com.vungle.publisher.protocol.message.RequestConfigResponse.Factory;
import com.vungle.publisher.protocol.message.RequestConfigResponse.a;
import com.vungle.sdk.VunglePub.Gender;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Provider;
import org.json.JSONException;

/* compiled from: vungle */
public class ck extends InfiniteRetryHttpResponseHandler {
    @Inject
    public Factory a;
    @Inject
    public SdkConfig b;
    @Inject
    public Provider<RequestConfigAsync> c;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.all.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.wifi.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    protected final void a(HttpTransaction httpTransaction, cf cfVar) throws IOException, JSONException {
        RequestConfigResponse requestConfigResponse = (RequestConfigResponse) this.a.a(cg.a(cfVar.a));
        Integer num = requestConfigResponse.b;
        if (num != null && num.intValue() > 0) {
            ((RequestConfigAsync) this.c.get()).a((long) (num.intValue() * 1000));
        }
        a aVar = requestConfigResponse.d;
        if (aVar != null) {
            switch (AnonymousClass1.a[aVar.ordinal()]) {
                case Gender.FEMALE /*1*/:
                    this.b.a(ca.values());
                    break;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    this.b.a(ca.wifi);
                    break;
                default:
                    Logger.w(Logger.NETWORK_TAG, "unhandled streaming connectivity type " + aVar);
                    break;
            }
        }
        SdkConfig sdkConfig = this.b;
        boolean equals = Boolean.TRUE.equals(requestConfigResponse.a);
        Logger.d(Logger.CONFIG_TAG, (equals ? "enabling" : "disabling") + " ad streaming");
        sdkConfig.b = equals;
        Integer num2 = requestConfigResponse.c;
        if (num2 == null) {
            Logger.w(Logger.NETWORK_TAG, "null request streaming ad timeout millis");
            return;
        }
        SdkConfig sdkConfig2 = this.b;
        int intValue = num2.intValue();
        Logger.d(Logger.CONFIG_TAG, "setting streaming response timeout " + intValue + " ms");
        sdkConfig2.d = intValue;
    }
}
