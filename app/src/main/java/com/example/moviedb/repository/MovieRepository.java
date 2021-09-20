package com.example.moviedb.repository;

import androidx.lifecycle.LiveData;
import com.example.moviedb.model.MovieModel;
import com.example.moviedb.request.MovieAPICLIENT;
import java.util.List;
//responsible to interact with movie api client and DB,part of the model in MVVM
public class MovieRepository {

    private static MovieRepository instance;
    private MovieAPICLIENT movieAPIclient;
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

    //GETTERS
    public LiveData<List<MovieModel>> getMovies() {
         return movieAPIclient.getMovies();
    }

    public void searchPopular_MovieAPI(int pageNB) {
        pageNB = pageNB;
        movieAPIclient.searchPopular_movies(pageNB);
    }

    public void searchNextPage(){ //call API to get next page with other movies
        pageNB++;
        searchPopular_MovieAPI(pageNB);
    }
}
