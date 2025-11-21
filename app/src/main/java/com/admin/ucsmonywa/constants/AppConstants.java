package com.admin.ucsmonywa.constants;

/**
 * Application-wide constants
 * Centralized constant management for better maintainability
 */
public final class AppConstants {
    
    // Prevent instantiation
    private AppConstants() {
        throw new AssertionError("Cannot instantiate constants class");
    }
    
    // API Configuration
    public static final class Api {
        public static final String BASE_URL = "http://192.168.100.72:3000/";
        public static final int CONNECT_TIMEOUT = 30; // seconds
        public static final int READ_TIMEOUT = 30; // seconds
        public static final int WRITE_TIMEOUT = 30; // seconds
    }
    
    // Firebase Configuration
    public static final class Firebase {
        public static final String NOTICEBOARD_PATH = "noticeboard";
        public static final String ACTIVITIES_PATH = "activities";
        public static final String STORAGE_IMAGES_PATH = "images/";
        public static final String STORAGE_ACTIVITY_IMAGES_PATH = "activityimg/";
    }
    
    // SharedPreferences Configuration
    public static final class Preferences {
        public static final String PREF_FILE_NAME = "com.internship.ucsmonywa";
        public static final String KEY_YEAR = "YEAR";
        public static final String KEY_SECTION = "SECTION";
    }
    
    // Intent Extra Keys
    public static final class IntentKeys {
        public static final String TITLE = "title";
        public static final String DETAIL = "detail";
        public static final String IMAGE = "img";
        public static final String DATE = "date";
        public static final String SUBJECT = "subject";
        public static final String TEACHER = "teacher";
        public static final String DURATION = "duration";
        public static final String ITEM_POSITION = "Item Position";
    }
    
    // Request Codes
    public static final class RequestCodes {
        public static final int PICK_IMAGE = 71;
    }
    
    // Year and Section Data
    public static final class Academic {
        public static final String[] YEARS = {
            "First Year", "Second Year", "Third Year", "Fourth Year", "Fifth Year"
        };
        
        public static final String[] FIRST_YEAR_SECTIONS = {
            "Section A", "Section B", "Section C", "Section D", "Section E"
        };
        
        public static final String[] OTHER_YEAR_SECTIONS = {
            "Section A", "Section B", "Section C", "Section D", "CT"
        };
    }
    
    // UI Messages
    public static final class Messages {
        public static final String LOADING = "Loading....";
        public static final String UPLOADING = "Uploading....";
        public static final String UPLOADED = "Uploaded";
        public static final String NO_DATA = "No timetable data available";
        public static final String FAIL = "Fail!";
        public static final String ERROR_SUBJECT_REQUIRED = "Subject Required";
        public static final String ERROR_TEACHER_REQUIRED = "Teacher Required";
        public static final String ERROR_DURATION_REQUIRED = "Duration Required";
    }
}
