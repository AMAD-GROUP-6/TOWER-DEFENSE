package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by Sam on 3/1/2016.
 */
public class BasicEnemy extends Enemy {

    public static Bitmap image;

    public BasicEnemy(int width, int height, long health, float speed, long moneyReward){
        super(width, height, speed, health, moneyReward);
        this.memberimage = Bitmap.createScaledBitmap(image, width, height, false);
        this.memberimagewhite = Bitmap.createScaledBitmap(image, width, height, false);

        changeBitmapColor(memberimagewhite, Color.HSVToColor(new float[]{health * 300 / (float) baseHealth + 60, 1f, 1f}));

    }


    @Override
    public void draw(Canvas c) {
        c.drawBitmap(memberimage, pos.x , pos.y, null);
    }



}
