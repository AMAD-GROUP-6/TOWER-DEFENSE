package csc415.towerdefense;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sam on 3/1/2016.
 */
public class GameView extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback {


    public static final int WIDTH = 640;
    public static final int HEIGHT = 960;

    public static float widthRatio;
    public static float heightRatio;

    public static int lives = 0;
    public static long money = 0;

    public static float interest = 0;
    public static int interestLevel = 0;
    public static int interestLevelCost = 0;

    public static boolean loadGame = true;

    private GameThread thread;

    public static Map map;
    public static UI ui;
    public static ArrayList<Tower> towers = new ArrayList<Tower>();

    public static Context myContext;
    public static Wave currentWave;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        myContext = context;
        setOnTouchListener(this);



        thread = new GameThread(getHolder(), this);

    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        towers.clear();
        money = 750;
        lives = 100;
        interestLevelCost = 1000;
        interestLevel = 1;
        interest = 0.05f;

        //Must be created before any enemies////////////
        map = new Map(BitmapFactory.decodeResource(getResources(), R.drawable.map),
                new Vector2f[]  {new Vector2f(-100, 96), new Vector2f(160, 96), new Vector2f(160, 544),
                                 new Vector2f(544, 544), new Vector2f(544, 352),
                                 new Vector2f(288, 352), new Vector2f(288, 160),
                                 new Vector2f(544, 160), new Vector2f(544, -70)},
                new Point[] {new Point(0, 1), new Point(1, 1),new Point(2, 1),
                        new Point(2, 2),new Point(2, 3),new Point(2, 4),
                        new Point(2, 5),new Point(2, 6),new Point(2, 7),
                        new Point(2, 8),new Point(3, 8),new Point(4, 8),
                        new Point(5, 8),new Point(6, 8),new Point(7, 8),
                        new Point(8, 8),new Point(8, 7),new Point(8, 6),
                        new Point(8, 5),new Point(7, 5),new Point(6, 5),
                        new Point(5, 5),new Point(4, 5),new Point(4, 4),
                        new Point(4, 3),new Point(4, 2),new Point(5, 2),
                        new Point(6, 2),new Point(7, 2),new Point(8, 2),
                        new Point(8, 1), new Point(8, 0)});
        Enemy.map = map;

        //Sets enemy image, not scaling this so there can be different sizes of the same
        //type of enemy
        BasicEnemy.image = BitmapFactory.decodeResource(getResources(), R.drawable.tempenemy);

