package com.trialpay.android;

import android.os.Bundle;
import android.view.View;

public interface NavBar {
    public static final String TAG = "Trialpay.NavBar";

    public enum EventContainerStatus {
        E_OPENED,
        E_CLOSED
    }

    public enum EventPageStatus {
        E_LOADING_STARTED,
        E_LOADING_FINISHED
    }

    public enum EventSource {
        E_OFFERWALL_CONTAINER,
        E_OFFER_CONTAINER
    }

    public interface SimpleCommandListener {
        void onCommand();
    }

    public interface UrlCommandListener {
        void onCommand(String str);
    }

    void executeCommand(String str);

    View getView();

    void hideSpinner();

    void onContainerStatusChanged(EventSource eventSource, EventContainerStatus eventContainerStatus);

    void onPageStatusChanged(EventSource eventSource, EventPageStatus eventPageStatus, String str);

    void open(String str);

    void reload();

    void restoreState(Bundle bundle);

    void saveState(Bundle bundle);

    void setBackCommandListener(SimpleCommandListener simpleCommandListener);

    void setCloseCommandListener(SimpleCommandListener simpleCommandListener);

    void setOfferCommandListener(UrlCommandListener urlCommandListener);

    void setOfferwallCommandListener(UrlCommandListener urlCommandListener);

    void setRefreshCommandListener(SimpleCommandListener simpleCommandListener);

    void setReloadCommandListener(SimpleCommandListener simpleCommandListener);

    void setSubTitle(String str);

    void setTitle(String str);

    void setUpCommandListener(SimpleCommandListener simpleCommandListener);

    void showSpinner();
}
