package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.AppThisService;

public class AppThisController implements IOfferController {
    private static final String TAG = AppThisController.class.getSimpleName();
    private AppThisService service;

    public AppThisController(String ndid) {
        this.service = new AppThisService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
