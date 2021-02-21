package com.bassam.movieapp.API;

import com.bassam.movieapp.Models.MovieDetail;
import com.bassam.movieapp.Models.MovieResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    String IMAGES_BASE_URL = "https://image.tmdb.org/t/p";
    String IMAGE_SIZE_SMALL = "/w185";
    String IMAGE_SIZE_BANNER = "/w780";

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/{movie_id}?append_to_response=videos,credits,reviews")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") int id,
                                     @Query("api_key")String apiKey);

}
