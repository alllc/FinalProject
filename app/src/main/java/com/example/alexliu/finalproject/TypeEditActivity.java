package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;


/**
 * Created by alexliu on 16-07-27.
 */
public class TypeEditActivity extends AppCompatActivity{
    public static final String DEFAULT = "not available";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeedit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_setting);

        TabHost thost = (TabHost) findViewById(R.id.tabhost);
        thost.setup();

        LayoutInflater.from(this).inflate(R.layout.activity_incomeedit, thost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.activity_expenseedit, thost.getTabContentView());

        thost.addTab(thost.newTabSpec("tab1").setIndicator("Income").setContent(R.id.tab01));
        thost.addTab(thost.newTabSpec("tab2").setIndicator("Expense").setContent(R.id.tab02));

        thost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {
                    Toast.makeText(TypeEditActivity.this, "Income", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab2")) {
                    Toast.makeText(TypeEditActivity.this, "Expense", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void LogOut (View view){
        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("loginUser", DEFAULT);
        editor.putString("loginPassword", DEFAULT);
        editor.commit();
        Toast.makeText(this,"user log out, loading to log in page",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



    //button click to open income page
    public void incomePage(View view){
        String input = "Income";
        Intent intent = new Intent(this, IncomeActivity.class);
        intent.putExtra("query",input);
        startActivity(intent);
    }
    //button click to open expense page
    public void expensePage(View view){
        String inputT = "Expense";
        Intent intent = new Intent(this, ExpenseActivity.class);
        intent.putExtra("query",inputT);
        startActivity(intent);
    }
    //button click to open input page
    public void inputPage(View view){
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }
    //button click to open result page
    public void resultPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //button click to open setting page
    public void settingPage(View view){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
