package com.example.android.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class playingMusic extends AppCompatActivity {

    private TextView songTitle;
    private ImageView ImageAlbum;

    private String receiveSongTitle;
    private int receiveImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_music);


        // receive the bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            receiveSongTitle = bundle.getString(myConstants.getKeySendTitle());
            receiveImage = bundle.getInt(myConstants.getKeySendImage());

        }


        songTitle = (TextView) findViewById(R.id.title_of_song_playingMusic);
        songTitle.setText(receiveSongTitle);

        ImageAlbum = (ImageView) findViewById(R.id.image_playingMusic);
        ImageAlbum.setImageResource(receiveImage);


    }


}
