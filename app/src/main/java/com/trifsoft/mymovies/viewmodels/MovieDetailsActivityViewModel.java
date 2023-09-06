package com.trifsoft.mymovies.viewmodels;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.trifsoft.mymovies.AppRepository;
import com.trifsoft.mymovies.models.MovieData;

public class MovieDetailsActivityViewModel extends AndroidViewModel {
    public AppRepository appRepository;
    public MutableLiveData<MovieData> movieDataMutableLiveData;

    public Integer intentId;

    public MovieDetailsActivityViewModel(@NonNull Application application) {
        super(application);

        this.intentId = null;
        this.appRepository = new AppRepository(application);
    }


    public MutableLiveData<MovieData> getMovieDataMutableLiveData(int id){
        movieDataMutableLiveData = appRepository.getMovieDataMutableLiveData(id);
        return movieDataMutableLiveData;
    }
    public LiveData<MovieData> getMovieData(long id){
        return appRepository.getMovieData(id);
    }

    public boolean isConnected(ConnectivityManager connectivityManager){
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
