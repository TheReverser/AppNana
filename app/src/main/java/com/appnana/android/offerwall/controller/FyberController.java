package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.FyberService;

public class FyberController implements IOfferController {
    private static final String TAG = FyberController.class.getSimpleName();
    private FyberService service;

    public FyberController(String ndid) {
        this.service = new FyberService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
