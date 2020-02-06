package com.example.gifyapi.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gifyapi.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<String>> gifs = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    private Repository repo = Repository.getInstance();

    public void fetchGifyData(String query) {
        repo.getGifs(query)
                .enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        gifs.postValue(response.body());
                        error.postValue("");
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        gifs.postValue(new ArrayList<String>());
                        error.postValue(t.getMessage());
                    }
                });
    }

    public int getNumber() {
        return new Random().nextInt(10);
    }

    public LiveData<List<String>> getGifyLiveData() {
        return gifs;
    }

    public LiveData<String> getErrorLiveData() {
        return error;
    }
}
