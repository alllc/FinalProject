package com.example.alexliu.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by alexliu on 16-07-01.
 */
public class InputActivity extends AppCompatActivity {
    EditText  moneyName,  moneyAmt, moneyDate, selectType;
    Spinner moneyInput,moneyType;
    MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        moneyInput = (Spinner)findViewById(R.id.inputSpinner);
        moneyName = (EditText)findViewById(R.id.nameEditTxt);
        moneyType = (Spinner)findViewById(R.id.typeSpinner);
        moneyAmt = (EditText)findViewById(R.id.amountEditTxt);
        moneyDate = (EditText)findViewById(R.id.dateEditTxt);

        db = new MyDataBase(this);

    }
    public void submitButton (View view)
    {
      //  String input = moneyInput.
        String name = moneyName.getText().toString();
      //  String type = moneyType.getText().toString();
        String amount = moneyAmt.getText().toString();
        String date = moneyDate.getText().toString();
        Toast.makeText(this, name + type + amount + date, Toast.LENGTH_SHORT).show();
        long id = db.insertData(name, type, amount, date);
        if (id < 0)
        {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

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
