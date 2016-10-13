package com.appnana.android.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Device {
    private static Context context;
    private static Device instance;
    private String advertisingId;
    private String androidID;
    private int appVersionCode;
    private String appVersionName;
    private String clientPackage;
    private String deviceID;
    private String deviceModel;
    private String deviceOSVersion;
    private String deviceType;
    private boolean isLimitAdTrackingEnabled;
    private Map<String, String> map;
    private String userAgent;
    private String webViewUserAgent;

    public static Device getInstance() {
        if (context != null) {
            return instance;
        }
        throw new NullPointerException("Must call Device.newInstance(ApplicationContext) in Application class first.");
    }

    public static Device newInstance(Context context) {
        context = context;
        instance = new Device();
        new Thread(new Runnable() {
            public void run() {
                Device.instance.initAdvertisingInfo();
            }
        }).start();
        return instance;
    }

    public String getAndroidID() {
        if (this.androidID == null) {
            this.androidID = Secure.getString(context.getContentResolver(), "android_id");
        }
        return this.androidID;
    }

    public String getDeviceID() {
        if (this.deviceID == null) {
            this.deviceID = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        }
        return this.deviceID;
    }

    public String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getCountry() {
        return Locale.getDefault().getCountry();
    }

    public String getLocale() {
        return getLanguage() + "-" + getCountry();
    }

    public String getModel() {
        if (this.deviceModel == null) {
            this.deviceModel = Build.MODEL;
        }
        return this.deviceModel;
    }

    public String getType() {
        if (this.deviceType == null) {
            if (isTablet()) {
                this.deviceType = "tablet";
            } else {
                this.deviceType = "phone";
            }
        }
        return this.deviceType;
    }

    public boolean isTablet() {
        if (VERSION.SDK_INT >= 13) {
            if (context.getResources().getConfiguration().smallestScreenWidthDp >= 600) {
                return true;
            }
            return false;
        } else if ((context.getResources().getConfiguration().screenLayout & 15) < 3) {
            return false;
        } else {
            return true;
        }
    }

    public String getOSVersion() {
        if (this.deviceOSVersion == null) {
            this.deviceOSVersion = VERSION.RELEASE;
        }
        return this.deviceOSVersion;
    }

    public String getClientPackage() {
        if (this.clientPackage == null) {
            this.clientPackage = context.getPackageName();
        }
        return this.clientPackage;
    }

    public String getAppVersionName() {
        if (this.appVersionName == null) {
            try {
                this.appVersionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (NameNotFoundException e) {
                this.appVersionName = "-1";
                e.printStackTrace();
            }
        }
        return this.appVersionName;
    }

    public int getAppVersionCode() {
        if (this.appVersionCode == 0) {
            try {
                this.appVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (NameNotFoundException e) {
                this.appVersionCode = 0;
            }
        }
        return this.appVersionCode;
    }

    public void initAdvertisingInfo() {
        if (this.advertisingId == null) {
            try {
                Info info = AdvertisingIdClient.getAdvertisingIdInfo(context);
                this.advertisingId = info.getId();
                this.isLimitAdTrackingEnabled = info.isLimitAdTrackingEnabled();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e2) {
                e2.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e3) {
                e3.printStackTrace();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
    }

    public String getAdvertisingId() {
        return this.advertisingId;
    }

    public boolean isLimitAdTrackingEnabled() {
        return this.isLimitAdTrackingEnabled;
    }

    public String getUserAgent() {
        if (this.userAgent == null) {
            int height;
            int width;
            DisplayMetrics display = context.getResources().getDisplayMetrics();
            if (context.getResources().getConfiguration().orientation == 2) {
                height = display.widthPixels;
                width = display.heightPixels;
            } else {
                width = display.widthPixels;
                height = display.heightPixels;
            }
            this.userAgent = String.format("%1$s/%2$s (%3$s; U; Android %4$s; %5$s; %11$s Build/%6$s; %7$s) %8$dX%9$d %10$s %11$s", new Object[]{getClientPackage(), getAppVersionName(), System.getProperty("os.name", "Linux"), getOSVersion(), getLocale(), Build.ID, Build.BRAND, Integer.valueOf(width), Integer.valueOf(height), Build.MANUFACTURER, getModel()});
        }
        return this.userAgent;
    }

    public String getWebViewUserAgent() {
        if (this.webViewUserAgent != null) {
            return this.webViewUserAgent;
        }
        if (VERSION.SDK_INT >= 17) {
            return WebSettings.getDefaultUserAgent(context);
        }
        return new WebView(context).getSettings().getUserAgentString();
    }

    public boolean isOnWifi() {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }

    public boolean isHacked() {
        String buildTags = Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    public boolean isPackageExisted(String targetPackage) {
        try {
            context.getPackageManager().getPackageInfo(targetPackage, 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public Map<String, String> getAsMap() {
        if (this.map == null) {
            this.map = new HashMap();
            this.map.put("androidId", getAndroidID());
            this.map.put("deviceId", getDeviceID());
            this.map.put("deviceModel", getModel());
            this.map.put("deviceType", getType());
            this.map.put("deviceOSVersion", getOSVersion());
            this.map.put("applicationId", getClientPackage());
            this.map.put("appVersionName", getAppVersionName());
            this.map.put("appVersionCode", String.valueOf(getAppVersionCode()));
            this.map.put("userAgent", getUserAgent());
            this.map.put("webViewUserAgent", getWebViewUserAgent());
            this.map.put("advertisingId", getAdvertisingId());
            this.map.put("isLimitAdTrackingEnabled", String.valueOf(isLimitAdTrackingEnabled()));
        }
        return this.map;
    }
}
