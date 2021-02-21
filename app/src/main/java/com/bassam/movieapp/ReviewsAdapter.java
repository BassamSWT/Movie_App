package com.bassam.movieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bassam.movieapp.Models.ReviewResult;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>{
    List<ReviewResult> reviews;

    public ReviewsAdapter(List<ReviewResult> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        return new ReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewResult review = reviews.get(position);
        String userName = review.getAuthor();

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(userName.substring(0,1).toUpperCase(),color);

        holder.imageView.setImageDrawable(drawable);

        holder.textAuthor.setText(userName);

        holder.textContent.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        if(reviews != null){
            return reviews.size();

        }else {
            return 0;
        }
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textAuthor,textContent;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_author);
            textAuthor = itemView.findViewById(R.id.text_author);
            textContent = itemView.findViewById(R.id.txt_content);
        }
    }
}
