package csc415.towerdefense;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameScreen extends Activity {

    public static boolean isPaused = false;

    SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView
                (this));

        this.soundPlayer = null;

    }

    protected void onPause(){
        super.onPause();

        if(GameView.currentWave.isFinished){
            GameView.saveGameInfo();
        }
        if(this.soundPlayer!=null)this.soundPlayer.pause();
        isPaused = true;



    }

    protected void onResume(){
        super.onResume();

        if(isPaused){

            GameView g = new GameView(this);
            g.loadGame = true;
            setContentView(g);
        }

        isPaused = false;

        SharedPreferences sharedPref = getSharedPreferences("androidTowerDefensePrefs", Context.MODE_PRIVATE);
        OptionsActivity.startingVolume = sharedPref.getInt("volume", 100);

        if(SharedState.musicenabled && (this.soundPlayer == null || this.soundPlayer.isStopped()))this.soundPlayer = new SoundPlayer(this,true,R.raw.background_music, true);

        if(this.soundPlayer!=null)this.soundPlayer.resume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       if(this.soundPlayer!=null) this.soundPlayer.stop();

    }
}
