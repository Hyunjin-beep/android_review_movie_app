package com.example.android_project_review_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MovieDetail extends AppCompatActivity {

    ImageView iv_cover_detail;
    EditText et_add_comment;
    TextView tv_content_detail, tv_date, tv_title_detail, tv_userEmail;
    Button btn_playlist, btn_add_comment;

    String content, title, date, img_path, id, userID, userName;

    //root
    FirebaseDatabase rootNode;
    //sub
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        et_add_comment = findViewById(R.id.et_add_comment);
        tv_userEmail = findViewById(R.id.tv_userEmail);
        btn_add_comment = findViewById(R.id.btn_add_comment);


        if(getIntent().hasExtra("key")) {
            MovieModel movie = getIntent().getExtras().getParcelable("key");
            content = movie.getOverview();
            title = movie.getTitle();
            date = movie.getRelease_date();
            img_path = movie.getImg_path();
            id = movie.getID();

        } else{
            MovieFieldModel movieFieldModel = (MovieFieldModel)getIntent().getSerializableExtra("key2");

            content = movieFieldModel.getOverview();
            title = movieFieldModel.getTitle();
            date = movieFieldModel.getRelease_date();
            img_path = movieFieldModel.getImg_path();
            id = movieFieldModel.getID();
        }

        tv_content_detail = findViewById(R.id.tv_content_detail);
        tv_title_detail = findViewById(R.id.tv_title_detail);
        tv_date = findViewById(R.id.tv_date);
        iv_cover_detail = findViewById(R.id.iv_cover_detail);

        tv_content_detail.setText(content);
        tv_title_detail.setText(title);
        tv_date.setText(date);
        Glide.with(MovieDetail.this).load(img_path).into(iv_cover_detail);

        btn_playlist = findViewById(R.id.btn_playlist);
        btn_playlist.setOnClickListener(addMovieToList);

        //user infor
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        userID = firebaseUser.getUid();
        userName = firebaseUser.getEmail();
        tv_userEmail.setText(userName);

        btn_add_comment.setOnClickListener(addComment);


    }

    @Override
    protected void onStart() {
        super.onStart();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Comments");

        ListView lv_comment = (ListView) findViewById(R.id.lv_Listview);
        ArrayList<User> userArrayList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    User user = childSnapshot.getValue(User.class);
                    userArrayList.add(user);
                }
                CommentAdapter adapter = new CommentAdapter(MovieDetail.this, R.layout.comment_list, userArrayList);
                lv_comment.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MovieDetail.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private final View.OnClickListener addMovieToList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Playlist");

            User user = new User(userID, id);
            reference.child(userID).setValue(user);

        }
    };

    private final View.OnClickListener addComment = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String comment = et_add_comment.getText().toString();
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Comments");

            User user = new User(userID, userName, comment, id);

            reference.child(userID).setValue(user);
            Log.d("hc", id);
        }
    };

}