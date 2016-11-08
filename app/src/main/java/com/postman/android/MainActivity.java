package com.postman.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.postman.android.Network.CustomRequestWithHeader;
import com.postman.android.Network.ResponseModel;
import com.postman.android.Network.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @BindView(R.id.response)
    TextView mTextViewResponse;

    @BindView(R.id.status_code)
    TextView mTextViewStatusCode;

    @BindView(R.id.time)
    TextView mTextViewTime;


    @BindView(R.id.headers)
    TextView mTextViewHeaders;

    @BindView(R.id.spinner)
    Spinner mSpinner;

    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        List<String> categories = new ArrayList<String>();
        categories.add("GET");
        categories.add("POST");
        categories.add("PUT");
        categories.add("DELETE");
        categories.add("COPY");

        mSpinner.getBackground().setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);
    }


    @OnClick(R.id.btn_test)
    public void newRequest() {

        mProgressBar.setVisibility(View.VISIBLE);
        final int TIME_OUT = 800000;
        String url = "https://wall.alphacoders.com/api2.0/get.php?auth=62f6061416e9f81fc4915afb93980778&method=sub_category_list&id=31";


        CustomRequestWithHeader request = new CustomRequestWithHeader(Request.Method.GET, url, null, new Response.Listener<ResponseModel>() {
            @Override
            public void onResponse(ResponseModel response) {
                mProgressBar.setVisibility(View.GONE);
                showResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressBar.setVisibility(View.GONE);
                Timber.d("error");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + "");
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getsInstance().addToRequestQueue(request);
    }

    private void showResponse(ResponseModel response) {
        mTextViewHeaders.setText(response.getResponseHeader().toString());
        mTextViewResponse.setText(response.getResponse());
        mTextViewStatusCode.setText(response.getStatusCode()+"");
        mTextViewTime.setText(response.getNetworkTime()+" ms");

    }


}
