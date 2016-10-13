package com.vungle.publisher.protocol.message;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ExtraInfo$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<MessageFactory> a;

    public ExtraInfo$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.ExtraInfo$Factory", "members/com.vungle.publisher.protocol.message.ExtraInfo$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        Linker linker2 = linker;
        this.a = linker2.requestBinding("members/com.vungle.publisher.protocol.message.MessageFactory", Factory.class, getClass().getClassLoader(), false, true);
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
