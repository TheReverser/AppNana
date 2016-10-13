package com.chartboost.sdk.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface cp {
    public static final cp a = new cp() {
        public InetAddress[] a(String str) throws UnknownHostException {
            if (str != null) {
                return InetAddress.getAllByName(str);
            }
            throw new UnknownHostException("host == null");
        }
    };

    InetAddress[] a(String str) throws UnknownHostException;
}
