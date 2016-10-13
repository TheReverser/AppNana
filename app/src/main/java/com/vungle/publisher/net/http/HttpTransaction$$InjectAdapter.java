package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class HttpTransaction$$InjectAdapter extends Binding<HttpTransaction> implements MembersInjector<HttpTransaction>, Provider<HttpTransaction> {
    private Binding<HttpTransport> a;

    public HttpTransaction$$InjectAdapter() {
        super("com.vungle.publisher.net.http.HttpTransaction", "members/com.vungle.publisher.net.http.HttpTransaction", false, HttpTransaction.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.net.http.HttpTransport", HttpTransaction.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final HttpTransaction get() {
        HttpTransaction httpTransaction = new HttpTransaction();
        injectMembers(httpTransaction);
        return httpTransaction;
    }

    public final void injectMembers(HttpTransaction object) {
        object.d = (HttpTransport) this.a.get();
    }
}
