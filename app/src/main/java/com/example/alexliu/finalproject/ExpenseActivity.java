package com.example.alexliu.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by alexliu on 16-07-01.
 */
public class ExpenseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView myList;
    MyDataBase db;
    SimpleCursorAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        myList = (ListView)findViewById(R.id.listView);
        db = new MyDataBase(this);

        Intent intent = getIntent();
        Cursor cursor = null;
        if(intent. hasExtra("query")){
            String queryResult = intent.getStringExtra("query");
            cursor = db.getSelectedData(queryResult);
        }
        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = { Constants.NAME, Constants.TYPE, Constants.AMOUNT, Constants.DATE};
        int[] toViews = {R.id.plantNameEntry, R.id.plantTypeEntry, R.id.plantLocationEntry, R.id.plantLatinEntry }; // The TextView in simple_list_item_1

        myAdapter = new SimpleCursorAdapter(this, R.layout.list_row, cursor, fromColumns, toViews, 4);
        myList.setAdapter(myAdapter);
        myList.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView plantNameTextView = (TextView) view.findViewById(R.id.plantNameEntry);
        TextView plantTypeTextView = (TextView) view.findViewById(R.id.plantTypeEntry);
        TextView plantLocationTextView = (TextView) view.findViewById(R.id.plantLocationEntry);
        TextView plantLatinTextView = (TextView) view.findViewById(R.id.plantLatinEntry);
//      Toast.makeText(this, "row " + (1 + position) + ":  " + plantNameTextView.getText() + " " + plantTypeTextView.getText() + " " + plantLocationTextView.getText() + " " + plantLatinTextView.getText(), Toast.LENGTH_LONG).show();
        }

    //button click to open income page
    public void incomePage(View view){
        String input = "Income";
        Intent intent = new Intent(this, ExpenseActivity.class);
        intent.putExtra("query",input);
        startActivity(intent);
    }
    //button click to open expense page
    public void expensePage(View view){
        String inputT = "Expense";
        Intent intent = new Intent(this, MoneyListViewActivity.class);
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
