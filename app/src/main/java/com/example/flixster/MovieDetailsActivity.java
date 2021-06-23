package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    // Attributes
    Movie movie;

    // View objects
    TextView tvOverview;
    TextView tvTitle;
    ImageView ivImage;
    RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Resolve all the ViewObjects
        this.tvOverview = findViewById(R.id.tvOverview);
        this.tvTitle = findViewById(R.id.tvTitle);
        this.ivImage = findViewById(R.id.ivImage);
        this.rbVoteAverage = findViewById(R.id.rbVoteAverage);

        // Unwrap movie passed in the intent
        this.movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

        // Set ViewObjects content
        this.tvOverview.setText(this.movie.getOverview());
        this.tvTitle.setText(this.movie.getTitle());

        // Use Glide library to load image into ImageView
        Glide.with(this)
                .load(this.movie.getBackdropPath())
                .placeholder(R.drawable.backdrop_placeholder)
                .error(R.drawable.backdrop_placeholder)
                .fitCenter()
                .into(this.ivImage);

        // Set Vote Average
        float voteAverage = this.movie.getVoteAverage().floatValue();
        this.rbVoteAverage.setRating(voteAverage / 2.0f);


    }

}