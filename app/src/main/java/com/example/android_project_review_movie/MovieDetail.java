package com.example.android_project_review_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetail extends AppCompatActivity {

    ImageView iv_cover_detail;
    TextView tv_content_detail, tv_date, tv_title_detail;
    Button btn_playlist;

    String content, title, date, img_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        if(getIntent().hasExtra("key")) {
            MovieModel movie = getIntent().getExtras().getParcelable("key");
            Log.d("check", "movie");
            content = movie.getOverview();
            title = movie.getTitle();
            date = movie.getRelease_date();
            img_path = movie.getImg_path();

        } else{
            MovieFieldModel movieFieldModel = (MovieFieldModel)getIntent().getSerializableExtra("key2");
            Log.d("check", movieFieldModel.getOverview());

            content = movieFieldModel.getOverview();
            title = movieFieldModel.getTitle();
            date = movieFieldModel.getRelease_date();
            img_path = movieFieldModel.getImg_path();
        }

        tv_content_detail = findViewById(R.id.tv_content_detail);
        tv_title_detail = findViewById(R.id.tv_title_detail);
        tv_date = findViewById(R.id.tv_date);
        iv_cover_detail = findViewById(R.id.iv_cover_detail);

        tv_content_detail.setText(content);
        tv_title_detail.setText(title);
        tv_date.setText(date);
        Glide.with(MovieDetail.this).load(img_path).into(iv_cover_detail);

    }
}