package com.appnana.android.giftcardrewards.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.appnana.android.giftcardrewards.exception.EmptyPasswordException;
import com.appnana.android.giftcardrewards.exception.InvalidEmailException;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.Hash;
import com.appnana.android.utils.RegEx;
import com.facebook.BuildConfig;
import com.facebook.appevents.AppEventsConstants;
import java.text.DecimalFormat;

public class UserModel implements Parcelable {
    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        public UserModel createFromParcel(Parcel source) {
            boolean z = true;
            UserModel userModel = UserModel.createForGuest();
            userModel.id = source.readInt();
            userModel.deviceID = source.readInt();
            userModel.email = source.readString();
            userModel.name = source.readString();
            userModel.paypalAccount = source.readString();
            userModel.bitcoinAddress = source.readString();
            userModel.points = source.readInt();
            userModel.earnedPoints = source.readInt();
            userModel.inputCode = source.readInt();
            userModel.sentInvitationCount = source.readInt();
            userModel.isNew = source.readInt() == 1;
            if (source.readInt() != 1) {
                z = false;
            }
            userModel.isDailyLogin = z;
            userModel.needMoreNanas = source.readInt();
            userModel.needMoreInvites = source.readInt();
            userModel.massInvite = source.readInt();
            userModel.rfn = source.readInt();
            userModel.encryptedDeviceId = source.readString();
            userModel.gamingTimes = source.readInt();
            userModel.lastGamingTime = source.readString();
            return userModel;
        }

        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
    public static final String EXTRA_ANDROID_ID = "AndroidID";
    public static final String EXTRA_NAME = "UserModel";
    public static final String EXTRA_REWARD_INDEX = "RewardIndex";
    public static final int FACEBOOK_MASS_INVITE = Integer.parseInt("1000", 2);
    private static final String GENERAL_SOURCE = "Android";
    public static final int GMAIL_MASS_INVITE = Integer.parseInt("100", 2);
    public static final int HOTMAIL_MASS_INVITE = Integer.parseInt(AppEventsConstants.EVENT_PARAM_VALUE_YES, 2);
    public static final int INSTAGRAM_MASS_INVITE = Integer.parseInt("10000", 2);
    public static final int LOGIN_ACTION_CODE = 1002;
    public static final int LOG_OUT_ACTION_CODE = 1003;
    public static final int REGISTER_ACTION_CODE = 1001;
    public static final String RESET_PASS_URL = "http://appnana2.mapiz.com/password_reset/";
    public static final int YMAIL_MASS_INVITE = Integer.parseInt("10", 2);
    private static UserModel instance;
    private static String source;
    private final String SECRET_KEY = "VU58A2ND8S9";
    private String bitcoinAddress;
    private int deviceID;
    private int earnedPoints;
    private String email;
    private String encryptedDeviceId;
    private int gamingTimes;
    private int id;
    private int inputCode;
    public boolean isDailyLogin;
    public boolean isNew;
    private String lastGamingTime;
    private int massInvite;
    private String name;
    public int needMoreInvites;
    public int needMoreNanas;
    private String password;
    private String paypalAccount;
    private int points;
    private int rfn;
    private int sentInvitationCount;
    private int shareTimes;

    private UserModel(int id, int deviceID, String email, String password, String name, String paypalAccount, String bitcoinAddress, int points, int earnedPoints, int inputCode, int sentInvitationCount, int massInvite, int rfn, String encryptedDeviceId, int shareTimes, int gamingTimes, String lastGamingTime) {
        this.id = id;
        this.deviceID = deviceID;
        this.email = email;
        this.password = password;
        this.name = name;
        this.paypalAccount = paypalAccount;
        this.bitcoinAddress = bitcoinAddress;
        this.points = points;
        this.earnedPoints = earnedPoints;
        this.inputCode = inputCode;
        this.sentInvitationCount = sentInvitationCount;
        this.massInvite = massInvite;
        this.rfn = rfn;
        this.encryptedDeviceId = encryptedDeviceId;
        this.shareTimes = shareTimes;
        this.gamingTimes = gamingTimes;
        this.lastGamingTime = lastGamingTime;
    }

