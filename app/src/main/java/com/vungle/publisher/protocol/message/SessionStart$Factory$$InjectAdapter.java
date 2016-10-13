package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bf;
import com.vungle.publisher.bh;
import com.vungle.publisher.protocol.message.SessionStart.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SessionStart$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<bf> a;
    private Binding<bh> b;

    public SessionStart$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.SessionStart$Factory", "members/com.vungle.publisher.protocol.message.SessionStart$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bf", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.bh", Factory.class, getClass().getClassLoader());
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
        object.a = (bf) this.a.get();
        object.b = (bh) this.b.get();
    }
}
