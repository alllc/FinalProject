package com.example.alexliu.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alexliu on 16-07-01.
 */
public class InputActivity extends AppCompatActivity {
    EditText  moneyName,  moneyAmt, moneyDate;
    TextView textView;
    Spinner moneyInput,moneyType;
    String mInput,mType;
    MyDataBase db;
    ArrayAdapter adapterType, adapterincomeType,adapterexpenseType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        moneyInput = (Spinner)findViewById(R.id.inputSpinner);
        moneyName = (EditText)findViewById(R.id.nameEditTxt);
        moneyType = (Spinner)findViewById(R.id.typeSpinner);
        moneyAmt = (EditText)findViewById(R.id.amountEditTxt);
        moneyDate = (EditText)findViewById(R.id.dateEditTxt);
        textView = (TextView)findViewById(R.id.textView6);
        db = new MyDataBase(this);
        adapterincomeType = ArrayAdapter.createFromResource(this,R.array.income_type,android.R.layout.simple_spinner_dropdown_item);
        adapterincomeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterexpenseType = ArrayAdapter.createFromResource(this,R.array.expense_type,android.R.layout.simple_spinner_dropdown_item);
        adapterexpenseType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        moneyInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String[] m = getResources().getStringArray(
                        R.array.input_type);
                mInput = m[position];
                if(position == 0){
                    moneyType.setAdapter(adapterincomeType);
                    adapterType = adapterincomeType;
                }else if(position == 1){
                    moneyType.setAdapter(adapterexpenseType);
                    adapterType = adapterexpenseType;
                }
                textView.setText("The type u choice is：" + m[position]);//显示
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        moneyType.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
        moneyType.setVisibility(View.VISIBLE);


    }
    class SpinnerXMLSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long id) {
            textView.setText("The type u choice is：" + mInput + " " + adapterType.getItem(position));
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
//        class moneyInputListener implements AdapterView.OnItemSelectedListener{
//
//            public void onItemSelected(AdapterView<?> arg0,View arg1, int arg2, long arg3){
//
//            }
//
//        }
    public void submitButton (View view)
    {
        String input = mInput;
        String name = moneyName.getText().toString();
        String type = "null";
        String amount = moneyAmt.getText().toString();
        String date = moneyDate.getText().toString();
        Toast.makeText(this, input + name + type + amount + date, Toast.LENGTH_SHORT).show();
        long id = db.insertData(input, name, type, amount, date);
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
