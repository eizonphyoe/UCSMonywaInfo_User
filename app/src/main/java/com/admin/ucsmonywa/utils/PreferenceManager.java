package com.admin.ucsmonywa.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.admin.ucsmonywa.constants.AppConstants;

/**
 * Centralized SharedPreferences manager
 * Provides type-safe access to app preferences
 */
public class PreferenceManager {
    
    private static PreferenceManager instance;
    private final SharedPreferences sharedPreferences;
    
    private PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(
            AppConstants.Preferences.PREF_FILE_NAME, 
            Context.MODE_PRIVATE
        );
    }
    
    public static synchronized PreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceManager(context.getApplicationContext());
        }
        return instance;
    }
    
    /**
     * Save selected year
     */
    public void saveYear(String year) {
        sharedPreferences.edit()
            .putString(AppConstants.Preferences.KEY_YEAR, year)
            .apply();
    }
    
    /**
     * Get selected year
     */
    public String getYear() {
        return sharedPreferences.getString(AppConstants.Preferences.KEY_YEAR, "");
    }
    
    /**
     * Save selected section
     */
    public void saveSection(String section) {
        sharedPreferences.edit()
            .putString(AppConstants.Preferences.KEY_SECTION, section)
            .apply();
    }
    
    /**
     * Get selected section
     */
    public String getSection() {
        return sharedPreferences.getString(AppConstants.Preferences.KEY_SECTION, "");
    }
    
    /**
     * Clear all preferences
     */
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
