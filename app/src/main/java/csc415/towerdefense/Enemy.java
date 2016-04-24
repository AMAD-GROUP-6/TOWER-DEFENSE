package csc415.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Vibrator;
import android.support.annotation.CallSuper;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Sam on 3/1/2016.
 */
public abstract class Enemy {

    public final int MAX_HEIGHT = 60;
    public final int MAX_WIDTH = 60;
    public int distance = 0;
    public long baseHealth = 10;
    public long health = 10;
    public long apparentHealth = 10;
    public float baseSpeed = 3;

    public float moneyReward = 1;

    public Bitmap memberimage;
    public Bitmap memberimagewhite;

    boolean hasEscaped = false;
    boolean isDead = false;
    boolean isDying = false;

    public int width = 32, height = 32;
    public Vector2f halfSize = new Vector2f(width / 2, height / 2);
    public static Map map;
    public Vector2f offset;
    public Vector2f pos = new Vector2f(-100, 166 - height / 2);
    private int pointIndex = 0;
    private Vector2f currentTarget = map.checkPoints[0].minus(halfSize);

    public int frozenFramesLeft = 0;
    public boolean frozen = false;


    public Enemy(int width, int height, float speed, long health, float moneyReward) {

        this.width = width;
        this.height = height;

        baseHealth = health;
        this.health = health;
        this.apparentHealth = health;
        baseSpeed = speed;

        this.moneyReward = moneyReward;


        pointIndex = 0;

        //used to make the center of the monster follow the path
        //not the top left corner
        halfSize = new Vector2f(width / 2, height / 2);


        //Randomness in mob paths
        int dx = (int) (Math.random() * (MAX_WIDTH - width) / 2 * (Math.random() < 0.5f ? 1 : -1));
        int dy = (int) (Math.random() * (MAX_HEIGHT - height) / 2 * (Math.random() < 0.5f ? 1 : -1));
        offset = new Vector2f(dx, dy);

        //Apply the randomness to the position and destination
        pos = map.checkPoints[0].plus(offset);
        currentTarget = map.checkPoints[0].minus(halfSize).plus(offset);
    }


    public abstract void draw(Canvas c);

    @CallSuper
    public void update() {
        if (!hasEscaped && !isDead) {
            if(!frozen){
                if (pos.distance(currentTarget) > baseSpeed) {

                    Vector2f movement = currentTarget.minus(pos);
                    movement.normalize();
                    movement.multiply(baseSpeed);

                    pos.add(movement);

                } else {
                    if (pointIndex < map.checkPoints.length - 1) {
                        currentTarget = (map.checkPoints[++pointIndex]).minus(halfSize).plus(offset);
                        Vector2f movement = currentTarget.minus(pos);
                        movement.normalize();
                        movement.multiply(baseSpeed);

                        pos.add(movement);
                    } else {
                        hasEscaped = true;
                        GameView.lives--;

                        ((Vibrator)SharedState.context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(100);

                        //Call the next line last in case of dead code
                        GameView.currentWave.enemies.remove(this);
                    }

                }
                distance+=baseSpeed;
            } else{
                if(frozenFramesLeft > 0){
                    frozenFramesLeft--;
                }else{
                    frozen = false;
                }
            }

        }


    }

    public void damage(long amount){
        health -= amount;
        if(health <= 0){
            isDead = true;
            GameView.money += moneyReward;
            //Call this next line last in case of dead code
            GameView.currentWave.enemies.remove(this);
        }

        changeBitmapColor(memberimagewhite, Color.HSVToColor(new float[]{health * (float)(300 / (double)baseHealth) + 60 , 1f, 1f}));
    }


    public void freeze(int framesToFreeze){
        frozenFramesLeft = framesToFreeze;
        frozen = true;
    }


    public void changeBitmapColor(Bitmap sourceBitmap, int color) {

        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0,
                sourceBitmap.getWidth()  , sourceBitmap.getHeight() );
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);

        memberimage = resultBitmap;

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
    }


}
