package com.vungle.publisher.async;

import com.vungle.publisher.ap;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class ExecutorAsync$$InjectAdapter extends Binding<ap> implements MembersInjector<ap> {
    private Binding<ScheduledPriorityExecutor> a;

    public ExecutorAsync$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.async.ExecutorAsync", false, ap.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", ap.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final void injectMembers(ap object) {
        object.a = (ScheduledPriorityExecutor) this.a.get();
    }
}
