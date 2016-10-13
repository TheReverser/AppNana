package com.vungle.publisher.display.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import com.vungle.log.Logger;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AlertDialogFactory {

    /* compiled from: vungle */
    class AnonymousClass1 implements OnClickListener {
        final /* synthetic */ a a;
        final /* synthetic */ AlertDialogFactory b;

        AnonymousClass1(AlertDialogFactory alertDialogFactory, a aVar) {
            this.b = alertDialogFactory;
            this.a = aVar;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            Logger.d(Logger.AD_TAG, "positive click");
            this.a.a();
        }
    }

    /* compiled from: vungle */
    class AnonymousClass2 implements OnClickListener {
        final /* synthetic */ a a;
        final /* synthetic */ AlertDialogFactory b;

        AnonymousClass2(AlertDialogFactory alertDialogFactory, a aVar) {
            this.b = alertDialogFactory;
            this.a = aVar;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            Logger.d(Logger.AD_TAG, "negative click");
            this.a.b();
        }
    }

    /* compiled from: vungle */
    class AnonymousClass3 implements OnCancelListener {
        final /* synthetic */ a a;
        final /* synthetic */ AlertDialogFactory b;

        AnonymousClass3(AlertDialogFactory alertDialogFactory, a aVar) {
            this.b = alertDialogFactory;
            this.a = aVar;
        }

        public final void onCancel(DialogInterface dialogInterface) {
            Logger.d(Logger.AD_TAG, "cancel click");
            this.a.c();
        }
    }

    /* compiled from: vungle */
    public interface a {
        void a();

        void b();

        void c();
    }
}
