package com.example.myapplication1;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

   public class Asyncc extends AsyncTask<String, String, String> {

    Click uploadfunc;
    Context ctx;
    ProgressDialog dialog;
    ProgressBar progressBar;
    byte[] imagearray;
    int currentprogress=0;
    Bitmap image;
    Uri tempuri;

    public Asyncc(Context ctx, Uri dataBaos,ProgressBar progressBar) {
        this.ctx=ctx;


        uploadfunc= new Click();

        this.progressBar=progressBar;
        progressBar.setMax(100);

        //   dialog = new ProgressDialog(activity);
        //   dialog.setIndeterminate(false);
        // Progress dialog horizontal style
        //  dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //    this.imagearray=dataBaos;
        // this.image=dataBaos;

        this.tempuri=dataBaos;
    }


    @Override
    protected String doInBackground(String... voids) {


        FirebaseStorage storage;
        StorageReference storageRef;

        storage = FirebaseStorage.getInstance();


        storageRef = storage.getReference().child("" + new Date().getTime());

        UploadTask uploadTask = storageRef.putFile(tempuri);


//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // ...
//                Toast.makeText(uploadfunc, "Please Connect To The Internet", Toast.LENGTH_SHORT).show();
//
//
//
//            }
//        });





        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                System.out.println("Upload is " + progress + "% done");
                currentprogress = (int) progress;

                publishProgress(currentprogress+"");
                progressBar.setProgress(currentprogress);
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                System.out.println("Upload is paused");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ctx, "Please Connect To Internet", Toast.LENGTH_SHORT).show();
            }
        });

        //  uploadfunc.uploadphoto(tempuri);
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);

//        dialog.setMessage("Upoading... please wait.");
//        dialog.show();
    }


    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);


        progressBar.setVisibility(View.GONE);

        //   Utils.showToast(ctx,"Upload successful");


    }


    @Override
    protected void onProgressUpdate(String... values) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(Integer.parseInt(values[0]));
        Log.d("progg",values[0]+"");
        if(Integer.parseInt(values[0])==100)
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(ctx, "Upload Successful", Toast.LENGTH_SHORT).show();
            //
        }
        //super.onProgressUpdate(values);
    }
}
