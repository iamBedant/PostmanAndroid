package com.postman.android.Network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;


import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by @iamBedant on 07/11/16.
 */

public class CustomRequest extends Request<ResponseModel> {

    private Listener<ResponseModel> listener;
    private Map<String, String> params;

    public CustomRequest(int method, String url, Map<String, String> params,
                         Listener<ResponseModel> reponseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
    }

    @Override
    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
        return params;
    }


    @Override
    protected void deliverResponse(ResponseModel response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<ResponseModel> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new ResponseModel(response.headers, jsonString, response.statusCode, response.networkTimeMs),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

}
