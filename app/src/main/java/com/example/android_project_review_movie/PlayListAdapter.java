package com.example.android_project_review_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Playlist> playlists;

    public PlayListAdapter(Context context, ArrayList<Playlist> playlists){
        this.context = context;
        this.playlists = playlists;
    }



    @NonNull
    @Override
    public PlayListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.movie_field_list, parent, false);

        return new MyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListAdapter.MyViewHolder holder, int position) {
        Playlist playlist = playlists.get(position);
        Glide.with(context).load(playlist.getImg_path()).into(holder.ibtn_cover);
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ibtn_cover;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ibtn_cover = itemView.findViewById(R.id.ibtn_cover);
        }
    }
}
