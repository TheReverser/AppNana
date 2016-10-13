package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.ResponseCache;
import java.net.SecureCacheResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

public class dj implements ci {
    private final ResponseCache a;

    private static final class a extends HttpURLConnection {
        private final dg a;
        private final di b;

        public a(di diVar) {
            boolean z = true;
            super(diVar.a().a());
            this.a = diVar.a();
            this.b = diVar;
            this.connected = true;
            if (diVar.h() != null) {
                z = false;
            }
            this.doOutput = z;
            this.method = this.a.c();
        }

        public void connect() throws IOException {
            throw dj.f();
        }

        public void disconnect() {
            throw dj.f();
        }

        public void setRequestProperty(String key, String value) {
            throw dj.f();
        }

        public void addRequestProperty(String key, String value) {
            throw dj.f();
        }

        public String getRequestProperty(String key) {
            return this.a.a(key);
        }

        public Map<String, List<String>> getRequestProperties() {
            throw dj.g();
        }

        public void setFixedLengthStreamingMode(int contentLength) {
            throw dj.f();
        }

        public void setChunkedStreamingMode(int chunklen) {
            throw dj.f();
        }

        public void setInstanceFollowRedirects(boolean followRedirects) {
            throw dj.f();
        }

        public boolean getInstanceFollowRedirects() {
            return super.getInstanceFollowRedirects();
        }

        public void setRequestMethod(String method) throws ProtocolException {
            throw dj.f();
        }

        public String getRequestMethod() {
            return this.a.c();
        }

        public String getHeaderFieldKey(int position) {
            if (position < 0) {
                throw new IllegalArgumentException("Invalid header index: " + position);
            } else if (position == 0) {
                return null;
            } else {
                return this.b.g().a(position - 1);
            }
        }

        public String getHeaderField(int position) {
            if (position < 0) {
                throw new IllegalArgumentException("Invalid header index: " + position);
            } else if (position == 0) {
                return this.b.b();
            } else {
                return this.b.g().b(position - 1);
            }
        }

        public String getHeaderField(String fieldName) {
            return fieldName == null ? this.b.b() : this.b.g().a(fieldName);
        }

        public Map<String, List<String>> getHeaderFields() {
            return df.a(this.b.g(), this.b.b());
        }

        public int getResponseCode() throws IOException {
            return this.b.c();
        }

        public String getResponseMessage() throws IOException {
            return this.b.d();
        }

        public InputStream getErrorStream() {
            return null;
        }

        public boolean usingProxy() {
            return false;
        }

        public void setConnectTimeout(int timeout) {
            throw dj.f();
        }

        public int getConnectTimeout() {
            return 0;
        }

        public void setReadTimeout(int timeout) {
            throw dj.f();
        }

        public int getReadTimeout() {
            return 0;
        }

        public Object getContent() throws IOException {
            throw dj.i();
        }

        public Object getContent(Class[] classes) throws IOException {
            throw dj.i();
        }

        public InputStream getInputStream() throws IOException {
            throw dj.i();
        }

        public OutputStream getOutputStream() throws IOException {
            throw dj.f();
        }

        public void setDoInput(boolean doInput) {
            throw dj.f();
        }

        public boolean getDoInput() {
            return true;
        }

        public void setDoOutput(boolean doOutput) {
            throw dj.f();
        }

        public boolean getDoOutput() {
            return this.a.e() != null;
        }

        public void setAllowUserInteraction(boolean allowUserInteraction) {
            throw dj.f();
        }

        public boolean getAllowUserInteraction() {
            return false;
        }

        public void setUseCaches(boolean useCaches) {
            throw dj.f();
        }

        public boolean getUseCaches() {
            return super.getUseCaches();
        }

        public void setIfModifiedSince(long ifModifiedSince) {
            throw dj.f();
        }

        public long getIfModifiedSince() {
            return 0;
        }

        public boolean getDefaultUseCaches() {
            return super.getDefaultUseCaches();
        }

