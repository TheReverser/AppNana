package com.appnana.android.giftcardrewards.controller;

import com.appnana.android.giftcardrewards.exception.AccessForbiddenException;
import com.appnana.android.giftcardrewards.exception.AccountException;
import com.appnana.android.giftcardrewards.exception.AlreadyInputException;
import com.appnana.android.giftcardrewards.exception.AppNanaException;
import com.appnana.android.giftcardrewards.exception.DuplicatedException;
import com.appnana.android.giftcardrewards.exception.InvalidCodeException;
import com.appnana.android.giftcardrewards.exception.MassInviteAlreadySent;
import com.appnana.android.giftcardrewards.exception.MultipleDevicesException;
import com.appnana.android.giftcardrewards.exception.NotEnoughPointsException;
import com.appnana.android.giftcardrewards.exception.OfferAlreadyFinishedException;
import com.appnana.android.giftcardrewards.exception.OfferExpiredException;
import com.appnana.android.giftcardrewards.exception.OfferNotFoundException;
import com.appnana.android.giftcardrewards.exception.RfnConflictException;
import com.appnana.android.giftcardrewards.exception.RfnForbiddenException;
import com.appnana.android.giftcardrewards.exception.SelfCodeException;
import com.appnana.android.giftcardrewards.exception.SessionExpiredException;
import com.appnana.android.giftcardrewards.exception.ShareLimitReachedException;
import com.appnana.android.giftcardrewards.exception.UnknownException;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.giftcardrewards.service.WebService;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.offerwall.model.AppNana;
import com.appnana.android.offerwall.model.AppNana.Offer;
import com.appnana.android.offerwall.model.AppNanaHistory;
import com.appnana.android.utils.MapizLog;
import com.facebook.BuildConfig;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vungle.sdk.VunglePub.Gender;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class UserCommand {
    private WebService mWebService = WebService.init();

    private UserCommand() {
    }

    public static UserCommand create() {
        return new UserCommand();
    }

    public void getSettings() throws IOException, JSONException, UnknownException {
        JSONObject result = null;
        try {
            result = (JSONObject) parseJSON(this.mWebService.getSettings());
        } catch (AppNanaException e) {
            e.printStackTrace();
        }
        Settings.newInstance(result);
    }

    public void register() throws IOException, JSONException, UnknownException, DuplicatedException, AccessForbiddenException, MultipleDevicesException, AccountException, SessionExpiredException {
        String result = null;
        try {
            result = (String) parseJSON(this.mWebService.register());
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (MultipleDevicesException e2) {
            e2.printStackTrace();
        } catch (MassInviteAlreadySent massInviteAlreadySent) {
            massInviteAlreadySent.printStackTrace();
        } catch (AccessForbiddenException e3) {
            e3.printStackTrace();
        } catch (SessionExpiredException e4) {
            e4.printStackTrace();
        } catch (RfnForbiddenException e5) {
            e5.printStackTrace();
        } catch (RfnConflictException e6) {
            e6.printStackTrace();
        } catch (OfferAlreadyFinishedException e7) {
            e7.printStackTrace();
        } catch (OfferExpiredException e8) {
            e8.printStackTrace();
        } catch (OfferNotFoundException e9) {
            e9.printStackTrace();
        } catch (ShareLimitReachedException e10) {
            e10.printStackTrace();
        }
        if (result.equals(GraphResponse.SUCCESS_KEY)) {
            login(true);
        }
    }

    public void login(boolean isNewUser) throws IOException, JSONException, AccountException, UnknownException, MultipleDevicesException, AccessForbiddenException, SessionExpiredException {
        String result = null;
        try {
            result = (String) parseJSON(this.mWebService.login());
        } catch (DuplicatedException e) {
            e.printStackTrace();
        } catch (MassInviteAlreadySent massInviteAlreadySent) {
            massInviteAlreadySent.printStackTrace();
        } catch (SessionExpiredException e2) {
            e2.printStackTrace();
        } catch (RfnForbiddenException e3) {
            e3.printStackTrace();
        } catch (RfnConflictException e4) {
            e4.printStackTrace();
        } catch (OfferAlreadyFinishedException e5) {
            e5.printStackTrace();
        } catch (OfferExpiredException e6) {
            e6.printStackTrace();
        } catch (OfferNotFoundException e7) {
            e7.printStackTrace();
        } catch (ShareLimitReachedException e8) {
            e8.printStackTrace();
        }
        if (result.equals("success daily") || result.equals(GraphResponse.SUCCESS_KEY) || result.startsWith("need")) {
            boolean isDailyLogin = false;
            int needMoreNanas = 0;
            int needMoreInvites = 0;
            if (result.equals("success daily")) {
                isDailyLogin = true;
            }
            if (result.startsWith("need")) {
                needMoreNanas = Integer.parseInt(result.split(":")[1]);
                needMoreInvites = Integer.parseInt(result.split(":")[2]);
            }
            getUserInfo(isNewUser, isDailyLogin, needMoreNanas, needMoreInvites);
        }
    }

    public void logout() {
        try {
            this.mWebService.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUserInfo(boolean isNewUser, boolean isDailyLogin, int needMoreNanas, int needMoreInvites) throws IOException, JSONException, UnknownException, SessionExpiredException, AccessForbiddenException {
        JSONObject json = null;
        try {
            json = (JSONObject) parseJSON(this.mWebService.getUserInfo());
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (DuplicatedException e2) {
            e2.printStackTrace();
        } catch (MultipleDevicesException e3) {
            e3.printStackTrace();
        } catch (MassInviteAlreadySent massInviteAlreadySent) {
            massInviteAlreadySent.printStackTrace();
        } catch (RfnForbiddenException e4) {
            e4.printStackTrace();
        } catch (RfnConflictException e5) {
            e5.printStackTrace();
        } catch (OfferAlreadyFinishedException e6) {
            e6.printStackTrace();
        } catch (OfferExpiredException e7) {
            e7.printStackTrace();
        } catch (OfferNotFoundException e8) {
            e8.printStackTrace();
        } catch (ShareLimitReachedException e9) {
            e9.printStackTrace();
        }
        int id = json.getInt(ShareConstants.WEB_DIALOG_PARAM_ID);
        String email = json.getString("email");
        String name = json.getString(ShareConstants.WEB_DIALOG_PARAM_NAME);
        String paypal = json.getString("paypal_email");
        String bitcoinAddress = json.getString("bitcoin_address");
        UserModel.setInstance(id, json.getInt("current_device_id"), email, name, paypal, bitcoinAddress, json.getInt("nanas"), json.getInt("offer_nanas"), json.getInt("input_invitation"), json.getInt("sent_invitation_count"), json.getInt("mass_invitation"), json.getInt("rfn"), json.getString("current_device"), json.getInt("share_times"), json.getInt("gaming_times"), json.getString("last_gaming_time"));
        UserModel.getInstance().isNew = isNewUser;
        UserModel.getInstance().isDailyLogin = isDailyLogin;
        UserModel.getInstance().needMoreNanas = needMoreNanas;
        UserModel.getInstance().needMoreInvites = needMoreInvites;
    }

    public boolean isRedeemSuccess(int rewardID) throws IOException, JSONException, UnknownException, NotEnoughPointsException, AccessForbiddenException, SessionExpiredException {
        JSONObject jSONObject = null;
        try {
            jSONObject = parseJSON(this.mWebService.redeem(rewardID));
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (DuplicatedException e2) {
            e2.printStackTrace();
        } catch (MultipleDevicesException e3) {
            e3.printStackTrace();
        } catch (MassInviteAlreadySent massInviteAlreadySent) {
            massInviteAlreadySent.printStackTrace();
        } catch (RfnForbiddenException e4) {
            e4.printStackTrace();
        } catch (RfnConflictException e5) {
            e5.printStackTrace();
        } catch (OfferAlreadyFinishedException e6) {
            e6.printStackTrace();
        } catch (OfferExpiredException e7) {
            e7.printStackTrace();
        } catch (OfferNotFoundException e8) {
            e8.printStackTrace();
        } catch (ShareLimitReachedException e9) {
            e9.printStackTrace();
        }
        try {
            String resultString = (String) jSONObject;
            if (resultString.equals(BuildConfig.VERSION_NAME)) {
                return true;
            }
            if (!resultString.equals("Not Enough")) {
                return false;
            }
            throw new NotEnoughPointsException();
        } catch (ClassCastException e10) {
            int refundNanas = jSONObject.getInt("back_nanas");
            return true;
        }
    }

    public boolean finishOfferRfn() throws IOException, UnknownException, JSONException, AccessForbiddenException, RfnConflictException, RfnForbiddenException, SessionExpiredException {
        try {
            parseJSON(this.mWebService.finishOfferRfn());
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (DuplicatedException e2) {
            e2.printStackTrace();
        } catch (MultipleDevicesException e3) {
            e3.printStackTrace();
        } catch (MassInviteAlreadySent massInviteAlreadySent) {
            massInviteAlreadySent.printStackTrace();
        } catch (OfferAlreadyFinishedException e4) {
            e4.printStackTrace();
        } catch (OfferExpiredException e5) {
            e5.printStackTrace();
        } catch (OfferNotFoundException e6) {
            e6.printStackTrace();
        } catch (ShareLimitReachedException e7) {
            e7.printStackTrace();
        }
        return true;
    }

    public boolean isInvitationCodeVerified(String userInputCode, String invitationCode) throws IOException, JSONException, UnknownException, AlreadyInputException, InvalidCodeException, SelfCodeException, NotEnoughPointsException, AccessForbiddenException, SessionExpiredException {
        if (!verifyByLocal(userInputCode, invitationCode)) {
            return false;
        }
        String result = null;
        try {
            result = (String) parseJSON(this.mWebService.inviteVerify(userInputCode));
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (DuplicatedException e2) {
            e2.printStackTrace();
        } catch (MultipleDevicesException e3) {
            e3.printStackTrace();
        } catch (MassInviteAlreadySent massInviteAlreadySent) {
            massInviteAlreadySent.printStackTrace();
        } catch (RfnForbiddenException e4) {
            e4.printStackTrace();
        } catch (RfnConflictException e5) {
            e5.printStackTrace();
        } catch (OfferAlreadyFinishedException e6) {
            e6.printStackTrace();
        } catch (OfferExpiredException e7) {
            e7.printStackTrace();
        } catch (OfferNotFoundException e8) {
            e8.printStackTrace();
        } catch (ShareLimitReachedException e9) {
            e9.printStackTrace();
        }
        if (result.equals("True")) {
            return true;
        }
        if (result.equals("Already")) {
            throw new AlreadyInputException();
        } else if (result.equals("Invalid")) {
            throw new InvalidCodeException();
        } else if (result.equals("self")) {
            throw new SelfCodeException();
        } else if (!result.equals("NotEnoughNanas")) {
            return false;
        } else {
            throw new NotEnoughPointsException();
        }
    }

    public List<Offer> getOffers() throws IOException {
        HttpResponse response = this.mWebService.getOffers();
        List<Offer> result = new ArrayList();
        try {
            result = ((AppNana) new Gson().fromJson(((JSONObject) parseJSON(response)).toString(), AppNana.class)).getOffers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean acceptOffer(String offerId) throws SessionExpiredException, UnknownException, OfferNotFoundException, JSONException, OfferAlreadyFinishedException, AccessForbiddenException, OfferExpiredException, IOException {
        try {
            parseJSON(this.mWebService.acceptOffer(offerId));
        } catch (RfnForbiddenException e) {
            e.printStackTrace();
        } catch (AccountException e2) {
            e2.printStackTrace();
        } catch (RfnConflictException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        } catch (MultipleDevicesException e5) {
            e5.printStackTrace();
        } catch (MassInviteAlreadySent e6) {
            e6.printStackTrace();
        } catch (DuplicatedException e7) {
            e7.printStackTrace();
        } catch (ShareLimitReachedException e8) {
            e8.printStackTrace();
        }
        return true;
    }

    public List<String> getHistoryName() throws IOException {
        try {
            return ((AppNanaHistory) new Gson().fromJson(((JSONObject) parseJSON(this.mWebService.getHistoryName())).toString(), AppNanaHistory.class)).getOfferNames();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public List<AppNanaHistory.Offer> getHistory(int offset) throws IOException {
        try {
            return ((AppNanaHistory) new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss z").create().fromJson(((JSONObject) parseJSON(this.mWebService.getHistory(offset))).toString(), AppNanaHistory.class)).getOffers();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public boolean postHistory(AbstractOffer offer) throws SessionExpiredException, UnknownException, OfferNotFoundException, JSONException, OfferAlreadyFinishedException, AccessForbiddenException, OfferExpiredException, IOException {
        try {
            parseJSON(this.mWebService.postHistory(offer));
        } catch (RfnForbiddenException e) {
            e.printStackTrace();
        } catch (AccountException e2) {
            e2.printStackTrace();
        } catch (RfnConflictException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        } catch (MultipleDevicesException e5) {
            e5.printStackTrace();
        } catch (MassInviteAlreadySent e6) {
            e6.printStackTrace();
        } catch (DuplicatedException e7) {
            e7.printStackTrace();
        } catch (ShareLimitReachedException e8) {
            e8.printStackTrace();
        }
        return true;
    }

    public boolean verifyFacebookPost(String postId) throws MassInviteAlreadySent, UnknownException, IOException {
        try {
            parseJSON(this.mWebService.verifyFacebookPost(postId));
        } catch (RfnForbiddenException e) {
            e.printStackTrace();
        } catch (AccountException e2) {
            e2.printStackTrace();
        } catch (RfnConflictException e3) {
            e3.printStackTrace();
        } catch (JSONException e4) {
            e4.printStackTrace();
        } catch (OfferExpiredException e5) {
            e5.printStackTrace();
        } catch (SessionExpiredException e6) {
            e6.printStackTrace();
        } catch (OfferAlreadyFinishedException e7) {
            e7.printStackTrace();
        } catch (OfferNotFoundException e8) {
            e8.printStackTrace();
        } catch (AccessForbiddenException e9) {
            e9.printStackTrace();
        } catch (MultipleDevicesException e10) {
            e10.printStackTrace();
        } catch (DuplicatedException e11) {
            e11.printStackTrace();
        } catch (ShareLimitReachedException e12) {
            e12.printStackTrace();
        }
        return true;
    }

    public void sendMassInvite(String sharer) throws SessionExpiredException, UnknownException, ShareLimitReachedException {
        AppNanaException e;
        Exception e2;
        try {
            parseJSON(this.mWebService.sendMassInvite(sharer));
            return;
        } catch (SessionExpiredException e3) {
            e = e3;
        } catch (ShareLimitReachedException e4) {
            e = e4;
        } catch (UnknownException e5) {
            e2 = e5;
            e2.printStackTrace();
            throw new UnknownException();
        } catch (IOException e6) {
            e2 = e6;
            e2.printStackTrace();
            throw new UnknownException();
        } catch (JSONException e7) {
            e2 = e7;
            e2.printStackTrace();
            throw new UnknownException();
        } catch (Exception e22) {
            e22.printStackTrace();
            return;
        }
        throw e;
    }

    private Object parseJSON(HttpResponse response) throws IOException, JSONException, UnknownException, AccountException, DuplicatedException, MultipleDevicesException, MassInviteAlreadySent, AccessForbiddenException, SessionExpiredException, RfnForbiddenException, RfnConflictException, OfferNotFoundException, OfferAlreadyFinishedException, OfferExpiredException, ShareLimitReachedException {
        JSONObject jsonObject = encodeJSON(response);
        if (isRequestSuccessfully(jsonObject.getJSONObject("header"))) {
            return jsonObject.get("response");
        }
        throw new UnknownException();
    }

    private boolean isRequestSuccessfully(JSONObject header) throws JSONException, AccountException, DuplicatedException, MultipleDevicesException, MassInviteAlreadySent, AccessForbiddenException, SessionExpiredException, RfnForbiddenException, RfnConflictException, OfferNotFoundException, OfferExpiredException, OfferAlreadyFinishedException, ShareLimitReachedException {
        switch (header.getInt("errno")) {
            case Gender.MALE /*0*/:
                return true;
            case UserModel.REGISTER_ACTION_CODE /*1001*/:
                throw new DuplicatedException();
            case 1400:
                throw new SessionExpiredException();
            case 1401:
                throw new AccountException();
            case 1403:
                throw new AccessForbiddenException();
            case 1408:
                throw new MassInviteAlreadySent();
            case 1410:
                throw new MultipleDevicesException();
            case 1412:
                throw new ShareLimitReachedException();
            case 1503:
                throw new OfferAlreadyFinishedException();
            case 1504:
                throw new OfferNotFoundException();
            case 2403:
                throw new RfnForbiddenException();
            case 2409:
                throw new RfnConflictException();
            case 4410:
                throw new OfferExpiredException();
            default:
                return false;
        }
    }

    private JSONObject encodeJSON(HttpResponse response) throws IOException, JSONException {
        String data = EntityUtils.toString(response.getEntity());
        MapizLog.d("response", data);
        return new JSONObject(data);
    }

    private boolean verifyByLocal(String userInputCode, String invitationCode) throws InvalidCodeException, SelfCodeException {
        userInputCode = userInputCode.trim();
        if (userInputCode.length() < 2) {
            throw new InvalidCodeException();
        }
        try {
            int id = Integer.parseInt(userInputCode.substring(1));
            int userId = Integer.parseInt(invitationCode.substring(1));
            if (String.valueOf(id).substring(0, 1).equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                throw new InvalidCodeException();
            } else if (id != userId) {
                return true;
            } else {
                throw new SelfCodeException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidCodeException();
        }
    }
}
