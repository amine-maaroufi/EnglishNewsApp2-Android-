package com.medamine.englishnewsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.medamine.englishnewsapp.Fragment.EconomicFragment;
import com.medamine.englishnewsapp.Fragment.HomeFragment;
import com.medamine.englishnewsapp.Fragment.MusicFragment;
import com.medamine.englishnewsapp.Fragment.ScienceFragment;
import com.medamine.englishnewsapp.Fragment.SportFragment;
import com.medamine.englishnewsapp.Fragment.TechnologyFragment;
import com.medamine.englishnewsapp.Fragment.politicFragment;
import com.medamine.englishnewsapp.Modele.ScienceReponse;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static DrawerLayout drawer;
    public static ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });*/



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

      //loading the home fragment in the first page
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containerID, new HomeFragment()).commit();
        }

        //default item selected
        navigationView.setCheckedItem(R.id.nav_latest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/
       if (id == android.R.id.home) {
            // Home/Up logic handled by onBackPressed implementation
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_latest) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, HomeFragment.newInstance()).commit();
            getSupportActionBar().setTitle("Latest News");

        } else if (id == R.id.nav_politico) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, politicFragment.newInstance()).commit();
            getSupportActionBar().setTitle("Politico");

        } else if (id == R.id.nav_economic) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, EconomicFragment.newInstance()).commit();
            getSupportActionBar().setTitle("Economic");

        } else if (id == R.id.nav_sport) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, SportFragment.newInstance()).commit();
            getSupportActionBar().setTitle("Sport");

        } else if (id == R.id.nav_science) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, ScienceFragment.newInstance()).commit();
            getSupportActionBar().setTitle("Science");

        } else if (id == R.id.nav_technology) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, TechnologyFragment.newInstance()).commit();
            getSupportActionBar().setTitle("Technology");

        } else if (id == R.id.nav_music) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, MusicFragment.newInstance()).commit();
            getSupportActionBar().setTitle("Music");


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
