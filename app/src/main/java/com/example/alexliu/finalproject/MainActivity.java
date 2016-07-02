package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String DEFAULT = "not available";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if there is a data
        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String loginUser = sharedPrefs.getString("loginUser", DEFAULT);
        String loginPassword = sharedPrefs.getString("loginPassword", DEFAULT);
        //check if there is a data
//        Toast.makeText(this,"loading to log in page",Toast.LENGTH_LONG).show();
//        if(loginUser.equals(DEFAULT) && loginPassword.equals(DEFAULT)){
//            Toast.makeText(this,"No user log in, loading to log in page",Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//        }
    }
    //button click to open income page
    public void incomePage(View view){
        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
    }
    //button click to open expense page
    public void expensePage(View view){
        Intent intent = new Intent(this, ExpenseActivity.class);
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
