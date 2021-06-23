package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    // Attributes
    String posterPath;
    String title;
    String overview;

    /**
     * Constructor for the Movie class
     * @param jsonObject: The JSONObject to be turned into a movie
     * @throws JSONException: Exception thrown when a required key is not found on JSONObject
     */
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
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
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    /**
     * Getter for title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for overview
     * @return String
     */
    public String getOverview() {
        return overview;
    }
}
