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


        final ArrayList<myMusicModel> myMusicModels = new ArrayList<>();
        myMusicModels.add(new myMusicModel("test", "test ", "2019",
                R.drawable.ic_animal, R.raw.color_black));
        myMusicModels.add(new myMusicModel("test", "test ", "2019",
                R.drawable.ic_animal, R.raw.color_black));
        myMusicModels.add(new myMusicModel("test", "test ", "2019",
                R.drawable.ic_animal, R.raw.color_black));
        myMusicModels.add(new myMusicModel("test", "test ", "2019",
                R.raw.color_black));


        musicAdapter adapter = new musicAdapter(MainActivity.this, myMusicModels);
        GridView gridView = (GridView) findViewById(R.id.myGridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // get the song position into the object
                myMusicModel songsList = myMusicModels.get(position);

                /** use bundle to send the data  to {@link playingMusic} activity    */
                Bundle bundle = new Bundle();
                // put the data inside the bundle which will be send to new activity
                // using intent
                bundle.putInt(myConstants.getKeySendImage(), songsList.getmImageResource());//ToDo there is null error here try to fix it
                bundle.putInt(myConstants.getKeySendSong(), songsList.getmAudio());
                bundle.putString(myConstants.getKeySendTitle(), songsList.getmSongTitle());
                bundle.putString(myConstants.getKeySendSingerName() ,songsList.getmSingerName());

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
