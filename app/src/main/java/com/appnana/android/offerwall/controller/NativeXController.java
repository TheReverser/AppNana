package com.appnana.android.offerwall.controller;

import com.android.volley.NetworkResponse;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.appnana.android.offerwall.model.NativeX.Device;
import com.appnana.android.offerwall.service.NativeXService;
import com.appnana.android.offerwall.service.NativeXSessionService;
import com.google.gson.Gson;

public class NativeXController implements IOfferController {
    private static final String TAG = NativeXController.class.getSimpleName();
    private ErrorListener errorListener;
    private Listener sessionResponseListener = new Listener() {
        public void onResponse(Object response) {
            Device device = (Device) response;
            if (device.getSession() == null) {
                NativeXController.this.errorListener.onErrorResponse(new VolleyError(new NetworkResponse(new Gson().toJson(device).getBytes())));
                return;
            }
            new NativeXService(device.getSession()).fetchData(NativeXController.TAG, NativeXController.this.successListener, NativeXController.this.errorListener);
        }
    };
    private NativeXSessionService sessionService;
    private Listener successListener;

    public NativeXController(String ndid) {
        this.sessionService = new NativeXSessionService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.successListener = listener;
        this.errorListener = errorListener;
        this.sessionService.fetchData(TAG, this.sessionResponseListener, errorListener);
    }
}
