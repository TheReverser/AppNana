package com.vungle.publisher.location;

import android.location.Location;
import com.facebook.BuildConfig;
import com.vungle.log.Logger;
import com.vungle.publisher.bv;
import com.vungle.publisher.bz;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AndroidLocation implements bz {
    @Inject
    bv a;

    public final String a() {
        String str = BuildConfig.VERSION_NAME;
        try {
            str = Locale.getDefault().getISO3Language();
        } catch (Throwable e) {
            Logger.w(Logger.LOCATION_TAG, "error getting ISO 3-letter language code", e);
        }
        return str;
    }

    public final Location b() {
        if (this.a != null) {
            return this.a.b();
        }
        Logger.d(Logger.LOCATION_TAG, "cannot provide detailed location - null detailed location provider");
        return null;
    }

    public final String c() {
        return TimeZone.getDefault().getID();
    }
}
