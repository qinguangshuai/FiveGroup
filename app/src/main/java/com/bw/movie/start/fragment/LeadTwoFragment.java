package com.bw.movie.start.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.start.StartActivity;

/**
 * date:2018/1/2    15:33
 * author:秦广帅(Lenovo)
 * fileName:AttCinemaAdapter
 */

public class LeadTwoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lead_two, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.Tiaozhuan3();
            }
        });
        return view;
    }

}
