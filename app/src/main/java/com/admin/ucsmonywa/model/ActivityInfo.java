package com.admin.ucsmonywa.model;

import androidx.annotation.NonNull;

/**
 * Model class for campus activities
 * Represents an activity with image, title and details
 */
public class ActivityInfo {
    private String image;
    private String imageTitle;
    private String imageDetail;

    /**
     * Default constructor required for Firebase
     */
    public ActivityInfo() {
        this.image = "";
        this.imageTitle = "";
        this.imageDetail = "";
    }

    /**
     * Parameterized constructor
     */
    public ActivityInfo(String image, String imageTitle, String imageDetail) {
        this.image = image != null ? image : "";
        this.imageTitle = imageTitle != null ? imageTitle : "";
        this.imageDetail = imageDetail != null ? imageDetail : "";
    }

    @NonNull
    public String getImage() {
        return image != null ? image : "";
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    public String getImageTitle() {
        return imageTitle != null ? imageTitle : "";
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    @NonNull
    public String getImageDetail() {
        return imageDetail != null ? imageDetail : "";
    }

    public void setImageDetail(String imageDetail) {
        this.imageDetail = imageDetail;
    }
}
