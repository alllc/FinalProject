package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDataBase db;
    public static final String DEFAULT = "not available";
    private TextView incomeNumView;
    Cursor c;

//    int sum = c.getInt(c.getColumnIndex("sum"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView incomeNumView = (TextView)findViewById(R.id.incomeNumTxt);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDataBase(this);

//        //c= db.rawQuery("SELECT SUM(amount) FROM income_table", null);
//        if (c.moveToFirst()){
//            sum = c.getInt(0);
//        } else {
//            sum = -1;
//        }
//        c.close();
//        incomeNumView.setText(""+sum);

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
