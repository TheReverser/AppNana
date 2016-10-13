package com.vungle.publisher.env;

import android.content.Context;
import com.vungle.log.Logger;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.bm;
import com.vungle.publisher.ca;
import com.vungle.publisher.cr;
import com.vungle.publisher.event.ClientEventListenerAdapter;
import com.vungle.publisher.event.ClientEventListenerAdapter.Factory;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class SdkConfig {
    public final Map<EventListener, bm> a = new HashMap();
    public boolean b;
    public final Set<ca> c = EnumSet.noneOf(ca.class);
    public int d;
    @Inject
    Factory e;
    @Inject
    public Context f;

    SdkConfig() {
    }

    public final void a(EventListener... eventListenerArr) {
        if (eventListenerArr != null) {
            for (EventListener eventListener : eventListenerArr) {
                if (eventListener == null) {
                    Logger.d(Logger.EVENT_TAG, "ignoring add null event listener");
                } else {
                    if ((!this.a.containsKey(eventListener) ? 1 : null) != null) {
                        Logger.d(Logger.EVENT_TAG, "adding event listener " + eventListener);
                        ClientEventListenerAdapter clientEventListenerAdapter = (ClientEventListenerAdapter) this.e.a.get();
                        clientEventListenerAdapter.a = eventListener;
                        this.a.put(eventListener, clientEventListenerAdapter);
                        clientEventListenerAdapter.c();
                    } else {
                        Logger.d(Logger.EVENT_TAG, "already added event listener " + eventListener);
                    }
                }
            }
        }
    }

    public final void a() {
        for (bm d : this.a.values()) {
            d.d();
        }
        this.a.clear();
    }

    public final void a(ca... caVarArr) {
        Logger.d(Logger.CONFIG_TAG, "setting ad streaming connectivity types " + cr.b(caVarArr));
        this.c.clear();
        if (caVarArr != null) {
            for (Object obj : caVarArr) {
                if (obj != null) {
                    this.c.add(obj);
                }
            }
        }
    }

    public final boolean b() {
        boolean z = true;
        String[] strArr = new String[]{"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            if (this.f.checkCallingOrSelfPermission(str) != 0) {
                Logger.w(Logger.CONFIG_TAG, "Missing permission - did you remember to add <uses-permission android:name=\"" + str + "\"/> to your AndroidManifest.xml?");
                z = false;
            }
        }
        return z;
    }
}
