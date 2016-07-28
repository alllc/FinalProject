package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alexliu on 16-07-01.
 */
public class IncomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView myList;
    MyDataBase db;
    SimpleCursorAdapter myAdapter;
    String queryResult;
    public static final String DEFAULT = "not available";
    String name,type,amount,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        myList = (ListView)findViewById(R.id.listView);
        db = new MyDataBase(this);

        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String loginUser = sharedPrefs.getString("loginUser", DEFAULT);

        Intent intent = getIntent();
        Cursor cursor ;
        if(intent. hasExtra("query")){
            queryResult = intent.getStringExtra("query");
            cursor = db.getSelectedData(queryResult,loginUser);
        } else {
            cursor = db.getData();
        }

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = { Constants.NAME, Constants.TYPE, Constants.AMOUNT, Constants.DATE};
        int[] toViews = {R.id.nameEntry, R.id.typeEntry, R.id.amountEntry, R.id.dateEntry }; // The TextView in simple_list_item_1
        name = Constants.NAME;
        type = Constants.TYPE;
        amount = Constants.AMOUNT;
        date = Constants.DATE;
        myAdapter = new SimpleCursorAdapter(this, R.layout.list_row, cursor, fromColumns, toViews, 4);
        myList.setAdapter(myAdapter);
        myList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView plantNameTextView = (TextView) view.findViewById(R.id.nameEntry);
        TextView plantTypeTextView = (TextView) view.findViewById(R.id.typeEntry);
        TextView plantLocationTextView = (TextView) view.findViewById(R.id.amountEntry);
        TextView plantLatinTextView = (TextView) view.findViewById(R.id.dateEntry);
//      Toast.makeText(this, "row " + (1 + position) + ":  " + plantNameTextView.getText() + " " + plantTypeTextView.getText() + " " + plantLocationTextView.getText() + " " + plantLatinTextView.getText(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra("INPUT",queryResult);
//        intent.putExtra("NAME", name);
//        intent.putExtra("TYPE", type);
//        intent.putExtra("AMOUNT", amount);
//        intent.putExtra("DATE", date);
        intent.putExtra("INPUT",queryResult);
        intent.putExtra("NAME", plantNameTextView.getText());
        intent.putExtra("TYPE",plantTypeTextView.getText());
        intent.putExtra("AMOUNT", plantLocationTextView.getText());
        intent.putExtra("DATE", plantLatinTextView.getText());
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
        String input = "Expense";
        Intent intent = new Intent(this, ExpenseActivity.class);
        intent.putExtra("query",input);
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
