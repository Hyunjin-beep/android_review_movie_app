package com.example.android_project_review_movie;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieFieldsAdapter extends RecyclerView.Adapter<MovieFieldsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<MovieFieldModel> movieModels;


    public MovieFieldsAdapter(Context context, ArrayList<MovieFieldModel> movieModels) {
        this.context = context;
        this.movieModels = movieModels;
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
        MovieFieldModel movieFieldModel = movieModels.get(position);
        Glide.with(context).load(movieFieldModel.getImg_path()).into(holder.ibtn_cover);

        holder.ibtn_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("1234", String.valueOf(movieFieldModel.getTitle()));
                Intent intent = new Intent(context, MovieDetail.class);

                intent.putExtra("key2", movieFieldModel);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageButton ibtn_cover;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ibtn_cover = itemView.findViewById(R.id.ibtn_cover);

        }
    }
}
