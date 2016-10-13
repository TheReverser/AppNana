package com.vungle.publisher.protocol.message;

import com.vungle.publisher.Demographic;
import com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory;
import com.vungle.publisher.protocol.message.RequestAd.Demographic.Location;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestAd$Demographic$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<Demographic> a;
    private Binding<Location.Factory> b;
    private Binding<MessageFactory> c;

    public RequestAd$Demographic$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestAd$Demographic$Factory", "members/com.vungle.publisher.protocol.message.RequestAd$Demographic$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.Demographic", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.message.RequestAd$Demographic$Location$Factory", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.protocol.message.MessageFactory", Factory.class, getClass().getClassLoader(), false, true);
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
        object.a = (Demographic) this.a.get();
        object.b = (Location.Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
