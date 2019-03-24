package com.example.srk.navigationdrawer.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.srk.navigationdrawer.R;

public class Fine_Viewholder_Adapter extends RecyclerView.ViewHolder {

    View view;

    public Fine_Viewholder_Adapter(@NonNull View itemView) {
        super(itemView);

        view = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickListener.onItemClick(v,getAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mclickListener.onItemLongClick(v,getAdapterPosition());
                return false;
            }
        });
    }

    public void setvalue(String booktitle, String accno, String authorname, String issuedate, String returndate) {

        TextView booktitle_tv = itemView.findViewById(R.id.booktitle_tv);
        TextView accno_tv = itemView.findViewById(R.id.accno_tv);
        TextView authorname_tv = itemView.findViewById(R.id.authorname_tv);
        TextView issuedate_tv = itemView.findViewById(R.id.issuedate_tv);
        TextView returndate_tv = itemView.findViewById(R.id.returndate_tv);

        booktitle_tv.setText(booktitle);
        accno_tv.setText(accno);
        authorname_tv.setText(authorname);
        issuedate_tv.setText(issuedate);
        returndate_tv.setText(returndate);

    }

    private Fine_Viewholder_Adapter.clickListener mclickListener;
    public interface clickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(Fine_Viewholder_Adapter.clickListener clicklistener){
        mclickListener=clicklistener;
    }


}
