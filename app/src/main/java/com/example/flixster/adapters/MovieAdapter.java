package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.MovieDetailsActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    // Attributes
    Context context;
    List<Movie> movies;

    /**
     * Constructor for the MovieAdapter class
     * @param context: the context the adaptor is constructed in
     * @param movies: the list of movies that it contains
     */
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    /**
     * Inflate a layout defined in XML layouts and returns it inside a ViewGroup (wrapped)
     * @param parent: the ViewGroup to which it is a child of
     * @param viewType: what is the type of view
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(this.context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    /**
     * Populating data into the View through the ViewHolder
     * @param holder: the holder to bind
     * @param position: the position it should bind to
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get movie at passed position
        Movie movie = this.movies.get(position);

        // Bind the data into de ViewHolder
        holder.bind(movie);
    }

    /**
     * Get the total items in list of Adapter
     * @return int
     */
    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Atributtes
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        /**
         * Constructor for ViewHolder of the class MovieAdapter
         * @param itemView: the View that the ViewHolder has
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find items on layout
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvOverview = itemView.findViewById(R.id.tvOverview);
            this.ivPoster = itemView.findViewById(R.id.ivPoster);

            // Set OnClickListener with current context
            itemView.setOnClickListener(this);
        }

        /**
         * Populate the data from the movie into the ViewHolder
         * @param movie: The movie who's data is going to be used
         */
        public void bind(Movie movie) {
            this.tvTitle.setText(movie.getTitle());

            // Crop the overview if it is too long to show
            String overview = movie.getOverview();
            int maxLength = 120;
            if(overview.length() > maxLength) {
                overview = overview.substring(0, maxLength) + "...  \n[click to read more]";
            }
            this.tvOverview.setText(overview);

            // Set which image to load depending on orientation
            String imageUrl;
            int placeholder;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
                placeholder = R.drawable.backdrop_placeholder;
            } else {
                imageUrl = movie.getPosterPath();
                placeholder = R.drawable.poster_placeholder;
            }

            // Use Glide library to load image into ImageView
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .fitCenter()
                    .transform(new RoundedCornersTransformation(30, 10))
                    .into(this.ivPoster);
        }

        /**
         * Function called when a row is clicked to display the information of a movie in MovieDetailsActivity
         * @param v: The View object
         */
        @Override
        public void onClick(View v) {
            // Get position of item clicked
            int position = getAdapterPosition();

            // Ensure the position is valid
            if(position != RecyclerView.NO_POSITION) {
                // Get Movie at position
                Movie movie = movies.get(position);

                // Create intent for MovieDetailsActivity
                Intent intent = new Intent(context, MovieDetailsActivity.class);

                // Serialize Movie object using Parceler library
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));

                // Show MovieDetailsActivity
                context.startActivity(intent);
            }

        }

    }

}
