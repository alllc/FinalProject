package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by alexliu on 16-07-01.
 */
public class InputActivity extends AppCompatActivity implements LocationListener{
    EditText  moneyName,  moneyAmt, moneyDate;
    TextView textView;
    Spinner moneyInput,moneyType;
    String mInput,mType;
    MyDataBase db;
    ArrayAdapter adapterType, adapterincomeType,adapterexpenseType;
    String loginUser;
    public static final String DEFAULT = "not available";
    String[] incometypedata;
    byte[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_note);

        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        loginUser = sharedPrefs.getString("loginUser", DEFAULT);

        moneyInput = (Spinner)findViewById(R.id.inputSpinner);
        moneyName = (EditText)findViewById(R.id.nameEditTxt);
        moneyType = (Spinner)findViewById(R.id.typeSpinner);
        moneyAmt = (EditText)findViewById(R.id.amountEditTxt);
        moneyDate = (EditText)findViewById(R.id.dateEditTxt);
        textView = (TextView)findViewById(R.id.textView6);
        db = new MyDataBase(this);

        incometypedata = new String[]{"Interest", "Payment", "Others"};


//        adapterincomeType = ArrayAdapter.createFromResource(this,R.array.income_type,android.R.layout.simple_spinner_dropdown_item);
        adapterincomeType = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,incometypedata);
        adapterincomeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterexpenseType = ArrayAdapter.createFromResource(this,R.array.expense_type,android.R.layout.simple_spinner_dropdown_item);
        adapterexpenseType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        moneyInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String[] m = getResources().getStringArray(
                        R.array.input_type);
                mInput = m[position];
                //if income, then show income type, else show expense
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

        img = image(R.drawable.homegrey1);//测试用图。 应该是在这里改成相机的

        //gps

    }



    class SpinnerXMLSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long id) {
            mType = adapterType.getItem(position).toString();
//            textView.setText("The type u choice is：" + mInput + " " + mType);
            textView.setText(img.toString());
        }
        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }



    public void submitButton (View view)
    {
        String input = mInput;
        String name = moneyName.getText().toString();
        String type = mType;
        String amount = moneyAmt.getText().toString();
        String date = moneyDate.getText().toString();
        Toast.makeText(this, loginUser + input + name + type + amount + date, Toast.LENGTH_SHORT).show();


        long id = db.insertData(loginUser, input ,name, type, amount, date, img);
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

        moneyName.setText("");
        moneyAmt.setText("");
        moneyDate.setText("");
    }
    //图片
    public byte[] image(int id)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(id)).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();

    }

    //gps
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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
