package com.example.myapplication1;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

}
