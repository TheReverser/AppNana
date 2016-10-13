package com.chartboost.sdk.impl;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public final class db {
    public static final Set<String> a = new LinkedHashSet(Arrays.asList(new String[]{"OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "PATCH"}));

    public static boolean a(String str) {
        return str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("DELETE");
    }
}
