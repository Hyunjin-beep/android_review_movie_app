package com.example.android_project_review_movie;

public class Playlist {
    String mID, uID, img_path, vID;

    public Playlist(){

    }

    public String getvID() {
        return vID;
    }

    public void setvID(String vID) {
        this.vID = vID;
    }

    public Playlist(String mID, String uID, String img_path, String vID) {
        this.mID = mID;
        this.uID = uID;
        this.img_path = img_path;
        this.vID = vID;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }


}
