package com.vungle.publisher.display.view;

import com.vungle.publisher.bf;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class WebViewFragment$$InjectAdapter extends Binding<WebViewFragment> implements MembersInjector<WebViewFragment> {
    private Binding<bf> a;
    private Binding<AdFragment> b;

    public WebViewFragment$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.display.view.WebViewFragment", false, WebViewFragment.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bf", WebViewFragment.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.display.view.AdFragment", WebViewFragment.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final void injectMembers(WebViewFragment object) {
        object.c = (bf) this.a.get();
        this.b.injectMembers(object);
    }
}
