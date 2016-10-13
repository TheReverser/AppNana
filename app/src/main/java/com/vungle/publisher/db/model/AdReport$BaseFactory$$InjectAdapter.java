package com.vungle.publisher.db.model;

import com.vungle.publisher.as.a;
import com.vungle.publisher.db.model.AdReport.BaseFactory;
import com.vungle.publisher.db.model.AdReportExtra.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class AdReport$BaseFactory$$InjectAdapter extends Binding<BaseFactory> implements MembersInjector<BaseFactory> {
    private Binding<Factory> a;
    private Binding<a> b;

    public AdReport$BaseFactory$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.db.model.AdReport$BaseFactory", false, BaseFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.AdReportExtra$Factory", BaseFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel$Factory", BaseFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final void injectMembers(BaseFactory object) {
        object.a = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
