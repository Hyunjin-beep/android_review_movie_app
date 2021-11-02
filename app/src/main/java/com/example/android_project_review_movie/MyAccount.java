package com.example.android_project_review_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item){
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the options menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_account, menu);
        return super.onCreateOptionsMenu(menu);
    }

}