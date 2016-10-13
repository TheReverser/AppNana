package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bz;
import com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestAd$Demographic$Location$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<bz> a;
    private Binding<MessageFactory> b;

    public RequestAd$Demographic$Location$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestAd$Demographic$Location$Factory", "members/com.vungle.publisher.protocol.message.RequestAd$Demographic$Location$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bz", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.protocol.message.MessageFactory", Factory.class, getClass().getClassLoader(), false, true);
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
        object.a = (bz) this.a.get();
        this.b.injectMembers(object);
    }
}
