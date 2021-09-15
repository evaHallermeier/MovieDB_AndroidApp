package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.moviedb.Models.MovieModel;
import com.example.moviedb.ViewModels.MovieList_VM;
import com.example.moviedb.request.RetrofitCaller;
import com.example.moviedb.response.MovieSearchResponse;
import com.example.moviedb.utils.APIparams;
import com.example.moviedb.utils.I_API_Movie;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenMovieListActivity extends AppCompatActivity {
//contain list of movies

    //fields
    private MovieList_VM movieList_vm;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        movieList_vm = new ViewModelProvider(this).get(MovieList_VM.class);

        /*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponseByID();


            }
        });

         */
    }

//observe data change from VM
    private void ObserverAnyChange(){
        movieList_vm.getMovies().observe(this, new Observer<List<MovieModel>>() {


            @Override
            public void onChanged(List<MovieModel> movieModel) { //observe any data change

            }
        });
    }





    //pour l instant pas utile
    private void GetRetrofitResponse() {
        I_API_Movie movieAPI = RetrofitCaller.getMovieAPI();
      //  Call<MovieSearchResponse> responseCall = movieAPI.searchMovie
    }

    private void GetRetrofitResponseByID(){
        I_API_Movie movieAPI = RetrofitCaller.getMovieAPI();
        Call<MovieModel> responseCall = movieAPI.getMovie(550, //for test 550 Fight club
                                            APIparams.API_key);

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code() ==200) {
                    MovieModel movie = response.body();
                    Log.v("Tag", "The response" + movie.getTitle());
                }
                else{
                    try{
                        Log.v("Tag", "Error" + response.errorBody().toString());
                    } catch (Exception e) {
                         e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
            }
        });


    }
}