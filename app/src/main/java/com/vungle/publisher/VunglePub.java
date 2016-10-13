package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.inject.AdaptiveIdOverrideModule;
import com.vungle.publisher.inject.Injector;

/* compiled from: vungle */
public class VunglePub extends VunglePubBase {
    public static final String VERSION = "VungleDroid/3.3.0";
    private static final VunglePub l = new VunglePub();

    public static VunglePub getInstance() {
        return l;
    }

    VunglePub() {
    }

    protected final void a(Context context, String str) {
        Injector instance = Injector.getInstance();
        instance.b.add(new AdaptiveIdOverrideModule());
        super.a(context, str);
    }

    public boolean init(Context context, String vungleAppId) {
        return super.init(context, vungleAppId);
    }

    public Demographic getDemographic() {
        return super.getDemographic();
    }

    public void addEventListeners(EventListener... eventListeners) {
        super.addEventListeners(eventListeners);
    }

    public void clearEventListeners() {
        super.clearEventListeners();
    }

    public void setEventListeners(EventListener... eventListeners) {
        super.setEventListeners(eventListeners);
    }

    public void removeEventListeners(EventListener... eventListeners) {
        super.removeEventListeners(eventListeners);
    }

    public AdConfig getGlobalAdConfig() {
        return super.getGlobalAdConfig();
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public boolean isAdPlayable() {
        return super.isAdPlayable();
    }

    public void playAd() {
        super.playAd();
    }

    public void playAd(AdConfig adConfig) {
        super.playAd(adConfig);
    }
}
