package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.DisplayIOService;

public class DisplayIOController implements IOfferController {
    private static final String TAG = DisplayIOController.class.getSimpleName();
    private DisplayIOService service;

    public DisplayIOController(String ndid) {
        this.service = new DisplayIOService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
