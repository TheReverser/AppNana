package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestConfigHttpRequest$$InjectAdapter extends Binding<RequestConfigHttpRequest> implements MembersInjector<RequestConfigHttpRequest>, Provider<RequestConfigHttpRequest> {
    private Binding<ProtocolHttpRequest> a;

    public RequestConfigHttpRequest$$InjectAdapter() {
        super("com.vungle.publisher.protocol.RequestConfigHttpRequest", "members/com.vungle.publisher.protocol.RequestConfigHttpRequest", true, RequestConfigHttpRequest.class);
    }

    public final void attach(Linker linker) {
        Linker linker2 = linker;
        this.a = linker2.requestBinding("members/com.vungle.publisher.protocol.ProtocolHttpRequest", RequestConfigHttpRequest.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final RequestConfigHttpRequest get() {
        RequestConfigHttpRequest requestConfigHttpRequest = new RequestConfigHttpRequest();
        injectMembers(requestConfigHttpRequest);
        return requestConfigHttpRequest;
    }

    public final void injectMembers(RequestConfigHttpRequest object) {
        this.a.injectMembers(object);
    }
}
