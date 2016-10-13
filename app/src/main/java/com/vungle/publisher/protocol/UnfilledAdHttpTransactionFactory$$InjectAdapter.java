package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.UnfilledAdHttpRequest.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class UnfilledAdHttpTransactionFactory$$InjectAdapter extends Binding<UnfilledAdHttpTransactionFactory> implements MembersInjector<UnfilledAdHttpTransactionFactory>, Provider<UnfilledAdHttpTransactionFactory> {
    private Binding<Factory> a;
    private Binding<FireAndForgetHttpResponseHandler> b;
    private Binding<HttpTransaction.Factory> c;

    public UnfilledAdHttpTransactionFactory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.UnfilledAdHttpTransactionFactory", "members/com.vungle.publisher.protocol.UnfilledAdHttpTransactionFactory", true, UnfilledAdHttpTransactionFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.UnfilledAdHttpRequest$Factory", UnfilledAdHttpTransactionFactory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler", UnfilledAdHttpTransactionFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpTransaction$Factory", UnfilledAdHttpTransactionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final UnfilledAdHttpTransactionFactory get() {
        UnfilledAdHttpTransactionFactory unfilledAdHttpTransactionFactory = new UnfilledAdHttpTransactionFactory();
        injectMembers(unfilledAdHttpTransactionFactory);
        return unfilledAdHttpTransactionFactory;
    }

    public final void injectMembers(UnfilledAdHttpTransactionFactory object) {
        object.a = (Factory) this.a.get();
        object.b = (FireAndForgetHttpResponseHandler) this.b.get();
        this.c.injectMembers(object);
    }
}
