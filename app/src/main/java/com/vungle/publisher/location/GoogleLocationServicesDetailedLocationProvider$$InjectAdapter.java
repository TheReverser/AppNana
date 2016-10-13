package com.vungle.publisher.location;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class GoogleLocationServicesDetailedLocationProvider$$InjectAdapter extends Binding<GoogleLocationServicesDetailedLocationProvider> implements MembersInjector<GoogleLocationServicesDetailedLocationProvider>, Provider<GoogleLocationServicesDetailedLocationProvider> {
    private Binding<Context> a;
    private Binding<BaseGoogleDetailedLocationProvider> b;

    public GoogleLocationServicesDetailedLocationProvider$$InjectAdapter() {
        super("com.vungle.publisher.location.GoogleLocationServicesDetailedLocationProvider", "members/com.vungle.publisher.location.GoogleLocationServicesDetailedLocationProvider", true, GoogleLocationServicesDetailedLocationProvider.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", GoogleLocationServicesDetailedLocationProvider.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.location.BaseGoogleDetailedLocationProvider", GoogleLocationServicesDetailedLocationProvider.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final GoogleLocationServicesDetailedLocationProvider get() {
        GoogleLocationServicesDetailedLocationProvider googleLocationServicesDetailedLocationProvider = new GoogleLocationServicesDetailedLocationProvider();
        injectMembers(googleLocationServicesDetailedLocationProvider);
        return googleLocationServicesDetailedLocationProvider;
    }

    public final void injectMembers(GoogleLocationServicesDetailedLocationProvider object) {
        object.b = (Context) this.a.get();
        this.b.injectMembers(object);
    }
}
