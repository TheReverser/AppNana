package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.ReportAd.Play.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ReportLocalAd$Factory$PlayFactory$$InjectAdapter extends Binding<PlayFactory> implements MembersInjector<PlayFactory>, Provider<PlayFactory> {
    private Binding<UserActionFactory> a;
    private Binding<Factory> b;

    public ReportLocalAd$Factory$PlayFactory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory", "members/com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory", true, PlayFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory$UserActionFactory", PlayFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.protocol.message.ReportAd$Play$Factory", PlayFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final PlayFactory get() {
        PlayFactory playFactory = new PlayFactory();
        injectMembers(playFactory);
        return playFactory;
    }

    public final void injectMembers(PlayFactory object) {
        object.a = (UserActionFactory) this.a.get();
        this.b.injectMembers(object);
    }
}
