package com.example.troyphattrinh.fitness_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfirmRegisterActivity extends AppCompatActivity {

    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_register);

        final Intent intent = new Intent(this, MainActivity.class);
        final TextView errorText = findViewById(R.id.errorTextView);
        confirmButton = findViewById(R.id.confirmButton);


        Bundle confirmRegisterData = getIntent().getExtras();
        if(confirmRegisterData == null){
            return;
        }
        final String confirmCode = confirmRegisterData.getString("confirmCode");
        final EditText confirmText = findViewById(R.id.confirmText);
        final String confirmUser = confirmText.getText().toString();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmCode.equals(confirmUser)){
                    errorText.setText("");
                    startActivity(intent);
                }
                else{
                    errorText.setText("INCORRECT CONFIRMATION CODE!!!");
                }
            }
        });
    }

    public void clickConfirmButton(View v){


        Bundle confirmRegisterData = getIntent().getExtras();

        if(confirmRegisterData == null){
            return;
        }

        String confirmCode = confirmRegisterData.getString("confirmCode");

        final EditText confirmText = findViewById(R.id.confirmText);
        String confirmUser = confirmText.getText().toString();



    }

}
