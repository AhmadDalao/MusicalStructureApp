package com.example.android.musicalstructureapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class playingMusic extends AppCompatActivity {

    private TextView songTitle;
    private ImageView ImageAlbum;
    private TextView singerName;
    private ImageView playMusicIcon;
    private ImageView forwardMusic;
    private ImageView rewindMusic;
    private TextView songDurationTime;
    private SeekBar mSeekBar;
    private TextView startingTime;
    private String currentTime;
    private String remaningTime;
    int totalTime;

    //    private Runnable runnable;
//    private Handler handler;


    /**
     * declaring variables to hold the data which is sent from {@link MainActivity}
     */
    private String receiveSongTitle;
    private int receiveImage;
    private String receiveSingerName;
    private int receiveMusic;

    /*
       handle playback of all the sound files
        */
    MediaPlayer mediaPlayer;

    /**
     * this listener is triggered when the {@link MediaPlayer} has completed
     * playing the file.
     */
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //   mediaPlayer.setLooping(true);
            playMusicIcon.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            if (! mediaPlayer.isPlaying()) {
                mediaPlayer.seekTo(0);
                mSeekBar.setProgress(0);


            }
            //  releaseMediaPlayer();
        }
    };

    AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.

                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_music);

        /** Create and setup the {@link AudioManager} to request audio focus */
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        // receive the bundle
        Bundle bundle = getIntent().getExtras();
        // check if the bundle is not null meaning there is data to be received
        if (bundle != null) {
            // assigning the data to variables
            receiveSongTitle = bundle.getString(myConstants.getKeySendTitle());
            receiveImage = bundle.getInt(myConstants.getKeySendImage());
            receiveSingerName = bundle.getString(myConstants.getKeySendSingerName());
            receiveMusic = bundle.getInt(myConstants.getKeySendSong());

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

        // finding the view
        startingTime = (TextView) findViewById(R.id.where_song_start);

        // finding the view
        songDurationTime = (TextView) findViewById(R.id.song_duration);

        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);


        //finding the view
        playMusicIcon = (ImageView) findViewById(R.id.play_arrow);
        //attach on click listener
        playMusicIcon.setOnClickListener(new View.OnClickListener() {

            // this variable will check if there is a music playing
            private boolean playingSound = true;
            // to save the song once the pause icon is clicked on
            int currentSongPosition;

            @Override
            public void onClick(View v) {
                // if the music start when the button is licked on
                // change the picture to pause
                if (playingSound) {
                    // set the image from playing icon to pause
                    playMusicIcon.setImageResource(R.drawable.ic_pause_black_24dp);
                    // once done re assign the value false to the variable to it wont stuck
                    playingSound = false;

                  /*
                release the media player if it currently exists because we are about to play
                a different sound file
                 */
                    releaseMediaPlayer();

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mediaPlayer = (MediaPlayer) MediaPlayer.create(playingMusic.this, receiveMusic);


                    // Start the audio file
                    mediaPlayer.start();
                    mediaPlayer.seekTo(currentSongPosition);
                    // setup  a listener on media player , so that we can stop and release the
                    //media player once sound has finished
                    mSeekBar.setProgress(currentSongPosition);


                    totalTime = mediaPlayer.getDuration(); // ToDo there is bug
                    mSeekBar.setMax(totalTime);
                    mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser) {
                                mediaPlayer.seekTo(progress);
                                mSeekBar.setProgress(progress);
                            }
                        }


                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    // need a thread to update the seekbar


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (mediaPlayer != null) {
                                try {
//                        Log.i("Thread ", "Thread Called");
                                    // create new message to send to handler
                                    if (mediaPlayer.isPlaying()) {//Todo there is a bug here
                                        Message msg = new Message();
                                        msg.what = mediaPlayer.getCurrentPosition();
                                        handler.sendMessage(msg);
                                        Thread.sleep(1000);
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            releaseMediaPlayer();// added this to fix the crush it works xD
                        }
                    }).start();


                    mediaPlayer.setOnCompletionListener(onCompletionListener);

                } else {
                    // we are at the pause right now , there is no music playing at the moment
                    // pause the song and change the icon to play again and so on the loop will go
                    playingSound = true;
                    mediaPlayer.pause();
                    playMusicIcon.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    // save the current position of the song
                    // which i will be calling above under  mediaPlayer.start();
                    //once the audio play again it will go to the last position it was at
                    currentSongPosition = mediaPlayer.getCurrentPosition();
                    mSeekBar.setProgress(currentSongPosition);

                }
            }// end of on click
        });// end of on click listener


        // finding the view for the forward icon
        forwardMusic = (ImageView) findViewById(R.id.fast_forward);
        //attach on click listener
        forwardMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get current song position
                int currentSongPosition = mediaPlayer.getCurrentPosition();
                // check if seekForward time is lesser than song duration
                if (currentSongPosition + myConstants.getForwardSong() <= mediaPlayer.getDuration()) {
                    // forward the song if so
                    mediaPlayer.seekTo(currentSongPosition + myConstants.getForwardSong());
                    mSeekBar.setProgress(currentSongPosition + myConstants.getForwardSong()); // new addition
                } else {
                    // forward to end position
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
                }

            }
        });


        // finding the view for the rewind icon
        rewindMusic = (ImageView) findViewById(R.id.fast_rewind);
        //attach on click listener
        rewindMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get current song position
                int currentSongPosition = mediaPlayer.getCurrentPosition();
                // check if seekBackward time is greater than 0 sec
                if (currentSongPosition - myConstants.getRewindSong() >= 0) {
                    // rewind the song if so
                    mediaPlayer.seekTo(currentSongPosition - myConstants.getRewindSong());
                    mSeekBar.setProgress(currentSongPosition - myConstants.getRewindSong()); // new addition it works fine

                } else {
                    // backward to starting position it works fine
                    mediaPlayer.seekTo(0);
                }
            }

        });


    }// end of on create


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            Log.i("handler ", "handler called");
            int current_position = msg.what;
            mSeekBar.setProgress(current_position);
            currentTime = createTimeLabel(current_position);
            startingTime.setText(currentTime);
            remaningTime = createTimeLabel(totalTime - current_position);
            songDurationTime.setText(remaningTime);
        }
    };


    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;


    }


    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }


}// end of playing music activity
