package com.moter.nearbylocation.Remote;

import com.moter.nearbylocation.Model.MyPlaces;
import com.moter.nearbylocation.Model.PlaceDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by moter on 14.02.2018.
 */

public interface IGoogleService {
    @GET
    Call<MyPlaces> getNearbyPlaces(@Url String Url);

    @GET
    Call<PlaceDetail> getDetailPlaces(@Url String Url);
}
