package com.chartboost.sdk.Tracking;

import android.text.TextUtils;
import android.util.Base64;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.impl.av;
import com.facebook.internal.AnalyticsEvents;
import java.util.EnumMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CBPostInstallTracker {
    private static CBPostInstallTracker a = null;
    private Chartboost b = Chartboost.sharedChartboost();

    public enum CBCustomEventType {
        CBCustomEventType1,
        CBCustomEventType2,
        CBCustomEventType3
    }

    public enum CBIAPPurchaseInfo {
        PRODUCT_ID,
        PRODUCT_TITLE,
        PRODUCT_DESCRIPTION,
        PRODUCT_PRICE,
        PRODUCT_CURRENCY_CODE,
        GOOGLE_PURCHASE_DATA,
        GOOGLE_PURCHASE_SIGNATURE,
        AMAZON_PURCHASE_TOKEN,
        AMAZON_USER_ID
    }

    public enum CBIAPType {
        GOOGLE_PLAY,
        AMAZON
    }

    public enum CBMiscRevenueGeneratingEventType {
        CBMiscRevenueGeneratingEventType1,
        CBMiscRevenueGeneratingEventType2,
        CBMiscRevenueGeneratingEventType3
    }

    private CBPostInstallTracker() {
    }

    public static CBPostInstallTracker sharedPostInstallTracker() {
        if (!Chartboost.isSessionStarted()) {
            return null;
        }
        if (a == null) {
            synchronized (CBPostInstallTracker.class) {
                if (a == null) {
                    a = new CBPostInstallTracker();
                }
            }
        }
        return a;
    }

    public synchronized void trackInAppPurchaseEvent(EnumMap<CBIAPPurchaseInfo, String> map, CBIAPType iapType) {
        if (!(map == null || iapType == null)) {
            if (!(TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_ID)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_TITLE)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_PRICE)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE)))) {
                try {
                    Matcher matcher = Pattern.compile("(\\d+\\.\\d+)|(\\d+)").matcher((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_PRICE));
                    matcher.find();
                    Object group = matcher.group();
                    if (TextUtils.isEmpty(group)) {
                        throw new IllegalArgumentException("Invalid price object");
                    }
                    float parseFloat = Float.parseFloat(group);
                    a aVar = null;
                    if (iapType == CBIAPType.GOOGLE_PLAY) {
                        if (TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_DATA)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_SIGNATURE))) {
                            throw new IllegalArgumentException("Null object is passed for for purchase data or purchase signature");
                        }
                        aVar = e.a(e.a("purchaseData", map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_DATA)), e.a("purchaseSignature", map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_SIGNATURE)), e.a("type", Integer.valueOf(CBIAPType.GOOGLE_PLAY.ordinal())));
                    } else if (iapType == CBIAPType.AMAZON) {
                        if (TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.AMAZON_USER_ID)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.AMAZON_PURCHASE_TOKEN))) {
                            throw new IllegalArgumentException("Null object is passed for for amazon user id or amazon purchase token");
                        }
                        aVar = e.a(e.a("userID", map.get(CBIAPPurchaseInfo.AMAZON_USER_ID)), e.a("purchaseToken", map.get(CBIAPPurchaseInfo.AMAZON_PURCHASE_TOKEN)), e.a("type", Integer.valueOf(CBIAPType.AMAZON.ordinal())));
                    }
                    if (aVar == null) {
                        CBLogging.b("CBPostInstallTracker", "Error while parsing the receipt to a JSON Object, ");
                    } else {
                        String encodeToString = Base64.encodeToString(aVar.toString().getBytes(), 2);
                        a(e.a(e.a("localized-title", map.get(CBIAPPurchaseInfo.PRODUCT_TITLE)), e.a("localized-description", map.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION)), e.a("price", Float.valueOf(parseFloat)), e.a("currency", map.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE)), e.a("productID", map.get(CBIAPPurchaseInfo.PRODUCT_ID)), e.a("receipt", encodeToString)), "iap");
                    }
                } catch (IllegalStateException e) {
                    throw new IllegalStateException("Invalid price object");
                }
            }
        }
        throw new IllegalArgumentException("Null object is passed. Please pass a valid value object");
    }

    private synchronized void a(a aVar, String str) {
        av avVar = new av(String.format(Locale.US, "%s%s", new Object[]{"/post-install-event/", str}), "https://live.chartboost.com", "postInstallTracker");
        avVar.a(str, (Object) aVar);
        avVar.a(f.a(f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, com.chartboost.sdk.Libraries.a.a)));
        avVar.a(str);
        avVar.a(true);
        avVar.j();
    }
}
