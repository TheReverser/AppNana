package com.chartboost.sdk.Libraries;

import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class k {
    public static boolean a = false;
    public static boolean b = true;

    private static class a {
        private String a;

        public enum a {
            check_su_binary(new String[]{"/system/xbin/which", "su"});
            
            String[] b;

            private a(String[] strArr) {
                this.b = strArr;
            }
        }

        private a() {
            this.a = a.class.getName();
        }

        public ArrayList<String> a(a aVar) {
            ArrayList<String> arrayList = new ArrayList();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(aVar.b).getInputStream()));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        CBLogging.a(this.a, "--> Line received: " + readLine);
                        arrayList.add(readLine);
                    } catch (Throwable e) {
                        CBLogging.b("Root", "error reading root status", e);
                    }
                }
                CBLogging.a(this.a, "--> Full response was: " + arrayList);
                return arrayList;
            } catch (Exception e2) {
                return null;
            }
        }
    }

    public static boolean a() {
        boolean z;
        if (b() || c() || d()) {
            z = true;
        } else {
            z = false;
        }
        a = z;
        b = false;
        return a;
    }

    private static boolean b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean c() {
        try {
            return new File("/system/app/Superuser.apk").exists();
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean d() {
        return new a().a(a.check_su_binary) != null;
    }
}
