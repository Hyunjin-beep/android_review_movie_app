package com.example.android_project_review_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieModel>{

    private final Context context;
    private final int movie_layoutID;

    public MovieAdapter(MainActivity context, int movie_layoutID, ArrayList<MovieModel> movies) {
        super(context, movie_layoutID, movies);

        this.context = context;
        this.movie_layoutID = movie_layoutID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(movie_layoutID, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        ImageView ivCover = convertView.findViewById(R.id.iv_cover);

        MovieModel movieModel = getItem(position);

        tvTitle.setText(movieModel.getTitle());
        Glide.with(context).load(movieModel.getImg_path()).into(ivCover);


        return convertView;

    }
}