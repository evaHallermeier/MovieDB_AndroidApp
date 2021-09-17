package com.example.moviedb.response;

import com.example.moviedb.models.MovieModel;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

import java.util.List;

//class for getting multiple movies (popular movies for main screen)
public class MovieSearchResponse {

    @SerializedName("total results") //which keyword search in json
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<MovieModel> movies;

    public int getTotal_count(){
        return total_count;
    }

    public List<MovieModel> getMovies(){
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                '}';
    }
}
