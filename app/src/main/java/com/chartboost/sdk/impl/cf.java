package com.chartboost.sdk.impl;

import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public final class cf {
    private final String a;
    private final List<Certificate> b;
    private final List<Certificate> c;

    private cf(String str, List<Certificate> list, List<Certificate> list2) {
        this.a = str;
        this.b = list;
        this.c = list2;
    }

    public static cf a(SSLSession sSLSession) {
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        Object[] peerCertificates;
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            peerCertificates = null;
        }
        List a = peerCertificates != null ? cs.a(peerCertificates) : Collections.emptyList();
        Object[] localCertificates = sSLSession.getLocalCertificates();
        return new cf(cipherSuite, a, localCertificates != null ? cs.a(localCertificates) : Collections.emptyList());
    }

    public static cf a(String str, List<Certificate> list, List<Certificate> list2) {
        if (str != null) {
            return new cf(str, cs.a((List) list), cs.a((List) list2));
        }
        throw new IllegalArgumentException("cipherSuite == null");
    }

    public String a() {
        return this.a;
    }

    public List<Certificate> b() {
        return this.b;
    }

    public Principal c() {
        return !this.b.isEmpty() ? ((X509Certificate) this.b.get(0)).getSubjectX500Principal() : null;
    }

    public List<Certificate> d() {
        return this.c;
    }

    public Principal e() {
        return !this.c.isEmpty() ? ((X509Certificate) this.c.get(0)).getSubjectX500Principal() : null;
    }

    public boolean equals(Object other) {
        if (!(other instanceof cf)) {
            return false;
        }
        cf cfVar = (cf) other;
        if (this.a.equals(cfVar.a) && this.b.equals(cfVar.b) && this.c.equals(cfVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
