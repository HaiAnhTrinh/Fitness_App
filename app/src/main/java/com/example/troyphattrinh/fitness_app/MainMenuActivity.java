package com.example.troyphattrinh.fitness_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //enables the intent to get extra data from another activity
        Bundle mainMenuData = getIntent().getExtras();
        //if there is no extra data, don't do anything
        if(mainMenuData==null){
            return;
        }
        String username = mainMenuData.getString("username");

        final TextView usernameText = findViewById(R.id.username);

        usernameText.setText(username);

    }
}
