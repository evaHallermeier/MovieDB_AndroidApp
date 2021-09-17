package com.example.moviedb.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.AppExecutors;
import com.example.moviedb.models.MovieModel;
import com.example.moviedb.response.MovieSearchResponse;
import com.example.moviedb.utils.APIparams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieAPICLIENT {
    //live data
      private MutableLiveData<List<MovieModel>> movies;
      private RetrieveMovie retrieveMoviesR;
      private static MovieAPICLIENT instance;


      //CTOR
    private MovieAPICLIENT() {
        movies = new MutableLiveData<>();
    }





    public static MovieAPICLIENT getInstance(){
        if(instance ==null) {
            instance = new MovieAPICLIENT();
        }
        return instance;
    }



    public LiveData<List<MovieModel>> getMovies() {
        return movies;
    }


    public void searchMoviesAPI(String query, int pageNb){
        if(retrieveMoviesR !=null) {
            retrieveMoviesR = null;
        }
        retrieveMoviesR = new RetrieveMovie(query, pageNb);
        final Future myhandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesR);

        //set timeout
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                //cancel retrofit request
            myhandler.cancel(true); //interrupt if timeout
            }
        }, 3000, TimeUnit.MILLISECONDS);

    }

    //retrieve data from RESTAPI by runnable class
    //2 type of request : ID
    private class RetrieveMovie implements Runnable{
        private String query;
        private int pageNb;
        boolean cancelRequest = false;

        public RetrieveMovie(String q, int p) {
            query = q;
            pageNb=p;
        }


        @Override
        public void run() {
//get response
            try{
                Response response = getMovies(query, pageNb).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body())
                            .getMovies());
                    if(pageNb ==1) {
                        //send data to livedata, postvalue used for background thread,
                        // setvalue not for background thread
                        movies.postValue(list);
                    }else{
                        List<MovieModel> currentMovies = movies.getValue();
                        currentMovies.addAll(list);
                        movies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag", "error" + error);
                    movies.postValue(null);
                }
            }
            catch(IOException E){
                E.printStackTrace();
                movies.postValue(null);
            }


        }

        //search method quey
        private Call<MovieSearchResponse> getMovies(String query, int pageNb) {
                return RetrofitCaller.getMovieAPI().searchMovie(APIparams.API_key, query, pageNb);
        }

        private void cancelRequest(){
                Log.v("Tag", "cancel request");
                cancelRequest = true;
            }
        }
    }




