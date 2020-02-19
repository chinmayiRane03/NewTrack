package com.example.myapplication1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginTOP extends AppCompatActivity {

    Button btn_login;
    TextView textBack;
    TextView textRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_top);

        textBack=(TextView) findViewById(R.id.back);
        btn_login=(Button) findViewById(R.id.login1);
        textRegister=(TextView) findViewById(R.id.regis);


        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(LoginTOP.this,Register.class);
                startActivity(in);
            }
        });

        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(LoginTOP.this,MainActivity.class);
                startActivity(in);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(LoginTOP.this,MainActivity1.class);
                startActivity(in);
            }
        });
    }
}


