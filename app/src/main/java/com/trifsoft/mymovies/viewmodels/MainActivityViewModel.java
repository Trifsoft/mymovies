package com.trifsoft.mymovies.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trifsoft.mymovies.AppRepository;
import com.trifsoft.mymovies.models.Result;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    public AppRepository appRepository;

    public MutableLiveData<ArrayList<Result>> allMoviesMutableLiveData;
    public MainActivityViewModel() {
        appRepository = new AppRepository();
        allMoviesMutableLiveData = null;
    }

    public MutableLiveData<ArrayList<Result>> getAllMoviesMutableLiveData() {
        if (allMoviesMutableLiveData == null) {
            allMoviesMutableLiveData = appRepository.getAllMoviesMutableLiveData();
        }
        return allMoviesMutableLiveData;
    }
}
