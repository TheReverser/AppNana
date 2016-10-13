package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder binder) {
            this.binder = binder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(FIRST_TRANSACTION_CODE, data, reply, AttributionIdentifiers.CONNECTION_RESULT_SUCCESS);
                reply.readException();
                String id = reply.readString();
                return id;
            } finally {
                reply.recycle();
                data.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            boolean limitAdTracking = true;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                data.writeInt(FIRST_TRANSACTION_CODE);
                this.binder.transact(SECOND_TRANSACTION_CODE, data, reply, AttributionIdentifiers.CONNECTION_RESULT_SUCCESS);
                reply.readException();
                if (reply.readInt() == 0) {
                    limitAdTracking = false;
                }
                reply.recycle();
                data.recycle();
                return limitAdTracking;
            } catch (Throwable th) {
                reply.recycle();
                data.recycle();
            }
        }
    }

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                this.queue.put(service);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }

        public IBinder getBinder() throws InterruptedException {
            if (!this.consumed.compareAndSet(true, true)) {
                return (IBinder) this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r16) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextEntry(HashMap.java:789)
	at java.util.HashMap$KeyIterator.next(HashMap.java:814)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r0 = recentlyFetchedIdentifiers;
        if (r0 == 0) goto L_0x0017;
    L_0x0004:
        r4 = java.lang.System.currentTimeMillis();
        r0 = recentlyFetchedIdentifiers;
        r14 = r0.fetchTime;
        r4 = r4 - r14;
        r14 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r0 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
        if (r0 >= 0) goto L_0x0017;
    L_0x0014:
        r0 = recentlyFetchedIdentifiers;
    L_0x0016:
        return r0;
    L_0x0017:
        r10 = getAndroidId(r16);
        r8 = 0;
        r0 = 3;
        r2 = new java.lang.String[r0];	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = 0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = "aid";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r2[r0] = r3;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = 1;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = "androidid";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r2[r0] = r3;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = 2;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = "limit_tracking";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r2[r0] = r3;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r1 = 0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = r16.getPackageManager();	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = "com.facebook.katana.provider.AttributionIdProvider";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r4 = 0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = r0.resolveContentProvider(r3, r4);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r0 == 0) goto L_0x0056;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x003c:
        r0 = "content://com.facebook.katana.provider.AttributionIdProvider";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r1 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x0042:
        r11 = getInstallerPackageName(r16);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r11 == 0) goto L_0x004a;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x0048:
        r10.androidInstallerPackage = r11;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x004a:
        if (r1 != 0) goto L_0x006a;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x004c:
        r0 = cacheAndReturnIdentifiers(r10);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r8 == 0) goto L_0x0016;
    L_0x0052:
        r8.close();
        goto L_0x0016;
    L_0x0056:
        r0 = r16.getPackageManager();	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = "com.facebook.wakizashi.provider.AttributionIdProvider";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r4 = 0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = r0.resolveContentProvider(r3, r4);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r0 == 0) goto L_0x0042;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x0063:
        r0 = "content://com.facebook.wakizashi.provider.AttributionIdProvider";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r1 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        goto L_0x0042;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x006a:
        r0 = r16.getContentResolver();	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = 0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r4 = 0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r5 = 0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r8 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r8 == 0) goto L_0x007d;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x0077:
        r0 = r8.moveToFirst();	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r0 != 0) goto L_0x0087;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x007d:
        r0 = cacheAndReturnIdentifiers(r10);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r8 == 0) goto L_0x0016;
    L_0x0083:
        r8.close();
        goto L_0x0016;
    L_0x0087:
        r0 = "aid";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r7 = r8.getColumnIndex(r0);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = "androidid";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r6 = r8.getColumnIndex(r0);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = "limit_tracking";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r12 = r8.getColumnIndex(r0);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = r8.getString(r7);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r10.attributionId = r0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r6 <= 0) goto L_0x00b9;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x00a1:
        if (r12 <= 0) goto L_0x00b9;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x00a3:
        r0 = r10.getAndroidAdvertiserId();	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        if (r0 != 0) goto L_0x00b9;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x00a9:
        r0 = r8.getString(r6);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r10.androidAdvertiserId = r0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = r8.getString(r12);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = java.lang.Boolean.parseBoolean(r0);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r10.limitTracking = r0;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
    L_0x00b9:
        if (r8 == 0) goto L_0x00be;
    L_0x00bb:
        r8.close();
    L_0x00be:
        r0 = cacheAndReturnIdentifiers(r10);
        goto L_0x0016;
    L_0x00c4:
        r9 = move-exception;
        r0 = TAG;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3.<init>();	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r4 = "Caught unexpected exception in getAttributionId(): ";	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r4 = r9.toString();	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        android.util.Log.d(r0, r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00e9 }
        r0 = 0;
        if (r8 == 0) goto L_0x0016;
    L_0x00e4:
        r8.close();
        goto L_0x0016;
    L_0x00e9:
        r0 = move-exception;
        if (r8 == 0) goto L_0x00ef;
    L_0x00ec:
        r8.close();
    L_0x00ef:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers identifiers = getAndroidIdViaReflection(context);
        if (identifiers != null) {
            return identifiers;
        }
        identifiers = getAndroidIdViaService(context);
        if (identifiers == null) {
            return new AttributionIdentifiers();
        }
        return identifiers;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method isGooglePlayServicesAvailable = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
            if (isGooglePlayServicesAvailable == null) {
                return null;
            }
            Object connectionResult = Utility.invokeMethodQuietly(null, isGooglePlayServicesAvailable, context);
            if (!(connectionResult instanceof Integer) || ((Integer) connectionResult).intValue() != 0) {
                return null;
            }
            Method getAdvertisingIdInfo = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
            if (getAdvertisingIdInfo == null) {
                return null;
            }
            Object advertisingInfo = Utility.invokeMethodQuietly(null, getAdvertisingIdInfo, context);
            if (advertisingInfo == null) {
                return null;
            }
            Method getId = Utility.getMethodQuietly(advertisingInfo.getClass(), "getId", new Class[CONNECTION_RESULT_SUCCESS]);
            Method isLimitAdTrackingEnabled = Utility.getMethodQuietly(advertisingInfo.getClass(), "isLimitAdTrackingEnabled", new Class[CONNECTION_RESULT_SUCCESS]);
            if (getId == null || isLimitAdTrackingEnabled == null) {
                return null;
            }
            AttributionIdentifiers identifiers = new AttributionIdentifiers();
            identifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(advertisingInfo, getId, new Object[CONNECTION_RESULT_SUCCESS]);
            identifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(advertisingInfo, isLimitAdTrackingEnabled, new Object[CONNECTION_RESULT_SUCCESS])).booleanValue();
            return identifiers;
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection connection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, connection, 1)) {
            try {
                GoogleAdInfo adInfo = new GoogleAdInfo(connection.getBinder());
                AttributionIdentifiers identifiers = new AttributionIdentifiers();
                identifiers.androidAdvertiserId = adInfo.getAdvertiserId();
                identifiers.limitTracking = adInfo.isTrackingLimited();
                return identifiers;
            } catch (Exception exception) {
                Utility.logd("android_id", exception);
            } finally {
                context.unbindService(connection);
            }
        }
        return null;
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers identifiers) {
        identifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = identifiers;
        return identifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    @Nullable
    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }
}
