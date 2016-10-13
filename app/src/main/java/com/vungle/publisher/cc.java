package com.vungle.publisher;

import com.facebook.BuildConfig;
import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.net.http.TrackEventHttpTransactionFactory;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;

/* compiled from: vungle */
public class cc extends cd {
    @Inject
    public TrackEventHttpTransactionFactory a;
    @Inject
    public ScheduledPriorityExecutor b;

    public final void a(Ad<?, ?, ?> ad, a aVar, Map<String, String> map, String... strArr) {
        if (strArr != null) {
            for (final String str : strArr) {
                if (str != null) {
                    final Ad<?, ?, ?> ad2 = ad;
                    final a aVar2 = aVar;
                    final Map<String, String> map2 = map;
                    this.b.a(new Runnable(this) {
                        final /* synthetic */ cc e;

                        public final void run() {
                            try {
                                this.e.a.a(ad2, aVar2, cc.a(str, map2)).a();
                            } catch (Throwable e) {
                                Logger.w(Logger.NETWORK_TAG, "error sending " + aVar2 + " event", e);
                            }
                        }
                    }, b.externalNetworkRequest);
                }
            }
        }
    }

    static String a(String str, Map<String, String> map) {
        if (map == null) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        for (Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            int i = -1;
            while (true) {
                int indexOf = stringBuilder.indexOf(str2, i);
                if (indexOf > 0) {
                    stringBuilder.replace(indexOf, str2.length() + indexOf, entry.getValue() == null ? BuildConfig.VERSION_NAME : (String) entry.getValue());
                    i = indexOf;
                }
            }
        }
        return stringBuilder.toString();
    }
}
