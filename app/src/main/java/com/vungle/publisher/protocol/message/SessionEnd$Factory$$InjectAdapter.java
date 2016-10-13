package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.SessionEnd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SessionEnd$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<SessionStart.Factory> a;

    public SessionEnd$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.SessionEnd$Factory", "members/com.vungle.publisher.protocol.message.SessionEnd$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.SessionStart$Factory", Factory.class, getClass().getClassLoader());
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
        object.a = (SessionStart.Factory) this.a.get();
    }
}
