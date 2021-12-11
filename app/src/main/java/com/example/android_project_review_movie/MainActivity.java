package com.example.android_project_review_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private String movie_db_url = "https://api.themoviedb.org/3/movie/popular?api_key=a7dab8c476fb31214a42e7867fa023b2";
    private String movie_db_genres = "https://api.themoviedb.org/3/discover/movie?api_key=a7dab8c476fb31214a42e7867fa023b2&with_genres=";

    Button btn_action, btn_drama, btn_comedy, btn_fantasy, btn_mystery;
    ImageView iv_cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ParseDBURL parseDBURL = new ParseDBURL();
        parseDBURL.execute(movie_db_url);

        btn_action = findViewById(R.id.btn_field_action);
        btn_comedy = findViewById(R.id.btn_field_comedy);
        btn_drama = findViewById(R.id.btn_field_drama);
        btn_fantasy = findViewById(R.id.btn_field_fantasy);
        btn_mystery = findViewById(R.id.btn_field_mystery);

        btn_action.setOnClickListener(new buttonTestSecond(28));
        btn_comedy.setOnClickListener(new buttonTestSecond(35));
        btn_drama.setOnClickListener(new buttonTestSecond(18));
        btn_fantasy.setOnClickListener(new buttonTestSecond(14));
        btn_mystery.setOnClickListener(new buttonTestSecond(9648));

    }


    private class buttonTestSecond implements View.OnClickListener {
        int genre_code;
        public buttonTestSecond(int genre_code) {
            this.genre_code = genre_code;
        }

        @Override
        public void onClick(View v)
        {
            ParseDBURLForField parseDBURLForField = new ParseDBURLForField();
            parseDBURLForField.execute(movie_db_genres + this.genre_code);
            Log.d("error", movie_db_genres + this.genre_code);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.menu_account){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the options menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class ParseDBURLForField extends AsyncTask<String, String, String> {
        ArrayList<MovieFieldModel> movieFieldModels = new ArrayList<>();
        RecyclerView recyclerViewForField = findViewById(R.id.recyclerViewForField);
        String img_path_url = "https://image.tmdb.org/t/p/w500";

        protected  ParseDBURLForField(){
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return ParsingJSON(params);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray movies = obj.getJSONArray("results");

                for(int i=0; i< movies.length(); i++){
                    JSONObject object = movies.getJSONObject(i);

                    String title = object.getString("title");
                    String date = object.getString("release_date");
                    String img_path = img_path_url + object.getString("poster_path");
                    String id = object.getString("id");
                    String overview = object.getString("overview");

                    MovieFieldModel model = new MovieFieldModel();
                    model.setTitle(title);
                    model.setRelease_date(date);
                    model.setImg_path(img_path);
                    model.setId(id);
                    model.setOverview(overview);
                    movieFieldModels.add(model);

                }

                int numberOfColumns = 3;
                recyclerViewForField.setLayoutManager(new GridLayoutManager(MainActivity.this, numberOfColumns));

                MovieFieldsAdapter adapter = new MovieFieldsAdapter(MainActivity.this, movieFieldModels);
                recyclerViewForField.setAdapter(adapter);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    private class ParseDBURL extends AsyncTask<String, String, String> {
        ArrayList<MovieModel> movieModels = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        String img_path_url = "https://image.tmdb.org/t/p/w500";

        protected  ParseDBURL(){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            return ParsingJSON(params);
        }

        @Override
        protected void onPostExecute(String s) {
            //Bitmap bitmap = null;
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray movies = obj.getJSONArray("results");

                for(int i=0; i< movies.length(); i++){
                    JSONObject object = movies.getJSONObject(i);

                    String title = object.getString("title");
                    String date = object.getString("release_date");
                    String img_path = img_path_url + object.getString("poster_path");
                    String id = object.getString("id");
                    String overview = object.getString("overview");

                    MovieModel model = new MovieModel();
                    model.setTitle(title);
                    model.setRelease_date(date);
                    model.setImg_path(img_path);
                    model.setId(id);
                    model.setOverview(overview);
                    // Log.d("adf", img_path);
                    movieModels.add(model);

                }

                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                MoviesAdapter adapter = new MoviesAdapter(MainActivity.this, movieModels);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new MoviesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.d("qwe", String.valueOf(movieModels.get(position).getID()));
                        Intent intent = new Intent(MainActivity.this, MovieDetail.class);

                        intent.putExtra("key", movieModels.get(position));
                        startActivity(intent);
                    }
                });
                Log.d("adf", "adapter");

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public String ParsingJSON(String... params){
        StringBuilder current = new StringBuilder();
        HttpsURLConnection connection = null;
        InputStreamReader inputStreamReader;
        try{
            URL url = new URL(params[0]);
            connection = (HttpsURLConnection) url.openConnection();

            InputStream stream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(stream);

            int data = inputStreamReader.read();
            while(data != -1){
                current.append((char) data);
                data = inputStreamReader.read();
            }
            return current.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(connection != null){
                connection.disconnect();
            }
        }
        return null;
    }
}