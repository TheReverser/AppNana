package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SafeBundleAdConfigFactory$$InjectAdapter extends Binding<SafeBundleAdConfigFactory> implements MembersInjector<SafeBundleAdConfigFactory>, Provider<SafeBundleAdConfigFactory> {
    private Binding<AdConfig> a;

    public SafeBundleAdConfigFactory$$InjectAdapter() {
        super("com.vungle.publisher.SafeBundleAdConfigFactory", "members/com.vungle.publisher.SafeBundleAdConfigFactory", true, SafeBundleAdConfigFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.AdConfig", SafeBundleAdConfigFactory.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final SafeBundleAdConfigFactory get() {
        SafeBundleAdConfigFactory safeBundleAdConfigFactory = new SafeBundleAdConfigFactory();
        injectMembers(safeBundleAdConfigFactory);
        return safeBundleAdConfigFactory;
    }

    public final void injectMembers(SafeBundleAdConfigFactory object) {
        object.a = (AdConfig) this.a.get();
    }
}
