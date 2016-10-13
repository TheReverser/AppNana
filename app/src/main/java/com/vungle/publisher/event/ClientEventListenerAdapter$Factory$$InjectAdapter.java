package com.vungle.publisher.event;

import com.vungle.publisher.event.ClientEventListenerAdapter.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ClientEventListenerAdapter$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<Provider<ClientEventListenerAdapter>> a;

    public ClientEventListenerAdapter$Factory$$InjectAdapter() {
        super("com.vungle.publisher.event.ClientEventListenerAdapter$Factory", "members/com.vungle.publisher.event.ClientEventListenerAdapter$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.event.ClientEventListenerAdapter>", Factory.class, getClass().getClassLoader());
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
        object.a = (Provider) this.a.get();
    }
}
