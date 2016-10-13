package com.vungle.publisher.protocol.message;

import com.vungle.publisher.AdConfig;
import com.vungle.publisher.Demographic.Gender;
import com.vungle.publisher.bf;
import com.vungle.publisher.bh;
import com.vungle.publisher.bu;
import com.vungle.publisher.bz;
import com.vungle.publisher.ca;
import com.vungle.publisher.cb;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class RequestAd<Q extends RequestAd<Q>> extends BaseJsonSerializable {
    protected String a;
    protected String b;
    protected Demographic c;
    protected DeviceInfo d;
    protected Boolean e;
    protected String f;

    /* compiled from: vungle */
    public static class Demographic extends BaseJsonSerializable {
        protected Integer a;
        protected Gender b;
        protected Location c;

        @Singleton
        /* compiled from: vungle */
        public static class Factory extends MessageFactory<Demographic> {
            @Inject
            protected com.vungle.publisher.Demographic a;
            @Inject
            protected Factory b;

            protected final /* synthetic */ java.lang.Object a() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r1 = this;
                r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic;
                r0.<init>();
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory.a():java.lang.Object");
            }

            protected final /* bridge */ /* synthetic */ java.lang.Object[] a(int r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r1 = this;
                r0 = new com.vungle.publisher.protocol.message.RequestAd.Demographic[r2];
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory.a(int):java.lang.Object[]");
            }

            protected Factory() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r0 = this;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory.<init>():void");
            }

            protected final com.vungle.publisher.protocol.message.RequestAd.Demographic b() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r3 = this;
                r0 = r3.a;
                r1 = new com.vungle.publisher.protocol.message.RequestAd$Demographic;
                r1.<init>();
                r2 = r0.getAge();
                r1.a = r2;
                r0 = r0.getGender();
                r1.b = r0;
                r0 = r3.b;
                r0 = r0.b();
                r1.c = r0;
                return r1;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory.b():com.vungle.publisher.protocol.message.RequestAd$Demographic");
            }
        }

        /* compiled from: vungle */
        public static class Location extends BaseJsonSerializable {
            protected Float a;
            protected Double b;
            protected Double c;
            protected Float d;
            protected Long e;

            @Singleton
            /* compiled from: vungle */
            public static class Factory extends MessageFactory<Location> {
                @Inject
                bz a;

                protected final /* synthetic */ java.lang.Object a() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                    /*
                    r1 = this;
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Location;
                    r0.<init>();
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory.a():java.lang.Object");
                }

                protected final /* bridge */ /* synthetic */ java.lang.Object[] a(int r2) {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                    /*
                    r1 = this;
                    r0 = new com.vungle.publisher.protocol.message.RequestAd.Demographic.Location[r2];
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory.a(int):java.lang.Object[]");
                }

                protected Factory() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                    /*
                    r0 = this;
                    r0.<init>();
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory.<init>():void");
                }

                protected final com.vungle.publisher.protocol.message.RequestAd.Demographic.Location b() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                    /*
                    r4 = this;
                    r0 = 0;
                    r1 = r4.a;
                    r1 = r1.b();
                    if (r1 != 0) goto L_0x0011;
                L_0x0009:
                    r1 = "VungleProtocol";
                    r2 = "detailed location not available";
                    com.vungle.log.Logger.d(r1, r2);
                L_0x0010:
                    return r0;
                L_0x0011:
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Location;
                    r0.<init>();
                    r2 = r1.getAccuracy();
                    r2 = java.lang.Float.valueOf(r2);
                    r0.a = r2;
                    r2 = r1.getLatitude();
                    r2 = java.lang.Double.valueOf(r2);
                    r0.b = r2;
                    r2 = r1.getLongitude();
                    r2 = java.lang.Double.valueOf(r2);
                    r0.c = r2;
                    r2 = r1.getSpeed();
                    r2 = java.lang.Float.valueOf(r2);
                    r0.d = r2;
                    r2 = r1.getTime();
                    r1 = java.lang.Long.valueOf(r2);
                    r0.e = r1;
                    goto L_0x0010;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory.b():com.vungle.publisher.protocol.message.RequestAd$Demographic$Location");
                }
            }

            protected Location() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r0 = this;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.<init>():void");
            }

            public final org.json.JSONObject b() throws org.json.JSONException {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r3 = this;
                r0 = super.b();
                r1 = "accuracyMeters";
                r2 = r3.a;
                r0.putOpt(r1, r2);
                r1 = "lat";
                r2 = r3.b;
                r0.putOpt(r1, r2);
                r1 = "long";
                r2 = r3.c;
                r0.putOpt(r1, r2);
                r1 = "speedMetersPerSecond";
                r2 = r3.d;
                r0.putOpt(r1, r2);
                r1 = "timestampMillis";
                r2 = r3.e;
                r0.putOpt(r1, r2);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.b():org.json.JSONObject");
            }
        }

        protected Demographic() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
            /*
            r0 = this;
            r0.<init>();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.<init>():void");
        }

        public final org.json.JSONObject b() throws org.json.JSONException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
            /*
            r3 = this;
            r0 = super.b();
            r1 = "age";
            r2 = r3.a;
            r0.putOpt(r1, r2);
            r1 = "gender";
            r2 = r3.b;
            r0.putOpt(r1, r2);
            r1 = "location";
            r2 = r3.c;
            r2 = com.vungle.publisher.bu.a(r2);
            r0.putOpt(r1, r2);
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.b():org.json.JSONObject");
        }
    }

    /* compiled from: vungle */
    public static class DeviceInfo extends BaseJsonSerializable {
        protected ca a;
        protected DisplayDimension b;
        protected Boolean c;
        protected Boolean d;
        protected String e;
        protected String f;
        protected String g;
        protected String h;
        protected a i;
        protected Float j;
        protected String k;

        /* compiled from: vungle */
        public static class DisplayDimension extends BaseJsonSerializable {
            protected Integer a;
            protected Integer b;

            @Singleton
            /* compiled from: vungle */
            public static class Factory extends MessageFactory<DisplayDimension> {
                @Inject
                protected bf a;

                protected final /* synthetic */ java.lang.Object a() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                    /*
                    r1 = this;
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension;
                    r0.<init>();
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory.a():java.lang.Object");
                }

                protected final /* bridge */ /* synthetic */ java.lang.Object[] a(int r2) {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                    /*
                    r1 = this;
                    r0 = new com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension[r2];
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory.a(int):java.lang.Object[]");
                }

                protected Factory() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                    /*
                    r0 = this;
                    r0.<init>();
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory.<init>():void");
                }

                protected final com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension b() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                    /*
                    r3 = this;
                    r0 = 0;
                    r1 = r3.a;
                    r1 = r1.h();
                    r2 = r1.heightPixels;
                    if (r2 > 0) goto L_0x000f;
                L_0x000b:
                    r2 = r1.widthPixels;
                    if (r2 <= 0) goto L_0x0024;
                L_0x000f:
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension;
                    r0.<init>();
                    r2 = r1.heightPixels;
                    r2 = java.lang.Integer.valueOf(r2);
                    r0.a = r2;
                    r1 = r1.widthPixels;
                    r1 = java.lang.Integer.valueOf(r1);
                    r0.b = r1;
                L_0x0024:
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory.b():com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension");
                }
            }

            protected DisplayDimension() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r0 = this;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.<init>():void");
            }

            public final org.json.JSONObject b() throws org.json.JSONException {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r3 = this;
                r0 = super.b();
                r1 = "height";
                r2 = r3.a;
                r0.putOpt(r1, r2);
                r1 = "width";
                r2 = r3.b;
                r0.putOpt(r1, r2);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.b():org.json.JSONObject");
            }
        }

        @Singleton
        /* compiled from: vungle */
        public static class Factory extends MessageFactory<DeviceInfo> {
            @Inject
            protected AdConfig a;
            @Inject
            protected bf b;
            @Inject
            protected Factory c;
            @Inject
            protected cb d;
            @Inject
            protected bh e;

            protected final /* synthetic */ java.lang.Object a() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r1 = this;
                r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo;
                r0.<init>();
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory.a():java.lang.Object");
            }

            protected final /* bridge */ /* synthetic */ java.lang.Object[] a(int r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r1 = this;
                r0 = new com.vungle.publisher.protocol.message.RequestAd.DeviceInfo[r2];
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory.a(int):java.lang.Object[]");
            }

            protected Factory() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r0 = this;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory.<init>():void");
            }

            protected final com.vungle.publisher.protocol.message.RequestAd.DeviceInfo b() {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r2 = this;
                r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo;
                r0.<init>();
                r1 = r2.d;
                r1 = r1.a();
                r0.a = r1;
                r1 = r2.c;
                r1 = r1.b();
                r0.b = r1;
                r1 = r2.b;
                r1 = r1.o();
                r1 = java.lang.Boolean.valueOf(r1);
                r0.c = r1;
                r1 = r2.a;
                r1 = r1.isSoundEnabled();
                r1 = java.lang.Boolean.valueOf(r1);
                r0.d = r1;
                r1 = r2.b;
                r1 = r1.j();
                r0.e = r1;
                r1 = r2.b;
                r1 = r1.m();
                r0.f = r1;
                r1 = r2.d;
                r1 = r1.b();
                r0.g = r1;
                r1 = r2.b;
                r1 = r1.g();
                r0.h = r1;
                r1 = com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.a.a;
                r0.i = r1;
                r1 = r2.b;
                r1 = r1.n();
                r0.j = r1;
                r1 = r2.b;
                r1 = r1.q();
                r0.k = r1;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory.b():com.vungle.publisher.protocol.message.RequestAd$DeviceInfo");
            }
        }

        /* compiled from: vungle */
        public enum a {
            ;

            private a(java.lang.String r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
                /*
                r1 = this;
                r0 = 0;
                r1.<init>(r2, r0);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.a.<init>(java.lang.String):void");
            }
        }

        protected DeviceInfo() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
            /*
            r0 = this;
            r0.<init>();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.<init>():void");
        }

        public final org.json.JSONObject b() throws org.json.JSONException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
            /*
            r3 = this;
            r1 = super.b();
            r0 = "connection";
            r2 = r3.a;
            r1.putOpt(r0, r2);
            r0 = "dim";
            r2 = r3.b;
            r2 = com.vungle.publisher.bu.a(r2);
            r1.putOpt(r0, r2);
            r2 = "isSdCardAvailable";
            r0 = r3.c;
            if (r0 != 0) goto L_0x0059;
        L_0x001c:
            r0 = 0;
        L_0x001d:
            r1.putOpt(r2, r0);
            r0 = "soundEnabled";
            r2 = r3.d;
            r1.putOpt(r0, r2);
            r0 = "mac";
            r2 = r3.e;
            r1.putOpt(r0, r2);
            r0 = "model";
            r2 = r3.f;
            r1.putOpt(r0, r2);
            r0 = "networkOperator";
            r2 = r3.g;
            r1.putOpt(r0, r2);
            r0 = "osVersion";
            r2 = r3.h;
            r1.putOpt(r0, r2);
            r0 = "platform";
            r2 = r3.i;
            r1.putOpt(r0, r2);
            r0 = "volume";
            r2 = r3.j;
            r1.putOpt(r0, r2);
            r0 = "userAgent";
            r2 = r3.k;
            r1.putOpt(r0, r2);
            return r1;
        L_0x0059:
            r0 = r0.booleanValue();
            if (r0 == 0) goto L_0x0065;
        L_0x005f:
            r0 = 1;
        L_0x0060:
            r0 = java.lang.Integer.valueOf(r0);
            goto L_0x001d;
        L_0x0065:
            r0 = 0;
            goto L_0x0060;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.b():org.json.JSONObject");
        }
    }

    /* compiled from: vungle */
    public static abstract class a<Q extends RequestAd<Q>> extends MessageFactory<Q> {
        @Inject
        protected Factory a;
        @Inject
        bf b;
        @Inject
        protected Factory c;
        @Inject
        protected bh d;

        protected a() {
        }

        protected Q b() {
            RequestAd requestAd = (RequestAd) a();
            requestAd.a = this.b.a();
            requestAd.b = this.b.c();
            requestAd.c = this.a.b();
            requestAd.d = this.c.b();
            requestAd.e = Boolean.valueOf(this.b.i());
            requestAd.f = this.d.b();
            return requestAd;
        }
    }

    public JSONObject b() throws JSONException {
        JSONObject b = super.b();
        b.putOpt("isu", this.b);
        b.putOpt("ifa", this.a);
        b.putOpt("demo", bu.a(this.c));
        b.putOpt("deviceInfo", bu.a(this.d));
        if (Boolean.FALSE.equals(this.e)) {
            b.putOpt("adTrackingEnabled", this.e);
        }
        b.putOpt("pubAppId", this.f);
        return b;
    }
}