    public static UserModel getInstance() {
        if (instance == null) {
            return createForGuest();
        }
        return instance;
    }

    public static UserModel setInstance(int id, int deviceID, String email, String name, String paypalAccount, String bitcoinAddress, int points, int earnedPoints, int inputCode, int sentInvitationCount, int massInvite, int rfn, String encryptedDeviceId, int shareTimes, int gamingTimes, String lastGamingTime) {
        instance = new UserModel(id, deviceID, email, BuildConfig.VERSION_NAME, name, paypalAccount, bitcoinAddress, points, earnedPoints, inputCode, sentInvitationCount, massInvite, rfn, encryptedDeviceId, shareTimes, gamingTimes, lastGamingTime);
        return instance;
    }

    public static void restoreInstance(UserModel userModel) {
        instance = userModel;
    }

    public static UserModel createForLoginOrRegister(Context context, String email, String password) throws InvalidEmailException, EmptyPasswordException {
        if (!RegEx.match("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)+", email)) {
            throw new InvalidEmailException();
        } else if (password.isEmpty()) {
            throw new EmptyPasswordException();
        } else {
            instance = new UserModel(INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, email, password, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, BuildConfig.VERSION_NAME, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, null);
            instance.setSource(getSourceFromPref(context));
            return instance;
        }
    }

    public static UserModel createForGuest() {
        return new UserModel(INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, BuildConfig.VERSION_NAME, INSTAGRAM_MASS_INVITE, INSTAGRAM_MASS_INVITE, null);
    }

    public void logout() {
        instance = createForGuest();
    }

    public String getInvitationCode() {
        if (this.email.equals(BuildConfig.VERSION_NAME)) {
            return "null";
        }
        return this.email.substring(INSTAGRAM_MASS_INVITE, 1) + this.id;
    }

    public int getId() {
        return this.id;
    }

    public int getDeviceID() {
        return this.deviceID;
    }

    public String getCustomID() {
        return String.format("%sz%s", new Object[]{Integer.valueOf(this.id), Integer.valueOf(this.deviceID)});
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        if (this.name == null || this.name.equals(BuildConfig.VERSION_NAME)) {
            return BuildConfig.VERSION_NAME;
        }
        if (this.name.contains(" ")) {
            return this.name.split(" ")[INSTAGRAM_MASS_INVITE];
        }
        return this.name;
    }

    public String getLastName() {
        if (this.name == null || this.name.equals(BuildConfig.VERSION_NAME) || !this.name.contains(" ")) {
            return BuildConfig.VERSION_NAME;
        }
        String[] nameArray = this.name.split(" ");
        return nameArray[nameArray.length - 1];
    }

