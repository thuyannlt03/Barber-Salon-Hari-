package com.example.da_1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.da_1.LoginActivity;
import com.example.da_1.R;


public class OnboardingFragment2 extends Fragment {

    Button btngetStar;
    View mview;


    public OnboardingFragment2() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_onboarding2, container, false);
        btngetStar = mview.findViewById(R.id.btn_getStart);
        btngetStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return mview;
    }
}