package com.example.moviedb.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.models.MovieModel;
import com.example.moviedb.repositories.MovieRepository;

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

    public void searchMovieAPI(String query, int pageNB) {
        movieRepository.searchMovieAPI(query, pageNB);
    }


}
