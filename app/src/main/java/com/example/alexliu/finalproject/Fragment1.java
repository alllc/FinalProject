package com.example.alexliu.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class Fragment1 extends Fragment {
    private View tab1view;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,  Bundle savedInstanceState) {
        tab1view=inflater.inflate(R.layout.activity_incomeedit, container, false);
        return tab1view;
    }
}