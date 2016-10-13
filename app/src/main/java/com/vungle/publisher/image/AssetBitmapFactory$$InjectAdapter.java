package com.vungle.publisher.image;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AssetBitmapFactory$$InjectAdapter extends Binding<AssetBitmapFactory> implements MembersInjector<AssetBitmapFactory>, Provider<AssetBitmapFactory> {
    private Binding<Context> a;
    private Binding<BaseBitmapFactory> b;

    public AssetBitmapFactory$$InjectAdapter() {
        super("com.vungle.publisher.image.AssetBitmapFactory", "members/com.vungle.publisher.image.AssetBitmapFactory", true, AssetBitmapFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", AssetBitmapFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.image.BaseBitmapFactory", AssetBitmapFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final AssetBitmapFactory get() {
        AssetBitmapFactory assetBitmapFactory = new AssetBitmapFactory();
        injectMembers(assetBitmapFactory);
        return assetBitmapFactory;
    }

    public final void injectMembers(AssetBitmapFactory object) {
        object.a = (Context) this.a.get();
        this.b.injectMembers(object);
    }
}
