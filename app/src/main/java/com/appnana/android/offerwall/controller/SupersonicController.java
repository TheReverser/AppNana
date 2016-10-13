package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.SupersonicService;

public class SupersonicController implements IOfferController {
    private static final String TAG = SupersonicController.class.getSimpleName();
    private SupersonicService service;

    public SupersonicController(String ndid) {
        this.service = new SupersonicService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
