package com.example.troyphattrinh.fitness_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConfirmRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_register);

        Bundle confirmRegisterData = getIntent().getExtras();

        if(confirmRegisterData == null){
            return;
        }

        String confirmCode = confirmRegisterData.getString("confirmCode");

        final EditText confirmText = findViewById(R.id.confirmText);
        String confirmUser = confirmText.getText().toString();

    }
/*
    public void clickConfirmButton(View v){
        Intent intent = new Intent(this, MainActivity.class);
        TextView errorText = findViewById(R.id.errorTextView);


        if(){
            errorText.setText("");
            startActivity(intent);
        }
        else{
            errorText.setText("INCORRECT CONFIRMATION CODE!!!");
        }
    }
    */
}
