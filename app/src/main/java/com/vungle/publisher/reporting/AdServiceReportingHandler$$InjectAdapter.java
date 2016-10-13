package com.vungle.publisher.reporting;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdServiceReportingHandler$$InjectAdapter extends Binding<AdServiceReportingHandler> implements Provider<AdServiceReportingHandler> {
    public AdServiceReportingHandler$$InjectAdapter() {
        super("com.vungle.publisher.reporting.AdServiceReportingHandler", "members/com.vungle.publisher.reporting.AdServiceReportingHandler", true, AdServiceReportingHandler.class);
    }

    public final AdServiceReportingHandler get() {
        return new AdServiceReportingHandler();
    }
}
