package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/**
 *  Create an ArrayList of WordsModel objects assigning the value by using the method getArrayListPhrases
 which contains the array of words defined at {@link myMusicModel}
 */
        final ArrayList<myMusicModel> myMusicModels = new ArrayList<>();
        myMusicModels.add(new myMusicModel(getString(R.string.careless_whisper),
                getString(R.string.george_michael_wham), getString(R.string.careless_whisper_date),
                R.drawable.george_michael_4, R.raw.george_michael_wham_careless_whisper));

        myMusicModels.add(new myMusicModel(getString(R.string.onlyYou), getString(R.string.joe_dolan),
                getString(R.string.onlyYou_date), R.drawable.joe_dolan, R.raw.only_you));

        myMusicModels.add(new myMusicModel(getString(R.string.eminem_rock), getString(R.string.eminem),
                getString(R.string.eminem_rock_date), R.drawable.eminem, R.raw.eminem_rock_bottom));


        myMusicModels.add(new myMusicModel(getString(R.string.when_am_gone), getString(R.string.eminem),
                getString(R.string.eminem_gone_date), R.raw.eminem_when_am_gone));

        myMusicModels.add(new myMusicModel(getString(R.string.no_apologies),
                getString(R.string.eminem), getString(R.string.no_apologies_date),  R.raw.eminem_no_apoligies));

        /** Create an {@link musicAdapter}, whose data source is a list of
         *
         {@link WordsModel}s. The adapter knows how to create list item views for each item
         in the list.
         */
        musicAdapter adapter = new musicAdapter(MainActivity.this, myMusicModels);

        // Get a reference to the GridView, and attach the adapter to the GridView.
        // Find the {@link GridView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link GridView} with the view ID called list, which is declared in the
        // activity_main.xml layout file.

        GridView gridView = (GridView) findViewById(R.id.myGridview);

        /** Make the {@link GridView} use the {@link musicAdapter} we created above, so that the
         {@link GridView} will display list items for each {@link myMusicModel} in the list. */
        gridView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // get the song position into the object
                // Get the {@link myMusicModel} object at the given position the user clicked on
                myMusicModel songsList = myMusicModels.get(position);


                /** use bundle to send the data  to {@link playingMusic} activity    */
                Bundle bundle = new Bundle();
                // put the data inside the bundle which will be send to new activity
                // using intent
                bundle.putInt(myConstants.getKeySendImage(), songsList.getmImageResource());
                bundle.putInt(myConstants.getKeySendSong(), songsList.getmAudio());
                bundle.putString(myConstants.getKeySendTitle(), songsList.getmSongTitle());
                bundle.putString(myConstants.getKeySendSingerName(), songsList.getmSingerName());

                /** using intent to send the data to {@link playingMusic} and start new activity {@link playingMusic} */
                Intent intent = new Intent(view.getContext(), playingMusic.class);
                // put bundle inside an intent to send it to the new activity
                intent.putExtras(bundle);
                // launch the activity
                startActivity(intent);


            }
        });


    }


}
