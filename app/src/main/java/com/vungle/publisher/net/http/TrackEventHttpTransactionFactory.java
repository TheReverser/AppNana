package com.vungle.publisher.net.http;

import com.vungle.log.Logger;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import com.vungle.sdk.VunglePub.Gender;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class TrackEventHttpTransactionFactory extends Factory {
    @Inject
    TrackEventHttpRequest.Factory a;
    @Inject
    Lazy<TrackEventHttpResponseHandler> b;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.postroll_click.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.video_click.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public final HttpTransaction a(Ad<?, ?, ?> ad, a aVar, String str) {
        TrackEventHttpResponseHandler trackEventHttpResponseHandler = (TrackEventHttpResponseHandler) this.b.get();
        switch (AnonymousClass1.a[aVar.ordinal()]) {
            case Gender.FEMALE /*1*/:
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                trackEventHttpResponseHandler.a = true;
                break;
            default:
                trackEventHttpResponseHandler.h = 1;
                trackEventHttpResponseHandler.g = 10;
                break;
        }
        TrackEventHttpRequest trackEventHttpRequest = (TrackEventHttpRequest) this.a.b();
        trackEventHttpRequest.e = ad;
        trackEventHttpRequest.f = aVar;
        trackEventHttpRequest.b = str;
        return super.a(trackEventHttpRequest, trackEventHttpResponseHandler);
    }
}
