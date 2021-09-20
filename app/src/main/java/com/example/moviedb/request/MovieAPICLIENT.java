package com.example.moviedb.request;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.moviedb.AppExecutors;
import com.example.moviedb.model.MovieModel;
import com.example.moviedb.response.MovieSearchResponse;
import com.example.moviedb.API.APIparams;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Response;

//part of the model in MVVM
public class MovieAPICLIENT {

      private static MovieAPICLIENT instance;
      private MutableLiveData<List<MovieModel>> movies; //observers for data change
      private RetrieveMoviePop retrieveMoviesRunnable_Popular; //popular runnable

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

    private class RetrieveMoviePop implements Runnable {
    private int pageNb;
    boolean cancelRequest;

    public RetrieveMoviePop(int p) { //CTOR
        this.pageNb=p;
        cancelRequest = false;
    }

    @Override
    public void run() {
        //get response
        try{
            Response responseP = getPopular_Movies(pageNb).execute();
            if (cancelRequest) {
                return;
            }
            if(responseP.code() == 200) {
                List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)responseP.body()).getMovies());
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
                String error = responseP.errorBody().string();
                Log.v("Tag", "error" + error);
                movies.postValue(null);
            }
        }
        catch(IOException E){
            E.printStackTrace();
            movies.postValue(null);
        }
    }

    private Call<MovieSearchResponse> getPopular_Movies(int pageNb) {
        return RetrofitCaller.getMovieAPI().getPopular(APIparams.API_key, pageNb);
    }
}
}





