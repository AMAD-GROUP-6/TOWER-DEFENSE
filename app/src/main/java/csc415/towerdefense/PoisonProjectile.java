package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Sam on 3/8/2016.
 */
public class PoisonProjectile extends Projectile{

    public static Bitmap image;

    float poisonPercent = 1f;
    int poisonFrames = 0;
    int framesPassed = 0;

    public PoisonProjectile(Enemy target, float speed, long damage, Vector2f pos, int poisonFrames, float poisonPercent){
        super(target, speed, damage, pos);
        this.poisonFrames = poisonFrames;
        this.poisonPercent = poisonPercent;
    }

    public void draw(Canvas c){
        if(framesPassed < 1) {
            c.drawBitmap(image, pos.x, pos.y, null);
        }
    }

    public void update(){
        super.update();

        if(hasHitTarget){

            if(framesPassed < poisonFrames){
                hasHitTarget = false;

                target.health = (long)(target.health*poisonPercent);
                target.apparentHealth = (long)(target.apparentHealth *poisonPercent);
            }

            framesPassed++;
        }

    }


}
