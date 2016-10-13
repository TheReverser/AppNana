package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.db.model.ArchiveEntry.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ArchiveEntry$$InjectAdapter extends Binding<ArchiveEntry> implements MembersInjector<ArchiveEntry>, Provider<ArchiveEntry> {
    private Binding<Factory> a;
    private Binding<Factory> b;
    private Binding<as> c;

    public ArchiveEntry$$InjectAdapter() {
        super("com.vungle.publisher.db.model.ArchiveEntry", "members/com.vungle.publisher.db.model.ArchiveEntry", false, ArchiveEntry.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.ArchiveEntry$Factory", ArchiveEntry.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.LocalArchive$Factory", ArchiveEntry.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel", ArchiveEntry.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final ArchiveEntry get() {
        ArchiveEntry archiveEntry = new ArchiveEntry();
        injectMembers(archiveEntry);
        return archiveEntry;
    }

    public final void injectMembers(ArchiveEntry object) {
        object.d = (Factory) this.a.get();
        object.e = (Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
