package com.chartboost.sdk.impl;

import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBOrientation.Difference;
import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public final class be {

    public interface a {
        void a(com.chartboost.sdk.Model.a aVar);
    }

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[Difference.values().length];
        static final /* synthetic */ int[] b = new int[b.values().length];

        static {
            try {
                b[b.FADE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[b.PERSPECTIVE_ZOOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[b.PERSPECTIVE_ROTATE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[b.SLIDE_FROM_BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[b.SLIDE_FROM_TOP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[b.BOUNCE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[Difference.ANGLE_90.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[Difference.ANGLE_180.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[Difference.ANGLE_270.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[Difference.ANGLE_0.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public enum b {
        PERSPECTIVE_ROTATE,
        BOUNCE,
        PERSPECTIVE_ZOOM,
        SLIDE_FROM_BOTTOM,
        SLIDE_FROM_TOP,
        FADE,
        NONE;

        public static b a(int i) {
            if (i != 0 && i > 0 && i <= values().length) {
                return values()[i - 1];
            }
            return null;
        }
    }

    public static void a(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2) {
        b(bVar, aVar, aVar2, true);
    }

    public static void b(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2) {
        c(bVar, aVar, aVar2, false);
    }

    private static void b(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2, boolean z) {
        if (bVar == b.NONE) {
            if (aVar2 != null) {
                aVar2.a(aVar);
            }
        } else if (aVar == null || aVar.i == null) {
            CBLogging.a("CBAnimationManager", "Transition of impression canceled due to lack of container");
        } else {
            final View f = aVar.i.f();
            if (f == null) {
                CBLogging.a("CBAnimationManager", "Transition of impression canceled due to lack of view");
                return;
            }
            ViewTreeObserver viewTreeObserver = f.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                final b bVar2 = bVar;
                final com.chartboost.sdk.Model.a aVar3 = aVar;
                final a aVar4 = aVar2;
                final boolean z2 = z;
                viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        f.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        be.c(bVar2, aVar3, aVar4, z2);
                    }
                });
            }
        }
    }

    private static void c(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2, boolean z) {
        CBPreferences instance = CBPreferences.getInstance();
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(1.0f, 1.0f));
        if (aVar == null || aVar.i == null) {
            CBLogging.a("CBAnimationManager", "Transition of impression canceled due to lack of container");
            return;
        }
        View f = aVar.i.f();
        if (f == null) {
            CBLogging.a("CBAnimationManager", "Transition of impression canceled due to lack of view");
            return;
        }
        Animation alphaAnimation;
        float width = (float) f.getWidth();
        float height = (float) f.getHeight();
        float f2 = (1.0f - 0.4f) / 2.0f;
        Difference forcedOrientationDifference = instance.getForcedOrientationDifference();
        float f3;
        float f4;
        float f5;
        float f6;
        switch (AnonymousClass3.b[bVar.ordinal()]) {
            case Gender.FEMALE /*1*/:
                if (z) {
                    alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                } else {
                    alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                }
                alphaAnimation.setDuration(600);
                alphaAnimation.setFillAfter(true);
                Animation animationSet2 = new AnimationSet(true);
                animationSet2.addAnimation(alphaAnimation);
                alphaAnimation = animationSet2;
                break;
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                switch (AnonymousClass3.a[forcedOrientationDifference.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                        if (!z) {
                            alphaAnimation = new bj(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        } else {
                            alphaAnimation = new bj(-1114636288, 0.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        }
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        if (!z) {
                            alphaAnimation = new bj(0.0f, -1114636288, width / 2.0f, height / 2.0f, false);
                            break;
                        } else {
                            alphaAnimation = new bj(60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        }
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        if (!z) {
                            alphaAnimation = new bj(0.0f, -1114636288, width / 2.0f, height / 2.0f, true);
                            break;
                        } else {
                            alphaAnimation = new bj(60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        }
                    default:
                        if (!z) {
                            alphaAnimation = new bj(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        } else {
                            alphaAnimation = new bj(-1114636288, 0.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        }
                }
                alphaAnimation.setDuration(600);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                } else {
                    alphaAnimation = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                }
                alphaAnimation.setDuration(600);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                switch (AnonymousClass3.a[forcedOrientationDifference.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                        if (!z) {
                            alphaAnimation = new TranslateAnimation(0.0f, (-width) * 0.4f, 0.0f, height * f2);
                            break;
                        } else {
                            alphaAnimation = new TranslateAnimation(width, 0.0f, height * f2, 0.0f);
                            break;
                        }
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        if (!z) {
                            alphaAnimation = new TranslateAnimation(0.0f, width * f2, 0.0f, (-height) * 0.4f);
                            break;
                        } else {
                            alphaAnimation = new TranslateAnimation(width * f2, 0.0f, height, 0.0f);
                            break;
                        }
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        if (!z) {
                            alphaAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * f2);
                            break;
                        } else {
                            alphaAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f2, 0.0f);
                            break;
                        }
                    default:
                        if (!z) {
                            alphaAnimation = new TranslateAnimation(0.0f, width * f2, 0.0f, height);
                            break;
                        } else {
                            alphaAnimation = new TranslateAnimation(width * f2, 0.0f, (-height) * 0.4f, 0.0f);
                            break;
                        }
                }
                alphaAnimation.setDuration(600);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                switch (AnonymousClass3.a[forcedOrientationDifference.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                        if (!z) {
                            alphaAnimation = new bj(0.0f, -1114636288, width / 2.0f, height / 2.0f, false);
                            break;
                        } else {
                            alphaAnimation = new bj(60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        }
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        if (!z) {
                            alphaAnimation = new bj(0.0f, -1114636288, width / 2.0f, height / 2.0f, true);
                            break;
                        } else {
                            alphaAnimation = new bj(60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        }
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        if (!z) {
                            alphaAnimation = new bj(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        } else {
                            alphaAnimation = new bj(-1114636288, 0.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        }
                    default:
                        if (!z) {
                            alphaAnimation = new bj(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        } else {
                            alphaAnimation = new bj(-1114636288, 0.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        }
                }
                alphaAnimation.setDuration(600);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                } else {
                    alphaAnimation = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                }
                alphaAnimation.setDuration(600);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                switch (AnonymousClass3.a[forcedOrientationDifference.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                        if (!z) {
                            alphaAnimation = new TranslateAnimation(0.0f, width * f2, 0.0f, height);
                            break;
                        } else {
                            alphaAnimation = new TranslateAnimation(width * f2, 0.0f, (-height) * 0.4f, 0.0f);
                            break;
                        }
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        if (!z) {
                            alphaAnimation = new TranslateAnimation(0.0f, (-width) * 0.4f, 0.0f, height * f2);
                            break;
                        } else {
                            alphaAnimation = new TranslateAnimation(width, 0.0f, height * f2, 0.0f);
                            break;
                        }
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        if (!z) {
                            alphaAnimation = new TranslateAnimation(0.0f, width * f2, 0.0f, (-height) * 0.4f);
                            break;
                        } else {
                            alphaAnimation = new TranslateAnimation(width * f2, 0.0f, height, 0.0f);
                            break;
                        }
                    default:
                        if (!z) {
                            alphaAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * f2);
                            break;
                        } else {
                            alphaAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f2, 0.0f);
                            break;
                        }
                }
                alphaAnimation.setDuration(600);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            case Logger.INFO_LOGGING_LEVEL /*4*/:
                switch (AnonymousClass3.a[forcedOrientationDifference.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                        f3 = z ? -width : 0.0f;
                        f4 = 0.0f;
                        f5 = z ? 0.0f : -width;
                        f6 = 0.0f;
                        break;
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        f4 = z ? -height : 0.0f;
                        f6 = z ? 0.0f : -height;
                        f5 = 0.0f;
                        f3 = 0.0f;
                        break;
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        f3 = z ? width : 0.0f;
                        if (z) {
                            f6 = 0.0f;
                        } else {
                            f6 = width;
                        }
                        f4 = 0.0f;
                        f5 = f6;
                        f6 = 0.0f;
                        break;
                    case Logger.INFO_LOGGING_LEVEL /*4*/:
                        f4 = z ? height : 0.0f;
                        if (z) {
                            f6 = 0.0f;
                        } else {
                            f6 = height;
                        }
                        f5 = 0.0f;
                        f3 = 0.0f;
                        break;
                    default:
                        f6 = 0.0f;
                        f4 = 0.0f;
                        f5 = 0.0f;
                        f3 = 0.0f;
                        break;
                }
                Animation translateAnimation = new TranslateAnimation(f3, f5, f4, f6);
                translateAnimation.setDuration(600);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case Logger.WARN_LOGGING_LEVEL /*5*/:
                switch (AnonymousClass3.a[forcedOrientationDifference.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                        f6 = z ? width : 0.0f;
                        if (z) {
                            width = 0.0f;
                        }
                        f4 = 0.0f;
                        f5 = f6;
                        f6 = 0.0f;
                        break;
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        f6 = z ? height : 0.0f;
                        if (z) {
                            height = 0.0f;
                        }
                        f4 = f6;
                        width = 0.0f;
                        f5 = 0.0f;
                        f6 = height;
                        break;
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        f3 = z ? -width : 0.0f;
                        width = z ? 0.0f : -width;
                        f6 = 0.0f;
                        f4 = 0.0f;
                        f5 = f3;
                        break;
                    case Logger.INFO_LOGGING_LEVEL /*4*/:
                        f4 = z ? -height : 0.0f;
                        f6 = z ? 0.0f : -height;
                        width = 0.0f;
                        f5 = 0.0f;
                        break;
                    default:
                        f6 = 0.0f;
                        width = 0.0f;
                        f4 = 0.0f;
                        f5 = 0.0f;
                        break;
                }
                Animation translateAnimation2 = new TranslateAnimation(f5, width, f4, f6);
                translateAnimation2.setDuration(600);
                translateAnimation2.setFillAfter(true);
                animationSet.addAnimation(translateAnimation2);
                alphaAnimation = animationSet;
                break;
            case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                if (!z) {
                    alphaAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                    alphaAnimation.setDuration(600);
                    alphaAnimation.setStartOffset(0);
                    alphaAnimation.setFillAfter(true);
                    animationSet.addAnimation(alphaAnimation);
                    alphaAnimation = animationSet;
                    break;
                }
                alphaAnimation = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) 600) * 0.6f));
                alphaAnimation.setStartOffset(0);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = new ScaleAnimation(1.0f, 0.81818175f, 1.0f, 0.81818175f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) 600) * 0.19999999f));
                alphaAnimation.setStartOffset((long) Math.round(((float) 600) * 0.6f));
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = new ScaleAnimation(1.0f, 1.1111112f, 1.0f, 1.1111112f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) 600) * 0.099999964f));
                alphaAnimation.setStartOffset((long) Math.round(((float) 600) * 0.8f));
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            default:
                alphaAnimation = animationSet;
                break;
        }
        if (bVar != b.NONE) {
            if (aVar2 != null) {
                final a aVar3 = aVar2;
                final com.chartboost.sdk.Model.a aVar4 = aVar;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        aVar3.a(aVar4);
                    }
                }, 600);
            }
            f.startAnimation(alphaAnimation);
        } else if (aVar2 != null) {
            aVar2.a(aVar);
        }
    }

    public static void a(boolean z, View view) {
        a(z, view, 510);
    }

    public static void a(boolean z, View view, long j) {
        float f;
        float f2 = 1.0f;
        if (z) {
            view.setVisibility(0);
        }
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (!z) {
            f2 = 0.0f;
        }
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(510);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }
}
