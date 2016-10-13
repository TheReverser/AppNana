package com.vungle.publisher.display.view;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AlertDialogFactory$$InjectAdapter extends Binding<AlertDialogFactory> implements Provider<AlertDialogFactory> {
    public AlertDialogFactory$$InjectAdapter() {
        super("com.vungle.publisher.display.view.AlertDialogFactory", "members/com.vungle.publisher.display.view.AlertDialogFactory", true, AlertDialogFactory.class);
    }

    public final AlertDialogFactory get() {
        return new AlertDialogFactory();
    }
}
