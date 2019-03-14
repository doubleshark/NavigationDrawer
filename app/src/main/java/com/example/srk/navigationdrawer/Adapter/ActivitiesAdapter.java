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

        public TextView booktitle;
        public TextView accno;
        public TextView authorname;
        public TextView issuedate;
        public TextView returndate;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            booktitle = itemView.findViewById(R.id.booktitle_tv);
            accno = itemView.findViewById(R.id.accno_tv);
            authorname = itemView.findViewById(R.id.authorname_tv);
            issuedate = itemView.findViewById(R.id.issuedate_tv);
            returndate = itemView.findViewById(R.id.returndate_tv);
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

        exampleViewHolder.booktitle.setText(currentItem.getBooktitle());
        exampleViewHolder.accno.setText(currentItem.getAccno());
        exampleViewHolder.authorname.setText(currentItem.getAuthorname());
        exampleViewHolder.issuedate.setText(currentItem.getIssuedate());
        exampleViewHolder.returndate.setText(currentItem.getReturndate());

    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }
}
