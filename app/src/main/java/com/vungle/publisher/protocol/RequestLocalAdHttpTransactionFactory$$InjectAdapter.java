package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.RequestLocalAdHttpRequest.Factory;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestLocalAdHttpTransactionFactory$$InjectAdapter extends Binding<RequestLocalAdHttpTransactionFactory> implements MembersInjector<RequestLocalAdHttpTransactionFactory>, Provider<RequestLocalAdHttpTransactionFactory> {
    private Binding<Factory> a;
    private Binding<Lazy<RequestLocalAdHttpResponseHandler>> b;
    private Binding<HttpTransaction.Factory> c;

    public RequestLocalAdHttpTransactionFactory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.RequestLocalAdHttpTransactionFactory", "members/com.vungle.publisher.protocol.RequestLocalAdHttpTransactionFactory", true, RequestLocalAdHttpTransactionFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.RequestLocalAdHttpRequest$Factory", RequestLocalAdHttpTransactionFactory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("dagger.Lazy<com.vungle.publisher.protocol.RequestLocalAdHttpResponseHandler>", RequestLocalAdHttpTransactionFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpTransaction$Factory", RequestLocalAdHttpTransactionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final RequestLocalAdHttpTransactionFactory get() {
        RequestLocalAdHttpTransactionFactory requestLocalAdHttpTransactionFactory = new RequestLocalAdHttpTransactionFactory();
        injectMembers(requestLocalAdHttpTransactionFactory);
        return requestLocalAdHttpTransactionFactory;
    }

    public final void injectMembers(RequestLocalAdHttpTransactionFactory object) {
        object.a = (Factory) this.a.get();
        object.b = (Lazy) this.b.get();
        this.c.injectMembers(object);
    }
}
