package csc415.towerdefense;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OptionsActivity extends Activity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    SeekBar volume;
    TextView txtVolume;
    Switch musicEnabledSwitch;
    Switch soundEnabledSwitch;
    Switch hideNavBar; // for devices that don't have hardware nav buttons (home, back, recents), they have on screen buttons

    public static int startingVolume = 0;

    SoundPlayer soundOnChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_options);

        txtVolume = (TextView) findViewById(R.id.txtVolume);
        volume = (SeekBar) findViewById(R.id.seekBarVolume);
        volume.setOnSeekBarChangeListener(this);

        musicEnabledSwitch = (Switch) findViewById(R.id.music_enabled_switch);
        soundEnabledSwitch = (Switch) findViewById(R.id.sound_effects_enabled_switch);
        hideNavBar = (Switch) findViewById(R.id.hide_nav_switch);

        musicEnabledSwitch.setOnCheckedChangeListener(this);
        soundEnabledSwitch.setOnCheckedChangeListener(this);


        soundOnChanged = null;
    }

    @Override
    protected void onResume(){
        super.onResume();

        SharedPreferences sharedPref = getSharedPreferences("androidTowerDefensePrefs", Context.MODE_PRIVATE);
        OptionsActivity.startingVolume = sharedPref.getInt("volume", 100);



        if(this.soundOnChanged == null || this.soundOnChanged.isStopped()) soundOnChanged = new SoundPlayer(this, false, R.raw.change_settings, false);

        volume.setProgress(OptionsActivity.startingVolume);
    }

    @Override
    public void onCheckedChanged(CompoundButton s, boolean checked) {
       /* if(s == this.musicEnabledSwitch){

        }
        else if (s == this.soundEnabledSwitch) {

        }*/
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.equals(volume)) {


                soundOnChanged.setVolume(volume.getProgress());

                soundOnChanged.restart();


            txtVolume.setText(volume.getProgress() + "");
            SoundPlayer.startingVolume = volume.getProgress();
            SharedPreferences sharedPref = getSharedPreferences("androidTowerDefensePrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("volume", volume.getProgress());
            editor.commit();

            for(SoundPlayer s : SoundPlayer.allSoundPlayers){
                s.setVolume(sharedPref.getInt("volume", 100));
            }



        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}