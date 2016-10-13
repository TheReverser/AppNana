package com.trialpay.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.trialpay.android.BaseTrialpayManager.StateListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressLint({"NewApi"})
public class Foreground implements ActivityLifecycleCallbacks {
    public static final long CHECK_DELAY = 500;
    public static final String TAG = Foreground.class.getName();
    private static Foreground instance;
    private Runnable check;
    private boolean foreground = true;
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<StateListener> listeners = new CopyOnWriteArrayList();
    private boolean paused = false;

    @SuppressLint({"NewApi"})
    public static Foreground getInstance(Context ctx) {
        if (instance == null) {
            Context appCtx = ctx.getApplicationContext();
            if (appCtx instanceof Application) {
                instance = new Foreground();
                ((Application) appCtx).registerActivityLifecycleCallbacks(instance);
            } else {
                throw new IllegalStateException("Foreground did not initialize because it cannot obtain the Application object from the provided context");
            }
        }
        return instance;
    }

    public static void addListener(Context ctx, StateListener listener) {
        getInstance(ctx).listeners.add(listener);
    }

    public static void removeListener(Context ctx, StateListener listener) {
        getInstance(ctx).listeners.remove(listener);
    }

    public void onActivityResumed(Activity activity) {
        boolean wasBackground = false;
        this.paused = false;
        if (!this.foreground) {
            wasBackground = true;
        }
        this.foreground = true;
        if (this.check != null) {
            this.handler.removeCallbacks(this.check);
        }
        if (wasBackground) {
            Log.i(TAG, "app opened");
            for (StateListener listener : this.listeners) {
                try {
                    listener.onBecameForeground();
                } catch (Exception e) {
                    Log.e(TAG, "ForegroundListener threw exception: ", e);
                }
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        this.paused = true;
        if (this.check != null) {
            this.handler.removeCallbacks(this.check);
        }
        Handler handler = this.handler;
        Runnable anonymousClass1 = new Runnable() {
            public void run() {
                if (Foreground.this.foreground && Foreground.this.paused) {
                    Foreground.this.foreground = false;
                    Log.i(Foreground.TAG, "app closed");
                    for (StateListener listener : Foreground.this.listeners) {
                        try {
                            listener.onBecameBackground();
                        } catch (Exception e) {
                            Log.e(Foreground.TAG, "ForegroundListener threw exception: ", e);
                        }
                    }
                }
            }
        };
        this.check = anonymousClass1;
        handler.postDelayed(anonymousClass1, CHECK_DELAY);
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
