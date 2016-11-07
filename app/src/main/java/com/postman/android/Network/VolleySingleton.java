package com.postman.android.Network;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.postman.android.MyApplication;

/**
 * Created by @iamBedant on 07/11/16.
 */

public class VolleySingleton {

    public static final String TAG = VolleySingleton.class.getSimpleName();

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;
    public VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getsInstance(){
        if(sInstance == null){
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }


    public RequestQueue getRequestQueue() {

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
