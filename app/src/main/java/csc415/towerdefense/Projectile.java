package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.CallSuper;

/**
 * Created by Sam on 3/5/2016.
 */
public abstract class Projectile {

    float speed;
    long damage;

    public Enemy target;

    Vector2f pos;
    boolean hasHitTarget = false;



    public Projectile(Enemy target, float speed, long damage, Vector2f pos){
        this.target = target;
        this.speed = speed;
        this.damage = damage;
        this.pos = pos;

        Vector2f movement = target.pos.minus(pos).plus(target.halfSize);
        movement.normalize();
        movement.multiply(20);
        pos.add(movement);
    }

@CallSuper
    public void update(){


        if(!hasHitTarget){
            if(target.pos.plus(target.halfSize).distance(pos) > speed + 0.1f){
                Vector2f movement = target.pos.minus(pos).plus(target.halfSize);
                movement.normalize();
                movement.multiply(speed);
                pos.add(movement);
            }else{
                if(target.isDead){
                    hasHitTarget = true;
                    return;
                }
                target.damage(this.damage);
                hasHitTarget = true;
            }
        }

    }


    public abstract void draw(Canvas c);

}
