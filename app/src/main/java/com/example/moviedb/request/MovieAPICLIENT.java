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


      private RetrieveMovie retrieveMoviesR;

    //live data
      //for search
      private static MovieAPICLIENT instance;
      private MutableLiveData<List<MovieModel>> movies;


      //livedata for popular movies
      private MutableLiveData<List<MovieModel>> popular_movies;
    private RetrieveMoviePop retrieveMoviesRunnable_Popular; //popular runnable


      //CTOR
    private MovieAPICLIENT() {
        movies = new MutableLiveData<>();
        popular_movies = new MutableLiveData<>();
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
    public LiveData<List<MovieModel>> getPopular_Movies() {
        return popular_movies;
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




    public void searchPopular_movies(int pageNb){
        if(retrieveMoviesRunnable_Popular !=null) {
            retrieveMoviesRunnable_Popular = null;
        }
        retrieveMoviesRunnable_Popular = new RetrieveMoviePop(pageNb);
        final Future handler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable_Popular);

        //set timeout
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                //cancel retrofit request
                handler.cancel(true); //interrupt if timeout
            }
        }, 4000, TimeUnit.MILLISECONDS);

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


    private class RetrieveMoviePop implements Runnable{
    private int pageNb;
    boolean cancelRequest;

    public RetrieveMoviePop(int p) {
        this.pageNb=p;
        cancelRequest = false;
    }


    @Override
    public void run() {
//get response
        try{
            Log.v("Tag", "line 177 movieapiclient");
            Response responseP = getPopular_Movies(pageNb).execute();
            Log.v("Tag", "line 179 movieapiclient");
            if (cancelRequest) {
                return;
            }
            if(responseP.code() == 200) {
                List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)responseP.body()).getMovies());
                if(pageNb ==1) {
                    //send data to livedata, postvalue used for background thread,
                    // setvalue not for background thread
                    popular_movies.postValue(list);
                }else{
                    List<MovieModel> currentMovies = popular_movies.getValue();
                    currentMovies.addAll(list);
                    popular_movies.postValue(currentMovies);
                }
            }else{
                String error = responseP.errorBody().string();
                Log.v("Tag", "error" + error);
                popular_movies.postValue(null);
            }
        }
        catch(IOException E){
            E.printStackTrace();
            popular_movies.postValue(null);
        }


    }

    //search method quey
    private Call<MovieSearchResponse> getMovies(String query, int pageNb) {
        return RetrofitCaller.getMovieAPI().searchMovie(APIparams.API_key, query, pageNb);
    }

    private Call<MovieSearchResponse> getPopular_Movies(int pageNb) {
        Log.v("Tag", "line 212 movieapiclient");
        return RetrofitCaller.getMovieAPI().getPopular(APIparams.API_key, pageNb);
    }

    private void cancelRequest(){
        Log.v("Tag", "cancel request");
        cancelRequest = true;
    }
}
    }





