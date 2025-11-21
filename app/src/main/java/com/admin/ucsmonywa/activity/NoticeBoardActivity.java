package com.admin.ucsmonywa.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.base.BaseActivity;
import com.admin.ucsmonywa.constants.AppConstants;
import com.admin.ucsmonywa.model.ImageUploadInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Activity for creating and uploading notice board items
 */
public class NoticeBoardActivity extends BaseActivity {

    private EditText imgTitle, detail;
    private ImageView imageView;
    private Uri filePath;
    
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_noticeboard);
        
        initViews();
        initFirebase();
        setupListeners();
    }
    
    private void initViews() {
        Button btnUpload = findViewById(R.id.btn_upload);
        Button btnAdd = findViewById(R.id.btn_add);
        imageView = findViewById(R.id.image_view);
        imgTitle = findViewById(R.id.edit_text_title);
        detail = findViewById(R.id.edit_text_detail);
        
        btnUpload.setOnClickListener(v -> chooseImage());
        btnAdd.setOnClickListener(v -> uploadNotice());
    }
    
    private void initFirebase() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseReference = FirebaseDatabase.getInstance()
            .getReference()
            .child(AppConstants.Firebase.NOTICEBOARD_PATH);
    }
    
    private void setupListeners() {
        // Listeners already set in initViews()
    }

    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, 
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, AppConstants.RequestCodes.PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppConstants.RequestCodes.PICK_IMAGE 
                && resultCode == RESULT_OK 
                && data != null 
                && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                showToast("Failed to load image");
            }
        }
    }

    private void uploadNotice() {
        String title = imgTitle.getText().toString().trim();
        String detailText = detail.getText().toString().trim();
        
        if (!validateInput(title, detailText)) {
            return;
        }
        
        if (filePath == null) {
            showToast("Please select an image");
            return;
        }

        showProgress(AppConstants.Messages.UPLOADING);

        String fileName = AppConstants.Firebase.STORAGE_IMAGES_PATH 
            + System.currentTimeMillis() 
            + "." + getFileExtension(filePath);
            
        final StorageReference fileRef = storageReference.child(fileName);
        
        fileRef.putFile(filePath)
            .addOnSuccessListener(taskSnapshot -> 
                fileRef.getDownloadUrl().addOnSuccessListener(uri -> 
                    saveToDatabase(title, detailText, uri.toString())))
            .addOnFailureListener(e -> {
                hideProgress();
                showToast("Upload failed: " + e.getMessage());
            })
            .addOnProgressListener(taskSnapshot -> {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() 
                    / taskSnapshot.getTotalByteCount());
                showProgress(AppConstants.Messages.UPLOADING + " " + (int) progress + "%");
            });
    }
    
    private boolean validateInput(String title, String detail) {
        if (TextUtils.isEmpty(title)) {
            imgTitle.setError("Title required");
            imgTitle.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(detail)) {
            this.detail.setError("Detail required");
            this.detail.requestFocus();
            return false;
        }
        return true;
    }
    
    private void saveToDatabase(String title, String detail, String imageUrl) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy / MM / dd", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());

        ImageUploadInfo imageUploadInfo = new ImageUploadInfo(title, detail, imageUrl, date);
        String imageUploadId = databaseReference.push().getKey();
        
        if (imageUploadId != null) {
            databaseReference.child(imageUploadId).setValue(imageUploadInfo)
                .addOnSuccessListener(aVoid -> {
                    hideProgress();
                    showToast(AppConstants.Messages.UPLOADED);
                    finish();
                })
                .addOnFailureListener(e -> {
                    hideProgress();
                    showToast("Failed to save: " + e.getMessage());
                });
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
