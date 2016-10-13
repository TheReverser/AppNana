package com.vungle.publisher.net.http;

import com.vungle.publisher.net.http.TrackEventHttpRequest.Factory;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class TrackEventHttpTransactionFactory$$InjectAdapter extends Binding<TrackEventHttpTransactionFactory> implements MembersInjector<TrackEventHttpTransactionFactory>, Provider<TrackEventHttpTransactionFactory> {
    private Binding<Factory> a;
    private Binding<Lazy<TrackEventHttpResponseHandler>> b;
    private Binding<HttpTransaction.Factory> c;

    public TrackEventHttpTransactionFactory$$InjectAdapter() {
        super("com.vungle.publisher.net.http.TrackEventHttpTransactionFactory", "members/com.vungle.publisher.net.http.TrackEventHttpTransactionFactory", true, TrackEventHttpTransactionFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.net.http.TrackEventHttpRequest$Factory", TrackEventHttpTransactionFactory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("dagger.Lazy<com.vungle.publisher.net.http.TrackEventHttpResponseHandler>", TrackEventHttpTransactionFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpTransaction$Factory", TrackEventHttpTransactionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final TrackEventHttpTransactionFactory get() {
        TrackEventHttpTransactionFactory trackEventHttpTransactionFactory = new TrackEventHttpTransactionFactory();
        injectMembers(trackEventHttpTransactionFactory);
        return trackEventHttpTransactionFactory;
    }

    public final void injectMembers(TrackEventHttpTransactionFactory object) {
        object.a = (Factory) this.a.get();
        object.b = (Lazy) this.b.get();
        this.c.injectMembers(object);
    }
}
