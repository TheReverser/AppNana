package com.vungle.publisher.protocol;

import com.vungle.publisher.cj;
import com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class ReportAdHttpResponseHandler$$InjectAdapter extends Binding<cj> implements MembersInjector<cj> {
    private Binding<Lazy<AdReportManager>> a;
    private Binding<InfiniteRetryHttpResponseHandler> b;

    public ReportAdHttpResponseHandler$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.protocol.ReportAdHttpResponseHandler", false, cj.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("dagger.Lazy<com.vungle.publisher.reporting.AdReportManager>", cj.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler", cj.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final void injectMembers(cj object) {
        object.a = (Lazy) this.a.get();
        this.b.injectMembers(object);
    }
}
