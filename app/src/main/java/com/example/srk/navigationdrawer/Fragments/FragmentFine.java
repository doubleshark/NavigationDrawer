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
import android.widget.Button;
import android.widget.Toast;

import com.example.srk.navigationdrawer.Adapter.Fine_Viewholder_Adapter;
import com.example.srk.navigationdrawer.Others.Fine_pojo;
import com.example.srk.navigationdrawer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FragmentFine extends Fragment {


    View view;

    private RecyclerView mrecyclerView;
    public static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;
    FirebaseRecyclerAdapter adapter;

    FloatingActionButton mfine_fab;


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

        // when fragment is create or restart so all this 2 are in initial stage means card are non selected
        Fine_pojo.selected.clear();
        Fine_pojo.selecter = false;

        mfine_fab = view.findViewById(R.id.fine_fab);
        mfine_fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Fine_pojo.fineamount > 0) {

                    Fine_bottomsheetdialog fineBottomsheetdialog = new Fine_bottomsheetdialog();
                    fineBottomsheetdialog.show(getChildFragmentManager(), "finebottomsheet");

                }

                else {

                    Toast.makeText(getActivity(), "first select something", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Query query = mdatabaseRef.limitToLast(50);
        FirebaseRecyclerOptions<Fine_pojo> options = new FirebaseRecyclerOptions.Builder<Fine_pojo>().setQuery(query,Fine_pojo.class).build();

        adapter = new FirebaseRecyclerAdapter<Fine_pojo, Fine_Viewholder_Adapter>(options) {



            @Override
            protected void onBindViewHolder(@NonNull Fine_Viewholder_Adapter holder, int position, @NonNull Fine_pojo model) {

                holder.setvalue(model.getBooktitle(), model.getAccno(), model.getAuthorname(), model.getBooktype(), model.getFine());

                holder.setOnClickListener(new Fine_Viewholder_Adapter.clickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //write task of onclick in adapter to work correctly



                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        //write task of onlongclick in adapter to work correctly


                    }
                });

            }

//            @Override
//            protected void populateViewHolder(Fine_Viewholder_Adapter viewHolder, Fine_pojo model, int position) {
//
//                viewHolder.setvalue(model.getBooktitle(), model.getAccno(), model.getAuthorname(), model.getIssuedate(), model.getReturndate());
//
//            }

            @NonNull
            @Override
            public Fine_Viewholder_Adapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fine_activities_item,viewGroup,false);
                return new Fine_Viewholder_Adapter(view1);
            }

        };

        mrecyclerView.setAdapter(adapter);

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
