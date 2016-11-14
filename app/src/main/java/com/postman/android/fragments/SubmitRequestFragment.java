package com.postman.android.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.postman.android.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitRequestFragment extends Fragment {
    public static final String TAG = SubmitRequestFragment.class.getSimpleName();

    @BindView(R.id.body_layout) RelativeLayout mBodyAttributeslayout;
    @BindView(R.id.header_layout) LinearLayout mHeaderAttributeslayout;

    private int startIdHeaderAttrs = 100;

    private Map<String, String> mHeaderKeyValueMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submit_request, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @OnClick(R.id.request_header)
    void onHeaderClick(){
        mBodyAttributeslayout.setVisibility(View.GONE);
        mHeaderAttributeslayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.request_body)
    void onBodyClick(){
        mBodyAttributeslayout.setVisibility(View.VISIBLE);
        mHeaderAttributeslayout.setVisibility(View.GONE);
    }

    View.OnClickListener headerAttrsDeleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            headerAttrsCounter--;
            mHeaderAttributeslayout.removeView(mHeaderAttributeslayout.findViewById(view.getId()-3));
        }
    };

    private int headerAttrsCounter = 0;
    @OnClick(R.id.fab_button)
    void onAddHeaders(){
        Log.d(TAG,"adding header attrs");
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        LinearLayout headerRowLayout = (LinearLayout) inflater.inflate(R.layout.header_row_layout, mHeaderAttributeslayout, false);
        headerRowLayout.setId(++headerAttrsCounter*startIdHeaderAttrs);
        for (int i=0; i<headerRowLayout.getChildCount(); i++){
            headerRowLayout.getChildAt(i).setId(headerAttrsCounter*startIdHeaderAttrs+i+1);
            if (i==2) headerRowLayout.getChildAt(i).setOnClickListener(headerAttrsDeleteListener);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        headerRowLayout.setLayoutParams(params);
        mHeaderAttributeslayout.addView(headerRowLayout);
    }

    public static Fragment newInstance() {
        return new SubmitRequestFragment();
    }
}
