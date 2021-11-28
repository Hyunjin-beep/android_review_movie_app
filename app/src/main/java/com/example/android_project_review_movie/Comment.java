package com.example.android_project_review_movie;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment  implements Parcelable {
    String content, uID, movieID, userEmail, contentID;
    Object timestamp;
    Reply reply;

    public Comment(String content, String uID, String movieID, String userEmail, String contentID, Object timestamp, Reply reply) {
        this.content = content;
        this.uID = uID;
        this.movieID = movieID;
        this.userEmail = userEmail;
        this.contentID = contentID;
        this.timestamp = timestamp;
        this.reply = reply;
    }

    public Comment(String content, String uID, String movieID, String userEmail, String contentID, Reply reply) {
        this.content = content;
        this.uID = uID;
        this.movieID = movieID;
        this.userEmail = userEmail;
        this.contentID = contentID;
        this.reply = reply;
    }

    public Comment(String movieID){
        this.movieID = movieID;
    }

    public Comment(String content, String uID, String movieID, String userEmail, String contentID) {
        this.content = content;
        this.uID = uID;
        this.movieID = movieID;
        this.userEmail = userEmail;
        this.contentID = contentID;
    }

    protected Comment(Parcel in) {
        content = in.readString();
        uID = in.readString();
        movieID = in.readString();
        userEmail = in.readString();
        contentID = in.readString();
        reply = in.readParcelable(Reply.class.getClassLoader());
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public Comment(){

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(uID);
        dest.writeString(movieID);
        dest.writeString(userEmail);
        dest.writeString(contentID);
        dest.writeParcelable(reply, flags);
    }
}
