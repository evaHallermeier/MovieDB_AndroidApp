package com.example.moviedb.model;
import android.os.Parcel;
import android.os.Parcelable; //to move data from one activity to another
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//hold info on each movie displayed and to not interact directly to the view
public class MovieModel implements Parcelable{

//relevant data on a specific movie
    private String title;
    private String poster_path;
    private String release_date;
    private float vote_average;

    @SerializedName("overview")
    @Expose
    private String movie_overview;

    protected MovieModel(Parcel in) { //called when we click on a movie cell
        //get from the response all the data we want to display
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString().substring(0,4);
        //get only the 4 first characters: to get only year of release
        
        vote_average = in.readFloat();
        movie_overview = in.readString();
    }
    
    //GETTERS -called to passed info on movie on the movie detail window
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) { //called after click on a movie cell
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeFloat(vote_average);
        parcel.writeString(movie_overview);
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) { //called after a click on a movie cell
            return new MovieModel(in);
        }
        @Override
        public MovieModel[] newArray(int size) { //for CTOR of Creator
            return new MovieModel[size];
        }
    };
}

