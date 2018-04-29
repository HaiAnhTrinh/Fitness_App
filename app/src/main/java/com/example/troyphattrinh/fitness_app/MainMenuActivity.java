package com.example.troyphattrinh.fitness_app;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class MainMenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //enables the intent to get extra data from another activity
        Bundle mainMenuData = getIntent().getExtras();
        //if there is no extra data, don't do anything
        /*if(mainMenuData==null){
            return;
        }
        */
        String username = mainMenuData.getString("email");
        final TextView usernameText = findViewById(R.id.username);

        //set welcome message
        usernameText.setText("Welcome " + username);


        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        //handle clicked item of the navigation menu
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        // Add code here to update the UI based on the item selected
                        switch (menuItem.getItemId()){
                            case R.id.nav_home:
                                drawerLayout.closeDrawers();
                                return true;
                            case R.id.nav_cal_bmi:
                                drawerLayout.closeDrawers();;
                                return true;
                            case R.id.nav_foot_step:
                                drawerLayout.closeDrawers();
                                return true;
                            case R.id.nav_heart_rate:
                                drawerLayout.closeDrawers();
                                return true;
                            case R.id.nav_logout:
                                drawerLayout.closeDrawers();
                                logout();
                                return true;
                        }

                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        return false;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.menu_icon);
        actionbar.setDisplayHomeAsUpEnabled(true);

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

    private void logout(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainMenuActivity.this, MainActivity.class));
    }
}