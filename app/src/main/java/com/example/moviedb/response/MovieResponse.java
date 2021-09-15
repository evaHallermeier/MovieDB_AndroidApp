package com.example.moviedb.response;
import com.example.moviedb.Models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//class for single movie request
public class MovieResponse {

    //first step- find movie object in json
    @SerializedName("results")
    @Expose
    private MovieModel movie;


    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString(){
        return "MovieResponse{" + "movie=" + movie + '}';
    }
}
