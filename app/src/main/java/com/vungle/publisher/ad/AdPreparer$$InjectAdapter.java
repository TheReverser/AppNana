package com.vungle.publisher.ad;

import android.content.Context;
import com.vungle.publisher.ad.prepare.PrepareAdRunnable.Factory;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdPreparer$$InjectAdapter extends Binding<AdPreparer> implements MembersInjector<AdPreparer>, Provider<AdPreparer> {
    private Binding<Context> a;
    private Binding<Factory> b;
    private Binding<ScheduledPriorityExecutor> c;

    public AdPreparer$$InjectAdapter() {
        super("com.vungle.publisher.ad.AdPreparer", "members/com.vungle.publisher.ad.AdPreparer", true, AdPreparer.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", AdPreparer.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.ad.prepare.PrepareAdRunnable$Factory", AdPreparer.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", AdPreparer.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final AdPreparer get() {
        AdPreparer adPreparer = new AdPreparer();
        injectMembers(adPreparer);
        return adPreparer;
    }

    public final void injectMembers(AdPreparer object) {
        object.a = (Context) this.a.get();
        object.b = (Factory) this.b.get();
        object.c = (ScheduledPriorityExecutor) this.c.get();
    }
}
