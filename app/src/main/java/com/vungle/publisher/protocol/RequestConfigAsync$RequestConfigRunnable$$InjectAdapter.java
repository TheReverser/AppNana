package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestConfigAsync$RequestConfigRunnable$$InjectAdapter extends Binding<RequestConfigRunnable> implements MembersInjector<RequestConfigRunnable>, Provider<RequestConfigRunnable> {
    private Binding<ProtocolHttpGateway> a;

    public RequestConfigAsync$RequestConfigRunnable$$InjectAdapter() {
        super("com.vungle.publisher.protocol.RequestConfigAsync$RequestConfigRunnable", "members/com.vungle.publisher.protocol.RequestConfigAsync$RequestConfigRunnable", true, RequestConfigRunnable.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.ProtocolHttpGateway", RequestConfigRunnable.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final RequestConfigRunnable get() {
        RequestConfigRunnable requestConfigRunnable = new RequestConfigRunnable();
        injectMembers(requestConfigRunnable);
        return requestConfigRunnable;
    }

    public final void injectMembers(RequestConfigRunnable object) {
        object.a = (ProtocolHttpGateway) this.a.get();
    }
}
