package com.example.android.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class playingMusic extends AppCompatActivity {

    private TextView songTitle;
    private ImageView ImageAlbum;
    private TextView singerName;
    /**
     * declaring variables to hold the data which is sent from {@link MainActivity}
     */
    private String receiveSongTitle;
    private int receiveImage;
    private String receiveSingerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_music);


        // receive the bundle
        Bundle bundle = getIntent().getExtras();
        // check if the bundle is not null meaning there is data to be received
        if (bundle != null) {
            // assigning the data to variables
            receiveSongTitle = bundle.getString(myConstants.getKeySendTitle());
            receiveImage = bundle.getInt(myConstants.getKeySendImage());
            receiveSingerName = bundle.getString(myConstants.getKeySendSingerName());

        }

        // finding the view
        songTitle = (TextView) findViewById(R.id.title_of_song_playingMusic);
        //setting the title to the data received
        songTitle.setText(receiveSongTitle);

        // finding the view
        singerName = (TextView) findViewById(R.id.singer_name_playingMusic);
        // setting the name to the data received
        singerName.setText(receiveSingerName);


        // finding the view
        ImageAlbum = (ImageView) findViewById(R.id.image_playingMusic);
        //setting the album image to the image received
        ImageAlbum.setImageResource(receiveImage);


    }


}
