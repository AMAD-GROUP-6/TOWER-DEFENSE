package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Sam on 3/7/2016.
 */
public class BombProjectile extends Projectile{

    int splashRange = 0;

    public static Bitmap image;

    public BombProjectile(Enemy target, float speed, long damage, Vector2f pos, int splashRange){
        super(target, speed, damage, pos);
        this.splashRange = splashRange;

    }

    public void draw(Canvas c){
        c.drawBitmap(image, pos.x, pos.y, null);
    }


    public void update(){
        super.update();

        if(hasHitTarget){
            for(Enemy e : GameView.currentWave.enemies){
                float temp = 1;
                if( (temp = pos.distance(e.pos.plus(e.halfSize))) < splashRange && e != target){
                    e.damage((int)(damage/((temp+1)/20)));

                    e.apparentHealth -= (int)(damage/((temp+1)/20));
                }
            }

        }

    }

}
