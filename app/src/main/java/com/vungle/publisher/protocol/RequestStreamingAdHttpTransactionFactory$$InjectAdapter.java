package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.RequestStreamingAdHttpRequest.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestStreamingAdHttpTransactionFactory$$InjectAdapter extends Binding<RequestStreamingAdHttpTransactionFactory> implements MembersInjector<RequestStreamingAdHttpTransactionFactory>, Provider<RequestStreamingAdHttpTransactionFactory> {
    private Binding<Factory> a;
    private Binding<RequestStreamingAdHttpResponseHandler> b;
    private Binding<HttpTransaction.Factory> c;

    public RequestStreamingAdHttpTransactionFactory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.RequestStreamingAdHttpTransactionFactory", "members/com.vungle.publisher.protocol.RequestStreamingAdHttpTransactionFactory", true, RequestStreamingAdHttpTransactionFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.RequestStreamingAdHttpRequest$Factory", RequestStreamingAdHttpTransactionFactory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.RequestStreamingAdHttpResponseHandler", RequestStreamingAdHttpTransactionFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpTransaction$Factory", RequestStreamingAdHttpTransactionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final RequestStreamingAdHttpTransactionFactory get() {
        RequestStreamingAdHttpTransactionFactory requestStreamingAdHttpTransactionFactory = new RequestStreamingAdHttpTransactionFactory();
        injectMembers(requestStreamingAdHttpTransactionFactory);
        return requestStreamingAdHttpTransactionFactory;
    }

    public final void injectMembers(RequestStreamingAdHttpTransactionFactory object) {
        object.a = (Factory) this.a.get();
        object.b = (RequestStreamingAdHttpResponseHandler) this.b.get();
        this.c.injectMembers(object);
    }
}
