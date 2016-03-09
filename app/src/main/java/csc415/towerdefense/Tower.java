package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.CallSuper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sam on 3/5/2016.
 */

public abstract class Tower {

    public int width  = 64;
    public int height = 64;

    public int range = 100;
    public long damage = 2;
    public float delayFrames = 30;
    public int framesSinceShot = 0;
    public long cost = 50;
    public long nextUpgradeCost = 50;

    public int level = 1;

    public float damageRatio = 1.2f;
    public float rangeRatio = 1.07f;
    public float delayRatio = 0.9f;
    public float costRatio = 1.5f;

    public Enemy currentTarget;
    public static Bitmap imageBase;

    public Bitmap rotatedTurret;

    public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private ArrayList<Projectile> removeThese = new ArrayList<Projectile>();

    public Vector2f pos;
    Matrix m = new Matrix();
    Paint paint = new Paint();

    public abstract void draw(Canvas c);

    public void draw(Canvas c, Bitmap imageTurret){

        c.drawBitmap(imageBase, pos.x * width, pos.y * height, null);
        if(currentTarget != null){
            m.setRotate(-90 + (float) Math.toDegrees(Math.atan2(pos.multipliedBy(64).plus(new Vector2f(32, 32)).minus(currentTarget.pos.plus(currentTarget.halfSize)).y, pos.multipliedBy(64).plus(new Vector2f(32, 32)).minus(currentTarget.pos.plus(currentTarget.halfSize)).x)), 32, 32);
        }
        rotatedTurret = Bitmap.createBitmap(imageTurret.getWidth(), imageTurret.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas temp = new Canvas(rotatedTurret);
        temp.drawBitmap(imageTurret, m, paint);


        c.drawBitmap(rotatedTurret, pos.x * width, pos.y * height, null);

    }


@CallSuper
    public void update(){
    findTarget();

    framesSinceShot++;

    for(Projectile p : projectiles){
        if(p.hasHitTarget){
            removeThese.add(p);
        }
    }
    projectiles.removeAll(removeThese);
    removeThese.clear();



    }

    public Tower(int x, int y){
        pos = new Vector2f(x, y);

        imageBase = Bitmap.createScaledBitmap(imageBase, 64, 64, false);
        paint.setAntiAlias(true);
    }


    private void findTarget(){
        Enemy furthest = null;

        for(Enemy e : GameView.currentWave.enemies){
            if(e.pos.plus(e.halfSize).distance(pos.multipliedBy(64).plus(new Vector2f(32, 32))) < range){
                if(e.isDying) continue;
                if(furthest == null){
                    furthest = e;
                }
                if(e.distance > furthest.distance){
                    furthest = e;
                }
            }
        }
        currentTarget = furthest;

    }


    public abstract void upgradeTower(boolean takeMoney);

    public abstract float getNextDelay();
    public abstract long getNextDamage();
    public abstract int getNextRange();

    public abstract void drawStats(Canvas c, Paint p);

}
