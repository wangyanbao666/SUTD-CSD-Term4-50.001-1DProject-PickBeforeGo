package com.example.PickBeforeGo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.PickBeforeGo.R;

public class InStockAvailabilityFragment extends Fragment {

    public InStockAvailabilityFragment() {
        // Required empty public constructor
    }

    public static InStockAvailabilityFragment newInstance() {
        InStockAvailabilityFragment fragment = new InStockAvailabilityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_availability, container, false);
    }
}