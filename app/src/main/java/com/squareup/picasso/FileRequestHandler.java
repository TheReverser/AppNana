package com.squareup.picasso;

import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.RequestHandler.Result;
import com.vungle.log.Logger;
import java.io.IOException;

class FileRequestHandler extends ContentStreamRequestHandler {
    FileRequestHandler(Context context) {
        super(context);
    }

    public boolean canHandleRequest(Request data) {
        return "file".equals(data.uri.getScheme());
    }

    public Result load(Request request, int networkPolicy) throws IOException {
        return new Result(null, getInputStream(request), LoadedFrom.DISK, getFileExifRotation(request.uri));
    }

    static int getFileExifRotation(Uri uri) throws IOException {
        switch (new ExifInterface(uri.getPath()).getAttributeInt("Orientation", 1)) {
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                return 180;
            case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                return 90;
            case HapticPlaybackThread.HAPTIC_DOWNLOAD_ERROR /*8*/:
                return 270;
            default:
                return 0;
        }
    }
}
