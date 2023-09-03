package com.trifsoft.mymovies.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trifsoft.mymovies.AppRepository;
import com.trifsoft.mymovies.models.MovieData;

public class MovieDetailsActivityViewModel extends ViewModel {
    public AppRepository appRepository;
    public MutableLiveData<MovieData> movieDataMutableLiveData;

    public Integer intentId;
    public MovieDetailsActivityViewModel() {
        appRepository = new AppRepository();
        intentId = null;
    }

    public MutableLiveData<MovieData> getMovieDataMutableLiveData(int id){
        movieDataMutableLiveData = appRepository.getMovieDataMutableLiveData(id);
        return movieDataMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("TAG", "onCleared");
    }
}
