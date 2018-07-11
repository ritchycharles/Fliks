package com.example.gustave.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    String posterPath;
    String originalTitle;
    String overview;


    public Movie(JSONObject JsonObject) throws JSONException{
        this.posterPath = JsonObject.getString("poster_path");
        this.originalTitle = JsonObject.getString("original_title");
        this.overview = JsonObject.getString("overview");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
