package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    // Attributes
    String posterPath;
    String title;
    String overview;
    String backdropPath;

    /**
     * Empty constructor for Parcel library
     */
    public Movie() {

    }

    /**
     * Constructor for the Movie class
     * @param jsonObject: The JSONObject to be turned into a movie
     * @throws JSONException: Exception thrown when a required key is not found on JSONObject
     */
    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
    }

    /**
     * Creates an array of Movies from a JSONArray
     * @param jsonArray: Array of objects in form or JSONArray to turn into movies
     * @throws JSONException: Exception thrown when a required key is not found on JSONObject
     * @return List of movies
     */
    public static List<Movie> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for(int i=0; i < jsonArray.length(); i++) {
           movies.add(new Movie(jsonArray.getJSONObject(i)));
        }

        return movies;
    }

    /**
     * Getter for poster path
     * @return String
     */
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", this.posterPath);
    }

    /**
     * Getter for backdrop path
     * @return String
     */
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", this.backdropPath);
    }

    /**
     * Getter for title
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Getter for overview
     * @return String
     */
    public String getOverview() {
        return this.overview;
    }
}
