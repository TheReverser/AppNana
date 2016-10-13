package com.vungle.publisher.protocol;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.AdPreparer;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse.Factory;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestLocalAdHttpResponseHandler$$InjectAdapter extends Binding<RequestLocalAdHttpResponseHandler> implements MembersInjector<RequestLocalAdHttpResponseHandler>, Provider<RequestLocalAdHttpResponseHandler> {
    private Binding<AdPreparer> a;
    private Binding<AdServiceReportingHandler> b;
    private Binding<EventBus> c;
    private Binding<EventTrackingHttpLogEntryDeleteDelegate> d;
    private Binding<Lazy<AdManager>> e;
    private Binding<Lazy<SdkState>> f;
    private Binding<Factory> g;
    private Binding<ScheduledPriorityExecutor> h;
    private Binding<ProtocolHttpGateway> i;
    private Binding<InfiniteRetryHttpResponseHandler> j;

    public RequestLocalAdHttpResponseHandler$$InjectAdapter() {
        super("com.vungle.publisher.protocol.RequestLocalAdHttpResponseHandler", "members/com.vungle.publisher.protocol.RequestLocalAdHttpResponseHandler", true, RequestLocalAdHttpResponseHandler.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.ad.AdPreparer", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.reporting.AdServiceReportingHandler", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.event.EventBus", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.protocol.EventTrackingHttpLogEntryDeleteDelegate", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        this.e = linker.requestBinding("dagger.Lazy<com.vungle.publisher.ad.AdManager>", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        this.f = linker.requestBinding("dagger.Lazy<com.vungle.publisher.env.SdkState>", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        this.g = linker.requestBinding("com.vungle.publisher.protocol.message.RequestLocalAdResponse$Factory", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        this.h = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        this.i = linker.requestBinding("com.vungle.publisher.protocol.ProtocolHttpGateway", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.j = linker2.requestBinding("members/com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler", RequestLocalAdHttpResponseHandler.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
        injectMembersBindings.add(this.f);
        injectMembersBindings.add(this.g);
        injectMembersBindings.add(this.h);
        injectMembersBindings.add(this.i);
        injectMembersBindings.add(this.j);
    }

    public final RequestLocalAdHttpResponseHandler get() {
        RequestLocalAdHttpResponseHandler requestLocalAdHttpResponseHandler = new RequestLocalAdHttpResponseHandler();
        injectMembers(requestLocalAdHttpResponseHandler);
        return requestLocalAdHttpResponseHandler;
    }

    public final void injectMembers(RequestLocalAdHttpResponseHandler object) {
        object.a = (AdPreparer) this.a.get();
        object.b = (AdServiceReportingHandler) this.b.get();
        object.c = (EventBus) this.c.get();
        object.d = (EventTrackingHttpLogEntryDeleteDelegate) this.d.get();
        object.e = (Lazy) this.e.get();
        object.j = (Lazy) this.f.get();
        object.k = (Factory) this.g.get();
        object.l = (ScheduledPriorityExecutor) this.h.get();
        object.m = (ProtocolHttpGateway) this.i.get();
        this.j.injectMembers(object);
    }
}
