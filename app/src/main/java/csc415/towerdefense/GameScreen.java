package csc415.towerdefense;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameScreen extends Activity {

    public static boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView
                (this));

    }

    protected void onPause(){
        super.onPause();

        if(GameView.currentWave.isFinished){
            GameView.saveGameInfo();
        }

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

    }

}
