package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.dg.b;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketPermission;
import java.net.URL;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class dd extends HttpURLConnection {
    final ch a;
    protected IOException b;
    protected da c;
    cf d;
    private com.chartboost.sdk.impl.cw.a e = new com.chartboost.sdk.impl.cw.a();
    private long f = -1;
    private int g;
    private cl h;

    enum a {
        NONE,
        SAME_CONNECTION,
        DIFFERENT_CONNECTION
    }

    public dd(URL url, ch chVar) {
        super(url);
        this.a = chVar;
    }

    public final void connect() throws IOException {
        a();
        do {
        } while (!a(false));
    }

    public final void disconnect() {
        if (this.c != null) {
            this.c.n();
        }
    }

    public final InputStream getErrorStream() {
        InputStream inputStream = null;
        try {
            da b = b();
            if (b.o() && b.h().c() >= 400) {
                inputStream = b.j();
            }
        } catch (IOException e) {
        }
        return inputStream;
    }

    public final String getHeaderField(int position) {
        try {
            return b().h().g().b(position);
        } catch (IOException e) {
            return null;
        }
    }

    public final String getHeaderField(String fieldName) {
        try {
            di h = b().h();
            return fieldName == null ? h.b() : h.g().a(fieldName);
        } catch (IOException e) {
            return null;
        }
    }

    public final String getHeaderFieldKey(int position) {
        try {
            return b().h().g().a(position);
        } catch (IOException e) {
            return null;
        }
    }

    public final Map<String, List<String>> getHeaderFields() {
        try {
            di h = b().h();
            return df.a(h.g(), h.b());
        } catch (IOException e) {
            return Collections.emptyMap();
        }
    }

    public final Map<String, List<String>> getRequestProperties() {
        if (!this.connected) {
            return df.a(this.e.a(), null);
        }
        throw new IllegalStateException("Cannot access request header fields after connection is set");
    }

    public final InputStream getInputStream() throws IOException {
        if (this.doInput) {
            da b = b();
            if (getResponseCode() >= 400) {
                throw new FileNotFoundException(this.url.toString());
            }
            InputStream j = b.j();
            if (j != null) {
                return j;
            }
            throw new ProtocolException("No response body exists; responseCode=" + getResponseCode());
        }
        throw new ProtocolException("This protocol does not support input");
    }

    public final OutputStream getOutputStream() throws IOException {
        connect();
        dq e = this.c.e();
        if (e == null) {
            throw new ProtocolException("method does not support a request body: " + this.method);
        } else if (!this.c.f()) {
            return e.d();
        } else {
            throw new ProtocolException("cannot write request body after response has been read");
        }
    }

    public final Permission getPermission() throws IOException {
        String host = getURL().getHost();
        int a = cs.a(getURL());
        if (usingProxy()) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) this.a.c().address();
            host = inetSocketAddress.getHostName();
            a = inetSocketAddress.getPort();
        }
        return new SocketPermission(host + ":" + a, "connect, resolve");
    }

    public final String getRequestProperty(String field) {
        if (field == null) {
            return null;
        }
        return this.e.c(field);
    }

    public void setConnectTimeout(int timeoutMillis) {
        this.a.a((long) timeoutMillis, TimeUnit.MILLISECONDS);
    }

    public int getConnectTimeout() {
        return this.a.a();
    }

    public void setReadTimeout(int timeoutMillis) {
        this.a.b((long) timeoutMillis, TimeUnit.MILLISECONDS);
    }

    public int getReadTimeout() {
        return this.a.b();
    }

    private void a() throws IOException {
        if (this.b != null) {
            throw this.b;
        } else if (this.c == null) {
            this.connected = true;
            try {
                if (this.doOutput) {
                    if (this.method.equals("GET")) {
                        this.method = "POST";
                    } else if (!db.a(this.method)) {
                        throw new ProtocolException(this.method + " does not support writing");
                    }
                }
                this.c = a(this.method, null, null);
            } catch (IOException e) {
                this.b = e;
                throw e;
            }
        }
    }

    private da a(String str, cd cdVar, dk dkVar) {
        boolean z;
        b a = new b().a(getURL()).a(str, null);
        cw a2 = this.e.a();
        for (int i = 0; i < a2.a(); i++) {
            a.b(a2.a(i), a2.b(i));
        }
        if (!db.a(str)) {
            z = false;
        } else if (this.f != -1) {
            a.a("Content-Length", Long.toString(this.f));
            z = false;
        } else if (this.chunkLength > 0) {
            a.a("Transfer-Encoding", "chunked");
            z = false;
        } else {
            z = true;
        }
        dg a3 = a.a();
        ch chVar = this.a;
        if (!(chVar.f() == null || getUseCaches())) {
            chVar = this.a.o().a(null);
        }
        return new da(chVar, a3, z, cdVar, null, dkVar);
    }

    private da b() throws IOException {
        a();
        if (this.c.f()) {
            return this.c;
        }
        while (true) {
            if (a(true)) {
                a c = c();
                if (c == a.NONE) {
                    this.c.m();
                    return this.c;
                }
                String str = this.method;
                ed d = this.c.d();
                int c2 = this.c.h().c();
                if (c2 == 300 || c2 == 301 || c2 == 302 || c2 == 303) {
                    str = "GET";
                    this.e.b("Content-Length");
                    d = null;
                }
                if (d == null || (d instanceof dk)) {
                    if (c == a.DIFFERENT_CONNECTION) {
                        this.c.m();
                    }
                    this.c = a(str, this.c.n(), (dk) d);
                } else {
                    throw new HttpRetryException("Cannot retry streamed HTTP body", c2);
                }
            }
        }
    }

    private boolean a(boolean z) throws IOException {
        try {
            this.c.a();
            this.h = this.c.l();
            this.d = this.c.k() != null ? this.c.k().i() : null;
            if (z) {
                this.c.q();
            }
            return true;
        } catch (IOException e) {
            da a = this.c.a(e);
            if (a != null) {
                this.c = a;
                return false;
            }
            this.b = e;
            throw e;
        }
    }

    private a c() throws IOException {
        cd k = this.c.k();
        Proxy b = k != null ? k.b().b() : this.a.c();
        int responseCode = getResponseCode();
        switch (responseCode) {
            case 300:
            case 301:
            case 302:
            case 303:
            case 307:
                if (!getInstanceFollowRedirects()) {
                    return a.NONE;
                }
                int i = this.g + 1;
                this.g = i;
                if (i > 20) {
                    throw new ProtocolException("Too many redirects: " + this.g);
                } else if (responseCode == 307 && !this.method.equals("GET") && !this.method.equals("HEAD")) {
                    return a.NONE;
                } else {
                    String headerField = getHeaderField("Location");
                    if (headerField == null) {
                        return a.NONE;
                    }
                    URL url = this.url;
                    this.url = new URL(url, headerField);
                    if (!this.url.getProtocol().equals("https") && !this.url.getProtocol().equals("http")) {
                        return a.NONE;
                    }
                    boolean equals = url.getProtocol().equals(this.url.getProtocol());
                    if (!equals && !this.a.k()) {
                        return a.NONE;
                    }
                    boolean equals2 = url.getHost().equals(this.url.getHost());
                    Object obj = cs.a(url) == cs.a(this.url) ? 1 : null;
                    if (equals2 && obj != null && equals) {
                        return a.SAME_CONNECTION;
                    }
                    return a.DIFFERENT_CONNECTION;
                }
            case 401:
                break;
            case 407:
                if (b.type() != Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                break;
            default:
                return a.NONE;
        }
        dg a = cx.a(this.a.i(), this.c.h(), b);
        if (a == null) {
            return a.NONE;
        }
        this.e = a.g().b();
        return a.SAME_CONNECTION;
    }

    public final boolean usingProxy() {
        Proxy b = this.h != null ? this.h.b() : this.a.c();
        return (b == null || b.type() == Type.DIRECT) ? false : true;
    }

    public String getResponseMessage() throws IOException {
        return b().h().d();
    }

    public final int getResponseCode() throws IOException {
        return b().h().c();
    }

    public final void setRequestProperty(String field, String newValue) {
        if (this.connected) {
            throw new IllegalStateException("Cannot set request property after connection is made");
        } else if (field == null) {
            throw new NullPointerException("field == null");
        } else if (newValue == null) {
            cr.a().a("Ignoring header " + field + " because its value was null.");
        } else if ("X-Android-Transports".equals(field) || "X-Android-Protocols".equals(field)) {
            a(newValue, false);
        } else {
            this.e.b(field, newValue);
        }
    }

    public void setIfModifiedSince(long newValue) {
        super.setIfModifiedSince(newValue);
        if (this.ifModifiedSince != 0) {
            this.e.b("If-Modified-Since", cz.a(new Date(this.ifModifiedSince)));
        } else {
            this.e.b("If-Modified-Since");
        }
    }

    public final void addRequestProperty(String field, String value) {
        if (this.connected) {
            throw new IllegalStateException("Cannot add request property after connection is made");
        } else if (field == null) {
            throw new NullPointerException("field == null");
        } else if (value == null) {
            cr.a().a("Ignoring header " + field + " because its value was null.");
        } else if ("X-Android-Transports".equals(field) || "X-Android-Protocols".equals(field)) {
            a(value, true);
        } else {
            this.e.a(field, value);
        }
    }

    private void a(String str, boolean z) {
        List arrayList = new ArrayList();
        if (z) {
            arrayList.addAll(this.a.m());
        }
        String[] split = str.split(",", -1);
        int length = split.length;
        int i = 0;
        while (i < length) {
            try {
                arrayList.add(cs.a(ds.a(split[i])));
                i++;
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
        this.a.a(arrayList);
    }

    public void setRequestMethod(String method) throws ProtocolException {
        if (db.a.contains(method)) {
            this.method = method;
            return;
        }
        throw new ProtocolException("Expected one of " + db.a + " but was " + method);
    }

    public void setFixedLengthStreamingMode(int contentLength) {
        a((long) contentLength);
    }

    public void a(long j) {
        if (this.connected) {
            throw new IllegalStateException("Already connected");
        } else if (this.chunkLength > 0) {
            throw new IllegalStateException("Already in chunked mode");
        } else if (j < 0) {
            throw new IllegalArgumentException("contentLength < 0");
        } else {
            this.f = j;
            this.fixedContentLength = (int) Math.min(j, 2147483647L);
        }
    }
}
