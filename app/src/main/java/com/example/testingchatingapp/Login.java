package com.example.testingchatingapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText mobile;
    EditText password;
    Button login;
    TextView forget_pw;
    Button create_new;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobile = findViewById(R.id.et_Mobile);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        forget_pw = findViewById(R.id.tv_forget_password);
        create_new = findViewById(R.id.btn_create_new);
        preferences = getSharedPreferences("MyappName" + "",MODE_PRIVATE);
        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_register = new Intent(Login.this,MainActivity.class);
                startActivity(new_register);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Mobile no should not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                        Toast.makeText(Login.this, "password should not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                try {
                    JSONObject userlist = new JSONObject(preferences.getString("userlist","{}"));
                    Log.e("TAG",userlist.toString());
                    if (userlist.has(mobile.getText().toString())) {
                        Toast.makeText(Login.this, "user exits in list", Toast.LENGTH_SHORT).show();
                        JSONObject userdetails = new JSONObject();

                        userdetails = userlist.getJSONObject(mobile.getText().toString());
                        String pwd = userdetails.getString("password");
                        String pw = password.getText().toString();
                        if (pw.equals(pwd)) {
                            Intent pass = new Intent(Login.this, Autocomplete.class);
                            startActivity(pass);
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Password is incorrect , try new", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("TAG", "+password");
                        Log.e("TAG", "+userid");
                    }
                    else{
                        Toast.makeText(Login.this, "Userlist not exists in list", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}