package com.example.alexliu.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by alexliu on 16-07-01.
 */
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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
