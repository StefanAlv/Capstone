package com.example.karsa.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.karsa.InputSampelActivity;
import com.example.karsa.R;

public class ProfileFragment extends Fragment {

    ImageView btnInputSampel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btnInputSampel = view.findViewById(R.id.btnInputSampel);

        btnInputSampel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InputSampelActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}