package com.vungle.publisher.file;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CacheManager$$InjectAdapter extends Binding<CacheManager> implements MembersInjector<CacheManager>, Provider<CacheManager> {
    private Binding<Provider<String>> a;
    private Binding<Provider<String>> b;

    public CacheManager$$InjectAdapter() {
        super("com.vungle.publisher.file.CacheManager", "members/com.vungle.publisher.file.CacheManager", true, CacheManager.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("@com.vungle.publisher.inject.annotations.AdTempDirectory()/javax.inject.Provider<java.lang.String>", CacheManager.class, getClass().getClassLoader());
        this.b = linker.requestBinding("@com.vungle.publisher.inject.annotations.OldAdTempDirectory()/javax.inject.Provider<java.lang.String>", CacheManager.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final CacheManager get() {
        CacheManager cacheManager = new CacheManager();
        injectMembers(cacheManager);
        return cacheManager;
    }

    public final void injectMembers(CacheManager object) {
        object.a = (Provider) this.a.get();
        object.b = (Provider) this.b.get();
    }
}
