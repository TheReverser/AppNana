package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.SessionStartHttpRequest.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SessionStartHttpTransactionFactory$$InjectAdapter extends Binding<SessionStartHttpTransactionFactory> implements MembersInjector<SessionStartHttpTransactionFactory>, Provider<SessionStartHttpTransactionFactory> {
    private Binding<Factory> a;
    private Binding<FireAndForgetHttpResponseHandler> b;
    private Binding<HttpTransaction.Factory> c;

    public SessionStartHttpTransactionFactory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.SessionStartHttpTransactionFactory", "members/com.vungle.publisher.protocol.SessionStartHttpTransactionFactory", true, SessionStartHttpTransactionFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.SessionStartHttpRequest$Factory", SessionStartHttpTransactionFactory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler", SessionStartHttpTransactionFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpTransaction$Factory", SessionStartHttpTransactionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final SessionStartHttpTransactionFactory get() {
        SessionStartHttpTransactionFactory sessionStartHttpTransactionFactory = new SessionStartHttpTransactionFactory();
        injectMembers(sessionStartHttpTransactionFactory);
        return sessionStartHttpTransactionFactory;
    }

    public final void injectMembers(SessionStartHttpTransactionFactory object) {
        object.a = (Factory) this.a.get();
        object.b = (FireAndForgetHttpResponseHandler) this.b.get();
        this.c.injectMembers(object);
    }
}
