package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bu;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LocalAdReportEvent;
import com.vungle.publisher.db.model.LocalVideo;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ReportLocalAd extends ReportAd<RequestLocalAd, ReportLocalAd> {
    ExtraInfo m;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.protocol.message.ReportAd.Factory<RequestLocalAd, RequestLocalAdResponse, ReportLocalAd, LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo> {
        @Inject
        PlayFactory b;
        @Inject
        com.vungle.publisher.protocol.message.RequestLocalAd.Factory c;

        @Singleton
        /* compiled from: vungle */
        static class PlayFactory extends com.vungle.publisher.protocol.message.ReportAd.Play.Factory<RequestLocalAdResponse, LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo> {
            @Inject
            UserActionFactory a;

            @Singleton
            /* compiled from: vungle */
            static class UserActionFactory extends com.vungle.publisher.protocol.message.ReportAd.Play.UserAction.Factory<RequestLocalAdResponse, LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo> {
                @javax.inject.Inject
                UserActionFactory() {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory.<init>():void");
                }
            }

            PlayFactory() {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.<init>():void");
            }
        }

        public final /* synthetic */ com.vungle.publisher.protocol.message.ReportAd a(com.vungle.publisher.db.model.AdReport r3) {
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
            r2 = this;
            r3 = (com.vungle.publisher.db.model.LocalAdReport) r3;
            r0 = super.a(r3);
            r0 = (com.vungle.publisher.protocol.message.ReportLocalAd) r0;
            if (r0 == 0) goto L_0x0030;
        L_0x000a:
            r1 = r3.A();
            r1 = java.lang.Integer.valueOf(r1);
            r0.a = r1;
            r1 = r3.f();
            r1 = com.vungle.publisher.protocol.message.ExtraInfo.Factory.b(r1);
            r0.m = r1;
            r1 = r3.e();
            r1 = (com.vungle.publisher.db.model.LocalAd) r1;
            r1 = r1.k();
            r1 = (com.vungle.publisher.db.model.LocalVideo) r1;
            r1 = r1.b;
            r1 = r1.c;
            r0.l = r1;
        L_0x0030:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.a(com.vungle.publisher.db.model.AdReport):com.vungle.publisher.protocol.message.ReportAd");
        }

        protected final /* synthetic */ java.lang.Object a() {
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
            r1 = this;
            r0 = new com.vungle.publisher.protocol.message.ReportLocalAd;
            r0.<init>();
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.a():java.lang.Object");
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.protocol.message.RequestAd.a b() {
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
            r1 = this;
            r0 = r1.c;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.b():com.vungle.publisher.protocol.message.RequestAd$a");
        }

        Factory() {
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
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.<init>():void");
        }
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        if (b != null) {
            b.putOpt("extraInfo", bu.a(this.m));
        }
        return b;
    }
}
