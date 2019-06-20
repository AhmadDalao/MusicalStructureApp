package com.example.android.musicalstructureapp;


/**
 * {@link myMusicModel} represents the music the user wants to listen too.
 * it also contains the song name , name of singer , release date and image of album
 */
public class myMusicModel {


    /*
     * Title as text in this case the title of each song
     */

    private String mSongTitle;

    /*
     * Name as text in this case the singer of each song
     */

    private String mSingerName;

    /*
     * Release year as text in this case the year the song was release
     */

    private String mYearOfRelease;


    /**
     * Image resource ID for each song
     */

    private int mImageResource = NO_IMAGE;


    /**
     * Constant value that represents no image was provided for this word
     */
    private static final int NO_IMAGE = -1;

    /*
     * Audio resource ID for the song
     */
    private int mAudio;

    /*
     * check wither an image is provided or not
     */
    public boolean hasImage() {
        return mImageResource != NO_IMAGE;
    }

     /*
    retrieve and return the song title as text
     */

    public String getmSongTitle() {
        return mSongTitle;
    }

    /*
       retrieve and return the singer name as text
        */
    public String getmSingerName() {
        return mSingerName;
    }

    /*
   retrieve and return the year of release as text
    */
    public String getmYearOfRelease() {
        return mYearOfRelease;
    }

    /*
   retrieve and return the image ID
    */
    public int getmImageResource() {
        return mImageResource;
    }

    /*
   retrieve and return the song audio
    */
    public int getmAudio() {
        return mAudio;
    }


    /**
     * create a new myMusicModel  object
     *
     * @param mSongTitle     the text  that the user is already familiar with
     *                       ( such as his favorite song ).
     * @param mSingerName    the  name of the singer who sing the song
     * @param mImageResource the image which will be added next to each item in the list to help
     *                       the user determine the song they want
     * @param mAudio         the audio which will be played  and the user going to hear
     */

    public myMusicModel(String mSongTitle, String mSingerName, String mYearOfRelease, int mImageResource, int mAudio) {
        this.mSongTitle = mSongTitle;
        this.mSingerName = mSingerName;
        this.mYearOfRelease = mYearOfRelease;
        this.mImageResource = mImageResource;
        this.mAudio = mAudio;
    }

    /**
     * create a new myMusicModel  object
     *
     * @param mSongTitle  the text  that the user is already familiar with
     *                    ( such as his favorite song ).
     * @param mSingerName the  name of the singer who sing the song
     * @param mAudio      the audio which will be played  and the user going to hear
     */

    public myMusicModel(String mSongTitle, String mSingerName, String mYearOfRelease, int mAudio) {
        this.mSongTitle = mSongTitle;
        this.mSingerName = mSingerName;
        this.mYearOfRelease = mYearOfRelease;
        this.mAudio = mAudio;
    }


    // setters to set the data if needed
    public void setmSongTitle(String mSongTitle) {
        this.mSongTitle = mSongTitle;
    }

    public void setmSingerName(String mSingerName) {
        this.mSingerName = mSingerName;
    }

    public void setmYearOfRelease(String mYearOfRelease) {
        this.mYearOfRelease = mYearOfRelease;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public void setmAudio(int mAudio) {
        this.mAudio = mAudio;
    }
}