        public void setDefaultUseCaches(boolean defaultUseCaches) {
            super.setDefaultUseCaches(defaultUseCaches);
        }
    }

    private static final class b extends cu {
        private final a a;

        public b(a aVar) {
            super(aVar);
            this.a = aVar;
        }

        protected cf a() {
            return this.a.b.f();
        }

        public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            throw dj.f();
        }

        public HostnameVerifier getHostnameVerifier() {
            throw dj.h();
        }

        public void setSSLSocketFactory(SSLSocketFactory socketFactory) {
            throw dj.f();
        }

        public SSLSocketFactory getSSLSocketFactory() {
            throw dj.h();
        }
    }

    public dj(ResponseCache responseCache) {
        this.a = responseCache;
    }

    public di a(dg dgVar) throws IOException {
        CacheResponse c = c(dgVar);
        if (c == null) {
            return null;
        }
        com.chartboost.sdk.impl.di.b bVar = new com.chartboost.sdk.impl.di.b();
        bVar.a(dgVar);
        bVar.a(b(c));
        cw a = a(c);
        bVar.a(a);
        bVar.a(ck.CACHE);
        bVar.a(a(a, c.getBody()));
        if (c instanceof SecureCacheResponse) {
            List serverCertificateChain;
            SecureCacheResponse secureCacheResponse = (SecureCacheResponse) c;
            try {
                serverCertificateChain = secureCacheResponse.getServerCertificateChain();
            } catch (SSLPeerUnverifiedException e) {
                serverCertificateChain = Collections.emptyList();
            }
            List localCertificateChain = secureCacheResponse.getLocalCertificateChain();
            if (localCertificateChain == null) {
                localCertificateChain = Collections.emptyList();
            }
            bVar.a(cf.a(secureCacheResponse.getCipherSuite(), serverCertificateChain, localCertificateChain));
        }
        return bVar.a();
    }

    public CacheRequest a(di diVar) throws IOException {
        return this.a.put(diVar.a().b(), b(diVar));
    }

    public boolean b(dg dgVar) throws IOException {
        return false;
    }

    public void a(di diVar, di diVar2) throws IOException {
    }

    public void a() {
    }

    public void a(ck ckVar) {
    }

    private CacheResponse c(dg dgVar) throws IOException {
        return this.a.get(dgVar.b(), dgVar.c(), d(dgVar));
    }

    private static HttpURLConnection b(di diVar) {
        if (diVar.a().k()) {
            return new b(new a(diVar));
        }
        return new a(diVar);
    }

    private static cw a(CacheResponse cacheResponse) throws IOException {
        Map headers = cacheResponse.getHeaders();
        com.chartboost.sdk.impl.cw.a aVar = new com.chartboost.sdk.impl.cw.a();
        for (Entry entry : headers.entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                for (String a : (List) entry.getValue()) {
                    aVar.a(str, a);
                }
            }
        }
        return aVar.a();
    }

    private static String b(CacheResponse cacheResponse) throws IOException {
        List list = (List) cacheResponse.getHeaders().get(null);
        if (list == null || list.size() == 0) {
            return null;
        }
        return (String) list.get(0);
    }

    private static Map<String, List<String>> d(dg dgVar) {
        return df.a(dgVar.d(), null);
    }

    private static com.chartboost.sdk.impl.di.a a(final cw cwVar, final InputStream inputStream) {
        return new com.chartboost.sdk.impl.di.a() {
            public InputStream a() {
                return inputStream;
            }
        };
    }

    private static RuntimeException f() {
        throw new UnsupportedOperationException("ResponseCache cannot modify the request.");
    }

    private static RuntimeException g() {
        throw new UnsupportedOperationException("ResponseCache cannot access request headers");
    }

    private static RuntimeException h() {
        throw new UnsupportedOperationException("ResponseCache cannot access SSL internals");
    }

    private static RuntimeException i() {
        throw new UnsupportedOperationException("ResponseCache cannot access the response body.");
    }
}
