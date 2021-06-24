package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.databinding.ActivityMainBinding;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    // Global variables
    public final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + this.getString(R.string.TMDB_API_KEY);
    public static final String TAG = "MainActivity";
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Started to use ViewBinding
        // setContentView(R.layout.activity_main); [NO LONGER NEEDED BECAUSE OF VIEWBINDING]
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Define RecyclerView [NO LONGER NEEDED BECAUSE OF VIEWBINDING]
        // RecyclerView rvMovies = findViewById(R.id.rvMovies);

        // Create MovieAdapter
        this.movies = new ArrayList<>();
        MovieAdapter movieAdapter = new MovieAdapter(this, this.movies);

        // Set MovieAdapter on the RecyclerView
        binding.rvMovies.setAdapter(movieAdapter);

        // Set a LayoutManager on the RecyclerView
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(this));

        // Create a new instance of CodePath's AsyncHttpClient
        AsyncHttpClient client = new AsyncHttpClient();

        // Create a GET request to get current playing movies
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");

                    // Populate our Movies array by adding them and notify movieAdapter
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.e(TAG, "Hit JSON exception", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}