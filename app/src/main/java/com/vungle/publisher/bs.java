package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.image.BitmapFactory;

/* compiled from: vungle */
public interface bs {
    void a(Context context, String str);

    void a(Class<? extends FullScreenAdActivity> cls);

    void setBitmapFactory(BitmapFactory bitmapFactory);

    void setWrapperFramework(WrapperFramework wrapperFramework);

    void setWrapperFrameworkVersion(String str);
}
