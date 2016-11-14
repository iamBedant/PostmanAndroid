package com.postman.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.postman.android.fragments.SubmitRequestFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getFragmentManager().beginTransaction().replace(R.id.container, SubmitRequestFragment.newInstance(),SubmitRequestFragment.TAG).commit();
    }
}
