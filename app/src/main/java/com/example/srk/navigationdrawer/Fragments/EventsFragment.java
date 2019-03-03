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

import com.example.srk.navigationdrawer.Adapter.Viewholder_Firebase_Events;
import com.example.srk.navigationdrawer.GetData_From_FireBase;
import com.example.srk.navigationdrawer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventsFragment extends Fragment {

    private RecyclerView mrecycler;
    public static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;

    static boolean calledAlready_offlinemode = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        //Initiate RecyclerView
        mrecycler = (RecyclerView) view.findViewById(R.id.myrecycler);
        mrecycler.setHasFixedSize(true);
        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (!calledAlready_offlinemode) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready_offlinemode = true;
        }

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef = mfirebaseDatabase.getReference("Data");
        mdatabaseRef.keepSynced(true);


        FirebaseRecyclerAdapter<GetData_From_FireBase, Viewholder_Firebase_Events> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<GetData_From_FireBase, Viewholder_Firebase_Events>(GetData_From_FireBase.class,
                R.layout.events_cardview, Viewholder_Firebase_Events.class, mdatabaseRef) {
            @Override
            protected void populateViewHolder(Viewholder_Firebase_Events viewHolder, GetData_From_FireBase model, int position) {
                viewHolder.setDetails(getActivity(), model.getTitle(), model.getDescription(), model.getImage(), model.getTime());
            }

            @Override
            public Viewholder_Firebase_Events onCreateViewHolder(ViewGroup parent, int viewType) {
                Viewholder_Firebase_Events h = super.onCreateViewHolder(parent, viewType);

                return h;
            }
        };

        mrecycler.setAdapter(firebaseRecyclerAdapter);


        return view;

    }
}
