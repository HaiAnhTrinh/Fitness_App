package com.example.troyphattrinh.fitness_app;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;


public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.menu_icon);
        actionbar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        //handle clicked item of the navigation menu
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeActivity();

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction= fragManager.beginTransaction();

        fragTransaction.replace(R.id.screen, fragment);

        fragTransaction.commit();

    }


    //handle clicked menu icon on the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                //GravityCompat.START ensures the navigation bar opens correctly
                //either from right to left or vice versa
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        Fragment fragment = null;

        // Add code here to update the UI based on the item selected

        int id = menuItem.getItemId();

        if(id == R.id.nav_home){
            drawerLayout.closeDrawers();
            fragment = new HomeActivity();
        }
        else if(id == R.id.nav_cal_bmi){
            drawerLayout.closeDrawers();
            fragment = new BmiActivity();
        }
        else if(id == R.id.nav_foot_step){
            drawerLayout.closeDrawers();
        }
        else if(id == R.id.nav_heart_rate){
            drawerLayout.closeDrawers();
            startActivity(new Intent(MainMenuActivity.this, HeartRateActivity.class));
        }
        else if(id == R.id.nav_logout){
            drawerLayout.closeDrawers();
            logout();
        }

        if(fragment != null){

            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction fragTransaction= fragManager.beginTransaction();

            fragTransaction.replace(R.id.screen, fragment);

            fragTransaction.commit();
        }

        // set item as selected to persist highlight
        menuItem.setChecked(true);

        return false;
    }

    private void logout(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainMenuActivity.this, MainActivity.class));
    }
}