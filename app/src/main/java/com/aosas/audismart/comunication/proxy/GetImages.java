package com.aosas.audismart.comunication.proxy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by dayanamartinez on 06-10-16.
 */
public class GetImages extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    public GetImages(ImageView imageView,Context context) {
        this.imageView = imageView;
        Toast.makeText(context, "Decargando imagen", Toast.LENGTH_SHORT).show();
    }


    protected Bitmap doInBackground(String... urls) {
        String imageURL = urls[0];
        Bitmap bimage = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            bimage = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }

}
