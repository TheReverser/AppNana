package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.ArchiveEntry.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalArchive$$InjectAdapter extends Binding<LocalArchive> implements MembersInjector<LocalArchive>, Provider<LocalArchive> {
    private Binding<Factory> a;
    private Binding<Factory> b;
    private Binding<LocalAd.Factory> c;
    private Binding<LocalViewableDelegate> d;
    private Binding<Viewable> e;

    public LocalArchive$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalArchive", "members/com.vungle.publisher.db.model.LocalArchive", false, LocalArchive.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.LocalArchive$Factory", LocalArchive.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.ArchiveEntry$Factory", LocalArchive.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.db.model.LocalAd$Factory", LocalArchive.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.db.model.LocalViewableDelegate", LocalArchive.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.e = linker2.requestBinding("members/com.vungle.publisher.db.model.Viewable", LocalArchive.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
    }

    public final LocalArchive get() {
        LocalArchive localArchive = new LocalArchive();
        injectMembers(localArchive);
        return localArchive;
    }

    public final void injectMembers(LocalArchive object) {
        object.e = (Factory) this.a.get();
        object.f = (Factory) this.b.get();
        object.g = (LocalAd.Factory) this.c.get();
        object.h = (LocalViewableDelegate) this.d.get();
        this.e.injectMembers(object);
    }
}
