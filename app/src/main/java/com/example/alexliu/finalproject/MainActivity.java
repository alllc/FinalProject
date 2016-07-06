package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String DEFAULT = "not available";
    TextView incomeNumTxt, expenseNumTxt, resultNumTxt;
    MyDataBase db;
    Cursor c_income, c_expense;
    int sum_income, sum_expense, total;
//    int sum = c.getInt(c.getColumnIndex("sum"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incomeNumTxt = (TextView)findViewById(R.id.incomeNumTxt);
        expenseNumTxt = (TextView)findViewById(R.id.expenseNumTxt);
        resultNumTxt = (TextView)findViewById(R.id.resultNumTxt);
        db = new MyDataBase(this);

        c_income = db.query_income();
        if (c_income != null && c_income.getCount() > 0) {
            if (c_income.moveToFirst()){
                sum_income = c_income.getInt(0);
            }else {
                sum_income = -1;
            }
        }else {
            sum_income = -2;
        }
        incomeNumTxt.setText(" $ "+sum_income);

        c_expense = db.query_expense();
        if (c_expense != null && c_expense.getCount() > 0) {
            if (c_expense.moveToFirst()){
                sum_expense = c_expense.getInt(0);
            }else {
                sum_expense = -1;
            }
        }else {
            sum_expense = -2;
        }
        expenseNumTxt.setText(" $ "+sum_expense);

        total = sum_income - sum_expense;
        resultNumTxt.setText(" $ "+total);

        //check if there is a data
        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String loginUser = sharedPrefs.getString("loginUser", DEFAULT);
        String loginPassword = sharedPrefs.getString("loginPassword", DEFAULT);
        //check if there is a data on user
        Toast.makeText(this,"loading to log in page",Toast.LENGTH_LONG).show();
        if(loginUser.equals(DEFAULT) && loginPassword.equals(DEFAULT)){
            Toast.makeText(this,"No user log in, loading to log in page",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
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
