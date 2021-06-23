package com.example.flixster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

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

    public class ViewHolder extends RecyclerView.ViewHolder {

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

        }

        /**
         * Populate the data from the movie into the ViewHolder
         * @param movie: The movie who's data is going to be used
         */
        public void bind(Movie movie) {
            this.tvTitle.setText(movie.getTitle());
            this.tvOverview.setText(movie.getOverview());

            // Use Glide library to load image into ImageView
            Glide.with(context)
                    .load(movie.getPosterPath())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .fitCenter()
                    .into(this.ivPoster);
        }
    }

}