        //Sets all towers base image
        Tower.imageBase = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.towerbase), 64, 64, false);

        //Sets pellet's image
        //Projectile.image = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pellet), 5, 5, false);
        Pellet.image = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pellet), 5, 5, false);
        IceProjectile.image =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.iceprojectile), 7, 7, false);
        BombProjectile.image =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bombprojectile), 11, 11, false);
        PoisonProjectile.image =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.poisonprojectile), 5, 5, false);


        //Sets tower's turret image
        PelletTower.imageTurret = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.testtowerturret), 64, 64, false);
        FreezeTower.imageTurret = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.freezetowerturret), 64, 64, false);
        CannonTower.imageTurret = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bombtowerturret), 64, 64, false);
        PoisonTower.imageTurret = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.poisontowerturret), 64, 64, false);

        UI.imageBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ui), 640, 320, false);
        UI.imageOverlay = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.uioverlay), 640, 320, false);
        UI.imagePopupBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.popupbackground), 480, 320, false);
        UI.imageTowerHighlight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.towerhighlight), 74, 74, false);
        UI.imageTowerHighlightRed = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.towerhighlightred), 74, 74, false);
        UI.imageHearts = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 65, 65, false);
        UI.imageMoney = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.money), 65, 65, false);
        UI.imagePercentInterest = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.interest), 42, 42, false);


        UI.imageConfirm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.confirm), 145, 145, false);
        UI.imageCancel = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cancel), 145, 145, false);

        UI.imageTowerBase = Bitmap.createScaledBitmap(Tower.imageBase, 145, 145, false);
        UI.imagePelletTowerTurret = Bitmap.createScaledBitmap(PelletTower.imageTurret, 145, 145, false);
        UI.imageFreezeTowerTurret = Bitmap.createScaledBitmap(FreezeTower.imageTurret, 145, 145, false);
        UI.imageBombTowerTurret = Bitmap.createScaledBitmap(CannonTower.imageTurret, 145, 145, false);
        UI.imagePoisonTowerTurret = Bitmap.createScaledBitmap(PoisonTower.imageTurret, 145, 145, false);

        UI.imageReadyButton = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.readybutton), 192, 128, false);

        UI.imageUpgradeButton = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.upgradebutton), 145, 145, false);

        UI.imageNextButton =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nextbutton), 145, 145, false);

        UI.imageBackButton =  Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.backbutton), 145, 145, false);


        ui = new UI();
        ////////////////////////////////////////////////

        if(!thread.running){
            currentWave = new Wave(0);
            currentWave.isFinished = true;
           // currentWave.start();
            thread.start();
        }

        if(loadGame){
            loadGameInfo();
        }

        saveGameInfo();

        widthRatio = WIDTH / (float)getWidth();
        heightRatio = HEIGHT/ (float)getHeight();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Vector2f temp = new Vector2f(event.getX(), event.getY());
        temp = screenCoordinatesToCanvasCoordinates(temp);

        //Log.d("touched", "Screen: (" + event.getX() + ", " + event.getY() + ")");
        Log.d("touched", "Canvas: (" + temp.x + ", " + temp.y + ")");

        return ui.touched(temp);
    }


    public void update(){

        for(Enemy e : currentWave.enemies){
            e.update();
        }

        for(Tower t : towers){
            t.update();
            for(Projectile p : t.projectiles){
                p.update();
            }

        }

    }


    @Override
    public void draw(Canvas canvas)
    {

        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight() / (HEIGHT*1.f);

        if(canvas!=null) {
            super.draw(canvas);
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);

            map.draw(canvas);

            for(Enemy e : currentWave.enemies){
                e.draw(canvas);
            }


            for(Tower t : towers){
                t.draw(canvas);

            }

            for(Tower t : towers){
                for(Projectile p : t.projectiles){
                    p.draw(canvas);
                }
            }


            ui.draw(canvas);


            canvas.restoreToCount(savedState);
        }
    }


    public static Vector2f screenCoordinatesToCanvasCoordinates(Vector2f a){
        return new Vector2f(a.x * widthRatio, a.y * heightRatio);
    }


    public static void startNextWave(){
        currentWave = new Wave(currentWave.waveNumber + 1);
        currentWave.start();
    }


    public static void saveGameInfo(){
        String filename = "save.dat";

        try {
            File file= new File(myContext.getFilesDir(), filename);
            FileWriter writer = new FileWriter(file);

            writer.append(GameView.currentWave.waveNumber +"\n");
            writer.append(GameView.money+"\n");
            writer.append(GameView.lives + "\n");
            writer.append(GameView.interestLevel + "\n");

            for(Tower t : towers){

                writer.append( t.getClass() + ":" +  t.pos.x + ":" + t.pos.y + ":" + t.level + "\n");

            }

            writer.append("*");

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void loadGameInfo(){

        towers.clear();
        currentWave.enemies.clear();

        map = new Map(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.map),
                new Vector2f[]  {new Vector2f(-100, 96), new Vector2f(160, 96), new Vector2f(160, 544),
                        new Vector2f(544, 544), new Vector2f(544, 352),
                        new Vector2f(288, 352), new Vector2f(288, 160),
                        new Vector2f(544, 160), new Vector2f(544, -70)},
                new Point[] {new Point(0, 1), new Point(1, 1),new Point(2, 1),
                        new Point(2, 2),new Point(2, 3),new Point(2, 4),
                        new Point(2, 5),new Point(2, 6),new Point(2, 7),
                        new Point(2, 8),new Point(3, 8),new Point(4, 8),
                        new Point(5, 8),new Point(6, 8),new Point(7, 8),
                        new Point(8, 8),new Point(8, 7),new Point(8, 6),
                        new Point(8, 5),new Point(7, 5),new Point(6, 5),
                        new Point(5, 5),new Point(4, 5),new Point(4, 4),
                        new Point(4, 3),new Point(4, 2),new Point(5, 2),
                        new Point(6, 2),new Point(7, 2),new Point(8, 2),
                        new Point(8, 1), new Point(8, 0)});
        Enemy.map = map;

        String filename = "save.dat";
        File file = new File(myContext.getFilesDir(),filename);

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String[] parts;

            line = br.readLine();

            currentWave = new Wave(Integer.parseInt(line));
            currentWave.isFinished = true;

            line = br.readLine();
            money = Long.parseLong(line);

            line = br.readLine();
            lives = Integer.parseInt(line);

            line = br.readLine();
            int level = Integer.parseInt(line);

            for(int i = 0; i < level - 1; i++){
                upgradeInterest(false);
            }


            while ((line = br.readLine()) != "*") {
                Tower temp = null;
                parts = line.split(":"); //can't split by space in android?(????)
                parts[0] = parts[0].substring(26);

                map.placeable[(int) Float.parseFloat(parts[2])][(int)Float.parseFloat(parts[1])] = 1;

                switch(parts[0]){

                    case "PelletTower":
                        towers.add(temp = new PelletTower((int)Float.parseFloat(parts[1]),(int) Float.parseFloat(parts[2])));
                        for(int i = 1; i < Integer.parseInt(parts[3]); i++){
                            temp.upgradeTower(false);
                        }

                        break;
                    case "CannonTower":
                        towers.add(temp = new CannonTower((int)Float.parseFloat(parts[1]),(int) Float.parseFloat(parts[2])));
                        for(int i = 1; i < Integer.parseInt(parts[3]); i++){
                            temp.upgradeTower(false);
                        }

                        break;
                    case "FreezeTower":
                        towers.add(temp = new FreezeTower((int)Float.parseFloat(parts[1]),(int) Float.parseFloat(parts[2])));
                        for(int i = 1; i < Integer.parseInt(parts[3]); i++){
                            temp.upgradeTower(false);
                        }
                        break;

                    case "PoisonTower":
                        towers.add(temp = new PoisonTower((int)Float.parseFloat(parts[1]),(int) Float.parseFloat(parts[2])));
                        for(int i = 1; i < Integer.parseInt(parts[3]); i++){
                            temp.upgradeTower(false);
                        }
                        break;
                }
            }


            br.close();
        }
        catch (Exception e) {
            //You'll need to add proper error handling here
            Log.e("tower-defense","Fatal error: couldn't load save file");
        }

    }



    public static void upgradeInterest(boolean takeMoney){

        if(takeMoney){
            money -= interestLevelCost;
        }

        interest = getNextInterest();
        interestLevel++;
        interestLevelCost += 100;



    }

    public static float getNextInterest(){

        return interest + 0.01f;

    }


}
