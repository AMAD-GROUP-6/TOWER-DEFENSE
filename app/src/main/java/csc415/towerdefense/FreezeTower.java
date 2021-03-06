package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Sam on 3/7/2016.
 */
public class FreezeTower extends Tower {

    int splashRange = 25;
    int freezeTime = 10;
    public static Bitmap imageTurret;


    public FreezeTower(int x, int y){
        super(x, y);
        cost = 200;
        delayFrames = 100;
        damage = 0;
        nextUpgradeCost = 100;
    }


    public void update(){
        super.update();

        if(framesSinceShot >= delayFrames - 1 && currentTarget != null){
            projectiles.add(new IceProjectile(currentTarget, 8, damage, new Vector2f(pos.x * 64 + 32, pos.y * 64 + 32), splashRange, freezeTime));
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
        splashRange = getNextSplashRange();
        level++;
    }

    @Override
    public float getNextDelay(){
        return Math.max(20, delayFrames* delayRatio);
    }

    @Override
    public long getNextDamage() {
        if(damage == 0 && level == 4){
           return 10;
        }

        return (int)(damage * 1.2f);
    }

    @Override
    public int getNextRange() {
        return (int)(range * rangeRatio);
    }

    public int getNextSplashRange(){
        return splashRange + 2;
    }

    public void drawStats(Canvas c, Paint p){

        c.drawText("Damage: ", 145 + 10 + 50, 640 + 80, p);
        c.drawText("" + damage, 145 + 10 + 50, 640 + 120, p);
        c.drawText("  →   " + getNextDamage() + "", 10 + 145 + 10 + 100, 640 + 120, p);

        c.drawText("Splash range: ", 145 + 10 + 50, 640 + 160, p);
        c.drawText("" + splashRange, 145 + 10 + 50, 640 + 200, p);
        c.drawText("  →   " + getNextSplashRange() + "", 10 + 145 + 10 + 100, 640 + 200, p);

        c.drawText("Delay: ", 145 + 10 + 50, 640 + 240, p);
        c.drawText(String.format("%.1f", delayFrames), 145 + 10 + 50, 640 + 280, p);
        c.drawText("  →   " + String.format("%.1f", getNextDelay()), 10 + 145 + 10 + 100, 640 + 280, p);

        c.drawText("Level: " + level, 145 + 10 + 50 + 200, 640 + 80, p);

    }


}
