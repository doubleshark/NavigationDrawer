package com.example.srk.navigationdrawer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EventsFragment extends Fragment {

    private RecyclerView mrecycler;
    public static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;

    FirebaseRecyclerAdapter adapter;


    private LottieAnimationView lottieAnimationView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_events, container, false);

        // Loading before show cardview in events fragments
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.lottie_view);

        //Initiate RecyclerView
        mrecycler = (RecyclerView) view.findViewById(R.id.recyclerview_events);
        mrecycler.setHasFixedSize(true);
        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef = mfirebaseDatabase.getReference("Data");
        mdatabaseRef.keepSynced(true);

        Query query = mdatabaseRef.limitToLast(50);
        FirebaseRecyclerOptions<Getter_Setter> options = new FirebaseRecyclerOptions.Builder<Getter_Setter>().setQuery(query,Getter_Setter.class).build();


//        FirebaseRecyclerAdapter<Getter_Setter, Viewholder_Firebase_Events> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Getter_Setter, Viewholder_Firebase_Events>(Getter_Setter.class,
//                R.layout.events_cardview, Viewholder_Firebase_Events.class, mdatabaseRef) {
//

        adapter = new FirebaseRecyclerAdapter<Getter_Setter,Viewholder_Firebase_Events>(options) {


            @NonNull
            @Override
            public Viewholder_Firebase_Events onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.events_cardview,viewGroup,false);
                return new Viewholder_Firebase_Events(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull Viewholder_Firebase_Events holder, int position, @NonNull Getter_Setter model) {
                holder.setDetails(getActivity(), model.getTitle(), model.getDescription(), model.getImage(), model.getTime());
                //we gone the visibily of loading
                lottieAnimationView.setVisibility(View.GONE);

                holder.setOnClickListener(new Viewholder_Firebase_Events.clickListener() {
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

            }

//            @Override
//            protected void populateViewHolder(Viewholder_Firebase_Events viewHolder, Getter_Setter model, int position) {
//
//                viewHolder.setDetails(getActivity(), model.getTitle(), model.getDescription(), model.getImage(), model.getTime());
//                //we gone the visibily of loading
//                lottieAnimationView.setVisibility(View.GONE);
//            }

//            @Override
//            public Viewholder_Firebase_Events onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                Viewholder_Firebase_Events viewholder_firebase_events = super.onCreateViewHolder(parent, viewType);
//                viewholder_firebase_events.setOnClickListener(new Viewholder_Firebase_Events.clickListener() {
//
//                    @Override
//                    public void onItemClick(View view, int position) {
//
//                        TextView title = (TextView)view.findViewById(R.id.title);
//                        String tit = title.getText().toString();
//
//                        Intent intent = new Intent(view.getContext(), Events_Detail.class);
//                        intent.putExtra("title",tit);
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void onItemLongClick(View view, int position) {
//
//                    }
//                });
//
//                return viewholder_firebase_events;
//            }
        };

        mrecycler.setAdapter(adapter);


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        adapter.stopListening();
    }
}
