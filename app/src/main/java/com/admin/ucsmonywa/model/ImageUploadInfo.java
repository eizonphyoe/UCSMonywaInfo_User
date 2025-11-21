package com.admin.ucsmonywa.model;

import androidx.annotation.NonNull;

/**
 * Model class for notice board image uploads
 * Represents a notice with title, detail, image URL and date
 */
public class ImageUploadInfo {

    private String imageTitle;
    private String imageDetail;
    private String imageUrl;
    private String date;

    /**
     * Default constructor required for Firebase
     */
    public ImageUploadInfo() {
        this.imageTitle = "";
        this.imageDetail = "";
        this.imageUrl = "";
        this.date = "";
    }

    /**
     * Parameterized constructor
     */
    public ImageUploadInfo(String imageTitle, String imageDetail, String imageUrl, String date) {
        this.imageTitle = imageTitle != null ? imageTitle : "";
        this.imageDetail = imageDetail != null ? imageDetail : "";
        this.imageUrl = imageUrl != null ? imageUrl : "";
        this.date = date != null ? date : "";
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

    @NonNull
    public String getImageUrl() {
        return imageUrl != null ? imageUrl : "";
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getDate() {
        return date != null ? date : "";
    }

    public void setDate(String date) {
        this.date = date;
    }
}
