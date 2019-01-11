package com.bw.movie.start.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.ShowActivity;
import com.bw.movie.login.LoginActivity;

/**
 * date:2018/1/2    15:33
 * author:秦广帅(Lenovo)
 * fileName:AttCinemaAdapter
 */

public class LeadFourFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lead_four, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ShowActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}