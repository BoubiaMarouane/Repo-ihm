package edu.polytech.repo_ihm.firebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DrawableFromURL extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public DrawableFromURL(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0]; // There will only be one URL at a time anyway
        Bitmap img = null; // initializing to null in case decoding throws an error
        try {
            InputStream is = new URL(url).openStream();
            img = BitmapFactory.decodeStream(is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    protected void onPostExecute(Bitmap img) {
        imageView.setImageBitmap(img);
    }
}
