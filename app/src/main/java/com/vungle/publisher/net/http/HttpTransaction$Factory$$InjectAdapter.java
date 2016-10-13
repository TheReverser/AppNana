package com.vungle.publisher.net.http;

import com.vungle.publisher.net.http.HttpTransaction.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class HttpTransaction$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<Provider<HttpTransaction>> a;

    public HttpTransaction$Factory$$InjectAdapter() {
        super("com.vungle.publisher.net.http.HttpTransaction$Factory", "members/com.vungle.publisher.net.http.HttpTransaction$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.net.http.HttpTransaction>", Factory.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.c = (Provider) this.a.get();
    }
}
