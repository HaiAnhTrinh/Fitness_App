package com.example.troyphattrinh.fitness_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    //used to debug and keep track of the steps
    private static final String TAG = "importantMessage";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG,"onCreate");
    }

    public void clickRegButton(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void clickLoginButton(View v){
        Intent intent = new Intent(this, MainMenuActivity.class);

        //adding extra data to mainMenu
        final EditText usernameText = findViewById(R.id.usernameText);
        String username = usernameText.getText().toString();
        intent.putExtra("username", username);

        startActivity(intent);
    }


}
