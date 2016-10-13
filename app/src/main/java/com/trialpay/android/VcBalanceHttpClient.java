package com.trialpay.android;

import android.util.Log;
import com.facebook.BuildConfig;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.trialpay.android.UrlManager.Url;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VcBalanceHttpClient {
    private static final String TAG = "Trialpay.VcBalanceHttpClient";
    private final Map<String, DataChunk> request = new HashMap();
    private final Map<String, DataChunk> response = new HashMap();
    private final String sid;

    public static class DataChunk {
        public Integer diffBalance;
        public String error;
        public Integer lastTransactionId;
        public Integer secondsValid;
        public Boolean success;
        public String vic;
        public List<String> vics;

        public DataChunk(JSONObject obj) throws JSONException {
            boolean z = true;
            this.vic = obj.getString("vic");
            if (obj.has("balance")) {
                this.diffBalance = Integer.valueOf(obj.getInt("balance"));
            }
            if (obj.has("last_transaction_id")) {
                this.lastTransactionId = Integer.valueOf(obj.getInt("last_transaction_id"));
            }
            if (obj.has("seconds_valid")) {
                this.secondsValid = Integer.valueOf(obj.getInt("seconds_valid"));
            }
            if (obj.has("vics")) {
                this.vics = new ArrayList();
                JSONArray vicsJsonArray = obj.getJSONArray("vics");
                for (int i = 0; i < vicsJsonArray.length(); i++) {
                    this.vics.add(vicsJsonArray.getString(i));
                }
            }
            if (obj.has(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE)) {
                this.error = obj.getString(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE);
            }
            if (obj.has(GraphResponse.SUCCESS_KEY)) {
                String s = null;
                Integer i2 = null;
                Boolean b = null;
                try {
                    s = obj.getString(GraphResponse.SUCCESS_KEY);
                } catch (JSONException e) {
                    try {
                        b = Boolean.valueOf(obj.getBoolean(GraphResponse.SUCCESS_KEY));
                    } catch (JSONException e2) {
                        try {
                            i2 = Integer.valueOf(obj.getInt(GraphResponse.SUCCESS_KEY));
                        } catch (JSONException e3) {
                        }
                    }
                }
                if (b != null) {
                    this.success = b;
                } else if (i2 != null) {
                    if (i2.intValue() == 0) {
                        z = false;
                    }
                    this.success = Boolean.valueOf(z);
                } else if (s != null) {
                    s = s.trim().toLowerCase();
                    if (s.equals(BuildConfig.VERSION_NAME) || s.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO) || s.equals("false")) {
                        z = false;
                    }
                    this.success = Boolean.valueOf(z);
                } else {
                    throw new JSONException("Cannot parse \"success\" value");
                }
            }
        }

        public DataChunk(String vic) {
            this.vic = vic;
        }

        public DataChunk(DataChunk dc) {
            this.vic = dc.vic;
            this.diffBalance = dc.diffBalance;
            this.lastTransactionId = dc.lastTransactionId;
            this.secondsValid = dc.secondsValid;
            this.vics = dc.vics;
            this.error = dc.error;
            this.success = dc.success;
        }

        public JSONObject toJSONObject(boolean includeErrorSuccess, boolean includeSecondsValid) {
            try {
                JSONObject obj = new JSONObject();
                obj.put("vic", this.vic);
                if (this.diffBalance != null) {
                    obj.put("balance", this.diffBalance);
                }
                if (this.lastTransactionId != null) {
                    obj.put("last_transaction_id", this.lastTransactionId);
                }
                if (includeSecondsValid && this.secondsValid != null) {
                    obj.put("seconds_valid", this.secondsValid);
                }
                if (!includeErrorSuccess) {
                    return obj;
                }
                if (this.error != null) {
                    obj.put(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, this.error);
                }
                if (this.success == null) {
                    return obj;
                }
                obj.put(GraphResponse.SUCCESS_KEY, this.success);
                return obj;
            } catch (JSONException ex) {
                Log.v(VcBalanceHttpClient.TAG, ex.toString());
                Utils.assertTrue(true, "Unexpected behavior", VcBalanceHttpClient.TAG);
                return null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            sb.append("vic=").append(this.vic).append("|");
            sb.append("diff=").append(this.diffBalance).append("|");
            sb.append("lastTransactionId=").append(this.lastTransactionId).append("|");
            sb.append("secondsValid=").append(this.secondsValid).append("|");
            sb.append("error=").append(this.error).append("|");
            sb.append("success=").append(this.success).append("]");
            return sb.toString();
        }
    }

    public static class InvalidResponseException extends Exception {
        private static final long serialVersionUID = 8815966791517901787L;

        public InvalidResponseException(String s) {
            super(s);
        }
    }

    public static class ServerErrorException extends Exception {
        private static final long serialVersionUID = -7271837527998344873L;

        public ServerErrorException(String s) {
            super(s);
        }
    }

    public VcBalanceHttpClient(String sid) {
        this.sid = sid;
    }

    public void reset() {
        this.request.clear();
        this.response.clear();
    }

    public void addRequestParams(DataChunk params) {
        Utils.assertTrue(params.vic != null, "VIC must be provided", TAG);
        this.request.put(params.vic, params);
    }

    public void addRequestParams(String vic) {
        this.request.put(vic, new DataChunk(vic));
    }

    public DataChunk getRequestParams(String vic) {
        return (DataChunk) this.request.get(vic);
    }

    public boolean hasVicInRequest(String vic) {
        return this.request.containsKey(vic);
    }

    public String[] getRequestVics() {
        return (String[]) this.request.keySet().toArray(new String[0]);
    }

    public void execQueryRequest() throws IOException, JSONException, ServerErrorException, InvalidResponseException {
        Url url = UrlManager.getInstance().getVcBalanceUrl().addQueryParam("sid", this.sid);
        for (String vic : this.request.keySet()) {
            url.addQueryParam("vic[]", vic);
        }
        String builtUrl = url.toString();
        Log.v(TAG, "Sending Query request: " + builtUrl);
        parseResponse(createHttpClient().execute(new HttpGet(builtUrl)));
    }

    public void execAckRequest() throws IOException, JSONException, ServerErrorException, InvalidResponseException {
        Url url = UrlManager.getInstance().getVcBalanceUrl().addQueryParam("sid", this.sid);
        HttpPost request = new HttpPost(url.toString());
        JSONArray data = new JSONArray();
        for (String vic : this.request.keySet()) {
            data.put(((DataChunk) this.request.get(vic)).toJSONObject(false, false));
        }
        Log.v(TAG, "Sending Ack request: " + url.toString() + " with data " + data.toString());
        try {
            request.setEntity(new StringEntity(data.toString(), "UTF8"));
        } catch (UnsupportedEncodingException e) {
            try {
                Log.d(TAG, "Unsupported encoding UTF8, proceeding without encoding");
                request.setEntity(new StringEntity(data.toString()));
            } catch (UnsupportedEncodingException e1) {
                Utils.assertTrue(false, "Should never happen", TAG);
                Log.v(TAG, e1.toString());
                throw new RuntimeException();
            }
        }
        request.setHeader("Content-type", "application/json");
        parseResponse(createHttpClient().execute(request));
    }

    private void parseResponse(HttpResponse response) throws JSONException, IOException, ServerErrorException, InvalidResponseException {
        this.response.clear();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            StringBuilder resultBuilder = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                resultBuilder.append(line);
            }
            instream.close();
            String result = resultBuilder.toString();
            Log.v(TAG, "Response: " + result);
            if (result.trim().startsWith("{")) {
                try {
                    JSONObject objErr = new JSONObject(result);
                    if (objErr.has(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE)) {
                        throw new ServerErrorException(objErr.getString(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE));
                    }
                } catch (JSONException e) {
                }
                try {
                    Log.v(TAG, "JSON looks weird, proceeding at our own risk");
                } catch (JSONException ex) {
                    Log.v(TAG, ex.toString());
                    Log.w(TAG, String.format("Cannot parse %s at %d: SKIP", new Object[]{result, Integer.valueOf(i)}));
                } catch (Throwable th) {
                    if (true) {
                        this.response.clear();
                    }
                }
            }
            JSONArray array = new JSONArray(result);
            int i = 0;
            while (i < array.length()) {
                DataChunk chunk = new DataChunk(array.getJSONObject(i));
                this.response.put(chunk.vic, chunk);
                if (hasVicInRequest(chunk.vic)) {
                    i++;
                } else {
                    throw new InvalidResponseException("Response contains unknown VIC: " + chunk.vic);
                }
            }
            if (false) {
                this.response.clear();
                return;
            }
            return;
        }
        throw new InvalidResponseException("No response (HttpEntity is null)");
    }

    public DataChunk getResponse(String vic) {
        return (DataChunk) this.response.get(vic);
    }

    protected HttpClient createHttpClient() {
        return new DefaultHttpClient();
    }
}
