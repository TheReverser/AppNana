package com.chartboost.sdk.Model;

import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Libraries.f.a;
import com.chartboost.sdk.Libraries.f.k;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;

public class b {
    public static a a = f.b(f.a("close", e), f.a("header-center", e), f.a("header-tile", e));
    public static a b = f.a(f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, com.chartboost.sdk.Libraries.a.a), f.a("ad_id", f.a()), f.a(ShareConstants.WEB_DIALOG_PARAM_TO, f.a()), f.a("type", f.a()), f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, f.a(f.a())), f.a(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, f.a()), f.a(ShareConstants.WEB_DIALOG_PARAM_LINK, f.a()), f.a("cgn", f.a()), f.a("creative", f.a()), f.a("assets", f), f.a("ux", g), f.a("reward", f.a(f.a(Object.class))));
    public static a c = f.a(f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, com.chartboost.sdk.Libraries.a.a), f.a(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, f.a()), f.a("type", f.a()), f.a("assets", a), f.a("cells", h));
    private static a d = f.a(f.a(NativeProtocol.WEB_DIALOG_URL, f.a()), f.a(ShareConstants.WEB_DIALOG_PARAM_ID, f.a()), f.a("local-file", f.a()), f.a("buffer", f.a(f.b())));
    private static a e;
    private static a f = f.b(f.a("video-landscape", f.a(d)), f.a("video-portrait", f.a(d)), f.a("ad-landscape", f.a(e)), f.a("frame-landscape", f.a(e)), f.a("close-landscape", f.a(e)), f.a("replay-landscape", f.a(e)), f.a("ad-portrait", f.a(e)), f.a("frame-portrait", f.a(e)), f.a("close-portrait", f.a(e)), f.a("replay-portrait", f.a(e)), f.a("video-click-button", f.a(e)), f.a("post-video-icon", f.a(e)), f.a("post-video-button", f.a(e)), f.a("video-confirmation-icon", f.a(e)), f.a("video-confirmation-button", f.a(e)), f.a("post-video-reward-icon", f.a(e)), f.a("blur-background", f.a(e)));
    private static a g;
    private static a h;

    static {
        k[] kVarArr = new k[4];
        kVarArr[0] = f.a(NativeProtocol.WEB_DIALOG_URL, f.a());
        kVarArr[1] = f.a("checksum", f.a());
        kVarArr[2] = f.a("scale", f.a(f.b()));
        kVarArr[3] = f.a("offset", f.a(f.b(f.a("x", f.b()), f.a("y", f.b()))));
        e = f.a(kVarArr);
        kVarArr = new k[11];
        kVarArr[0] = f.a("progress", f.a(f.b(f.a("delay", f.b()), f.a("background-color", f.a()), f.a("progress-color", f.a()), f.a("border-color", f.a()), f.a("radius", f.b()))));
        kVarArr[1] = f.a("video-controls-togglable", f.a(f.c()));
        kVarArr[2] = f.a("video-controls-background", f.b(f.a("color", f.a()), f.a("border-color", f.a())));
        kVarArr[3] = f.a("post-video-toaster", f.a(f.b(f.a(ShareConstants.WEB_DIALOG_PARAM_TITLE, f.a()), f.a("tagline", f.a()))));
        kVarArr[4] = f.a("pre-popup", f.a(f.b(f.a(ShareConstants.WEB_DIALOG_PARAM_TITLE, f.a()), f.a("text", f.a()), f.a("confirm", f.a()), f.a("cancel", f.a()))));
        kVarArr[5] = f.a("post-popup", f.a(f.b(f.a(ShareConstants.WEB_DIALOG_PARAM_TITLE, f.a()), f.a("text", f.a()), f.a("confirm", f.a()))));
        kVarArr[6] = f.a("cancel-popup", f.a(f.b(f.a(ShareConstants.WEB_DIALOG_PARAM_TITLE, f.a()), f.a("text", f.a()), f.a("confirm", f.a()), f.a("cancel", f.a()))));
        kVarArr[7] = f.a("confirmation", f.a(f.b(f.a("text", f.a()), f.a("color", f.a()))));
        kVarArr[8] = f.a("video-progress-timer-enabled", f.a(f.c()));
        k[] kVarArr2 = new k[2];
        kVarArr2[0] = f.a("text", f.a());
        kVarArr2[1] = f.a("position", f.a("inside-top", "outside-bottom"));
        kVarArr[9] = f.a("post-video-reward-toaster", f.a(f.b(kVarArr2)));
        kVarArr[10] = f.a("play-another-popup", f.a(f.b(f.a("skip-confirmation", f.c()), f.a(ShareConstants.WEB_DIALOG_PARAM_TITLE, f.a()), f.a("text", f.a()), f.a("cancel", f.a()), f.a("confirm", f.a()))));
        g = f.a(f.b(kVarArr));
        kVarArr = new k[8];
        kVarArr[4] = f.a("assets", f.a(f.a("icon", e)));
        kVarArr[5] = f.a("text", f.a());
        kVarArr[6] = f.a(ShareConstants.WEB_DIALOG_PARAM_LINK, f.a(f.a()));
        kVarArr[7] = f.a("itunes_id", f.a(f.a()));
        h = f.b(f.a(kVarArr));
    }
}
