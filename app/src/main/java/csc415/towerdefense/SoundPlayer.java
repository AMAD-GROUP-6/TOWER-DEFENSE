package csc415.towerdefense;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

/**
 * Created by Josh on 3/20/2016.
 */


/* IMPORTANT:

    to use this class to play a sound, make a SoundPlayer var as a field in a class,
    then instatiate it with "new SoundPlayer(this, true, R.raw.mysoundfile);
    the second param 'true' is whether the sound should loop or not

    store the sound file (preferably in .ogg (vorbis) format) You can use the free program "Audacity"
    to convert to ogg and lessen the quality of the sound so it takes less space if you want.

    use SoundPlayer.stop() to stop the sound (for onPause)

    you can find more methods in android.os.AsyncTask, from which this is extended
 */

public class SoundPlayer extends AsyncTask<Void, Void, Void> {
    private Context c;
    boolean loop;
    int id;

    public SoundPlayer(Context c,boolean loop,int id){
        this.c = c;
        this.loop = loop;
        this.id = id;
        doInBackground();
    }
    @Override
    protected Void doInBackground(Void... params){
        MediaPlayer mp = MediaPlayer.create(this.c,this.id);
        mp.setLooping(loop);
        mp.start();
        return null;
    }
}