package csc415.towerdefense;

import android.app.Activity;
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

            txtVolume.setText(volume.getProgress() + "");

        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}