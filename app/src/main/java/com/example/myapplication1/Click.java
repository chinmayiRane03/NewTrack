package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.filter.entity.ImageFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.vincent.filepicker.activity.ImagePickActivity.IS_NEED_CAMERA;

public class Click extends AppCompatActivity implements View.OnClickListener {

    ImageView i1;
    CircleImageView i2;
    Button bt1;
    EditText et1, et2;
    Spinner sp;
    Uri photoURI;
    Asyncc progress;
    Uri tempUri;
    private Uri uri;

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int REQUEST_LOCATION = 1;
    private static final String TAG = "CapturePicture";
    static final int REQUEST_PICTURE_CAPTURE = 1;
    private ImageView image;
    private String pictureFilePath;
    private String deviceIdentifier;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    Bitmap imageBitmap;
    byte[] dataBAOS;
    int rresultCode;
    DatabaseReference databaseReference, databaseReference1;
    public String zoneImageURI = null;

    File mPhotoFile;
    String ZoneTitle;
    String uid;

    LocationManager locationManager;
    String lattitude, longitude;
    public String Lat, Logg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        i1 = findViewById(R.id.imageView1_markzone);
        i2 = findViewById(R.id.image);
        bt1 = findViewById(R.id.button_markzone);
        progressBar = findViewById(R.id.progressBar);



        // mProgress = new ProgressDialog(this);
        //  mProgress=(ProgressBar)findViewById(R.id.progressBar);

        bt1.setOnClickListener(this);
        bt1.setClickable(false);
        bt1.setBackgroundColor(getResources().getColor(R.color.grey_100));
        i1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        if (view == bt1) {
//            final String ZoneData = et1.getText().toString().trim();
            //          final String ZoneSolution = et2.getText().toString().trim();
            //        final String ZoneImage = zoneImageURI;


//
            //  mProgress.setMessage("Uploading your Status...");

            progress = new Asyncc(this, photoURI, progressBar);
            progress.execute();
            bt1.setClickable(false);
            bt1.setBackgroundColor(getResources().getColor(R.color.grey_100));

            //   progressBar.setVisibility(View.GONE);

            //   uploadphoto();
        }


        if (view == i1) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                // Bundle extras = new Bundle();


                //Comment this portion for thumbnail upload

                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    // Error occurred while creating the File
                }
                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(this,
                            BuildConfig.APPLICATION_ID + ".provider",
                            photoFile);

                    mPhotoFile = photoFile;
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                    //  startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);


                     startActivityForResult(intent, CAMERA_REQUEST_CODE);

                }


            }
        }
    }


    //   Log.d("bataBAOS0", String.valueOf(dataBAOS[0]));


    public void uploadphoto (Uri dataBAOS){

        FirebaseStorage storage;
        StorageReference storageRef;
        storage = FirebaseStorage.getInstance();


        // progress.onPreExecute();

        //    byte[] data = baos.toByteArray();

        storageRef = storage.getReference().child("" + new Date().getTime());

        UploadTask uploadTask = storageRef.putFile(dataBAOS);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...


            }
        });
        //   mProgress.dismiss();
        // progress.onPostExecute(null);

    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            // Bundle extras = data.getExtras();
            //imageBitmap = (Bitmap) extras.get("data");

            //  tempUri = data.getData();
            //   mSelectImage.setImageURI(mImageUri);
            //tempUri = getImageUri(getApplicationContext(), imageBitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //compress size commented

            //     imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            //     dataBAOS = baos.toByteArray();

            rresultCode = resultCode;

            i2.setImageURI(photoURI);


            // i2.setImageBitmap(imageBitmap);

            bt1.setClickable(true);
            bt1.setBackgroundColor(getResources().getColor(R.color.blue400));

            super.onActivityResult(requestCode, resultCode, data);

            //  super.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode==Constant.REQUEST_CODE_PICK_IMAGE)
        {
            if (resultCode == RESULT_OK) {
                ArrayList<ImageFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
                Log.d("LISTTTT",list.get(0).toString());

                // Uri path = Uri.fromFile(list.get(0));

                //Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(list.get(0)));
                Log.d(  "pathhh",list.get(0).getPath());
                photoURI= Uri.fromFile(new File(list.get(0).getPath()));

                i2.setImageURI(Uri.fromFile(new File(list.get(0).getPath())));
                bt1.setClickable(true);
                bt1.setBackgroundColor(getResources().getColor(R.color.blue400));

                //i2.setImageDrawable(Drawable.createFromPath(list.get(0).toString()));

            }
        }
    }


    private File createImageFile () throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        return mFile;
    }


    public void attach_file(View view) {

        Intent intent1 = new Intent(this, ImagePickActivity.class);
        intent1.putExtra(IS_NEED_CAMERA, true);
        intent1.putExtra(Constant.MAX_NUMBER, 1);
        startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

