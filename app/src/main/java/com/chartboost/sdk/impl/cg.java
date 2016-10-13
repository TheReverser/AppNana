package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

public interface cg {

    public static final class a {
        private final String a;
        private final String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public boolean equals(Object o) {
            return (o instanceof a) && ((a) o).a.equals(this.a) && ((a) o).b.equals(this.b);
        }

        public int hashCode() {
            return this.a.hashCode() + (this.b.hashCode() * 31);
        }

        public String toString() {
            return this.a + " realm=\"" + this.b + "\"";
        }
    }

    public static final class b {
        private final String a;

        private b(String str) {
            this.a = str;
        }

        public static b a(String str, String str2) {
            try {
                return new b("Basic " + ds.a((str + ":" + str2).getBytes("ISO-8859-1")).b());
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError();
            }
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object o) {
            return (o instanceof b) && ((b) o).a.equals(this.a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return this.a;
        }
    }

    b a(Proxy proxy, URL url, List<a> list) throws IOException;

    b b(Proxy proxy, URL url, List<a> list) throws IOException;
}
