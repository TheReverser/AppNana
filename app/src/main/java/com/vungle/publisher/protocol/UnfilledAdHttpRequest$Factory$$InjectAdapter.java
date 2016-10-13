package com.vungle.publisher.protocol;

import com.vungle.publisher.bf;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.UnfilledAdHttpRequest.Factory;
import com.vungle.publisher.protocol.message.RequestLocalAd;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class UnfilledAdHttpRequest$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<bf> a;
    private Binding<RequestLocalAd.Factory> b;
    private Binding<a> c;

    public UnfilledAdHttpRequest$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.UnfilledAdHttpRequest$Factory", "members/com.vungle.publisher.protocol.UnfilledAdHttpRequest$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bf", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.message.RequestLocalAd$Factory", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.protocol.ProtocolHttpRequest$Factory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.g = (bf) this.a.get();
        object.h = (RequestLocalAd.Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
