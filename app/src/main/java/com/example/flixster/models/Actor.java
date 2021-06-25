package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Actor {

    // Attributes
    int id;
    String name;
    String profilePicture;
    String character;

    /**
     * Empty constructor for Parcel library
     */
    public Actor() {

    }

    /**
     * Constructor for Actor from JSONObject
     * @param jsonObject: The object to turn into an actor
     */
    public Actor(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt("id");
        this.name = jsonObject.getString("name");
        this.profilePicture = jsonObject.getString("profile_path");
        this.character = jsonObject.getString("character");
    }

    /**
     * Creates an array of Actors from a JSONArray
     * @param jsonArray: Array of objects in form or JSONArray to turn into actors
     * @throws JSONException : Exception thrown when a required key is not found on JSONObject
     * @return List of actors
     */
    public static List<Actor> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Actor> actors = new ArrayList<>();

        for(int i=0; i < jsonArray.length(); i++) {
            actors.add(new Actor(jsonArray.getJSONObject(i)));
        }

        return actors;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfilePicture() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", this.profilePicture);
    }

    public String getCharacter() {
        return character;
    }
}
