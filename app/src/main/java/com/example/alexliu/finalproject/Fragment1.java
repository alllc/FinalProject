package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fragment1 extends Fragment {
    private View tab1view;
    ListView incomelist;
    TextView testview;
    EditText tyname;
    String loginUser;
    public static final String DEFAULT = "not available";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        tab1view=inflater.inflate(R.layout.activity_incomeedit, container, false);
        return tab1view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        incomelist = (ListView) this.getView().findViewById(R.id.listView2);
        SharedPreferences sharedPrefs = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        loginUser = sharedPrefs.getString("loginUser", DEFAULT);
        Set<String> incometypedata = sharedPrefs.getStringSet(loginUser.toString()+"incomelist",new HashSet<String>());
        List<String> list = new ArrayList<String>(incometypedata);

        tyname = (EditText)tab1view.findViewById(R.id.tyname);

        incomelist.setAdapter(new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,list));

        Button button=(Button)tab1view.findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击了我", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getActivity(),AddActivity.class);
                startActivity(intent);
            }
        });
    }


}