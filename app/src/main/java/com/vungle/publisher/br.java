package com.vungle.publisher;

import com.facebook.internal.Utility;
import com.vungle.log.Logger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* compiled from: vungle */
public final class br {

    /* compiled from: vungle */
    public interface a {
        void a(File file, long j);
    }

    public static void a(File file, File file2, a... aVarArr) throws IOException {
        Logger.d(Logger.FILE_TAG, "extracting " + file + " to " + file2);
        if (file2.isDirectory() || file2.mkdirs()) {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            byte[] bArr = new byte[Utility.DEFAULT_STREAM_BUFFER_SIZE];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    if (bo.b(name)) {
                        File canonicalFile = new File(file2, name).getCanonicalFile();
                        if (bo.a(file2, canonicalFile)) {
                            int read;
                            Logger.v(Logger.FILE_TAG, "verified " + canonicalFile + " is nested within " + file2);
                            bo.a(canonicalFile.getParentFile());
                            Logger.v(Logger.FILE_TAG, "extracting " + canonicalFile);
                            OutputStream fileOutputStream = new FileOutputStream(canonicalFile);
                            long j = 0;
                            while (true) {
                                try {
                                    read = zipInputStream.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    j += (long) read;
                                    fileOutputStream.write(bArr, 0, read);
                                } catch (Throwable th) {
                                    try {
                                        zipInputStream.close();
                                    } catch (IOException e) {
                                        Logger.w(Logger.FILE_TAG, "error closing zip input stream " + file);
                                    }
                                }
                            }
                            for (read = 0; read <= 0; read++) {
                                aVarArr[read].a(canonicalFile, j);
                            }
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                Logger.w(Logger.FILE_TAG, "error closing file output stream " + file2);
                            }
                        } else {
                            throw new bq("aborting zip extraction - child " + name + " escapes destination directory " + file2);
                        }
                    }
                    throw new bp("Unsafe path " + name);
                }
                try {
                    zipInputStream.close();
                    return;
                } catch (IOException e3) {
                    Logger.w(Logger.FILE_TAG, "error closing zip input stream " + file);
                    return;
                }
            }
        }
        throw new IOException("failed to create directories " + file2);
    }
}
