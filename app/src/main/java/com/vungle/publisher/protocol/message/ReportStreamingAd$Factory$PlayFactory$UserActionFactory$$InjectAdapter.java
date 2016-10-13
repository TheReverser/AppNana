package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.ReportAd.Play.UserAction.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ReportStreamingAd$Factory$PlayFactory$UserActionFactory$$InjectAdapter extends Binding<UserActionFactory> implements MembersInjector<UserActionFactory>, Provider<UserActionFactory> {
    private Binding<Factory> a;

    public ReportStreamingAd$Factory$PlayFactory$UserActionFactory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.ReportStreamingAd$Factory$PlayFactory$UserActionFactory", "members/com.vungle.publisher.protocol.message.ReportStreamingAd$Factory$PlayFactory$UserActionFactory", true, UserActionFactory.class);
    }

    public final void attach(Linker linker) {
        Linker linker2 = linker;
        this.a = linker2.requestBinding("members/com.vungle.publisher.protocol.message.ReportAd$Play$UserAction$Factory", UserActionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final UserActionFactory get() {
        UserActionFactory userActionFactory = new UserActionFactory();
        injectMembers(userActionFactory);
        return userActionFactory;
    }

    public final void injectMembers(UserActionFactory object) {
        this.a.injectMembers(object);
    }
}
