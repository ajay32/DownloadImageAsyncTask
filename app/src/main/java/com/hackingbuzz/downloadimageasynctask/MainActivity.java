package com.hackingbuzz.downloadimageasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView im;


    public void downloadi(View view) {  //we can create Button (onClick method) without initizling the Button.
        Bitmap imag;
        DownloadImageTask imageTask = new DownloadImageTask();

        try {

                // giving input as string n will get result here n we getting result in imageview (imag) as Bitmap  // we get image from code as bitmap
             imag = imageTask.execute("https://s-media-cache-ak0.pinimg.com/736x/e6/36/26/e6362646288847d50d256ab58dd77a57.jpg").get();
            im.setImageBitmap(imag);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im = (ImageView)findViewById(R.id.im);

    }

    // inner class

    class DownloadImageTask extends AsyncTask<String, String, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {  // varags in java ..can handle one or more argument (doInbackgroind(), doInbackground("hello") , doInbackgroind("hello","feelo","yello") or like this as string array from doInbackgrond(variable[0])

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();  // opening browser. & putting URL
                connection.connect();         // tapping on ENter button and getting Image (Image will first encode in Byte from as stream (we will hold byte data in variable that can hold byte data (inputstream)
                InputStream inputStream = connection.getInputStream();     // this inputstream will come with byte data
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream); // getting Image

                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
