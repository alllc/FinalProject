package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fragment2 extends Fragment {
    private View tab2view;
    ListView incomelist;
    TextView testview;
    String loginUser;
    public static final String DEFAULT = "not available";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        tab2view = inflater.inflate(R.layout.activity_expenseedit, container, false);
        return tab2view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        incomelist = (ListView) this.getView().findViewById(R.id.listView2);
        SharedPreferences sharedPrefs = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        loginUser = sharedPrefs.getString("loginUser", DEFAULT);
        Set<String> expensetypedata = sharedPrefs.getStringSet(loginUser.toString()+"expenselist",new HashSet<String>());
        List<String> list = new ArrayList<String>(expensetypedata);


        incomelist.setAdapter(new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,list));

    }
}