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
import com.example.flixster.models.Actor;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {

    // Attributes
    Context context;
    List<Actor> actors;

    /**
     * Constructor for the ActorAdapter class
     * @param context: the context the adaptor is constructed in
     * @param actors: the list of movies that it contains
     */
    public ActorAdapter(Context context, List<Actor> actors) {
        this.context = context;
        this.actors = actors;
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
        View actorView = LayoutInflater.from(this.context).inflate(R.layout.item_actor, parent, false);
        return new ViewHolder(actorView);
    }

    /**
     * Populating data into the View through the ViewHolder
     * @param holder: the holder to bind
     * @param position: the position it should bind to
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get actor at passed position
        Actor actor = this.actors.get(position);

        // Bind the data into de ViewHolder
        holder.bind(actor);
    }

    /**
     * Get the total items in list of Adapter
     * @return int
     */
    @Override
    public int getItemCount() {
        return this.actors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Atributtes
        TextView tvName;
        TextView tvCharacter;
        ImageView ivProfile;

        /**
         * Constructor for ViewHolder of the class MovieAdapter
         * @param itemView: the View that the ViewHolder has
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find items on layout
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvCharacter = itemView.findViewById(R.id.tvCharacter);
            this.ivProfile = itemView.findViewById(R.id.ivProfile);
        }

        /**
         * Populate the data from the actor into the ViewHolder
         * @param actor: The actor who's data is going to be used
         */
        public void bind(Actor actor) {
            this.tvName.setText(actor.getName());
            this.tvCharacter.setText(actor.getCharacter());

            // Use Glide library to load image into ImageView
            Glide.with(context)
                    .load(actor.getProfilePicture())
                    .placeholder(R.drawable.poster_placeholder)
                    .error(R.drawable.poster_placeholder)
                    .fitCenter()
                    .transform(new RoundedCornersTransformation(30, 10))
                    .into(this.ivProfile);
        }

    }

}
