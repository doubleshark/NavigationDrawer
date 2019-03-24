package com.example.srk.navigationdrawer.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.srk.navigationdrawer.Adapter.Fine_Viewholder_Adapter;
import com.example.srk.navigationdrawer.Others.Fine_pojo;
import com.example.srk.navigationdrawer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentFine extends Fragment {


    View view;

    private RecyclerView mrecyclerView;
    public static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;

    public CardView card_outer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fine_fragment, container, false);

        mrecyclerView = view.findViewById(R.id.fine_recyclerview);
       // mrecyclerView.setHasFixedSize(true);   // fuck... simply remove this shit of code and its working
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef = mfirebaseDatabase.getReference("Fine");
        mdatabaseRef.keepSynced(true);

        card_outer = view.findViewById(R.id.card_outer);

        FirebaseRecyclerAdapter<Fine_pojo, Fine_Viewholder_Adapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Fine_pojo, Fine_Viewholder_Adapter>(
                Fine_pojo.class, R.layout.fine_activities_item, Fine_Viewholder_Adapter.class, mdatabaseRef) {

            @Override
            protected void populateViewHolder(Fine_Viewholder_Adapter viewHolder, Fine_pojo model, int position) {

                viewHolder.setvalue(model.getBooktitle(), model.getAccno(), model.getAuthorname(), model.getIssuedate(), model.getReturndate());

            }

            @Override
            public Fine_Viewholder_Adapter onCreateViewHolder(ViewGroup parent, int viewType) {

                Fine_Viewholder_Adapter fine_viewholder_adapter = super.onCreateViewHolder(parent, viewType);
                fine_viewholder_adapter.setOnClickListener(new Fine_Viewholder_Adapter.clickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        // use for select the item
                        if (Fine_pojo.selecter && !(Fine_pojo.selected.contains((Integer)position))) {

                            card_outer.setCardBackgroundColor(Color.rgb(98, 0, 238));

                            Fine_pojo.selected.add((Integer) position);
                            Toast.makeText(getActivity(), "" + Fine_pojo.selected, Toast.LENGTH_SHORT).show();

                        }

                        // use for unselect the item
                        else if (Fine_pojo.selecter && Fine_pojo.selected.contains((Integer)position)){

                            card_outer.setCardBackgroundColor(Color.parseColor("#00000000"));

                            Fine_pojo.selected.remove((Integer) position);
                            Toast.makeText(getActivity(), "" + Fine_pojo.selected, Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getActivity(), "Press Long for selection", Toast.LENGTH_SHORT).show();
                        }


                        // this help to active or deactive the long press selectetion
                        if (Fine_pojo.selected.isEmpty()) {
                            Fine_pojo.selecter = false;
                        }


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        if (!Fine_pojo.selecter) {
                            card_outer.setCardBackgroundColor(Color.rgb(98, 0, 238));

                            Toast.makeText(getActivity(), "" + Fine_pojo.selected, Toast.LENGTH_SHORT).show();
                            //Fine_pojo.selecter = true;
                        }


                    }
                });

                return fine_viewholder_adapter;
            }
        };

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);



        return view;
    }

}
