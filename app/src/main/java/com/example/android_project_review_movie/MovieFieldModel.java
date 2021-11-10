package com.example.android_project_review_movie;

public class MovieFieldModel {
    String img_path;

    public MovieFieldModel(String img_path){
        this.img_path = img_path;
    }

    public MovieFieldModel(){
    }

    public String getImg_path(){
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
