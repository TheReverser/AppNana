package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.db.model.AdReportExtra.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class AdReport$$InjectAdapter extends Binding<AdReport> implements MembersInjector<AdReport> {
    private Binding<Factory> a;
    private Binding<as> b;

    public AdReport$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.db.model.AdReport", false, AdReport.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.AdReportExtra$Factory", AdReport.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel", AdReport.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final void injectMembers(AdReport object) {
        object.o = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
