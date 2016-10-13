package com.vungle.publisher.ad.prepare;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class PrepareAdRunnable$$InjectAdapter extends Binding<PrepareAdRunnable> implements MembersInjector<PrepareAdRunnable>, Provider<PrepareAdRunnable> {
    private Binding<EventBus> a;
    private Binding<AdManager> b;
    private Binding<AdReportManager> c;

    public PrepareAdRunnable$$InjectAdapter() {
        super("com.vungle.publisher.ad.prepare.PrepareAdRunnable", "members/com.vungle.publisher.ad.prepare.PrepareAdRunnable", false, PrepareAdRunnable.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.event.EventBus", PrepareAdRunnable.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.ad.AdManager", PrepareAdRunnable.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.reporting.AdReportManager", PrepareAdRunnable.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final PrepareAdRunnable get() {
        PrepareAdRunnable prepareAdRunnable = new PrepareAdRunnable();
        injectMembers(prepareAdRunnable);
        return prepareAdRunnable;
    }

    public final void injectMembers(PrepareAdRunnable object) {
        object.a = (EventBus) this.a.get();
        object.b = (AdManager) this.b.get();
        object.c = (AdReportManager) this.c.get();
    }
}
