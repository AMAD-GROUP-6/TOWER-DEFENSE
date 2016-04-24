package csc415.towerdefense;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

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
    private boolean loop;
    private int id;
    private MediaPlayer mp;
    private boolean stopped;
    public static ArrayList<SoundPlayer> allSoundPlayers = new ArrayList<SoundPlayer>();
    public static int startingVolume = 100;

    public SoundPlayer(Context c,boolean loop,int id, boolean lastsForever){
        this.c = c;
        this.loop = loop;
        this.id = id;
        doInBackground();
        this.stopped = false;

        if(lastsForever){
            allSoundPlayers.add(this);
        }
    }
    @Override
    protected Void doInBackground(Void... params){

        mp = MediaPlayer.create(this.c,this.id);
        mp.setLooping(loop);
        this.setVolume(OptionsActivity.startingVolume);
        mp.start();

        return null;
    }

    public void stop(){
        mp.stop();
        this.cancel(true);
        this.stopped = true;
    }

    public void pause(){
        mp.pause();
    }
    public void resume(){
        mp.start();
    }

  public void restart(){
      mp.seekTo(0);
      resume();
  }



    public boolean isStopped(){
        return this.stopped;
    }

    public void setVolume(float volume){
        if(volume > 100){volume = 100;}
        if (volume < 0){volume = 0;}
        volume /= 100.f;
        mp.setVolume(volume, volume);
    }
}