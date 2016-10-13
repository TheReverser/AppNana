package com.trialpay.android;

import android.content.Context;
import android.content.Intent;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Video {
    private static final long FONT_LIFE_PERIOD = 432000;
    private static final int MAX_VIDEO_COUNTS = 10;
    private static final String TAG = "Trialpay.Video";
    private static DownloadWorker downloadWorker = null;
    private static Thread downloadWorkerThread = null;
    private static Set<String> downloadingVideos = new HashSet();
    private static boolean isVideoInitialized = false;
    private static String[] optionalParams = new String[]{"toi_url", "isCompleteVideoStored", "videoExpirationTime", "ec_cc", "duration"};
    private static String[] requiredParams = new String[]{"dl_url", "ck_url", "cn_url", "ec_slcb", "ec_to", "ec_url", "ec_ck_url", ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID, "completion_time", "exit_delay", "use_cd", "cd_text", "tc", "exp", "oid"};
    private static BaseTrialpayManager trialpayManager = null;
    private static VideoCachingClient videoCachingClient = new VideoCachingClient();
    public static final String videoPrefix = "tpvideo";
    private static Map<String, Map<String, String>> videoTrailersInfo = new HashMap();

    private static class DownloadWorker implements Runnable {
        private boolean interruptTaskFlag;
        private List<Runnable> taskQueue;

        private DownloadWorker() {
            this.taskQueue = new LinkedList();
            this.interruptTaskFlag = false;
        }

        public synchronized void run() {
            while (true) {
                if (this.taskQueue.size() > 0) {
                    Runnable currentTask = (Runnable) this.taskQueue.get(0);
                    this.taskQueue.remove(0);
                    currentTask.run();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        public synchronized void scheduleTask(Runnable task) {
            this.taskQueue.add(task);
            notify();
        }

        public synchronized void interruptCurrentTask() {
            this.interruptTaskFlag = true;
        }

        public synchronized boolean isInterruptTaskFlag() {
            return this.interruptTaskFlag;
        }
    }

    private static class FirePixelTask implements Runnable {
        private Callback callback;
        private String endcapPixelUrl;

        public FirePixelTask(String endcapPixelUrl, Callback callback) {
            this.endcapPixelUrl = endcapPixelUrl;
            this.callback = callback;
        }

        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.endcapPixelUrl).openStream(), Charset.forName("UTF-8")));
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    int line = bufferedReader.read();
                    if (line != -1) {
                        stringBuilder.append((char) line);
                    } else {
                        JSONObject json = new JSONObject(stringBuilder.toString());
                        Message callbackMessage = Message.obtain();
                        callbackMessage.obj = json;
                        this.callback.handleMessage(callbackMessage);
                        return;
                    }
                }
            } catch (Exception e) {
                Log.d(Video.TAG, "Endcap click pixel firing failed");
                e.printStackTrace();
            }
        }
    }

    private static class FontDownloadTask implements Runnable {
        public static final String fontAwesomeFileName = "font-awesome.ttf";
        private static final String fontAwesomeUrlString = "http://d2n8p8eh14pae1.cloudfront.net/static/fontawesome-webfont.ttf?r=b983eacb";
        private final int DELAY_BEFORE_REATTEMPT_FONT_DOWNLOADING;
        private boolean isFirstAttemptFailed;

        private FontDownloadTask() {
            this.isFirstAttemptFailed = false;
            this.DELAY_BEFORE_REATTEMPT_FONT_DOWNLOADING = 15000;
        }

        public void run() {
            if (downloadFontAwesome()) {
                Map<String, String> fontData = new HashMap();
                fontData.put("downloadingTimestamp", String.valueOf(new Date().getTime()));
                JSONObject fontJSONObject = new JSONObject(fontData);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(Video.getTpMetaDataFileByName("font.data"));
                    fileOutputStream.write(fontJSONObject.toString().getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean downloadFontAwesome() {
            try {
                URLConnection connection = new URL(fontAwesomeUrlString).openConnection();
                connection.setUseCaches(false);
                File fontRawFile = Video.getTpRawFile(fontAwesomeFileName);
                if (fontRawFile.exists()) {
                    fontRawFile.delete();
                } else {
                    fontRawFile.createNewFile();
                }
                connection.connect();
                int responseCode = ((HttpURLConnection) connection).getResponseCode();
                if (responseCode >= 200 && responseCode < 400) {
                    InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                    boolean z = fontRawFile.length() > 0 && responseCode == 206;
                    OutputStream outputStream = new FileOutputStream(fontRawFile, Boolean.valueOf(z).booleanValue());
                    byte[] data = new byte[4096];
                    while (true) {
                        int count = inputStream.read(data);
                        if (count != -1) {
                            outputStream.write(data, 0, count);
                        } else {
                            outputStream.close();
                            return true;
                        }
                    }
                } else if (this.isFirstAttemptFailed) {
                    return false;
                } else {
                    this.isFirstAttemptFailed = true;
                    getClass();
                    Thread.sleep(15000);
                    return downloadFontAwesome();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (this.isFirstAttemptFailed) {
                    return false;
                }
                this.isFirstAttemptFailed = true;
                try {
                    getClass();
                    Thread.sleep(15000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                return downloadFontAwesome();
            }
        }
    }

    public static class VideoCachingClient {
        boolean shouldCacheVideo() {
            return true;
        }
    }

    private static class VideoDownloadTask implements Runnable {
        private final int TIMEOUT_BEFORE_REATTEMPT_VIDEO_DOWNLOADING = 15000;
        private String downloadURL = null;
        private boolean isFirstAttemptFailed = false;

        public VideoDownloadTask(String downloadURL) {
            this.downloadURL = downloadURL;
        }

        public void run() {
            Boolean isVideoDownloaded = Boolean.valueOf(downloadVideoFromURL());
            synchronized (Video.videoTrailersInfo) {
                Map<String, String> videoTrailerStringMap = (Map) Video.videoTrailersInfo.get(this.downloadURL);
                if (videoTrailerStringMap != null) {
                    if (isVideoDownloaded.booleanValue()) {
                        videoTrailerStringMap.put("isCompleteVideoStored", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    } else {
                        videoTrailerStringMap.put("isCompleteVideoStored", "false");
                    }
                    Video.updateVideoMetaFile(videoTrailerStringMap);
                }
                Video.downloadingVideos.remove(this.downloadURL);
            }
        }

        private boolean downloadVideoFromURL() {
            if (this.downloadURL == null) {
                return false;
            }
            try {
                URL videoURL = new URL(this.downloadURL);
                String fullFileName = videoURL.getFile();
                String fileName = fullFileName.substring(fullFileName.lastIndexOf("/") + 1, fullFileName.contains("?") ? fullFileName.indexOf("?") : fullFileName.length());
                URLConnection connection = videoURL.openConnection();
                connection.setUseCaches(false);
                File videoRawFile = Video.getTpRawFile(fileName);
                if (videoRawFile.exists()) {
                    Map<String, String> videoTrailerStringMap = (Map) Video.videoTrailersInfo.get(this.downloadURL);
                    if (!(videoTrailerStringMap == null || ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(videoTrailerStringMap.get("isCompleteVideoStored")))) {
                        URLConnection connectionToCheckHeaders = videoURL.openConnection();
                        connectionToCheckHeaders.connect();
                        if (videoRawFile.length() >= ((long) ((HttpURLConnection) connectionToCheckHeaders).getContentLength())) {
                            return true;
                        }
                        connection.setRequestProperty("Range", "bytes=" + videoRawFile.length() + "-");
                    }
                } else {
                    videoRawFile.createNewFile();
                }
                connection.connect();
                int responseCode = ((HttpURLConnection) connection).getResponseCode();
                if (responseCode >= 200 && responseCode < 400) {
                    InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                    boolean z = videoRawFile.length() > 0 && responseCode == 206;
                    OutputStream outputStream = new FileOutputStream(videoRawFile, Boolean.valueOf(z).booleanValue());
                    Log.d(Video.TAG, "Video downloading started: " + this.downloadURL);
                    byte[] data = new byte[4096];
                    do {
                        int count = inputStream.read(data);
                        if (count != -1) {
                            outputStream.write(data, 0, count);
                        } else {
                            outputStream.close();
                            Log.d(Video.TAG, "Video downloading finished: " + this.downloadURL);
                            return true;
                        }
                    } while (!Video.downloadWorker.isInterruptTaskFlag());
                    outputStream.close();
                    return false;
                } else if (this.isFirstAttemptFailed) {
                    return false;
                } else {
                    this.isFirstAttemptFailed = true;
                    getClass();
                    Thread.sleep(15000);
                    return downloadVideoFromURL();
                }
            } catch (Exception e) {
                Log.d(Video.TAG, "Video downloading failed");
                e.printStackTrace();
                if (this.isFirstAttemptFailed) {
                    return false;
                }
                String str = Video.TAG;
                StringBuilder append = new StringBuilder().append("Reattempt downloading in ");
                getClass();
                Log.d(str, append.append(15000).append("ms").toString());
                this.isFirstAttemptFailed = true;
                try {
                    getClass();
                    Thread.sleep(15000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                return downloadVideoFromURL();
            }
        }
    }

    public static void initialize() {
        isVideoInitialized = true;
        trialpayManager = BaseTrialpayManager.getInstance();
        File[] subfiles = getTpMetaDataFileDirectory().listFiles();
        JSONArray videoJsonArray = new JSONArray();
        downloadWorker = new DownloadWorker();
        downloadWorkerThread = new Thread(downloadWorker);
        downloadWorkerThread.start();
        if (!isFontAwesomeDownloaded()) {
            downloadFontAwesome();
        }
        try {
            for (File subfile : subfiles) {
                if (subfile.getName().endsWith(".meta")) {
                    JSONObject videoJSONObject = getJSONObjectFromFile(subfile);
                    if (videoJSONObject == null) {
                        Log.d(TAG, "video meta file is incorrect: " + subfile + " file will be removed");
                        removeVideoRawAndMetaDataFiles(subfile.getName());
                    } else if (isVideoOutdated(videoJSONObject)) {
                        Log.d(TAG, "video file is outdated: " + videoJSONObject.getString("dl_url") + " file will be removed");
                        removeVideoRawAndMetaDataFiles(videoJSONObject.getString("dl_url"));
                    } else {
                        Log.d(TAG, "the video can be shown: " + videoJSONObject.getString("dl_url"));
                        videoJsonArray.put(videoJSONObject);
                    }
                }
            }
            initializeVideosWithParams(videoJsonArray);
        } catch (Exception e) {
            Log.d(TAG, "Videos initialization failed");
            e.printStackTrace();
        }
    }

    public static List<String> getLoadedVideoOfferIdsList() {
        List<String> loadedOffersList = new ArrayList();
        for (String videoOfferId : videoTrailersInfo.keySet()) {
            Map<String, String> videoTrailerStringMap = (Map) videoTrailersInfo.get(videoOfferId);
            if (videoTrailerStringMap.get("isCompleteVideoStored") != null && ((String) videoTrailerStringMap.get("isCompleteVideoStored")).equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                loadedOffersList.add(videoTrailerStringMap.get("oid"));
            }
        }
        return loadedOffersList;
    }

    public static boolean setVideoUnavailable(String videoDownloadUrl) {
        Map<String, String> videoTrailerStringMap = (Map) videoTrailersInfo.get(videoDownloadUrl);
        if (videoTrailerStringMap == null) {
            return false;
        }
        videoTrailerStringMap.put("isVideoPlayed", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        updateVideoMetaFile(videoTrailerStringMap);
        return true;
    }

    public static boolean open(Context context, String videoDownloadUrl, String touchpointName, boolean isPrimary) {
        Map<String, String> videoTrailerStringMap = (Map) videoTrailersInfo.get(videoDownloadUrl);
        if (videoTrailerStringMap != null) {
            String isCompleteVideoStored = (String) videoTrailerStringMap.get("isCompleteVideoStored");
            if (isCompleteVideoStored != null && isCompleteVideoStored.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra(Strings.EXTRA_KEY_TOUCHPOINT_NAME, touchpointName);
                intent.putExtra(Strings.EXTRA_KEY_VIDEO_DOWNLOAD_URL, videoDownloadUrl);
                intent.putExtra(Strings.EXTRA_KEY_IS_PRIMARY, new Boolean(isPrimary));
                intent.addFlags(268435456);
                context.startActivity(intent);
                return true;
            }
        }
        return false;
    }

    public static boolean isVideoReady(String videoDownloadUrl) {
        Map<String, String> videoTrailerInfoStringMap = getVideoInfoByDownloadUrl(videoDownloadUrl);
        if (videoTrailerInfoStringMap != null && videoTrailerInfoStringMap.get("isCompleteVideoStored") != null && !"false".equals(videoTrailerInfoStringMap.get("isCompleteVideoStored")) && videoTrailersInfo.get("isVideoPlayed") == null) {
            return true;
        }
        Log.d(TAG, "video is not ready:" + videoDownloadUrl);
        return false;
    }

    private static boolean isVideoOutdated(JSONObject videoJSONObject) throws JSONException {
        if (videoJSONObject.getLong("expirationTime") < new Date().getTime()) {
            return true;
        }
        return false;
    }

    public static boolean removeVideoRawAndMetaDataFiles(String videoDownloadUrl) {
        try {
            if (((Map) videoTrailersInfo.get(videoDownloadUrl)) != null) {
                String fileName = videoDownloadUrl.substring(videoDownloadUrl.lastIndexOf("/") + 1);
                File videoRawFile = new File(getTpDataFileDirectory().getPath() + "/" + fileName);
                File videoMetaDataFile = getTpMetaDataFileByName(fileName + ".meta");
                videoTrailersInfo.remove(videoDownloadUrl);
                videoMetaDataFile.delete();
                videoRawFile.delete();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, String> getVideoInfoByDownloadUrl(String downloadUrl) {
        return (Map) videoTrailersInfo.get(downloadUrl);
    }

    public static boolean initializeVideoWithParams(JSONObject newVideoTrailerInfo) {
        return initializeVideoWithParams(newVideoTrailerInfo, Boolean.valueOf(true));
    }

    public static synchronized boolean initializeVideoWithParams(JSONObject newVideoTrailerInfo, Boolean checkVideosCountAfterAdding) {
        boolean z;
        synchronized (Video.class) {
            if (!isVideoInitialized) {
                initialize();
            }
            try {
                Log.d(TAG, "initialize video: " + newVideoTrailerInfo.getString("dl_url"));
                Map<String, String> videoTrailerStringMap = new HashMap();
                for (String requiredParam : requiredParams) {
                    if (!newVideoTrailerInfo.has(requiredParam)) {
                        Log.w(TAG, "Video trailer initialization aborted - missing parameter " + requiredParam);
                        z = false;
                        break;
                    }
                    videoTrailerStringMap.put(requiredParam, newVideoTrailerInfo.getString(requiredParam));
                }
                for (String optionalParam : optionalParams) {
                    if (newVideoTrailerInfo.has(optionalParam)) {
                        videoTrailerStringMap.put(optionalParam, newVideoTrailerInfo.getString(optionalParam));
                    }
                }
                String downloadURL = (String) videoTrailerStringMap.get("dl_url");
                videoTrailerStringMap.put("expirationTime", String.valueOf(new Date().getTime() + ((String) videoTrailerStringMap.get("exp"))));
                boolean isVideoAlreadyDownloaded = false;
                if (videoTrailersInfo.get(downloadURL) != null) {
                    Map<String, String> existedVideoTrailerStringMap = (Map) videoTrailersInfo.get(downloadURL);
                    if (existedVideoTrailerStringMap.get("isCompleteVideoStored") != null) {
                        videoTrailerStringMap.put("isCompleteVideoStored", existedVideoTrailerStringMap.get("isCompleteVideoStored"));
                    }
                }
                if (downloadingVideos.contains(downloadURL)) {
                    Log.d(TAG, "the video is already scheduled to be downloaded " + downloadURL);
                    isVideoAlreadyDownloaded = true;
                } else if (videoTrailerStringMap.get("isCompleteVideoStored") == null || !((String) videoTrailerStringMap.get("isCompleteVideoStored")).equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                    Log.d(TAG, "video will be downloaded: " + downloadURL);
                } else {
                    isVideoAlreadyDownloaded = true;
                    Log.d(TAG, "the video is already downloaded " + downloadURL);
                }
                videoTrailersInfo.remove(downloadURL);
                updateVideoMetaFile(videoTrailerStringMap);
                videoTrailersInfo.put(downloadURL, videoTrailerStringMap);
                if (checkVideosCountAfterAdding.booleanValue() && videoTrailersInfo.size() > MAX_VIDEO_COUNTS) {
                    removeOldestVideoRawAndMetaDataFiles();
                }
                if (!isVideoAlreadyDownloaded) {
                    while (!videoCachingClient.shouldCacheVideo()) {
                        Thread.sleep(60000);
                    }
                    VideoDownloadTask videoDownloadTask = new VideoDownloadTask((String) videoTrailerStringMap.get("dl_url"));
                    Log.d(TAG, "add download task (" + Thread.currentThread().getId() + "): " + downloadURL);
                    downloadingVideos.add(downloadURL);
                    downloadWorker.scheduleTask(videoDownloadTask);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            z = false;
        }
        return z;
    }

    public static boolean initializeVideosWithParams(JSONArray newVideoTrailersInfo) {
        for (int i = 0; i < newVideoTrailersInfo.length(); i++) {
            try {
                initializeVideoWithParams(newVideoTrailersInfo.getJSONObject(i), Boolean.valueOf(false));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (videoTrailersInfo.size() > MAX_VIDEO_COUNTS) {
            removeOldestVideoRawAndMetaDataFiles();
        }
        return true;
    }

    public static boolean fireVideoPixel(final String pixelUrlString) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int responseCode = ((HttpURLConnection) new URL(pixelUrlString).openConnection()).getResponseCode();
                    if (responseCode >= 200 && responseCode < 400) {
                        Log.d(Video.TAG, "Successfully fired pixel to " + pixelUrlString);
                    }
                } catch (Exception e) {
                    Log.d(Video.TAG, "Pixel firing failed (url: " + pixelUrlString + ")");
                    e.printStackTrace();
                }
            }
        }).start();
        return true;
    }

    public static void setVideoCachingClient(VideoCachingClient newVideoCachingClient) {
        videoCachingClient = newVideoCachingClient;
    }

    public static void fireEndcapPixel(String endcapPixelUrl, Callback callback) {
        new Thread(new FirePixelTask(endcapPixelUrl, callback)).start();
    }

    private static boolean removeOldestVideoRawAndMetaDataFiles() {
        Map<String, String> oldestVideoTrailerStringMap = null;
        for (Map<String, String> videoTrailerStringMap : videoTrailersInfo.values()) {
            long parseLong = (oldestVideoTrailerStringMap == null || oldestVideoTrailerStringMap.get("expirationTime") == null) ? 0 : Long.parseLong((String) oldestVideoTrailerStringMap.get("expirationTime"));
            Long oldestVideoTrailerExpirationTime = Long.valueOf(parseLong);
            Long videoTrailerExpirationTime = Long.valueOf(videoTrailerStringMap.get("expirationTime") == null ? 0 : Long.parseLong((String) videoTrailerStringMap.get("expirationTime"), MAX_VIDEO_COUNTS));
            if (oldestVideoTrailerExpirationTime.longValue() == 0 || videoTrailerExpirationTime.longValue() <= oldestVideoTrailerExpirationTime.longValue()) {
                oldestVideoTrailerStringMap = videoTrailerStringMap;
            }
        }
        return oldestVideoTrailerStringMap != null && removeVideoRawAndMetaDataFiles((String) oldestVideoTrailerStringMap.get("dl_url"));
    }

    private static boolean updateVideoMetaFile(Map<String, String> videoTrailerStringMap) {
        try {
            String downloadURL = (String) videoTrailerStringMap.get("dl_url");
            FileOutputStream fileOutputStream = new FileOutputStream(getTpMetaDataFileByName(downloadURL.substring(downloadURL.lastIndexOf("/") + 1) + ".meta"));
            fileOutputStream.write(new JSONObject(videoTrailerStringMap).toString().getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Can't update video metadata file");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFontAwesomeDownloaded() {
        JSONObject fontJSONObject = getJSONObjectFromFile(getTpMetaDataFileByName("font.data"));
        try {
            long currentTimestamp = new Date().getTime();
            if (fontJSONObject == null || Long.parseLong(fontJSONObject.getString("downloadingTimestamp")) + FONT_LIFE_PERIOD < currentTimestamp) {
                return false;
            }
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private static void downloadFontAwesome() {
        downloadWorker.scheduleTask(new FontDownloadTask());
    }

    public static File getFontAwesomeFile() {
        return getTpRawFile(FontDownloadTask.fontAwesomeFileName);
    }

    private static JSONObject getJSONObjectFromFile(File file) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    stringBuilder.append(line);
                } else {
                    bufferedReader.close();
                    return new JSONObject(stringBuilder.toString());
                }
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e2) {
            return null;
        } catch (JSONException e3) {
            return null;
        }
    }

    private static File getTpMetaDataFileDirectory() {
        File tpInternalSubdirectory = new File(trialpayManager.getContext().getFilesDir(), Strings.TP_FILES_DIRECTORY);
        if (!tpInternalSubdirectory.exists()) {
            tpInternalSubdirectory.mkdir();
        }
        return tpInternalSubdirectory;
    }

    private static File getTpMetaDataFileByName(String fileName) {
        return new File(getTpMetaDataFileDirectory(), fileName);
    }

    private static File getTpDataFileDirectory() {
        return trialpayManager.getContext().getExternalFilesDir(null);
    }

    public static File getTpRawFile(String fileName) {
        return new File(getTpDataFileDirectory(), fileName);
    }
}
