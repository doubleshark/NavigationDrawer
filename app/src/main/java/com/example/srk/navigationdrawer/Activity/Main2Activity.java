package com.example.srk.navigationdrawer.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.srk.navigationdrawer.R;
import com.r0adkll.slidr.Slidr;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Slidr.attach(this);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== android.R.id.home)
        {
            this.finish();
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }
}
