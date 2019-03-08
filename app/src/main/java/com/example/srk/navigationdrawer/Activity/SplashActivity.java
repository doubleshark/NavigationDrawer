package com.example.srk.navigationdrawer.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.srk.navigationdrawer.R;

public class SplashActivity extends AppCompatActivity {

    TextView text_splash;
    ImageView image_splash;
    Animation fromtopbottom, smalltobig;

    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        text_splash = (TextView)findViewById(R.id.text_splash);
        image_splash = (ImageView)findViewById(R.id.image_splash);
        
        animationofsplash();
    }

    private void animationofsplash() {

        fromtopbottom = AnimationUtils.loadAnimation(this,R.anim.fromtopbottom);
        smalltobig = AnimationUtils.loadAnimation(this,R.anim.smalltobig);
        text_splash.setAnimation(fromtopbottom);
        image_splash.setAnimation(smalltobig);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent sliderintent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(sliderintent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();

            }
        },SPLASH_TIME_OUT);
    }
}
