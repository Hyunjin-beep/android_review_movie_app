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

public class ReplyAdapter extends ArrayAdapter<Reply> {

    private final Context context;
    private final int reply_layoutID;

    public ReplyAdapter(@NonNull Context context, int reply_layoutID, @NonNull ArrayList<Reply> replies) {
        super(context, reply_layoutID, replies);
        this.context = context;
        this.reply_layoutID = reply_layoutID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(reply_layoutID, parent, false);
        }

        Reply reply = getItem(position);

        TextView tv_email_reply = convertView.findViewById(R.id.tv_email_reply_reply);
        TextView tv_comment_reply = convertView.findViewById(R.id.tv_comment_reply_reply);

        tv_email_reply.setText(reply.getUserEmail());
        tv_comment_reply.setText(reply.getReply());
        return convertView;
    }
}
