package com.example.troyphattrinh.fitness_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    //used to debug and keep track of the steps
    private static final String TAG = "importantMessage";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        Log.i(TAG,"onCreate");
    }

    public void clickRegButton(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        TextView errorText = findViewById(R.id.loginError);
        errorText.setText("");
        startActivity(intent);
    }

    public void clickLoginButton(View v){
        EditText emailText = findViewById(R.id.emailText);
        EditText passwordText = findViewById(R.id.passwordText);
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        final TextView errorText = findViewById(R.id.loginError);

        if(email.isEmpty() || password.isEmpty()){
            errorText.setText("Invalid login details!!!");
        }
        else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (checkEmailVerification()) {
                            errorText.setText("");
                        } else {
                            errorText.setText("Email not verified!!!");
                        }
                    } else {
                        errorText.setText("Incorrect login details!!!");
                    }
                }
            });
        }
    }


    //check if the email has already been confirmed
    private boolean checkEmailVerification(){
        Boolean verified = false;
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean flag = firebaseUser.isEmailVerified();


        if(flag){
            verified = true;
            finish();
            startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
        }
        else{
            Toast.makeText(MainActivity.this, "PLEASE VERIFY YOUR EMAIL", Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }

        return verified;
    }

}