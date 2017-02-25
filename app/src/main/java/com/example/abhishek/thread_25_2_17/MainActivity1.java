package com.example.abhishek.thread_25_2_17;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity1 extends AppCompatActivity {

    EditText editText;
    ImageView imageView;
    URL url;
    URLConnection urlConnection;
    Downloadimage downloadimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        downloadimage=new Downloadimage();
        editText=(EditText)findViewById(R.id.editText);
        imageView=(ImageView)findViewById(R.id.imageView);
    }

    public class Downloadimage extends AsyncTask<String,Void,Bitmap> {


        Bitmap bitmap;
        String u;
        @Override
        protected Bitmap doInBackground(String... strings) {



            u=strings[0];


            try {
                url=new URL(u);
                urlConnection=url.openConnection();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                InputStream in=new BufferedInputStream(urlConnection.getInputStream());
               // byte b[]=new byte[u.getContentLength()];
                //in.read(b,0,b.length);

                bitmap= BitmapFactory.decodeStream(in);




            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            imageView.setImageBitmap(bitmap);
        }
    }


    public void geturl(View v)
    {
        String link=editText.getText().toString();

        downloadimage.execute(link);



    }

}
