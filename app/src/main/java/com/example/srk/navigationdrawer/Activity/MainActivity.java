package com.example.srk.navigationdrawer.Activity;

import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;

import com.example.srk.navigationdrawer.Others.myinterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.srk.navigationdrawer.Fragments.DashboardFragment;
import com.example.srk.navigationdrawer.Fragments.EventsFragment;
import com.example.srk.navigationdrawer.Fragments.ProfileFragment;
import com.example.srk.navigationdrawer.Fragments.SearchFragment;
import com.example.srk.navigationdrawer.Fragments.ActivitiesFragment;
import com.example.srk.navigationdrawer.R;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoogle;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    private ActionBar actionBar;

    private long backPressedTime;  //slider activity
    private Toast backToast;

    //myinterface my;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdrawerlayout = findViewById(R.id.drawer);
        mtoogle = new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoogle);
        mtoogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Give the tool bar name
        actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Double Shark");

        //this give the color of navigation bar color to black
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.db:
                        actionBar.setElevation(0);    //Remove Shadow From the action bar
                        navigationView.setCheckedItem(R.id.db);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragment_container, new DashboardFragment()).commit();
                        return true;
                    case R.id.event:
                        actionBar.setElevation(8);    //Remove Shadow From the action bar
                        navigationView.setCheckedItem(R.id.event);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new EventsFragment()).commit();
                        return true;
                    case R.id.search:
                        actionBar.setElevation(8);    //Remove Shadow From the action bar
                        navigationView.setCheckedItem(R.id.search);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragment_container, new SearchFragment()).commit();
                        return true;
                    case R.id.profile:
                        actionBar.setElevation(8);    //Remove Shadow From the action bar
                        navigationView.setCheckedItem(R.id.profile);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new ProfileFragment()).commit();
                        return true;
                    case R.id.activities:
                        actionBar.setElevation(0);    //Remove Shadow From the action bar
                        navigationView.setCheckedItem(R.id.activities);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new ActivitiesFragment()).commit();
                        return true;
                }

                return false;
            }
        });

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.db);
            bottomNavigationView.setSelectedItemId(R.id.db);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.db:
                bottomNavigationView.setSelectedItemId(R.id.db);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new DashboardFragment()).commit();
                break;
            case R.id.event:
                bottomNavigationView.setSelectedItemId(R.id.event);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new EventsFragment()).commit();
                break;
            case R.id.search:
                bottomNavigationView.setSelectedItemId(R.id.search);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new SearchFragment()).commit();
                break;
            case R.id.profile:
                bottomNavigationView.setSelectedItemId(R.id.profile);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;
            case R.id.activities:
                bottomNavigationView.setSelectedItemId(R.id.activities);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new ActivitiesFragment(),"activities_tag").commit();
                break;
            case R.id.logout:
                Toast.makeText(MainActivity.this, "Log out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
//                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"This is Double Shark App \n\n https://www.youtube.com/watch?v=oRZ0cfZ9SeU ");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                break;
            case R.id.send:
                Toast.makeText(MainActivity.this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }

        mdrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mtoogle.onOptionsItemSelected(item))
        {
            return true;
        }

        switch (item.getItemId()){

            case R.id.notification:
                Toast.makeText(this, "notification", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                break;
            case R.id.aboutus:
                Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this, "log out", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    // this is use for double back button exit
    @Override
    public void onBackPressed() {

        if (backPressedTime+2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            backToast = Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    public void callfromDSfragment() {

        //After pass the excution from DS fragment we select activities fragment by bottomnavigationview
        bottomNavigationView.setSelectedItemId(R.id.activities);

        //Then we call method of Activities fragment
//        ActivitiesFragment Fragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentByTag("activities_tag");
//        Fragment.callfromMainActivity();

        //callfromMainActivity("hello");

       // my.myfunction();




    }

//    public  void initmyinterface(myinterface inter) {
//
//        my = inter;
//        Log.e(TAG, my.toString());
//    }




}
