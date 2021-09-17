package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.moviedb.adapters.MovieRecyclerView;
import com.example.moviedb.adapters.OnMovieListener;
import com.example.moviedb.models.MovieModel;
import com.example.moviedb.viewModels.MovieList_VM;
import com.example.moviedb.request.RetrofitCaller;
import com.example.moviedb.response.MovieSearchResponse;
import com.example.moviedb.utils.APIparams;
import com.example.moviedb.utils.I_API_Movie;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenMovieListActivity extends AppCompatActivity implements OnMovieListener {
//contain list of movies

    //fields
    private MovieList_VM movieList_vm;
    private RecyclerView recycleView;
    private MovieRecyclerView movieRecyclerViewAdapter;
  //  Button btn;


    private void ConfigureRecycleView() {

        movieRecyclerViewAdapter = new MovieRecyclerView(this);
        recycleView.setAdapter(movieRecyclerViewAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycleView = findViewById(R.id.recycleView);
     //   btn = findViewById(R.id.button);
        movieList_vm = new ViewModelProvider(this).get(MovieList_VM.class);
        ConfigureRecycleView();
        ObserverAnyChange();
        searchMovieAPI("america", 1);
        //TEST
        /*
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchMovieAPI("Fast" , 1); //get forst page of results of
                // movies that contain word fast in title
            }
        });

         */
        /*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponseByID();


            }
        });

         */
    }

    private void searchMovieAPI(String query, int pageNB){
        movieList_vm.searchMovieAPI(query, pageNB);
    }



//observe data change from VM
    private void ObserverAnyChange(){
        movieList_vm.getMovies().observe(this, new Observer<List<MovieModel>>() {


            @Override
            public void onChanged(List<MovieModel> movieModel) { //observe any data change
                if(movieModel !=null) {
                    for(MovieModel model: movieModel) {
                        Log.v("Tag", "ONCHANGED: "+ model.getTitle());
                        movieRecyclerViewAdapter.setMovies(movieModel);
                    }
                }
            }
        });
    }


    //pour l instant pas utile
    private void GetRetrofitResponse() {
        I_API_Movie movieAPI = RetrofitCaller.getMovieAPI();
        Call<MovieSearchResponse> responseCall = movieAPI.searchMovie(APIparams.API_key,
                "Jack Reacher", 1);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call,
                                   Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    //Log.v("Tag", "answer" + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    for (MovieModel movie : movies) {
                        Log.v("Tag", "title" + movie.getTitle());

                    }
                }


            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });

        /*
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
 */

    }


    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}