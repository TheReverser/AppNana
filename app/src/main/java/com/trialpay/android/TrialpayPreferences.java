package com.trialpay.android;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.facebook.BuildConfig;
import com.trialpay.android.BaseTrialpayManager.Gender;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class TrialpayPreferences {
    private static final int N_VISIT_TIMES_STORE = 5;
    private static String TAG = "Trialpay.Preferences";
    private String currentTouchpointName = null;
    private final SharedPreferences sharedPreferences;
    private Editor transactionEditor = null;

    public TrialpayPreferences(SharedPreferences preferences) {
        this.sharedPreferences = preferences;
    }

    public TrialpayPreferences startTouchpointAccess(String touchpointName) {
        assertTrue(this.currentTouchpointName == null, "Must be out of touchpoint");
        this.currentTouchpointName = touchpointName;
        return this;
    }

    public TrialpayPreferences closeCurrentTouchpoint() {
        this.currentTouchpointName = null;
        return this;
    }

    public TrialpayPreferences startTransaction() {
        assertTrue(this.transactionEditor == null, "Already in transaction");
        this.transactionEditor = this.sharedPreferences.edit();
        return this;
    }

    public TrialpayPreferences commit() {
        assertTrue(this.transactionEditor != null, "Must be within a transaction");
        this.transactionEditor.commit();
        this.transactionEditor = null;
        return this;
    }

    private Editor getEditor() {
        if (this.transactionEditor != null) {
            return this.transactionEditor;
        }
        return this.sharedPreferences.edit();
    }

    private void commitIfNotInTr(Editor e) {
        if (e != null && e != this.transactionEditor) {
            e.commit();
        }
    }

    public float getTotalDollarsSpent(float defaultValue) {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        return this.sharedPreferences.getFloat("info_totalspent_" + this.currentTouchpointName, defaultValue);
    }

    public TrialpayPreferences setTotalDollarsSpent(float dollarAmount) {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        Editor e = getEditor();
        e.putFloat("info_totalspent_" + this.currentTouchpointName, dollarAmount);
        commitIfNotInTr(e);
        return this;
    }

    public boolean hasTotalDollarsSpent() {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        return this.sharedPreferences.contains("info_totalspent_" + this.currentTouchpointName);
    }

    public int getPurchasedVcAmount(int defaultValue) {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        return this.sharedPreferences.getInt("info_totalvcearned_" + this.currentTouchpointName, defaultValue);
    }

    public TrialpayPreferences setPurchasedVcAmount(int vcBalance) {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        Editor e = getEditor();
        e.putInt("info_totalvcearned_" + this.currentTouchpointName, vcBalance);
        commitIfNotInTr(e);
        return this;
    }

    public boolean hasPurchasedVcAmount() {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        return this.sharedPreferences.contains("info_totalvcearned_" + this.currentTouchpointName);
    }

    public int getVcBalance(int defaultValue) {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        return this.sharedPreferences.getInt("info_vcbalance_" + this.currentTouchpointName, defaultValue);
    }

    public TrialpayPreferences setVcBalance(int vcBalance) {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        Editor e = getEditor();
        e.putInt("info_vcbalance_" + this.currentTouchpointName, vcBalance);
        commitIfNotInTr(e);
        return this;
    }

    public boolean hasVcBalance() {
        assertTrue(this.currentTouchpointName != null, "A touchpoint must be provided");
        return this.sharedPreferences.contains("info_vcbalance_" + this.currentTouchpointName);
    }

    public int getLevel(int defaultValue) {
        return this.sharedPreferences.getInt("info_level", defaultValue);
    }

    public void setLevel(int newLevel) {
        Editor e = getEditor();
        e.putInt("info_level", newLevel);
        commitIfNotInTr(e);
    }

    public boolean hasLevel() {
        return this.sharedPreferences.contains("info_level");
    }

    public Gender getGender(Gender defaultValue) {
        return Gender.charToEnum(this.sharedPreferences.getString("info_gender", String.valueOf(defaultValue.toChar())).charAt(0));
    }

    public void setGender(Gender gender) {
        Editor e = getEditor();
        e.putString("info_gender", String.valueOf(gender.toChar()));
        commitIfNotInTr(e);
    }

    public boolean hasGender() {
        return this.sharedPreferences.contains("info_gender");
    }

    public int getAge(int defaultValue) {
        return this.sharedPreferences.getInt("info_age", defaultValue);
    }

    public void setAge(int age) {
        Editor e = getEditor();
        e.putInt("info_age", age);
        commitIfNotInTr(e);
    }

    public boolean hasAge() {
        return this.sharedPreferences.contains("info_age");
    }

    public void setCreationTime(long unixTimestamp) {
        Editor e = getEditor();
        e.putLong("info_created", unixTimestamp);
        commitIfNotInTr(e);
    }

    public long getCreationTime() {
        return this.sharedPreferences.getLong("info_created", 0);
    }

    public boolean hasCreationTime() {
        return this.sharedPreferences.contains("info_created");
    }

    public void logVisitTime(long unixTimestamp) {
        String times = addToStringList(String.valueOf(unixTimestamp), getVisitTimes(), N_VISIT_TIMES_STORE, ',');
        Editor e = getEditor();
        e.putString("info_visits", times);
        commitIfNotInTr(e);
    }

    public String getVisitTimes() {
        return this.sharedPreferences.getString("info_visits", BuildConfig.VERSION_NAME);
    }

    public boolean hasVisitTimes() {
        return this.sharedPreferences.contains("info_visits");
    }

    public void setSid(String sid) {
        Editor e = getEditor();
        e.putString("sid", sid);
        commitIfNotInTr(e);
    }

    public String getSid(String defaultValue) {
        return this.sharedPreferences.getString("sid", defaultValue);
    }

    public boolean hasSid() {
        return this.sharedPreferences.contains("sid");
    }

    public String resolveVic(String touchpointName, String defaultValue) {
        return this.sharedPreferences.getString("vic_" + touchpointName, defaultValue);
    }

    public TrialpayPreferences addVicResolver(String touchpointName, String vic) {
        Editor e = getEditor();
        e.putString("vic_" + touchpointName, vic);
        commitIfNotInTr(e);
        return this;
    }

    public String getTouchpoint(String vic) {
        String str = null;
        if (vic != null) {
            for (String prefixedTouchpoint : this.sharedPreferences.getAll().keySet()) {
                try {
                    if (vic.equals(this.sharedPreferences.getString(prefixedTouchpoint, null)) && prefixedTouchpoint.startsWith("vic_")) {
                        str = prefixedTouchpoint.substring("vic_".length());
                        break;
                    }
                } catch (ClassCastException e) {
                }
            }
        }
        return str;
    }

    public void removeTouchpoint(String touchpointName) {
        Editor e = getEditor();
        e.remove("vic_" + touchpointName);
        commitIfNotInTr(e);
    }

    public List<String> getVics() {
        ArrayList<String> res = new ArrayList();
        for (Entry<String, String> entry : this.sharedPreferences.getAll().entrySet()) {
            if (((String) entry.getKey()).startsWith("vic_")) {
                res.add(entry.getValue());
            }
        }
        return Collections.unmodifiableList(res);
    }

    public synchronized void setTouchpointUrl(String landingPageUrl) {
        Editor e = getEditor();
        if (landingPageUrl != null) {
            e.putString("touchpoint_url_" + this.currentTouchpointName, landingPageUrl);
        } else {
            e.remove("touchpoint_url_" + this.currentTouchpointName);
        }
        commitIfNotInTr(e);
    }

    public String getTouchpointUrl() {
        return this.sharedPreferences.getString("touchpoint_url_" + this.currentTouchpointName, null);
    }

    public int getBalance(String vic, int defaultValue) {
        return this.sharedPreferences.getInt("balance_" + vic, defaultValue);
    }

    public void setBalance(String vic, int amount) {
        Editor e = getEditor();
        e.putInt("balance_" + vic, amount);
        commitIfNotInTr(e);
    }

    public int getPrecreditBalance(String vic, int defaultValue) {
        return this.sharedPreferences.getInt("precredit_balance_" + vic, defaultValue);
    }

    public void setPrecreditBalance(String vic, int amount) {
        Editor e = getEditor();
        e.putInt("precredit_balance_" + vic, amount);
        commitIfNotInTr(e);
    }

    private static String addToStringList(String newItem, String list, int listMaxSize, char delimiter) {
        StringBuffer out = new StringBuffer(newItem);
        int pos = 0;
        if (list != null && !BuildConfig.VERSION_NAME.equals(list)) {
            for (int cnt = 1; cnt < listMaxSize; cnt++) {
                int newPos = list.indexOf(delimiter, pos);
                if (newPos == -1) {
                    out.append(delimiter).append(list.substring(pos));
                    break;
                }
                out.append(delimiter).append(list.substring(pos, newPos));
                pos = newPos + 1;
            }
        }
        return out.toString();
    }

    private static void assertTrue(boolean condition, String message) {
        Utils.assertTrue(condition, message, TAG);
    }

    public void clearAll() {
        getEditor().clear().commit();
    }
}
