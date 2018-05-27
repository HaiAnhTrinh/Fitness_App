package com.example.troyphattrinh.fitness_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog;

import java.io.InputStream;

public class FbUserInfo extends AppCompatActivity
{

    //initialisation
    private ShareDialog shareDialog;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this); //facebook SDK initializtion
        setContentView(R.layout.activity_usercontent); //display the usercontent view

        shareDialog = new ShareDialog(this);

        //getting data from main activity
        Bundle bundle = getIntent().getExtras();
        String name = bundle.get("name").toString();
        String avatar = bundle.get("avatar").toString();

        TextView txtName = (TextView) findViewById(R.id.userName); //assign value to a textview object
        txtName.setText(name);

        Button logout = (Button) findViewById(R.id.logout); //assign value to a button object
        logout.setOnClickListener(new View.OnClickListener() //set action for a button
        {
            @Override
            public void onClick(View view)
            {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(FbUserInfo.this, MainActivity.class); //open new activity
                startActivity(login);
                finish();
            }
        });

        new FbUserInfo.DownloadImage((ImageView) findViewById(R.id.profile)).execute(avatar); //retrieve profile picture from user's facebook account

    }
    
    //getting profile picture from user's facebook account
    public class DownloadImage extends AsyncTask<String, Void, Bitmap>
    {
        ImageView image;

        public DownloadImage(ImageView image)
        {
            this.image = image;
        }

        protected Bitmap doInBackground(String... urls)
        {
            String url = urls[0];
            Bitmap icon = null;
            try
            {
                InputStream in = new java.net.URL(url).openStream();
                icon = BitmapFactory.decodeStream(in);
            }
            catch (Exception e)
            {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return icon;
        }

        protected void onPostExecute(Bitmap bitmap)
        {
            image.setImageBitmap(bitmap);
        }
    }
}
