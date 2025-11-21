package com.admin.ucsmonywa.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.admin.ucsmonywa.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

/**
 * Utility class for image loading operations
 * Centralizes Glide configuration and usage
 */
public class ImageLoader {
    
    /**
     * Load image with standard options (no placeholder to avoid loading indicator issue)
     */
    public static void loadImage(ImageView imageView, String url) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        
        RequestOptions options = new RequestOptions()
            .error(R.mipmap.ic_launcher);
        
        Glide.with(imageView.getContext())
            .load(url)
            .apply(options)
            .listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    // On failure, just use error drawable
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    // Image loaded successfully, clear any background
                    imageView.setBackground(null);
                    return false;
                }
            })
            .into(imageView);
    }
    
    /**
     * Load image with center crop for detail views
     */
    public static void loadImageCenterCrop(ImageView imageView, String url) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        
        RequestOptions options = new RequestOptions()
            .error(R.mipmap.ic_launcher)
            .centerCrop();
        
        Glide.with(imageView.getContext())
            .load(url)
            .apply(options)
            .listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    imageView.setBackground(null);
                    return false;
                }
            })
            .into(imageView);
    }
    
    /**
     * Clear image cache for memory management
     */
    public static void clearMemory(android.content.Context context) {
        if (context != null) {
            Glide.get(context).clearMemory();
        }
    }
}
