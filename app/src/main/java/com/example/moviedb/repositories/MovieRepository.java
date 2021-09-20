package com.example.moviedb.repositories;


import androidx.lifecycle.LiveData;

import com.example.moviedb.models.MovieModel;
import com.example.moviedb.request.MovieAPICLIENT;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    private MovieAPICLIENT movieAPIclient;
    private String query;
    private int pageNB;

     public static MovieRepository getInstance() { //singleton
         if(instance ==null) {
            instance = new MovieRepository();
         }
         return instance;
     }

     //CTOR
     private MovieRepository(){
        movieAPIclient = MovieAPICLIENT.getInstance();
     }


    //get

    public LiveData<List<MovieModel>> getMovies() {
         return movieAPIclient.getMovies();
    }
    public LiveData<List<MovieModel>> getPop_Movies() {
        return movieAPIclient.getPopular_Movies();
    }



    public void searchPopular_MovieAPI(int pageNB) {
        pageNB = pageNB;
        movieAPIclient.searchPopular_movies(pageNB);
    }


    public void searchNextPage(){
        pageNB++;
        searchPopular_MovieAPI(pageNB);
    }

}
