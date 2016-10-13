package com.vungle.publisher.env;

import android.content.Context;
import com.vungle.publisher.event.ClientEventListenerAdapter.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SdkConfig$$InjectAdapter extends Binding<SdkConfig> implements MembersInjector<SdkConfig>, Provider<SdkConfig> {
    private Binding<Factory> a;
    private Binding<Context> b;

    public SdkConfig$$InjectAdapter() {
        super("com.vungle.publisher.env.SdkConfig", "members/com.vungle.publisher.env.SdkConfig", true, SdkConfig.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.event.ClientEventListenerAdapter$Factory", SdkConfig.class, getClass().getClassLoader());
        this.b = linker.requestBinding("android.content.Context", SdkConfig.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final SdkConfig get() {
        SdkConfig sdkConfig = new SdkConfig();
        injectMembers(sdkConfig);
        return sdkConfig;
    }

    public final void injectMembers(SdkConfig object) {
        object.e = (Factory) this.a.get();
        object.f = (Context) this.b.get();
    }
}
