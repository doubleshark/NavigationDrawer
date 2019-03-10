package com.example.srk.navigationdrawer.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.srk.navigationdrawer.Others.ActivitiesItem;
import com.example.srk.navigationdrawer.R;

import java.util.ArrayList;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ExampleViewHolder> {

    private ArrayList<ActivitiesItem> mExamplelist;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView booktitle_tv;
        public TextView accno_tv;
        public TextView authorname_tv;
        public TextView issuedate_tv;
        public TextView returndate_tv;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            booktitle_tv = itemView.findViewById(R.id.booktitle_tv);
            accno_tv = itemView.findViewById(R.id.accno_tv);
            authorname_tv = itemView.findViewById(R.id.authorname_tv);
            issuedate_tv = itemView.findViewById(R.id.issuedate_tv);
            returndate_tv = itemView.findViewById(R.id.returndate_tv);
        }
    }

    public ActivitiesAdapter(ArrayList<ActivitiesItem> examplelist ) {
        mExamplelist = examplelist;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activities_item, viewGroup, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(v);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {

        ActivitiesItem currentItem  = mExamplelist.get(i);

        exampleViewHolder.booktitle_tv.setText(currentItem.getBooktitle_tv());
        exampleViewHolder.accno_tv.setText(currentItem.getAccno_tv());
        exampleViewHolder.authorname_tv.setText(currentItem.getAuthorname_tv());
        exampleViewHolder.issuedate_tv.setText(currentItem.getIssuedate_tv());
        exampleViewHolder.returndate_tv.setText(currentItem.getReturndate_tv());

    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }
}
