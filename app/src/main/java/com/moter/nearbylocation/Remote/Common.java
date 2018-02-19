package com.moter.nearbylocation.Remote;

import com.moter.nearbylocation.Model.Results;

/**
 * Created by moter on 14.02.2018.
 */

public class Common  {
    private static final String GOOGLE_API_URL="https://maps.googleapis.com";
    public static Results currentResults;

    public static IGoogleService getGoogleApiService(){
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleService.class);
    }
}
