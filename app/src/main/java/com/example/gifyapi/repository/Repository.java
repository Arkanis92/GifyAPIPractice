package com.example.gifyapi.repository;

import com.example.gifyapi.retrofit.GifyService;
import com.example.gifyapi.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Repository {

    private Retrofit retrofit;

    private Repository() {
        retrofit = RetrofitInstance.getInstance();
    }

    private static class RepositoryHolder {
        private static final Repository INSTANCE = new Repository();
    }

    public static Repository getInstance() {
        return RepositoryHolder.INSTANCE;
    }

    public Call<List<String>> getGifs(String query) {
        return retrofit.create(GifyService.class)
                .getGify(
                        "7j7AaeeoCxyly5dcEIsoLLUVlhQnadgj",
                        1,
                        query

                );
    }
}
