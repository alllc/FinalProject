package com.example.alexliu.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
public class Fragment2 extends Fragment {
    private View tab2view;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,  Bundle savedInstanceState) {
        tab2view=inflater.inflate(R.layout.activity_expenseedit, container, false);
        Button button=(Button)tab2view.findViewById(R.id.button2);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "点击了我", Toast.LENGTH_SHORT).show();
            }
        });
        return tab2view;
    }
}