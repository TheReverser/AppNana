package com.vungle.publisher.net.http;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: vungle */
public final class HttpTransport$$InjectAdapter extends Binding<HttpTransport> implements Provider<HttpTransport> {
    public HttpTransport$$InjectAdapter() {
        super("com.vungle.publisher.net.http.HttpTransport", "members/com.vungle.publisher.net.http.HttpTransport", true, HttpTransport.class);
    }

    public final HttpTransport get() {
        return new HttpTransport();
    }
}
