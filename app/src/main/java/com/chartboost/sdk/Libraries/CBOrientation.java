package com.chartboost.sdk.Libraries;

import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public enum CBOrientation {
    UNSPECIFIED,
    PORTRAIT,
    LANDSCAPE,
    PORTRAIT_REVERSE,
    LANDSCAPE_REVERSE;
    
    public static final CBOrientation LANDSCAPE_LEFT = null;
    public static final CBOrientation LANDSCAPE_RIGHT = null;
    public static final CBOrientation PORTRAIT_LEFT = null;
    public static final CBOrientation PORTRAIT_RIGHT = null;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = null;
        static final /* synthetic */ int[] b = null;

        static {
            b = new int[Difference.values().length];
            try {
                b[Difference.ANGLE_90.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[Difference.ANGLE_180.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[Difference.ANGLE_270.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[Difference.ANGLE_0.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            a = new int[CBOrientation.values().length];
            try {
                a[CBOrientation.LANDSCAPE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[CBOrientation.PORTRAIT_REVERSE.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[CBOrientation.LANDSCAPE_REVERSE.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[CBOrientation.PORTRAIT.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[CBOrientation.UNSPECIFIED.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public enum Difference {
        public static final Difference ANGLE_0 = null;
        public static final Difference ANGLE_180 = null;
        public static final Difference ANGLE_270 = null;
        public static final Difference ANGLE_90 = null;
        private static final /* synthetic */ Difference[] a = null;

        private Difference(String str, int i) {
        }

        public static Difference valueOf(String name) {
            return (Difference) Enum.valueOf(Difference.class, name);
        }

        public static Difference[] values() {
            return (Difference[]) a.clone();
        }

        static {
            ANGLE_0 = new Difference("ANGLE_0", 0);
            ANGLE_90 = new Difference("ANGLE_90", 1);
            ANGLE_180 = new Difference("ANGLE_180", 2);
            ANGLE_270 = new Difference("ANGLE_270", 3);
            a = new Difference[]{ANGLE_0, ANGLE_90, ANGLE_180, ANGLE_270};
        }

        public int getAsInt() {
            switch (AnonymousClass1.b[ordinal()]) {
                case Gender.FEMALE /*1*/:
                    return 90;
                case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                    return 180;
                case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                    return 270;
                default:
                    return 0;
            }
        }

        public boolean isOdd() {
            return this == ANGLE_90 || this == ANGLE_270;
        }

        public boolean isReverse() {
            return this == ANGLE_180 || this == ANGLE_270;
        }
    }

    static {
        PORTRAIT_LEFT = PORTRAIT_REVERSE;
        PORTRAIT_RIGHT = PORTRAIT;
        LANDSCAPE_LEFT = LANDSCAPE;
        LANDSCAPE_RIGHT = LANDSCAPE_REVERSE;
    }

    public CBOrientation rotate90() {
        switch (AnonymousClass1.a[ordinal()]) {
            case Gender.FEMALE /*1*/:
                return PORTRAIT_LEFT;
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                return LANDSCAPE_RIGHT;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                return PORTRAIT_RIGHT;
            case Logger.INFO_LOGGING_LEVEL /*4*/:
                return LANDSCAPE_LEFT;
            default:
                return UNSPECIFIED;
        }
    }

    public CBOrientation rotate180() {
        return rotate90().rotate90();
    }

    public CBOrientation rotate270() {
        return rotate90().rotate90().rotate90();
    }

    public boolean isPortrait() {
        return this == PORTRAIT || this == PORTRAIT_REVERSE;
    }

    public boolean isLandscape() {
        return this == LANDSCAPE || this == LANDSCAPE_REVERSE;
    }
}
