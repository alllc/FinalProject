package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alexliu on 16-06-30.
 */
public class RegisterActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    List<String> namelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_signup);

        usernameEditText = (EditText)findViewById(R.id.userEditText);
        passwordEditText = (EditText)findViewById(R.id.editText);

        List<String> namelist = new ArrayList<String>();

    }
    public void register (View view){
        namelist.add(usernameEditText.getText().toString());
        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(namelist);
        editor.putStringSet("userlist", set);
//        editor.putString("username", usernameEditText.getText().toString());
//        editor.putString("password", passwordEditText.getText().toString());
        Toast.makeText(this, "Username and password saved to Preferences", Toast.LENGTH_LONG).show();
        editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