    public boolean setName(String name) {
        name = name.trim();
        if (!RegEx.match(".+", name)) {
            return false;
        }
        this.name = name;
        return true;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPayPalAccount() {
        return this.paypalAccount;
    }

    public boolean setPayPalAccount(String email) {
        email = email.trim();
        if (!RegEx.match("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)+", email)) {
            return false;
        }
        this.paypalAccount = email;
        return true;
    }

    public String getBitcoinAddress() {
        return this.bitcoinAddress;
    }

    public Boolean setBitcoinAddress(String bitcoinAddress) {
        bitcoinAddress = bitcoinAddress.trim();
        if (!RegEx.match("[0-9a-zA-Z]{34}", bitcoinAddress)) {
            return Boolean.valueOf(false);
        }
        this.bitcoinAddress = bitcoinAddress;
        return Boolean.valueOf(true);
    }

    public String getPassword() {
        return this.password;
    }

    public int getPoints() {
        return this.points;
    }

    public String getPointsShow() {
        return getPointsShow(getPoints());
    }

    public String getPointsShow(int num) {
        return new DecimalFormat(",###").format((long) num);
    }

    private void setSource(String value) {
        if (value == null || value.isEmpty()) {
            source = GENERAL_SOURCE;
        } else {
            source = "Android." + value;
        }
    }

    public String getSource() {
        if (source == null || source.isEmpty()) {
            source = GENERAL_SOURCE;
        }
        return source;
    }

    private static String getSourceFromPref(Context context) {
        return context.getSharedPreferences(AppNanaPrefrences.PREFERENCE, INSTAGRAM_MASS_INVITE).getString(AppNanaPrefrences.PREF_SOURCE, null);
    }

    public int getInputCodeCount() {
        return this.inputCode;
    }

    public void addInputCodeCount() {
        this.inputCode++;
    }

    public boolean canInputInvitationCode() {
        if (this.inputCode == 0) {
            return true;
        }
        if (this.inputCode <= 0) {
            return false;
        }
        if (this.sentInvitationCount < 5) {
            return false;
        }
        return true;
    }

    public int getSentInvitationCount() {
        return this.sentInvitationCount;
    }

    public void resetSentInvitaionCount() {
        this.sentInvitationCount = INSTAGRAM_MASS_INVITE;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void costPoints(int points) {
        this.points -= points;
    }

    public void addPoints(int points) {
        this.points += points;
        this.earnedPoints += points;
    }

    public void setMassInviteDone(int inviteFlag) {
        this.massInvite |= inviteFlag;
    }

    public int getInstagramInviteNanas() {
        if (hasFinishedInstagramMassInvite()) {
            return INSTAGRAM_MASS_INVITE;
        }
        String nanasString = Settings.getInstance().getInstagramInviteNanasString();
        if (this.shareTimes == 0) {
            return Integer.valueOf(nanasString.split(":")[INSTAGRAM_MASS_INVITE]).intValue();
        }
        if (this.shareTimes <= 0 || this.shareTimes >= Settings.getInstance().getShareLimit()) {
            return INSTAGRAM_MASS_INVITE;
        }
        return Integer.valueOf(nanasString.split(":")[1]).intValue();
    }

    public boolean hasFinishedFacebookMassInvite() {
        return hasFinishedOneMassInvite(FACEBOOK_MASS_INVITE);
    }

    public boolean hasFinishedInstagramMassInvite() {
        return hasFinishedOneMassInvite(INSTAGRAM_MASS_INVITE);
    }

    private boolean hasFinishedOneMassInvite(int inviteDoneFlag) {
        return (this.massInvite | inviteDoneFlag) == this.massInvite;
    }

    public int getMassInvite() {
        return this.massInvite;
    }

    public int getEarnedPoints() {
        return this.earnedPoints;
    }

    public String getSignUpKey() {
        return Hash.sha1(Device.getInstance().getAndroidID() + this.email + source + "VU58A2ND8S9");
    }

    public String getSignInKey() {
        return Hash.sha1(Device.getInstance().getAndroidID() + this.email + "VU58A2ND8S9");
    }

    public int getRfn() {
        return this.rfn;
    }

    public void setRfn(int rfn) {
        this.rfn = rfn;
    }

    public String getFormattedRfn() {
        return getPointsShow(this.rfn);
    }

    public String getEncryptedDeviceId() {
        return this.encryptedDeviceId;
    }

    public int getGamingTimes() {
        return this.gamingTimes;
    }

    public String getLastGamingTime() {
        return this.lastGamingTime;
    }

    public boolean isLogged() {
        return this.id != 0;
    }

    public int describeContents() {
        return INSTAGRAM_MASS_INVITE;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeInt(this.id);
        dest.writeInt(this.deviceID);
        dest.writeString(this.email);
        dest.writeString(this.name);
        dest.writeString(this.paypalAccount);
        dest.writeString(this.bitcoinAddress);
        dest.writeInt(this.points);
        dest.writeInt(this.earnedPoints);
        dest.writeInt(this.inputCode);
        dest.writeInt(this.sentInvitationCount);
        dest.writeInt(this.isNew ? 1 : INSTAGRAM_MASS_INVITE);
        if (!this.isDailyLogin) {
            i = INSTAGRAM_MASS_INVITE;
        }
        dest.writeInt(i);
        dest.writeInt(this.needMoreNanas);
        dest.writeInt(this.needMoreInvites);
        dest.writeInt(this.massInvite);
        dest.writeInt(this.rfn);
        dest.writeString(this.encryptedDeviceId);
        dest.writeInt(this.gamingTimes);
        dest.writeString(this.lastGamingTime);
    }
}
