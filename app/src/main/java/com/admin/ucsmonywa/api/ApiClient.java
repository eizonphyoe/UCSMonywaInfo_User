package com.admin.ucsmonywa.api;

import com.admin.ucsmonywa.constants.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit API client
 * Implements singleton pattern for efficient resource usage
 */
public class ApiClient {
    
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    private ApiClient() {
        throw new AssertionError("Cannot instantiate API client");
    }
    
    /**
     * Get configured OkHttpClient with timeouts
     */
    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(AppConstants.Api.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.Api.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.Api.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
        }
        return okHttpClient;
    }

    /**
     * Get Retrofit client instance
     */
    public static Retrofit getApiClient(String baseUrl) {
        if (retrofit == null || !retrofit.baseUrl().toString().equals(baseUrl)) {
            retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}
