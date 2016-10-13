package com.vungle.publisher.net.http;

import android.content.Context;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.cd;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class HttpGateway$$InjectAdapter extends Binding<cd> implements MembersInjector<cd> {
    private Binding<Context> a;
    private Binding<ScheduledPriorityExecutor> b;

    public HttpGateway$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.net.http.HttpGateway", false, cd.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", cd.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", cd.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final void injectMembers(cd object) {
        object.c = (Context) this.a.get();
        object.d = (ScheduledPriorityExecutor) this.b.get();
    }
}
