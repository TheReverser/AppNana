package com.vungle.publisher;

import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.protocol.message.RequestAdResponse;

/* compiled from: vungle */
public interface at<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends av<Integer> {

    /* compiled from: vungle */
    public enum a {
        aware,
        downloading,
        downloaded,
        ready
    }

    /* compiled from: vungle */
    public enum b {
        localVideo,
        postRoll,
        preRoll,
        streamingVideo
    }

    void a(a aVar);

    A c();

    String d();

    a e();

    b f();
}
