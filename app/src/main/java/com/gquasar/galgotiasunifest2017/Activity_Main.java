package com.gquasar.galgotiasunifest2017;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Activity_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Galgotias Unifest 2017");
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment_homePage= new Fragment_HomePage();
        FragmentManager  fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_activity__home_page,fragment_homePage);
        fragmentTransaction.commit();

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

        getMenuInflater().inflate(R.menu.activity__home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment;

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            toolbar.setTitle("Galgotias Unifest 2017");
            fragment= new Fragment_HomePage();
            FragmentManager  fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_activity__home_page,fragment);
            fragmentTransaction.commit();

        }

       else if (id == R.id.nav_events) {


        } else if (id == R.id.nav_gallery) {
            toolbar.setTitle("Gallery");
           fragment= new Fragment_Gallery();
            FragmentManager  fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_activity__home_page,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_register) {
            toolbar.setTitle("Register");
             fragment= new Fragment_Register();
            FragmentManager  fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_activity__home_page,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_contacts) {
            toolbar.setTitle("Contacts");
            fragment= new Fragment_Contacts();
            FragmentManager  fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_activity__home_page,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {

            Toast.makeText(this, "Sharing Option Here", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_developer) {
            toolbar.setTitle("Developers");
            fragment= new Fragment_Developers();
            FragmentManager  fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_activity__home_page,fragment);
            fragmentTransaction.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
