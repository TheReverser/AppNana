package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bh;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestConfig$$InjectAdapter extends Binding<RequestConfig> implements MembersInjector<RequestConfig>, Provider<RequestConfig> {
    private Binding<bh> a;
    private Binding<BaseJsonSerializable> b;

    public RequestConfig$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestConfig", "members/com.vungle.publisher.protocol.message.RequestConfig", true, RequestConfig.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bh", RequestConfig.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.protocol.message.BaseJsonSerializable", RequestConfig.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final RequestConfig get() {
        RequestConfig requestConfig = new RequestConfig();
        injectMembers(requestConfig);
        return requestConfig;
    }

    public final void injectMembers(RequestConfig object) {
        object.a = (bh) this.a.get();
        this.b.injectMembers(object);
    }
}
