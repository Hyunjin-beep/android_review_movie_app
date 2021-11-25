package com.example.android_project_review_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MovieDetail extends AppCompatActivity {

    ImageView iv_cover_detail;
    EditText et_add_comment;
    TextView tv_content_detail, tv_date, tv_title_detail, tv_userEmail;
    Button btn_playlist, btn_add_comment, btn_remove;

    String content, title, date, img_path, id, userID, userName;

    //root
    FirebaseDatabase rootNode;
    //sub
    DatabaseReference reference, playRef;

    ArrayList<Comment> comments;
    static String COMMENT_KEY_DB = "Comments";
    static String PLAYLIST_KEY_DB = "Playlist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        et_add_comment = findViewById(R.id.et_add_comment);
        tv_userEmail = findViewById(R.id.tv_userEmail);
        btn_add_comment = findViewById(R.id.btn_add_comment);
        btn_remove = findViewById(R.id.btn_remove);


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


        //user infor
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        userID = firebaseUser.getUid();
        userName = firebaseUser.getEmail();
        tv_userEmail.setText(userName);

        rootNode = FirebaseDatabase.getInstance();
        DatabaseReference playcheckref = rootNode.getReference().child(PLAYLIST_KEY_DB).child(userID);
        playcheckref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if(Objects.requireNonNull(dataSnapshot.getValue(Playlist.class)).getmID().equals(id)){
                        btn_playlist.setVisibility(View.INVISIBLE);
                        btn_playlist.setEnabled(false);
                        btn_remove.setVisibility(View.VISIBLE);
                        btn_remove.setEnabled(true);

                        String vID = Objects.requireNonNull(dataSnapshot.getValue(Playlist.class)).getvID();
                        btn_remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                btn_playlist.setVisibility(View.VISIBLE);
                                btn_playlist.setEnabled(true);
                                btn_remove.setVisibility(View.INVISIBLE);
                                btn_remove.setEnabled(false);

                                DatabaseReference removeRef = rootNode.getReference("Playlist").child(userID).child(vID);
                                removeRef.setValue(null);

                                Toast.makeText(MovieDetail.this, "Successfully deleted" + Objects.requireNonNull(dataSnapshot.getValue(Playlist.class)).getvID(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else{
                        btn_playlist.setOnClickListener(addMovieToList);
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_add_comment.setOnClickListener(addComment);
        btn_playlist.setOnClickListener(addMovieToList);

    }


    @Override
    protected void onStart() {
        super.onStart();
        ListView lv_comment = findViewById(R.id.lv_Listview);

        rootNode = FirebaseDatabase.getInstance();
        DatabaseReference commentRef = rootNode.getReference(COMMENT_KEY_DB).child(id);

        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comments = new ArrayList<>();
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    Comment comment = childSnapshot.getValue(Comment.class);
                    comments.add(comment);
                }
                CommentAdapter adapter = new CommentAdapter(MovieDetail.this, R.layout.comment_list, comments);
                lv_comment.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private final View.OnClickListener addMovieToList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playRef = rootNode.getReference("Playlist").child(userID).push();

            Playlist playlist = new Playlist(id, userID, img_path, playRef.getKey());

            playRef.setValue(playlist).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(MovieDetail.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    btn_playlist.setVisibility(View.INVISIBLE);
                    btn_playlist.setEnabled(false);
                    btn_remove.setVisibility(View.VISIBLE);
                    btn_remove.setEnabled(true);
                }
            });

        }
    };

    private final View.OnClickListener addComment = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            btn_add_comment.setVisibility(View.INVISIBLE);
            reference = rootNode.getReference(COMMENT_KEY_DB).child(id).push();
            String content = et_add_comment.getText().toString();
            Comment comment = new Comment(content, userID, id, userName);

            reference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(MovieDetail.this, "comment added", Toast.LENGTH_SHORT).show();
                    et_add_comment.setText("");
                    btn_add_comment.setVisibility(View.VISIBLE);
                }
            });

        }
    };

}