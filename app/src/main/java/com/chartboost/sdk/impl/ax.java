package com.chartboost.sdk.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.chartboost.sdk.Libraries.CBLogging;
import java.util.Observable;

public class ax extends Observable {
    private static ax c = null;
    private static b d = b.CONNECTION_UNKNOWN;
    private boolean a;
    private boolean b;
    private a e;

    private class a extends BroadcastReceiver {
        final /* synthetic */ ax a;

        public a(ax axVar) {
            this.a = axVar;
        }

        public void onReceive(Context context, Intent intent) {
            ax a = ax.a();
            a.a(context);
            a.notifyObservers();
        }
    }

    public enum b {
        CONNECTION_WIFI,
        CONNECTION_MOBILE,
        CONNECTION_ERROR,
        CONNECTION_UNKNOWN
    }

    private ax() {
        this.a = true;
        this.b = false;
        this.e = null;
        this.e = new a(this);
    }

    public static ax a() {
        if (c == null) {
            c = new ax();
        }
        return c;
    }

    public b b() {
        return d;
    }

    public void a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                a(false);
                d = b.CONNECTION_ERROR;
                CBLogging.a("CBReachability", "######### NO Network");
                return;
            }
            a(true);
            if (activeNetworkInfo.getType() == 1) {
                d = b.CONNECTION_WIFI;
                CBLogging.a("CBReachability", "######### TYPE_WIFI");
            } else if (activeNetworkInfo.getType() == 0) {
                d = b.CONNECTION_WIFI;
                CBLogging.a("CBReachability", "######### TYPE_MOBILE");
            }
        } catch (SecurityException e) {
            throw new SecurityException("Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
    }

    public void notifyObservers() {
        if (this.a) {
            setChanged();
            super.notifyObservers(this);
        }
    }

    public void a(boolean z) {
        this.a = z;
    }

    public boolean c() {
        return this.a;
    }

    public Intent b(Context context) {
        if (context == null || this.b) {
            return null;
        }
        b(true);
        CBLogging.a("CBReachability", "######### Network broadcast successfully registered");
        return context.registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void c(Context context) {
        if (context != null && this.b) {
            context.unregisterReceiver(this.e);
            b(false);
            CBLogging.a("CBReachability", "######### Network broadcast successfully unregistered");
        }
    }

    public void b(boolean z) {
        this.b = z;
    }
}
