package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.f.e;

public final class a {
    public static final com.chartboost.sdk.Libraries.f.a a = f.a(f.b(), new e() {
        public boolean a(Object obj) {
            int intValue = ((Number) obj).intValue();
            return intValue >= 200 && intValue < 300;
        }

        public String a() {
            return "Must be a valid status code (>=200 && <300)";
        }
    });

    public static String a(CBPreferences cBPreferences) {
        return "Chartboost-Android-SDK" + cBPreferences.getUserAgentSuffix() + " " + "4.1.1";
    }
}
