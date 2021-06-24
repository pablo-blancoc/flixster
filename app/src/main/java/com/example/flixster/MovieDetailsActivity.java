package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    // Attributes
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Unwrap movie passed in the intent
        this.movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

        // Set ViewObjects content
        binding.tvOverview.setText(this.movie.getOverview());
        binding.tvTitle.setText(this.movie.getTitle());

        // Set which image to load depending on orientation
        String imageUrl;
        int placeholder;
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movie.getPosterPath();
            placeholder = R.drawable.poster_placeholder;
        } else {
            imageUrl = movie.getBackdropPath();
            placeholder = R.drawable.backdrop_placeholder;
        }

        // Use Glide library to load image into ImageView
        Glide.with(this)
                .load(imageUrl)
                .placeholder(placeholder)
                .error(placeholder)
                .fitCenter()
                .transform(new RoundedCornersTransformation(30, 10))
                .into(binding.ivImage);

        // Set Vote Average
        float voteAverage = this.movie.getVoteAverage().floatValue();
        binding.rbVoteAverage.setRating(voteAverage / 2.0f);


    }

}