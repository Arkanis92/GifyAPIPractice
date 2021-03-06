package com.example.gifyapi.retrofit;

import com.example.gifyapi.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GifyService {

    @GET(Constants.GIFY_PATH)
    Call<List<String>> getGify(

            @Query("api_key") String api_key,
            @Query("limit") int limit,
            @Query("q") String q
    );
}
