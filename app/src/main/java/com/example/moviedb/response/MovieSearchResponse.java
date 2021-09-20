package com.example.moviedb.response;

import com.example.moviedb.model.MovieModel;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import java.util.List;

//class for getting multiple movies (popular movies for main screen)
public class MovieSearchResponse {
    @SerializedName("results")
    @Expose()

    private List<MovieModel> movies;

    public List<MovieModel> getMovies(){
        return movies;
    }
}
