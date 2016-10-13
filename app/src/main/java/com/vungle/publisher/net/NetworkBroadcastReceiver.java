package com.vungle.publisher.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.vungle.log.Logger;
import com.vungle.publisher.ay;
import com.vungle.publisher.az;
import com.vungle.publisher.cb;
import com.vungle.publisher.event.EventBus;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
    public static final IntentFilter a = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    @Inject
    public Context b;
    @Inject
    cb c;
    @Inject
    EventBus d;

    NetworkBroadcastReceiver() {
    }

    public void onReceive(Context context, Intent intent) {
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            return;
        }
        if (intent.getBooleanExtra("noConnectivity", false)) {
            Logger.d(Logger.NETWORK_TAG, "lost connectivity");
            this.d.a(new az());
        } else if (intent.getBooleanExtra("isFailover", false)) {
            Logger.d(Logger.NETWORK_TAG, "connectivity failover");
        } else {
            Logger.d(Logger.NETWORK_TAG, "connectivity established");
            synchronized (this) {
                notifyAll();
            }
            this.d.a(new ay());
        }
    }
}
