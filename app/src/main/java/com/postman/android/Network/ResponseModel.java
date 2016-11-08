package com.postman.android.Network;

import java.util.Map;

/**
 * Created by @iamBedant on 08/11/16.
 */

public class ResponseModel {
    Map<String, String> responseHeader;
    String response;
    int statusCode;
    long networkTime;

    public ResponseModel(Map<String, String> responseHeader, String response, int status_code, long networkTimeMs) {
        this.responseHeader = responseHeader;
        this.response = response;
        this.statusCode = status_code;
        this.networkTime = networkTimeMs;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getNetworkTime() {
        return networkTime;
    }

    public Map<String, String> getResponseHeader() {
        return responseHeader;
    }

    public String getResponse() {
        return response;
    }
}
