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
import com.example.srk.navigationdrawer.Adapter.Issue_Viewholder_Adapter;
import com.example.srk.navigationdrawer.Others.ActivitiesItem;
import com.example.srk.navigationdrawer.Others.Issue_pojo;
import com.example.srk.navigationdrawer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentIssue extends Fragment {

    View view;

    private RecyclerView mrecyclerView;
    public static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;
    FirebaseRecyclerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.issue_fragment, container, false);

        //Initiate RecyclerView
        mrecyclerView = (RecyclerView) view.findViewById(R.id.issue_recyclerview);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef = mfirebaseDatabase.getReference("Issue");
        mdatabaseRef.keepSynced(true);

        Query query = mdatabaseRef.limitToLast(50);
        FirebaseRecyclerOptions<Issue_pojo> options = new FirebaseRecyclerOptions.Builder<Issue_pojo>().setQuery(query,Issue_pojo.class).build();

        adapter = new FirebaseRecyclerAdapter<Issue_pojo, Issue_Viewholder_Adapter>(options) {

            @Override
            protected void onBindViewHolder(@NonNull Issue_Viewholder_Adapter holder, int position, @NonNull Issue_pojo model) {

                holder.setvalue(model.getBooktitle(),model.getAccno(),model.getAuthorname(),model.getIssuedate(),model.getReturndate());

                holder.setOnClickListener(new Issue_Viewholder_Adapter.clickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
            }

            @NonNull
            @Override
            public Issue_Viewholder_Adapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activities_item,viewGroup,false);
                return new Issue_Viewholder_Adapter(view1);
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
