package com.vungle.publisher.net.http;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.cg;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class HttpResponseHandler$$InjectAdapter extends Binding<cg> implements MembersInjector<cg> {
    private Binding<ScheduledPriorityExecutor> a;

    public HttpResponseHandler$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.net.http.HttpResponseHandler", false, cg.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", cg.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final void injectMembers(cg object) {
        object.f = (ScheduledPriorityExecutor) this.a.get();
    }
}
