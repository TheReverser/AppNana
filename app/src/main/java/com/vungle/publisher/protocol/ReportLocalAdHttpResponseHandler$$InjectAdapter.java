package com.vungle.publisher.protocol;

import com.vungle.publisher.cj;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ReportLocalAdHttpResponseHandler$$InjectAdapter extends Binding<ReportLocalAdHttpResponseHandler> implements MembersInjector<ReportLocalAdHttpResponseHandler>, Provider<ReportLocalAdHttpResponseHandler> {
    private Binding<EventTrackingHttpLogEntryDeleteDelegate> a;
    private Binding<cj> b;

    public ReportLocalAdHttpResponseHandler$$InjectAdapter() {
        super("com.vungle.publisher.protocol.ReportLocalAdHttpResponseHandler", "members/com.vungle.publisher.protocol.ReportLocalAdHttpResponseHandler", true, ReportLocalAdHttpResponseHandler.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.EventTrackingHttpLogEntryDeleteDelegate", ReportLocalAdHttpResponseHandler.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.protocol.ReportAdHttpResponseHandler", ReportLocalAdHttpResponseHandler.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final ReportLocalAdHttpResponseHandler get() {
        ReportLocalAdHttpResponseHandler reportLocalAdHttpResponseHandler = new ReportLocalAdHttpResponseHandler();
        injectMembers(reportLocalAdHttpResponseHandler);
        return reportLocalAdHttpResponseHandler;
    }

    public final void injectMembers(ReportLocalAdHttpResponseHandler object) {
        object.b = (EventTrackingHttpLogEntryDeleteDelegate) this.a.get();
        this.b.injectMembers(object);
    }
}
