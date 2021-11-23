package com.example.android_project_review_movie;

public class User {

    String uID;
    String comment;
    String movieID;
    String userEmail;

    User(){

    }

    User(String uID, String movieID){
        this.uID = uID;
        this.movieID = movieID;
    }

    User(String uID, String userEmail, String comment, String movieID){
        this.uID = uID;
        this.comment = comment;
        this.movieID = movieID;
        this.userEmail = userEmail;
    }

    public String getComment() {
        return comment;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getuID() {
        return uID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
