package com.vungle.publisher.reporting;

import com.vungle.publisher.bk;
import com.vungle.publisher.cc;
import com.vungle.publisher.db.model.AdReport.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdReportEventListener$$InjectAdapter extends Binding<AdReportEventListener> implements MembersInjector<AdReportEventListener>, Provider<AdReportEventListener> {
    private Binding<AdServiceReportingHandler> a;
    private Binding<Factory> b;
    private Binding<AdReportManager> c;
    private Binding<cc> d;
    private Binding<bk> e;

    public AdReportEventListener$$InjectAdapter() {
        super("com.vungle.publisher.reporting.AdReportEventListener", "members/com.vungle.publisher.reporting.AdReportEventListener", true, AdReportEventListener.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.reporting.AdServiceReportingHandler", AdReportEventListener.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.AdReport$Factory", AdReportEventListener.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.reporting.AdReportManager", AdReportEventListener.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.cc", AdReportEventListener.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.e = linker2.requestBinding("members/com.vungle.publisher.event.BaseEventListener", AdReportEventListener.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
    }

    public final AdReportEventListener get() {
        AdReportEventListener adReportEventListener = new AdReportEventListener();
        injectMembers(adReportEventListener);
        return adReportEventListener;
    }

    public final void injectMembers(AdReportEventListener object) {
        object.c = (AdServiceReportingHandler) this.a.get();
        object.d = (Factory) this.b.get();
        object.e = (AdReportManager) this.c.get();
        object.g = (cc) this.d.get();
        this.e.injectMembers(object);
    }
}
