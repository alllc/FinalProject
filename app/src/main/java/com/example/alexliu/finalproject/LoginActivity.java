package com.example.alexliu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by alexliu on 16-06-30.
 */
public class LoginActivity extends AppCompatActivity{

    EditText userEdit,passwordEdit;
    String u, p;
    public static final String DEFAULT = "not available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEdit = (EditText)findViewById(R.id.userNameEditTxt);
        passwordEdit = (EditText)findViewById(R.id.passWordEditTxt);
    }
    public void login(View view) {

        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = sharedPrefs.getString("username", DEFAULT);
        String password = sharedPrefs.getString("password", DEFAULT);
        u = userEdit.getText().toString();
        p = passwordEdit.getText().toString();
        if (username.equals(u) && password.equals(p)) {
            Toast.makeText(this, "Welcome back" + username + " " + password, Toast.LENGTH_LONG).show();
            String loginU = sharedPrefs.getString("loginUser",DEFAULT);
            String loginP = sharedPrefs.getString("loginPassword",DEFAULT);
            if (loginU.equals(DEFAULT) && loginP.equals(DEFAULT)) {
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("loginUser", u);
                editor.putString("loginPassword", p);
                editor.commit();
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
            }
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("loginUser", DEFAULT);
            editor.putString("loginPassword", DEFAULT);
            editor.commit();
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
