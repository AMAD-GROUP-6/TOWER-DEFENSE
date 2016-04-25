package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Sam on 3/5/2016.
 */
public class Pellet extends Projectile {

    public static Bitmap image;

    public Pellet(Enemy target, float speed, long damage, Vector2f pos){
        super(target, speed, damage, pos);
        image = Bitmap.createScaledBitmap(image, 5, 5, false);
        new SoundPlayer(SharedState.context,false,R.raw.boop,false);
    }

    public void draw(Canvas c){
         c.drawBitmap(image, pos.x, pos.y, null);
    }
}
