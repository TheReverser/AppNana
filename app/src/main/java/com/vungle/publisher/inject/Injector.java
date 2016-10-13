package com.vungle.publisher.inject;

import android.content.Context;
import com.vungle.log.Logger;
import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.bs;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.image.BitmapFactory;
import dagger.ObjectGraph;
import java.util.ArrayList;
import java.util.List;

/* compiled from: vungle */
public class Injector implements bs {
    private static final Injector c = new Injector();
    public ObjectGraph a;
    public List<OverrideModule> b = new ArrayList();
    private boolean d;
    private bs e;

    public static Injector getInstance() {
        return c;
    }

    private Injector() {
    }

    public void setBitmapFactory(BitmapFactory bitmapFactory) {
        try {
            if (this.d) {
                Logger.d(Logger.INJECT_TAG, "bitmap factory in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting bitmap factory in injector " + bitmapFactory);
            a().setBitmapFactory(bitmapFactory);
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public final void a(Class<? extends FullScreenAdActivity> cls) {
        try {
            if (this.d) {
                Logger.d(Logger.INJECT_TAG, "full screen ad activity class in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting full screen ad activity class in injector " + cls);
            a().a(cls);
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public void setWrapperFramework(WrapperFramework wrapperFramework) {
        try {
            if (this.d) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting wrapper framework in injector: " + wrapperFramework);
            a().setWrapperFramework(wrapperFramework);
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public void setWrapperFrameworkVersion(String wrapperFrameworkVersion) {
        try {
            if (this.d) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework version in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting wrapper framework version in injector: " + wrapperFrameworkVersion);
            a().setWrapperFrameworkVersion(wrapperFrameworkVersion);
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    private bs a() {
        if (this.e == null) {
            this.e = new ConfigurablePublisherModule();
        }
        return this.e;
    }

    public final void a(Context context, String str) {
        try {
            if (this.d) {
                Logger.d(Logger.INJECT_TAG, "already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "initializing");
            bs a = a();
            a.a(context, str);
            List arrayList = new ArrayList();
            arrayList.add(a);
            arrayList.addAll(this.b);
            this.a = ObjectGraph.create(arrayList.toArray());
            this.d = true;
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, "error initializing injector", e);
        }
    }
}
