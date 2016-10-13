package com.chartboost.sdk.impl;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

public class by {

    private static abstract class c extends bv {
        protected final ca a;

        c(ca caVar) {
            this.a = caVar;
        }
    }

    private static class a extends c {
        a(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            fr frVar = (fr) obj;
            br brVar = new br();
            brVar.a("$code", frVar.a());
            this.a.a(brVar, stringBuilder);
        }
    }

    private static class b extends c {
        b(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            fs fsVar = (fs) obj;
            br brVar = new br();
            brVar.a("$code", fsVar.a());
            brVar.a("$scope", fsVar.b());
            this.a.a(brVar, stringBuilder);
        }
    }

    private static class d extends c {
        d(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            stringBuilder.append("{ ");
            bt btVar = (bt) obj;
            Object obj2 = 1;
            for (String str : btVar.keySet()) {
                if (obj2 != null) {
                    obj2 = null;
                } else {
                    stringBuilder.append(" , ");
                }
                bx.a(stringBuilder, str);
                stringBuilder.append(" : ");
                this.a.a(btVar.a(str), stringBuilder);
            }
            stringBuilder.append("}");
        }
    }

    private static class e extends c {
        e(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            bu buVar = (bu) obj;
            br brVar = new br();
            brVar.a("$ref", buVar.b());
            brVar.a("$id", buVar.a());
            this.a.a(brVar, stringBuilder);
        }
    }

    private static class f extends c {
        f(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            Object obj2 = 1;
            stringBuilder.append("[ ");
            for (Object next : (Iterable) obj) {
                if (obj2 != null) {
                    obj2 = null;
                } else {
                    stringBuilder.append(" , ");
                }
                this.a.a(next, stringBuilder);
            }
            stringBuilder.append("]");
        }
    }

    private static class g extends c {
        g(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            fp fpVar = (fp) obj;
            br brVar = new br();
            brVar.a("$ts", Integer.valueOf(fpVar.a()));
            brVar.a("$inc", Integer.valueOf(fpVar.b()));
            this.a.a(brVar, stringBuilder);
        }
    }

    private static class h extends bv {
        private h() {
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            stringBuilder.append("<Binary Data>");
        }
    }

    private static class i extends c {
        i(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            Date date = (Date) obj;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            simpleDateFormat.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
            this.a.a(new br("$date", simpleDateFormat.format(date)), stringBuilder);
        }
    }

    private static class j extends c {
        j(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            stringBuilder.append("{ ");
            Object obj2 = 1;
            for (Entry entry : ((Map) obj).entrySet()) {
                if (obj2 != null) {
                    obj2 = null;
                } else {
                    stringBuilder.append(" , ");
                }
                bx.a(stringBuilder, entry.getKey().toString());
                stringBuilder.append(" : ");
                this.a.a(entry.getValue(), stringBuilder);
            }
            stringBuilder.append("}");
        }
    }

    private static class k extends c {
        k(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            this.a.a(new br("$maxKey", Integer.valueOf(1)), stringBuilder);
        }
    }

    private static class l extends c {
        l(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            this.a.a(new br("$minKey", Integer.valueOf(1)), stringBuilder);
        }
    }

    private static class m extends c {
        m(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            stringBuilder.append("[ ");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    stringBuilder.append(" , ");
                }
                this.a.a(Array.get(obj, i), stringBuilder);
            }
            stringBuilder.append("]");
        }
    }

    private static class n extends c {
        n(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            this.a.a(new br("$oid", obj.toString()), stringBuilder);
        }
    }

    private static class o extends c {
        o(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            bt brVar = new br();
            brVar.a("$regex", obj.toString());
            if (((Pattern) obj).flags() != 0) {
                brVar.a("$options", fd.a(((Pattern) obj).flags()));
            }
            this.a.a(brVar, stringBuilder);
        }
    }

    private static class p extends bv {
        private p() {
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            bx.a(stringBuilder, (String) obj);
        }
    }

    private static class q extends bv {
        private q() {
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            stringBuilder.append(obj.toString());
        }
    }

    private static class r extends c {
        r(ca caVar) {
            super(caVar);
        }

        public void a(Object obj, StringBuilder stringBuilder) {
            UUID uuid = (UUID) obj;
            br brVar = new br();
            brVar.a("$uuid", uuid.toString());
            this.a.a(brVar, stringBuilder);
        }
    }

    public static ca a() {
        ca b = b();
        b.a(Date.class, new i(b));
        b.a(fp.class, new g(b));
        b.a(fq.class, new h());
        b.a(byte[].class, new h());
        return b;
    }

    static bw b() {
        bw bwVar = new bw();
        bwVar.a(Object[].class, new m(bwVar));
        bwVar.a(Boolean.class, new q());
        bwVar.a(fr.class, new a(bwVar));
        bwVar.a(fs.class, new b(bwVar));
        bwVar.a(bt.class, new d(bwVar));
        bwVar.a(bu.class, new e(bwVar));
        bwVar.a(Iterable.class, new f(bwVar));
        bwVar.a(Map.class, new j(bwVar));
        bwVar.a(ft.class, new k(bwVar));
        bwVar.a(fu.class, new l(bwVar));
        bwVar.a(Number.class, new q());
        bwVar.a(fv.class, new n(bwVar));
        bwVar.a(Pattern.class, new o(bwVar));
        bwVar.a(String.class, new p());
        bwVar.a(UUID.class, new r(bwVar));
        return bwVar;
    }
}
