package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.cg.a;
import com.chartboost.sdk.impl.cg.b;
import java.io.IOException;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class cx {
    public static final cg a = new cg() {
        public b a(Proxy proxy, URL url, List<a> list) throws IOException {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                a aVar = (a) list.get(i);
                if ("Basic".equalsIgnoreCase(aVar.a())) {
                    PasswordAuthentication requestPasswordAuthentication = Authenticator.requestPasswordAuthentication(url.getHost(), a(proxy, url), url.getPort(), url.getProtocol(), aVar.b(), aVar.a(), url, RequestorType.SERVER);
                    if (requestPasswordAuthentication != null) {
                        return b.a(requestPasswordAuthentication.getUserName(), new String(requestPasswordAuthentication.getPassword()));
                    }
                }
            }
            return null;
        }

        public b b(Proxy proxy, URL url, List<a> list) throws IOException {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                a aVar = (a) list.get(i);
                if ("Basic".equalsIgnoreCase(aVar.a())) {
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();
                    PasswordAuthentication requestPasswordAuthentication = Authenticator.requestPasswordAuthentication(inetSocketAddress.getHostName(), a(proxy, url), inetSocketAddress.getPort(), url.getProtocol(), aVar.b(), aVar.a(), url, RequestorType.PROXY);
                    if (requestPasswordAuthentication != null) {
                        return b.a(requestPasswordAuthentication.getUserName(), new String(requestPasswordAuthentication.getPassword()));
                    }
                }
            }
            return null;
        }

        private InetAddress a(Proxy proxy, URL url) throws IOException {
            return (proxy == null || proxy.type() == Type.DIRECT) ? InetAddress.getByName(url.getHost()) : ((InetSocketAddress) proxy.address()).getAddress();
        }
    };

    public static dg a(cg cgVar, di diVar, Proxy proxy) throws IOException {
        String str;
        String str2;
        if (diVar.c() == 401) {
            str = "WWW-Authenticate";
            str2 = "Authorization";
        } else if (diVar.c() == 407) {
            str = "Proxy-Authenticate";
            str2 = "Proxy-Authorization";
        } else {
            throw new IllegalArgumentException();
        }
        List a = a(diVar.g(), str);
        if (a.isEmpty()) {
            return null;
        }
        dg a2 = diVar.a();
        b b = diVar.c() == 407 ? cgVar.b(proxy, a2.a(), a) : cgVar.a(proxy, a2.a(), a);
        if (b == null) {
            return null;
        }
        return a2.f().a(str2, b.a()).a();
    }

    private static List<a> a(cw cwVar, String str) {
        List<a> arrayList = new ArrayList();
        for (int i = 0; i < cwVar.a(); i++) {
            if (str.equalsIgnoreCase(cwVar.a(i))) {
                String b = cwVar.b(i);
                int i2 = 0;
                while (i2 < b.length()) {
                    int a = cv.a(b, i2, " ");
                    String trim = b.substring(i2, a).trim();
                    a = cv.a(b, a);
                    if (!b.regionMatches(true, a, "realm=\"", 0, "realm=\"".length())) {
                        break;
                    }
                    i2 = "realm=\"".length() + a;
                    a = cv.a(b, i2, "\"");
                    String substring = b.substring(i2, a);
                    i2 = cv.a(b, cv.a(b, a + 1, ",") + 1);
                    arrayList.add(new a(trim, substring));
                }
            }
        }
        return arrayList;
    }
}
