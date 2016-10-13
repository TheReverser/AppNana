package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.db.model.AdReportExtra.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdReportExtra$$InjectAdapter extends Binding<AdReportExtra> implements MembersInjector<AdReportExtra>, Provider<AdReportExtra> {
    private Binding<Factory> a;
    private Binding<as> b;

    public AdReportExtra$$InjectAdapter() {
        super("com.vungle.publisher.db.model.AdReportExtra", "members/com.vungle.publisher.db.model.AdReportExtra", false, AdReportExtra.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.AdReportExtra$Factory", AdReportExtra.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel", AdReportExtra.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final AdReportExtra get() {
        AdReportExtra adReportExtra = new AdReportExtra();
        injectMembers(adReportExtra);
        return adReportExtra;
    }

    public final void injectMembers(AdReportExtra object) {
        object.d = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
