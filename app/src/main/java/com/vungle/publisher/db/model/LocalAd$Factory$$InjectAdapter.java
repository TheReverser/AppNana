package com.vungle.publisher.db.model;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LocalAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalAd$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<Provider<String>> a;
    private Binding<Factory> b;
    private Binding<Viewable.Factory> c;
    private Binding<Provider<LocalAd>> d;
    private Binding<LocalVideo.Factory> e;
    private Binding<ScheduledPriorityExecutor> f;
    private Binding<Ad.Factory> g;

    public LocalAd$Factory$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalAd$Factory", "members/com.vungle.publisher.db.model.LocalAd$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("@com.vungle.publisher.inject.annotations.AdTempDirectory()/javax.inject.Provider<java.lang.String>", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.LocalArchive$Factory", Factory.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.db.model.Viewable$Factory", Factory.class, getClass().getClassLoader());
        this.d = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.db.model.LocalAd>", Factory.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.db.model.LocalVideo$Factory", Factory.class, getClass().getClassLoader());
        this.f = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.g = linker2.requestBinding("members/com.vungle.publisher.db.model.Ad$Factory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
        injectMembersBindings.add(this.f);
        injectMembersBindings.add(this.g);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.d = (Provider) this.a.get();
        object.e = (Factory) this.b.get();
        object.f = (Viewable.Factory) this.c.get();
        object.g = (Provider) this.d.get();
        object.h = (LocalVideo.Factory) this.e.get();
        object.i = (ScheduledPriorityExecutor) this.f.get();
        this.g.injectMembers(object);
    }
}
