package com.example.android_project_review_movie;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    String title;
    String release_date;
    String img_path;
    String id;
    String overview;

    public MovieModel(String title, String release_date, String img_path, String id, String overview){
        this.title = title;
        this.release_date = release_date;
        this.img_path = img_path;
        this.id = id;
        this.overview = overview;
    }

    public MovieModel(){

    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        release_date = in.readString();
        img_path = in.readString();
        id = in.readString();
        overview = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeString(img_path);
        dest.writeString(id);
        dest.writeString(overview);
    }
}