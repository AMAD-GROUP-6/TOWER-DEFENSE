package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Sam on 3/8/2016.
 */
public class PoisonTower extends Tower {

    float poisonPercent = 0.999f;
    int poisonFrames = 10;
    public static Bitmap imageTurret;

    public PoisonTower(int x, int y){
        super(x, y);
        cost = 3000;
        delayFrames = 80;
        damage = 0;
        nextUpgradeCost = 1000;
        range = 150;
    }


    public void update(){
        super.update();

        if(framesSinceShot >= delayFrames - 1 && currentTarget != null){
            projectiles.add(new PoisonProjectile(currentTarget, 8, damage, new Vector2f(pos.x * 64 + 32, pos.y * 64 + 32), poisonFrames, poisonPercent));

            currentTarget.apparentHealth-= damage;

           // for(int i = 0; i < poisonFrames; i++){
           //     currentTarget.apparentHealth = (long)(currentTarget.apparentHealth * poisonPercent);
           // }

            if(currentTarget.apparentHealth <= 0){
                currentTarget.isDying = true;
            }

            framesSinceShot = 0;

        }
    }

    public void draw(Canvas c){
        draw(c, imageTurret);
    }

    @Override
    public void upgradeTower(boolean takeMoney) {

        if(takeMoney)
            GameView.money -= nextUpgradeCost;
        cost += nextUpgradeCost;
        nextUpgradeCost = (int)(nextUpgradeCost * costRatio);

        delayFrames = getNextDelay();
        poisonPercent = getNextPoisonPercent();
        poisonFrames = getNextPoisonFrames();

        level++;

    }

    @Override
    public float getNextDelay() {
        return delayFrames-2;
    }

    @Override
    public long getNextDamage() {
        return 0;
    }

    @Override
    public int getNextRange() {
        return 0;
    }

    public float getNextPoisonPercent(){
        return poisonPercent - 0.001f;
    }

    public int getNextPoisonFrames(){
        return poisonFrames + 5;
    }

    @Override
    public void drawStats(Canvas c, Paint p) {

        c.drawText("Poison %: ", 145 + 10 + 50, 640 + 80, p);
        c.drawText(String.format("%.1f", (1-poisonPercent)*100) + "%", 145 + 10 + 50, 640 + 120, p);
        c.drawText("  →   " + String.format("%.1f", (1- getNextPoisonPercent()) * 100) + "%", 10 + 145 + 10 + 100, 640 + 120, p);

        c.drawText("Poison Length: ", 145 + 10 + 50, 640 + 160, p);
        c.drawText("" + poisonFrames, 145 + 10 + 50, 640 + 200, p);
        c.drawText("  →   " + getNextPoisonFrames() + "", 10 + 145 + 10 + 100, 640 + 200, p);

        c.drawText("Delay: ", 145 + 10 + 50, 640 + 240, p);
        c.drawText(String.format("%.1f", delayFrames), 145 + 10 + 50, 640 + 280, p);
        c.drawText("  →   " + String.format("%.1f", getNextDelay()), 10 + 145 + 10 + 100, 640 + 280, p);

        c.drawText("Level: " + level, 145 + 10 + 50 + 200, 640 + 80, p);

    }
}
