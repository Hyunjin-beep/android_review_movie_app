package com.example.android_project_review_movie;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reviewed#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Reviewed extends Fragment {

    //root
    FirebaseDatabase rootNode;
    //sub
    DatabaseReference playRef, reference;

    ArrayList<Playlist> playlistArrayList, reviewedArrayList;
    ArrayList<String> movieIDArrayList, checkArrayList;
    ArrayList<Playlist> newList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Reviewed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reviewed.
     */
    // TODO: Rename and change types and number of parameters
    public static Reviewed newInstance(String param1, String param2) {
        Reviewed fragment = new Reviewed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_reviewed, container, false);

        View view = inflater.inflate(R.layout.fragment_reviewed, container, false);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        String uID = firebaseUser.getUid();

        RecyclerView recyclerViewReviewed = view.findViewById(R.id.recyclerViewForReviewedFrg);
        LinearLayoutManager linearLayoutManagerForReviewed = new LinearLayoutManager(getActivity());

        rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference2 = rootNode.getReference("CommentUnderUserID").child(uID);
        reference2.addValueEventListener(new ValueEventListener() {
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


                int numberOfColumns = 3;
                recyclerViewReviewed.setLayoutManager(new GridLayoutManager(view.getContext(), numberOfColumns));


                PlayListAdapter playListAdapterForReviewed = new PlayListAdapter(view.getContext(), newList);
                recyclerViewReviewed.setAdapter(playListAdapterForReviewed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;

    }


}