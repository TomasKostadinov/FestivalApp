package com.tomaskostadinov.openbeatz.helpers;

/**
 * Created by tomas on 05.04.17
 */

import com.loopj.android.http.*;

public class ConnectRestClient {
    private static final String BASE_URL = "https://connect.nearstage.com/v1/openbeatz";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getUrl(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
