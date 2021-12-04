package com.example.android_project_review_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {

    private final Context context;
    private final int comment_layoutID;

    public CommentAdapter(@NonNull Context context, int comment_layoutID, @NonNull ArrayList<Comment> comments) {
        super(context, comment_layoutID, comments);
        this.context = context;
        this.comment_layoutID = comment_layoutID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(comment_layoutID, parent, false);

        }

        Comment comment = getItem(position);

        TextView tv_userEmail_comment = convertView.findViewById(R.id.tv_userEmail_comment);
        TextView tv_comment_comment = convertView.findViewById(R.id.tv_comment_comment);
        TextView tv_time_comment = convertView.findViewById(R.id.tv_time_comment);

        tv_userEmail_comment.setText(comment.getUserEmail());
        tv_comment_comment.setText(comment.getContent());
        tv_time_comment.setText(comment.getDate());

        return convertView;

    }
}
