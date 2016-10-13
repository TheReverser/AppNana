package com.vungle.publisher.ad.prepare;

import com.vungle.publisher.ad.prepare.PrepareAdRunnable.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class PrepareAdRunnable$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<Provider<PrepareAdRunnable>> a;

    public PrepareAdRunnable$Factory$$InjectAdapter() {
        super("com.vungle.publisher.ad.prepare.PrepareAdRunnable$Factory", "members/com.vungle.publisher.ad.prepare.PrepareAdRunnable$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.ad.prepare.PrepareAdRunnable>", Factory.class, getClass().getClassLoader());
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
