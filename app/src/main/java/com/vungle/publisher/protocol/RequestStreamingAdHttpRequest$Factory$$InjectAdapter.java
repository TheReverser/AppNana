package com.vungle.publisher.protocol;

import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.RequestStreamingAdHttpRequest.Factory;
import com.vungle.publisher.protocol.message.RequestStreamingAd;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestStreamingAdHttpRequest$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<RequestStreamingAd.Factory> a;
    private Binding<a> b;

    public RequestStreamingAdHttpRequest$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.RequestStreamingAdHttpRequest$Factory", "members/com.vungle.publisher.protocol.RequestStreamingAdHttpRequest$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.RequestStreamingAd$Factory", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.protocol.ProtocolHttpRequest$Factory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.g = (RequestStreamingAd.Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
