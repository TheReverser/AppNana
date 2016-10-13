package com.vungle.publisher.net.http;

import com.vungle.publisher.bf;
import com.vungle.publisher.net.http.HttpRequest.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class HttpRequest$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory> {
    private Binding<bf> a;

    public HttpRequest$Factory$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.net.http.HttpRequest$Factory", false, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bf", Factory.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final void injectMembers(Factory object) {
        object.a = (bf) this.a.get();
    }
}
