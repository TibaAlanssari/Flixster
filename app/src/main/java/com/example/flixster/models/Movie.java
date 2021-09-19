package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String basePath = "https://image.tmdb.org/t/p/";
    int movieID;
    double rating;
    String posterPath;
    String backdropPath;
    String title;
    String overview;
    private static String backdropImageSize;
    private static String posterImageSize;

    //an empty constructor is needed for thr Parceler library
    public Movie(){}

    public Movie(JSONObject jsonObject, String backdropImageSizePath, String posterImageSize) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");
        Movie.posterImageSize = posterImageSize;
        Movie.backdropImageSize = backdropImageSize;
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray, String backdropImageSize, String posterImageSize) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i), backdropImageSize, posterImageSize));
        }
        return movies;
    }

    public String getPosterPath() {
        return basePath + posterImageSize +  posterPath;
    }

    public String getBackdropPath(){
        return basePath + backdropImageSize + backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieID() {
        return movieID;
    }
}
