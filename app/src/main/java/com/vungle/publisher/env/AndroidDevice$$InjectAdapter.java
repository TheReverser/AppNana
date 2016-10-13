package com.vungle.publisher.env;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.view.WindowManager;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AndroidDevice$$InjectAdapter extends Binding<AndroidDevice> implements MembersInjector<AndroidDevice>, Provider<AndroidDevice> {
    private Binding<AudioManager> a;
    private Binding<WindowManager> b;
    private Binding<Context> c;
    private Binding<SharedPreferences> d;
    private Binding<DeviceIdStrategy> e;

    public AndroidDevice$$InjectAdapter() {
        super("com.vungle.publisher.env.AndroidDevice", "members/com.vungle.publisher.env.AndroidDevice", true, AndroidDevice.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.media.AudioManager", AndroidDevice.class, getClass().getClassLoader());
        this.b = linker.requestBinding("android.view.WindowManager", AndroidDevice.class, getClass().getClassLoader());
        this.c = linker.requestBinding("android.content.Context", AndroidDevice.class, getClass().getClassLoader());
        this.d = linker.requestBinding("@com.vungle.publisher.inject.annotations.EnvSharedPreferences()/android.content.SharedPreferences", AndroidDevice.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.env.AndroidDevice$DeviceIdStrategy", AndroidDevice.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
    }

    public final AndroidDevice get() {
        AndroidDevice androidDevice = new AndroidDevice();
        injectMembers(androidDevice);
        return androidDevice;
    }

    public final void injectMembers(AndroidDevice object) {
        object.g = (AudioManager) this.a.get();
        object.h = (WindowManager) this.b.get();
        object.i = (Context) this.c.get();
        object.j = (SharedPreferences) this.d.get();
        object.k = (DeviceIdStrategy) this.e.get();
    }
}
