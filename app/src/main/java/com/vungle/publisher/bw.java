package com.vungle.publisher;

import android.location.Location;
import com.vungle.log.Logger;
import dagger.Lazy;
import javax.inject.Inject;

/* compiled from: vungle */
public class bw implements bv {
    boolean a = true;
    boolean b = true;
    @Inject
    public Lazy<bx> c;
    @Inject
    public by d;

    public final Location b() {
        Object obj;
        Location location;
        Throwable th;
        Throwable th2;
        Location location2 = null;
        Location location3;
        try {
            if (this.b) {
                obj = this.d;
                if (obj != null) {
                    try {
                        location2 = obj.b();
                    } catch (Throwable e) {
                        Logger.i(Logger.LOCATION_TAG, "permanent error obtaining detailed location " + obj, e);
                        this.b = false;
                        location = location2;
                    } catch (Throwable e2) {
                        th = e2;
                        location = location2;
                        th2 = th;
                        Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, th2);
                        return location;
                    }
                }
                location = location2;
            } else {
                location3 = location2;
                location = location2;
            }
            try {
                if (!this.a || location != null) {
                    return location;
                }
                try {
                    bv bvVar = (bv) this.c.get();
                    if (bvVar != null) {
                        try {
                            location2 = bvVar.b();
                        } catch (Throwable e3) {
                            th = e3;
                            obj = r0;
                            th2 = th;
                            Logger.i(Logger.LOCATION_TAG, "permanent error obtaining detailed location " + obj, th2);
                            this.a = false;
                            return location;
                        } catch (Throwable e32) {
                            th = e32;
                            obj = r0;
                            th2 = th;
                            Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, th2);
                            return location;
                        }
                    }
                    location2 = location;
                    return location2;
                } catch (NoClassDefFoundError e4) {
                    th2 = e4;
                    Logger.i(Logger.LOCATION_TAG, "permanent error obtaining detailed location " + obj, th2);
                    this.a = false;
                    return location;
                } catch (Throwable th3) {
                    th2 = th3;
                    Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, th2);
                    return location;
                }
            } catch (Throwable th4) {
                th2 = th4;
                Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, th2);
                return location;
            }
        } catch (Throwable e22) {
            location3 = location2;
            th = e22;
            location = location2;
            th2 = th;
            Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, th2);
            return location;
        }
    }
}
