package com.hadyandev.charalist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CharacterDetailsActivity extends AppCompatActivity {
    public static final String CHARACTER_NAME = "character_name";
    public static final String CHARACTER_ANIME = "character_anime";
    public static final String CHARACTER_DESCRIPTION = "character_description";
    public static final String CHARACTER_QUOTE = "character_quote";
    public static final String CHARACTER_PHOTO = "character_photo";
    TextView tvName;
    TextView tvPhotoText;
    TextView tvAnime;
    TextView tvDescription;
    TextView tvQuote;
    TextView tvQuoteAuthor;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        tvName = findViewById(R.id.tv_name);
        tvPhotoText = findViewById(R.id.tv_photo_text);
        ivPhoto = findViewById(R.id.iv_photo);
        tvDescription = findViewById(R.id.tv_description);
        tvAnime = findViewById(R.id.tv_anime);
        tvQuote = findViewById(R.id.tv_quote);
        tvQuoteAuthor = findViewById(R.id.tv_quote_author);

        String name = getIntent().getStringExtra(CHARACTER_NAME);
        String anime = getIntent().getStringExtra(CHARACTER_ANIME);
        String description = getIntent().getStringExtra(CHARACTER_DESCRIPTION);
        String quote = getIntent().getStringExtra(CHARACTER_QUOTE);
        String photo = getIntent().getStringExtra(CHARACTER_PHOTO);

        tvPhotoText.setText(name + " (" + anime + ")");
        tvName.setText(name);
        tvAnime.setText("From " + anime + " Anime");
        tvDescription.setText(description);
        tvQuote.setText(quote);
        tvQuoteAuthor.setText("~ " + name);
        new DownloadImageFromInternet(ivPhoto).execute(photo);

        getSupportActionBar().setTitle("Character Details");
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
//                InputStream in = new java.net.URL(imageURL).openStream();
//                bimage = BitmapFactory.decodeStream(in);

                if(imageURL != null){
                    String newUrl = imageURL.replace(" ", "%20");
                    java.net.URL url = new java.net.URL(newUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    bimage = BitmapFactory.decodeStream(input);
                }

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

}

