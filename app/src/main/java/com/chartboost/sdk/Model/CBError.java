package com.chartboost.sdk.Model;

import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public final class CBError {
    private a a;
    private String b;
    private boolean c = true;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.MISCELLANEOUS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.PUBLIC_KEY_MISSING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.INTERNET_UNAVAILABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.NETWORK_FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[a.INVALID_RESPONSE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[a.UNEXPECTED_RESPONSE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public enum CBClickError {
        URI_INVALID,
        URI_UNRECOGNIZED,
        AGE_GATE_FAILURE,
        NO_HOST_ACTIVITY,
        INTERNAL
    }

    public enum CBImpressionError {
        INTERNAL,
        INTERNET_UNAVAILABLE,
        TOO_MANY_CONNECTIONS,
        WRONG_ORIENTATION,
        FIRST_SESSION_INTERSTITIALS_DISABLED,
        NETWORK_FAILURE,
        NO_AD_FOUND,
        SESSION_NOT_STARTED,
        IMPRESSION_ALREADY_VISIBLE,
        NO_HOST_ACTIVITY,
        USER_CANCELLATION
    }

    public enum a {
        MISCELLANEOUS,
        INTERNET_UNAVAILABLE,
        INVALID_RESPONSE,
        UNEXPECTED_RESPONSE,
        NETWORK_FAILURE,
        PUBLIC_KEY_MISSING
    }

    public CBError(a error, String errorDesc) {
        this.a = error;
        this.b = errorDesc;
    }

    public a a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public CBImpressionError c() {
        switch (AnonymousClass1.a[this.a.ordinal()]) {
            case Gender.FEMALE /*1*/:
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                return CBImpressionError.INTERNAL;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                return CBImpressionError.INTERNET_UNAVAILABLE;
            default:
                return CBImpressionError.NETWORK_FAILURE;
        }
    }

    public void a(boolean z) {
        this.c = z;
    }
}
