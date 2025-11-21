package com.admin.ucsmonywa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.base.BaseActivity;
import com.admin.ucsmonywa.constants.AppConstants;
import com.admin.ucsmonywa.utils.ImageLoader;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * Activity to display campus activity details
 */
public class ActivityDetail extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView title, detail, toolbar_title;
    private String mTitle, mDetail, mImage;

    private boolean isHideToolbarView = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticeboard_detail);

        initViews();
        loadIntentData();
        displayData();
    }
    
    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);

        imageView = findViewById(R.id.image);
        title = findViewById(R.id.title);
        detail = findViewById(R.id.detail);
        toolbar_title = findViewById(R.id.title_on_appbar);
    }
    
    private void loadIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            mTitle = intent.getStringExtra(AppConstants.IntentKeys.TITLE);
            mDetail = intent.getStringExtra(AppConstants.IntentKeys.DETAIL);
            mImage = intent.getStringExtra(AppConstants.IntentKeys.IMAGE);
        }

        if (mTitle == null) mTitle = "";
        if (mDetail == null) mDetail = "";
        if (mImage == null) mImage = "";

        Log.d("Details", mDetail);
    }
    
    private void displayData() {
        title.setText(mTitle);
        detail.setText(mDetail);
        toolbar_title.setText(mTitle);

        ImageLoader.loadImageCenterCrop(imageView, mImage);
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
