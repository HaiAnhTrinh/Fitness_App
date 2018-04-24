package com.example.troyphattrinh.fitness_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.troyphattrinh.fitness_app.SQL.DatabaseHelper;

import java.util.Random;


public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper dbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }


    public void clickRegButton(View v){
        Intent intent = new Intent(this, ConfirmRegisterActivity.class);

        final EditText usernameText = findViewById(R.id.username_textField);
        final EditText passwordText = findViewById(R.id.password_textField);
        final EditText confirmPasswordText = findViewById(R.id.confirm_password_textField);
        final EditText emailText = findViewById(R.id.email_textField);
        final TextView dobText = findViewById(R.id.dob_textView);
        final TextView errorText = findViewById(R.id.error_textView);


        //TODO: get user input and record them but dont put in the database yet
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String confirmPassword = confirmPasswordText.getText().toString();
        String dob = dobText.getText().toString();
        String email = emailText.getText().toString();


        //TODO: have functions to check validity
        if(     checkUsername(username) &&
                checkPassword(password) &&
                checkConfirmPassword(confirmPassword, password) &&
                dob.length() != 0 &&
                email.length() != 0 ) {

            dbh = new DatabaseHelper(this);
            boolean success = dbh.addUser(username, password, dob, email);

            if(success == true){
                Toast.makeText(RegisterActivity.this, "Register successful!!!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(RegisterActivity.this, "OPPS", Toast.LENGTH_LONG).show();
            }

            //generate a random 4 digit number
            Random random = new Random();
            String rand = String.format("%04d", random.nextInt(10000));


            intent.putExtra("confirmCode", rand);

            sendEmail(rand);

            startActivity(intent);
            errorText.setText("");
        }
        else{
            errorText.setText("Invalid input!!! Please enter again.");
        }
    }


    /* TODO: checks duplicate username in the database */
    boolean checkUsername(String username){
        return username.length() >= 1;
    }

    /*checks correct requirement for password (minimum 8 characters)*/
    boolean checkPassword(String password){
        return password.length() >= 8;
    }

    /*compare password and confirmPassword*/
    boolean checkConfirmPassword(String confirmPassword, String password){
        boolean check = false;

        if(confirmPassword.equals(password)){
            check = true;
        }

        return check;
    }

    /*checks format*/
    boolean checkDOB(String dob){
        return true;
    }


    boolean checkEmail(String email){
        return true;
    }

    private void sendEmail(String confirmCode) {

        final EditText emailText = findViewById(R.id.email_textField);
        String TO = emailText.getText().toString();

        //create an intent with ACTION_SEND action
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        //putting contents to the email
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "FITNESS APP CONFIRMATION CODE");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your confirmation code is: " + confirmCode);

    }
}