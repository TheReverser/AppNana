package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking;
import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class EventTracking extends as<Integer> {
    String a;
    a b;
    String c;
    @Inject
    Factory d;

    @Singleton
    /* compiled from: vungle */
    static class Factory extends com.vungle.publisher.as.a<EventTracking, Integer> {
        @Inject
        Provider<EventTracking> a;

        protected final /* synthetic */ as a(as asVar, Cursor cursor) {
            EventTracking eventTracking = (EventTracking) asVar;
            eventTracking.s = aq.d(cursor, ShareConstants.WEB_DIALOG_PARAM_ID);
            eventTracking.a = aq.f(cursor, "ad_id");
            eventTracking.b = (a) aq.a(cursor, "event", a.class);
            eventTracking.c = aq.f(cursor, NativeProtocol.WEB_DIALOG_URL);
            return eventTracking;
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new EventTracking[i];
        }

        protected final /* synthetic */ as c_() {
            return b();
        }

        Factory() {
        }

        final Map<a, List<EventTracking>> a(String str, ThirdPartyAdTracking thirdPartyAdTracking) {
            if (thirdPartyAdTracking == null) {
                return null;
            }
            Map<a, List<EventTracking>> hashMap = new HashMap();
            a(str, hashMap, a.error, thirdPartyAdTracking.f());
            a(str, hashMap, a.mute, thirdPartyAdTracking.g());
            PlayCheckpoint[] i = thirdPartyAdTracking.i();
            if (i != null && i.length > 0) {
                for (PlayCheckpoint playCheckpoint : i) {
                    Float f = playCheckpoint.a;
                    if (f != null) {
                        a aVar;
                        float floatValue = f.floatValue();
                        if (floatValue == 0.0f) {
                            aVar = a.play_percentage_0;
                        } else if (((double) floatValue) == 0.25d) {
                            aVar = a.play_percentage_25;
                        } else if (((double) floatValue) == 0.5d) {
                            aVar = a.play_percentage_50;
                        } else if (((double) floatValue) == 0.75d) {
                            aVar = a.play_percentage_75;
                        } else if (floatValue == 1.0f) {
                            aVar = a.play_percentage_100;
                        } else {
                            Logger.w(Logger.DATABASE_TAG, "invalid play percent: " + floatValue);
                            aVar = null;
                        }
                        if (aVar != null) {
                            a(str, hashMap, aVar, playCheckpoint.b);
                        }
                    }
                }
            }
            a(str, hashMap, a.postroll_click, thirdPartyAdTracking.c());
            a(str, hashMap, a.postroll_view, thirdPartyAdTracking.j());
            a(str, hashMap, a.video_click, thirdPartyAdTracking.d());
            a(str, hashMap, a.video_close, thirdPartyAdTracking.e());
            a(str, hashMap, a.video_pause, thirdPartyAdTracking.h());
            a(str, hashMap, a.video_resume, thirdPartyAdTracking.k());
            a(str, hashMap, a.unmute, thirdPartyAdTracking.l());
            return hashMap;
        }

        private void a(String str, Map<a, List<EventTracking>> map, a aVar, String[] strArr) {
            if (strArr != null && strArr.length > 0) {
                List list;
                if (strArr == null || strArr.length <= 0) {
                    list = null;
                } else {
                    List arrayList = new ArrayList();
                    for (String str2 : strArr) {
                        Object obj;
                        if (aVar == null || str2 == null) {
                            obj = null;
                        } else {
                            obj = (EventTracking) this.a.get();
                            obj.a = str;
                            obj.b = aVar;
                            obj.c = str2;
                        }
                        if (obj != null) {
                            arrayList.add(obj);
                        }
                    }
                    list = arrayList;
                }
                if (list == null || list.isEmpty()) {
                    list = null;
                }
                if (list != null && !list.isEmpty()) {
                    map.put(aVar, list);
                }
            }
        }

        final void a(String str) {
            Logger.v(Logger.DATABASE_TAG, "deleted " + this.c.getWritableDatabase().delete("event_tracking", "ad_id = ?", new String[]{str}) + " expired event_tracking records for adId: " + str);
        }

        static void a(Map<a, List<EventTracking>> map) {
            if (map != null) {
                for (List<EventTracking> list : map.values()) {
                    if (list != null) {
                        for (EventTracking r : list) {
                            r.r();
                        }
                    }
                }
            }
        }

        final Map<a, List<EventTracking>> b(String str) {
            Throwable th;
            Cursor cursor = null;
            if (str == null) {
                Logger.w(Logger.DATABASE_TAG, "failed to fetch event_tracking records by ad_id: " + str);
                return null;
            }
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching event_tracking records by ad_id: " + str);
                Cursor query = this.c.getReadableDatabase().query("event_tracking", null, "ad_id = ?", new String[]{str}, null, null, null);
                try {
                    Map<a, List<EventTracking>> map;
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, count + " event_tracking for ad_id: " + str);
                    if (count > 0) {
                        Map<a, List<EventTracking>> hashMap = new HashMap();
                        while (query.moveToNext()) {
                            as b = b();
                            b(b, query);
                            if (b != null) {
                                a aVar = b.b;
                                List list = (List) hashMap.get(aVar);
                                if (list == null) {
                                    list = new ArrayList();
                                    hashMap.put(aVar, list);
                                }
                                list.add(b);
                            }
                        }
                        map = hashMap;
                    } else {
                        map = null;
                    }
                    if (query == null) {
                        return map;
                    }
                    query.close();
                    return map;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        protected final String e_() {
            return "event_tracking";
        }

        private EventTracking b() {
            return (EventTracking) this.a.get();
        }
    }

    /* compiled from: vungle */
    public enum a {
        error,
        mute,
        play_percentage_0(0.0f),
        play_percentage_25(0.25f),
        play_percentage_50(0.5f),
        play_percentage_75(0.75f),
        play_percentage_80(0.8f),
        play_percentage_100(0.99f),
        postroll_click,
        postroll_view,
        unmute,
        video_click,
        video_close,
        video_pause,
        video_resume;
        
        public final float p;

        private a(float f) {
            this.p = f;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.as.a a_() {
        return this.d;
    }

    EventTracking() {
    }

    protected final String b() {
        return "event_tracking";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShareConstants.WEB_DIALOG_PARAM_ID, (Integer) this.s);
        contentValues.put("ad_id", this.a);
        contentValues.put("event", this.b.toString());
        contentValues.put(NativeProtocol.WEB_DIALOG_URL, this.c);
        return contentValues;
    }

    public final StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "ad_id", this.a, false);
        as.a(p, "event", this.b, false);
        as.a(p, NativeProtocol.WEB_DIALOG_URL, this.c, false);
        return p;
    }
}
