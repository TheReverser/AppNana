package com.chartboost.sdk;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.Libraries.CBOrientation.Difference;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.ay.c;
import com.chartboost.sdk.impl.i;
import com.chartboost.sdk.impl.k;
import com.facebook.BuildConfig;
import com.facebook.internal.AnalyticsEvents;
import java.util.Map;
import org.json.JSONObject;

public final class CBPreferences {
    private static volatile CBPreferences p = null;
    private String a;
    private String b;
    private String c;
    private ChartboostDelegate d;
    private int e;
    private boolean f;
    private CBOrientation g;
    private boolean h;
    private boolean i;
    private boolean j;
    private String k;
    private Chartboost l;
    private String m;
    private SharedPreferences n;
    private boolean o;

    private CBPreferences() {
        this.e = 30000;
        this.f = false;
        this.i = false;
        this.j = false;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.l = Chartboost.sharedChartboost();
    }

    private SharedPreferences a() {
        if (this.n == null) {
            this.n = CBUtility.a();
        }
        return this.n;
    }

    public static CBPreferences getInstance() {
        Chartboost.sharedChartboost();
        if (p == null) {
            p = new CBPreferences();
        }
        return p;
    }

    public int getTimeout() {
        return this.e;
    }

    public void setTimeout(int timeout) {
        this.e = timeout;
    }

    public void setUserAgentSuffix(String framework) {
        this.k = framework;
    }

    public String getUserAgentSuffix() {
        return this.k == null ? BuildConfig.VERSION_NAME : this.k;
    }

    public String getAppID() {
        this.a = a().getString("appId", this.a);
        return this.a;
    }

    public void setAppID(String appId) {
        this.a = appId;
        a().edit().putString("appId", appId).commit();
    }

    public String getAppSignature() {
        this.b = a().getString("appSignature", this.b);
        return this.b;
    }

    public void setAppSignature(String appSignature) {
        this.b = appSignature;
        a().edit().putString("appSignature", appSignature).commit();
    }

    public String getAppPublicKey() {
        this.c = a().getString("appPublicKey", this.c);
        return this.c;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.c = appPublicKey;
        a().edit().putString("appPublicKey", appPublicKey).commit();
    }

    public ChartboostDelegate getDelegate() {
        return this.d;
    }

    public void setDelegate(ChartboostDelegate delegate) {
        this.d = delegate;
    }

    public boolean getImpressionsUseActivities() {
        return this.f;
    }

    public void setImpressionsUseActivities(boolean impressionsUseActivities) {
        this.f = impressionsUseActivities;
    }

    public boolean getIgnoreErrors() {
        return this.i;
    }

    public void setIgnoreErrors(boolean ignoreErrors) {
        this.i = ignoreErrors;
    }

    public boolean getAnimationsOff() {
        return this.j;
    }

    public void setAnimationsOff(boolean animationsOff) {
        this.j = animationsOff;
    }

    public void setOrientation(CBOrientation _orientation) {
        boolean z = true;
        if (!(this.o || this.l.getContext() == null || this.l.getContext().getApplicationInfo().targetSdkVersion < 14)) {
            this.o = true;
            CBLogging.a("The CBPreferences.setOrientation method is not supported when you set your app's targetSdkVersion to 14 or higher in your Android Manifest.");
        }
        if (_orientation == CBOrientation.UNSPECIFIED) {
            z = false;
        }
        this.h = z;
        this.g = _orientation;
    }

    public CBOrientation getOrientation() {
        if (this.l.getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling getOrientation().");
        } else if (!this.h || this.g == CBOrientation.UNSPECIFIED) {
            return CBUtility.c(this.l.getContext());
        } else {
            return this.g;
        }
    }

    public Difference getForcedOrientationDifference() {
        if (!this.h) {
            return Difference.ANGLE_0;
        }
        CBOrientation c = CBUtility.c(this.l.getContext());
        CBOrientation orientation = getOrientation();
        if (orientation == CBOrientation.UNSPECIFIED || orientation == c) {
            return Difference.ANGLE_0;
        }
        if (orientation == c.rotate90()) {
            return Difference.ANGLE_90;
        }
        if (orientation == c.rotate180()) {
            return Difference.ANGLE_180;
        }
        return Difference.ANGLE_270;
    }

    public JSONObject getTrackingLevels() {
        Object string = a().getString("trackingLevels", BuildConfig.VERSION_NAME);
        try {
            if (!TextUtils.isEmpty(string)) {
                return new JSONObject(string);
            }
        } catch (Throwable e) {
            CBLogging.b("preferences", "error creating json", e);
        }
        return null;
    }

    public void setTrackingLevels(JSONObject trackingLevels) {
        a().edit().putString("trackingLevels", trackingLevels.toString()).commit();
    }

    public void setLoggingLevel(Level lvl) {
        CBLogging.a = lvl;
    }

    public Level getLoggingLevel() {
        return CBLogging.a;
    }

    public String getCustomID() {
        return this.m;
    }

    public void setCustomID(String customID) {
        this.m = customID;
    }

    protected void a(a aVar) {
        if (aVar != null) {
            Map f = aVar.f();
            if (f != null) {
                Editor edit = a().edit();
                for (String str : f.keySet()) {
                    Object obj = f.get(str);
                    if (obj instanceof String) {
                        edit.putString(str, (String) obj);
                    } else if (obj instanceof Integer) {
                        edit.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Float) {
                        edit.putFloat(str, ((Float) obj).floatValue());
                    } else if (obj instanceof Long) {
                        edit.putLong(str, ((Long) obj).longValue());
                    } else if (obj instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj != null) {
                        edit.putString(str, obj.toString());
                    }
                }
                edit.commit();
            }
        }
    }

    protected void a(final Chartboost.a aVar) {
        av avVar = new av("/api/config", null, "main");
        avVar.a(false);
        avVar.b(false);
        avVar.a(k.a.HIGH);
        avVar.a(f.a(f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, com.chartboost.sdk.Libraries.a.a)));
        avVar.a(new c(this) {
            final /* synthetic */ CBPreferences b;

            public void a(a aVar, ay ayVar, i iVar) {
                if (aVar != null) {
                    this.b.a(aVar.a("response"));
                }
                if (aVar != null) {
                    aVar.a();
                }
            }

            public void a(ay ayVar, CBError cBError, i iVar) {
                if (aVar != null) {
                    aVar.a();
                }
            }
        });
    }
}
