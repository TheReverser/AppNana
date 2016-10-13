package com.vungle.publisher.protocol.message;

import com.vungle.publisher.db.model.AdReportExtra;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
final class ExtraInfo extends BaseJsonSerializable {
    protected Map<String, String> a;

    @Singleton
    /* compiled from: vungle */
    static class Factory extends MessageFactory<ExtraInfo> {
        protected final /* synthetic */ Object a() {
            return new ExtraInfo();
        }

        @Inject
        Factory() {
        }

        protected static ExtraInfo a(Map<String, String> map) {
            if (map == null) {
                return null;
            }
            ExtraInfo extraInfo = new ExtraInfo();
            extraInfo.a = new HashMap(map);
            return extraInfo;
        }

        protected static ExtraInfo b(Map<String, AdReportExtra> map) {
            if (map == null) {
                return null;
            }
            ExtraInfo extraInfo = new ExtraInfo();
            Map hashMap = new HashMap();
            extraInfo.a = hashMap;
            for (AdReportExtra adReportExtra : map.values()) {
                hashMap.put(adReportExtra.b, adReportExtra.c);
            }
            return extraInfo;
        }
    }

    ExtraInfo() {
    }

    public final JSONObject b() throws JSONException {
        return (this.a == null || this.a.isEmpty()) ? null : new JSONObject(this.a);
    }
}
