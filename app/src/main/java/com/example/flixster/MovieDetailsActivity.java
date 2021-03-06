package com.example.flixster;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.flixster.adapters.ActorAdapter;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    // Attributes
    Movie movie;
    ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
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

        binding.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( movie.getVideoId() == null ) {
                    Toast.makeText(MovieDetailsActivity.this, "No video available", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // Create intent and start activity
                    Toast.makeText(MovieDetailsActivity.this, "Showing video", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                    intent.putExtra("videoId", movie.getVideoId());
                    MovieDetailsActivity.this.startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        // Actors Adapter
        ActorAdapter actorAdapter = new ActorAdapter(this, this.movie.getActors());
        if (binding.rvCast != null) {
            binding.rvCast.setLayoutManager(new LinearLayoutManager(MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            binding.rvCast.setAdapter(actorAdapter);
        }
        super.onResume();
    }

}