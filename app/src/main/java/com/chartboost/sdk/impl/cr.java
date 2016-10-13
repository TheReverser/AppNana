package com.chartboost.sdk.impl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

public class cr {
    private static final cr a = c();

    private static class a extends cr {
        protected final Class<?> a;
        private final Method b;
        private final Method c;
        private final Method d;
        private final Method e;
        private final Method f;
        private final Method g;

        private a(Class<?> cls, Method method, Method method2, Method method3, Method method4, Method method5, Method method6) {
            this.a = cls;
            this.b = method;
            this.c = method2;
            this.d = method3;
            this.e = method4;
            this.f = method5;
            this.g = method6;
        }

        public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (Throwable e) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e);
                throw iOException;
            }
        }

        public void a(SSLSocket sSLSocket, String str) {
            super.a(sSLSocket, str);
            if (this.a.isInstance(sSLSocket)) {
                try {
                    this.b.invoke(sSLSocket, new Object[]{Boolean.valueOf(true)});
                    this.c.invoke(sSLSocket, new Object[]{str});
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e2) {
                    throw new AssertionError(e2);
                }
            }
        }

        public void a(SSLSocket sSLSocket, List<cj> list) {
            if (this.d != null && this.a.isInstance(sSLSocket)) {
                try {
                    Object[] objArr = new Object[]{cr.a((List) list)};
                    if (this.f != null) {
                        this.f.invoke(sSLSocket, objArr);
                    }
                    this.d.invoke(sSLSocket, objArr);
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                } catch (Throwable e2) {
                    throw new RuntimeException(e2);
                }
            }
        }

        public ds b(SSLSocket sSLSocket) {
            if (this.e == null) {
                return null;
            }
            if (!this.a.isInstance(sSLSocket)) {
                return null;
            }
            try {
                byte[] bArr;
                if (this.g != null) {
                    bArr = (byte[]) this.g.invoke(sSLSocket, new Object[0]);
                    if (bArr != null) {
                        return ds.a(bArr);
                    }
                }
                bArr = (byte[]) this.e.invoke(sSLSocket, new Object[0]);
                if (bArr == null) {
                    return null;
                }
                return ds.a(bArr);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    private static class b extends cr {
        private final Method a;
        private final Method b;
        private final Class<?> c;
        private final Class<?> d;

        public b(Method method, Method method2, Class<?> cls, Class<?> cls2) {
            this.b = method;
            this.a = method2;
            this.c = cls;
            this.d = cls2;
        }

        public void a(SSLSocket sSLSocket, List<cj> list) {
            try {
                List arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    arrayList.add(((cj) list.get(i)).d.a());
                }
                Object newProxyInstance = Proxy.newProxyInstance(cr.class.getClassLoader(), new Class[]{this.c, this.d}, new c(arrayList));
                this.b.invoke(null, new Object[]{sSLSocket, newProxyInstance});
            } catch (InvocationTargetException e) {
                throw new AssertionError(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public ds b(SSLSocket sSLSocket) {
            try {
                c cVar = (c) Proxy.getInvocationHandler(this.a.invoke(null, new Object[]{sSLSocket}));
                if (cVar.b || cVar.c != null) {
                    return cVar.b ? null : ds.a(cVar.c);
                }
                Logger.getLogger("com.squareup.okhttp.OkHttpClient").log(Level.INFO, "NPN callback dropped so SPDY is disabled. Is npn-boot on the boot class path?");
                return null;
            } catch (InvocationTargetException e) {
                throw new AssertionError();
            } catch (IllegalAccessException e2) {
                throw new AssertionError();
            }
        }
    }

    private static class c implements InvocationHandler {
        private final List<String> a;
        private boolean b;
        private String c;

        public c(List<String> list) {
            this.a = list;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String name = method.getName();
            Class returnType = method.getReturnType();
            if (args == null) {
                args = cs.b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.valueOf(true);
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.b = true;
                return null;
            } else if (name.equals("protocols") && args.length == 0) {
                return this.a;
            } else {
                if (name.equals("selectProtocol") && String.class == returnType && args.length == 1 && (args[0] == null || (args[0] instanceof List))) {
                    List list = (List) args[0];
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.a.contains(list.get(i))) {
                            name = (String) list.get(i);
                            this.c = name;
                            return name;
                        }
                    }
                    name = (String) this.a.get(0);
                    this.c = name;
                    return name;
                } else if (!name.equals("protocolSelected") || args.length != 1) {
                    return method.invoke(this, args);
                } else {
                    this.c = (String) args[0];
                    return null;
                }
            }
        }
    }

    public static cr a() {
        return a;
    }

    public String b() {
        return "OkHttp";
    }

    public void a(String str) {
        System.out.println(str);
    }

    public void a(Socket socket) throws SocketException {
    }

    public void b(Socket socket) throws SocketException {
    }

    public URI a(URL url) throws URISyntaxException {
        return url.toURI();
    }

    public void a(SSLSocket sSLSocket, String str) {
    }

    public void a(SSLSocket sSLSocket) {
        sSLSocket.setEnabledProtocols(new String[]{"SSLv3"});
    }

    public ds b(SSLSocket sSLSocket) {
        return null;
    }

    public void a(SSLSocket sSLSocket, List<cj> list) {
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.chartboost.sdk.impl.cr c() {
        /*
        r0 = 0;
        r1 = "com.android.org.conscrypt.OpenSSLSocketImpl";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x005d, NoSuchMethodException -> 0x006b }
    L_0x0007:
        r2 = "setUseSessionTickets";
        r3 = 1;
        r3 = new java.lang.Class[r3];	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        r4 = 0;
        r5 = java.lang.Boolean.TYPE;	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        r3[r4] = r5;	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        r2 = r1.getMethod(r2, r3);	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        r3 = "setHostname";
        r4 = 1;
        r4 = new java.lang.Class[r4];	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        r5 = 0;
        r6 = java.lang.String.class;
        r4[r5] = r6;	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        r3 = r1.getMethod(r3, r4);	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        r4 = "setNpnProtocols";
        r5 = 1;
        r5 = new java.lang.Class[r5];	 Catch:{ NoSuchMethodException -> 0x0065, ClassNotFoundException -> 0x00e6 }
        r6 = 0;
        r7 = byte[].class;
        r5[r6] = r7;	 Catch:{ NoSuchMethodException -> 0x0065, ClassNotFoundException -> 0x00e6 }
        r5 = r1.getMethod(r4, r5);	 Catch:{ NoSuchMethodException -> 0x0065, ClassNotFoundException -> 0x00e6 }
        r4 = "getNpnSelectedProtocol";
        r6 = 0;
        r6 = new java.lang.Class[r6];	 Catch:{ NoSuchMethodException -> 0x00e8, ClassNotFoundException -> 0x00e6 }
        r6 = r1.getMethod(r4, r6);	 Catch:{ NoSuchMethodException -> 0x00e8, ClassNotFoundException -> 0x00e6 }
        r4 = "setAlpnProtocols";
        r7 = 1;
        r7 = new java.lang.Class[r7];	 Catch:{ NoSuchMethodException -> 0x00ec, ClassNotFoundException -> 0x00e6 }
        r8 = 0;
        r9 = byte[].class;
        r7[r8] = r9;	 Catch:{ NoSuchMethodException -> 0x00ec, ClassNotFoundException -> 0x00e6 }
        r4 = r1.getMethod(r4, r7);	 Catch:{ NoSuchMethodException -> 0x00ec, ClassNotFoundException -> 0x00e6 }
        r7 = "getAlpnSelectedProtocol";
        r8 = 0;
        r8 = new java.lang.Class[r8];	 Catch:{ NoSuchMethodException -> 0x00f0, ClassNotFoundException -> 0x00e6 }
        r0 = r1.getMethod(r7, r8);	 Catch:{ NoSuchMethodException -> 0x00f0, ClassNotFoundException -> 0x00e6 }
    L_0x0051:
        r7 = r0;
        r10 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r10;
    L_0x0056:
        r0 = new com.chartboost.sdk.impl.cr$a;	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        r8 = 0;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
    L_0x005c:
        return r0;
    L_0x005d:
        r1 = move-exception;
        r1 = "org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00e6, NoSuchMethodException -> 0x006b }
        goto L_0x0007;
    L_0x0065:
        r4 = move-exception;
        r4 = r0;
    L_0x0067:
        r7 = r0;
        r6 = r0;
        r5 = r0;
        goto L_0x0056;
    L_0x006b:
        r0 = move-exception;
    L_0x006c:
        r0 = "org.eclipse.jetty.npn.NextProtoNego";
        r1 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r2.<init>();	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r2 = r2.append(r0);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r3 = "$Provider";
        r2 = r2.append(r3);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r2 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r3 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r3.<init>();	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r3 = r3.append(r0);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r4 = "$ClientProvider";
        r3 = r3.append(r4);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r3 = r3.toString();	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r3 = java.lang.Class.forName(r3);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r4 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r4.<init>();	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r0 = r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r4 = "$ServerProvider";
        r0 = r0.append(r4);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r0 = r0.toString();	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r4 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r0 = "put";
        r5 = 2;
        r5 = new java.lang.Class[r5];	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r6 = 0;
        r7 = javax.net.ssl.SSLSocket.class;
        r5[r6] = r7;	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r6 = 1;
        r5[r6] = r2;	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r2 = r1.getMethod(r0, r5);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r0 = "get";
        r5 = 1;
        r5 = new java.lang.Class[r5];	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r6 = 0;
        r7 = javax.net.ssl.SSLSocket.class;
        r5[r6] = r7;	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r1 = r1.getMethod(r0, r5);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r0 = new com.chartboost.sdk.impl.cr$b;	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        r0.<init>(r2, r1, r3, r4);	 Catch:{ ClassNotFoundException -> 0x00dc, NoSuchMethodException -> 0x00e4 }
        goto L_0x005c;
    L_0x00dc:
        r0 = move-exception;
    L_0x00dd:
        r0 = new com.chartboost.sdk.impl.cr;
        r0.<init>();
        goto L_0x005c;
    L_0x00e4:
        r0 = move-exception;
        goto L_0x00dd;
    L_0x00e6:
        r0 = move-exception;
        goto L_0x006c;
    L_0x00e8:
        r4 = move-exception;
        r4 = r5;
        goto L_0x0067;
    L_0x00ec:
        r4 = move-exception;
        r4 = r0;
        goto L_0x0051;
    L_0x00f0:
        r7 = move-exception;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.cr.c():com.chartboost.sdk.impl.cr");
    }

    static byte[] a(List<cj> list) {
        int i = 0;
        for (cj cjVar : list) {
            i = (cjVar.d.e() + 1) + i;
        }
        Object obj = new byte[i];
        i = 0;
        for (cj cjVar2 : list) {
            int e = cjVar2.d.e();
            int i2 = i + 1;
            obj[i] = (byte) e;
            System.arraycopy(cjVar2.d.f(), 0, obj, i2, e);
            i = i2 + e;
        }
        return obj;
    }
}
