package com.example.troyphattrinh.fitness_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.troyphattrinh.fitness_app.SQL.DatabaseHelper;


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
        if(     username.length() != 0 &&
                password.length() != 0 &&
                confirmPassword.length() != 0 &&
                confirmPassword.equals(password) &&
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

            startActivity(intent);
            errorText.setText("");
        }
        else{
            errorText.setText("Invalid input!!! Please enter again.");
        }
    }


    /*checks duplicate username in the database*
     * return 1 if there is no duplicate, 0 otherwise*/
    boolean checkUsername(String username){
        return true;
    }

    /*checks correct requirement for password (minimum 8 characters)*/
    boolean checkPassword(String password){
        return true;
    }

    /*compare password and confirmPassword*
     * return 1 if they are identical, 0 otherwise*/
    boolean checkConfirmPassword(String confirmPassword, String password){
        return true;
    }

    /*checks format*/
    boolean checkDOB(String dob){
        return true;
    }


    boolean checkEmail(String email){
        return true;
    }
}