package com.bassam.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bassam.movieapp.API.ApiClient;
import com.bassam.movieapp.API.MovieService;
import com.bassam.movieapp.Models.Genre;
import com.bassam.movieapp.Models.MovieDetail;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.squareup.picasso.Picasso;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_ID = "Movie_ID";
    private static final int DEFAULT_ID = -1;
    ImageView banner,img_poster;
    TextView txt_title,txt_release,txt_vote,txt_language,txt_overview,label_vote;
    ChipGroup geners;
    MovieDetail movieDetail;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CastAdapter castAdapter;
    ReviewsAdapter adapter;
    RecyclerView rv_cast,rv_review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeLight);
        setContentView(R.layout.activity_detail);
        final int movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID,DEFAULT_ID);
        if(movieId == DEFAULT_ID){
            return;
        }
        initViews();
        getData(movieId);


    }
    private void initViews(){
        banner = findViewById(R.id.img_movie_backdrop);
        img_poster = findViewById(R.id.img_poster);
        txt_title = findViewById(R.id.txt_title);
        txt_release = findViewById(R.id.txt_release_date);
        txt_vote = findViewById(R.id.txt_vote);
        txt_language = findViewById(R.id.txt_language);
        txt_overview = findViewById(R.id.txt_overview);
        label_vote = findViewById(R.id.lbl_vote);
        geners = findViewById(R.id.group_chip);
        rv_cast = findViewById(R.id.rv_cast);
        rv_review = findViewById(R.id.list_reviews);
    }
    private void getData(int movieId){
        ApiClient.getsInstance().getMovieDetail(movieId,"c96b6f95ae798d142c8b6a51d0f29edd")
                .enqueue(new Callback<MovieDetail>() {
                    @Override
                    public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                        movieDetail = response.body();
                        setDatatoView();
                        setCastAdapter();
                        setReviewsAdapter();

                    }

                    @Override
                    public void onFailure(Call<MovieDetail> call, Throwable t) {

                    }
                });
    }
    private void setDatatoView(){
        String banner_url = MovieService.IMAGES_BASE_URL + MovieService.IMAGE_SIZE_BANNER
                + movieDetail.getBackdropPath();
        Picasso.get().load(banner_url).into(banner);
        String poster_url = MovieService.IMAGES_BASE_URL + MovieService.IMAGE_SIZE_SMALL
                + movieDetail.getPosterPath();
        Picasso.get().load(poster_url).into(img_poster);

        txt_title.setText(movieDetail.getTitle());

        for (Genre g:
             movieDetail.getGenres()) {
            Chip chip = new Chip(this);
            chip.setText(g.getName());
            chip.setChipStrokeWidth(3);
            chip.setChipStrokeColor(ColorStateList.valueOf(
                    ContextCompat.getColor(this,R.color.colorPrimary)
            ));
            chip.setChipBackgroundColorResource(android.R.color.transparent);
            geners.addView(chip);

            txt_release.setText(movieDetail.getReleaseDate());
            txt_vote.setText(String.valueOf(movieDetail.getVoteAverage()));
            txt_language.setText(movieDetail.getOriginalLanguage());
            txt_overview.setText(movieDetail.getOverview());
            label_vote.setText(String.valueOf(movieDetail.getVoteCount()));
            
        }

    }
    private void setCastAdapter(){
        castAdapter = new CastAdapter(movieDetail.getCredits().getCast());
        rv_cast.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        rv_cast.setAdapter(castAdapter);
    }
    private void setReviewsAdapter(){
        adapter = new ReviewsAdapter(movieDetail.getReviews().getResults());
        rv_review.setAdapter(adapter);
        rv_review.setLayoutManager(new LinearLayoutManager(this));

    }

}