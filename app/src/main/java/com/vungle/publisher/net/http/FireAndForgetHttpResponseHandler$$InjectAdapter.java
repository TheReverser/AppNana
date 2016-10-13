package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class FireAndForgetHttpResponseHandler$$InjectAdapter extends Binding<FireAndForgetHttpResponseHandler> implements MembersInjector<FireAndForgetHttpResponseHandler>, Provider<FireAndForgetHttpResponseHandler> {
    private Binding<MaxRetryAgeHttpResponseHandler> a;

    public FireAndForgetHttpResponseHandler$$InjectAdapter() {
        super("com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler", "members/com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler", true, FireAndForgetHttpResponseHandler.class);
    }

    public final void attach(Linker linker) {
        Linker linker2 = linker;
        this.a = linker2.requestBinding("members/com.vungle.publisher.net.http.MaxRetryAgeHttpResponseHandler", FireAndForgetHttpResponseHandler.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final FireAndForgetHttpResponseHandler get() {
        FireAndForgetHttpResponseHandler fireAndForgetHttpResponseHandler = new FireAndForgetHttpResponseHandler();
        injectMembers(fireAndForgetHttpResponseHandler);
        return fireAndForgetHttpResponseHandler;
    }

    public final void injectMembers(FireAndForgetHttpResponseHandler object) {
        this.a.injectMembers(object);
    }
}
