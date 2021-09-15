package com.example.moviedb.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.Models.MovieModel;
import com.example.moviedb.Repositories.MovieRepository;

import java.util.List;

//class in VIEW MODEL part
public class MovieList_VM extends ViewModel {

    private MovieRepository movieRepository;
    //live data
   // private MutableLiveData<List<MovieModel>> movies = new MutableLiveData<>();


    //CTOR
    public MovieList_VM() {
        movieRepository =MovieRepository.getInstance();
    }


    //getmovies method
    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }



}
