package com.vungle.publisher.protocol;

import com.vungle.publisher.ap;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestConfigAsync$$InjectAdapter extends Binding<RequestConfigAsync> implements MembersInjector<RequestConfigAsync>, Provider<RequestConfigAsync> {
    private Binding<RequestConfigRunnable> a;
    private Binding<ap> b;

    public RequestConfigAsync$$InjectAdapter() {
        super("com.vungle.publisher.protocol.RequestConfigAsync", "members/com.vungle.publisher.protocol.RequestConfigAsync", true, RequestConfigAsync.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.RequestConfigAsync$RequestConfigRunnable", RequestConfigAsync.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.async.ExecutorAsync", RequestConfigAsync.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final RequestConfigAsync get() {
        RequestConfigAsync requestConfigAsync = new RequestConfigAsync();
        injectMembers(requestConfigAsync);
        return requestConfigAsync;
    }

    public final void injectMembers(RequestConfigAsync object) {
        object.b = (RequestConfigRunnable) this.a.get();
        this.b.injectMembers(object);
    }
}
