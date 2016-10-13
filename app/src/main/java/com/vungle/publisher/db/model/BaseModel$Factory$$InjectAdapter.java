package com.vungle.publisher.db.model;

import com.vungle.publisher.as.a;
import com.vungle.publisher.db.DatabaseHelper;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class BaseModel$Factory$$InjectAdapter extends Binding<a> implements MembersInjector<a> {
    private Binding<DatabaseHelper> a;

    public BaseModel$Factory$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.db.model.BaseModel$Factory", false, a.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.DatabaseHelper", a.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final void injectMembers(a object) {
        object.c = (DatabaseHelper) this.a.get();
    }
}
