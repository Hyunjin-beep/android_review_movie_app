package com.example.android_project_review_movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MovieFieldModel implements Serializable {
    String title;
    String release_date;
    String img_path;
    String id;
    String overview;

    public MovieFieldModel(String title, String release_date, String img_path, String id, String overview){
        this.title = title;
        this.release_date = release_date;
        this.img_path = img_path;
        this.id = id;
        this.overview = overview;
    }

    public MovieFieldModel(){

    }

    protected MovieFieldModel(Parcel in) {
        title = in.readString();
        release_date = in.readString();
        img_path = in.readString();
        id = in.readString();
        overview = in.readString();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getImg_path(){
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getID(){
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


}
