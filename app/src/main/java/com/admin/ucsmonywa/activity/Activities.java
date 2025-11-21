package com.admin.ucsmonywa.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.constants.AppConstants;
import com.admin.ucsmonywa.model.ActivityInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Activities extends AppCompatActivity {

    public String Storage_Path="activityimg/";

    //Root Database Name for Firebase Database
    public static final String Database_Path="activities";

    Button btnUpload,btnAdd;
    TextInputEditText imgTitle,imgDetail;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST=71;

    int Image_Request_Code=7;

    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_noticeboard);

        btnUpload=findViewById(R.id.btn_upload);
        btnAdd=findViewById(R.id.btn_add);
        imageView=findViewById(R.id.image_view);


        //for edit text
        imgTitle=findViewById(R.id.edit_text_title);
        imgDetail=findViewById(R.id.edit_text_detail);





        progressDialog=new ProgressDialog(Activities.this);

        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference().child(AppConstants.Firebase.ACTIVITIES_PATH);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

    }
    public void chooseImage(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data!=null && data.getData() !=null){
            filePath=data.getData();
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void  add(){
        if(filePath != null){
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Uploading....");
            progressDialog.show();


            final StorageReference storageReference1=storageReference.child(AppConstants.Firebase.STORAGE_ACTIVITY_IMAGES_PATH+System.currentTimeMillis()+"."+GetFileExtension(filePath));
            UploadTask uploadTask=storageReference1.putFile(filePath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();
                            String ImageTitle=imgTitle.getText().toString().trim();

                            String Detail=imgDetail.getText().toString().trim();

                            progressDialog.dismiss();
                            Toast.makeText(Activities.this,"Uploaded",Toast.LENGTH_LONG).show();

                            ActivityInfo activityInfo=new ActivityInfo(uri.toString(),ImageTitle,Detail);
                            String activityInfoId=databaseReference.push().getKey();
                            databaseReference.child(activityInfoId).setValue(activityInfo);

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+(int)progress+"%");
                }
            });
        }

    }
    public String GetFileExtension(Uri uri){
        ContentResolver contentResolver=getContentResolver();

        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}
