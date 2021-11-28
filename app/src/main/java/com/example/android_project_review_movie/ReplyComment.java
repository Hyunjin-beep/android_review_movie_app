package com.example.android_project_review_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReplyComment extends AppCompatActivity {

    TextView tv_email_reply, tv_comment_reply;
    Button btn_add_comment_reply;
    EditText et_add_comment_reply;

    //root
    FirebaseDatabase rootNode;
    //sub
    DatabaseReference reference;

    String userID, userName;

    ArrayList<Reply> replies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_comment);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        tv_email_reply = findViewById(R.id.tv_email_reply);
        tv_comment_reply = findViewById(R.id.tv_comment_reply);
        btn_add_comment_reply = findViewById(R.id.btn_add_comment_reply);
        et_add_comment_reply = findViewById(R.id.et_add_comment_reply);

        Comment comment = getIntent().getExtras().getParcelable("comment");

        String email = comment.getUserEmail();
        String content = comment.getContent();

        tv_email_reply.setText(email);
        tv_comment_reply.setText(content);

        btn_add_comment_reply.setOnClickListener(addComment);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        userID = firebaseUser.getUid();
        userName = firebaseUser.getEmail();

    }

    @Override
    protected void onStart() {
        super.onStart();
        ListView lv_reply = findViewById(R.id.lv_Listview_reply);
        Comment comment = getIntent().getExtras().getParcelable("comment");

        rootNode = FirebaseDatabase.getInstance();
        DatabaseReference replyRef = rootNode.getReference("Reply").child(comment.getContentID());

        replyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                replies =  new ArrayList<>();
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    Reply reply = childSnapshot.getValue(Reply.class);
                    replies.add(reply);
                }

                ReplyAdapter replyAdapter = new ReplyAdapter(ReplyComment.this, R.layout.reply_list, replies);
                lv_reply.setAdapter(replyAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private final View.OnClickListener addComment = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Comment comment = getIntent().getExtras().getParcelable("comment");
            btn_add_comment_reply.setVisibility(View.INVISIBLE);

            String key = comment.getContentID();

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Reply").child(comment.getContentID()).push();

            String content = et_add_comment_reply.getText().toString();
            String replyKey = reference.getKey();

            Reply reply = new Reply(content, userID, comment.getMovieID(), userName, key, replyKey);

            reference.setValue(reply).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ReplyComment.this, "comment added", Toast.LENGTH_SHORT).show();
                    et_add_comment_reply.setText("");
                    btn_add_comment_reply.setVisibility(View.VISIBLE);
                }
            });

        }
    };

}