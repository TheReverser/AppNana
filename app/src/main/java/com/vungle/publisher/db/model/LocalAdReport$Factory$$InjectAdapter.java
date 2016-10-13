package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.AdReport.BaseFactory;
import com.vungle.publisher.db.model.LocalAdReport.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalAdReport$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<LocalAd.Factory> a;
    private Binding<Factory> b;
    private Binding<Provider<LocalAdReport>> c;
    private Binding<BaseFactory> d;

    public LocalAdReport$Factory$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalAdReport$Factory", "members/com.vungle.publisher.db.model.LocalAdReport$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.LocalAd$Factory", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.LocalAdPlay$Factory", Factory.class, getClass().getClassLoader());
        this.c = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.db.model.LocalAdReport>", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.d = linker2.requestBinding("members/com.vungle.publisher.db.model.AdReport$BaseFactory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.b = (LocalAd.Factory) this.a.get();
        object.d = (Factory) this.b.get();
        object.e = (Provider) this.c.get();
        this.d.injectMembers(object);
    }
}
