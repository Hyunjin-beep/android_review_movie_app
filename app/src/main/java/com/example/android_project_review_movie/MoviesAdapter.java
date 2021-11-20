package com.example.android_project_review_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<MovieModel> movieModels;
    private OnItemClickListener mClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mClickListener = listener;
    }

    public MoviesAdapter(Context context, ArrayList<MovieModel> movieModels) {
        this.context = context;
        this.movieModels = movieModels;
    }

//    public MoviesAdapter(ArrayList<MovieModel> movieModels) {
//        this.movieModels = movieModels;
//    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView;
        LayoutInflater inflater =LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.movies_list, parent, false);

        return new MyViewHolder(convertView, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MovieModel movie = movieModels.get(position);
        holder.title.setText(movie.getTitle());
        Glide.with(context).load(movie.getImg_path()).into(holder.ivCover);

    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView ivCover;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            ivCover = itemView.findViewById(R.id.iv_cover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
