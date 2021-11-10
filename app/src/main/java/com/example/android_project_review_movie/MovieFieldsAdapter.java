package com.example.android_project_review_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieFieldsAdapter extends RecyclerView.Adapter<MovieFieldsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<MovieFieldModel> movieFieldModels;

    public MovieFieldsAdapter(Context context, ArrayList<MovieFieldModel> movieFieldModels) {
        this.context = context;
        this.movieFieldModels = movieFieldModels;
    }


    @NonNull
    @Override
    public MovieFieldsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.movie_field_list, parent, false);
        return new MyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieFieldsAdapter.MyViewHolder holder, int position) {
        MovieFieldModel movieFieldModel = movieFieldModels.get(position);
        Glide.with(context).load(movieFieldModel.getImg_path()).into(holder.ibtn_cover);

    }

    @Override
    public int getItemCount() {
        return movieFieldModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageButton ibtn_cover;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ibtn_cover = itemView.findViewById(R.id.ibtn_cover);
        }
    }
}
