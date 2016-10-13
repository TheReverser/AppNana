package com.vungle.publisher.protocol;

import com.vungle.publisher.SafeBundleAdConfigFactory;
import com.vungle.publisher.cd;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ProtocolHttpGateway$$InjectAdapter extends Binding<ProtocolHttpGateway> implements MembersInjector<ProtocolHttpGateway>, Provider<ProtocolHttpGateway> {
    private Binding<EventBus> a;
    private Binding<PrepareLocalAdEventListener> b;
    private Binding<ReportAdHttpTransactionFactory> c;
    private Binding<HttpTransaction> d;
    private Binding<RequestLocalAdHttpTransactionFactory> e;
    private Binding<RequestStreamingAdHttpTransactionFactory> f;
    private Binding<SafeBundleAdConfigFactory> g;
    private Binding<SessionEndHttpTransactionFactory> h;
    private Binding<SessionStartHttpTransactionFactory> i;
    private Binding<Provider<HttpTransaction>> j;
    private Binding<UnfilledAdHttpTransactionFactory> k;
    private Binding<AdServiceReportingHandler> l;
    private Binding<cd> m;

    public ProtocolHttpGateway$$InjectAdapter() {
        super("com.vungle.publisher.protocol.ProtocolHttpGateway", "members/com.vungle.publisher.protocol.ProtocolHttpGateway", true, ProtocolHttpGateway.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.event.EventBus", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.ProtocolHttpGateway$PrepareLocalAdEventListener", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.protocol.ReportAdHttpTransactionFactory", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.d = linker.requestBinding("@com.vungle.publisher.inject.annotations.RequestConfigHttpTransaction()/com.vungle.publisher.net.http.HttpTransaction", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.protocol.RequestLocalAdHttpTransactionFactory", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.f = linker.requestBinding("com.vungle.publisher.protocol.RequestStreamingAdHttpTransactionFactory", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.g = linker.requestBinding("com.vungle.publisher.SafeBundleAdConfigFactory", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.h = linker.requestBinding("com.vungle.publisher.protocol.SessionEndHttpTransactionFactory", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.i = linker.requestBinding("com.vungle.publisher.protocol.SessionStartHttpTransactionFactory", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.j = linker.requestBinding("@com.vungle.publisher.inject.annotations.TrackInstallHttpTransaction()/javax.inject.Provider<com.vungle.publisher.net.http.HttpTransaction>", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.k = linker.requestBinding("com.vungle.publisher.protocol.UnfilledAdHttpTransactionFactory", ProtocolHttpGateway.class, getClass().getClassLoader());
        this.l = linker.requestBinding("com.vungle.publisher.reporting.AdServiceReportingHandler", ProtocolHttpGateway.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.m = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpGateway", ProtocolHttpGateway.class, getClass().getClassLoader(), false, true);
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
        injectMembersBindings.add(this.k);
        injectMembersBindings.add(this.l);
        injectMembersBindings.add(this.m);
    }

    public final ProtocolHttpGateway get() {
        ProtocolHttpGateway protocolHttpGateway = new ProtocolHttpGateway();
        injectMembers(protocolHttpGateway);
        return protocolHttpGateway;
    }

    public final void injectMembers(ProtocolHttpGateway object) {
        object.a = (EventBus) this.a.get();
        object.b = (PrepareLocalAdEventListener) this.b.get();
        object.e = (ReportAdHttpTransactionFactory) this.c.get();
        object.f = (HttpTransaction) this.d.get();
        object.g = (RequestLocalAdHttpTransactionFactory) this.e.get();
        object.h = (RequestStreamingAdHttpTransactionFactory) this.f.get();
        object.i = (SafeBundleAdConfigFactory) this.g.get();
        object.j = (SessionEndHttpTransactionFactory) this.h.get();
        object.k = (SessionStartHttpTransactionFactory) this.i.get();
        object.l = (Provider) this.j.get();
        object.m = (UnfilledAdHttpTransactionFactory) this.k.get();
        object.n = (AdServiceReportingHandler) this.l.get();
        this.m.injectMembers(object);
    }
}
