package com.bassam.movieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bassam.movieapp.API.MovieService;
import com.bassam.movieapp.Models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH>{
    private List<Result> movieList;
    private onClick clicked;

    public MovieAdapter(List<Result> movieList, onClick clicked) {
        this.movieList = movieList;
        this.clicked = clicked;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,
                parent,false);
        return new VH(v);

    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Result res = movieList.get(position);
        holder.txtMovieName.setText(res.getTitle());

        String image_url = MovieService.IMAGES_BASE_URL + MovieService.IMAGE_SIZE_SMALL
                + res.getPosterPath();
        Picasso.get().load(image_url).into(holder.imgMovieCover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.MovieSelected(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView imgMovieCover;
        TextView txtMovieName;
        public VH(@NonNull View itemView) {
            super(itemView);
            imgMovieCover= itemView.findViewById(R.id.img_movie_cover);
            txtMovieName= itemView.findViewById(R.id.txt_movie_name);
        }
    }
    interface onClick{
        void MovieSelected(int position);
    }
}
