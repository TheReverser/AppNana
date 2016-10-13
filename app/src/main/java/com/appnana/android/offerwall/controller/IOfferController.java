package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public interface IOfferController {
    void requestOffers(Listener listener, ErrorListener errorListener);
}
