package com.vungle.publisher.protocol;

import com.vungle.publisher.ck;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler;
import com.vungle.publisher.protocol.message.RequestConfigResponse.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestConfigHttpResponseHandler$$InjectAdapter extends Binding<ck> implements MembersInjector<ck>, Provider<ck> {
    private Binding<Factory> a;
    private Binding<SdkConfig> b;
    private Binding<Provider<RequestConfigAsync>> c;
    private Binding<InfiniteRetryHttpResponseHandler> d;

    public RequestConfigHttpResponseHandler$$InjectAdapter() {
        super("com.vungle.publisher.ck", "members/com.vungle.publisher.protocol.RequestConfigHttpResponseHandler", false, ck.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.RequestConfigResponse$Factory", ck.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.env.SdkConfig", ck.class, getClass().getClassLoader());
        this.c = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.protocol.RequestConfigAsync>", ck.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.d = linker2.requestBinding("members/com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler", ck.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
    }

    public final ck get() {
        ck ckVar = new ck();
        injectMembers(ckVar);
        return ckVar;
    }

    public final void injectMembers(ck object) {
        object.a = (Factory) this.a.get();
        object.b = (SdkConfig) this.b.get();
        object.c = (Provider) this.c.get();
        this.d.injectMembers(object);
    }
}
