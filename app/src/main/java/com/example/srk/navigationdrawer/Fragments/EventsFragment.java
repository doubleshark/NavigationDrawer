package com.example.srk.navigationdrawer.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.srk.navigationdrawer.Activity.Events_Detail;
import com.example.srk.navigationdrawer.Adapter.Viewholder_Firebase_Events;
import com.example.srk.navigationdrawer.Others.Getter_Setter;
import com.example.srk.navigationdrawer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventsFragment extends Fragment {

    private RecyclerView mrecycler;
    public static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;


    private LottieAnimationView lottieAnimationView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        // Loading before show cardview in events fragments
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);

        //Initiate RecyclerView
        mrecycler = (RecyclerView) view.findViewById(R.id.recyclerview_events);
        mrecycler.setHasFixedSize(true);
        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef = mfirebaseDatabase.getReference("Data");
        mdatabaseRef.keepSynced(true);


        FirebaseRecyclerAdapter<Getter_Setter, Viewholder_Firebase_Events> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Getter_Setter, Viewholder_Firebase_Events>(Getter_Setter.class,
                R.layout.events_cardview, Viewholder_Firebase_Events.class, mdatabaseRef) {

            @Override
            protected void populateViewHolder(Viewholder_Firebase_Events viewHolder, Getter_Setter model, int position) {

                viewHolder.setDetails(getActivity(), model.getTitle(), model.getDescription(), model.getImage(), model.getTime());
                //we gone the visibily of loading
                lottieAnimationView.setVisibility(View.GONE);
            }

            @Override
            public Viewholder_Firebase_Events onCreateViewHolder(ViewGroup parent, int viewType) {

                Viewholder_Firebase_Events viewholder_firebase_events = super.onCreateViewHolder(parent, viewType);
                viewholder_firebase_events.setOnClickListener(new Viewholder_Firebase_Events.clickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        TextView title = (TextView)view.findViewById(R.id.title);
                        String tit = title.getText().toString();

                        Intent intent = new Intent(view.getContext(), Events_Detail.class);
                        intent.putExtra("title",tit);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });

                return viewholder_firebase_events;
            }
        };

        mrecycler.setAdapter(firebaseRecyclerAdapter);


        return view;

    }
}
