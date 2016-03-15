package csc415.towerdefense;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OptionsActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    SeekBar volume;
    TextView txtVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_options);

        txtVolume = (TextView) findViewById(R.id.txtVolume);
        volume = (SeekBar) findViewById(R.id.seekBarVolume);
        volume.setOnSeekBarChangeListener(this);

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
