package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.AarkiService;

public class AarkiController implements IOfferController {
    private static final String TAG = AarkiController.class.getSimpleName();
    private AarkiService service;

    public AarkiController(String ndid) {
        this.service = new AarkiService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
