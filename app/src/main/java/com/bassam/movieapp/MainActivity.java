package com.bassam.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bassam.movieapp.API.ApiClient;
import com.bassam.movieapp.Models.MovieResponse;
import com.bassam.movieapp.Models.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.onClick {
    List<Result> movies;
    RecyclerView rv_movies;
    MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObject();
        getData();
    }

    public void initObject(){
        rv_movies = findViewById(R.id.rv_movies);
        movies = new ArrayList<>();
        adapter = new MovieAdapter(movies,this);
        rv_movies.setLayoutManager(new GridLayoutManager(this,3));
        rv_movies.setAdapter(adapter);
    }
    private void getData(){
        ApiClient.getsInstance().getPopularMovies("c96b6f95ae798d142c8b6a51d0f29edd")
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if(response.isSuccessful()){
                            movies.clear();
                            movies.addAll(response.body().getResults());
                            adapter.notifyDataSetChanged();
                        }
                        
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.e("Crash", "onFailure: ", t);
                    }
                });
    }

    @Override
    public void MovieSelected(int position) {

        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(DetailActivity.EXTRA_MOVIE_ID,movies.get(position).getId());
        startActivity(i);

    }
}