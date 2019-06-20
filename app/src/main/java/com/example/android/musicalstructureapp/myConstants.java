package com.example.android.musicalstructureapp;


/**
 * this class contains all the myConstants i need to send the
 * data from {@link MainActivity} to {@link playingMusic} Activity
 * <p>
 * the constants contains all the keys needed by the bungle to send the data.
 */

public class myConstants {


    private final static String KEY_SEND_IMAGE = "KEY_SEND_IMAGE";
    private final static String KEY_SEND_TITLE = "KEY_SEND_TITLE";
    private final static String KEY_SEND_SONG = "KEY_SEND_SONG";
    private final static String KEY_SEND_SINGER_NAME = "KEY_SEND_SINGER_NAME";

    public static String getKeySendSingerName() {
        return KEY_SEND_SINGER_NAME;
    }

    public static String getKeySendImage() {
        return KEY_SEND_IMAGE;
    }

    public static String getKeySendTitle() {
        return KEY_SEND_TITLE;
    }

    public static String getKeySendSong() {
        return KEY_SEND_SONG;
    }
}
