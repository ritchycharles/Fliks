package com.example.gustave.flickster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.gustave.flickster.adapters.MovieArrayAdapter;
import com.example.gustave.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

       // getData();

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=30642d4a38cf088693d6f8c7d541ff38";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray movieJsonResults = response.getJSONArray("results");
                    Log.d("DEBUG", response.toString());
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.d("DEBUG", e.toString());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DEBUG", responseString);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        } );

    }


    public void getData(){
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=51751cd31e15efbb2e24a3594ffaef11";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Debug", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG",errorResponse.toString());
            }
        });


    }
}
