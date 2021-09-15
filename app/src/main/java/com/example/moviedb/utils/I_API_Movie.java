package com.example.moviedb.utils;

import com.example.moviedb.Models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface I_API_Movie {
    //search for movies //surement a enlever plus tard si pas utile


    //search movie by id
    //msybe for details
    //https://api.themoviedb.org/3/movie/550?api_key=08e696dd9eee56bfad34f61503057b69
    //for example 550 is id of a specific movie
@GET("3/movie/{movie_id}?")
Call<MovieModel> getMovie(@Path("movie_id") int movie_id,
                            @Query("api_key") String api_key

);
}
