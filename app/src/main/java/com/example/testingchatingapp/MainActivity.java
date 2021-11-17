package com.example.testingchatingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
    EditText firstname;
    EditText middlename;
    EditText lastname;
    EditText dd;
    EditText mm;
    EditText yyyy;
    RadioGroup rg1;
    RadioButton male;
    RadioButton female;
    RadioButton other;
    EditText mail;
    EditText password;
    EditText confirm;
    CheckBox turms;
    boolean like;
    String radiogroup;
    Button submit;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.et_first_name);
        middlename = findViewById(R.id.et_middle_name);
        lastname = findViewById(R.id.et_last_name);
        dd = findViewById(R.id.et_dd);
        mm = findViewById(R.id.et_mm);
        rg1 = findViewById(R.id.rg);
        yyyy = findViewById(R.id.et_yyyy);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);
        mail = findViewById(R.id.et_mailid);
        password = findViewById(R.id.et_password);
        confirm = findViewById(R.id.et_confirm_password);
        turms = findViewById(R.id.turms);
        submit = findViewById(R.id.btn_submit);
        preferences = getSharedPreferences("MyappName",MODE_PRIVATE);
        editor = preferences.edit();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject userdetails = new JSONObject();
                JSONObject userlist =new JSONObject();
                if (firstname.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "first name should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }if (middlename.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "middle name should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }if (lastname.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "last name should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }if (dd.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, " dd should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }if (mm.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, " mm should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }if (yyyy.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "yyyy should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }if (mail.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Email should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }if (password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "password should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }if (confirm.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "confirm password should not empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().equals(confirm.getText().toString())){
                    Toast.makeText(MainActivity.this, "password matched", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "password missmatched", Toast.LENGTH_SHORT).show();
                }
                try {
                    userdetails.put("firstname",firstname.getText().toString());
                    userdetails.put("middlename",middlename.getText().toString());
                    userdetails.put("lastname",lastname.getText().toString());
                    userdetails.put("dob",dd.getText().toString() + "/" + mm.getText().toString() +"/" + yyyy.getText().toString());
                    userdetails.put("password",password.getText().toString());
                    if (userlist.has(mail.getText().toString())){
                        Toast.makeText(MainActivity.this, "User Already Exits", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        userlist = new JSONObject(preferences.getString("userlist","{}"));
                        userlist.put(mail.getText().toString(),userdetails);
                        editor.putString("userlist",userlist.toString());
                        editor.commit();
                    }
                    Log.e("TAG",userlist.toString());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        turms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    like = true;
                } else {
                    Log.e("MainActivity", "check");
                    like = false;
                }
            }
        });

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.male){
                    Log.e("TAG","male");
                    radiogroup = "male";
                }if (i == R.id.female){
                    Log.e("TAG","female");
                    radiogroup = "male";
                }if (i == R.id.other){
                    Log.e("TAG","other");
                    radiogroup = "other";
                }
            }
        });




    }
}