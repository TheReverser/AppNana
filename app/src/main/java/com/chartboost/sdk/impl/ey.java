package com.chartboost.sdk.impl;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

public final class ey implements HostnameVerifier {
    public static final ey a = new ey();
    private static final Pattern b = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private ey() {
    }

    public boolean verify(String host, SSLSession session) {
        try {
            return a(host, (X509Certificate) session.getPeerCertificates()[0]);
        } catch (SSLException e) {
            return false;
        }
    }

    public boolean a(String str, X509Certificate x509Certificate) {
        return a(str) ? b(str, x509Certificate) : c(str, x509Certificate);
    }

    static boolean a(String str) {
        return b.matcher(str).matches();
    }

    private boolean b(String str, X509Certificate x509Certificate) {
        for (String equalsIgnoreCase : a(x509Certificate, 7)) {
            if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                return true;
            }
        }
        return false;
    }

    private boolean c(String str, X509Certificate x509Certificate) {
        String toLowerCase = str.toLowerCase(Locale.US);
        boolean z = false;
        for (String a : a(x509Certificate, 2)) {
            String a2;
            if (a(toLowerCase, a2)) {
                return true;
            }
            z = true;
        }
        if (!z) {
            a2 = new ex(x509Certificate.getSubjectX500Principal()).a("cn");
            if (a2 != null) {
                return a(toLowerCase, a2);
            }
        }
        return false;
    }

    private List<String> a(X509Certificate x509Certificate, int i) {
        List<String> arrayList = new ArrayList();
        try {
            Collection<List> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2) {
                    Integer num = (Integer) list.get(0);
                    if (num != null && num.intValue() == i) {
                        String str = (String) list.get(1);
                        if (str != null) {
                            arrayList.add(str);
                        }
                    }
                }
            }
            return arrayList;
        } catch (CertificateParsingException e) {
            return Collections.emptyList();
        }
    }

    public boolean a(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return false;
        }
        String toLowerCase = str2.toLowerCase(Locale.US);
        if (!toLowerCase.contains("*")) {
            return str.equals(toLowerCase);
        }
        if (toLowerCase.startsWith("*.") && str.regionMatches(0, toLowerCase, 2, toLowerCase.length() - 2)) {
            return true;
        }
        int indexOf = toLowerCase.indexOf(42);
        if (indexOf > toLowerCase.indexOf(46)) {
            return false;
        }
        if (!str.regionMatches(0, toLowerCase, 0, indexOf)) {
            return false;
        }
        int length = toLowerCase.length() - (indexOf + 1);
        int length2 = str.length() - length;
        if (str.indexOf(46, indexOf) < length2 && !str.endsWith(".clients.google.com")) {
            return false;
        }
        if (str.regionMatches(length2, toLowerCase, indexOf + 1, length)) {
            return true;
        }
        return false;
    }
}
