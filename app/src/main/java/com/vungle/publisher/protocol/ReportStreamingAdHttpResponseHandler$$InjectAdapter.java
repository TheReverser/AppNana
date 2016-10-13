package com.vungle.publisher.protocol;

import com.vungle.publisher.cj;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ReportStreamingAdHttpResponseHandler$$InjectAdapter extends Binding<ReportStreamingAdHttpResponseHandler> implements MembersInjector<ReportStreamingAdHttpResponseHandler>, Provider<ReportStreamingAdHttpResponseHandler> {
    private Binding<cj> a;

    public ReportStreamingAdHttpResponseHandler$$InjectAdapter() {
        super("com.vungle.publisher.protocol.ReportStreamingAdHttpResponseHandler", "members/com.vungle.publisher.protocol.ReportStreamingAdHttpResponseHandler", true, ReportStreamingAdHttpResponseHandler.class);
    }

    public final void attach(Linker linker) {
        Linker linker2 = linker;
        this.a = linker2.requestBinding("members/com.vungle.publisher.protocol.ReportAdHttpResponseHandler", ReportStreamingAdHttpResponseHandler.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final ReportStreamingAdHttpResponseHandler get() {
        ReportStreamingAdHttpResponseHandler reportStreamingAdHttpResponseHandler = new ReportStreamingAdHttpResponseHandler();
        injectMembers(reportStreamingAdHttpResponseHandler);
        return reportStreamingAdHttpResponseHandler;
    }

    public final void injectMembers(ReportStreamingAdHttpResponseHandler object) {
        this.a.injectMembers(object);
    }
}
