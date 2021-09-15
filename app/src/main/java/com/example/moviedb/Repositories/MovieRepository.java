package com.example.moviedb.Repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.Models.MovieModel;
import com.example.moviedb.request.MovieAPICLIENT;

import java.util.List;

public class MovieRepository {
    //members
    private static MovieRepository instance;
    private MovieAPICLIENT movieAPIclient;

    //live data
   //  private MutableLiveData<List<MovieModel>> movies;

     public static MovieRepository getInstance() { //singleton
         if(instance ==null) {
            instance = new MovieRepository();
         }
         return instance;
     }

     //CTOR
     private MovieRepository(){
        // movies = new MutableLiveData<>();
        movieAPIclient = MovieAPICLIENT.getInstance();
     }


    //get

    public LiveData<List<MovieModel>> getMovies() {
         return movieAPIclient.getMovies();
    }

    /*
    public MovieAPICLIENT getMovieAPIclient() {
        return movieAPIclient;
    }

     */

}
