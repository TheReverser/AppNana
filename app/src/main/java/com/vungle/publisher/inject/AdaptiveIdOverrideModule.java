package com.vungle.publisher.inject;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.vungle.publisher.env.AdaptiveDeviceIdStrategy;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = {ConfigurablePublisherModule.class}, overrides = true)
/* compiled from: vungle */
public class AdaptiveIdOverrideModule implements OverrideModule {
    @Singleton
    @Provides
    DeviceIdStrategy provideDeviceIdStrategy(AdaptiveDeviceIdStrategy deviceIdStrategy) {
        return deviceIdStrategy;
    }

    @Provides
    WifiManager provideWifiManager(Context context) {
        return (WifiManager) context.getSystemService("wifi");
    }
}
