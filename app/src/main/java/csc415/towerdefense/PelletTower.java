package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Sam on 3/5/2016.
 */
public class PelletTower extends Tower {

    public static Bitmap imageTurret;



    public PelletTower(int x, int y){
        super(x, y);
        cost = 50;
    }


    public void update(){
        super.update();

        if(framesSinceShot >= delayFrames - 1 && currentTarget != null){
            projectiles.add(new Pellet(currentTarget, 8, damage, new Vector2f(pos.x * 64 + 32, pos.y * 64 + 32)));
            currentTarget.apparentHealth-= damage;


            if(currentTarget.apparentHealth <= 0){
                currentTarget.isDying = true;
            }
            framesSinceShot = 0;
        }
    }

    public void draw(Canvas c){
        draw(c, imageTurret);
    }


    public void upgradeTower(boolean takeMoney){
        if(takeMoney)
        GameView.money -= nextUpgradeCost;
        cost += nextUpgradeCost;
        nextUpgradeCost = (int)(nextUpgradeCost * costRatio);

        delayFrames = getNextDelay();

        damage = getNextDamage();

        range = getNextRange();

        Log.d("Stats: ", "Damage: " + damage + " Range: " + range + " Delay: " + delayFrames);
        level++;
    }

    @Override
    public float getNextDelay(){
        return Math.max(1.0f, delayFrames* delayRatio);
    }

    @Override
    public long getNextDamage() {
        if((level+1)%5 == 0){
           return (damage*2);
        }else {
            return damage + 2;
        }
    }

    @Override
    public int getNextRange() {
        return (int)(range * rangeRatio);
    }

    public void drawStats(Canvas c, Paint p){

        c.drawText("Damage: ", 145 + 10 + 50, 640 + 50, p);
        c.drawText("" + damage, 145 + 10 + 50, 640 + 90, p);
        c.drawText("  →   " + getNextDamage() + "", 10 + 145 + 10 + 100, 640 + 90, p);

        c.drawText("Range: ", 145 + 10 + 50, 640 + 150, p);
        c.drawText("" + range, 145 + 10 + 50, 640 + 190, p);
        c.drawText("  →   " + getNextRange() + "", 10 + 145 + 10 + 100, 640 + 190, p);

        c.drawText("Delay: ", 145 + 10 + 50, 640 + 250, p);
        c.drawText(String.format("%.1f", delayFrames), 145 + 10 + 50, 640 + 290, p);
        c.drawText("  →   " + String.format("%.1f", getNextDelay()), 10 + 145 + 10 + 100, 640 + 290, p);

        c.drawText("Level: " + level, 145 + 50 + 300, 640 + 50, p);
    }


}
