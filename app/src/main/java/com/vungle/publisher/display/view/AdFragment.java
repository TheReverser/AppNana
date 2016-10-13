package com.vungle.publisher.display.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.vungle.log.Logger;
import com.vungle.publisher.inject.Injector;

/* compiled from: vungle */
public abstract class AdFragment extends Fragment {
    public abstract void a();

    public abstract String b();

    public boolean a(int i) {
        return false;
    }

    public void a(boolean z) {
        Logger.v(Logger.AD_TAG, (z ? "gained" : "lost") + " window focus");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getInstance().a.inject(this);
    }
}
