package com.vungle.publisher;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: vungle */
public final class Demographic$$InjectAdapter extends Binding<Demographic> implements Provider<Demographic> {
    public Demographic$$InjectAdapter() {
        super("com.vungle.publisher.Demographic", "members/com.vungle.publisher.Demographic", true, Demographic.class);
    }

    public final Demographic get() {
        return new Demographic();
    }
}
