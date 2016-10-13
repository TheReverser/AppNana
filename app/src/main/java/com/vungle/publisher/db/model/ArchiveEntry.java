package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ArchiveEntry extends as<Integer> {
    LocalArchive a;
    String b;
    Integer c;
    @Inject
    Factory d;
    @Inject
    Factory e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<ArchiveEntry, Integer> {
        @Inject
        Provider<ArchiveEntry> a;

        protected final /* synthetic */ as a(as asVar, Cursor cursor) {
            ArchiveEntry archiveEntry = (ArchiveEntry) asVar;
            archiveEntry.s = aq.d(cursor, ShareConstants.WEB_DIALOG_PARAM_ID);
            archiveEntry.b = aq.f(cursor, "relative_path");
            archiveEntry.c = aq.d(cursor, "size");
            return archiveEntry;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new ArchiveEntry[i];
        }

        protected final /* synthetic */ as c_() {
            return b();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        final int a(Integer num) {
            if (num == null) {
                throw new IllegalArgumentException("null viewable_id");
            }
            int delete = this.c.getWritableDatabase().delete("archive_entry", "viewable_id = ?", new String[]{String.valueOf(num)});
            Logger.d(Logger.DATABASE_TAG, "deleted " + delete + " archive_entry rows for viewable_id " + num);
            return delete;
        }

        final ArchiveEntry[] a(LocalArchive localArchive) {
            Throwable th;
            if (localArchive == null) {
                throw new IllegalArgumentException("null archive");
            }
            Integer E = localArchive.E();
            if (E == null) {
                throw new IllegalArgumentException("null viewable_id");
            }
            Cursor query;
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching archive_entry records by viewable_id " + E);
                query = this.c.getReadableDatabase().query("archive_entry", null, "viewable_id = ?", new String[]{String.valueOf(E)}, null, null, null);
                try {
                    ArchiveEntry[] archiveEntryArr = new ArchiveEntry[query.getCount()];
                    int i = 0;
                    while (query.moveToNext()) {
                        as b = b();
                        b(b, query);
                        b.a = localArchive;
                        archiveEntryArr[i] = b;
                        Logger.v(Logger.DATABASE_TAG, "fetched " + b);
                        i++;
                    }
                    if (query != null) {
                        query.close();
                    }
                    return archiveEntryArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        protected final String e_() {
            return "archive_entry";
        }

        final ArchiveEntry b() {
            return (ArchiveEntry) this.a.get();
        }
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.d;
    }

    protected ArchiveEntry() {
    }

    protected final String b() {
        return "archive_entry";
    }

    private Integer d() {
        return this.a == null ? null : this.a.E();
    }

    public final int m() {
        if (this.s != null) {
            return super.m();
        }
        Integer d = d();
        Logger.d(Logger.DATABASE_TAG, "updating archive_entry by viewable_id " + d + ", relative_path " + this.b);
        int updateWithOnConflict = this.t.getWritableDatabase().updateWithOnConflict("archive_entry", a(false), "viewable_id = ? AND relative_path = ?", new String[]{String.valueOf(d), r6}, 3);
        w();
        return updateWithOnConflict;
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (this.s != null) {
            contentValues.put(ShareConstants.WEB_DIALOG_PARAM_ID, (Integer) this.s);
        }
        contentValues.put("viewable_id", d());
        contentValues.put("relative_path", this.b);
        contentValues.put("size", this.c);
        return contentValues;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "viewable_id", d(), false);
        as.a(p, "relative_path", this.b, false);
        as.a(p, "size", this.c, false);
        return p;
    }
}
