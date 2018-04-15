package com.example.troyphattrinh.fitness_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_register);
    }

    public void clickConfirmButton(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
