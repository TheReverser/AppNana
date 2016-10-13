package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.HttpTransaction.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ReportAdHttpTransactionFactory$$InjectAdapter extends Binding<ReportAdHttpTransactionFactory> implements MembersInjector<ReportAdHttpTransactionFactory>, Provider<ReportAdHttpTransactionFactory> {
    private Binding<Factory> a;
    private Binding<ReportLocalAdHttpResponseHandler> b;
    private Binding<Factory> c;
    private Binding<ReportStreamingAdHttpResponseHandler> d;
    private Binding<Factory> e;

    public ReportAdHttpTransactionFactory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.ReportAdHttpTransactionFactory", "members/com.vungle.publisher.protocol.ReportAdHttpTransactionFactory", true, ReportAdHttpTransactionFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.ReportLocalAdHttpRequest$Factory", ReportAdHttpTransactionFactory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.ReportLocalAdHttpResponseHandler", ReportAdHttpTransactionFactory.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.protocol.ReportStreamingAdHttpRequest$Factory", ReportAdHttpTransactionFactory.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.protocol.ReportStreamingAdHttpResponseHandler", ReportAdHttpTransactionFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.e = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpTransaction$Factory", ReportAdHttpTransactionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
    }

    public final ReportAdHttpTransactionFactory get() {
        ReportAdHttpTransactionFactory reportAdHttpTransactionFactory = new ReportAdHttpTransactionFactory();
        injectMembers(reportAdHttpTransactionFactory);
        return reportAdHttpTransactionFactory;
    }

    public final void injectMembers(ReportAdHttpTransactionFactory object) {
        object.a = (Factory) this.a.get();
        object.b = (ReportLocalAdHttpResponseHandler) this.b.get();
        object.d = (Factory) this.c.get();
        object.e = (ReportStreamingAdHttpResponseHandler) this.d.get();
        this.e.injectMembers(object);
    }
}
