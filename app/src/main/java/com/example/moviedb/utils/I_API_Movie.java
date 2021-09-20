package com.example.moviedb.utils;

import com.example.moviedb.models.MovieModel;
import com.example.moviedb.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface I_API_Movie {


    @GET("/3/discover/movie")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String api_key,
            @Query("page") int page
    );




}
