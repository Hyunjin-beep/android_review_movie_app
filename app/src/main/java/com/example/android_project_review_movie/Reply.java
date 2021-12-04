package com.example.android_project_review_movie;

import android.os.Parcel;
import android.os.Parcelable;

public class Reply implements Parcelable {
    String reply, uID, movieID, userEmail, originalID, replyID, date;

    Reply(){

    }

    protected Reply(Parcel in) {
        reply = in.readString();
        uID = in.readString();
        movieID = in.readString();
        userEmail = in.readString();
        originalID = in.readString();
        replyID = in.readString();
        date = in.readString();
    }

    public static final Creator<Reply> CREATOR = new Creator<Reply>() {
        @Override
        public Reply createFromParcel(Parcel in) {
            return new Reply(in);
        }

        @Override
        public Reply[] newArray(int size) {
            return new Reply[size];
        }
    };

    public String getOriginalID() {
        return originalID;
    }

    public void setOriginalID(String originalID) {
        this.originalID = originalID;
    }

    public Reply(String reply, String uID, String movieID, String userEmail, String originalID, String replyID) {
        this.reply = reply;
        this.uID = uID;
        this.movieID = movieID;
        this.userEmail = userEmail;
        this.originalID = originalID;
        this.replyID = replyID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Reply(String reply, String uID, String movieID, String userEmail, String originalID, String replyID, String date) {
        this.reply = reply;
        this.uID = uID;
        this.movieID = movieID;
        this.userEmail = userEmail;
        this.originalID = originalID;
        this.replyID = replyID;
        this.date = date;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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

    @Override
    public int describeContents() {
        return 0;
    }

    public String getReplyID() {
        return replyID;
    }

    public void setReplyID(String replyID) {
        this.replyID = replyID;
    }

    public static Creator<Reply> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reply);
        dest.writeString(uID);
        dest.writeString(movieID);
        dest.writeString(userEmail);
        dest.writeString(originalID);
        dest.writeString(date);
        dest.writeString(replyID);
    }
}
