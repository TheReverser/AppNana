package com.vungle.publisher;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdConfig$$InjectAdapter extends Binding<AdConfig> implements Provider<AdConfig> {
    public AdConfig$$InjectAdapter() {
        super("com.vungle.publisher.AdConfig", "members/com.vungle.publisher.AdConfig", true, AdConfig.class);
    }

    public final AdConfig get() {
        return new AdConfig();
    }
}
