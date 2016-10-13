package com.appnana.android.utils;

import com.facebook.BuildConfig;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTemplate {
    private static final Pattern keyPattern = Pattern.compile("\\$\\{([a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*)\\}");
    private boolean blankNull = false;
    private final Matcher m;
    private final String template;

    public StringTemplate(String template) {
        this.template = template;
        this.m = keyPattern.matcher(template);
    }

    public String substitute(Map<String, String> map) {
        this.m.reset();
        StringBuffer sb = new StringBuffer();
        while (this.m.find()) {
            String k0 = this.m.group();
            Object vobj = map.get(this.m.group(1));
            String v = vobj == null ? this.blankNull ? BuildConfig.VERSION_NAME : k0 : vobj.toString();
            this.m.appendReplacement(sb, Matcher.quoteReplacement(v));
        }
        this.m.appendTail(sb);
        return sb.toString();
    }

    public StringTemplate setBlankNull() {
        this.blankNull = true;
        return this;
    }

    public String getTemplate() {
        return this.template;
    }
}
