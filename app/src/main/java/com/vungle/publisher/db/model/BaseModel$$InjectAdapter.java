package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.db.DatabaseHelper;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class BaseModel$$InjectAdapter extends Binding<as> implements MembersInjector<as> {
    private Binding<DatabaseHelper> a;

    public BaseModel$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.db.model.BaseModel", false, as.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.DatabaseHelper", as.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final void injectMembers(as object) {
        object.t = (DatabaseHelper) this.a.get();
    }
}
