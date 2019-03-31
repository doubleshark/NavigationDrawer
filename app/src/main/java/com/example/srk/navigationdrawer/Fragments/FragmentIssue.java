package com.example.srk.navigationdrawer.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.srk.navigationdrawer.Adapter.ActivitiesAdapter;
import com.example.srk.navigationdrawer.Others.ActivitiesItem;
import com.example.srk.navigationdrawer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentIssue extends Fragment {

    View view;

    private RecyclerView mrecyclerView;
    public static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;

    ArrayList<ActivitiesItem> list;
    ActivitiesAdapter activitiesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.issue_fragment, container, false);

        //Initiate RecyclerView
        mrecyclerView = (RecyclerView) view.findViewById(R.id.issue_recyclerview);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<ActivitiesItem>();


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef = mfirebaseDatabase.getReference("Issue");
        mdatabaseRef.keepSynced(true);

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


        return view;
    }

}
