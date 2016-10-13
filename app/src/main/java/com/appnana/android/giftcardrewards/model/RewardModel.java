package com.appnana.android.giftcardrewards.model;

import android.content.Context;
import android.content.SharedPreferences;
import com.appnana.android.giftcardrewards.exception.UnknownException;
import com.appnana.android.giftcardrewards.service.WebService;
import com.appnana.android.utils.MapizLog;
import com.facebook.BuildConfig;
import com.vungle.sdk.VunglePub.Gender;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RewardModel {
    private static RewardModel instance;
    private String locale = this.sharedPreferences.getString(AppNanaPrefrences.PREF_LOCALE, CountryInfoModel.getInstance().getDefaultLocale());
    private List<RewardType> rewards;
    private SharedPreferences sharedPreferences;

    private RewardModel(Context context) {
        this.sharedPreferences = context.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0);
    }

    private void initWithServerData() throws IllegalStateException, UnknownException, ClientProtocolException, IOException, JSONException {
        String rewardDataString = ((JSONArray) parseJSON(WebService.init().getRewards(this.locale))).toString();
        this.sharedPreferences.edit().putString("rewards_" + this.locale, rewardDataString).commit();
        syncData(rewardDataString);
    }

    private Object parseJSON(HttpResponse response) throws IllegalStateException, IOException, JSONException, UnknownException {
        JSONObject jsonObject = encodeJSON(response);
        if (isRequestSuccessfully(jsonObject.getJSONObject("header"))) {
            return jsonObject.get("response");
        }
        throw new UnknownException();
    }

    private boolean isRequestSuccessfully(JSONObject header) throws JSONException, UnknownException {
        switch (header.getInt("errno")) {
            case Gender.MALE /*0*/:
                return true;
            case 9001:
                throw new UnknownException();
            default:
                return false;
        }
    }

    private JSONObject encodeJSON(HttpResponse response) throws IllegalStateException, IOException, JSONException {
        String data = EntityUtils.toString(response.getEntity());
        MapizLog.d("response", data);
        return new JSONObject(data);
    }

    private void initWithLocalData() {
        syncData(this.sharedPreferences.getString("rewards_" + this.locale, BuildConfig.VERSION_NAME));
    }

    private void syncData(String data) {
        int rewardCount;
        JSONException e;
        int i;
        JSONArray rewardTypeJsonArray = null;
        try {
            JSONArray rewardTypeJsonArray2 = new JSONArray(data);
            try {
                rewardCount = rewardTypeJsonArray2.length();
                rewardTypeJsonArray = rewardTypeJsonArray2;
            } catch (JSONException e2) {
                e = e2;
                rewardTypeJsonArray = rewardTypeJsonArray2;
                rewardCount = 0;
                e.printStackTrace();
                this.rewards = new ArrayList();
                for (i = 0; i < rewardCount; i++) {
                    try {
                        this.rewards.add(RewardType.create(rewardTypeJsonArray.getJSONObject(i)));
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        } catch (JSONException e4) {
            e3 = e4;
            rewardCount = 0;
            e3.printStackTrace();
            this.rewards = new ArrayList();
            for (i = 0; i < rewardCount; i++) {
                this.rewards.add(RewardType.create(rewardTypeJsonArray.getJSONObject(i)));
            }
        }
        this.rewards = new ArrayList();
        for (i = 0; i < rewardCount; i++) {
            this.rewards.add(RewardType.create(rewardTypeJsonArray.getJSONObject(i)));
        }
    }

    public static RewardModel getInstance(Context context) {
        if (instance == null || instance.getRewards() == null || instance.getRewards().isEmpty()) {
            instance = new RewardModel(context);
            try {
                instance.initWithServerData();
            } catch (Exception e) {
                e.printStackTrace();
                instance.initWithLocalData();
            }
        }
        return instance;
    }

    public static RewardModel restoreInstance(Context context) {
        if (instance == null) {
            instance = new RewardModel(context);
            instance.initWithLocalData();
        }
        return instance;
    }

    public List<RewardType> getRewards() {
        return this.rewards;
    }

    public void updateRewards(String locale) throws ClientProtocolException, IOException, JSONException, IllegalStateException, UnknownException {
        this.sharedPreferences.edit().putString(AppNanaPrefrences.PREF_LOCALE, locale).commit();
        this.locale = locale;
        initWithServerData();
    }
}
