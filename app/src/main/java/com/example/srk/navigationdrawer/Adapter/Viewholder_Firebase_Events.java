package com.example.srk.navigationdrawer.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class Viewholder_Firebase_Events extends RecyclerView.ViewHolder {

    View mview;


    // Default Constructor
    public Viewholder_Firebase_Events(@NonNull View itemView) {
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


    public void setDetails(Context context, String title, String description, String image, String time) {
        ImageView mimageView = (ImageView) mview.findViewById(R.id.img);
        TextView mtitle = (TextView) mview.findViewById(R.id.title);
        TextView mdesc = (TextView) mview.findViewById(R.id.desc);
        TextView mTime = (TextView) mview.findViewById(R.id.time);
        final ProgressBar mprogress = (ProgressBar) mview.findViewById(R.id.progress_load_photo);


        //Setting data
        mtitle.setText(title);
        mdesc.setText(description + "...");
        mTime.setText(time);
        Glide.with(context).load(image).transition(DrawableTransitionOptions.withCrossFade()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mprogress.setVisibility(View.GONE);
                return false;
            }
        }).into(mimageView);
    }

    //Declaration of cutstom onClickListner
    private Viewholder_Firebase_Events.clickListener mclickListener;
    public interface clickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(Viewholder_Firebase_Events.clickListener clicklistener){
        mclickListener=clicklistener;
    }
}
