package com.example.flixster.models;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

@Parcel
public class Movie {

    // Attributes
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    Double voteAverage;
    int id;
    String videoId;

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
    public Movie(JSONObject jsonObject, String api_key) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.voteAverage = jsonObject.getDouble("vote_average");
        this.id = jsonObject.getInt("id");

        // Create a new instance of CodePath's AsyncHttpClient
        AsyncHttpClient client = new AsyncHttpClient();

        // Create a GET request to get current playing movies
        client.get("https://api.themoviedb.org/3/movie/" + this.id + "/videos?api_key=" + api_key, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    JSONObject result = results.getJSONObject(0);
                    videoId = result.getString("key");

                } catch (JSONException e) {
                    Log.d("Movie", "No video available", e);
                    videoId = null;
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d("Movie", "onFailure");
                videoId = null;
            }
        });
    }

    /**
     * Creates an array of Movies from a JSONArray
     * @param jsonArray: Array of objects in form or JSONArray to turn into movies
     * @throws JSONException: Exception thrown when a required key is not found on JSONObject
     * @return List of movies
     */
    public static List<Movie> fromJsonArray(JSONArray jsonArray, String api_key) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for(int i=0; i < jsonArray.length(); i++) {
           movies.add(new Movie(jsonArray.getJSONObject(i), api_key));
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
     * Getter for voteAverage
     * @return String
     */
    public Double getVoteAverage() {
        return this.voteAverage;
    }

    /**
     * Getter for overview
     * @return String
     */
    public String getOverview() {
        return this.overview;
    }

    /**
     * Getter for id
     * @return int
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for videoId
     * @return String
     */
    public String getVideoId() {
        return videoId;
    }
}
