package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Sam on 3/7/2016.
 */
public class IceProjectile extends Projectile{

    int splashRange = 0;
    int freezeTime = 0;

    public static Bitmap image;

    public IceProjectile(Enemy target, float speed, long damage, Vector2f pos, int splashRange, int freezeTime){
        super(target, speed, damage, pos);
        this.splashRange = splashRange;
        this.freezeTime = freezeTime;

    }

    public void draw(Canvas c){
        c.drawBitmap(image, pos.x, pos.y, null);
    }


    public void update(){
        super.update();

        if(hasHitTarget){
            if(!target.frozen){
                target.freeze(freezeTime);
            }

            for(Enemy e : GameView.currentWave.enemies){
                if(pos.distance(e.pos.plus(e.halfSize)) < splashRange){
                    if(!e.frozen){
                        e.freeze(freezeTime);
                    }
                }
            }

        }

    }


}
