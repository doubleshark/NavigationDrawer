package com.example.srk.navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoogle;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    private ActionBar actionBar;


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

        actionBar = getSupportActionBar();

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
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment_container,new ActivitiesFragment()).commit();
                break;
            case R.id.logout:
                Toast.makeText(MainActivity.this, "Log out", Toast.LENGTH_SHORT).show();
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
        return super.onOptionsItemSelected(item);
    }




}
