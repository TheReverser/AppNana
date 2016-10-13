package com.vungle.publisher.protocol.message;

import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.bu;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.AdPlay;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.AdReportEvent;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.protocol.message.RequestAd.a;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class ReportAd<Q extends RequestAd<Q>, O extends ReportAd<Q, O>> extends BaseJsonSerializable {
    protected Integer a;
    protected Long b;
    protected String c;
    protected String d;
    protected Integer e;
    protected String f;
    protected Boolean g;
    protected String h;
    protected Play[] i;
    protected Q j;
    protected String k;
    protected String l;

    /* compiled from: vungle */
    public static abstract class Factory<Q extends RequestAd<Q>, R extends RequestAdResponse, O extends ReportAd<Q, O>, T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>> extends MessageFactory<O> {
        @Inject
        protected Factory a;

        protected abstract a<Q> b();

        protected Factory() {
        }

        public O a(T t) {
            Play[] playArr = null;
            int i = 0;
            if (t == null) {
                return null;
            }
            Ad e = t.e();
            ReportAd reportAd = (ReportAd) a();
            reportAd.b = t.l();
            reportAd.c = e.e();
            reportAd.d = e.d();
            reportAd.e = Integer.valueOf(t.k());
            reportAd.f = t.h();
            reportAd.g = Boolean.valueOf(t.g());
            reportAd.h = t.i();
            AdPlay[] t2 = t.t();
            int length = t2 == null ? 0 : t2.length;
            if (length > 0) {
                playArr = new Play[length];
                int length2 = t2.length;
                length = 0;
                while (i < length2) {
                    int i2 = length + 1;
                    playArr[length] = Factory.a(t2[i]);
                    i++;
                    length = i2;
                }
            }
            reportAd.i = playArr;
            reportAd.j = b().b();
            return reportAd;
        }
    }

    /* compiled from: vungle */
    public static class Play extends BaseJsonSerializable {
        protected Integer a;
        protected Integer b;
        protected Long c;
        protected UserAction[] d;

        /* compiled from: vungle */
        public static abstract class Factory<R extends RequestAdResponse, T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>> extends MessageFactory<Play> {
            protected final /* synthetic */ Object a() {
                return new Play();
            }

            protected Factory() {
            }

            static Play a(P p) {
                Play play = null;
                if (p != null) {
                    play = new Play();
                    play.d = Factory.a(p.d());
                    try {
                        play.a = p.a.j();
                    } catch (NullPointerException e) {
                        Logger.w(Logger.PROTOCOL_TAG, "null play report parent");
                    }
                    play.c = p.c;
                    play.b = p.b;
                }
                return play;
            }
        }

        /* compiled from: vungle */
        public static class UserAction extends BaseJsonSerializable {
            protected String a;
            protected Long b;
            protected String c;

            /* compiled from: vungle */
            public static abstract class Factory<R extends RequestAdResponse, T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>> extends MessageFactory<UserAction> {
                protected final /* synthetic */ Object a() {
                    return new UserAction();
                }

                protected Factory() {
                }

                protected static UserAction[] a(E[] eArr) {
                    int length = eArr == null ? 0 : eArr.length;
                    if (length <= 0) {
                        return null;
                    }
                    UserAction[] userActionArr = new UserAction[length];
                    int length2 = eArr.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length2) {
                        UserAction userAction;
                        AdReportEvent adReportEvent = eArr[i];
                        int i3 = i2 + 1;
                        if (adReportEvent != null) {
                            userAction = new UserAction();
                            userAction.a = String.valueOf(adReportEvent.b);
                            userAction.b = Long.valueOf(adReportEvent.c);
                            userAction.c = adReportEvent.d;
                        } else {
                            userAction = null;
                        }
                        userActionArr[i2] = userAction;
                        i++;
                        i2 = i3;
                    }
                    return userActionArr;
                }
            }

            protected UserAction() {
            }

            public final JSONObject b() throws JSONException {
                JSONObject b = super.b();
                b.putOpt(NativeProtocol.WEB_DIALOG_ACTION, this.a);
                b.putOpt("timestamp_millis", this.b);
                b.putOpt("value", this.c);
                return b;
            }
        }

        Play() {
        }

        public final JSONObject b() throws JSONException {
            JSONObject b = super.b();
            b.putOpt("userActions", bu.a(this.d));
            b.putOpt("videoLength", this.a);
            b.putOpt("videoViewed", this.b);
            b.putOpt("startTime", this.c);
            return b;
        }
    }

    protected ReportAd() {
    }

    public final Q c() {
        return this.j;
    }

    private List<String> d() {
        List<String> arrayList = new ArrayList();
        if (this.i != null && this.i.length > 0) {
            String aVar = AdReportEvent.a.volume.toString();
            for (Play play : this.i) {
                UserAction[] userActionArr = play.d;
                if (userActionArr != null) {
                    for (UserAction userAction : userActionArr) {
                        if (!aVar.equals(userAction.a)) {
                            arrayList.add(userAction.a);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public JSONObject b() throws JSONException {
        Object valueOf;
        JSONObject b = this.j == null ? super.b() : this.j.b();
        b.putOpt("ttDownload", this.a);
        b.putOpt("adStartTime", this.b);
        b.putOpt(ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID, this.c);
        b.putOpt("campaign", this.d);
        b.putOpt("adDuration", this.e);
        if (Boolean.TRUE.equals(this.g)) {
            b.putOpt(ShareConstants.WEB_DIALOG_PARAM_NAME, this.f);
        }
        String str = "incentivized";
        Boolean bool = this.g;
        if (bool != null) {
            valueOf = Integer.valueOf(bool.booleanValue() ? 1 : 0);
        } else {
            valueOf = null;
        }
        b.putOpt(str, valueOf);
        b.putOpt("placement", this.h);
        b.putOpt("plays", bu.a(this.i));
        b.putOpt(ShareConstants.WEB_DIALOG_PARAM_ID, this.k);
        b.putOpt("clickedThrough", new JSONArray(d()));
        b.putOpt(NativeProtocol.WEB_DIALOG_URL, this.l);
        return b;
    }
}
