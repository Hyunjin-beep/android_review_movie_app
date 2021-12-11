package com.example.android_project_review_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyAccount extends AppCompatActivity {

    //root
    FirebaseDatabase rootNode;
    //sub
    DatabaseReference playRef, reference;

    ArrayList<Playlist> playlistArrayList, reviewedArrayList;
    ArrayList<String> movieIDArrayList, checkArrayList;
    ArrayList<Playlist> newList;

    Button btn_logout;

    TextView tv_userEmail_account;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        btn_logout = findViewById(R.id.btn_logout);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        String uID = firebaseUser.getUid();
        //Toast.makeText(MyAccount.this, "Uid: " + firebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        tv_userEmail_account = findViewById(R.id.tv_userEmail_account);
        tv_userEmail_account.setText(firebaseUser.getEmail() + "!");


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

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MyAccount.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(MyAccount.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerViewReviewed = findViewById(R.id.recyclerViewViewed);
        LinearLayoutManager linearLayoutManagerForReviewed = new LinearLayoutManager(getApplicationContext());
        reference = rootNode.getReference("CommentUnderUserID").child(uID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reviewedArrayList = new ArrayList<>();
                movieIDArrayList = new ArrayList<>();
                newList = new ArrayList<>();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    Playlist playlist = childSnapshot.getValue(Playlist.class);
                    reviewedArrayList.add(playlist);

                }

                for(int i=0; i < reviewedArrayList.size(); i++){
                    String mID = reviewedArrayList.get(i).mID;
                    if(!movieIDArrayList.contains(mID)){
                        movieIDArrayList.add(mID);
                        newList.add(reviewedArrayList.get(i));
                    }
                }


                linearLayoutManagerForReviewed.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewReviewed.setLayoutManager(linearLayoutManagerForReviewed);
                recyclerViewReviewed.setItemAnimator(new DefaultItemAnimator());

                PlayListAdapter playListAdapterForReviewed = new PlayListAdapter(MyAccount.this, newList);
                recyclerViewReviewed.setAdapter(playListAdapterForReviewed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

//    public static <String> ArrayList<String> removeDuplicates(ArrayList<String> list){
//        ArrayList<String> newList = new ArrayList<>();
//
//        for(String element : list){
//            if(!newList.contains(element)){
//                newList.add(element);
//            }
//
//        }
//
//        return newList;
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