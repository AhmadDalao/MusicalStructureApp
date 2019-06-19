package com.example.android.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<myMusicModel> myMusicModels = new ArrayList<>();
        myMusicModels.add(new myMusicModel("test", "test ", "2019",
                R.drawable.ic_animal, R.raw.color_black));
        myMusicModels.add(new myMusicModel("test", "test ", "2019",
                R.drawable.ic_animal, R.raw.color_black));
        myMusicModels.add(new myMusicModel("test", "test ", "2019",
                R.drawable.ic_animal, R.raw.color_black));
        myMusicModels.add(new myMusicModel("test", "test ", "2019",
                R.raw.color_black));


        musicAdatper adatper = new musicAdatper(MainActivity.this, myMusicModels);
        GridView gridView = (GridView) findViewById(R.id.myGridview);
        gridView.setAdapter(adatper);

    }


}
