package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    Button btn_register;
    TextView textBack;
    TextView textLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_register =(Button) findViewById(R.id.register);
        textBack=(TextView) findViewById(R.id.back);
        textLog=(TextView) findViewById(R.id.log);

        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Register.this,MainActivity.class);
                startActivity(in);
            }
        });

        textLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Register.this,LoginTOP.class);
                startActivity(in);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Register.this,Privacy.class);
                startActivity(in);
            }
        });
    }
}
