package com.example.moviedb.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.Models.MovieModel;
import com.example.moviedb.Repositories.MovieRepository;

import java.util.List;

public class MovieAPICLIENT {
    //live data
      private MutableLiveData<List<MovieModel>> movies;

      private static MovieAPICLIENT instance;


      //CTOR
    private MovieAPICLIENT() {
        movies = new MutableLiveData<>();
    }





    public static MovieAPICLIENT getInstance(){
        if(instance ==null) {
            instance = new MovieAPICLIENT();
        }
        return instance;
    }



    public MutableLiveData<List<MovieModel>> getMovies() {
        return movies;
    }
}
