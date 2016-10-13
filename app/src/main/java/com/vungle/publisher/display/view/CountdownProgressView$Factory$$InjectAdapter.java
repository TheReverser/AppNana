package com.vungle.publisher.display.view;

import com.vungle.publisher.image.BitmapFactory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CountdownProgressView$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<BitmapFactory> a;

    public CountdownProgressView$Factory$$InjectAdapter() {
        super("com.vungle.publisher.display.view.CountdownProgressView$Factory", "members/com.vungle.publisher.display.view.CountdownProgressView$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.image.BitmapFactory", Factory.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.a = (BitmapFactory) this.a.get();
    }
}
