package com.appnana.android.net;

import android.content.Context;
import android.widget.ImageView;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppIconRequest<T> extends Request<String> {
    private boolean complete = false;
    private String mAppIconUrlRegex;
    private Context mContext;
    private ImageView mImageView;

    public AppIconRequest(Context context, String appId, String appIconUrlRegex, ImageView imageView) {
        super(0, "https://play.google.com/store/apps/details?id=" + appId, null);
        this.mContext = context;
        this.mAppIconUrlRegex = appIconUrlRegex;
        this.mImageView = imageView;
    }

    protected void deliverResponse(String response) {
        Matcher matcher = Pattern.compile(this.mAppIconUrlRegex).matcher(response);
        if (matcher.find()) {
            String appIconUrl = matcher.group(1);
            if (appIconUrl.startsWith("//")) {
                appIconUrl = "https:" + appIconUrl;
            } else if (!(appIconUrl.startsWith("https://") || appIconUrl.startsWith("http://"))) {
                appIconUrl = "https://" + appIconUrl;
            }
            Picasso.with(this.mContext).load(appIconUrl).into(this.mImageView, new Callback() {
                public void onSuccess() {
                    AppIconRequest.this.complete = true;
                }

                public void onError() {
                    AppIconRequest.this.complete = false;
                }
            });
        }
    }

    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return Response.success(new String(response.data), HttpHeaderParser.parseCacheHeaders(response));
    }

    public boolean isComplete() {
        return this.complete;
    }
}
