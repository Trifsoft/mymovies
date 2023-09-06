package com.trifsoft.mymovies.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("TAG", "onCleared");
    }
}
