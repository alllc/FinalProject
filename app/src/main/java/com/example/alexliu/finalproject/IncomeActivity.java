package com.example.alexliu.finalproject;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        myList = (ListView)findViewById(R.id.listView);
        db = new MyDataBase(this);


        Intent intent = getIntent();
        Cursor cursor = null;

        if(intent.hasExtra("query")){
            String queryResult = intent.getStringExtra("query");
            cursor = db.getSelectedData(queryResult);
        } else {
            cursor = db.getData();
        }

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {Constants.NAME, Constants.TYPE, Constants.AMOUNT, Constants.DATE};
        int[] toViews = {R.id.nameEntry, R.id.typeEntry, R.id.amountEntry, R.id.dateEntry }; // The TextView in simple_list_item_1


        myAdapter = new SimpleCursorAdapter(this, R.layout.list_row, cursor, fromColumns, toViews, 4);
        myList.setAdapter(myAdapter);
        myList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LinearLayout clickedRow = (LinearLayout) view;
        TextView nameTextView = (TextView) view.findViewById(R.id.nameEntry);
        TextView typeTextView = (TextView) view.findViewById(R.id.typeEntry);
        TextView amountTextView = (TextView) view.findViewById(R.id.amountEntry);
        TextView dateTextView = (TextView) view.findViewById(R.id.dateEntry);
        Toast.makeText(this, "row " + (1+position) + ":  " + nameTextView.getText() +" "+ typeTextView.getText()+" "+ amountTextView.getText()+" "+ dateTextView.getText(), Toast.LENGTH_LONG).show();

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
