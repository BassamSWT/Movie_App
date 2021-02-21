package com.bassam.movieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bassam.movieapp.API.MovieService;
import com.bassam.movieapp.Models.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    List<Cast> casts;

    public CastAdapter(List<Cast> casts) {
        this.casts = casts;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast,parent,false);
        return new CastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        String profileImage =
                MovieService.IMAGES_BASE_URL + MovieService.IMAGE_SIZE_SMALL + casts.get(position).getProfilePath();
        Picasso.get().load(profileImage).into(holder.imageView);
        holder.txt_cast_name.setText(casts.get(position).getCharacter());

    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView txt_cast_name;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_cast_profile);
            txt_cast_name = itemView.findViewById(R.id.text_cast_film);
        }
    }
}
