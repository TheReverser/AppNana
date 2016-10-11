package com.appnana.android.giftcardrewards.service;

import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.offerwall.service.RequestService;

public abstract class AppNanaService extends RequestService {
    protected String baseUrl = "http://appnana2.mapiz.com/api/";
    protected UserModel user = UserModel.getInstance();

    protected AppNanaService() {
    }
}
