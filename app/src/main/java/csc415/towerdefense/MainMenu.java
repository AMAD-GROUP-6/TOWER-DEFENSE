package csc415.towerdefense;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends Activity{

    ImageButton playButton;
    ImageButton optionsButton;
    ImageButton loadButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedState.context = getApplicationContext();
        //Make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(SharedState.hidenav)
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_main_menu);

        //instantiate buttons / make backgrounds transparent
        playButton = (ImageButton)findViewById(R.id.btnPlay);
        playButton.setBackgroundColor(Color.TRANSPARENT);
        optionsButton = (ImageButton)findViewById(R.id.btnOptions);
        optionsButton.setBackgroundColor(Color.TRANSPARENT);
        loadButton = (ImageButton)findViewById(R.id.btnLoadGame);
        loadButton.setBackgroundColor(Color.TRANSPARENT);

        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Load game activity
                Intent myIntent = new Intent(getApplicationContext(), GameScreen.class);
                GameView.loadGame = false;
                GameScreen.isPaused = false;
                startActivity(myIntent);
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Load options activity
                Intent myIntent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(myIntent);

            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), GameScreen.class);
                GameView.loadGame = true;
                startActivity(myIntent);

            }
        });




    }

    @Override
    public void onDestroy(){

        super.onDestroy();
    }

    @Override
    public void onResume(){
        super.onResume();

        SharedPreferences sharedPref = getSharedPreferences("androidTowerDefensePrefs", Context.MODE_PRIVATE);
        OptionsActivity.startingVolume = sharedPref.getInt("volume", 100);

        Log.d("Volume: ", OptionsActivity.startingVolume + "" );

    }

    @Override
    public void onPause(){
        super.onPause();



    }


}

class SharedState {
    public static Context context;
    public static boolean soundenabled=true,musicenabled=true,hidenav=true;
}
