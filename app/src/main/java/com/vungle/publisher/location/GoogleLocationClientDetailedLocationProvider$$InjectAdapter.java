package com.vungle.publisher.location;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class GoogleLocationClientDetailedLocationProvider$$InjectAdapter extends Binding<GoogleLocationClientDetailedLocationProvider> implements MembersInjector<GoogleLocationClientDetailedLocationProvider>, Provider<GoogleLocationClientDetailedLocationProvider> {
    private Binding<Context> a;
    private Binding<BaseGoogleDetailedLocationProvider> b;

    public GoogleLocationClientDetailedLocationProvider$$InjectAdapter() {
        super("com.vungle.publisher.location.GoogleLocationClientDetailedLocationProvider", "members/com.vungle.publisher.location.GoogleLocationClientDetailedLocationProvider", true, GoogleLocationClientDetailedLocationProvider.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", GoogleLocationClientDetailedLocationProvider.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.location.BaseGoogleDetailedLocationProvider", GoogleLocationClientDetailedLocationProvider.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final GoogleLocationClientDetailedLocationProvider get() {
        GoogleLocationClientDetailedLocationProvider googleLocationClientDetailedLocationProvider = new GoogleLocationClientDetailedLocationProvider();
        injectMembers(googleLocationClientDetailedLocationProvider);
        return googleLocationClientDetailedLocationProvider;
    }

    public final void injectMembers(GoogleLocationClientDetailedLocationProvider object) {
        object.b = (Context) this.a.get();
        this.b.injectMembers(object);
    }
}
