package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Privacy extends AppCompatActivity {

    Button btn_accept;
    Button toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        btn_accept=(Button) findViewById(R.id.accept);



        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Privacy.this,MainActivity1.class);
                startActivity(in);
                Toast.makeText(Privacy.this,"You agreed to our Terms and Conditions.",Toast.LENGTH_LONG).show();
            }
        });
    }

}
