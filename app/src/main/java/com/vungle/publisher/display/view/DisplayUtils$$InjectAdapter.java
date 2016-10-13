package com.vungle.publisher.display.view;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class DisplayUtils$$InjectAdapter extends Binding<DisplayUtils> implements MembersInjector<DisplayUtils>, Provider<DisplayUtils> {
    private Binding<Context> a;

    public DisplayUtils$$InjectAdapter() {
        super("com.vungle.publisher.display.view.DisplayUtils", "members/com.vungle.publisher.display.view.DisplayUtils", true, DisplayUtils.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", DisplayUtils.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final DisplayUtils get() {
        DisplayUtils displayUtils = new DisplayUtils();
        injectMembers(displayUtils);
        return displayUtils;
    }

    public final void injectMembers(DisplayUtils object) {
        object.a = (Context) this.a.get();
    }
}
