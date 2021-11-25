package com.example.android_project_review_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAccount extends AppCompatActivity {

    //root
    FirebaseDatabase rootNode;
    //sub
    DatabaseReference playRef;

    ArrayList<Playlist> playlistArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        String uID = firebaseUser.getUid();
        //Toast.makeText(MyAccount.this, "Uid: " + firebaseUser.getUid(), Toast.LENGTH_SHORT).show();


        RecyclerView recyclerViewPlaylist = findViewById(R.id.recyclerViewPlayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        rootNode = FirebaseDatabase.getInstance();
        playRef = rootNode.getReference("Playlist").child(uID);
        playRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playlistArrayList = new ArrayList<>();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    Playlist playlist = childSnapshot.getValue(Playlist.class);
                    playlistArrayList.add(playlist);
                }

                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewPlaylist.setLayoutManager(linearLayoutManager);
                recyclerViewPlaylist.setItemAnimator(new DefaultItemAnimator());

                PlayListAdapter playListAdapter = new PlayListAdapter(MyAccount.this, playlistArrayList);
                recyclerViewPlaylist.setAdapter(playListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyAccount.this, "failed to load movie list", Toast.LENGTH_SHORT).show();
            }
        });





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