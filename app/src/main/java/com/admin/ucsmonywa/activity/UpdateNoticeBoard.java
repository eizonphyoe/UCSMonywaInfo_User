package com.admin.ucsmonywa.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.admin.ucsmonywa.R;

public class UpdateNoticeBoard extends AppCompatActivity {
    EditText editTextTitle,editTextDetail;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_noticeboard);

        editTextTitle=findViewById(R.id.delete_title);
        editTextDetail=findViewById(R.id.delete_detail);
        imageView=findViewById(R.id.delete_image);


        Intent intent = this.getIntent();
        final Bundle bundle = intent.getExtras();

        editTextTitle.setText((String) bundle.getSerializable("title"));
        editTextDetail.setText((String)bundle.getSerializable("detail"));
       // imageView.setImageResource((int)(intent.get.getSerializable("url")));

    }
}
