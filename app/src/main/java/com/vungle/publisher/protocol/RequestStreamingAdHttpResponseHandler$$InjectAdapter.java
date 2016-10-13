package com.vungle.publisher.protocol;

import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.http.MaxRetryAgeHttpResponseHandler;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestStreamingAdHttpResponseHandler$$InjectAdapter extends Binding<RequestStreamingAdHttpResponseHandler> implements MembersInjector<RequestStreamingAdHttpResponseHandler>, Provider<RequestStreamingAdHttpResponseHandler> {
    private Binding<EventBus> a;
    private Binding<Factory> b;
    private Binding<MaxRetryAgeHttpResponseHandler> c;

    public RequestStreamingAdHttpResponseHandler$$InjectAdapter() {
        super("com.vungle.publisher.protocol.RequestStreamingAdHttpResponseHandler", "members/com.vungle.publisher.protocol.RequestStreamingAdHttpResponseHandler", true, RequestStreamingAdHttpResponseHandler.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.event.EventBus", RequestStreamingAdHttpResponseHandler.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.message.RequestStreamingAdResponse$Factory", RequestStreamingAdHttpResponseHandler.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.MaxRetryAgeHttpResponseHandler", RequestStreamingAdHttpResponseHandler.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final RequestStreamingAdHttpResponseHandler get() {
        RequestStreamingAdHttpResponseHandler requestStreamingAdHttpResponseHandler = new RequestStreamingAdHttpResponseHandler();
        injectMembers(requestStreamingAdHttpResponseHandler);
        return requestStreamingAdHttpResponseHandler;
    }

    public final void injectMembers(RequestStreamingAdHttpResponseHandler object) {
        object.a = (EventBus) this.a.get();
        object.b = (Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
