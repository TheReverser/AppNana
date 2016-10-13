package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

abstract class cu extends HttpsURLConnection {
    private final HttpURLConnection a;

    protected abstract cf a();

    public cu(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.a = httpURLConnection;
    }

    public String getCipherSuite() {
        cf a = a();
        return a != null ? a.a() : null;
    }

    public Certificate[] getLocalCertificates() {
        cf a = a();
        if (a == null) {
            return null;
        }
        List d = a.d();
        if (d.isEmpty()) {
            return null;
        }
        return (Certificate[]) d.toArray(new Certificate[d.size()]);
    }

    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        cf a = a();
        if (a == null) {
            return null;
        }
        List b = a.b();
        if (b.isEmpty()) {
            return null;
        }
        return (Certificate[]) b.toArray(new Certificate[b.size()]);
    }

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        cf a = a();
        return a != null ? a.c() : null;
    }

    public Principal getLocalPrincipal() {
        cf a = a();
        return a != null ? a.e() : null;
    }

    public void connect() throws IOException {
        this.connected = true;
        this.a.connect();
    }

    public void disconnect() {
        this.a.disconnect();
    }

    public InputStream getErrorStream() {
        return this.a.getErrorStream();
    }

    public String getRequestMethod() {
        return this.a.getRequestMethod();
    }

    public int getResponseCode() throws IOException {
        return this.a.getResponseCode();
    }

    public String getResponseMessage() throws IOException {
        return this.a.getResponseMessage();
    }

    public void setRequestMethod(String method) throws ProtocolException {
        this.a.setRequestMethod(method);
    }

    public boolean usingProxy() {
        return this.a.usingProxy();
    }

    public boolean getInstanceFollowRedirects() {
        return this.a.getInstanceFollowRedirects();
    }

    public void setInstanceFollowRedirects(boolean followRedirects) {
        this.a.setInstanceFollowRedirects(followRedirects);
    }

    public boolean getAllowUserInteraction() {
        return this.a.getAllowUserInteraction();
    }

    public Object getContent() throws IOException {
        return this.a.getContent();
    }

    public Object getContent(Class[] types) throws IOException {
        return this.a.getContent(types);
    }

    public String getContentEncoding() {
        return this.a.getContentEncoding();
    }

    public int getContentLength() {
        return this.a.getContentLength();
    }

    public String getContentType() {
        return this.a.getContentType();
    }

    public long getDate() {
        return this.a.getDate();
    }

    public boolean getDefaultUseCaches() {
        return this.a.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.a.getDoInput();
    }

    public boolean getDoOutput() {
        return this.a.getDoOutput();
    }

    public long getExpiration() {
        return this.a.getExpiration();
    }

    public String getHeaderField(int pos) {
        return this.a.getHeaderField(pos);
    }

    public Map<String, List<String>> getHeaderFields() {
        return this.a.getHeaderFields();
    }

    public Map<String, List<String>> getRequestProperties() {
        return this.a.getRequestProperties();
    }

    public void addRequestProperty(String field, String newValue) {
        this.a.addRequestProperty(field, newValue);
    }

    public String getHeaderField(String key) {
        return this.a.getHeaderField(key);
    }

    public long getHeaderFieldDate(String field, long defaultValue) {
        return this.a.getHeaderFieldDate(field, defaultValue);
    }

    public int getHeaderFieldInt(String field, int defaultValue) {
        return this.a.getHeaderFieldInt(field, defaultValue);
    }

    public String getHeaderFieldKey(int position) {
        return this.a.getHeaderFieldKey(position);
    }

    public long getIfModifiedSince() {
        return this.a.getIfModifiedSince();
    }

    public InputStream getInputStream() throws IOException {
        return this.a.getInputStream();
    }

    public long getLastModified() {
        return this.a.getLastModified();
    }

    public OutputStream getOutputStream() throws IOException {
        return this.a.getOutputStream();
    }

    public Permission getPermission() throws IOException {
        return this.a.getPermission();
    }

    public String getRequestProperty(String field) {
        return this.a.getRequestProperty(field);
    }

    public URL getURL() {
        return this.a.getURL();
    }

    public boolean getUseCaches() {
        return this.a.getUseCaches();
    }

    public void setAllowUserInteraction(boolean newValue) {
        this.a.setAllowUserInteraction(newValue);
    }

    public void setDefaultUseCaches(boolean newValue) {
        this.a.setDefaultUseCaches(newValue);
    }

    public void setDoInput(boolean newValue) {
        this.a.setDoInput(newValue);
    }

    public void setDoOutput(boolean newValue) {
        this.a.setDoOutput(newValue);
    }

    public void setIfModifiedSince(long newValue) {
        this.a.setIfModifiedSince(newValue);
    }

    public void setRequestProperty(String field, String newValue) {
        this.a.setRequestProperty(field, newValue);
    }

    public void setUseCaches(boolean newValue) {
        this.a.setUseCaches(newValue);
    }

    public void setConnectTimeout(int timeoutMillis) {
        this.a.setConnectTimeout(timeoutMillis);
    }

    public int getConnectTimeout() {
        return this.a.getConnectTimeout();
    }

    public void setReadTimeout(int timeoutMillis) {
        this.a.setReadTimeout(timeoutMillis);
    }

    public int getReadTimeout() {
        return this.a.getReadTimeout();
    }

    public String toString() {
        return this.a.toString();
    }

    public void setFixedLengthStreamingMode(int contentLength) {
        this.a.setFixedLengthStreamingMode(contentLength);
    }

    public void setChunkedStreamingMode(int chunkLength) {
        this.a.setChunkedStreamingMode(chunkLength);
    }
}
