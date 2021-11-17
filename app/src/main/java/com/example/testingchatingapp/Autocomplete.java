package com.example.testingchatingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class Autocomplete extends AppCompatActivity {
    Button date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Log.e("TAG", "onClick: " );
                DatePickerDialog datePickerDialog = new DatePickerDialog(Autocomplete.this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int month, int dayofMonth) {
                        int temp = month+1;
                        Log.e("TAG","onDateSet: " );
                        date.setText(i+"-"+(temp)+"-"+dayofMonth);
                    }
                }); 
                datePickerDialog.show();
            }
        });
    }
}