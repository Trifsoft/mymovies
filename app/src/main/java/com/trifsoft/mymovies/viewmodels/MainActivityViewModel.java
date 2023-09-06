package com.trifsoft.mymovies.viewmodels;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.trifsoft.mymovies.AppRepository;
import com.trifsoft.mymovies.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    public AppRepository appRepository;

    public MutableLiveData<ArrayList<Result>> allMoviesMutableLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        this.appRepository = new AppRepository(application);
        this.allMoviesMutableLiveData = null;
    }


    public MutableLiveData<ArrayList<Result>> getAllMoviesMutableLiveData() {
        if (allMoviesMutableLiveData == null) {
            allMoviesMutableLiveData = appRepository.getAllMoviesMutableLiveData();
        }
        return allMoviesMutableLiveData;
    }

    public LiveData<List<Result>> getAllResults(){
        return appRepository.getAllResults();
    }

    public LiveData<List<Result>> getResultsFromSearch(String search){
        return appRepository.getResultsFromSearch(search);
    }

    public boolean isConnected(ConnectivityManager connectivityManager){
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
