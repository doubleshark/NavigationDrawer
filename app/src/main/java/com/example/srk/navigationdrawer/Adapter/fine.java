package com.example.srk.navigationdrawer.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.srk.navigationdrawer.R;

public class fine extends RecyclerView.ViewHolder {

    View mview;


    // Default Constructor
    public fine(@NonNull View itemView) {
        super(itemView);

        mview = itemView;

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
                return true;
            }
        });


    }


    public void setDetails(String booktitle, String accno, String authorname, String issuedate, String returndate) {

        TextView booktitle_tv = itemView.findViewById(R.id.booktitle_tv);
        TextView accno_tv = itemView.findViewById(R.id.accno_tv);
        TextView authorname_tv = itemView.findViewById(R.id.authorname_tv);
        TextView issuedate_tv = itemView.findViewById(R.id.issuedate_tv);
        TextView returndate_tv = itemView.findViewById(R.id.returndate_tv);



        //Setting data
        booktitle_tv.setText(booktitle);
        accno_tv.setText(accno);
        authorname_tv.setText(authorname);
        issuedate_tv.setText(issuedate);
        returndate_tv.setText(returndate);
    }

    //Declaration of cutstom onClickListner
    private fine.clickListener mclickListener;

    public interface clickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(fine.clickListener clicklistener){
        mclickListener=clicklistener;
    }
}
