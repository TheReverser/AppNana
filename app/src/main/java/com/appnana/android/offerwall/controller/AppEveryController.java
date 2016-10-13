package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.AppEveryService;

public class AppEveryController implements IOfferController {
    private static final String TAG = AppEveryController.class.getSimpleName();
    private AppEveryService service;

    public AppEveryController(String ndid) {
        this.service = new AppEveryService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
