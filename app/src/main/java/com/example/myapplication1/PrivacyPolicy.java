package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PrivacyPolicy extends AppCompatActivity {

    PDFView policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);


        policy=(PDFView) findViewById(R.id.pdfpolicy);

        policy.fromAsset("PrivacyPolicy.pdf").load();

        //Random comment
    }
}
