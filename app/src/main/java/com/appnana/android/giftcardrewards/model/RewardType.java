package com.appnana.android.giftcardrewards.model;

import com.facebook.BuildConfig;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RewardType {
    private String fullName;
    private String name;
    private int place;
    private List<Reward> rewards;

    private RewardType(String name, String fullName, int place, List<Reward> rewards) {
        this.name = name;
        this.fullName = fullName;
        this.place = place;
        this.rewards = rewards;
    }

    public static RewardType create(JSONObject rewardTypeJson) {
        String rewardTypeName = BuildConfig.VERSION_NAME;
        String rewardTypeFullName = BuildConfig.VERSION_NAME;
        JSONArray rewardJsonArray = null;
        int rewardTypePlace = 0;
        try {
            rewardTypeName = rewardTypeJson.getString(ShareConstants.WEB_DIALOG_PARAM_NAME);
            rewardTypeFullName = rewardTypeJson.getString("full_name");
            rewardTypePlace = rewardTypeJson.getInt("place");
            rewardJsonArray = rewardTypeJson.getJSONArray(AppNanaPrefrences.PREF_REWARDS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int rewardCount = rewardJsonArray.length();
        List<Reward> rewards = new ArrayList();
        for (int i = 0; i < rewardCount; i++) {
            try {
                rewards.add(Reward.create(rewardJsonArray.getJSONObject(i), rewardTypeName, rewardTypeFullName));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return new RewardType(rewardTypeName, rewardTypeFullName, rewardTypePlace, rewards);
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int getPlace() {
        return this.place;
    }

    public List<Reward> getRewards() {
        return this.rewards;
    }
}
