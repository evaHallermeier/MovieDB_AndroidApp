package com.example.moviedb.API;

import com.example.moviedb.response.MovieSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface I_API_Movie {

    //get list of movies for main screen
    @GET("/3/discover/movie")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String api_key,
            @Query("page") int page
    );
}
