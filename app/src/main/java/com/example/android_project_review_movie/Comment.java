package com.example.android_project_review_movie;

import com.google.firebase.database.ServerValue;

public class Comment {
    String content, uID, movieID, userEmail;
    Object timestamp;

    Comment(){

    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    Comment(String content, String uID, String movieID, String userEmail){
        this.content = content;
        this.uID = uID;
        this.movieID = movieID;
        this.userEmail = userEmail;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public Comment(String content, String uID, String movieID, String userEmail, Object timestamp) {
        this.content = content;
        this.uID = uID;
        this.movieID = movieID;
        this.userEmail = userEmail;
        this.timestamp = timestamp;
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

    public String getUserEmail(){
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
