package com.admin.ucsmonywa.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Base Fragment class for common functionality
 * Handles common UI operations and lifecycle management
 */
public abstract class BaseFragment extends Fragment {
    
    private ProgressDialog progressDialog;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null) {
            initProgressDialog();
        }
    }
    
    private void initProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
    }
    
    /**
     * Show progress dialog with custom message
     */
    protected void showProgress(String message) {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }
    
    /**
     * Hide progress dialog safely
     */
    protected void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    
    /**
     * Show toast message
     */
    protected void showToast(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * Show long toast message
     */
    protected void showLongToast(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }
    
    @Override
    public void onDestroyView() {
        hideProgress();
        super.onDestroyView();
    }
}
