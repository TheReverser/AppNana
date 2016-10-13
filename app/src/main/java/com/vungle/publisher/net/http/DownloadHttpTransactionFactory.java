package com.vungle.publisher.net.http;

import com.vungle.publisher.at.b;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class DownloadHttpTransactionFactory extends Factory {
    @Inject
    Factory a;
    @Inject
    Factory b;

    public final HttpTransaction a(String str, b bVar, String str2, String str3) {
        DownloadHttpRequest downloadHttpRequest = (DownloadHttpRequest) this.a.b();
        downloadHttpRequest.b = str2;
        DownloadHttpResponseHandler downloadHttpResponseHandler = (DownloadHttpResponseHandler) this.b.a.get();
        downloadHttpResponseHandler.a = str;
        downloadHttpResponseHandler.b = str3;
        downloadHttpResponseHandler.c = bVar;
        return super.a(downloadHttpRequest, downloadHttpResponseHandler);
    }
}
