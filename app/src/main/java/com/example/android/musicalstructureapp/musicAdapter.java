package com.example.android.musicalstructureapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class musicAdapter extends ArrayAdapter {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param musicList A List of AndroidFlavor objects to display in a list
     */
    public musicAdapter(Context context, ArrayList<myMusicModel> musicList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, musicList);
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listOfSongs = convertView;

        // Check if the existing view is being reused, otherwise inflate the view

        if (listOfSongs == null) {
            listOfSongs = LayoutInflater.from(getContext()).inflate(R.layout.song_layout_list, parent, false);
        }

        /** Get the {@link myMusicModel} object located at this position in the list
         *
         */

        myMusicModel music = (myMusicModel) getItem(position);

        // Find the TextView in the song_layout_list.xml layout with the ID title_of_song
        TextView nameOfSong = (TextView) listOfSongs.findViewById(R.id.title_of_song);
        // Get the version name from the current myMusicModel object : music
        // set this text on the nameOfSong TextView
        nameOfSong.setText(music.getmSongTitle());

        // Find the TextView in the song_layout_list.xml layout with the ID singer_name
        TextView singerName = (TextView) listOfSongs.findViewById(R.id.singer_name);
        // Get the version name from the current myMusicModel object : music
        // set this text on the singerName TextView
        singerName.setText(music.getmSingerName());

        // Find the TextView in the song_layout_list.xml layout with the ID releaseYear
        TextView releaseYear = (TextView) listOfSongs.findViewById(R.id.releaseYear);
        // Get the version name from the current myMusicModel object : music
        // set this text on the releaseYear TextView
        releaseYear.setText(music.getmYearOfRelease());

        // Find the TextView in the song_layout_list.xml layout with the ID imageView
        ImageView image_album = (ImageView) listOfSongs.findViewById(R.id.imageView);

        if (music.hasImage()) {
            image_album.setVisibility(View.VISIBLE);
            // Get the image resource ID from the current myMusicModel object : music
            // set the image to image_album
            image_album.setImageResource(music.getmImageResource());
        } else {
            // if the GridView has no image provide one for it by setting the setmImageResource
            music.setmImageResource(R.drawable.music_player);
            // if there is no image set the image of the song_layout_list to the following
            image_album.setImageResource(R.drawable.music_player);
        }



        // Return the whole list item layout (containing 3 TextViews and an ImageView)
        // so that it can be shown in the GridView
        return listOfSongs;

    }
}
