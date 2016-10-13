package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.TrialPayService;

public class TrialPayController implements IOfferController {
    private static final String TAG = TrialPayController.class.getSimpleName();
    private TrialPayService service;

    public TrialPayController(String ndid) {
        this.service = new TrialPayService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
