package com.example.srk.navigationdrawer.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.srk.navigationdrawer.Adapter.ActivitiesAdapter;
import com.example.srk.navigationdrawer.Adapter.Viewholder_Firebase_Events;
import com.example.srk.navigationdrawer.Adapter.fine;
import com.example.srk.navigationdrawer.Others.ActivitiesItem;
import com.example.srk.navigationdrawer.Others.Fine_ActivitiesItem;
import com.example.srk.navigationdrawer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentFine extends Fragment {


    View view;

    private RecyclerView mrecyclerView;
    public static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;

    //ArrayList<ActivitiesItem> list;
    //ActivitiesAdapter activitiesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fine_fragment, container, false);

        //Initiate RecyclerView
        mrecyclerView = (RecyclerView) view.findViewById(R.id.issue_recyclerview);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //list = new ArrayList<ActivitiesItem>();


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef = mfirebaseDatabase.getReference("Fine");
        mdatabaseRef.keepSynced(true);

        FirebaseRecyclerAdapter<Fine_ActivitiesItem, fine> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Fine_ActivitiesItem, fine>(Fine_ActivitiesItem.class, R.layout.fine_activities_item, fine.class, mdatabaseRef) {
            @Override
            protected void populateViewHolder(fine viewHolder, Fine_ActivitiesItem model, int position) {

                viewHolder.setDetails(model.getBooktitle(), model.getAccno(), model.getAuthorname(), model.getIssuedate(), model.getReturndate());

            }

            @Override
            public fine onCreateViewHolder(ViewGroup parent, int viewType) {
                fine fine = super.onCreateViewHolder(parent, viewType);
                fine.setOnClickListener(new fine.clickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });

                return fine;

            }
        };

/*
        mdatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    ActivitiesItem activitiesItem = dataSnapshot1.getValue(ActivitiesItem.class);
                    list.add(activitiesItem);
                }

                activitiesAdapter = new ActivitiesAdapter(list);
                mrecyclerView.setAdapter(activitiesAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });
*/

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
        return view;
    }

}
