package com.example.srk.navigationdrawer.Activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Events_Detail extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private Toolbar toolbar;
    private boolean isHideToolbarView = false;
    private FrameLayout date_bheviour;
    private AppBarLayout appBarLayout;

    //Views
    private ImageView mimageView;
    TextView titleview, timeview, descview;
    DatabaseReference dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);


        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        date_bheviour = (FrameLayout) findViewById(R.id.dates);


        dataref = FirebaseDatabase.getInstance().getReference("Data");
        dataref.keepSynced(true);

        mimageView = (ImageView) findViewById(R.id.mim);
        titleview = (TextView) findViewById(R.id.titledeatail);
        timeview = (TextView) findViewById(R.id.da);
        descview = (TextView) findViewById(R.id.descdetail);
        final ProgressBar mdeatailProgressBar = (ProgressBar) findViewById(R.id.deatilProgress);

        String title = getIntent().getStringExtra("title");
        // textView.setText(title);


        final Query query = dataref.orderByChild("title").startAt(title).endAt(title + "\uf8ff");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String image = dataSnapshot.child("image").getValue().toString();
                String title = dataSnapshot.child("title").getValue().toString();
                String time = dataSnapshot.child("time").getValue().toString();
                String descr = dataSnapshot.child("description").getValue().toString();
                Glide.with(Events_Detail.this).load(image).transition(DrawableTransitionOptions.withCrossFade()).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mdeatailProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(mimageView);
                titleview.setText(title);
                timeview.setText(time);
                descview.setText(descr);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            date_bheviour.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        } else if (percentage < 1f && isHideToolbarView) {
            date_bheviour.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}