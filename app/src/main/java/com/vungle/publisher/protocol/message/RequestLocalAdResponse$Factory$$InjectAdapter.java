package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.RequestAdResponse.a;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestLocalAdResponse$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<a> a;

    public RequestLocalAdResponse$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestLocalAdResponse$Factory", "members/com.vungle.publisher.protocol.message.RequestLocalAdResponse$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        Linker linker2 = linker;
        this.a = linker2.requestBinding("members/com.vungle.publisher.protocol.message.RequestAdResponse$Factory", Factory.class, getClass().getClassLoader(), false, true);
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
        this.a.injectMembers(object);
    }
}
