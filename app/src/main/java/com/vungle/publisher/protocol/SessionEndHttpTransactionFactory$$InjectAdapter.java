package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.SessionEndHttpRequest.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SessionEndHttpTransactionFactory$$InjectAdapter extends Binding<SessionEndHttpTransactionFactory> implements MembersInjector<SessionEndHttpTransactionFactory>, Provider<SessionEndHttpTransactionFactory> {
    private Binding<Factory> a;
    private Binding<FireAndForgetHttpResponseHandler> b;
    private Binding<HttpTransaction.Factory> c;

    public SessionEndHttpTransactionFactory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.SessionEndHttpTransactionFactory", "members/com.vungle.publisher.protocol.SessionEndHttpTransactionFactory", true, SessionEndHttpTransactionFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.SessionEndHttpRequest$Factory", SessionEndHttpTransactionFactory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler", SessionEndHttpTransactionFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpTransaction$Factory", SessionEndHttpTransactionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final SessionEndHttpTransactionFactory get() {
        SessionEndHttpTransactionFactory sessionEndHttpTransactionFactory = new SessionEndHttpTransactionFactory();
        injectMembers(sessionEndHttpTransactionFactory);
        return sessionEndHttpTransactionFactory;
    }

    public final void injectMembers(SessionEndHttpTransactionFactory object) {
        object.a = (Factory) this.a.get();
        object.b = (FireAndForgetHttpResponseHandler) this.b.get();
        this.c.injectMembers(object);
    }
}
