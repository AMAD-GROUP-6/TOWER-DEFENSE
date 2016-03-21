package csc415.towerdefense;

import android.app.Activity;
import android.content.Intent;
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
    SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

        this.soundPlayer = new SoundPlayer(this,true,R.raw.background_music);

    }

}
