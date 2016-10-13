package com.vungle.publisher.net;

import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AndroidNetwork$$InjectAdapter extends Binding<AndroidNetwork> implements MembersInjector<AndroidNetwork>, Provider<AndroidNetwork> {
    private Binding<ConnectivityManager> a;
    private Binding<Provider<NetworkBroadcastReceiver>> b;
    private Binding<TelephonyManager> c;

    public AndroidNetwork$$InjectAdapter() {
        super("com.vungle.publisher.net.AndroidNetwork", "members/com.vungle.publisher.net.AndroidNetwork", true, AndroidNetwork.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.net.ConnectivityManager", AndroidNetwork.class, getClass().getClassLoader());
        this.b = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.net.NetworkBroadcastReceiver>", AndroidNetwork.class, getClass().getClassLoader());
        this.c = linker.requestBinding("android.telephony.TelephonyManager", AndroidNetwork.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final AndroidNetwork get() {
        AndroidNetwork androidNetwork = new AndroidNetwork();
        injectMembers(androidNetwork);
        return androidNetwork;
    }

    public final void injectMembers(AndroidNetwork object) {
        object.a = (ConnectivityManager) this.a.get();
        object.b = (Provider) this.b.get();
        object.c = (TelephonyManager) this.c.get();
    }
}
