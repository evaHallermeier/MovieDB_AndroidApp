package com.example.moviedb.models;
import android.os.Parcel;
import android.os.Parcelable; //to move data from one activity to another

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieModel implements Parcelable{

//relevant data on a specific movie
    private String title;
    private String poster_path;
    private String release_date;
    private int movie_id;
    private float vote_average;
    @SerializedName("overview")
    @Expose
    private String movie_overview;



    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString().substring(0,4);
        //get only the 4 first characters: to get only year of release

        movie_id = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getMovie_id() {
        return movie_id;
}

    public float getVote_average() {
        return vote_average;
    }
    public String getTitle() {
        return title;
    }
    public String get_overview() {
        return movie_overview;
    }
    public String get_release_date() {
        return release_date;
    }
    public String get_poster_path() {
        return poster_path;
    }
    //PARCELABLE

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeInt(movie_id);
        parcel.writeFloat(vote_average);
        parcel.writeString(movie_overview);
    }

}

