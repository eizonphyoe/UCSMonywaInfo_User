package com.admin.ucsmonywa.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.adapter.NoticeBoardAdapter;
import com.admin.ucsmonywa.model.ImageUploadInfo;
import com.admin.ucsmonywa.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardDetail extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView title,detail, toolbar_title,date;
    String mTitle,mDetail,mImage,mDate;

    FloatingActionButton delete;

    private boolean isHideToolbarView = false;

    List<ImageUploadInfo> imageUploadInfoList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticeboard_detail);

        toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);

        imageView=findViewById(R.id.image);
        title = findViewById(R.id.title);
        detail=findViewById(R.id.detail);
        toolbar_title = findViewById(R.id.title_on_appbar);
        date=findViewById(R.id.date);

        final Intent intent =  getIntent();
        if (intent != null) {
            mTitle = intent.getStringExtra("title");
            mDetail = intent.getStringExtra("detail");
            mImage = intent.getStringExtra("img");
            mDate = intent.getStringExtra("date");
        }

        if (mTitle == null) mTitle = "";
        if (mDetail == null) mDetail = "";
        if (mImage == null) mImage = "";
        if (mDate == null) mDate = "";

        Log.d("Details",mDetail);

        title.setText(mTitle);
        detail.setText(mDetail);

        if (!mDate.isEmpty()) {
            date.setText(Utils.DateFormat(mDate));
        }
        toolbar_title.setText(mTitle);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawableColor());
        requestOptions.error(Utils.getRandomDrawableColor());
        requestOptions.centerCrop();
        
        Glide.with(this)
                .load(mImage)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        delete=findViewById(R.id.delete_fab);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent == null || mTitle == null || mTitle.isEmpty()) {
                    return;
                }

                // Delete by matching the current item's data instead of using position
                final ImageUploadInfo info = new ImageUploadInfo(mTitle, mDetail, mImage, mDate);


                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
                        Query query1=ref.child("noticeboard").orderByChild("imageTitle").equalTo(info.getImageTitle());
                        Query query2=ref.child("noticeboard").orderByChild("imageDetail").equalTo(info.getImageDetail());
                        Query query3=ref.child("noticeboard").orderByChild("imageUrl").equalTo(info.getImageUrl());
                        Query query4=ref.child("noticeboard").orderByChild("date").equalTo(info.getDate());

                        query1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                                    snapshot.getRef().removeValue();

                                    Log.e("SnapShot","Done!");

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        query2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                                    snapshot.getRef().removeValue();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        query3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                                    snapshot.getRef().removeValue();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                query4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                            snapshot.getRef().removeValue();

                            Log.e("SnapShot","Done!");

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
                });
            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(i) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            toolbar_title.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbar_title.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }

    }
}
