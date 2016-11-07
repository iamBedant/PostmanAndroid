package com.postman.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.postman.android.Network.CustomRequest;
import com.postman.android.Network.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @BindView(R.id.response)
    TextView mTextViewResponse;

    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
    }


    @OnClick(R.id.btn_test)
    public void sendRequest(){

        mProgressBar.setVisibility(View.VISIBLE);
        int TIME_OUT =800000;

        String url = "https://wall.alphacoders.com/api2.0/get.php?auth=62f6061416e9f81fc4915afb93980778&method=sub_category_list&id=31";

        Map<String, String> params = new HashMap<String, String>();
        params.put("device_token", "");

        CustomRequest sendToken = new CustomRequest(Request.Method.GET, url, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProgressBar.setVisibility(View.GONE);
                Timber.d(response);
                mTextViewResponse.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mProgressBar.setVisibility(View.GONE);
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "No Internet connection", Toast.LENGTH_SHORT).show();
                } else {
                    NetworkResponse networkResponse = error.networkResponse;
                    int x = networkResponse.statusCode;
                    Timber.d(x+"");
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + "");
                return headers;
            }
        };

        sendToken.setRetryPolicy(new DefaultRetryPolicy(
                TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getsInstance().addToRequestQueue(sendToken);

    }

}
