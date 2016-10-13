package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.LocalAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalAd$$InjectAdapter extends Binding<LocalAd> implements MembersInjector<LocalAd>, Provider<LocalAd> {
    private Binding<Factory> a;
    private Binding<Ad> b;

    public LocalAd$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalAd", "members/com.vungle.publisher.db.model.LocalAd", false, LocalAd.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.LocalAd$Factory", LocalAd.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.Ad", LocalAd.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final LocalAd get() {
        LocalAd localAd = new LocalAd();
        injectMembers(localAd);
        return localAd;
    }

    public final void injectMembers(LocalAd object) {
        object.D = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
