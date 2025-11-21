package com.admin.ucsmonywa.utils;

import com.admin.ucsmonywa.api.ApiClient;
import com.admin.ucsmonywa.api.ApiInterface;
import com.admin.ucsmonywa.constants.AppConstants;

/**
 * API utility class
 * Provides centralized access to API endpoints
 */
public class ApiUtils {
    
    private ApiUtils() {
        throw new AssertionError("Cannot instantiate utility class");
    }
    
    /**
     * Get API interface instance
     */
    public static ApiInterface getApi() {
        return ApiClient.getApiClient(AppConstants.Api.BASE_URL)
                .create(ApiInterface.class);
    }
}
